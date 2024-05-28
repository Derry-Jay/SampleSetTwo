package com.set.two.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Contact implements Parcelable, Serializable {
    //private variables
//Convention to declare private variables to start with underscore.
    String _name;

    long _phoneNumber;

    // Empty constructor
    public Contact() {
        setName("");
        setPhoneNumber(0);
    }

    // constructor
    public Contact(String name, long phone_number) {
        this._name = name;
        this._phoneNumber = phone_number;
    }

    protected Contact(Parcel in) {
        _name = in.readString();
        _phoneNumber = in.readLong();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Contact c = (Contact) obj;
        return _phoneNumber == c._phoneNumber;
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    // getting name
    public String getName() {
        return this._name;
    }

    // setting name
    public void setName(String name) {
        this._name = name;
    }

    // getting phone number
    public long getPhoneNumber() {
        return this._phoneNumber;
    }

    // setting phone number
    public void setPhoneNumber(long phone_number) {
        this._phoneNumber = phone_number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(_name);
        dest.writeLong(_phoneNumber);
    }
}