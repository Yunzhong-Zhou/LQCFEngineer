package com.chetu.engineer.activity;

import android.content.Intent;
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
 * 提现
 */
public class TakeCashActivity extends BaseActivity {
    String y_with_account_id = "",v_code = "", user_phone = "",u_money="";
    TextView tv_money, tv_zhanghu;


    private TimeCount time;
    EditText editText1, editText2, editText3;
    TextView tv_code, tv_confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takecash);
        mImmersionBar.reset()
                .statusBarColor(R.color.blue)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
//                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @Override
    protected void initView() {
        tv_money = findViewByID_My(R.id.tv_money);
        tv_money.setText(getIntent().getStringExtra("money"));
        tv_zhanghu = findViewByID_My(R.id.tv_zhanghu);

        editText1 = findViewByID_My(R.id.editText1);
        editText1.setText(localUserInfo.getPhonenumber());
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);

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
            case R.id.tv_zhanghu:
                //选择账户
                Intent intent1 = new Intent(TakeCashActivity.this, SelectAccountActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("type", 10001);
                intent1.putExtras(bundle1);
                startActivityForResult(intent1, 10001, bundle1);
                break;
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
                    params.put("v_code", v_code);
                    params.put("u_money", u_money);
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
        v_code = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(v_code)) {
            myToast("请输入验证码");
            return false;
        }
        u_money = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(u_money)) {
            myToast("请输入提现金额");
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
                showToast("提现成功", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        finish();
                    }
                });
            }
        });
    }
    @Override
    protected void updateView() {
        titleView.setTitle("申请提现");
        titleView.setTitleColor(R.color.white);
        titleView.setBackground(R.color.blue);
        titleView.setLeftBtn(R.mipmap.ic_return_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10001:
                //选择车辆
                if (data != null) {
                    Bundle bundle1 = data.getExtras();
                    y_with_account_id = bundle1.getString("bankid");
                    tv_zhanghu.setText(bundle1.getString("bankname")
                            + "-" + bundle1.getString("banktype")
                            + "   ******" + bundle1.getString("banknum"));
                    /*
                    tv_carname.setText(bundle1.getString("carname") );
                    tv_carnum.setText(bundle1.getString("carnum"));
                    tv_cardetail.setText(bundle1.getString("cardetail"));
                    Glide.with(CarServiceActivity.this).load(URLs.IMGHOST + bundle1.getString("carlogo"))
                            .centerCrop()
                            .into(iv_carlogo);//加载图片*/
                }
                break;
        }

    }
}
