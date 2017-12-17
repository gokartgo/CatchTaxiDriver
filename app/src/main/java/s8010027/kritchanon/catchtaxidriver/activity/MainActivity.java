package s8010027.kritchanon.catchtaxidriver.activity;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import s8010027.kritchanon.catchtaxidriver.R;
import s8010027.kritchanon.catchtaxidriver.adapter.MainAdapter;
import s8010027.kritchanon.catchtaxidriver.fragment.MapsFragment;
import s8010027.kritchanon.catchtaxidriver.util.ImageTextButton;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    MainAdapter mainAdapter;
    ListView listView;
    ArrayList<ImageTextButton> menuName = new ArrayList<ImageTextButton>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstance();
        
        if(savedInstanceState == null) {
            //set fragment to activity
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, MapsFragment.newInstance(), "MapsFragment")
                    .commit();
        }

    }

    private void initInstance() {
        //setup toolbar
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //setup drawerLayout
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this
                ,drawerLayout
                ,R.string.open_drawer
                ,R.string.close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        listView = (ListView) findViewById(R.id.listView);
        mainAdapter = new MainAdapter(menuName);
        listView.setAdapter(mainAdapter);
        listView.setOnItemClickListener(listViewClick);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setMenu();
    }

    private void setMenu() {
        menuName.add(new ImageTextButton(R.drawable.icon_mobile,R.string.menu_1));
        menuName.add(new ImageTextButton(R.drawable.icon_mobile,R.string.menu_2));
        menuName.add(new ImageTextButton(R.drawable.icon_mobile,R.string.menu_3));
        menuName.add(new ImageTextButton(R.drawable.icon_mobile,R.string.menu_4));
        menuName.add(new ImageTextButton(R.drawable.icon_mobile,R.string.menu_5));
        menuName.add(new ImageTextButton(R.drawable.icon_mobile,R.string.menu_6));
        menuName.add(new ImageTextButton(R.drawable.icon_mobile,R.string.menu_7));
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

    AdapterView.OnItemClickListener listViewClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            if(position == 0){
                Toast.makeText(MainActivity.this,"asdfjkl;",Toast.LENGTH_LONG).show();
            }
            if(position == 1){
                Toast.makeText(MainActivity.this,"asdfjkl;asdf;",Toast.LENGTH_LONG).show();
            }
        }
    };

}
