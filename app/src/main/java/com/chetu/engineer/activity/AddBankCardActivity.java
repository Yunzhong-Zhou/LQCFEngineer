package com.chetu.engineer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.CodeModel;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.lsh.library.BankNumEditText;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/6/21.
 * 添加银行卡
 */
public class AddBankCardActivity extends BaseActivity {
    private TimeCount time;
    String v_code = "", user_phone = "", v_us_name = "", v_account = "", v_bank = "", v_cay = "银行卡";

    BankNumEditText editText4;
    EditText editText1, editText2, editText3, editText5;
    TextView tv_code, tv_confirm;

    ImageView iv_gouxuan;
    boolean isGouXuan = false;

    Handler handler = new Handler();
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbankcard);
        mImmersionBar.reset()
                .statusBarColor(R.color.blue)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
//                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
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
        /*editText4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                if (runnable != null) {
                    handler.removeCallbacks(runnable);

                }
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        //识别银行卡
                        MyLogger.i(">>>>>>>>>"+editText4.getText().toString().trim());
                        if (!editText4.getText().toString().trim().equals("")){

                        }
                    }
                };
                handler.postDelayed(runnable, 2000);
            }
        });*/
        editText5 = findViewByID_My(R.id.editText5);
        tv_code = findViewByID_My(R.id.tv_code);
        tv_confirm = findViewByID_My(R.id.tv_confirm);

        iv_gouxuan = findViewByID_My(R.id.iv_gouxuan);

        editText4.setFullVerify(false)
                .setBankNameListener(new BankNumEditText.BankNameListener() {
                    @Override
                    public void success(String name) {
//                        MyLogger.i(">>>>>>"+name);
                        if (!name.equals("")) {
//                            editText5.setText(name);
                            String[] strArr = name.split("-");
                            editText5.setText(strArr[0]);
                            for (int i = 0; i < strArr.length; i++) {
                                if (i == (strArr.length - 1)) {
                                    v_cay = strArr[i];
                                }
                            }
                        }else {
                            editText5.setText(name);
                        }

                    }

                    @Override
                    public void failure(int failCode, String failmsg) {
//                        myToast(failCode+failmsg);

                    }
                });
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
                    params.put("v_code", v_code);
                    params.put("v_us_name", v_us_name);
                    params.put("v_account", v_account);
                    params.put("v_bank", v_bank);
                    params.put("v_cay", v_cay);
                    RequestChage(params);//修改
                }
                break;
            case R.id.iv_gouxuan:
                //勾选图片
                isGouXuan = !isGouXuan;
                if (isGouXuan) {
                    iv_gouxuan.setImageResource(R.mipmap.ic_xuanzhong);
                } else {
                    iv_gouxuan.setImageResource(R.mipmap.ic_weixuan);
                }
                break;

            case R.id.imageView:
                //拍照-查询银行卡号
                /*Intent scanIntent = new Intent(this, CardIOActivity.class);
                // customize these values to suit your needs.
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, false); // 到期日期
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false); // default: false
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false); // default: false
                scanIntent.putExtra(CardIOActivity.EXTRA_HIDE_CARDIO_LOGO, true);//去除水印
                scanIntent.putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, true);//去除键盘
                scanIntent.putExtra(CardIOActivity.EXTRA_LANGUAGE_OR_LOCALE, "zh-Hans");//设置提示为中文
                startActivityForResult(scanIntent, 10003);*/
                Intent intent1 = new Intent(this, BankCardScannerActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("type", 10001);
                intent1.putExtras(bundle1);
                startActivityForResult(intent1, 10001, bundle1);

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
        v_us_name = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(v_us_name)) {
            myToast("请输入持卡人姓名");
            return false;
        }
        v_account = editText4.getText().toString().trim();
        if (TextUtils.isEmpty(v_account)) {
            myToast("请输入银行卡号");
            return false;
        }
        v_bank = editText5.getText().toString().trim();
        if (TextUtils.isEmpty(v_bank)) {
            myToast("请输入银行卡类型");
            return false;
        }
        if (!isGouXuan) {
            myToast("请阅读并同意《银行卡绑定条例》");
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
                myToast("添加成功");
                finish();
            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle("添加银行卡");
        titleView.setTitleColor(R.color.white);
        titleView.setLeftBtn(R.mipmap.ic_return_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleView.setBackground(R.color.blue);
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
                //车型
                if (data != null) {
                    Bundle bundle1 = data.getExtras();
                    editText4.setText(bundle1.getString("bankCard"));
                }
                break;
        }
        /*if (requestCode == 10003) {
            String resultDisplayStr;
            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

                // 切勿记录原始卡号 避免显示它，但如有必要，请使用getFormattedCardNumber（）
                // 显示尾号 scanResult.getRedactedCardNumber()
                resultDisplayStr = "Card Number: " + scanResult.getFormattedCardNumber() + "\n";
                editText4.setText(scanResult.getFormattedCardNumber());

                //用原始数字做某事，例如：
                // myService.setCardNumber（scanResult.cardNumber）;

                if (scanResult.isExpiryValid()) {
                    resultDisplayStr += "Expiration Date: " + scanResult.expiryMonth + "/" + scanResult.expiryYear + "\n";
                }

                if (scanResult.cvv != null) {
                    // Never log or display a CVV
                    resultDisplayStr += "CVV has " + scanResult.cvv.length() + " digits.\n";
                }

                if (scanResult.postalCode != null) {
                    resultDisplayStr += "Postal Code: " + scanResult.postalCode + "\n";
                }
            } else {
                resultDisplayStr = "Scan was canceled.";
            }
            MyLogger.i("resultDisplayStr", resultDisplayStr);
            // do something with resultDisplayStr, maybe display it in a textView
            // resultTextView.setText(resultDisplayStr);
        }*/
        // else handle other activity results
    }
}
