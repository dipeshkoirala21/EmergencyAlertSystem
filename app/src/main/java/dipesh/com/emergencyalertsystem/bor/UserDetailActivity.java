package dipesh.com.emergencyalertsystem.bor;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;


import dipesh.com.emergencyalertsystem.R;
import dipesh.com.emergencyalertsystem.databinding.ActivityDetailsBinding;



public class UserDetailActivity extends BaseActivity implements UserDetailContract.View, LocationUtil.LocationListener {


    private UserDetailContract.Presenter mPresenter;
    private ActivityDetailsBinding mActivityUserDetailsBinding;
    private UserDetail mUserDetail;
    private LocationUtil mLocationUtil;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        mLocationUtil.onResolutionResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter =
                new UserDetailPresenter(this, Injection.provideFireBaseAuth(), Injection.getSharedPreference(),
                        Injection.providesDataRepo());
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        mActivityUserDetailsBinding = (ActivityDetailsBinding) mBinding;
        mUserDetail = new UserDetail();
        mActivityUserDetailsBinding.setPresenter(mPresenter);
        mActivityUserDetailsBinding.setUserdetail(mUserDetail);
        mActivityUserDetailsBinding.etFirstName.setOnEditorActionListener((v, actionId, event) -> {
            mActivityUserDetailsBinding.etLastName.requestFocus();
            return true;
        });

        Toolbar toolbar =  findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initSpinner();


        mLocationUtil = new LocationUtil(this, Injection.getSharedPreference());
        mLocationUtil.fetchApproximateLocation(this);
        mPresenter.onCreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        mLocationUtil.onDestroy();
    }

    private void initSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.blood_group,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mActivityUserDetailsBinding.bloodGroupDropDown.setAdapter(adapter);
    }

    @Override
    public void showDatePickerDialog() {
        DialogFragment dialogFragment = DatePickerFragment.newInstance(mUserDetail.dob);
        dialogFragment.show(getSupportFragmentManager(), "datefragment");
    }

    @Override
    public void getLastLocation() {
        mLocationUtil.fetchApproximateLocation(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationUtil.onPermissionResult(requestCode, permissions, grantResults);
    }

    @Override
    public void launchHomeScreen() {
        Intent intent = new Intent(this, MapsActivity.class);
        finish();
        startActivity(intent);
    }

    @Override
    public void generalResponse(int responseId) {
        Toast.makeText(this, responseId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationReceived(@NonNull LatLng location, @NonNull String addressString) {
        mUserDetail.latitiude.set(location.latitude);
        mUserDetail.longitude.set(location.longitude);
        mActivityUserDetailsBinding.tvLocationPicker.setText(addressString);

    }
}
