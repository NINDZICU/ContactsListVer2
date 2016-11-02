package com.example.khlopunov.contactslistver2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khlopunov.contactslistver2.R;
import com.example.khlopunov.contactslistver2.adapters.ContactsRecyclerAdapter;

/**
 * Created by Admin on 30.10.2016.
 */

public class RecyclerFragment extends Fragment{
    RecyclerView rv;
    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    int pageNumber;

   public static RecyclerFragment newInstance(int page) {
        RecyclerFragment pageFragment = new RecyclerFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber=getArguments().getInt(ARGUMENT_PAGE_NUMBER);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_recycler, container, false);
        rv = (RecyclerView) view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        ContactsRecyclerAdapter adapter = new ContactsRecyclerAdapter(pageNumber, getActivity());
        rv.setAdapter(adapter);

        return view;
    }
}
