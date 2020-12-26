package com.chetu.engineer.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/6/24.
 * 发布招聘
 */
public class AddZhaoPingActivity extends BaseActivity {
    EditText editText1, editText2, editText3, editText4, editText5, editText6;
    String position = "", namestore = "", telephone = "", salary = "", address = "", handson = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addzhaoping);
        mImmersionBar.reset()
                .statusBarColor(R.color.background)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @Override
    protected void initView() {
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);
        editText4 = findViewByID_My(R.id.editText4);
        editText5 = findViewByID_My(R.id.editText5);
        editText6 = findViewByID_My(R.id.editText6);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_confirm:
                //发布
                if (match()) {
                    showProgress(true, getString(R.string.app_loading1));
                    Map<String, String> params = new HashMap<>();
                    params.put("u_token", localUserInfo.getToken());
                    params.put("position", position);
                    params.put("namestore", namestore);
                    params.put("telephone", telephone);
                    params.put("salary", salary);
                    params.put("address", address);
                    params.put("handson", handson);
                    RequestUpData(params);
                }
                break;
        }
    }

    private void RequestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.AddZhaoPin, params, headerMap, new CallBackUtil() {
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
                showToast("招聘发布成功", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        finish();
                    }
                });

            }
        });
    }

    private boolean match() {
        position = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(position)) {
            myToast("请输入招聘职位");
            return false;
        }
        namestore = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(namestore)) {
            myToast("请输入门店名称");
            return false;
        }
        telephone = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(telephone)) {
            myToast("请输入联系电话");
            return false;
        }
        salary = editText4.getText().toString().trim();
        if (TextUtils.isEmpty(salary)) {
            myToast("请输入薪资");
            return false;
        }
        address = editText5.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            myToast("请输入上班地址");
            return false;
        }
        handson = editText6.getText().toString().trim();
        if (TextUtils.isEmpty(handson)) {
            myToast("请输入职位要求");
            return false;
        }
        return true;
    }

    @Override
    protected void updateView() {
        titleView.setTitle("发布招聘");
        titleView.setBackground(R.color.background);
    }
}
