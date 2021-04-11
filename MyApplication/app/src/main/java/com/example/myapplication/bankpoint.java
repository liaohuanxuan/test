package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;

import java.util.ArrayList;

public class bankpoint extends AppCompatActivity {
    MapView mMapView;
    AMap aMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankpoint);
        mMapView = findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        aMap = mMapView.getMap();

        CustonerInfoWindow a=new CustonerInfoWindow();
        aMap.setInfoWindowAdapter(a);
        addMark();
    }

    private void addMark() {
        //绘制默认Marker
        LatLng latLng = new LatLng(39.906901, 116.397972);
        Marker marker = aMap.addMarker(new MarkerOptions().position(latLng)//在地图上标记位置的经纬度值，必填参数
                .title("北京") //点标记的标题
                .snippet("DefaultMaker") //点标记的内容
                .draggable(true) //点标记是否可拖拽
                .visible(true) //点标记是否可见
                .anchor(0.2f, 0.4f)//点标记的锚点
                .alpha(0.9f)); //点的透明度

        LatLng latLng2 = new LatLng(39.918901, 116.399972);
        Marker marker2 = aMap.addMarker(new MarkerOptions().position(latLng2)//在地图上标记位置的经纬度值，必填参数
                .title("北京2") //点标记的标题
                .snippet("DefaultMaker2") //点标记的内容
                .draggable(true) //点标记是否可拖拽
                .visible(true) //点标记是否可见
                .anchor(0.2f, 0.4f)//点标记的锚点
                .alpha(0.9f)); //点的透明度

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
    class CustonerInfoWindow implements AMap.InfoWindowAdapter{

        View infoWindowView;
        @Override
        public View getInfoWindow(Marker marker) {
            if (infoWindowView == null){
                infoWindowView = getLayoutInflater().inflate(R.layout.view_customer_infowindow,null,false);
            }
            TextView tvWindow =   infoWindowView.findViewById(R.id.tv_window);//样式
            ImageView imageView= infoWindowView.findViewById(R.id.im_window);
            tvWindow.setText(marker.getSnippet());//内容
            return infoWindowView;
        }

        View infoContentsView;
        @Override
        public View getInfoContents(Marker marker) {
            if (infoContentsView == null){
                infoContentsView = new View(bankpoint.this);
                infoContentsView.setBackgroundResource(R.drawable.ic_launcher_background);
            }
            return infoContentsView;
        }
    }
}