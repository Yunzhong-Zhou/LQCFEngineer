package com.chetu.engineer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.AddCarModelBean;
import com.chetu.engineer.model.CodeModel;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.cy.cyflowlayoutlibrary.FlowLayout;
import com.cy.cyflowlayoutlibrary.FlowLayoutAdapter;
import com.cy.dialog.BaseDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/6/1.
 */
public class AddCarActivity extends BaseActivity {
    String y_sedan_brand_id = "", user_phone = "", v_code = "", s_number = "";
    private TimeCount time;

    int s_cy = 2;
    TextView tv_pingpai, tv_chepai, tv_yanzhengma, tv_shangyexian, tv_jiaoqiangxian, tv_confirm;
    EditText et_carnum, et_phone, et_code;
    LinearLayout ll_geren, ll_gongsi;
    ImageView iv_geren, iv_gongsi, iv_moren;

    FlowLayoutAdapter<String> flowLayoutAdapter;
    List<String> stringList = new ArrayList<>();
    int i = 19;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcar);
    }

    @Override
    protected void initView() {
        tv_pingpai = findViewByID_My(R.id.tv_pingpai);
        tv_chepai = findViewByID_My(R.id.tv_chepai);
        tv_yanzhengma = findViewByID_My(R.id.tv_yanzhengma);
        tv_shangyexian = findViewByID_My(R.id.tv_shangyexian);
        tv_jiaoqiangxian = findViewByID_My(R.id.tv_jiaoqiangxian);
        tv_confirm = findViewByID_My(R.id.tv_confirm);
        et_carnum = findViewByID_My(R.id.et_carnum);
        et_phone = findViewByID_My(R.id.et_phone);
        et_code = findViewByID_My(R.id.et_code);
        ll_geren = findViewByID_My(R.id.ll_geren);
        ll_gongsi = findViewByID_My(R.id.ll_gongsi);
        iv_geren = findViewByID_My(R.id.iv_geren);
        iv_gongsi = findViewByID_My(R.id.iv_gongsi);
        iv_moren = findViewByID_My(R.id.iv_moren);

    }

    @Override
    protected void initData() {
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象
        stringList.add("川");
        stringList.add("京");
        stringList.add("津");
        stringList.add("冀");
        stringList.add("晋");
        stringList.add("蒙");
        stringList.add("辽");
        stringList.add("吉");
        stringList.add("黑");
        stringList.add("沪");
        stringList.add("苏");
        stringList.add("浙");
        stringList.add("皖");
        stringList.add("闽");
        stringList.add("赣");
        stringList.add("鲁");
        stringList.add("豫");
        stringList.add("鄂");
        stringList.add("湘");
        stringList.add("粤");
        stringList.add("桂");
        stringList.add("琼");
        stringList.add("渝");
        stringList.add("贵");
        stringList.add("云");
        stringList.add("藏");
        stringList.add("陕");
        stringList.add("甘");
        stringList.add("青");
        stringList.add("宁");
        stringList.add("新");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_pingpai:
                //品牌型号
                Intent intent1 = new Intent(AddCarActivity.this, AddCarModelActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("type", 10001);
                intent1.putExtras(bundle1);
                startActivityForResult(intent1, 10001, bundle1);

                break;
            case R.id.tv_chepai:
                //绑定车牌
                dialog.contentView(R.layout.dialog_chepai)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))
                        .animType(BaseDialog.AnimInType.BOTTOM)
                        .canceledOnTouchOutside(true)
                        .dimAmount(0.8f)
                        .show();
                //标签
                flowLayoutAdapter = new FlowLayoutAdapter<String>(stringList) {
                    @Override
                    public void bindDataToView(FlowLayoutAdapter.ViewHolder holder, int position, String bean) {
//                                holder.setText(R.id.tv,bean);
                        TextView tv = holder.getView(R.id.tv);
                        tv.setText(bean);

                        if (i == position)
                            tv.setTextColor(getResources().getColor(R.color.blue));
                        else
                            tv.setTextColor(getResources().getColor(R.color.black));

                    }

                    @Override
                    public void onItemClick(int position, String bean) {
//                        showToast("点击" + position);
                        i = position;
                        tv_chepai.setText(bean);

                        flowLayoutAdapter.notifyDataSetChanged();

                        dialog.dismiss();
                    }

                    @Override
                    public int getItemLayoutID(int position, String bean) {
                        return R.layout.item_flowlayout_chepai;
                    }
                };
                ((FlowLayout) dialog.findViewById(R.id.flowLayout)).setAdapter(flowLayoutAdapter);
                break;
            case R.id.ll_geren:
                //个人
                s_cy = 1;
                iv_geren.setImageResource(R.mipmap.ic_xuanzhong_yuan);
                iv_gongsi.setImageResource(R.mipmap.ic_weixuan);
                break;
            case R.id.ll_gongsi:
                //公司
                s_cy = 2;
                iv_geren.setImageResource(R.mipmap.ic_weixuan);
                iv_gongsi.setImageResource(R.mipmap.ic_xuanzhong_yuan);
                break;
            case R.id.iv_moren:
                //是否默认

                break;
            case R.id.tv_yanzhengma:
                //验证码
                user_phone = et_phone.getText().toString().trim();
                if (TextUtils.isEmpty(user_phone)) {
                    myToast("请输入手机号");
                } else {
                    showProgress(true, "正在获取短信验证码...");
                    tv_yanzhengma.setClickable(false);
                    HashMap<String, String> params = new HashMap<>();
                    params.put("user_phone", user_phone);
//                    params.put("type", "1");
                    RequestCode(params);//获取验证码
                }
                break;
            case R.id.tv_shangyexian:
                //商业险

                break;
            case R.id.tv_jiaoqiangxian:
                //交强险

                break;
            case R.id.tv_confirm:
                //提交
                if (match()) {
                    showProgress(true, getString(R.string.app_loading1));
                    Map<String, String> params = new HashMap<>();
                    params.put("y_sedan_brand_id", y_sedan_brand_id);
                    params.put("s_number", s_number);//车牌
                    params.put("s_cy", s_cy + "");//1为个人  2为公司
                    params.put("user_phone", user_phone);
                    params.put("v_code", v_code);

                    params.put("u_token", localUserInfo.getToken());
                    RequestUpData(params);
                }
                break;
        }
    }

    private boolean match() {
        user_phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(user_phone)) {
            myToast("请输入手机号");
            return false;
        }
        v_code = et_code.getText().toString().trim();
        if (TextUtils.isEmpty(v_code)) {
            myToast("请输入验证码");
            return false;
        }
        s_number = et_carnum.getText().toString().trim();
        if (TextUtils.isEmpty(s_number)) {
            myToast("请输入车牌号");
            return false;
        }
        s_number = tv_chepai.getText().toString() + et_carnum.getText().toString().trim();

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
                tv_yanzhengma.setClickable(true);
                if (!err.equals("")) {
                    showToast(err);
                }
            }

            @Override
            public void onResponse(CodeModel response) {
                hideProgress();
                tv_yanzhengma.setClickable(true);
                time.start();//开始计时
                myToast(getString(R.string.app_sendcode_hint));
                et_code.setText(response.getV_code());
            }
        });
    }

    /**
     * 添加车辆
     *
     * @param params
     */
    private void RequestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.AddCar, params, headerMap, new CallBackUtil<AddCarModelBean>() {
            @Override
            public AddCarModelBean onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(AddCarModelBean response) {
                hideProgress();
                myToast("添加成功");
                finish();
            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle("添加车辆");
    }

    //获取验证码倒计时
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            tv_yanzhengma.setText(getString(R.string.app_reacquirecode));
            tv_yanzhengma.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            tv_yanzhengma.setClickable(false);
            tv_yanzhengma.setText(millisUntilFinished / 1000 + getString(R.string.app_codethen));
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
                    y_sedan_brand_id = bundle1.getString("y_sedan_brand_id");
                    String pingpai = bundle1.getString("pingpai");
                    String xinghao = bundle1.getString("xinghao");
                    tv_pingpai.setText(pingpai + "\n" + xinghao);
                }
                break;
        }

    }
}
