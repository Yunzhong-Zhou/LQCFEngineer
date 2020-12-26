package com.chetu.engineer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.chetu.engineer.R;
import com.chetu.engineer.activity.DianPuChuZhuDetailActivity;
import com.chetu.engineer.activity.DianPuChuZuActivity;
import com.chetu.engineer.activity.GongJuZhuLinDetailActivity;
import com.chetu.engineer.activity.GongJuZuLinActivity;
import com.chetu.engineer.activity.JiShuJiaoLiuActivity;
import com.chetu.engineer.activity.JiShuJiaoLiuDetailActivity;
import com.chetu.engineer.activity.JiYouQiuZhuActivity;
import com.chetu.engineer.activity.JiYouQiuZhuDetailActivity;
import com.chetu.engineer.activity.KuCunPeiJianActivity;
import com.chetu.engineer.activity.KuCunPeiJianDetailActivity;
import com.chetu.engineer.activity.QiuZhiZhaoPingActivity;
import com.chetu.engineer.activity.QiuZhiZhaoPingDetailActivity1;
import com.chetu.engineer.activity.ReMenHuoDongActivity;
import com.chetu.engineer.activity.ReMenHuoDongDetailActivity;
import com.chetu.engineer.activity.SearchActivity;
import com.chetu.engineer.activity.WebContentActivity;
import com.chetu.engineer.activity.WeiXiuAnLiActivity;
import com.chetu.engineer.activity.WeiXiuAnLiDetailActivity;
import com.chetu.engineer.adapter.CircleImageAdapter;
import com.chetu.engineer.base.BaseFragment;
import com.chetu.engineer.model.Fragment3Model;
import com.chetu.engineer.model.Fragment3Model_tab;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.popupwindow.PhotoShowDialog;
import com.chetu.engineer.utils.CommonUtil;
import com.chetu.engineer.utils.DateUtils;
import com.chetu.engineer.utils.MyLogger;
import com.cretin.tools.scancode.CaptureActivity;
import com.cretin.tools.scancode.config.ScanConfig;
import com.cy.dialog.BaseDialog;
import com.liaoinstan.springview.widget.SpringView;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.zaaach.citypicker.model.HotCity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;


/**
 * Created by fafukeji01 on 2016/1/6.
 * 论坛
 */
public class Fragment3 extends BaseFragment {
    RelativeLayout rl_search;
    EditText et_search;

    String i_classify = "8";//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
    String longitude = "", latitude = "", y_circle_id = "0", keys = "";

    Banner banner;
    List<String> images = new ArrayList<>();
    int page = 0;

    ImageView tv_scan;
    RelativeLayout rl_xiaoxi;
    TextView tv_xiaoxinum;

    /*RecyclerView recyclerView1;
    List<Fragment3Model.ListBean> list1 = new ArrayList<>();
    CommonAdapter<Fragment3Model.ListBean> mAdapter1;
    RecyclerView recyclerView2;
    List<Fragment3Model.ListBean> list2 = new ArrayList<>();
    CommonAdapter<Fragment3Model.ListBean> mAdapter2;
    RecyclerView recyclerView3;
    List<Fragment3Model.ListBean> list3 = new ArrayList<>();
    CommonAdapter<Fragment3Model.ListBean> mAdapter3;
    RecyclerView recyclerView4;
    List<Fragment3Model.ListBean> list4 = new ArrayList<>();
    CommonAdapter<Fragment3Model.ListBean> mAdapter4;
    RecyclerView recyclerView5;
    List<Fragment3Model.ListBean> list5 = new ArrayList<>();
    CommonAdapter<Fragment3Model.ListBean> mAdapter5;
    RecyclerView recyclerView6;
    List<Fragment3Model.ListBean> list6 = new ArrayList<>();
    CommonAdapter<Fragment3Model.ListBean> mAdapter6;*/

    int item = 0;
    RecyclerView rv_tab, rv_tab1;
    List<Fragment3Model_tab> list_tab = new ArrayList<>();
    CommonAdapter<Fragment3Model_tab> mAdapter_tab;
    CommonAdapter<Fragment3Model_tab> mAdapter_tab1;

    RecyclerView recyclerView;
    List<Fragment3Model.ListBean> list = new ArrayList<>();
    CommonAdapter<Fragment3Model.ListBean> mAdapter;

