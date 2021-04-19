package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;

import org.jetbrains.annotations.NotNull;

public class bankpoint extends AppCompatActivity implements AMap.OnMarkerClickListener, AMap.OnMapClickListener {
    MapView mMapView;
    AMap aMap;
    private Marker oldMarker;
    private LatLng myLatLng;
    private boolean infoWindowShown = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankpoint);
        mMapView = findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        aMap = mMapView.getMap();
        aMap.setInfoWindowAdapter( new CustonerInfoWindow());
        addMark(Constant.csbank1,"中国邮政储蓄银行","长沙市天心区白沙路325号白沙晶城1楼");
        addMark(Constant.csbank2,"中国银行","长沙市天心区书院南路56号 ");
        addMark(Constant.csbank3,"中国建设银行","长沙市天心区南湖路276号");
        addMark(Constant.csbank4,"湖南省农村信用社联合社","长沙市天心区芙蓉南路一段439号");
        aMap.setOnMarkerClickListener(this);
        aMap.setOnMapClickListener(this);
        LatLng latLng = new LatLng(28.1127,112.98991);
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,11));
    }

    private void addMark(LatLng latLng, String title, String snippet) {
        //绘制默认Marker
     //   LatLng latLng = new LatLng(39.906901, 116.397972);
        aMap.addMarker(new MarkerOptions().position(latLng)//在地图上标记位置的经纬度值，必填参数
                .title(title) //点标记的标题
                .snippet(snippet) //点标记的内容
                .draggable(false) //点标记是否可拖拽
                .visible(true) //点标记是否可见
                .anchor(0.2f, 0.4f)//点标记的锚点
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_normal))
                .alpha(0.9f)); //点的透明度
    }


    //maker的点击事件
    @Override
    public boolean onMarkerClick(Marker marker) {

        //点击的marker不是自己位置的那个marker
            if (oldMarker != null) {
                oldMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_normal));
            }
            oldMarker = marker;
            marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_selected));
           infoWindowShown=false;


        return false; //返回 “false”，除定义的操作之外，默认操作也将会被执行
    }
    public void onMapClick(LatLng latLng){
        if (oldMarker.isInfoWindowShown()&& !infoWindowShown){
            infoWindowShown = true;
            return;
        }
        Toast.makeText(bankpoint.this, oldMarker.getId(), Toast.LENGTH_SHORT).show();
            oldMarker.hideInfoWindow();
            oldMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_normal));
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    //自定义infoWindow
   public class CustonerInfoWindow implements AMap.InfoWindowAdapter, View.OnClickListener {
        private LatLng latLng;
        private LinearLayout call;
        private LinearLayout navigation;
        private TextView nameTV;
        private String agentName;
        private TextView addrTV;
        private String snippet;
        private ImageView image;
        private String phonnum;

        View infoWindowView;

        private void initData(@NotNull Marker marker) { //初始化点
            latLng = marker.getPosition();
            snippet = marker.getSnippet();
            agentName = marker.getTitle();
        }
        @Override
        public View getInfoWindow(Marker marker) {
            initData(marker);
            infoWindowView = getLayoutInflater().inflate(R.layout.view_infowindow,null,false);
            navigation = infoWindowView.findViewById(R.id.navigation_LL);
            call = infoWindowView.findViewById(R.id.call_LL);
            nameTV = infoWindowView.findViewById(R.id.name);
            addrTV = infoWindowView.findViewById(R.id.addr);
            image= infoWindowView.findViewById(R.id.images);
            select(marker);
            nameTV.setText(agentName);
           // addrTV.setText(snippet);
            addrTV.setText(String.format(getResources().getString(R.string.agent_addr),snippet));
            navigation.setOnClickListener(this);
            call.setOnClickListener(this);
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
        public void onClick(View v) {
            int id = v.getId();
            switch (id){
                case R.id.navigation_LL:  //点击导航
                    NavigationUtils.Navigation(latLng);
                    break;

                case R.id.call_LL:  //点击打电话
                    PhoneCallUtils.call(phonnum);//TODO 处理电话号码
                    break;
            }
        }
        public void select(Marker marker){ //选择Marker


            switch (marker.getTitle()){
                case "中国邮政储蓄银行":
                    image.getDrawable().setLevel(0);
                    phonnum="0731-85577492";
                    break;
                case "中国银行":
                    image.getDrawable().setLevel(1);
                    phonnum="0731-85421112 ";
                    break;
                case "中国建设银行":
                    image.getDrawable().setLevel(2);
                    phonnum=" 0731-85297181";
                    break;
                case "湖南省农村信用社联合社":
                    image.getDrawable().setLevel(3);
                    phonnum="0731-82278780";
                    break;
            }
        }
    }
}