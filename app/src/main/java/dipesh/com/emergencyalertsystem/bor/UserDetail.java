package dipesh.com.emergencyalertsystem.bor;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableDouble;




public class UserDetail {
  public ObservableString firstName = new ObservableString();
  public ObservableString lastName = new ObservableString();
  public ObservableString email = new ObservableString();
  public ObservableString bloodGroup = new ObservableString();
  public ObservableString dob = new ObservableString();
  public ObservableBoolean isMale = new ObservableBoolean(true);
  public ObservableDouble latitiude = new ObservableDouble();
  public ObservableDouble longitude = new ObservableDouble();


  public UserDetail() {

  }
}
