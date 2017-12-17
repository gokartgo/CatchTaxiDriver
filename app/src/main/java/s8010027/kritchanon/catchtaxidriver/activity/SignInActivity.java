package s8010027.kritchanon.catchtaxidriver.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import s8010027.kritchanon.catchtaxidriver.R;
import s8010027.kritchanon.catchtaxidriver.fragment.SignInFragment;

public class SignInActivity extends AppCompatActivity implements SignInFragment.FragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer,SignInFragment.newInstance(),"SignInFragment")
                    .commit();
        }
    }

    @Override
    public void onButtonSignInClick() {
        Intent intent = new Intent(SignInActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
