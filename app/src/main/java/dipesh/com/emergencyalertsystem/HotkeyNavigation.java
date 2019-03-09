package dipesh.com.emergencyalertsystem;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;

public class HotkeyNavigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, CompoundButton.OnCheckedChangeListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {
    LocationManager locationManager ;
    boolean GpsStatus ;
   // ImageButton imageButton;
    //for Gps co-ordinates
    private TextView locationTv;
    private GoogleApiClient googleApiClient;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final long UPDATE_INTERVAL = 5000, FASTEST_INTERVAL = 5000; // = 5 seconds
    // lists for permissions
    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionsRejected = new ArrayList<>();
    private ArrayList<String> permissions = new ArrayList<>();
    // integer for permissions results request
    private static final int ALL_PERMISSIONS_RESULT = 1011;
    //-----------------------------------------------------------------------------

    SharedPreferences sharedPreferences;
    SettingsActivity settingsActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotkey_navigation);
        locationTv = findViewById(R.id.location);
        Toolbar toolbar = findViewById(R.id.toolbar);
      //  imageButton = findViewById(R.id.sendSMS);
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            toolbar.setTitle(mBundle.getString("Title"));
        }
        settingsActivity = new SettingsActivity(HotkeyNavigation.this);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //switch code check
        Switch securityOnOff = findViewById(R.id.security_on_off);
        boolean security = settingsActivity.checkSecurity();
        String securityState = (security) ? "ON" : "OFF";
        Toast.makeText(this, "Security is " + securityState, Toast.LENGTH_LONG).show();
        securityOnOff.setChecked(security);
        //Toast.makeText(this, "Security Current Position " + (securityOnOff.isChecked() ? "On" : "Off"),Toast.LENGTH_SHORT).show();
        securityOnOff.setOnCheckedChangeListener(this);


        //  permissions  to request location of the users
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);

        permissionsToRequest = permissionsToRequest(permissions);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionsToRequest.size() > 0) {
                requestPermissions(permissionsToRequest.toArray(
                        new String[0]), ALL_PERMISSIONS_RESULT);
            }


        }

        // building google api client
        googleApiClient = new GoogleApiClient.Builder(this).
                addApi(LocationServices.API).
                addConnectionCallbacks(this).
                addOnConnectionFailedListener(this).build();

        //image button onclick
