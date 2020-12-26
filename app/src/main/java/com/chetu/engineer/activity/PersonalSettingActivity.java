package com.chetu.engineer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.Fragment4Model;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.utils.CommonUtil;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/6/2.
 * 个性设置
 */
public class PersonalSettingActivity extends BaseActivity {
    TextView tv_renzheng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalsetting);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Map<String, String> params = new HashMap<>();
        params.put("u_token", localUserInfo.getToken());
        requestCenter(params);
    }
    private void requestCenter(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment4, params, headerMap, new CallBackUtil<Fragment4Model>() {
            @Override
            public Fragment4Model onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(Fragment4Model response) {
                hideProgress();
                if (response.getUser_info().getIsAuth() == 1){
                    tv_renzheng.setText("已认证");
                }else {
                    tv_renzheng.setText("未认证");
                }

            }
        });
    }
    @Override
    protected void initView() {
        tv_renzheng = findViewByID_My(R.id.tv_renzheng);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.linearLayout1:
                //我的信息
                CommonUtil.gotoActivity(this, MyProfileActivity.class);
                break;
            case R.id.linearLayout2:
                //实名认证
                CommonUtil.gotoActivity(this, VerifiedActivity.class);
                break;
            case R.id.linearLayout3:
                //修改手机号
                CommonUtil.gotoActivity(this, ChangePhoneActivity.class);
                break;
            case R.id.linearLayout4:
                //解绑门店
                CommonUtil.gotoActivity(this, UnbundleStoreActivity.class);
                break;
            case R.id.linearLayout5:
                //绑定支付宝
                CommonUtil.gotoActivity(this, BindingAlipayActivity.class);
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle("个性设置");
    }
}
