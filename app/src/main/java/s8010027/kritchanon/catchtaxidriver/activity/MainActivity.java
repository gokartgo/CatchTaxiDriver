package s8010027.kritchanon.catchtaxidriver.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import s8010027.kritchanon.catchtaxidriver.R;
import s8010027.kritchanon.catchtaxidriver.adapter.MainAdapter;
import s8010027.kritchanon.catchtaxidriver.fragment.MapsFragment;
import s8010027.kritchanon.catchtaxidriver.fragment.SentCustomerFragment;
import s8010027.kritchanon.catchtaxidriver.fragment.SentFinishCustomerFragment;
import s8010027.kritchanon.catchtaxidriver.manager.UserData;
import s8010027.kritchanon.catchtaxidriver.view.ImageTextButtonView;

public class MainActivity extends AppCompatActivity implements
        MapsFragment.ActivityCommunicator
        ,SentFinishCustomerFragment.SentFinishCustomerFragmentListener {

    Toolbar toolbar;
    SwitchCompat switchCompat;
    // in drawr layout
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    TextView tvName;
    TextView tvEmail;
    MainAdapter mainAdapter;
    ListView listView;
    // in activity
    Button btnCall;
    Button btnChat;
    Button btnChooseCustomer;
    Button btnPickUp;
    int pickUp = 0;
    Button btnFinish;
    ImageTextButtonView[] menuNameView;
    int chooseCustomer = -1;
    int money=0;
    int i=0;
    MapsFragment mapsFragment;
    // save value
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("Save_Value", Context.MODE_PRIVATE);
        editor = sp.edit();
        money = sp.getInt("money",0);

        initInstance();

        if(savedInstanceState == null) {
            //set fragment to activity
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, MapsFragment.newInstance(chooseCustomer), "MapsFragment")
                    .commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!UserData.getInstance().getName().equals("")) {
            tvName.setText(UserData.getInstance().getName());
            tvEmail.setText(UserData.getInstance().getEmail());
        }
    }

    private void initInstance() {
        //setup toolbar
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //setup switch compat
        switchCompat = (SwitchCompat)findViewById(R.id.switchCompat);
        switchCompat.setOnCheckedChangeListener(switchChange);
        //setup button
        btnCall = (Button) findViewById(R.id.btnCall);
        btnChat = (Button) findViewById(R.id.btnChat);
        btnChooseCustomer = (Button)findViewById(R.id.btnChooseCustomer);
        btnPickUp = (Button)findViewById(R.id.btnPickUp);
        btnFinish = (Button)findViewById(R.id.btnFinish);

        btnPickUp.setVisibility(View.GONE);
        btnFinish.setVisibility(View.GONE);

        btnCall.setOnClickListener(btnClick);
        btnChat.setOnClickListener(btnClick);
        btnChooseCustomer.setOnClickListener(btnClick);
        btnPickUp.setOnClickListener(btnClick);
        btnFinish.setOnClickListener(btnClick);
        //setup drawerLayout
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this
                ,drawerLayout
                ,R.string.open_drawer
                ,R.string.close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        listView = (ListView) findViewById(R.id.listView);
        menuNameView = new ImageTextButtonView[7];
        mainAdapter = new MainAdapter(menuNameView);
        listView.setAdapter(mainAdapter);
        listView.setOnItemClickListener(listViewClick);
        tvName = (TextView)findViewById(R.id.tvName);
        tvEmail = (TextView)findViewById(R.id.tvEmail);
        if(!UserData.getInstance().getName().equals("")) {
            tvName.setText(UserData.getInstance().getName());
            tvEmail.setText(UserData.getInstance().getEmail());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("money",money);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        money = savedInstanceState.getInt("money");
    }

    /****************
     * Activity result
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 12345 request index customer
        if(requestCode == 12345){
            if(resultCode == RESULT_OK){
                Intent getIntent = getIntent();
                chooseCustomer = Integer.parseInt(data.getStringExtra("chooseCustomer").toString());

                // refresh Mapsfragment after choose customer
                refreshMapsFragment();

            }
        }
    }

    private void refreshMapsFragment() {
        btnPickUp.setVisibility(View.VISIBLE);
        btnFinish.setVisibility(View.VISIBLE);
        btnCall.setVisibility(View.VISIBLE);
        btnChat.setVisibility(View.VISIBLE);
        btnChooseCustomer.setVisibility(View.GONE);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentContainer,MapsFragment.newInstance(chooseCustomer),"MapsFragment")
                .commit();
    }

    /***********
     set function drawerLayout
     */

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**********
     * on click listener
     */
    final CompoundButton.OnCheckedChangeListener switchChange = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(compoundButton == switchCompat){
                if(b == true){
                    Toast.makeText(MainActivity.this,"Active",Toast.LENGTH_SHORT).show();
                }
                if(b == false){
                    Toast.makeText(MainActivity.this,"Non Active",Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    final AdapterView.OnItemClickListener listViewClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            if(position == 0){
                Intent intent = new Intent(MainActivity.this,HistoryActivity.class);
                startActivity(intent);
            }
            if(position == 1){
                Intent intent = new Intent(MainActivity.this,WalletActivity.class);
                intent.putExtra("money",money);
                startActivity(intent);
            }
            if(position == 2){
                Intent intent = new Intent(MainActivity.this,RewardActivity.class);
                startActivity(intent);
            }
            if(position == 3){
                Intent intent = new Intent(MainActivity.this,CheckPointActivity.class);
                startActivity(intent);
            }
            if(position == 4){
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
            if(position == 5){
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:*12**"));
                startActivity(intent);
            }
            if(position == 6){
                Intent intent = new Intent(MainActivity.this, BlockActivity.class);
                startActivity(intent);
            }
            drawerLayout.closeDrawers();
        }
    };

    final View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view ==  btnPickUp){
                if(chooseCustomer == -1 ){
                    Toast.makeText(MainActivity.this,"You Must Choose Customer",Toast.LENGTH_SHORT).show();
                }
                else if(pickUp == 1){
                    Toast.makeText(MainActivity.this,"Customer Not Arrived",Toast.LENGTH_SHORT).show();
                }
                else {
                    btnChooseCustomer.setVisibility(View.GONE);
                    pickUp = 1;
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.contentContainer, SentCustomerFragment.newInstance(chooseCustomer), "SentCustomerFragment")
                            .commit();
                }
            }
            if(view == btnFinish){
                if(pickUp == 0){
                    Toast.makeText(MainActivity.this,"Your Must Pick Up User Before",Toast.LENGTH_SHORT).show();
                }
                else{
                    pickUp = 0;
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.contentContainer, SentFinishCustomerFragment.newInstance(chooseCustomer),"SentFinishCustomerFragment")
                            .commit();
                }
            }
            if(view == btnCall){
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:012345****"));
                startActivity(intent);
            }
            if(view == btnChat){
                Intent intent = new Intent(MainActivity.this,ChatActivity.class);
                startActivity(intent);
            }
            if(view == btnChooseCustomer){
                Intent intent = new Intent(MainActivity.this,ChooseCustomerActivity.class);
                // 12345 request index customer
                startActivityForResult(intent,12345);
            }
        }
    };

    /***********
     * get chooseCustomer from fragment then choose from marker
     */
    @Override
    public void passDataToActivity(int chooseCustomer) {
        this.chooseCustomer = chooseCustomer;
        refreshMapsFragment();
    }

    @Override
    public void sentFinishCustomerValue(int chooseCustomer,int pickup,int money) {
        this.chooseCustomer = chooseCustomer;
        this.pickUp = pickup;
        this.money = this.money+money;
        editor.putInt("money",money);
        editor.commit();
        if(chooseCustomer == -1){
            btnPickUp.setVisibility(View.GONE);
            btnFinish.setVisibility(View.GONE);
            btnCall.setVisibility(View.GONE);
            btnChat.setVisibility(View.GONE);
            btnChooseCustomer.setVisibility(View.VISIBLE);
        }
    }
}
