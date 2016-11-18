package com.example.wangkuan.honghaizimuying;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

public class BaiDuJianSuoActivity extends AppCompatActivity {
    /**
     * 定位SDK核心类
     */
    private LocationClient locationClient;
    /**
     * 定位监听
     */
    public MyLocationListenner myListener = new MyLocationListenner();
    /**
     * 百度地图控件
     */
    private MapView mapView;
    /**
     * 百度地图对象
     */
    private BaiduMap baiduMap;

    boolean isFirstLoc = true; // 是否首次定位
    private TextView diZhi;
    private Button huiDiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_du_jian_suo);
        //MdqXOGYmc6kZts01GYHswtqw5utxdp8E


        //获取百度地图控件
        mapView = (MapView) findViewById(R.id.bmapView);
        diZhi = (TextView) findViewById(R.id.xiangXiDiZhi);
        huiDiao = (Button) findViewById(R.id.huiDiao);


        //获取百度地图对象
        baiduMap = mapView.getMap();
        // 开启定位图层
        baiduMap.setMyLocationEnabled(true);
        /**
         * 定位初始化
         */
        //声明定位SDK核心类
        locationClient = new LocationClient(this);
        //注册监听
        locationClient.registerLocationListener(myListener);
        //定位配置信息
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setServiceName("com.baidu.location.service_v2.9");
        option.setAddrType("all");
        option.setPriority(LocationClientOption.NetWorkFirst);
        option.setPriority(LocationClientOption.GpsFirst); // gps
        option.disableCache(true);

        locationClient.setLocOption(option);
        //开启定位
        locationClient.start();

    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        private static final String TAG = "MyLocationListenner";

        @Override
        public void onReceiveLocation(BDLocation location) {


            // map view 销毁后不在处理新接收的位置
            if (location == null || mapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            baiduMap.setMyLocationData(locData);

            String city = location.getCity();//得到城市
            String district = location.getDistrict();//得到区域
            String street = location.getStreet();//得到街道
            String streetNumber = location.getStreetNumber();//门牌号
            Toast.makeText(BaiDuJianSuoActivity.this, city, Toast.LENGTH_LONG).show();
            Log.i(TAG, city + "1111111111111111111");
            Log.i(TAG, district + "1111111111111111111");
            Log.i(TAG, street + "1111111111111111111");
            diZhi.setText("当前位置：" + city + " " + district + " " + street + " " + streetNumber + "");
            //回传结果
            Intent in = new Intent();
            in.putExtra("a", city);
            in.putExtra("b", district);
            in.putExtra("c", street);
            in.putExtra("d", streetNumber);
            setResult(100, in);
            huiDiao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
        locationClient.stop();
        // 关闭定位图层
        baiduMap.setMyLocationEnabled(false);
        mapView.onDestroy();
        mapView = null;
        super.onDestroy();
    }
}
