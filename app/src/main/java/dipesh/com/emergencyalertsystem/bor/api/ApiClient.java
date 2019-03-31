package dipesh.com.emergencyalertsystem.bor.api;

import dipesh.com.emergencyalertsystem.bor.model.DefaultResponse;
import dipesh.com.emergencyalertsystem.bor.model.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiClient {
    @FormUrlEncoded
    @POST("createuser")
    Call<DefaultResponse> createUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("contact") String contact,
            @Field("date_of_birth") String date_of_birth
    );

    @FormUrlEncoded
    @POST("userlogin")
    Call<LoginResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );
//
//    @GET("allusers")
//    Call<UsersResponse> getUsers();
//
//    @FormUrlEncoded
//    @PUT("updateuser/{id}")
//    Call<LoginResponse> updateUser(
//            @Path("id") int id,
//            @Field("name") String name,
//            @Field("email") String email,
//            @Field("contact") String contact,
//            @Field("date_of_birth") String date_of_birth
//    );
//
//    @FormUrlEncoded
//    @PUT("updatepassword")
//    Call<DefaultResponse> updatePassword(
//            @Field("currentpassword") String currentpassword,
//            @Field("newpassword") String newpassword,
//            @Field("email") String email
//    );
//
//    @DELETE("deleteuser/{id}")
//    Call<DefaultResponse> deleteUser(@Path("id") int id);
}
