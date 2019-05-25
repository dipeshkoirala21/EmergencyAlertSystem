package dipesh.com.emergencyalertsystem.hk;

import android.Manifest;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;



public class MyService extends Service {

    static int count = 0;
    private String msg;
    private static BroadcastReceiver mybroadcast;

    SettingsActivity settingsActivity;
    SharedPreferences sharedPreferences;

    public MyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
//        registerReceiver(); /* Don't register the broadcast here */
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mybroadcast);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        registerReceiver();

        return START_STICKY;
    }

    private void registerReceiver() {
        mybroadcast = new BroadcastReceiver() {
            //When Event is published, onReceive method is called
            @Override
            public void onReceive(Context context, Intent intent) {

                if (intent.getAction() != null && intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                    Log.i("[BroadcastReceiver]", "Screen ON");
                    count++;
                } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                    Log.i("[BroadcastReceiver]", "Screen OFF");
                    count++;
                }

                Log.d("Count", Integer.toString(count));

                if (count == 1) {
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            // Reset counter after 2.5 seconds so that it doesn't get false alarm
                            count = 0;
                        }
                    }, 2500);
                }

                if (count == 4) {
                    Log.i("[BroadcastReceiver]", "Power button clicked Four times");

                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                    if (vibrator != null && vibrator.hasVibrator()) {

                        vibrator.vibrate(400);

                    }

                    Location location = getLocation();
                    String stringLongitude, stringLatitude;
                    if (location != null) {

                        stringLongitude = Double.toString(location.getLongitude());
                        stringLatitude = Double.toString(location.getLatitude());
                    } else {
                        stringLatitude = "\'Not Available\'";
                        stringLongitude = "\'Not Available\'";
                    }

                    //  AppDatabase db = AppDatabase.getAppDatabase(getApplicationContext());
                    String message = sharedPreferences.getString(settingsActivity.Messages, null);
                    String pContact = sharedPreferences.getString(settingsActivity.pContacts, null);
                    String sContact = sharedPreferences.getString(settingsActivity.sContacts, null);

                    List<String> mobNumber = new ArrayList<>();
                    mobNumber.add(pContact);
                    mobNumber.add(sContact);


                    SmsManager smsManager = SmsManager.getDefault();

                    // String gmapLink = "http://maps.google.com/maps?q=" + lati + ","+ longi;
                    //msg = "I am here at " + gmapLink + ". I need your help! Get to me Soon.";
                    String urlWithPrefix = " and I am at https://www.google.com/maps/search/?api=1&query=" + stringLatitude + "," + stringLongitude;
                    msg = message + urlWithPrefix;

                    for (String number : mobNumber) {
                        smsManager.sendTextMessage(number, null, msg, null, null);
                        Log.d("MSG", "sent message to " + number);
                    }

                    // Start a call to first contact
                    Intent call = new Intent(Intent.ACTION_CALL);
                    call.setData(Uri.parse("tel:" + mobNumber.get(0)));
                    call.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        //handle it
                    } else {

                        getApplicationContext().startActivity(call);
                    }
                    count = 0;
                }


            }
        };

        /// Register the broadcast receiver
        registerReceiver(mybroadcast, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(mybroadcast, new IntentFilter(Intent.ACTION_SCREEN_OFF));
    }

    public Location getLocation() {
        boolean gps_enabled = false;
        boolean network_enabled = false;

        LocationManager lm = (LocationManager) getApplicationContext()
                .getSystemService(Context.LOCATION_SERVICE);

        if (lm != null) {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }

        Location net_loc = null, gps_loc = null, finalLoc = null;

        if (gps_enabled)
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return null;
            }
        assert lm != null;
        gps_loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (network_enabled)
            net_loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if (gps_loc != null) {
            finalLoc = gps_loc;
        } else if (net_loc != null) {
            finalLoc = net_loc;
        }
        return finalLoc;
    }
}
