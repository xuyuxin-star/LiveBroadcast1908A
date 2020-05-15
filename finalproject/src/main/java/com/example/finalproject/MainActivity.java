package com.example.finalproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.finalproject.activity.DownUpLoadActivity;
import com.example.finalproject.adapter.MainAdapter;
import com.example.finalproject.fragment.CollectionFragment;
import com.example.finalproject.fragment.HomeFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp_main;
    private TabLayout tab_main;
    private Toolbar toolbar;
    private ConstraintLayout cl_main;
    private NavigationView nv;
    private DrawerLayout dl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        //头部监听
        View headerView = nv.getHeaderView(0);
        TextView tv_header = headerView.findViewById(R.id.tv_header);
        ImageView iv_header = headerView.findViewById(R.id.iv_header);
        iv_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到相册选取图片
            }
        });

        tv_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            }
        });

        //侧滑条目点击事件
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item_down_upload:
                        startActivity(new Intent(MainActivity.this, DownUpLoadActivity.class));
                        break;
                }
                return false;
            }
        });

        //dl点击事件
        dl.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                int right = drawerView.getRight();
                cl_main.setX(right);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    private void initView() {
        vp_main = (ViewPager) findViewById(R.id.vp_main);
        tab_main = (TabLayout) findViewById(R.id.tab_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        cl_main = (ConstraintLayout) findViewById(R.id.cl_main);
        nv = (NavigationView) findViewById(R.id.nv);
        dl = (DrawerLayout) findViewById(R.id.dl);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new CollectionFragment());

        MainAdapter adapter = new MainAdapter(getSupportFragmentManager(), fragments);
        vp_main.setAdapter(adapter);
        tab_main.setupWithViewPager(vp_main);

        tab_main.getTabAt(0).setText("首页").setIcon(R.drawable.selector_home);
        tab_main.getTabAt(1).setText("收藏").setIcon(R.drawable.selector_collection);

        toolbar.setTitle("首页");
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, toolbar, R.string.app_name, R.string.app_name);
        dl.addDrawerListener(toggle);
        toggle.syncState();
    }
}
