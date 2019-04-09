package dipesh.com.emergencyalertsystem.Call;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dipesh.com.emergencyalertsystem.R;


public class OneFragment extends Fragment {
    RecyclerView recyclerView;
    View view;
    public List<Number> fmList=new ArrayList<>();
    RecyclerViewAdapter adapter;
    List<CallLog> apiData=new ArrayList<>();

    TabLayout tabLayout;
    ViewPager viewPager;

    public OneFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), apiData);
        for(CallLog apiDatum : apiData){

            adapter.addFrag(new RadioListFragment(), apiDatum.getTitle(), apiDatum.getNumbers());
            viewPager.setAdapter(adapter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_one,container,false);

        apiData = (List<CallLog>) getArguments().getSerializable("apiData");
        tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getActivity(),R.color.colorPrimary));
        tabLayout.setSelectedTabIndicatorHeight((int) (3 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(ContextCompat.getColor(getActivity(),R.color.colorPrimary),
                ContextCompat.getColor(getActivity(),R.color.colorAccent));
        viewPager = view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }






//    private void loadFMLIST(){
//        RadioInterface apiInterface=APIClient.getClient().create(RadioInterface.class);
//     Call<List<FMCategory>> call=apiInterface.getFms();
//    call.enqueue(new Callback<List<FMCategory>>() {
//        @Override
//        public void onResponse(Call<List<FMCategory>> call, Response<List<FMCategory>> response) {
//            Log.d("TAG", response.code() + "gj");
//            Log.d("TAG", fmList.size() + "gjgjfkjsgfahsdjkghjdfhg");
//            fmList=new ArrayList<>();
//            fmList=response.body();
//            adapter = new RecyclerViewAdapter(getActivity(),fmList);
//            recyclerView.setAdapter(adapter);
//        }
//
//        @Override
//        public void onFailure(Call<List<FMCategory>> call, Throwable t) {
//
//        }
//    });



//        call.enqueue(new Callback<List<Fm>>() {
//            @Override
//            public void onResponse(Call<List<Fm>> call, Response<List<Fm>> response) {
//                Log.d("TAG", response.code() + "hajkfhkjaskjfakjsdf");
//                fmList = response.body();
//                Log.d("TAG", fmList.size()+"  ");
//                data = new ArrayList<>(Arrays.asList(jsonResponse.getCupon()));
//                adapter = new RecyclerViewAdapter(getActivity(),fmList);
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<List<Fm>> call, Throwable t) {
//                Log.d("Error",t.getMessage());
//            }
//        });
//    }

}