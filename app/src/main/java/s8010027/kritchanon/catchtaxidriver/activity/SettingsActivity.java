package s8010027.kritchanon.catchtaxidriver.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import s8010027.kritchanon.catchtaxidriver.R;
import s8010027.kritchanon.catchtaxidriver.fragment.ProfileFragment;
import s8010027.kritchanon.catchtaxidriver.fragment.SettingsFragment;

public class SettingsActivity extends AppCompatActivity implements  ProfileFragment.SaveFragmentListener{

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, SettingsFragment.newInstance(),"SettingsFragment")
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
        getSupportActionBar().setTitle(R.string.settings_actionbar);
    }

    // set action bar title from fragment

    public void setActionBarTitle(int title){
        getSupportActionBar().setTitle(title);
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

    /********
     * fragment listener
     */

    @Override
    public void onSaveItemClick() {
        finish();
    }
}
