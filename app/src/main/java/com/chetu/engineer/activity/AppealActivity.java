package com.chetu.engineer.activity;

import android.os.Bundle;
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
 * Created by zyz on 2020/6/4.
 * 申诉
 */
public class AppealActivity extends BaseActivity {
    String parent_id = "", user_hash = "";
    EditText editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appeal);
    }

    @Override
    protected void initView() {
        editText1 = findViewByID_My(R.id.editText1);
    }

    @Override
    protected void initData() {
        parent_id = getIntent().getStringExtra("parent_id");
        user_hash = getIntent().getStringExtra("user_hash");
    }

    @Override
    protected void updateView() {
        titleView.setTitle("申诉");
        titleView.showRightTextview("提交", R.color.blue, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText1.getText().toString().trim().equals("")) {
                    Map<String, String> params = new HashMap<>();
                    params.put("u_token", localUserInfo.getToken());
                    params.put("parent_id", parent_id);
                    params.put("user_hash", user_hash);
                    params.put("v_title", editText1.getText().toString().trim());
                    RequestShenShu(params);

                } else {
                    myToast("请填写申诉内容");
                }
            }
        });
    }

    /**
     * 申述
     *
     * @param params
     */
    private void RequestShenShu(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Appeal, params, headerMap, new CallBackUtil<String>() {
            @Override
            public String onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                myToast("申述成功");
                finish();
            }
        });
    }
}
