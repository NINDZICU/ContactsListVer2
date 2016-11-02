package com.example.khlopunov.contactslistver2.adapters;

import android.support.v4.app.DialogFragment;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khlopunov.contactslistver2.R;
import com.example.khlopunov.contactslistver2.entities.Contact;
import com.example.khlopunov.contactslistver2.fragments.InfoFragment;
import com.example.khlopunov.contactslistver2.fragments.MyDialogFragment;
import com.example.khlopunov.contactslistver2.providers.ContactsProvider;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Admin on 30.10.2016.
 */

public class ContactsRecyclerAdapter extends RecyclerView.Adapter<ContactsRecyclerAdapter.ContactsViewHolder> {
    //    private List<Contact> mContacts = addContacts();
    private List<Contact> mContacts;
    private FragmentActivity fragmentActivity;
    int pageNumber;

    public ContactsRecyclerAdapter(int pageNumber, FragmentActivity fragmentActivity) {
        this.pageNumber = pageNumber;
        this.fragmentActivity = fragmentActivity;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.contact_item,
                parent,
                false
        );
        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        final Contact contact = mContacts.get(position);
        holder.tv_name_contact.setText(contact.getName());
        holder.tv_number_contact.setText(contact.getNumber());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoFragment fragment = new InfoFragment().newInstance(contact);
                fragmentActivity.getSupportFragmentManager().popBackStack();
                fragmentActivity.getSupportFragmentManager().beginTransaction().
                        replace(R.id.frame_layout_info,
                                fragment,
                                InfoFragment.class.getSimpleName()).addToBackStack(null).commit();
            }
        });
        if (pageNumber == 0) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    DialogFragment dialog = new MyDialogFragment().newInstance(contact);
                    dialog.show(fragmentActivity.getSupportFragmentManager(), MyDialogFragment.class.getName());
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (pageNumber == 0) {
            mContacts = ContactsProvider.getInstance(fragmentActivity).getContacts();
        } else {
            mContacts = ContactsProvider.getInstance(fragmentActivity).getDeletedContacts();
        }
        return mContacts.size();
    }

//    public LinkedList<Contact> addContacts() {
//        LinkedList<Contact> contacts = new LinkedList<>();
//        for (int i = 0; i < 20; i++) {
//            Contact contact = new Contact(
//                    "UserName" + i,
//                    "880055353" + i
//            );
//            contacts.add(contact);
//        }
//        return contacts;
//    }


    public class ContactsViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_contact;
        TextView tv_name_contact;
        TextView tv_number_contact;

        public ContactsViewHolder(View itemView) {
            super(itemView);
            iv_contact = (ImageView) itemView.findViewById(R.id.iv_photo_contact);
            tv_name_contact = (TextView) itemView.findViewById(R.id.tv_name);
            tv_number_contact = (TextView) itemView.findViewById(R.id.tv_number);

        }
    }
}
