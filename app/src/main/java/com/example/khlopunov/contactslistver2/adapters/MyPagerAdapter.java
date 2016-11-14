package com.example.khlopunov.contactslistver2.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.khlopunov.contactslistver2.Update;
import com.example.khlopunov.contactslistver2.fragments.RecyclerFragment;

/**
 * Created by Admin on 30.10.2016.
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return RecyclerFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Contacts";
            default:
                return "DeletedContacts";
        }
    }

    @Override
    public int getItemPosition(Object object) {
        ((Update)object).updateAdapter();
        return super.getItemPosition(object);
    }
}
