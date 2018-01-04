package s8010027.kritchanon.catchtaxidriver.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import s8010027.kritchanon.catchtaxidriver.R;
import s8010027.kritchanon.catchtaxidriver.fragment.ChooseCustomerFragment;

public class ChooseCustomerActivity extends AppCompatActivity implements ChooseCustomerFragment.ChooseCustomerFragmentListener{

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_customer);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, ChooseCustomerFragment.newInstance(),"ChooseCustomerFragment")
                    .commit();
        }

        initInstance();
    }

    private void initInstance() {
        // set tool bar
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // set up button
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /*********
     * up button onclick
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCustomerItemClick(int value) {
        String chooseCustomer = value+"";
        Intent returnIntent = new Intent();
        returnIntent.putExtra("chooseCustomer",chooseCustomer);
        setResult(RESULT_OK,returnIntent);
        finish();
    }
}
