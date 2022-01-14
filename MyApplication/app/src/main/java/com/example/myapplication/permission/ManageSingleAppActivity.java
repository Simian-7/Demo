package com.example.myapplication.permission;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class ManageSingleAppActivity extends Activity implements View.OnClickListener{

    private static final String TAG = "ManageSingleAppActivity";
    private EditText inputEdit;
    private Button searchBtn;
    private ListView showAppPerm;
    private boolean isExistPkg = false;
    PackageInfo mPackageInfo;;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perm_signle_app);
        initUI();
        addAction();
    }

    private void initUI(){
        inputEdit = findViewById(R.id.input_signle_app);
        searchBtn = findViewById(R.id.search_signle_app);
        showAppPerm = findViewById(R.id.show_signle_app_permission);
    }

    private void addAction() {
        searchBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search_signle_app:
                showAppPermissions();
                break;
            default:
                break;
        }
    }

    private void showAppPermissions() {
        String pkgName = inputEdit.getText().toString();
        PackageManager mPackageManager = getPackageManager();
        isExistPkg = true;
        Log.d(TAG,"pkgName = "+pkgName);
        //first check system is install this app
        try{
            mPackageManager.getPackageInfo(pkgName,PackageManager.GET_ACTIVITIES);
            mPackageInfo = mPackageManager.getPackageInfo(pkgName,PackageManager.GET_PERMISSIONS);
        } catch (NameNotFoundException e) {
            //If package not find, toast the not find info.
            Toast.makeText(this,R.string.not_find_package_name,Toast.LENGTH_SHORT).show();
            isExistPkg = false;
        }
        //If package exist, show all permission info.
        if(isExistPkg){
            String[] pkgPermission = mPackageInfo.requestedPermissions;
            for (int i = 0; i < pkgPermission.length; i++){
                String permission = pkgPermission[i];
                Log.d(TAG,"permission : "+permission);
            }
        }
    }
}
