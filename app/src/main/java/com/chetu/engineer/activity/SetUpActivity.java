package com.chetu.engineer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.utils.CommonUtil;

/**
 * Created by zyz on 2020/5/25.
 * 设置
 */
public class SetUpActivity extends BaseActivity {
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
    }

    @Override
    protected void initView() {
        textView1 = findViewByID_My(R.id.textView1);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.linearLayout1:
                //清除缓存
                break;
            case R.id.linearLayout2:
                //意见反馈
                CommonUtil.gotoActivity(SetUpActivity.this, FeedBackActivity.class, false);
                break;
            case R.id.linearLayout3:
                //关于我们
                
                break;
            case R.id.linearLayout4:
                //版本说明
                CommonUtil.gotoActivity(SetUpActivity.this, VersionActivity.class, false);
                break;
            case R.id.tv_out:
                //退出
                showToast("确认退出登录吗？",
                        getString(R.string.app_confirm),
                        getString(R.string.app_cancel), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                /*showProgress(true, "正在注销登录，请稍候...");
                                requestOut("?token=" + localUserInfo.getToken());*/
                                localUserInfo.setUserHash("");
                                localUserInfo.setUserName("");
                                localUserInfo.setToken("");
                                localUserInfo.setPhoneNumber("");
                                localUserInfo.setNickname("");
                                localUserInfo.setWalletaddr("");
                                localUserInfo.setEmail("");
                                localUserInfo.setUserImage("");
                                CommonUtil.gotoActivityWithFinishOtherAll(SetUpActivity.this, LoginActivity.class, true);
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

    @Override
    protected void updateView() {
        titleView.setTitle("系统设置");
    }
}
