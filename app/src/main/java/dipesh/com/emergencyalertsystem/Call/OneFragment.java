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

            adapter.addFrag(new NumberListFragment(), apiDatum.getTitle(), apiDatum.getNumbers());
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

}