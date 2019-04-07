package dipesh.com.emergencyalertsystem.bor;


import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class User {
  public String fName;
  public String lName;
  public String email;
  public String bloodGroup;
  public String dob;
  public String gender;
  public double latitude;
  public double longitude;

  public User() {
  }
}
