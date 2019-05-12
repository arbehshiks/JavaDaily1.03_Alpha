package com.example.javadaily;

//import android.support.design.widget.TabLayout;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import java.io.IOException;
import java.sql.SQLException;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        DBHelper dbHelper;
//        dbHelper = new DBHelper(this);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(2, false);
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        private Fragment[] childFragments;
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            childFragments = new Fragment[] {
                    new Tests(),
                    new Exams(),
                    new Home(),
                    new Profile(),
                    new Notes(),
            };
        }
        @Override
        public Fragment getItem(int position) {
            return childFragments[position];
        }
        @Override
        public int getCount() {
            return childFragments.length; //3 items
        }
        @Override
        public CharSequence getPageTitle(int position) {
            String title = getItem(position).getClass().getName();
            return title.subSequence(title.lastIndexOf(".") + 1, title.length());
        }

    }
}
