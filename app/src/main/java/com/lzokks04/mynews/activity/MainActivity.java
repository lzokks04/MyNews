package com.lzokks04.mynews.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.Toast;

import com.lzokks04.mynews.R;
import com.lzokks04.mynews.adapter.MyFragmentPagerAdapter;
import com.lzokks04.mynews.fragment.CaijingFragment;
import com.lzokks04.mynews.fragment.GuojiFragment;
import com.lzokks04.mynews.fragment.GuoneiFragment;
import com.lzokks04.mynews.fragment.JunshiFragment;
import com.lzokks04.mynews.fragment.KejiFragment;
import com.lzokks04.mynews.fragment.ShehuiFragment;
import com.lzokks04.mynews.fragment.ShishangFragment;
import com.lzokks04.mynews.fragment.TiyuFragment;
import com.lzokks04.mynews.fragment.TopFragment;
import com.lzokks04.mynews.fragment.YuleFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    private MyFragmentPagerAdapter adapter;
    private List<Fragment> fragmentList;
    private long lastTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        initViewPager();
    }

    private void initToolbar() {
        mToolbar.setTitle("新闻");
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
    }

    private void initViewPager() {
        addFragment(fragmentList = new ArrayList<Fragment>());
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(10);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    private List<Fragment> addFragment(List<Fragment> list) {
        list.add(new TopFragment());
        list.add(new ShehuiFragment());
        list.add(new GuoneiFragment());
        list.add(new GuojiFragment());
        list.add(new YuleFragment());
        list.add(new TiyuFragment());
        list.add(new JunshiFragment());
        list.add(new KejiFragment());
        list.add(new CaijingFragment());
        list.add(new ShishangFragment());
        return list;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - lastTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                lastTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
