package com.example.myapplication.permission;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class MainPermissionActivity extends Activity implements View.OnClickListener{
    private Button contactPermBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permission_demo);
        initUI();
        addListener();
    }

    private void initUI(){
        contactPermBtn = findViewById(R.id.permission_contact);
    }

    private void addListener(){
        contactPermBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.permission_contact:
                startContactPermActivity();
                break;
            default:
                break;
        }
    }

    //start Contact Permission
    private void startContactPermActivity(){
        Intent intent = new Intent(this,ContactPermActivity.class);
        startActivity(intent);
    }
}
