package com.chetu.engineer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.AMapNaviViewListener;
import com.amap.api.navi.enums.NaviType;
import com.amap.api.navi.model.AMapCalcRouteResult;
import com.amap.api.navi.model.AMapLaneInfo;
import com.amap.api.navi.model.AMapModelCross;
import com.amap.api.navi.model.AMapNaviCameraInfo;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviRouteNotifyData;
import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AMapServiceAreaInfo;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.utils.MyLogger;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zyz on 2019-10-22.
 */
public class MapNavigationActivity extends BaseActivity implements AMapNaviListener, AMapNaviViewListener {
    AMapNaviView mAMapNaviView;
    AMapNavi mAMapNavi;

    double startlat = 0, startlng = 0, endlat = 0, endlng = 0;
    List<NaviLatLng> sList = new ArrayList<NaviLatLng>();//开始点
    List<NaviLatLng> eList = new ArrayList<NaviLatLng>();//结束点
    List<NaviLatLng> mWayPointList = new ArrayList<NaviLatLng>();//途经点

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapnavigation);

        mAMapNaviView = (AMapNaviView) findViewById(R.id.navi_view);
        mAMapNaviView.setAMapNaviViewListener(MapNavigationActivity.this);
        mAMapNaviView.onCreate(savedInstanceState);

        //初始化导航
        mAMapNavi = AMapNavi.getInstance(getApplicationContext());
        mAMapNavi.addAMapNaviListener(MapNavigationActivity.this);
        mAMapNavi.setUseInnerVoice(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAMapNaviView.onResume();


    }

    @Override
    protected void onPause() {
        super.onPause();
        mAMapNaviView.onPause();
//        停止导航之后，会触及底层stop，然后就不会再有回调了，但是讯飞当前还是没有说完的半句话还是会说完
//        mAMapNavi.stopNavi();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAMapNaviView.onDestroy();
        //since 1.6.0 不再在naviview destroy的时候自动执行AMapNavi.stopNavi();请自行执行
        mAMapNavi.stopNavi();
        mAMapNavi.destroy();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        showProgress(true, "定位中，正在搜索路线...");
        startlat = getIntent().getDoubleExtra("startlat", 0);
        startlng = getIntent().getDoubleExtra("startlng", 0);
        //开始点
        sList.clear();
        NaviLatLng mStartLatlng = new NaviLatLng(startlat, startlng);
        sList.add(mStartLatlng);

        //结束点
        endlat = getIntent().getDoubleExtra("endlat", 0);
        endlng = getIntent().getDoubleExtra("endlng", 0);
        eList.clear();
        NaviLatLng mEndLatlng = new NaviLatLng(endlat, endlng);
        eList.add(mEndLatlng);//结束点

    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();

    }

    @Override
    protected void updateView() {
        titleView.setTitle("地图导航");
    }


    /**
     * *******************************导航回调****************************************************
     */

    @Override
    public void onInitNaviSuccess() {
        //初始化成功
//        mAMapNavi.startGPS();
        /*//货车信息
        if (aMapCarInfo == null) {
            aMapCarInfo = new AMapCarInfo();
            aMapCarInfo.setCarType(model.getTindent().getCar_type_info().getCar_type());//设置车辆类型，0小车，1货车

            aMapCarInfo.setCarNumber(model.getTindent().getCar_type_info().getCar_number());//设置车辆的车牌号码. 如:京DFZ239,京ABZ239
            aMapCarInfo.setVehicleSize(model.getTindent().getCar_type_info().getVehicle_siz());// * 设置货车的大小
            aMapCarInfo.setVehicleLoad(model.getTindent().getCar_type_info().getVehicle_load());//设置货车的最大载重，单位：吨。
            aMapCarInfo.setVehicleWeight(model.getTindent().getCar_type_info().getVehicle_weight());//设置货车的载重
            aMapCarInfo.setVehicleLength(model.getTindent().getCar_type_info().getVehicle_length());//  * 设置货车的最大长度，单位：米。
            aMapCarInfo.setVehicleWidth(model.getTindent().getCar_type_info().getVehicle_width());//设置货车的最大宽度，单位：米。 如:1.8，1.5等等。
            aMapCarInfo.setVehicleHeight(model.getTindent().getCar_type_info().getVehicle_height());//设置货车的高度，单位：米。
            aMapCarInfo.setVehicleAxis(model.getTindent().getCar_type_info().getVehicle_axis());//设置货车的轴数
            aMapCarInfo.setVehicleLoadSwitch(true);//设置车辆的载重是否参与算路
            aMapCarInfo.setRestriction(true);//设置是否躲避车辆限行。
        }
        aMapCarInfo.setVehicleLoadSwitch(true);
        mAMapNavi.setCarInfo(aMapCarInfo);*/
        /**
         * 方法: int strategy=mAMapNavi.strategyConvert(congestion, avoidhightspeed, cost, hightspeed, multipleroute); 参数:
         *
         * @congestion 躲避拥堵
         * @avoidhightspeed 不走高速
         * @cost 避免收费
         * @hightspeed 高速优先
         * @multipleroute 多路径
         *
         *  说明: 以上参数都是boolean类型，其中multipleroute参数表示是否多条路线，如果为true则此策略会算出多条路线。
         *  注意: 不走高速与高速优先不能同时为true 高速优先与避免收费不能同时为true
         */
        int strategy = 0;
        try {
            //再次强调，最后一个参数为true时代表多路径，否则代表单路径
            strategy = mAMapNavi.strategyConvert(true, false, false, false, false);
//            strategy = mAMapNavi.strategyConvert(true, false, false, false, true);

        } catch (Exception e) {
            e.printStackTrace();
        }

        MyLogger.i(">>>>>>>>规划路线>>>onInitNaviSuccess" + eList.size() + ">>>>>>" + sList.size());
//        mAMapNavi.calculateDriveRoute(sList, eList, mWayPointList, strategy);//有开始点
        mAMapNavi.calculateDriveRoute(eList, mWayPointList, strategy);//没开始点

       /* AmapNaviParams naviParams = new AmapNaviParams(null, null, null, AmapNaviType.DRIVER);
        AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(), naviParams,null);*/
    }

    @Override
    public void onCalculateRouteSuccess(int[] ids) {
        MyLogger.i(">>>>>开始导航>>>>>>onCalculateRouteSuccess");
        hideProgress();
        //多路径算路成功回调
        //设置模拟导航的行车速度
//        mAMapNavi.setEmulatorNaviSpeed(200);//模拟速度
//        mAMapNavi.startNavi(NaviType.EMULATOR);//模拟导航
        mAMapNavi.startNavi(NaviType.GPS);//实时导航

        /**
         * 获取当前路线导航限制信息（例如： 限高，限宽）
         *//*
        List<AMapNaviLimitInfo> limitInfos = mAMapNavi.getNaviPath().getLimitInfos();

        *//**
         * 获取当前路线导航禁行信息 (例如：禁行)
         *//*
        List<AMapNaviForbiddenInfo> forbiddenInfos = mAMapNavi.getNaviPath().getForbiddenInfos();

        int forbiddenCount = 0;
        int limitHeight = 0;
        int limitWidth = 0;
        if (limitInfos != null) {
            for (int i = 0; i < limitInfos.size(); i++) {
                AMapNaviLimitInfo limitInfo = limitInfos.get(i);
                *//**
         * 81 : 货车限高
         * 82 : 货车限宽
         * 83 : 货车限重
         *//*
                if (limitInfo.type == NaviLimitType.TYPE_TRUCK_WIDTH_LIMIT) {
                    ++limitWidth;
                } else if (limitInfo.type == NaviLimitType.TYPE_TRUCK_HEIGHT_LIMIT) {
                    ++limitHeight;
                }
            }
        }

        if (forbiddenInfos != null) {
            forbiddenCount = forbiddenInfos.size();

            for (int i = 0; i < forbiddenInfos.size(); i++) {
                AMapNaviForbiddenInfo forbiddenInfo = forbiddenInfos.get(i);
                *//**
         * 0: 禁止左转
         * 1: 禁止右转
         * 2: 禁止左掉头
         * 3: 禁止右掉头
         * 4: 禁止直行
         *//*
                switch (forbiddenInfo.forbiddenType) {
                    case NaviForbidType.FORBID_TURN_LEFT:
                        MyLogger.i("当前路线有禁止左转");
                        break;
                    case NaviForbidType.FORBID_TURN_RIGHT:
                        MyLogger.i("当前路线有禁止右转");
                        break;
                    case NaviForbidType.FORBID_TURN_LEFT_ROUND:
                        MyLogger.i("当前路线有禁止左掉头");
                        break;
                    case NaviForbidType.FORBID_TURN_RIGHT_ROUND:
                        MyLogger.i("当前路线有禁止右掉头");
                        break;
                    case NaviForbidType.FORBID_GO_STRAIGHT:
                        MyLogger.i("当前路线有禁止直行");
                        break;
                    default:
                }

            }
        }

        String limitStr = "有";
        if (limitHeight > 0) {
            limitStr += limitHeight + "处限高，";
        }

        if (limitWidth > 0) {
            limitStr += limitHeight + "处限宽，";
        }

        if (forbiddenCount > 0) {
            limitStr += forbiddenCount + "处限行，";
        }

        if (limitStr.length() > 2) {
            limitStr = limitStr.substring(0, limitStr.length() - 1);
            limitStr += "无法避开";
        } else {
            limitStr = null;
        }

        if (!TextUtils.isEmpty(limitStr)) {
            Toast.makeText(this, limitStr, Toast.LENGTH_LONG).show();
        }*/

    }

    @Override
    public void onNaviSetting() {
        //底部导航设置点击回调
    }

    @Override
    public void onNaviCancel() {
        finish();
    }

    @Override
    public boolean onNaviBackClick() {
        return false;

    }

    @Override
    public void onNaviMapMode(int i) {
        //导航态车头模式，0:车头朝上状态；1:正北朝上模式。
    }

    @Override
    public void onNaviTurnClick() {
        //转弯view的点击回调
    }

    @Override
    public void onNextRoadClick() {
        //下一个道路View点击回调
    }

    @Override
    public void onScanViewButtonClick() {
        //全览按钮点击回调
    }

    @Override
    public void onLockMap(boolean b) {
        //锁地图状态发生变化时回调
    }

    @Override
    public void onNaviViewLoaded() {
        MyLogger.i("wlx", "导航页面加载成功");
//        MyLogger.i("wlx", "请不要使用AMapNaviView.getMap().setOnMapLoadedListener();会overwrite导航SDK内部画线逻辑");
    }

    @Override
    public void onMapTypeChanged(int i) {

    }

    @Override
    public void onNaviViewShowMode(int i) {

    }

    @Override
    public void onInitNaviFailure() {
        showToast("初始化导航失败", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
    }

    @Override
    public void onStartNavi(int i) {
        //启动导航后的回调函数
    }

    @Override
    public void onTrafficStatusUpdate() {

    }

    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {
        //当GPS位置有更新时的回调函数
        /*MyLogger.i(">>>>>>>>>GPS位置" + aMapNaviLocation.getCoord().getLatitude());
        DPoint mStartDPoint = new DPoint(aMapNaviLocation.getCoord().getLatitude(),
                aMapNaviLocation.getCoord().getLongitude());//起点-当前坐标
        DPoint mEndDPoint = new DPoint(lat, lng);//终点当前位置（为了测试，实际情况用下面参数）
        *//*DPoint mEndDPoint = new DPoint(Double.valueOf(model.getTindent().getNext_addr().getLat())
                , Double.valueOf(model.getTindent().getNext_addr().getLng()));//终点*//*
        juli = CoordinateConverter.calculateLineDistance(mStartDPoint, mEndDPoint);
        MyLogger.i(">>>>>>>>距目的地直线距离" + juli);*/
    }

    @Override
    public void onGetNavigationText(int i, String s) {
        //导航播报信息回调函数。
    }

    @Override
    public void onGetNavigationText(String s) {
        //导航播报信息回调函数。
    }

    @Override
    public void onEndEmulatorNavi() {

    }

    @Override
    public void onArriveDestination() {
        //到达目的地后回调函数。

    }

    @Override
    public void onCalculateRouteFailure(int errorInfo) {
        //路线计算失败
        showToast("路线计算失败", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
//        Log.i("dm", "--------------------------------------------");
//        Log.i("dm", "路线计算失败：错误码=" + errorInfo + ",Error Message= " + ErrorInfo.getError(errorInfo));
//        Log.i("dm", "错误码详细链接见：http://lbs.amap.com/api/android-navi-sdk/guide/tools/errorcode/");
//        Log.i("dm", "--------------------------------------------");
//        Toast.makeText(this, "errorInfo：" + errorInfo + ",Message：" + ErrorInfo.getError(errorInfo), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onReCalculateRouteForYaw() {

    }

    @Override
    public void onReCalculateRouteForTrafficJam() {

    }

    @Override
    public void onArrivedWayPoint(int i) {

    }

    @Override
    public void onGpsOpenStatus(boolean b) {

    }

    @Override
    public void onNaviInfoUpdate(NaviInfo naviInfo) {

//        MyLogger.i(">>>>>>>>距目的地实际距离:" + naviInfo.getPathRetainDistance());
//        juli = naviInfo.getPathRetainDistance();

//        MyLogger.i(">>>>>>>>距目的地剩余时间:"+naviInfo.getPathRetainTime());

        /*if (naviInfo.getPathRetainDistance() <100){
            if (tv_right.getText().toString().trim().equals("去装货")){
                tv_right.setText("确认装货");
            }else if (tv_right.getText().toString().trim().equals("去卸货")){
                tv_right.setText("确认卸货");
            }
        }*/
    }

   /* @Override
    public void onNaviInfoUpdated(AMapNaviInfo aMapNaviInfo) {

    }*/

    @Override
    public void updateCameraInfo(AMapNaviCameraInfo[] aMapNaviCameraInfos) {

    }

    @Override
    public void updateIntervalCameraInfo(AMapNaviCameraInfo aMapNaviCameraInfo, AMapNaviCameraInfo aMapNaviCameraInfo1, int i) {

    }

    @Override
    public void onServiceAreaUpdate(AMapServiceAreaInfo[] aMapServiceAreaInfos) {

    }

    @Override
    public void showCross(AMapNaviCross aMapNaviCross) {

    }

    @Override
    public void hideCross() {

    }

    @Override
    public void showModeCross(AMapModelCross aMapModelCross) {

    }

    @Override
    public void hideModeCross() {

    }

    @Override
    public void showLaneInfo(AMapLaneInfo[] aMapLaneInfos, byte[] bytes, byte[] bytes1) {

    }

    @Override
    public void showLaneInfo(AMapLaneInfo aMapLaneInfo) {

    }

    @Override
    public void hideLaneInfo() {

    }

    @Override
    public void notifyParallelRoad(int i) {
        switch (i) {
            case 0:
                Toast.makeText(this, "当前在主辅路过渡", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "当前在主路", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "当前在辅路", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] aMapNaviTrafficFacilityInfos) {

    }

    @Override
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo aMapNaviTrafficFacilityInfo) {

    }

   /* @Override
    public void OnUpdateTrafficFacility(TrafficFacilityInfo trafficFacilityInfo) {

    }*/

    @Override
    public void updateAimlessModeStatistics(AimLessModeStat aimLessModeStat) {

    }

    @Override
    public void updateAimlessModeCongestionInfo(AimLessModeCongestionInfo aimLessModeCongestionInfo) {

    }

    @Override
    public void onPlayRing(int i) {

    }

    @Override
    public void onCalculateRouteSuccess(AMapCalcRouteResult aMapCalcRouteResult) {
        //算路成功回调
    }

    @Override
    public void onCalculateRouteFailure(AMapCalcRouteResult aMapCalcRouteResult) {
        //步行或者驾车路径规划失败后的回调函数
    }

    @Override
    public void onNaviRouteNotify(AMapNaviRouteNotifyData aMapNaviRouteNotifyData) {

    }

    @Override
    public void onGpsSignalWeak(boolean b) {

    }
}
