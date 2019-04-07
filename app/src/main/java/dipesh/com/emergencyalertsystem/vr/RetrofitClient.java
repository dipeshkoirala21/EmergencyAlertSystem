package dipesh.com.emergencyalertsystem.vr;


import dipesh.com.emergencyalertsystem.vr.api.ApiClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitClient {
  //  private static final String AUTH = "Basic " + Base64.encodeToString(("belalkhan:123456").getBytes(), Base64.NO_WRAP);

    private static final String BASE_URL = "http://100.64.205.123/bor/public/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;


    private RetrofitClient() {
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(
//                        new Interceptor() {
//                            @Override
//                            public Response intercept(Chain chain) throws IOException {
//                                Request original = chain.request();
//
//                                Request.Builder requestBuilder = original.newBuilder()
//                                        .addHeader("Authorization", AUTH)
//                                        .method(original.method(), original.body());
//
//                                Request request = requestBuilder.build();
//                                return chain.proceed(request);
//                            }
//                        }
//                ).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
               // .client(okHttpClient)
                .build();
    }

    static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    ApiClient getApi() {
        return retrofit.create(ApiClient.class);
    }
}
