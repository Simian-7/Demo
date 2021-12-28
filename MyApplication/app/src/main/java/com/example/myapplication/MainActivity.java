package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.permission.MainPermissionActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        addListener();
    }

    //init ui
    private void initUI(){
        btnPermission = findViewById(R.id.permission_demo);
    }

    //add listener
    private void addListener(){
        btnPermission.setOnClickListener(this);
    }

    //add onclick action
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.permission_demo:
                openPermissionManager();
                break;
            default:
                break;
        }
    }

    //enter permission demo page
    private void openPermissionManager(){
        Intent intent = new Intent(this,MainPermissionActivity.class);
        startActivity(intent);
    }
}