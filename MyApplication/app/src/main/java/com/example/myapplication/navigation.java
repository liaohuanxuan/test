package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.AmapPageType;
import com.amap.api.navi.model.NaviLatLng;

import java.util.ArrayList;
import java.util.List;

public class navigation extends AppCompatActivity {
    MapView mMapView;
    AMap aMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        navigation(getApplicationContext(),28.176636,112.977233,28.132868,112.987818);
    }
    /**
     * 路线规划
     *
     * @param slat 起点纬度
     * @param slon 起点经度
     * @param dlat 终点纬度
     * @param dlon 终点经度
     */
    public void navigation(Context context, double slat, double slon, double dlat, double dlon) {
        Poi start = null;
        //如果设置了起点
        if (slat != 0 && slon != 0) {
            start = new Poi("起点名称", new LatLng(slat, slon), "");
        }
        Poi end = new Poi("csbank", new LatLng(dlat, dlon), "");
        AmapNaviParams params = new AmapNaviParams(start, null, end, AmapNaviType.DRIVER);
        params.setUseInnerVoice(true);
        params.setMultipleRouteNaviMode(true);
        params.setNeedDestroyDriveManagerInstanceWhenNaviExit(true);
        //发起导航
        AmapNaviPage.getInstance().showRouteActivity(context, params, null);
    }

}
