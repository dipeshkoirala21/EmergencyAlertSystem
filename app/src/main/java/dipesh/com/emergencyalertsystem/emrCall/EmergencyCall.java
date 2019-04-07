package dipesh.com.emergencyalertsystem.emrCall;

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
import dipesh.com.emergencyalertsystem.emrCall.model.CallLog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EmergencyCall extends AppCompatActivity {
    List<CallLog> apiData = new ArrayList<>();
    ProgressBar progressBar;
//    List<Fm> fmList;
    private Toolbar toolbar;
//    private TabLayout tabLayout;
//    private ViewPager viewPager;
//    ProgressDialog progressDialog;
//    RadioManager radioManager;
//    public RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_call);
//        progressDialog = new ProgressDialog(EmergencyCall.this);
//        progressDialog.setMessage("Loading....");
//        progressDialog.show();
        Toolbar toolbar =  findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        loadFMLIST();
        progressBar=findViewById(R.id.progress);
//        viewPager =  findViewById(R.id.viewpager);
//        tabLayout =  findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);
//        radioManager = RadioManager.with(this);
            initViews();

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



    private void initViews() {
//        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_id);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(adapter);
        loadFMLIST();
    }



    private void loadFMLIST() {
        CallInterface apiInterface = APIClient.getClient().create(CallInterface.class);
       // Call<List<FMCategory>> call = apiInterface.getFms();
        Call<List<CallLog>> call = apiInterface.getNumbers();
        call.enqueue(new Callback<List<CallLog>>() {
            @Override
            public void onResponse(Call<List<CallLog>> call, Response<List<CallLog>> response) {

                apiData = response.body();
//                setupViewPager(viewPager);
                Log.d("fsfslfdfkdsf", response.code() + "gj");
                progressBar.setVisibility(View.GONE);
                Fragment fragment = new OneFragment();
                Bundle b = new Bundle();
                b.putSerializable("apiData", (Serializable) apiData);
                fragment.setArguments(b);
                getSupportFragmentManager().beginTransaction().replace(R.id.flFrame, fragment)
                        .addToBackStack("list").commit();

//                Log.d("fsfslfdfkdsf", response.code() + "gj");
//                apiData = response.body();
////                setupViewPager(viewPager);
//                Fragment fragment = new OneFragment();
//                Bundle b = new Bundle();
//                b.putSerializable("apiData", (Serializable) apiData);
//                fragment.setArguments(b);
//                getSupportFragmentManager().beginTransaction().replace(R.id.flFrame, fragment)
//                        .addToBackStack("list").commit();
            }

            @Override
            public void onFailure(Call<List<CallLog>> call, Throwable t) {

            }
        });
//        call.enqueue(new Callback<List<FMCategory>>() {
//            @Override
//            public void onResponse(Call<List<FMCategory>> call, Response<List<FMCategory>> response) {
//                Log.d("fsfslfdfkdsf", response.code() + "gj");
//                apiData = response.body();
////                setupViewPager(viewPager);
//                progressBar.setVisibility(View.GONE);
//                Fragment fragment = new OneFragment();
//                Bundle b = new Bundle();
//                b.putSerializable("apiData", (Serializable) apiData);
//                fragment.setArguments(b);
//                getSupportFragmentManager().beginTransaction().replace(R.id.flFrame, fragment)
//                        .addToBackStack("list").commit();
//
//
//
//            }
//
//            @Override
//            public void onFailure(Call<List<FMCategory>> call, Throwable t) {
//                Log.d("",  "failed");
//                t.printStackTrace();
//
//            }
//        });
    }

//    @Override
//    public void onFragmentInteraction(Uri uri) {
//
//    }






     /*   private void loadJSON() {
        CallInterface apiInterface=APIClient.getClient().create(CallInterface.class);
       Call<ResponseBody> call = apiInterface.getAPI();
       call.enqueue(new Callback<ResponseBody>() {
           @Override
           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               progressDialog.dismiss();
               try {
                   JSONArray jsonArray = new JSONArray(response.body().string());
                   for(int i=0; i < jsonArray.length(); i++){
                       JSONObject jsonObject = jsonArray.getJSONObject(i);
                       FMCategory apiDatum = new FMCategory();
                       apiDatum.setCategoryName(jsonObject.getString("category_name"));
                       apiDatum.setCategoryName(jsonObject.getString("category_name"));
                       apiData.add(apiDatum);

                   }
                   setupViewPager(viewPager);

               } catch (IOException e) {
                   e.printStackTrace();
               } catch (JSONException e) {
                   e.printStackTrace();
               }

           }

           @Override
           public void onFailure(Call<ResponseBody> call, Throwable t) {
               progressDialog.dismiss();
               Log.d("Error",t.getMessage());
           }
       });
*/
//       call.enqueue(new Callback<List<APIDatum>>() {
//           @Override
//           public void onResponse(Call<List<APIDatum>> call, Response<List<APIDatum>> response) {
//               Log.d("TAG", response.code() + "hajkfhkjaskjfakjsdf");
//               fmList = response.body();
//               Log.d("TAG", fmList.size()+"  ");
//                System.out.println(postDataList.get(0).getTitle());
//               data = new ArrayList<>(Arrays.asList(jsonResponse.getDatas()));
//               adapter = new RecyclerViewAdapter(getApplicationContext(),fmList);
//               recyclerView.setAdapter(adapter);
//           }
//
//           @Override
//           public void onFailure(Call<List<APIDatum>> call, Throwable t) {
//               Log.d("Error",t.getMessage());
//           }
//       });

    }

