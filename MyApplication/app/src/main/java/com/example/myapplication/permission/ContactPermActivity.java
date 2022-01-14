package com.example.myapplication.permission;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.permission.adapter.ContactAdapter;
import com.example.myapplication.permission.data.ContactsData;
import com.example.myapplication.permission.util.PermissionUtil;

import java.util.ArrayList;

public class ContactPermActivity extends Activity implements View.OnClickListener{

    private Button contactSearchBtn;
    private ListView contactListView;
    private static final  Uri contactUri = Uri.parse("content://com.android.contacts/raw_contacts");
    private static final String TAG = "ContactPermActivity";
    private static final String[] needPermissionList = {"android.permission.READ_CONTACTS","android.permission.WRITE_CONTACTS"};
    private ContactAdapter mContactAdapter;
    private ArrayList<ContactsData> contactList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perm_contact);
        initContactUI();
        initContactAction();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initContactUI(){
        contactSearchBtn = findViewById(R.id.perm_contact_search);
        contactListView = findViewById(R.id.perm_contact_list);
    }

    @SuppressLint("ResourceType")
    private void initContactAction(){
        contactSearchBtn.setOnClickListener(this);
        contactList = new ArrayList<ContactsData>();
        mContactAdapter = new ContactAdapter(this,R.layout.perm_contacts_list,contactList);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.perm_contact_search:
                //查询通讯录
                PermissionUtil.addPermissionList(this,needPermissionList,1);

                break;
            default:
                break;
        }
    }

    private void searchContact(){
        ContentResolver contactResolver = getContentResolver();
        Cursor cursor = contactResolver.query(contactUri,null,null,null,null);
        ContactsData contactsData = new ContactsData();
        if(cursor.getCount() > 0){
            while (cursor.moveToNext()){
                //Contact id
                @SuppressLint("Range")
                String contactID = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                //Contacts name
                @SuppressLint("Range")
                String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                contactsData.contactName = contactName;
                Log.d(TAG, "contactName : "+contactsData.getContactName());
                Cursor cursor2 = contactResolver.query(Uri.parse("content://com.android.contacts/contacts/"+contactID+"/data"),null,null,null,null);
                while(cursor2.moveToNext()){
                    //Contact phone number
                    @SuppressLint("Range")
                    String contactNumber = cursor2.getString(cursor2.getColumnIndex("data1"));
                    contactsData.contactNumber = contactNumber;
                    Log.d(TAG, "contactNumber : "+contactsData.getContactNumber());
                    contactList.add(contactsData);
                }
                cursor2.close();
            }
            cursor.close();
        }
        contactListView.setAdapter(mContactAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasAllGranted = true;
        for(int i = 0; i < permissions.length; i++){
            Log.d(TAG, "onRequestPermissionsResult: permissions"+ permissions[i]);
        }
        Log.d(TAG, "onRequestPermissionsResult11: hasAllGranted = "+hasAllGranted);
        for (int grantResult : grantResults){
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                Log.d(TAG, "onRequestPermissionsResult: grantResult = "+grantResult);
                hasAllGranted = false;
                break;
            }
        }
        if (hasAllGranted){
            searchContact();
            Log.d(TAG, "onRequestPermissionsResult: hasAllGranted = "+hasAllGranted);
        } else {
            //处理禁止权限操作
            Log.d(TAG, "onRequestPermissionsResult22: hasAllGranted = "+hasAllGranted);
            Toast.makeText(this,"This permission can't allow!",Toast.LENGTH_SHORT).show();
        }
    }
}
