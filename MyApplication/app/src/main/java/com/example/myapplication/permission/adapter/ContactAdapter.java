package com.example.myapplication.permission.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.permission.data.ContactsData;

import java.util.List;

public class ContactAdapter extends ArrayAdapter {
    private final int resourceId;
    private final String TAG = "ContactAdapter";

    public ContactAdapter(@NonNull Context context, int textViewResourceId, @NonNull List objects) {
        super(context, textViewResourceId, objects);
        this.resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ContactsData contactsData = (ContactsData) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView contactNameView = (TextView) view.findViewById(R.id.contact_name);
        TextView contactNumberView = (TextView) view.findViewById(R.id.contact_number);
        contactNumberView.setText(contactsData.getContactName());
        contactNameView.setText(contactsData.getContactNumber());
        Log.d(TAG, "getView: name = "+contactsData.getContactName()+" number = "+contactsData.getContactNumber());
        return view;
    }
}
