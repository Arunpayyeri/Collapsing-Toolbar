package com.example.divya.collapse;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> personNames = new ArrayList<>(Arrays.asList("", "", "", "", ""));
    ArrayList<Integer> personImages = new ArrayList<>(Arrays.asList(R.drawable.st1, R.drawable.st2, R.drawable.st4, R.drawable.st3, R.drawable.st5));

    Toolbar androidToolbar;
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    CoordinatorLayout mRootLayout;
    GridView mGridView;

    /*static final String[] gridViewGridValue = new String[] {

            "Grid 1", "Grid 2", "Grid 3", "Grid 4", "Grid 5", "Grid 6", "Grid 7", "Grid 8",
            "Grid 9", "Grid 10", "Grid 11", "Grid 12", "Grid 13", "Grid 14", "Grid 15", "Grid 16",
            "Grid 17", "Grid 18", "Grid 19", "Grid 20", "Grid 21", "Grid 22", "Grid 23", "Grid 24",
            "Grid 25", "Grid 26", "Grid 27", "Grid 28", "Grid 29", "Grid 30", "Grid 31", "Grid 32",
            "Grid 33", "Grid 34", "Grid 35"

    }; */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initInstances();





        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayoutAndroidExample);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.android_appbar_layout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("Qatar Opera");
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });






        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
       // mGridView = (GridView) findViewById(R.id.gridview_parallax_header);
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
         //       android.R.layout.simple_list_item_1, gridViewGridValue);

        //mGridView.setAdapter(adapter);
       /* mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Toast.makeText(getApplicationContext(),
                        ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        }); */
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager); // set LayoutManager to RecyclerView
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, personNames, personImages);
        recyclerView.setAdapter(customAdapter);
    }

    private void initToolbar() {
        androidToolbar = (Toolbar) findViewById(R.id.toolbar_android);
        setSupportActionBar(androidToolbar);
    }

    private void initInstances() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutAndroidExample);
        mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRootLayout = (CoordinatorLayout) findViewById(R.id.coordinatorRootLayout);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayoutAndroidExample);
        //mCollapsingToolbarLayout.setTitle("Qatar Opera");
       // mCollapsingToolbarLayout.setTitleEnabled(false);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
