package com.example.khlopunov.contactslistver2.entities;

import java.io.Serializable;

/**
 * Created by Admin on 30.10.2016.
 */

public class Contact implements Serializable {
    private String name;
    private String number;

    public Contact(String name, String number) {

        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
}
