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
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/6/12.
 * 支付密码
 */
public class PayPasswordActivity extends BaseActivity {
    private TimeCount time;
    String vcode = "", user_phone = "", pay_pwd = "", pay_pwd1 = "";

    EditText editText1, editText2, editText3, editText4;
    TextView tv_code, tv_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypassword);
        mImmersionBar.reset()
                .statusBarColor(R.color.background)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
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
        editText3 = findViewByID_My(R.id.editText3);
        editText4 = findViewByID_My(R.id.editText4);
        tv_code = findViewByID_My(R.id.tv_code);
        tv_confirm = findViewByID_My(R.id.tv_confirm);

    }

    @Override
    protected void initData() {
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象
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
                    params.put("pay_pwd", pay_pwd);
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
        pay_pwd = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(pay_pwd)) {
            myToast("请输入密码");
            return false;
        }
        pay_pwd1 = editText4.getText().toString().trim();
        if (TextUtils.isEmpty(pay_pwd1)) {
            myToast("请再次输入密码");
            return false;
        }
        if (!pay_pwd.equals(pay_pwd1)){
            myToast("两次输入的密码不一致");
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
        OkhttpUtil.okHttpPost(URLs.ChagePayPassWord, params, headerMap, new CallBackUtil<Object>() {
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
                myToast("修改成功");
                finish();
            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle("支付密码");
        titleView.setBackground(R.color.background);
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
