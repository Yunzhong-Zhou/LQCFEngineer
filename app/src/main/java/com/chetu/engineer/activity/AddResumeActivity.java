package com.chetu.engineer.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.utils.CommonUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/6/18.
 * 添加履历
 */
public class AddResumeActivity extends BaseActivity {
    EditText editText1, editText2;
    TextView textView1, textView2;
    TimePickerView pvTime1;
    String y_user_resume_id = "", position = "", company = "", entry_time = "", quit_time = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addresume);
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
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);

    }

    @Override
    protected void initData() {
        y_user_resume_id = getIntent().getStringExtra("y_user_resume_id");
        if (!y_user_resume_id.equals("")){
            editText1.setText(getIntent().getStringExtra("position"));
            editText2.setText(getIntent().getStringExtra("company"));
            textView1.setText(getIntent().getStringExtra("entry_time"));
            textView2.setText(getIntent().getStringExtra("quit_time"));
            titleView.showRightTextview("修改", R.color.blue, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (match()) {
                        showProgress(true, "正在提交数据，请稍候...");
                        Map<String, String> params = new HashMap<>();
                        params.put("y_user_resume_id", y_user_resume_id);
                        params.put("u_token", localUserInfo.getToken());
                        params.put("position", position);
                        params.put("company", company);
                        params.put("entry_time", entry_time);
                        params.put("quit_time", quit_time);
                        RequestChage(params);//修改
                    }
                }
            });
        }else {
            titleView.showRightTextview("添加", R.color.blue, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (match()) {
                        showProgress(true, "正在提交数据，请稍候...");
                        Map<String, String> params = new HashMap<>();
                        params.put("u_token", localUserInfo.getToken());
                        params.put("position", position);
                        params.put("company", company);
                        params.put("entry_time", entry_time);
                        params.put("quit_time", quit_time);
                        RequestAdd(params);//添加
                    }
                }
            });
        }

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.textView1:
                //入职时间
                setDate1("请选择入职时间", textView1, textView1.getText().toString().trim());
                break;
            case R.id.textView2:
                //离职时间
                setDate1("请选择离职时间", textView2, textView2.getText().toString().trim());
                break;
        }
    }

    /**
     * 选择日期
     *
     * @param string
     * @param textView
     * @param date
     */
    private void setDate1(String string, TextView textView, String date) {
        //获取当前时间
        Calendar calendar = Calendar.getInstance();
        //年
        int year = calendar.get(Calendar.YEAR);
        //月
        int month = calendar.get(Calendar.MONTH);
        //日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        //分钟
        int minute = calendar.get(Calendar.MINUTE);
        //秒
        int second = calendar.get(Calendar.SECOND);

        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        if (!date.equals("")) {
            String[] strArr = date.split("-");//拆分日期 得到年月日
            selectedDate.set(Integer.valueOf(strArr[0]), Integer.valueOf(strArr[1]) - 1, Integer.valueOf(strArr[2]));
        }

        //正确设置方式 原因：注意事项有说明
//        startDate.set(year, month, day);
        startDate.set(1900, 1, 1);

        //当前时间加3天
//        calendar.add(Calendar.YEAR, 100);
        endDate.set(year, month, day);
        /*endDate.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));*/


        pvTime1 = new TimePickerBuilder(AddResumeActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                textView.setText(CommonUtil.getTime(date));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentTextSize(15)//滚轮文字大小
                .setTitleSize(16)//标题文字大小
                .setTitleText(string)//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
                .setTitleColor(getResources().getColor(R.color.black2))//标题文字颜色
                .setSubmitColor(getResources().getColor(R.color.blue))//确定按钮文字颜色
                .setCancelColor(getResources().getColor(R.color.blue))//取消按钮文字颜色
                .setTitleBgColor(getResources().getColor(R.color.black5))//标题背景颜色 Night mode
                .setBgColor(getResources().getColor(R.color.white))//滚轮背景颜色 Night mode
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(true)//是否显示为对话框样式
                .build();

        Dialog mDialog = pvTime1.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);
            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime1.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.1f);
            }
        }
        pvTime1.show();
    }

    @Override
    protected void updateView() {
        titleView.setTitle("添加履历");
        titleView.setBackground(R.color.background);
    }

    /**
     * 添加
     * @param params
     */
    private void RequestAdd(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.AddResume, params, headerMap, new CallBackUtil<Object>() {
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
    /**
     * 修改
     * @param params
     */
    private void RequestChage(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ChageResume, params, headerMap, new CallBackUtil<Object>() {
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

    private boolean match() {
        position = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(position)) {
            myToast("请输入在职职位");
            return false;
        }
        company = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(company)) {
            myToast("请输入在职公司");
            return false;
        }
        entry_time = textView1.getText().toString().trim();
        if (TextUtils.isEmpty(entry_time)) {
            myToast("请选择入职时间");
            return false;
        }
        quit_time = textView2.getText().toString().trim();
        if (TextUtils.isEmpty(quit_time)) {
            myToast("请选择离职时间");
            return false;
        }

        if (CommonUtil.dataOne1(quit_time) <= CommonUtil.dataOne1(entry_time)) {
            myToast("离职时间不能小于入职时间");
            return false;
        }
        return true;
    }
}
