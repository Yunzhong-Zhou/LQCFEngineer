package com.chetu.engineer.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.CodeModel;
import com.chetu.engineer.model.Fragment4Model;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/6/5.
 * 解绑门店
 */
public class UnbundleStoreActivity extends BaseActivity {
    private TimeCount time;
    String vcode = "", user_phone = "";

    EditText editText1, editText2;
    TextView tv_name, tv_code, tv_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unbundlestore);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (time != null)
            time.cancel();
    }

    @Override
    protected void initView() {
        editText1 = findViewByID_My(R.id.editText1);
        editText1.setText(localUserInfo.getPhonenumber());
        editText2 = findViewByID_My(R.id.editText2);
        tv_code = findViewByID_My(R.id.tv_code);
        tv_confirm = findViewByID_My(R.id.tv_confirm);
        tv_name = findViewByID_My(R.id.tv_name);
    }

    @Override
    protected void initData() {
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象
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
                tv_name.setText("当前门店："+response.getUser_info().getStore_info().getVName());
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_code:
                //获取验证码
                user_phone = editText1.getText().toString().trim();
                if (TextUtils.isEmpty(user_phone)) {
                    myToast("请输入手机号");
                } else {
                    showProgress(true, "正在获取短信验证码...");
                    tv_code.setClickable(false);
                    HashMap<String, String> params = new HashMap<>();
                    params.put("user_phone", user_phone);
//                    params.put("type", "1");
                    RequestCode(params);//获取验证码
                }
                break;
            case R.id.tv_confirm:
                //提交
                if (match()) {
                    tv_confirm.setClickable(false);
                    showProgress(true, "正在提交数据，请稍候...");
                    params.put("u_token", localUserInfo.getToken());
                    params.put("user_phone", user_phone);
                    params.put("vcode", vcode);
                    RequestChage(params);//修改
                }
                break;
        }

    }

    private boolean match() {
        user_phone = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(user_phone)) {
            myToast("请输入手机号");
            return false;
        }
        vcode = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(vcode)) {
            myToast("请输入验证码");
            return false;
        }
        return true;
    }

    private void RequestCode(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Code, params, headerMap, new CallBackUtil<CodeModel>() {
            @Override
            public CodeModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                tv_code.setClickable(true);
                if (!err.equals("")) {
                    showToast(err);
                }
            }

            @Override
            public void onResponse(CodeModel response) {
                hideProgress();
                tv_code.setClickable(true);
                time.start();//开始计时
                myToast(getString(R.string.app_sendcode_hint));
                editText2.setText(response.getV_code());
            }
        });
    }

    /**
     * 修改信息
     *
     * @param params
     */
    private void RequestChage(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.UnbundleStore, params, headerMap, new CallBackUtil<Object>() {
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
                myToast("解绑门店成功");
                finish();
            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle("解绑门店");
    }

    //获取验证码倒计时
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            tv_code.setText(getString(R.string.app_reacquirecode));
            tv_code.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            tv_code.setClickable(false);
            tv_code.setText(millisUntilFinished / 1000 + getString(R.string.app_codethen));
        }
    }
}