    //定位
    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;
    //城市选择
    TextView tv_addr;
    List<HotCity> hotCities = new ArrayList<>();
    String province = "", city = "", cityCode = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        StatusBarUtil.setTransparent(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLocationClient != null)
            mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }

    @Override
    public void onResume() {
        super.onResume();
        /*if (MainActivity.item == 2) {
            requestServer();
        }*/
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
       /* if (MainActivity.item == 2) {
            requestServer();
        }*/
    }

    @Override
    protected void initView(View view) {
//        CommonUtil.setMargins(findViewByID_My(R.id.headView), 0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);

        //刷新
        setSpringViewMore(true);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                Map<String, String> params = new HashMap<>();
                params.put("page", page + "");
                params.put("keys", keys);
                params.put("y_circle_id", y_circle_id);//圈子id
                params.put("i_classify", i_classify);//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
                params.put("u_token", localUserInfo.getToken());
                Request(params);
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("page", page + "");
                params.put("keys", keys);
                params.put("y_circle_id", y_circle_id);//圈子id
                params.put("i_classify", i_classify);//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
                RequestMore(params);
            }
        });
        tv_scan = findViewByID_My(R.id.tv_scan);
        tv_scan.setOnClickListener(this);
        tv_addr = findViewByID_My(R.id.tv_addr);
        tv_addr.setOnClickListener(this);
        rl_xiaoxi = findViewByID_My(R.id.rl_xiaoxi);
        rl_xiaoxi.setOnClickListener(this);
        tv_xiaoxinum = findViewByID_My(R.id.tv_xiaoxinum);
        rl_search = findViewByID_My(R.id.rl_search);
        rl_search.setOnClickListener(this);
        et_search = findViewByID_My(R.id.et_search);
        et_search.setOnClickListener(this);

        rv_tab = findViewByID_My(R.id.rv_tab);
        rv_tab.setLayoutManager(new GridLayoutManager(getActivity(), 4));

        rv_tab1 = findViewByID_My(R.id.rv_tab1);
        LinearLayoutManager llm1 = new LinearLayoutManager(getActivity());
        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        rv_tab1.setLayoutManager(llm1);

        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        list_tab.add(new Fragment3Model_tab(R.mipmap.ic_fragment3_1, "热门活动"));
        list_tab.add(new Fragment3Model_tab(R.mipmap.ic_fragment3_2, "求职招聘"));
        list_tab.add(new Fragment3Model_tab(R.mipmap.ic_fragment3_3, "交流圈"));
        list_tab.add(new Fragment3Model_tab(R.mipmap.ic_fragment3_4, "维修案例"));
        list_tab.add(new Fragment3Model_tab(R.mipmap.ic_fragment3_5, "机友求助"));
        list_tab.add(new Fragment3Model_tab(R.mipmap.ic_fragment3_6, "工具租赁"));
        list_tab.add(new Fragment3Model_tab(R.mipmap.ic_fragment3_7, "店铺出租"));
        list_tab.add(new Fragment3Model_tab(R.mipmap.ic_fragment3_8, "库存配件"));

        mAdapter_tab = new CommonAdapter<Fragment3Model_tab>
                (getActivity(), R.layout.item_fragment3_tab, list_tab) {
            @Override
            protected void convert(ViewHolder holder, Fragment3Model_tab model, int position) {
                ImageView iv = holder.getView(R.id.iv);
                iv.setImageResource(model.getImage());
                holder.setText(R.id.tv, model.getName());
            }
        };
        mAdapter_tab.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                switch (list_tab.get(i).getName()) {
                    case "热门活动":
                        CommonUtil.gotoActivity(getActivity(), ReMenHuoDongActivity.class);
                        break;
                    case "求职招聘":
                        CommonUtil.gotoActivity(getActivity(), QiuZhiZhaoPingActivity.class);
                        break;
                    case "交流圈":
                        CommonUtil.gotoActivity(getActivity(), JiShuJiaoLiuActivity.class);
                        break;
                    case "维修案例":
                        CommonUtil.gotoActivity(getActivity(), WeiXiuAnLiActivity.class);
                        break;
                    case "机友求助":
                        CommonUtil.gotoActivity(getActivity(), JiYouQiuZhuActivity.class);
                        break;
                    case "工具租赁":
                        CommonUtil.gotoActivity(getActivity(), GongJuZuLinActivity.class);
                        break;
                    case "店铺出租":
                        CommonUtil.gotoActivity(getActivity(), DianPuChuZuActivity.class);
                        break;
                    case "库存配件":
                        CommonUtil.gotoActivity(getActivity(), KuCunPeiJianActivity.class);
                        break;
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        rv_tab.setAdapter(mAdapter_tab);
        mAdapter_tab1 = new CommonAdapter<Fragment3Model_tab>
                (getActivity(), R.layout.item_fragment3_tab1, list_tab) {
            @Override
            protected void convert(ViewHolder holder, Fragment3Model_tab model, int position) {
                TextView textView = holder.getView(R.id.textView);
                textView.setText(model.getName());
                View view = holder.getView(R.id.view);
                if (item == position) {
                    textView.setTextColor(getResources().getColor(R.color.blue));
                    view.setVisibility(View.VISIBLE);
                } else {
                    textView.setTextColor(getResources().getColor(R.color.black));
                    view.setVisibility(View.INVISIBLE);
                }

            }
        };
        mAdapter_tab1.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                item = i;
                mAdapter_tab1.notifyDataSetChanged();
                switch (list_tab.get(i).getName()) {
                    case "热门活动":
                        i_classify = "8";//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
                        break;
                    case "求职招聘":
                        i_classify = "1";//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
                        break;
                    case "交流圈":
                        i_classify = "7";//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
                        break;
                    case "维修案例":
                        i_classify = "2";//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
                        break;
                    case "机友求助":
                        i_classify = "6";//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
                        break;
                    case "工具租赁":
                        i_classify = "5";//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
                        break;
                    case "店铺出租":
                        i_classify = "3";//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
                        break;
                    case "库存配件":
                        i_classify = "4";//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
                        break;
                }
                requestServer();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        rv_tab1.setAdapter(mAdapter_tab1);


        /*recyclerView1 = findViewByID_My(R.id.recyclerView1);
        LinearLayoutManager llm1 = new LinearLayoutManager(getActivity());
        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        recyclerView1.setLayoutManager(llm1);

        recyclerView2 = findViewByID_My(R.id.recyclerView2);
        LinearLayoutManager llm2 = new LinearLayoutManager(getActivity());
        llm2.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        recyclerView2.setLayoutManager(llm2);

        recyclerView3 = findViewByID_My(R.id.recyclerView3);
        LinearLayoutManager llm3 = new LinearLayoutManager(getActivity());
        llm3.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        recyclerView3.setLayoutManager(llm3);

        recyclerView4 = findViewByID_My(R.id.recyclerView4);
        LinearLayoutManager llm4 = new LinearLayoutManager(getActivity());
        llm4.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        recyclerView4.setLayoutManager(llm4);

        recyclerView5 = findViewByID_My(R.id.recyclerView5);
        LinearLayoutManager llm5 = new LinearLayoutManager(getActivity());
        llm5.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        recyclerView5.setLayoutManager(llm5);

        recyclerView6 = findViewByID_My(R.id.recyclerView6);
        recyclerView6.setLayoutManager(new LinearLayoutManager(getActivity()));*/


        banner = findViewByID_My(R.id.banner);
        //banner
        images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
        images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
        images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
        images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
        /*images.clear();
        for (int i = 0; i < response.getBanner().size(); i++) {
            images.add(OkHttpClientManager.IMGHOST+response.getBanner().get(i).getUrl());
        }*/
        banner.addBannerLifecycleObserver(this)//添加生命周期观察者
                .setDelayTime(3000)//设置轮播时间
                .setBannerGalleryEffect(10, 10)//为banner添加画廊效果
                .setAdapter(new CircleImageAdapter(images))
                .setIndicator(new CircleIndicator(getActivity()))
                .start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object data, int position) {
                /*Bundle bundle = new Bundle();
                bundle.putInt("type", response.getBanner().get(position).getType());
                CommonUtil.gotoActivityWithData(JiFenShangChengActivity.this, JiFenLieBiaoActivity.class, bundle, false);*/
            }
        });


    }

    @Override
    protected void initData() {
        requestServer();
        //热门城市
        hotCities.add(new HotCity("北京", "北京", "101010100")); //code为城市代码
        hotCities.add(new HotCity("上海", "上海", "101020100"));
        hotCities.add(new HotCity("广州", "广东", "101280101"));
        hotCities.add(new HotCity("深圳", "广东", "101280601"));
        hotCities.add(new HotCity("杭州", "浙江", "101210101"));
        //初始化定位
        mLocationClient = new AMapLocationClient(getActivity());
        AMapLocationClientOption option = new AMapLocationClientOption();
        //设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.Transport);

        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。AMapLocationMode.Battery_Saving，低功耗模式。AMapLocationMode.Device_Sensors，仅设备模式。
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        //获取一次定位结果：默认为false。
        option.setOnceLocation(true);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        option.setOnceLocationLatest(true);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        option.setInterval(5 * 1000);
        //设置是否返回地址信息（默认返回地址信息）
        option.setNeedAddress(true);
        //设置是否允许模拟位置,默认为true，允许模拟位置
        option.setMockEnable(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        option.setHttpTimeOut(30000);
        //是否开启定位缓存机制
        option.setLocationCacheEnable(false);

        mLocationClient.setLocationOption(option);

        //设置定位回调监听
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        MyLogger.i(">>>>>>>>>>定位信息：\n纬度：" + aMapLocation.getLatitude()
                                + "\n经度:" + aMapLocation.getLongitude()
                                + "\n地址:" + aMapLocation.getAddress());
//                        register_addr = aMapLocation.getAddress();

                        localUserInfo.setCityname(aMapLocation.getCity());
                        localUserInfo.setLongitude(longitude);
                        localUserInfo.setLatitude(latitude);

                        longitude = aMapLocation.getLongitude() + "";
                        latitude = aMapLocation.getLatitude() + "";

                        tv_addr.setText(aMapLocation.getCity() + "");

                        province = aMapLocation.getProvince();//省信息
                        city = aMapLocation.getCity();//城市信息
                        cityCode = aMapLocation.getCityCode();//城市编码

                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        MyLogger.e("定位失败：", "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                        myToast("" + aMapLocation.getErrorInfo());
                    }
                }
            }
        });

        //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
