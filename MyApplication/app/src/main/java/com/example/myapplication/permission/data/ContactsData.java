package com.example.myapplication.permission.data;

public class ContactsData {

    //contact data
    public String contactName;
    public String contactID;
    public String contactNumber;

    @Override
    public String toString() {
        return "ContactsData{" +
                "contactName='" + contactName + '\'' +
                ", contactID='" + contactID + '\'' +
                ", contentNumber='" + contactNumber + '\'' +
                '}';
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactID() {
        return contactID;
    }

    public void setContactID(String contactID) {
        this.contactID = contactID;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