//        imageButton.setOnClickListener(new View.OnClickListener()   {
//            public void onClick(View v)  {
//                try {
//                    sendMessage();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }

    private ArrayList<String> permissionsToRequest(ArrayList<String> wantedPermissions) {
        ArrayList<String> result = new ArrayList<>();

        for (String perm : wantedPermissions) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
        }

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!checkPlayServices()) {
            locationTv.setText("You need to install Google Play Services to use the App properly");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        // stop location updates
        if (googleApiClient != null && googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
            googleApiClient.disconnect();
        }
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);

        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST);
            } else {
                finish();
            }

            return false;
        }

        return true;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        // Permissions ok, we get last location
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if (location != null) {
            locationTv.setText("Latitude : " + location.getLatitude() + "\nLongitude : " + location.getLongitude());
        }

        startLocationUpdates();
    }

    private void startLocationUpdates() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(UPDATE_INTERVAL);
        locationRequest.setFastestInterval(FASTEST_INTERVAL);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "You need to enable permissions to display location !", Toast.LENGTH_SHORT).show();
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            locationTv.setText("Latitude : " + location.getLatitude() + "\nLongitude : " + location.getLongitude());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case ALL_PERMISSIONS_RESULT:
                for (String perm : permissionsToRequest) {
                    if (!hasPermission(perm)) {
                        permissionsRejected.add(perm);
                    }
                }

                if (permissionsRejected.size() > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                            new AlertDialog.Builder(HotkeyNavigation.this).
                                    setMessage("These permissions are mandatory to get your location. You need to allow them.").
                                    setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(permissionsRejected.
                                                        toArray(new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                            }
                                        }
                                    }).setNegativeButton("Cancel", null).create().show();

                            return;
                        }
                    }
                } else {
                    if (googleApiClient != null) {
                        googleApiClient.connect();
                    }
                }

                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Context context = getApplicationContext();
        sharedPreferences = context.getSharedPreferences(SettingsActivity.MyPreferences, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (isChecked) {
            editor.putBoolean(SettingsActivity.securityOnOffs, true);
            Toast.makeText(this, "Security is ON", Toast.LENGTH_LONG).show();
        } else {
            editor.putBoolean(SettingsActivity.securityOnOffs, false);
            Toast.makeText(this, "Security is OFF", Toast.LENGTH_LONG).show();
        }
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hotkey_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.settings) {

            System.out.println("Print ID :" + id);
            // Handle the settings action
            Intent SettingsActivityIntent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(SettingsActivityIntent);

        } else if (id == R.id.setup_hotkey) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void CheckGpsStatus(){

        locationManager = (LocationManager)getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        assert locationManager != null;
        GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }


    public void sendMessage( View view) {

        Context context = getApplicationContext();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        //GPSTracker gpsTracker = new GPSTracker(context);
        //Boolean checkInternet = checkInternetConnection(context);
        sharedPreferences = context.getSharedPreferences(settingsActivity.MyPreferences,
                0);
        SmsManager smsManager = SmsManager.getDefault();
        boolean security = sharedPreferences.getBoolean(settingsActivity.securityOnOffs, false);
        String message = sharedPreferences.getString(settingsActivity.Messages, null);
        String pContact = sharedPreferences.getString(settingsActivity.pContacts, null);
        String sContact = sharedPreferences.getString(settingsActivity.sContacts, null);
        if(security) {
            if (sharedPreferences.contains(settingsActivity.pContacts) || sharedPreferences.contains(settingsActivity.sContacts)) {
                if (sharedPreferences.contains(settingsActivity.Messages)) {
                    if (sharedPreferences.contains(settingsActivity.pContacts)) {
                        String urlWithPrefix="";
                        CheckGpsStatus() ;
//                        String stringLatitude = String.valueOf(location.getLatitude());
//                        String stringLongitude = String.valueOf(location.getLongitude());
//                        urlWithPrefix = " and I am at https://www.google.com.np/maps/@" + stringLatitude + "," + stringLongitude + ",17z";

                       //  check condition here and enable GPS
                        if(GpsStatus) {
                            String stringLatitude = String.valueOf(location.getLatitude());
                            String stringLongitude = String.valueOf(location.getLongitude());
                            urlWithPrefix = " and I am at https://www.google.com.np/maps/@" + stringLatitude + "," + stringLongitude + ",17z";

                        }else{
                            Toast.makeText(context,"Your GPS is OFF", Toast.LENGTH_LONG).show();
                        }

                        if (pContact != null && !pContact.isEmpty()) {
                            message = message + urlWithPrefix;
                            smsManager.sendTextMessage(pContact, null, message, null, null);
                            Toast.makeText(context, "Message sent : " + pContact, Toast.LENGTH_LONG).show();

                            if(sContact != null && !sContact.isEmpty()){
                                String url = "";
                                message = message + url;
                                smsManager.sendTextMessage(sContact, null, message, null, null);
                                Toast.makeText(context, "Message sent : " + sContact, Toast.LENGTH_LONG).show();
                            }

                        } else {
                            Toast.makeText(context,
                                    "Please setup Primary Contact ",
                                    Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(context,
                                "Please setup Primary Contact",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(context,
                            "You haven't setup any Emergency Message",
                            Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(context,
                        "Please Configure contact details ",
                        Toast.LENGTH_LONG).show();

            }
        } else {
            Toast.makeText(context,
                    "Your Security is OFF ",
                    Toast.LENGTH_LONG).show();
        }
    }
}
