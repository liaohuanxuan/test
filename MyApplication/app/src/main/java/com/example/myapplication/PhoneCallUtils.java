package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import static com.amap.api.maps.model.BitmapDescriptorFactory.getContext;

public class PhoneCallUtils {
    public  static void call(String phoneNum){
        //打开拨号界面并显示10086
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+phoneNum));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().startActivity(intent);
    }
}
