package dipesh.com.emergencyalertsystem.bor;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;



import dipesh.com.emergencyalertsystem.MainActivity;

public class NetworkUtil {

  public static boolean isOnline() {

    ConnectivityManager connectivityManager = (ConnectivityManager) MainActivity.getInstance()
        .getSystemService(Context.CONNECTIVITY_SERVICE);

    if (connectivityManager != null) {
      NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
      return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    return false;
  }
}
