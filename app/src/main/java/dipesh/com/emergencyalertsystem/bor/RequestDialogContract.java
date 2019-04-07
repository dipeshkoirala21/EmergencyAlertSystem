package dipesh.com.emergencyalertsystem.bor;

import android.support.annotation.NonNull;


public interface RequestDialogContract {
  interface View {
    void getLastLocation();

    void dismissDialog(@NonNull String userId, boolean isReceiver, ReceiverDonorRequestType receiverDonorRequestType);
  }

  interface Presenter extends BasePresenter {

    void onSubmitButtonClick(RequestDetails requestDetails);

    void onLocationClick();
  }
}
