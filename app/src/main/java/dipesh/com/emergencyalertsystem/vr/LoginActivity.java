package dipesh.com.emergencyalertsystem.vr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dipesh.com.emergencyalertsystem.R;

import dipesh.com.emergencyalertsystem.vr.model.LoginResponse;
import dipesh.com.emergencyalertsystem.vr.storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity  {
    EditText emailaddress,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar =  findViewById(R.id.toolbar);
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            toolbar.setTitle(mBundle.getString("Title"));
        }
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        emailaddress= findViewById(R.id.email);
        password= findViewById(R.id.pass);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, ViolenceReport.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    public void Login(View view) {
        String email = emailaddress.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (email.isEmpty()) {
            emailaddress.setError("Email is required");
            emailaddress.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailaddress.setError("Enter a valid email");
            emailaddress.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            password.setError("Password required");
            password.requestFocus();
            return;
        }

        if (pass.length() < 6) {
            password.setError("Password should be atleast 6 character long");
            password.requestFocus();
            return;
        }
        Call<LoginResponse> call = RetrofitClient
                .getInstance().getApi().userLogin(email,pass);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                if (!loginResponse.isError()) {

                    SharedPrefManager.getInstance(LoginActivity.this)
                            .saveUser(loginResponse.getUser());

                    Intent intent = new Intent(LoginActivity.this, ViolenceReport.class);
                    //closes all the previous activities and opens a new activity
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);


                } else {
                    Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }


//    // When Button Login clicked
//    public void Login(View v){
//
//        //Calling method of field validation
//        if(CheckFieldValidation()) {
//
//            //progressBar.setVisibility(View.VISIBLE);
//            setContentView(R.layout.progressbar_layout);
//            //making object of RestAdapter
//            RestAdapter adapter = new RestAdapter.Builder().setEndpoint(RestInterface.url).build();
//
//            //Creating Rest Services
//            final RestInterface restInterface = adapter.create(RestInterface.class);
//
//            //Calling method to get check login
//            restInterface.Login(email.getText().toString(), pass.getText().toString(), new Callback<LoginModel>() {
//                @Override
//                public void success(LoginModel model, Response response) {
//
//                    finish();
//                    startActivity(getIntent());
//
//                    email.setText("");
//                    pass.setText("");
//
//
//                    if (model.getStatus().equals("1")) {  //login Success
//
//                        Toast.makeText(LoginActivity.this, "Login In SuccessFully", Toast.LENGTH_SHORT).show();
//                        Intent i = new Intent(LoginActivity.this,EmergencyCall.class);
//                        startActivity(i);
//
//                    } else if (model.getStatus().equals("0"))  // login failure
//                    {
//                        Toast.makeText(LoginActivity.this, "Invalid UserName/Pass ", Toast.LENGTH_SHORT).show();
//                    }
//
//
//                }
//
//                @Override
//                public void failure(RetrofitError error) {
//                    finish();
//                    startActivity(getIntent());
//                    String merror = error.getMessage();
//                    Toast.makeText(LoginActivity.this, merror, Toast.LENGTH_LONG).show();
//                }
//            });
//        }
//    }
//
//    //When Button Sign up clicked
//    public void SignUp(View v){
//
//        Intent i= new Intent(LoginActivity.this,SignupActivity.class);
//        startActivity(i);
//
//
//    }
//
//    //checking field are empty
//    private boolean CheckFieldValidation(){
//
//        boolean valid=true;
//        if(email.getText().toString().equals("")){
//            email.setError("Can't be Empty");
//            valid=false;
//        }else if(pass.getText().toString().equals("")){
//            pass.setError("Can't be Empty");
//            valid=false;
//        }
//
//        return valid;
//
//    }

    public void signupTransition(View view) {
        Intent i= new Intent(LoginActivity.this,SignupActivity.class);
        startActivity(i);
    }


}