//        mLocationClient.stopLocation();
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        mLocationClient.startLocation();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_addr:
                //选择地址
                /*CityPicker.from(getActivity()) //activity或者fragment
                        .enableAnimation(true)    //启用动画效果，默认无
//                        .setAnimationStyle(anim)	//自定义动画
//                        .setLocatedCity(new LocatedCity("杭州", "浙江", "101210101"))  //APP自身已定位的城市，传null会自动定位（默认）
                        .setHotCities(hotCities)    //指定热门城市
                        .setOnPickListener(new OnPickListener() {
                            @Override
                            public void onPick(int position, City data) {
                                //选择的城市
                                tv_addr.setText(data.getName() + "");
                                city = data.getName();
                            }

                            @Override
                            public void onCancel() {
                                //取消
                                *//*Toast.makeText(getApplicationContext(), "取消选择", Toast.LENGTH_SHORT).show();*//*
                            }

                            @Override
                            public void onLocate() {
                                //定位接口，需要APP自身实现，这里模拟一下定位
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        //定位完成之后更新数据到城市选择器
                                        CityPicker.from(getActivity()).locateComplete(new LocatedCity(province,
                                                city, cityCode), LocateState.SUCCESS);
                                    }
                                }, 3000);
                            }
                        })
                        .show();*/
                break;
            case R.id.rl_search:
            case R.id.et_search:
                //搜索
                CommonUtil.gotoActivity(getActivity(), SearchActivity.class);
                break;
            case R.id.tv_scan:
                //扫一扫
                ScanConfig config = new ScanConfig()
                        .setShowFlashlight(true)//是否需要打开闪光灯
                        .setShowGalary(true)//是否需要打开相册
                        .setNeedRing(true);//是否需要提示音
                //ScanConfig 也可以不配置 默认都是打开
                CaptureActivity.launch(this, config);
                break;
            case R.id.rl_xiaoxi:
                String url = URLs.KFHOST + "/#/pages/chetu-kf/chetu-kf?token=" + localUserInfo.getToken() +
                        "&kf_userHash=" + localUserInfo.getKfuserhash() +
                        "&nickName=" + localUserInfo.getKfname() +
                        "&headerPic=" + localUserInfo.getKfhead();
                /*WebUtilsConfig config1 =
                        new WebUtilsConfig()
                                .setTitleBackgroundColor(R.color.colorPrimary)//设置标题栏背景色
//                                .setBackText("关闭")//设置返回按钮的文案
                                .setBackBtnRes(R.mipmap.ic_return_black)//设置返回按钮的图标
//                                .setMoreBtnRes(R.mipmap.more_web)//设置更多按钮的图标
                                .setShowBackText(false)//设置是否显示返回按钮的文案
                                .setShowMoreBtn(false)//设置是否显示更多按钮
                                .setShowTitleLine(false)//设置是否显示标题下面的分割线
                                .setShowTitleView(true)//设置是否显示标题栏，网页是全屏的时候可以选择隐藏标题栏
//                                .setTitleBackgroundRes(getResources().getColor(R.color.white))//设置标题栏背景资源
                                .setTitleBackgroundColor(R.color.white)
//                                .setBackTextColor(-1)//设置返回按钮的文案颜色
                                .setTitleTextColor(R.color.black)//设置标题文字颜色
                                .setStateBarTextColorDark(true)//设置状态栏文字颜色是否是暗色，如果你设置了标题栏背景颜色为白色，这里需要设置true，否则状态栏看不到文案了
                                .setTitleLineColor(R.color.app_title_color);//设置标题栏下面的分割线的颜色
                OpenWebActivity.openWebView(getActivity(), url, config1);*/
                Bundle bundle = new Bundle();
                bundle.putString("url", url);
                CommonUtil.gotoActivityWithData(getActivity(), WebContentActivity.class, bundle, false);
                /*//聊天列表
                String url1 = URLs.KFHOST + "/#/pages/chetu-kf/chat_list?token=" + localUserInfo.getToken();
                Bundle bundle1 = new Bundle();
                bundle1.putString("url", url1);
                CommonUtil.gotoActivityWithData(getActivity(), WebContentActivity.class, bundle1, false);*/
                break;

        }
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 0;
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("keys", keys);
        params.put("y_circle_id", y_circle_id);//圈子id
        params.put("i_classify", i_classify);//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
        params.put("u_token", localUserInfo.getToken());
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment3, params, headerMap, new CallBackUtil<Fragment3Model>() {
            @Override
            public Fragment3Model onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
                myToast(err);
            }

            @Override
            public void onResponse(Fragment3Model response) {
                hideProgress();

                list = response.getList();

                if (list.size() > 0) {
                    showContentPage();
                    switch (i_classify){//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
                        case "8":
                            //热门活动
                            mAdapter = new CommonAdapter<Fragment3Model.ListBean>
                                    (getActivity(), R.layout.item_remenhuodong, list) {
                                @Override
                                protected void convert(ViewHolder holder, Fragment3Model.ListBean model, int position) {
                                    holder.setText(R.id.tv_title, model.getV_title());
                                    holder.setText(R.id.tv_type, "类型：" + model.getActivity_info().getV_type());
                                    holder.setText(R.id.tv_name, "发起者：" + model.getUser_info().getUserName());
                                    holder.setText(R.id.tv_starttime, "开始时间：" + model.getActivity_info().getV_start_time());
                                    holder.setText(R.id.tv_endtime, "结束时间：" + model.getActivity_info().getV_end_time());
                                    holder.setText(R.id.tv_addr, model.getActivity_info().getV_place());
                                    holder.setText(R.id.tv_content, model.getActivity_info().getV_content());
                                    ImageView iv = holder.getView(R.id.iv);
                                    if (model.getActivity_info().getImgArr().size() > 0) {
                                        Glide.with(getActivity())
                                                .load(URLs.IMGHOST + model.getActivity_info().getImgArr().get(0))
                                                .centerCrop()
//                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                                .placeholder(R.mipmap.loading)//加载站位图
                                                .error(R.mipmap.zanwutupian)//加载失败
                                                .into(iv);//加载图片
                                    }

                                }
                            };
                            mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("ReMenHuoDongDetail", list.get(i));
                                    CommonUtil.gotoActivityWithData(getActivity(), ReMenHuoDongDetailActivity.class, bundle, false);
                                }

                                @Override
                                public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    return false;
                                }
                            });
                            break;
                        case "1":
                            //求职招聘
                            mAdapter = new CommonAdapter<Fragment3Model.ListBean>
                                    (getActivity(), R.layout.item_qiuzhizhaoping, list) {
                                @Override
                                protected void convert(ViewHolder holder, Fragment3Model.ListBean model, int position) {
                                    holder.setText(R.id.tv_title, model.getV_title());
//                            holder.setText(R.id.tv_money, model.getRecruit_info().getHandsOn() + "-" + model.getRecruit_info().getSalary() + "元");
                                    holder.setText(R.id.tv_money, model.getRecruit_info().getSalary() + "元");
                                    holder.setText(R.id.tv_phonenum, model.getRecruit_info().getTelephone());
                                    holder.setText(R.id.tv_name, model.getRecruit_info().getNameStore());
                                    holder.setText(R.id.tv_addr, "上班地址：" + model.getRecruit_info().getAddress());
                                    holder.setText(R.id.tv_time, model.getCreateDate());
                                }
                            };
                            mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("QiuZhiZhaoPingDetail", list.get(i));
                                    CommonUtil.gotoActivityWithData(getActivity(), QiuZhiZhaoPingDetailActivity1.class, bundle, false);
                                }

                                @Override
                                public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    return false;
                                }
                            });
                            break;
                        case "7":
                            //交流圈
                            mAdapter = new CommonAdapter<Fragment3Model.ListBean>
                                    (getActivity(), R.layout.item_jishujiaoliu, list) {
                                @Override
                                protected void convert(ViewHolder holder, Fragment3Model.ListBean model, int position) {
                                    holder.setText(R.id.tv_title, model.getExchange_info().getV_title());
                                    holder.setText(R.id.tv_text, model.getUser_info().getUserName());
                                    holder.setText(R.id.tv_content, model.getExchange_info().getV_describe());
                                    holder.setText(R.id.tv_shouchang, "" + model.getILike());
                                    holder.setText(R.id.tv_xihuan, "" + model.getIGive());
                                    ImageView iv = holder.getView(R.id.iv);
                                    Glide.with(getActivity())
                                            .load(URLs.IMGHOST + model.getUser_info().getHeadPortrait())
                                            .centerCrop()
//                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                            .placeholder(R.mipmap.loading)//加载站位图
                                            .error(R.mipmap.zanwutupian)//加载失败
                                            .into(iv);//加载图片

                                    //显示时间
                                    TextView tv_time = holder.getView(R.id.tv_time);
                                    try {
                                        Date date = new Date();
                                        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        date = simple.parse(model.getCreateDate());
                                        tv_time.setText(DateUtils.fromToday(date));
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                    //是否有图片
                                    RecyclerView rv_tupian = holder.getView(R.id.rv_tupian);
                                    if (model.getExchange_info().getImgArr() != null && model.getExchange_info().getImgArr().size() > 0) {
                                        rv_tupian.setVisibility(View.VISIBLE);

                                        LinearLayoutManager llm1 = new LinearLayoutManager(getActivity());
                                        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                                        rv_tupian.setLayoutManager(llm1);

                                        ArrayList<String> images = new ArrayList<>();
                                        for (String s : model.getExchange_info().getImgArr()) {
                                            images.add(URLs.IMGHOST + s);
                                        }
                                        CommonAdapter<String> mAdapter_tupian = new CommonAdapter<String>
                                                (getActivity(), R.layout.item_img_110_110, images) {
                                            @Override
                                            protected void convert(ViewHolder holder, String model, int position) {
                                                ImageView iv = holder.getView(R.id.iv);
                                                Glide.with(getActivity())
                                                        .load(model)
                                                        .centerCrop()
                                                        .placeholder(R.mipmap.loading)//加载站位图
                                                        .error(R.mipmap.zanwutupian)//加载失败
                                                        .into(iv);//加载图片
                                            }
                                        };
                                        mAdapter_tupian.setOnItemClickListener(new OnItemClickListener() {
                                            @Override
                                            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {

                                                PhotoShowDialog photoShowDialog = new PhotoShowDialog(getActivity(), images, i);
                                                photoShowDialog.show();
                                            }

                                            @Override
                                            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                                return false;
                                            }
                                        });
                                        rv_tupian.setAdapter(mAdapter_tupian);

                                    } else {
                                        rv_tupian.setVisibility(View.GONE);
                                    }


                                }
                            };
                            mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("JiShuJiaoLiuDetail", list.get(i));
                                    CommonUtil.gotoActivityWithData(getActivity(), JiShuJiaoLiuDetailActivity.class, bundle, false);
                                }

                                @Override
                                public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    return false;
                                }
                            });
                            break;
                        case "2":
                            //维修案例
                            mAdapter = new CommonAdapter<Fragment3Model.ListBean>
                                    (getActivity(), R.layout.item_weixiuanli, list) {
                                @Override
                                protected void convert(ViewHolder holder, Fragment3Model.ListBean model, int position) {
                                    holder.setText(R.id.tv_name, model.getUser_info().getUserName());
//                            holder.setText(R.id.tv_addr, model.getUser_info().getUserName());

                                    holder.setText(R.id.tv_title, model.getCase_info().getV_title());
                                    holder.setText(R.id.tv_content, model.getCase_info().getV_text());

                                    ImageView iv_head = holder.getView(R.id.iv_head);
                                    Glide.with(getActivity())
                                            .load(URLs.IMGHOST + model.getUser_info().getHeadPortrait())
                                            .centerCrop()
//                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                            .placeholder(R.mipmap.loading)//加载站位图
                                            .error(R.mipmap.zanwutupian)//加载失败
                                            .into(iv_head);//加载图片

                                    ImageView iv = holder.getView(R.id.iv);
                                    if (model.getCase_info().getImgArr().size() > 0) {
                                        Glide.with(getActivity())
                                                .load(URLs.IMGHOST + model.getCase_info().getImgArr().get(0))
                                                .centerCrop()
//                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                                .placeholder(R.mipmap.loading)//加载站位图
                                                .error(R.mipmap.zanwutupian)//加载失败
                                                .into(iv);//加载图片
                                    }

                                    //显示时间
                                    TextView tv_time = holder.getView(R.id.tv_time);
                                    tv_time.setText(model.getCreateDate());

                                }
                            };
                            mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("WeiXiuAnLiDetail", list.get(i));
                                    CommonUtil.gotoActivityWithData(getActivity(), WeiXiuAnLiDetailActivity.class, bundle, false);
                                }

                                @Override
                                public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    return false;
                                }
                            });
                            break;
                        case "6":
                            //机友求助
                            mAdapter = new CommonAdapter<Fragment3Model.ListBean>
                                    (getActivity(), R.layout.item_jiyouqiuzhu, list) {
                                @Override
                                protected void convert(ViewHolder holder, Fragment3Model.ListBean model, int position) {
                                    holder.setText(R.id.tv_name, model.getUser_info().getUserName());
                                    holder.setText(R.id.tv_time, model.getCreateDate());
                                    holder.setText(R.id.tv_addr, model.getHelp_info().getV_address());
                                    holder.setText(R.id.tv_title, model.getHelp_info().getV_title());
                                    holder.setText(R.id.tv_content, model.getHelp_info().getV_text());
                                    ImageView iv = holder.getView(R.id.iv);
                                    Glide.with(getActivity())
                                            .load(URLs.IMGHOST + model.getUser_info().getHeadPortrait())
                                            .centerCrop()
//                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                            .placeholder(R.mipmap.loading)//加载站位图
                                            .error(R.mipmap.zanwutupian)//加载失败
                                            .into(iv);//加载图片
                                    //是否有图片
                                    RecyclerView rv_tupian = holder.getView(R.id.rv_tupian);
                                    if (model.getHelp_info().getImgArr() != null && model.getHelp_info().getImgArr().size() > 0) {
                                        rv_tupian.setVisibility(View.VISIBLE);

                                        LinearLayoutManager llm1 = new LinearLayoutManager(getActivity());
                                        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                                        rv_tupian.setLayoutManager(llm1);

                                        ArrayList<String> images = new ArrayList<>();
                                        for (String s : model.getHelp_info().getImgArr()) {
                                            images.add(URLs.IMGHOST + s);
                                        }
                                        CommonAdapter<String> mAdapter_tupian = new CommonAdapter<String>
                                                (getActivity(), R.layout.item_img_110_110, images) {
                                            @Override
                                            protected void convert(ViewHolder holder, String model, int position) {
                                                ImageView iv = holder.getView(R.id.iv);
                                                Glide.with(getActivity())
                                                        .load(model)
                                                        .centerCrop()
                                                        .placeholder(R.mipmap.loading)//加载站位图
                                                        .error(R.mipmap.zanwutupian)//加载失败
                                                        .into(iv);//加载图片
                                            }
                                        };
                                        mAdapter_tupian.setOnItemClickListener(new OnItemClickListener() {
                                            @Override
                                            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {

                                                PhotoShowDialog photoShowDialog = new PhotoShowDialog(getActivity(), images, i);
                                                photoShowDialog.show();
                                            }

                                            @Override
                                            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                                return false;
                                            }
                                        });
                                        rv_tupian.setAdapter(mAdapter_tupian);

                                    } else {
                                        rv_tupian.setVisibility(View.GONE);
                                    }
                                    holder.getView(R.id.tv_huifu).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //回答
                                            dialog = new BaseDialog(getActivity());
                                            dialog.contentView(R.layout.dialog_edit)
                                                    .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                            ViewGroup.LayoutParams.WRAP_CONTENT))
                                                    .animType(BaseDialog.AnimInType.CENTER)
                                                    .canceledOnTouchOutside(true)
                                                    .dimAmount(0.8f)
                                                    .show();
                                            TextView tv_title = dialog.findViewById(R.id.tv_title);
                                            tv_title.setText("回复"+ model.getUser_info().getUserName());
                                            final EditText editText1 = dialog.findViewById(R.id.editText1);
                                            editText1.setHint("请输入回复内容");
