package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.widget.Toast;

import com.amap.api.maps.model.LatLng;

import java.io.File;

import static com.amap.api.maps.model.BitmapDescriptorFactory.getContext;

public class NavigationUtils  {
    public static void Navigation(LatLng latLng){
       // mContext = BaseApplication.getIntance().getBaseContext();


        if(isInstallPackage("com.autonavi.minimap")){
//            Toast.makeText(getContext(), "安装有高德地图", Toast.LENGTH_SHORT).show();
            SkipToGD(latLng);
        }else {
            Toast.makeText(getContext(), "请下载安装高德地图", Toast.LENGTH_SHORT).show();
        }
    }
    private static boolean isInstallPackage(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }
    private static void SkipToGD(LatLng latLng) {
        //跳转导航
        //dev 是否偏移(0:lat 和 lon 是已经加密后的,不需要国测加密; 1:需要国测加密)
        //style 导航方式(0 速度快; 1 费用少; 2 路程短; 3 不走高速；4 躲避拥堵；
        // 5 不走高速且避免收费；6 不走高速且躲避拥堵；7 躲避收费和拥堵；8 不走高速躲避收费和拥堵))
        Uri uri = Uri.parse("androidamap://navi?sourceApplication=CloudPatient&lat="+latLng.latitude+"&lon="+latLng.longitude+"&dev=0&style=2");
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().startActivity(intent);
    }
}
