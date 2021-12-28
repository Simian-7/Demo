package com.example.myapplication.permission.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public final class PermissionUtil {

    //check sdk version and add permission
    public static void addPermissionList(Activity activity, String[] permissions, int request){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> mPermissionList = new ArrayList<>();
            for (int i = 0; i < permissions.length; i++){
                if(ContextCompat.checkSelfPermission(activity,permissions[i]) !=
                        PackageManager.PERMISSION_GRANTED){
                    mPermissionList.add(permissions[i]);
                }
            }
            if (!mPermissionList.isEmpty()){
                String[] mPermissionArray = mPermissionList.toArray(new String[mPermissionList.size()]);
                ActivityCompat.requestPermissions(activity,mPermissionArray,request);
            }
        }
    }
}
