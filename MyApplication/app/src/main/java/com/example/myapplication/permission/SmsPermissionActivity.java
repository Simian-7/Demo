package com.example.myapplication.permission;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class SmsPermissionActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perm_sms);
    }
}
