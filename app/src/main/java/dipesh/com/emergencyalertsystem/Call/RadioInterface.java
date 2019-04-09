package dipesh.com.emergencyalertsystem.Call;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface RadioInterface {

    String BASE_URL="http://192.168.137.1/bor/public/";



    @GET("emergencyCall")
    Call<List<CallLog>> getFms();


}
