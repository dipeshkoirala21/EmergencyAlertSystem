package dipesh.com.emergencyalertsystem.Call;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dipesh.com.emergencyalertsystem.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmergencyCall extends AppCompatActivity  {
    List<CallLog> apiData = new ArrayList<>();
    private Toolbar toolbar;
    ProgressBar progressBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_call);

        progressBar=findViewById(R.id.progress);

        Toolbar toolbar =  findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadFMLIST();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;


    }


    private void loadFMLIST() {
        RadioInterface apiInterface = APIClient.getClient().create(RadioInterface.class);
        Call<List<CallLog>> call = apiInterface.getFms();
        call.enqueue(new Callback<List<CallLog>>() {
            @Override
            public void onResponse(Call<List<CallLog>> call, Response<List<CallLog>> response) {
                Log.d("fsfslfdfkdsf", response.code() + "gj");
                apiData = response.body();
                progressBar.setVisibility(View.GONE);
//                setupViewPager(viewPager);
                Fragment fragment = new OneFragment();
                Bundle b = new Bundle();
                b.putSerializable("apiData", (Serializable) apiData);
                fragment.setArguments(b);
                getSupportFragmentManager().beginTransaction().replace(R.id.flFrame, fragment)
                        .addToBackStack("list").commit();



            }

            @Override
            public void onFailure(Call<List<CallLog>> call, Throwable t) {
                Log.d("fsfslfdfkdsf",  "failed");
                t.printStackTrace();

            }
        });
    }
}

