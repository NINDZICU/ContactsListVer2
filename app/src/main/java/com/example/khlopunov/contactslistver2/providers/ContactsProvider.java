package com.example.khlopunov.contactslistver2.providers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.khlopunov.contactslistver2.entities.Contact;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Admin on 02.11.2016.
 */

public class ContactsProvider {
    public static final String CONTACT_PREFERENCES = "ContactsList";
    public static final String PREFERENCES_NAME = "ContactName";
    public static final String PREFERENCES_DELETED_NAME = "DeletedContactName";

    private static ContactsProvider sInstance;
    public static final String PREFERENCES_NUMBER = "ContactNumber";
    private Context context;

    public static ContactsProvider getInstance(@NonNull Context context) {
        if (sInstance == null) {
            sInstance = new ContactsProvider(context.getApplicationContext());
        }
        return sInstance;
    }

    public ContactsProvider(Context context) {
        this.context = context;
    }

    public List<Contact> getContacts(){
        SharedPreferences preferences = context.getSharedPreferences(CONTACT_PREFERENCES, Context.MODE_PRIVATE);
        if(preferences.contains(PREFERENCES_NAME)){
            Gson gson = new Gson();
            String jsonText = preferences.getString(PREFERENCES_NAME, "");
            Type listType = new TypeToken<List<Contact>>(){}.getType();
            List<Contact> contacts = gson.fromJson(jsonText, listType);
            return contacts;
        }
        else{
            List<Contact> contacts=addContacts();
            saveContacts(contacts);
            return contacts;
        }
    }
    public List<Contact> getDeletedContacts(){
        SharedPreferences preferences = context.getSharedPreferences(CONTACT_PREFERENCES, Context.MODE_PRIVATE);
        if(preferences.contains(PREFERENCES_DELETED_NAME)){
            Gson gson = new Gson();
            String jsonText = preferences.getString(PREFERENCES_DELETED_NAME, "");
            Type listType = new TypeToken<List<Contact>>(){}.getType();
            List<Contact> contacts = gson.fromJson(jsonText, listType);
            return contacts;
        }
        else{
            List<Contact> contacts=new LinkedList<>();
            saveDeletedContacts(contacts);
            return contacts;
        }
    }

    public void saveContacts(List<Contact> contacts){
        SharedPreferences preferences = context.getSharedPreferences(CONTACT_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =preferences.edit();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Contact>>(){}.getType();
        String jsonText = gson.toJson(contacts, listType);
        editor.putString(PREFERENCES_NAME, jsonText);
        editor.commit();
    }
    public void saveDeletedContacts(List<Contact> contacts){
        SharedPreferences preferences = context.getSharedPreferences(CONTACT_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =preferences.edit();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Contact>>(){}.getType();
        String jsonText = gson.toJson(contacts, listType);
        editor.putString(PREFERENCES_DELETED_NAME, jsonText);
        editor.commit();

    }

    public List<Contact> addContacts() {
        List<Contact> contacts = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            Contact contact = new Contact(
                    "UserName" + i,
                    "880055353" + i
            );
            contacts.add(contact);
        }
        return contacts;
    }


}
