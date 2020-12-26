package com.chetu.engineer.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.utils.CommonUtil;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/5/29.
 * 版本说明
 */
public class VersionActivity extends BaseActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);
        mImmersionBar.reset()
                .statusBarColor(R.color.background)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
//        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
    }

    @Override
    protected void initView() {
        textView = findViewByID_My(R.id.textView);
        textView.setText("版本不息 优化不止\nV" + CommonUtil.getVersionName(VersionActivity.this));

    }

    @Override
    protected void initData() {
        showProgress(true, getString(R.string.app_loading));
        Request(params);//添加
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Version, params, headerMap, new CallBackUtil<Object>() {
            @Override
            public Object onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(Object response) {
                hideProgress();

            }
        });
    }
    @Override
    protected void updateView() {
        titleView.setTitle("版本说明");
        titleView.setBackground(R.color.background);
    }
}
