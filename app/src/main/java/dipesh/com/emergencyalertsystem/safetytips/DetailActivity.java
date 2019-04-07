package dipesh.com.emergencyalertsystem.safetytips;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import dipesh.com.emergencyalertsystem.R;

public class DetailActivity extends AppCompatActivity {

    CardView cardView;
    TextView mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar =  findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         cardView= findViewById(R.id.safetyDesc);


        mDescription = findViewById(R.id.safety_desc);

        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
           // mToolbar.setTitle(mBundle.getString("Title"));
            mDescription.setText(mBundle.getString("Description"));
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
