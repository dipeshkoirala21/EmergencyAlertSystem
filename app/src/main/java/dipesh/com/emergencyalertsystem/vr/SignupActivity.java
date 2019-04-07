package dipesh.com.emergencyalertsystem.vr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dipesh.com.emergencyalertsystem.R;
import dipesh.com.emergencyalertsystem.vr.model.DefaultResponse;
import dipesh.com.emergencyalertsystem.vr.storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignupActivity extends AppCompatActivity  {
    EditText input_name,input_pass,input_email,input_contact,input_address,input_dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Toolbar toolbar =  findViewById(R.id.toolbar);
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            toolbar.setTitle(mBundle.getString("Title"));
        }
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



       // findViewById(R.id.buttonSignUp).setOnClickListener();

        input_name= findViewById(R.id.input_name);
        input_email= findViewById(R.id.input_email);
        input_pass= findViewById(R.id.input_pass);
        input_contact= findViewById(R.id.input_contact);
        input_address= findViewById(R.id.input_address);
        input_dob= findViewById(R.id.input_dob);


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

//    private void userSignUp() {
//
//
//
//    }



//    public void Signup(View v){
//
//
//        //calling field validation method
//        if(CheckFieldValidation()) {
//
//            setContentView(R.layout.progressbar_layout);
//            //making object of RestAdapter
//            RestAdapter adapter = new RestAdapter.Builder().setEndpoint(RestInterface.url).build();
//
//            //Creating Rest Services
//            final RestInterface restInterface = adapter.create(RestInterface.class);
//
//            //Calling method to signup
//            restInterface.SignUp(name.getText().toString(), email.getText().toString(),
//                    pass.getText().toString(),contact.getText().toString(), new Callback<LoginModel>() {
//                        @Override
//                        public void success(LoginModel model, Response response) {
//
//                            finish();
//                            startActivity(getIntent());
//
//                            email.setText("");
//                            pass.setText("");
//                            name.setText("");
//                            contact.setText("");
//
//
//                            if (model.getStatus().equals("1")) {  //Signup Success
//
//                                Toast.makeText(SignupActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
//                                finish();
//                                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
//                                startActivity(i);
//
//                            } else if (model.getStatus().equals("0"))  // Signup failure
//                            {
//                                Toast.makeText(SignupActivity.this, "Email Already Registered", Toast.LENGTH_SHORT).show();
//                            }
//
//
//                        }
//
//                        @Override
//                        public void failure(RetrofitError error) {
//                            finish();
//                            startActivity(getIntent());
//                            String merror = error.getMessage();
//                            Toast.makeText(SignupActivity.this, merror, Toast.LENGTH_LONG).show();
//                        }
//                    });
//        }
//
//    }
//
//    //checking field are empty
//    private boolean CheckFieldValidation(){
//
//        boolean valid=true;
//        if(name.getText().toString().equals("")){
//            name.setError("Can't be Empty");
//            valid=false;
//        }else if(email.getText().toString().equals("")){
//            email.setError("Can't be Empty");
//            valid=false;
//        }else if(pass.getText().toString().equals("")){
//            pass.setError("Can't be Empty");
//            valid=false;
//        }else if(contact.getText().toString().equals("")){
//            contact.setError("Can't be Empty");
//            valid=false;
//        }
//
//        return valid;
//
//    }
//
    public void loginTransition(View view) {
        Intent i= new Intent(SignupActivity.this,LoginActivity.class);
        startActivity(i);
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

    public void userSignup(View view) {
        String name = input_name.getText().toString().trim();
        String email = input_email.getText().toString().trim();
        String password = input_pass.getText().toString().trim();
        String contact = input_contact.getText().toString().trim();
        String address = input_address.getText().toString().trim();
        String dob = input_dob.getText().toString().trim();

        if (name.isEmpty()) {
            input_name.setError("Name required");
            input_name.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            input_email.setError("Email is required");
            input_email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            input_email.setError("Enter a valid email");
            input_email.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            input_pass.setError("Password required");
            input_pass.requestFocus();
            return;
        }

        if (password.length() < 6) {
            input_pass.setError("Password should be atleast 6 character long");
            input_pass.requestFocus();
            return;
        }
        if (contact.isEmpty()) {
            input_contact.setError("Phone Number required");
            input_contact.requestFocus();
            return;
        }
        if (!Patterns.PHONE.matcher(contact).matches()) {
            input_contact.setError("Enter a valid Phone Number");
            input_contact.requestFocus();
            return;
        }
        if (address.isEmpty()) {
            input_address.setError("Address required");
            input_address.requestFocus();
            return;
        }
        if (dob.isEmpty()) {
            input_dob.setError("Date of Birth required");
            input_dob.requestFocus();
            return;
        }



        Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .createUser(name,email,password,contact,dob);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.code() == 201) {

                    DefaultResponse dr = response.body();
                    Toast.makeText(SignupActivity.this, dr.getMessage(), Toast.LENGTH_LONG).show();

                } else if (response.code() == 422) {
                    Toast.makeText(SignupActivity.this, "User already exists ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }
//
//    @Override
//    public void onClick(View v) {
//
//    }


}
