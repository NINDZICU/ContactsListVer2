package com.example.khlopunov.contactslistver2.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.khlopunov.contactslistver2.R;
import com.example.khlopunov.contactslistver2.entities.Contact;

import java.io.Serializable;

/**
 * Created by Admin on 02.11.2016.
 */

public class InfoFragment extends Fragment {
    TextView tvNumber;
    Button btnSend;


    public static InfoFragment newInstance(Contact contact) {
        InfoFragment fragment = new InfoFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable("contact", contact);
        fragment.setArguments(arguments);
        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, null);
        final Contact contact = (Contact) getArguments().getSerializable("contact");
        tvNumber = (TextView) view.findViewById(R.id.tv_number);
        btnSend = (Button) view.findViewById(R.id.btn_send);
        tvNumber.setText(contact.getNumber());

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("sms_body", contact.getNumber());
                startActivity(smsIntent);
            }
        });

        return view;
    }
}
