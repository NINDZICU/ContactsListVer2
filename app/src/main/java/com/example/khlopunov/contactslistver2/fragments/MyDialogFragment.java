package com.example.khlopunov.contactslistver2.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.example.khlopunov.contactslistver2.MainActivity;
import com.example.khlopunov.contactslistver2.entities.Contact;
import com.example.khlopunov.contactslistver2.providers.ContactsProvider;

import java.util.List;

/**
 * Created by Admin on 02.11.2016.
 */

public class MyDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    private Contact contact;

    public static MyDialogFragment newInstance(Contact contact) {
        MyDialogFragment fragment = new MyDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable("contact", contact);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        contact = (Contact) getArguments().getSerializable("contact");
        AlertDialog.Builder adb = new AlertDialog.Builder (getActivity()).setTitle("Delete number?").
                setNegativeButton("No", this).setPositiveButton("Yes", this)
                .setMessage("Вы не сможете отменить это действие");
        return adb.create();
    }



    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case Dialog.BUTTON_POSITIVE:
                String number = contact.getNumber();
                List<Contact> contactList = ContactsProvider.getInstance(getActivity()).getContacts();
                List<Contact> deletedContactList1 = ContactsProvider.getInstance(getActivity()).getDeletedContacts();
                for (int i = 0; i < contactList.size(); i++) {
                    if(contactList.get(i).getNumber().equals(number)){
                        contactList.remove(i);
                        break;
                    }
                }
                deletedContactList1.add(contact);
                ContactsProvider.getInstance(getActivity()).saveContacts(contactList);
                ContactsProvider.getInstance(getActivity()).saveDeletedContacts(deletedContactList1);
                ((MainActivity) getActivity()).getPagerAdapter().notifyDataSetChanged();
                break;
            case Dialog.BUTTON_NEGATIVE:
                dismiss();
                break;
        }


    }
}
