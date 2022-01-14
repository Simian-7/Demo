package com.example.myapplication.permission;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class MainPermissionActivity extends Activity implements View.OnClickListener{
    private Button contactPermBtn,manageSingleAppBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permission_demo);
        initUI();
        addListener();
    }

    private void initUI(){
        contactPermBtn = findViewById(R.id.permission_contact);
        manageSingleAppBtn = findViewById(R.id.permission_singleApp);
    }

    private void addListener(){
        contactPermBtn.setOnClickListener(this);
        manageSingleAppBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.permission_contact:
                startContactPermActivity();
                break;
            case R.id.permission_singleApp:
                startManageSignleAppActivity();
            default:
                break;
        }
    }

    //start Contact Permission
    private void startContactPermActivity(){
        Intent intent = new Intent(this,ContactPermActivity.class);
        startActivity(intent);
    }

    private void startManageSignleAppActivity(){
        Intent intent = new Intent(this, ManageSingleAppActivity.class);
        startActivity(intent);
    }
}
