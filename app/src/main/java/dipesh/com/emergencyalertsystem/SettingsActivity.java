package dipesh.com.emergencyalertsystem;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    private EditText message, primaryContact, secondaryContact;
    public static final String MyPreferences = "emergencypreference";
    public static final String Messages = "MessageKey";
    public static final String pContacts = "pContactKey";

    public static final String sContacts = "sContactKey";

    public static final String securityOnOffs = "securityKey";

    SharedPreferences.Editor editor;

    SharedPreferences sharedPreferences;



    //constructor of the class

    public SettingsActivity(){

    }

    public SettingsActivity ( Context context){
        sharedPreferences = context.getSharedPreferences(MyPreferences, 0);
        editor=sharedPreferences.edit();
        editor.apply();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //setting up a toolbar
        Toolbar toolbar =  findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        message = findViewById(R.id.emergencyMessageTextField);
        primaryContact = findViewById(R.id.primaryContactTextField);
        secondaryContact = findViewById(R.id.secondaryContactTextField);
        Context context = getApplicationContext();
        sharedPreferences = context.getSharedPreferences(MyPreferences, 0);
        if(sharedPreferences.contains(Messages)) {
            message.setText(sharedPreferences.getString(Messages, ""));
        }
        if(sharedPreferences.contains(pContacts)) {
            primaryContact.setText(sharedPreferences.getString(pContacts, ""));
        }
        if(sharedPreferences.contains(sContacts)) {
            secondaryContact.setText(sharedPreferences.getString(sContacts, ""));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    public void settingDetails(View view){
        Context context = getApplicationContext();
        sharedPreferences = context.getSharedPreferences(MyPreferences, 0);
        editor= sharedPreferences.edit();
        String emergencyMessage = message.getText().toString();
        String primaryCont = primaryContact.getText().toString();

        String sContact = secondaryContact.getText().toString();

        editor.putString(Messages, emergencyMessage);
        editor.putString(pContacts, primaryCont);

        editor.putString(sContacts, sContact);

        editor.apply();
        Toast.makeText(this,"Configuration details have been saved", Toast.LENGTH_SHORT).show();
    }

    public boolean checkSecurity () {
        return sharedPreferences.getBoolean(securityOnOffs, false);

    }

    public void setSecurity(){

    }

}
