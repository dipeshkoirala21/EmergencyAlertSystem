package dipesh.com.emergencyalertsystem.bor;


public interface UserDetailContract {
    interface Presenter extends BasePresenter {
        void onCreateNowClick(UserDetail userDetail);

        void onDobButtonClick();

        void onLocationClick();
    }

    interface View extends BaseView {
        void showDatePickerDialog();

        void getLastLocation();

        void launchHomeScreen();

    }
}
