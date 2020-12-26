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
 * 回复
 */
public class ReplyActivity extends BaseActivity {
    String y_store_eval_id = "";
    EditText editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appeal);
    }

    @Override
    protected void initView() {
        editText1 = findViewByID_My(R.id.editText1);
        editText1.setHint("填写回复内容");
    }

    @Override
    protected void initData() {
        y_store_eval_id = getIntent().getStringExtra("y_store_eval_id");
    }

    @Override
    protected void updateView() {
        titleView.setTitle("回复");
        titleView.showRightTextview("提交", R.color.blue, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText1.getText().toString().trim().equals("")) {
                    Map<String, String> params = new HashMap<>();
                    params.put("u_token", localUserInfo.getToken());
                    params.put("y_store_eval_id", y_store_eval_id);
                    params.put("reply_msg", editText1.getText().toString().trim());
                    RequestHuiFu(params);

                } else {
                    myToast("请填写回复内容");
                }
            }
        });
    }

    /**
     * 回复
     *
     * @param params
     */
    private void RequestHuiFu(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Reply, params, headerMap, new CallBackUtil<String>() {
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
                myToast("回复成功");
                finish();
            }
        });
    }
}
