package s8010027.kritchanon.catchtaxidriver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import s8010027.kritchanon.catchtaxidriver.R;
import s8010027.kritchanon.catchtaxidriver.fragment.ChatFragment;
import s8010027.kritchanon.catchtaxidriver.fragment.NumberDigitFragment;
import s8010027.kritchanon.catchtaxidriver.fragment.SignUpFragment;

public class ChatActivity extends AppCompatActivity implements ChatFragment.FragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, ChatFragment.newInstance(),"ChatFragment")
                    .commit();
        }
    }


    @Override
    public void onButtonHomeClick() {
        finish();
    }

    @Override
    public void onCallClick() {
        Intent intent = new Intent(ChatActivity.this,CallPhoneActivity.class);
        intent.putExtra("customer",0);
        startActivity(intent);
    }
}
