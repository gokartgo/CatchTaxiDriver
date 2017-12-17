package s8010027.kritchanon.catchtaxidriver.activity;

import android.content.Intent;
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
        btnSignIn.setOnClickListener(btnClick);
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
        }
    };
}