//                editText1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                                            dialog.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    if (!editText1.getText().toString().trim().equals("")) {
                                                        CommonUtil.hideSoftKeyboard_fragment(v, getActivity());
                                                        dialog.dismiss();
                                                        showProgress(true,getString(R.string.app_loading1));
                                                        Map<String, String> params = new HashMap<>();
                                                        params.put("v_title", editText1.getText().toString().trim());
                                                        params.put("y_forum_posts_id", model.getYForumPostsId());
                                                        params.put("u_token", localUserInfo.getToken());
                                                        RequestUpData(params);
                                                    } else {
                                                        myToast("请输入回复内容");
                                                    }
                                                }
                                            });
                                            dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    dialog.dismiss();
                                                }
                                            });
                                        }
                                    });
                                }
                            };
                            mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("JiYouQiuZhuDetail", list.get(i));
                                    CommonUtil.gotoActivityWithData(getActivity(), JiYouQiuZhuDetailActivity.class, bundle, false);
                                }

                                @Override
                                public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    return false;
                                }
                            });
                            break;
                        case "5":
                            //工具租赁
                            mAdapter = new CommonAdapter<Fragment3Model.ListBean>
                                    (getActivity(), R.layout.item_gongjuzulin, list) {
                                @Override
                                protected void convert(ViewHolder holder, Fragment3Model.ListBean model, int position) {
                                    holder.setText(R.id.tv_name, model.getUser_info().getUserName());
//                            holder.setText(R.id.tv_addr, model.getUser_info().getUserName());

                                    holder.setText(R.id.tv_title, model.getTool_info().getV_title());
                                    holder.setText(R.id.tv_money, "" + model.getTool_info().getV_price());
                                    holder.setText(R.id.tv_longtime, "出租时长：" + model.getTool_info().getV_duration());
                                    ImageView iv_head = holder.getView(R.id.iv_head);
                                    Glide.with(getActivity())
                                            .load(URLs.IMGHOST + model.getUser_info().getHeadPortrait())
                                            .centerCrop()
//                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                            .placeholder(R.mipmap.loading)//加载站位图
                                            .error(R.mipmap.zanwutupian)//加载失败
                                            .into(iv_head);//加载图片

                                    ImageView iv = holder.getView(R.id.iv);
                                    if (model.getTool_info().getImgArr().size() > 0) {
                                        Glide.with(getActivity())
                                                .load(URLs.IMGHOST + model.getTool_info().getImgArr().get(0))
                                                .centerCrop()
//                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                                .placeholder(R.mipmap.loading)//加载站位图
                                                .error(R.mipmap.zanwutupian)//加载失败
                                                .into(iv);//加载图片
                                    }

                                    //显示时间
                                    TextView tv_time = holder.getView(R.id.tv_time);
                                    tv_time.setText(model.getCreateDate());
                                }
                            };
                            mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("GongJuZhuLinDetail", list.get(i));
                                    CommonUtil.gotoActivityWithData(getActivity(), GongJuZhuLinDetailActivity.class, bundle, false);
                                }

                                @Override
                                public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    return false;
                                }
                            });
                            break;
                        case "3":
                            //店铺出租
                            mAdapter = new CommonAdapter<Fragment3Model.ListBean>
                                    (getActivity(), R.layout.item_dianpuchuzu, list) {
                                @Override
                                protected void convert(ViewHolder holder, Fragment3Model.ListBean model, int position) {
                                    holder.setText(R.id.tv_name, model.getUser_info().getUserName());
                                    holder.setText(R.id.tv_time, model.getCreateDate());
                                    holder.setText(R.id.tv_addr, model.getLease_info().getV_address());
                                    holder.setText(R.id.tv_address, model.getLease_info().getV_address());
                                    holder.setText(R.id.tv_title, model.getLease_info().getV_title());
                                    holder.setText(R.id.tv_content, model.getLease_info().getV_text());
                                    holder.setText(R.id.tv_money, model.getLease_info().getV_cost() + "/月");
                                    holder.setText(R.id.tv_lianxiren, "联系人：" + model.getLease_info().getContacts());
                                    holder.setText(R.id.tv_phonenum, "联系方式：" + model.getLease_info().getContact_infor());

                                    ImageView iv = holder.getView(R.id.iv);
                                    Glide.with(getActivity())
                                            .load(URLs.IMGHOST + model.getUser_info().getHeadPortrait())
                                            .centerCrop()
//                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                            .placeholder(R.mipmap.loading)//加载站位图
                                            .error(R.mipmap.zanwutupian)//加载失败
                                            .into(iv);//加载图片

                                    //是否有图片
                                    RecyclerView rv_tupian = holder.getView(R.id.rv_tupian);
                                    if (model.getLease_info().getImgArr() != null && model.getLease_info().getImgArr().size() > 0) {
                                        rv_tupian.setVisibility(View.VISIBLE);

                                        LinearLayoutManager llm1 = new LinearLayoutManager(getActivity());
                                        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                                        rv_tupian.setLayoutManager(llm1);

                                        ArrayList<String> images = new ArrayList<>();
                                        for (String s : model.getLease_info().getImgArr()) {
                                            images.add(URLs.IMGHOST + s);
                                        }
                                        CommonAdapter<String> mAdapter_tupian = new CommonAdapter<String>
                                                (getActivity(), R.layout.item_img_110_110, images) {
                                            @Override
                                            protected void convert(ViewHolder holder, String model, int position) {
                                                ImageView iv = holder.getView(R.id.iv);
                                                Glide.with(getActivity())
                                                        .load(model)
                                                        .centerCrop()
                                                        .placeholder(R.mipmap.loading)//加载站位图
                                                        .error(R.mipmap.zanwutupian)//加载失败
                                                        .into(iv);//加载图片
                                            }
                                        };
                                        mAdapter_tupian.setOnItemClickListener(new OnItemClickListener() {
                                            @Override
                                            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {

                                                PhotoShowDialog photoShowDialog = new PhotoShowDialog(getActivity(), images, i);
                                                photoShowDialog.show();
                                            }

                                            @Override
                                            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                                return false;
                                            }
                                        });
                                        rv_tupian.setAdapter(mAdapter_tupian);

                                    } else {
                                        rv_tupian.setVisibility(View.GONE);
                                    }


                                }
                            };
                            mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("DianPuChuZhuDetail", list.get(i));
                                    CommonUtil.gotoActivityWithData(getActivity(), DianPuChuZhuDetailActivity.class, bundle, false);
                                }

                                @Override
                                public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    return false;
                                }
                            });
                            break;
                        case "4":
                            //库存配件
                            mAdapter = new CommonAdapter<Fragment3Model.ListBean>
                                    (getActivity(), R.layout.item_kucunpeijian, list) {
                                @Override
                                protected void convert(ViewHolder holder, Fragment3Model.ListBean model, int position) {
                                    holder.setText(R.id.tv_name, model.getUser_info().getUserName());
                                    holder.setText(R.id.tv_time, model.getCreateDate());
//                            holder.setText(R.id.tv_addr, model.getParts_info().get);
                                    holder.setText(R.id.tv_title, model.getParts_info().getV_title());
                                    holder.setText(R.id.tv_content, model.getParts_info().getV_text());
                                    holder.setText(R.id.tv_money, model.getParts_info().getV_price());
                                    holder.setText(R.id.tv_date, "购入日期：" + model.getParts_info().getV_bring());

                                    ImageView iv = holder.getView(R.id.iv);
                                    Glide.with(getActivity())
                                            .load(URLs.IMGHOST + model.getUser_info().getHeadPortrait())
                                            .centerCrop()
//                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                            .placeholder(R.mipmap.loading)//加载站位图
                                            .error(R.mipmap.zanwutupian)//加载失败
                                            .into(iv);//加载图片

                                    //是否有图片
                                    RecyclerView rv_tupian = holder.getView(R.id.rv_tupian);
                                    if (model.getParts_info().getImgArr() != null && model.getParts_info().getImgArr().size() > 0) {
                                        rv_tupian.setVisibility(View.VISIBLE);

                                        LinearLayoutManager llm1 = new LinearLayoutManager(getActivity());
                                        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                                        rv_tupian.setLayoutManager(llm1);

                                        ArrayList<String> images = new ArrayList<>();
                                        for (String s : model.getParts_info().getImgArr()) {
                                            images.add(URLs.IMGHOST + s);
                                        }
                                        CommonAdapter<String> mAdapter_tupian = new CommonAdapter<String>
                                                (getActivity(), R.layout.item_img_110_110, images) {
                                            @Override
                                            protected void convert(ViewHolder holder, String model, int position) {
                                                ImageView iv = holder.getView(R.id.iv);
                                                Glide.with(getActivity())
                                                        .load(model)
                                                        .centerCrop()
                                                        .placeholder(R.mipmap.loading)//加载站位图
                                                        .error(R.mipmap.zanwutupian)//加载失败
                                                        .into(iv);//加载图片
                                            }
                                        };
                                        mAdapter_tupian.setOnItemClickListener(new OnItemClickListener() {
                                            @Override
                                            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {

                                                PhotoShowDialog photoShowDialog = new PhotoShowDialog(getActivity(), images, i);
                                                photoShowDialog.show();
                                            }

                                            @Override
                                            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                                return false;
                                            }
                                        });
                                        rv_tupian.setAdapter(mAdapter_tupian);

                                    } else {
                                        rv_tupian.setVisibility(View.GONE);
                                    }


                                }
                            };
                            break;
                    }
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("KuCunPeiJianDetail", list.get(i));
                            CommonUtil.gotoActivityWithData(getActivity(), KuCunPeiJianDetailActivity.class, bundle, false);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                } else {
                    showEmptyPage();
                }
            }
        });

    }

    private void RequestMore(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment3, params, headerMap, new CallBackUtil<Fragment3Model>() {
            @Override
            public Fragment3Model onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(Fragment3Model response) {
                hideProgress();
                List<Fragment3Model.ListBean> list1 = new ArrayList<>();
                list1 = response.getList();
                if (list1.size() == 0) {
                    page--;
                    myToast(getString(R.string.app_nomore));
                } else {
                    list.addAll(list1);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    /**
     * 回复
     * @param params
     */
    private void RequestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.JiYouQiuZhu_HuiFu, params, headerMap, new CallBackUtil() {
            @Override
            public Object onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                if (!err.equals("")) {
                    showToast(err);
                }
            }

            @Override
            public void onResponse(Object response) {
                hideProgress();
                /*showToast("回复成功", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        requestServer();
                    }
                });*/
                myToast("回复成功");
                requestServer();
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == CaptureActivity.REQUEST_CODE_SCAN) {
            // 扫描二维码回传
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    //获取扫描结果
                    Bundle bundle = data.getExtras();
                    String result = bundle.getString(CaptureActivity.EXTRA_SCAN_RESULT);
                    MyLogger.i("扫码返回", result);
                }
            }
        }
    }
}
