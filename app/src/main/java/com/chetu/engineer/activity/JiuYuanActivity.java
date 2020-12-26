package com.chetu.engineer.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.Fragment2Model_JiuYuan;
import com.chetu.engineer.model.JiuYuanTypeModel;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.popupwindow.PhotoShowDialog;
import com.chetu.engineer.utils.CommonUtil;
import com.chetu.engineer.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/5/28.
 * 救援
 */
public class JiuYuanActivity extends BaseActivity {
    //    String id = "";
    Fragment2Model_JiuYuan.ListBean model;

    TextView tv_carnum, tv_name, tv_carname, tv_carpailiang, tv_carnianfen, tv_carlicheng,
            tv_phonenum, tv_addr, tv_content,tv_type,tv_id;
    RelativeLayout rl_confirm;

    RecyclerView rv;

    String longitude = "", latitude = "";

    //定位
    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiuyuan);
        mImmersionBar.reset()
                .statusBarColor(R.color.background)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();

        //初始化定位
        mLocationClient = new AMapLocationClient(this);
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
                        localUserInfo.setLongitude(aMapLocation.getLongitude() + "");
                        localUserInfo.setLatitude(aMapLocation.getLatitude() + "");

                        longitude = aMapLocation.getLongitude() + "";
                        latitude = aMapLocation.getLatitude() + "";

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
    public void onDestroy() {
        super.onDestroy();
        if (mLocationClient != null)
            mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                Map<String, String> params = new HashMap<>();
                params.put("y_rescue_id", model.getYRescueId());
                params.put("u_token", localUserInfo.getToken());
                RequestType(params);
            }

            @Override
            public void onLoadmore() {
            }
        });
        tv_id = findViewByID_My(R.id.tv_id);
        tv_carnum = findViewByID_My(R.id.tv_carnum);
        tv_name = findViewByID_My(R.id.tv_name);
        tv_carname = findViewByID_My(R.id.tv_carname);
        tv_carpailiang = findViewByID_My(R.id.tv_carpailiang);
        tv_carnianfen = findViewByID_My(R.id.tv_carnianfen);
        tv_carlicheng = findViewByID_My(R.id.tv_carlicheng);
        tv_phonenum = findViewByID_My(R.id.tv_phonenum);
        tv_addr = findViewByID_My(R.id.tv_addr);
        tv_content = findViewByID_My(R.id.tv_content);
        tv_type = findViewByID_My(R.id.tv_type);
        rv = findViewByID_My(R.id.rv);
        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        rv.setLayoutManager(llm1);

        rl_confirm = findViewByID_My(R.id.rl_confirm);
    }

    @Override
    protected void initData() {
//        id = getIntent().getStringExtra("id");
        model = (Fragment2Model_JiuYuan.ListBean) getIntent().getSerializableExtra("jiuyuan");

        tv_id.setText("订单编号："+model.getYRescueId());
        tv_carnum.setText(model.getUser_sedan_info().getSNumber());
        tv_name.setText(model.getFullName());
        tv_carname.setText(model.getUser_sedan_info().getSName());
//        tv_carpailiang.setText("排量："+model.getUser_sedan_info().);
//        tv_carnianfen.setText("年份："+model.getUser_sedan_info().);
//        tv_carlicheng.setText("里程："+model.getUser_sedan_info().);
        tv_phonenum.setText("联系方式：" + model.getTelephone());
        tv_addr.setText("地址：" + model.getAddress());
        tv_content.setText(model.getCarCondition());
        tv_type.setText("救援类型："+model.getMType());

        ArrayList<String> images = new ArrayList<>();
        for (String s : model.getImgArr()) {
            images.add(URLs.IMGHOST + s);
        }
        CommonAdapter<String> ca = new CommonAdapter<String>
                (this, R.layout.item_img_80_60, images) {
            @Override
            protected void convert(ViewHolder holder, String s, int item) {
                ImageView iv = holder.getView(R.id.iv);
                Glide.with(JiuYuanActivity.this).load(s)
                        .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(iv);//加载图片
            }
        };
        ca.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                PhotoShowDialog photoShowDialog = new PhotoShowDialog(JiuYuanActivity.this, images, i);
                photoShowDialog.show();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        rv.setAdapter(ca);

        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        /*showProgress(true, getString(R.string.app_loading));
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("u_token", localUserInfo.getToken());
        Request(params);*/
        showProgress(true, "正在获取救援状态...");
        Map<String, String> params = new HashMap<>();
        params.put("y_rescue_id", model.getYRescueId());
        params.put("u_token", localUserInfo.getToken());
        RequestType(params);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_message:
                //信息
                String url = URLs.KFHOST + "/#/pages/chetu-kf/chetu-kf?token=" + localUserInfo.getToken() +
                        "&receive_user_hash=" + model.getUser_info().getUserHash() +
                        "&nickName=" + model.getFullName() +
                        "&headerPic=" + URLs.IMGHOST + model.getUser_info().getHeadPortrait();
                Bundle bundle = new Bundle();
                bundle.putString("url", url);
                CommonUtil.gotoActivityWithData(JiuYuanActivity.this, WebContentActivity.class, bundle, false);
                break;
            case R.id.iv_call:
                //电话
                showToast("确认拨打 " + model.getTelephone() + " 吗？", "确认", "取消",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                //创建打电话的意图
                                Intent intent = new Intent();
                                //设置拨打电话的动作
                                intent.setAction(Intent.ACTION_CALL);//直接拨出电话
//                               intent.setAction(Intent.ACTION_DIAL);//只调用拨号界面，不拨出电话
                                //设置拨打电话的号码
                                intent.setData(Uri.parse("tel:" + model.getTelephone()));
                                //开启打电话的意图
                                startActivity(intent);
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                break;
            case R.id.tv_addr:
                //地址
                if (model.getLatitude() != null && !model.getLatitude().equals("")) {
                    if (!latitude.equals("")) {
                        Bundle bundle_m = new Bundle();
                        bundle_m.putDouble("startlat", Double.valueOf(latitude));
                        bundle_m.putDouble("startlng", Double.valueOf(longitude));
                        bundle_m.putDouble("endlat", Double.valueOf(model.getLatitude()));
                        bundle_m.putDouble("endlng", Double.valueOf(model.getLongitude()));
                        CommonUtil.gotoActivityWithData(JiuYuanActivity.this, MapNavigationActivity.class, bundle_m, false);
                    } else {
                        myToast("正在定位当前位置，请稍候再试");
                    }
                } else {
                    showToast("没有救援定位，无法导航");
                }
                break;
            case R.id.tv_confirm:
                //立即救援
                /*showProgress(true, "正在获取救援状态...");
                Map<String, String> params = new HashMap<>();
                params.put("y_rescue_id", model.getYRescueId());
                params.put("u_token", localUserInfo.getToken());
                RequestType(params);*/
                showToast("确认立即救援吗？", "确认", "取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Map<String, String> params = new HashMap<>();
                        params.put("y_rescue_id", model.getYRescueId());
                        params.put("u_token", localUserInfo.getToken());
                        RequestJiuYuan(params);
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                break;

        }
    }

    /**
     * 救援状态
     *
     * @param params
     */
    private void RequestType(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.JiuYuan_Type, params, headerMap, new CallBackUtil<JiuYuanTypeModel>() {
            @Override
            public JiuYuanTypeModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(JiuYuanTypeModel response) {
                hideProgress();
                if (response.getState() < 1) {//可以救援
                    rl_confirm.setVisibility(View.VISIBLE);

                }else {
                    rl_confirm.setVisibility(View.GONE);
//                    showToast("已经救援，不能再救援了");
                }
            }
        });
    }
    /**
     * 立即救援
     *
     * @param params
     */
    private void RequestJiuYuan(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.JiuYuan_Now, params, headerMap, new CallBackUtil<JiuYuanTypeModel>() {
            @Override
            public JiuYuanTypeModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(JiuYuanTypeModel response) {
                hideProgress();
                showToast("救援已提交");
            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle("救援");
        titleView.setBackground(R.color.background);
    }
}
