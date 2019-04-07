package dipesh.com.emergencyalertsystem.emrCall;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dipesh.com.emergencyalertsystem.emrCall.model.CallLog;


public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private List<Number> numbers = new ArrayList<>();
    List<CallLog> fmCategoryList;


    public ViewPagerAdapter(FragmentManager manager, List<CallLog> apiData) {
        super(manager);
        this.fmCategoryList = apiData;

    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mFragmentList.get(position);
        Bundle b = new Bundle();
        b.putInt("fragment_position", position);

        b.putSerializable("numbers", (Serializable) fmCategoryList.get(position).getNumbers());
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFrag(Fragment fragment, String title, List<Number> fms) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
        this.numbers = fms;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
