package dipesh.com.emergencyalertsystem.emrCall;

import java.util.List;

import dipesh.com.emergencyalertsystem.emrCall.model.CallLog;
import okhttp3.ResponseBody;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface CallInterface {

    String BASE_URL="http://192.168.0.103/bor/public/";

    @GET("fms")
    Call<ResponseBody> getAPI();

    @GET("emergencyCall")
    Call<List<CallLog>> getNumbers();

//    @GET("fms/{id}/playable")
//    Call<PlayableLinkModel> getId(@Path("id") String id);






}
