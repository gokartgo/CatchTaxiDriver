package s8010027.kritchanon.catchtaxidriver.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import s8010027.kritchanon.catchtaxidriver.R;
import s8010027.kritchanon.catchtaxidriver.fragment.SignInSignUpFragment;

public class SignInSignUpActivity extends AppCompatActivity {

    Button btnSignIn;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // request permission from user
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {


                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},23
                );
            }
        }
        setContentView(R.layout.activity_sign_in_sign_up);

        initInstance();

        if(savedInstanceState == null){
            //set fragment to activity
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, SignInSignUpFragment.newInstance(),"SignInSignUpFragment")
                    .commit();
        }

    }

    private void initInstance() {
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignIn.setOnClickListener(btnClick);
        btnSignUp.setOnClickListener(btnClick);
    }

    /****************
     * listener zone*
     */
    final View.OnClickListener btnClick = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            if(view == btnSignIn){
                Intent intent = new Intent(SignInSignUpActivity.this,SignInActivity.class);
                startActivity(intent);
            }
            if(view == btnSignUp){
                Intent intent = new Intent(SignInSignUpActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        }
    };
}
