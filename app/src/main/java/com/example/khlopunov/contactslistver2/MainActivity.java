package com.example.khlopunov.contactslistver2;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.khlopunov.contactslistver2.adapters.MyPagerAdapter;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;
    MyPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.pager);
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(myPagerAdapter);

    }
    public MyPagerAdapter getPagerAdapter(){
        return myPagerAdapter;
    }
}
