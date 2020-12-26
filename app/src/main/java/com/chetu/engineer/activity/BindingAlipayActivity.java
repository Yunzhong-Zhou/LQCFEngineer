package com.chetu.engineer.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/6/5.
 * 绑定支付宝
 */
public class BindingAlipayActivity extends BaseActivity {
    EditText editText1, editText2;
    TextView tv_confirm;
    String v_us_name = "", v_account = "", v_bank = "", v_cay = "支付宝", user_phone = "", v_code = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bindingalipay);
    }

    @Override
    protected void initView() {
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        tv_confirm = findViewByID_My(R.id.tv_confirm);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (match()) {
                    tv_confirm.setClickable(false);
                    showProgress(true, "正在提交数据，请稍候...");
                    params.put("u_token", localUserInfo.getToken());
                    params.put("v_us_name", v_us_name);
                    params.put("v_account", v_account);
                    params.put("v_bank", v_bank);
                    params.put("v_cay", v_cay);
                    params.put("user_phone", user_phone);
                    params.put("v_code", v_code);
                    RequestChage(params);//修改
                }
            }
        });
    }

    private boolean match() {
        v_us_name = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(v_us_name)) {
            myToast("请输入用户名");
            return false;
        }
        v_account = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(v_account)) {
            myToast("请输入账号");
            return false;
        }
        return true;
    }
    /**
     * 绑定银行卡、支付宝
     * @param params
     */
    private void RequestChage(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.BindingAccount, params, headerMap, new CallBackUtil<Object>() {
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
                myToast("绑定成功");
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {
        titleView.setTitle("绑定支付宝");
    }
}
