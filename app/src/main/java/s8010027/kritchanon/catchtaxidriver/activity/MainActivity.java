package s8010027.kritchanon.catchtaxidriver.activity;

import android.app.Dialog;
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
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import s8010027.kritchanon.catchtaxidriver.R;
import s8010027.kritchanon.catchtaxidriver.adapter.MainAdapter;
import s8010027.kritchanon.catchtaxidriver.fragment.MapsFragment;
import s8010027.kritchanon.catchtaxidriver.fragment.SentCustomerFragment;
import s8010027.kritchanon.catchtaxidriver.fragment.SentFinishCustomerFragment;
import s8010027.kritchanon.catchtaxidriver.manager.UserData;
import s8010027.kritchanon.catchtaxidriver.view.ImageTextButtonChooseEventView;
import s8010027.kritchanon.catchtaxidriver.view.ImageTextButtonView;

public class MainActivity extends AppCompatActivity implements
        MapsFragment.ActivityCommunicator
        , SentFinishCustomerFragment.SentFinishCustomerFragmentListener {

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
    /*Button btnCall;
    Button btnChat;
    Button btnPickUp;
    Button btnCancel;*/
    Button btnChooseCustomer;
    ImageTextButtonChooseEventView btnChat,btnCall,btnPickUp,btnCancel;
    int pickUp = 0;
    Button btnFinish;
    ImageTextButtonView[] menuNameView;
    int chooseCustomer = -1;
    int money = 0;
    int i = 0;
    MapsFragment mapsFragment;
    // save value
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    //User cancel deal customer
    Dialog dialogUserCancelCustomer;
    Button btnDialogUserCancelCustomerComfirm;
    Button btnDialogUserCancelCustomerCancel;

    //Customer Cancel
    Button btnCustomerCancel;
    Dialog dialogCancel;
    Button btnDialogCancelSkip,btnDialogCancelComment;
    Dialog dialogComment;
    Button btnDialogCommentSkip,btnDialogCommentSubmit;
    Dialog dialogRate;
    LinearLayout linearLayoutDialogPerfect,linearLayoutDialogExcellent
            ,linearLayoutDialogGood,linearLayoutDialogAdjust,linearLayoutDialogBad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("Save_Value", Context.MODE_PRIVATE);
        editor = sp.edit();
        money = sp.getInt("money", 0);

        initInstance();

        if (savedInstanceState == null) {
            //set fragment to activity
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, MapsFragment.newInstance(chooseCustomer), "MapsFragment")
                    .commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!UserData.getInstance().getName().equals("")) {
            tvName.setText(UserData.getInstance().getName());
            tvEmail.setText(UserData.getInstance().getEmail());
        }
    }

    private void initInstance() {

        btnCustomerCancel = (Button) findViewById(R.id.btnCustomerCancel);
        btnCustomerCancel.setOnClickListener(btnCancelCustomer);
        //setup toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //setup switch compat
        switchCompat = (SwitchCompat) findViewById(R.id.switchCompat);
        switchCompat.setOnCheckedChangeListener(switchChange);
        //setup button
        setImageTextButtonChooseEventView();
        btnChooseCustomer = (Button) findViewById(R.id.btnChooseCustomer);
        btnFinish = (Button) findViewById(R.id.btnFinish);
        // set Button on click
        btnCall.setOnClickListener(btnClick);
        btnChat.setOnClickListener(btnClick);
        btnChooseCustomer.setOnClickListener(btnClick);
        btnPickUp.setOnClickListener(btnClick);
        btnFinish.setOnClickListener(btnClick);
        btnCancel.setOnClickListener(btnClick);
        //setup drawerLayout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this
                , drawerLayout
                , R.string.open_drawer
                , R.string.close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        listView = (ListView) findViewById(R.id.listView);
        menuNameView = new ImageTextButtonView[7];
        mainAdapter = new MainAdapter(menuNameView);
        listView.setAdapter(mainAdapter);
        listView.setOnItemClickListener(listViewClick);
        tvName = (TextView) findViewById(R.id.tvName);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        if (!UserData.getInstance().getName().equals("")) {
            tvName.setText(UserData.getInstance().getName());
            tvEmail.setText(UserData.getInstance().getEmail());
        }
    }

    private void setImageTextButtonChooseEventView() {
        btnCall = (ImageTextButtonChooseEventView) findViewById(R.id.btnCall);
        btnChat = (ImageTextButtonChooseEventView) findViewById(R.id.btnChat);
        btnPickUp = (ImageTextButtonChooseEventView) findViewById(R.id.btnPickUp);
        btnCancel = (ImageTextButtonChooseEventView) findViewById(R.id.btnCancel);
        btnCall.setImageIvImageTextButton(R.drawable.icon_call_to_customer);
        btnCall.setTextTvImageTextButton(R.string.button_call);
        btnChat.setImageIvImageTextButton(R.drawable.icon_chat);
        btnChat.setTextTvImageTextButton(R.string.button_chat);
        btnPickUp.setImageIvImageTextButton(R.drawable.pick_up);
        btnPickUp.setTextTvImageTextButton(R.string.button_pick_up);
        btnCancel.setImageIvImageTextButton(R.drawable.cancel);
        btnCancel.setTextTvImageTextButton(R.string.cancel_button);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("money", money);
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
        if (requestCode == 12345) {
            if (resultCode == RESULT_OK) {
                Intent getIntent = getIntent();
                chooseCustomer = Integer.parseInt(data.getStringExtra("chooseCustomer").toString());
                // refresh Mapsfragment after choose customer
                refreshMapsFragment();

            }
        }
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
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**********
     * on click listener
     */

    // switch on off
    final CompoundButton.OnCheckedChangeListener switchChange = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (compoundButton == switchCompat) {
                if (b == true) {
                    Toast.makeText(MainActivity.this, "Active", Toast.LENGTH_SHORT).show();
                }
                if (b == false) {
                    Toast.makeText(MainActivity.this, "Non Active", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };


    // adapter on choose position in layout gravity start
    final AdapterView.OnItemClickListener listViewClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            if (position == 0) {
                Intent intent = new Intent(MainActivity.this, HistoryMainActivity.class);
                startActivity(intent);
            }
            if (position == 1) {
                Intent intent = new Intent(MainActivity.this, WalletActivity.class);
                intent.putExtra("money", money);
                startActivity(intent);
            }
            if (position == 2) {
                Intent intent = new Intent(MainActivity.this, RewardActivity.class);
                startActivity(intent);
            }
            if (position == 3) {
                Intent intent = new Intent(MainActivity.this, CheckPointActivity.class);
                startActivity(intent);
            }
            if (position == 4) {
                Intent intent = new Intent(MainActivity.this, SettingsMainActivity.class);
                startActivity(intent);
            }
            if (position == 5) {
                Intent intent = new Intent(MainActivity.this, CallCenterActivity.class);
                startActivity(intent);
            }
            if (position == 6) {
                Intent intent = new Intent(MainActivity.this, BlockActivity.class);
                startActivity(intent);
            }
            drawerLayout.closeDrawers();
        }
    };

    final View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == btnPickUp) {

                setButtonPickupFinishSent();

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer, SentCustomerFragment.newInstance(chooseCustomer), "SentCustomerFragment")
                        .commit();

            }
            if (view == btnFinish) {

                setButtonPickupFinishSent();

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer, SentFinishCustomerFragment.newInstance(chooseCustomer), "SentFinishCustomerFragment")
                        .commit();
            }
            if (view == btnCall) {
                Intent intent = new Intent(MainActivity.this,CallPhoneActivity.class);
                intent.putExtra("call center",0);
                startActivity(intent);
            }
            if (view == btnChat) {
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(intent);
            }
            if (view == btnCancel) {
                dialogUserCancelCustomer = new Dialog(MainActivity.this,R.style.Theme_Dialog);
                dialogUserCancelCustomer.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogUserCancelCustomer.setContentView(R.layout.dialog_cancel_customer_by_user);
                dialogUserCancelCustomer.setCancelable(true);
                dialogUserCancelCustomer.setCanceledOnTouchOutside(true);
                btnDialogUserCancelCustomerComfirm = (Button) dialogUserCancelCustomer.findViewById(R.id.btnDialogConfirm);
                btnDialogUserCancelCustomerComfirm.setOnClickListener(btnDialogCancelCustomerByUserClick);
                btnDialogUserCancelCustomerCancel = (Button) dialogUserCancelCustomer.findViewById(R.id.btnDialogCancel);
                btnDialogUserCancelCustomerCancel.setOnClickListener(btnDialogCancelCustomerByUserClick);
                dialogUserCancelCustomer.show();
            }
            if (view == btnChooseCustomer) {
                Intent intent = new Intent(MainActivity.this, ChooseCustomerActivity.class);
                // 12345 request index customer
                startActivityForResult(intent, 12345);
            }
        }
    };


    /***************
     * dialog cancel customer by user listener
     */

    final View.OnClickListener btnDialogCancelCustomerByUserClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == btnDialogUserCancelCustomerComfirm){
                dialogUserCancelCustomer.cancel();
                chooseCustomer = -1;
                refreshMapsFragment();
            }
            if(view == btnDialogUserCancelCustomerCancel){
                dialogUserCancelCustomer.cancel();
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
    public void sentFinishCustomerValue(int chooseCustomer, int pickup, int money) {
        this.chooseCustomer = chooseCustomer;
        this.pickUp = pickup;
        this.money = this.money + money;
        editor.putInt("money", money);
        editor.commit();
        if(chooseCustomer == -1) {
            refreshMapsFragment();
        }
    }

    /**************
     * refresh fragment and button in mainactivity by step
     */

    private void refreshMapsFragment() {
        if(chooseCustomer != -1){
            btnCustomerCancel.setVisibility(View.VISIBLE);
            btnCall.setVisibility(View.VISIBLE);
            btnChat.setVisibility(View.VISIBLE);
            btnPickUp.setVisibility(View.VISIBLE);
            btnFinish.setVisibility(View.GONE);
            btnCancel.setVisibility(View.VISIBLE);
            btnChooseCustomer.setVisibility(View.GONE);
        }
        else if (chooseCustomer == -1) {
            btnCustomerCancel.setVisibility(View.GONE);
            btnCall.setVisibility(View.GONE);
            btnChat.setVisibility(View.GONE);
            btnPickUp.setVisibility(View.GONE);
            btnFinish.setVisibility(View.GONE);
            btnCancel.setVisibility(View.GONE);
            btnChooseCustomer.setVisibility(View.VISIBLE);
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentContainer, MapsFragment.newInstance(chooseCustomer), "MapsFragment")
                .commit();
    }

    private void setButtonPickupFinishSent(){
        btnCustomerCancel.setVisibility(View.GONE);
        btnCall.setVisibility(View.GONE);
        btnChat.setVisibility(View.GONE);
        btnPickUp.setVisibility(View.GONE);
        btnFinish.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.GONE);
        btnChooseCustomer.setVisibility(View.GONE);
    }

    /*****************
     * listener cancel customer
     */

    final View.OnClickListener btnCancelCustomer = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == btnCustomerCancel){
                dialogCancel = new Dialog(MainActivity.this,R.style.Theme_Dialog);
                dialogCancel.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogCancel.setContentView(R.layout.dialog_customer_cancel);
                dialogCancel.setCancelable(true);
                dialogCancel.setCanceledOnTouchOutside(true);
                btnDialogCancelSkip = (Button) dialogCancel.findViewById(R.id.btnDialogCancelSkip);
                btnDialogCancelSkip.setOnClickListener(btnCancelCustomerChoose);
                btnDialogCancelComment = (Button) dialogCancel.findViewById(R.id.btnDialogCancelComment);
                btnDialogCancelComment.setOnClickListener(btnCancelCustomerChoose);
                dialogCancel.show();
            }
        }
    };

    final View.OnClickListener btnCancelCustomerChoose = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == btnDialogCancelSkip){
                dialogCancel.cancel();
                showDialogRate();
            }
            if(view == btnDialogCancelComment){
                dialogCancel.cancel();
                dialogComment= new Dialog(MainActivity.this,R.style.Theme_Dialog);
                dialogComment.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogComment.setContentView(R.layout.dialog_customer_cancel_comment);
                dialogComment.setCancelable(true);
                dialogComment.setCanceledOnTouchOutside(true);
                btnDialogCommentSkip = (Button) dialogComment.findViewById(R.id.btnDialogCommentSkip);
                btnDialogCommentSkip.setOnClickListener(btnCancelCustomerComment);
                btnDialogCommentSubmit = (Button) dialogComment.findViewById(R.id.btnDialogCommentSubmit);
                btnDialogCommentSubmit.setOnClickListener(btnCancelCustomerComment);
                dialogComment.show();
            }
        }
    };

    final View.OnClickListener btnCancelCustomerComment = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dialogComment.cancel();
            showDialogRate();
        }
    };

    private void showDialogRate() {
        dialogRate= new Dialog(MainActivity.this,R.style.Theme_Dialog_Rate);
        dialogRate.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogRate.setContentView(R.layout.dialog_rate_customer);
        dialogRate.setCancelable(true);
        dialogRate.setCanceledOnTouchOutside(true);

        linearLayoutDialogPerfect = (LinearLayout) dialogRate.findViewById(R.id.linearLayoutDialogPerfect);
        linearLayoutDialogPerfect.setOnClickListener(btnRateCustomer);
        linearLayoutDialogExcellent = (LinearLayout) dialogRate.findViewById(R.id.linearLayoutDialogExcellent);
        linearLayoutDialogExcellent.setOnClickListener(btnRateCustomer);
        linearLayoutDialogGood = (LinearLayout) dialogRate.findViewById(R.id.linearLayoutDialogGood);
        linearLayoutDialogGood.setOnClickListener(btnRateCustomer);
        linearLayoutDialogAdjust = (LinearLayout) dialogRate.findViewById(R.id.linearLayoutDialogAdjust);
        linearLayoutDialogAdjust.setOnClickListener(btnRateCustomer);
        linearLayoutDialogBad = (LinearLayout) dialogRate.findViewById(R.id.linearLayoutDialogBad);
        linearLayoutDialogBad.setOnClickListener(btnRateCustomer);

        dialogRate.show();
    }

    final View.OnClickListener btnRateCustomer = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dialogRate.cancel();
            chooseCustomer=-1;
            refreshMapsFragment();
        }
    };
}
