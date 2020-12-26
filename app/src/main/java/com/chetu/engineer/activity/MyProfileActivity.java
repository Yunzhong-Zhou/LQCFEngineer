package com.chetu.engineer.activity;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.Fragment4Model;
import com.chetu.engineer.model.MyResumeModel;
import com.chetu.engineer.model.UpFileModel;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.utils.CommonUtil;
import com.chetu.engineer.utils.MyChooseImages;
import com.chetu.engineer.utils.MyLogger;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.zelory.compressor.Compressor;
import okhttp3.Call;
import okhttp3.Response;

import static com.chetu.engineer.net.URLs.IMGHOST;
import static com.chetu.engineer.utils.MyChooseImages.REQUEST_CODE_CAPTURE_CAMEIA;
import static com.chetu.engineer.utils.MyChooseImages.REQUEST_CODE_PICK_IMAGE;


/**
 * Created by fafukeji01 on 2017/5/8.
 * 我的资料
 */

public class MyProfileActivity extends BaseActivity {
    //选择图片及上传
//    ArrayList<String> listFileNames = new ArrayList<>();
    ArrayList<File> listFiles = new ArrayList<>();
    ImageView imageView1, iv_nan, iv_nv, iv_gongli, iv_nongli;
    TextView textView, textView1, textView2, textView3, textView4;
    EditText editText1, editText2, editText3;

    TimePickerView pvTime1;
    CityConfig cityConfig = null;
    //开户行控件 申明对象
    CityPickerView mPicker = new CityPickerView();

    String head_portrait = "", user_name = "", u_gender = "男", birthday = "", city = "", profession = "", personal = "", lunar_calendar = "公历";

    //履历
    private RecyclerView recyclerView;
    List<MyResumeModel.ListBean> list = new ArrayList<>();
    CommonAdapter<MyResumeModel.ListBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
        mImmersionBar.reset()
                .statusBarColor(R.color.blue)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
//                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
//        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);

        //预先加载仿iOS滚轮实现的全部数据
        mPicker.init(this);
        cityConfig = new CityConfig.Builder()
                .title("请选择所在城市")//标题
                .titleTextSize(16)//标题文字大小
                .titleTextColor("#585858")//标题文字颜  色
                .titleBackgroundColor("#eaeaea")//标题栏背景色
                .confirTextColor("#0567F5")//确认按钮文字颜色
                .confirmText(getString(R.string.app_confirm))//确认按钮文字
                .confirmTextSize(15)//确认按钮文字大小
                .cancelTextColor("#0567F5")//取消按钮文字颜色
                .cancelText(getString(R.string.app_cancel))//取消按钮文字
                .cancelTextSize(15)//取消按钮文字大小
                .setCityWheelType(CityConfig.WheelType.PRO_CITY_DIS)//显示类，只显示省份一级，显示省市两级还是显示省市区三级
                .showBackground(true)//是否显示半透明背景
                .visibleItemsCount(7)//显示item的数量
                .province("北京市")//默认显示的省份
                .city("北京市")//默认显示省份下面的城市
                .district("朝阳区")//默认显示省市下面的区县数据
                .provinceCyclic(true)//省份滚轮是否可以循环滚动
                .cityCyclic(true)//城市滚轮是否可以循环滚动
                .districtCyclic(true)//区县滚轮是否循环滚动
                .setCustomItemLayout(R.layout.item_city)//自定义item的布局
                .setCustomItemTextViewId(R.id.textView1)//自定义item布局里面的textViewid
                .drawShadows(true)//滚轮不显示模糊效果
                .setLineColor("#80CDCDCE")//中间横线的颜色
                .setLineHeigh(1)//中间横线的高度
                .setShowGAT(true)//是否显示港澳台数据，默认不显示
                .build();

        //设置自定义的属性配置
        mPicker.setConfig(cityConfig);

        //监听选择点击事件及返回结果
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                //省份province//城市city//地区district
                textView3.setText(province.getName().toString() +
                        city.getName().toString() +
                        district.getName().toString());
                /*bank_address_temp = province.getName().toString() + "#" +
                        city.getName().toString() + "#" +
                        district.getName().toString();*/
            }

            @Override
            public void onCancel() {
//                ToastUtils.showLongToast(this, "已取消");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onResume() {
        super.onResume();
        requestServer();
    }

    @Override
    protected void initView() {
        imageView1 = findViewByID_My(R.id.imageView1);
        textView = findViewByID_My(R.id.textView);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);

        iv_nan = findViewByID_My(R.id.iv_nan);
        iv_nv = findViewByID_My(R.id.iv_nv);

        iv_gongli = findViewByID_My(R.id.iv_gongli);
        iv_nongli = findViewByID_My(R.id.iv_nongli);

        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        editText1.setText(localUserInfo.getNickname());
        if (!localUserInfo.getUserImage().equals(""))
            Glide.with(this)
                    .load(IMGHOST + localUserInfo.getUserImage())
                    .centerCrop()
//                    .placeholder(R.mipmap.headimg)//加载站位图
//                    .error(R.mipmap.headimg)//加载失败
                    .into(imageView1);//加载图片

    }

    @Override
    protected void initData() {
        //获取个人信息
        showProgress(true, getString(R.string.app_loading));
        params.put("u_token", localUserInfo.getToken());
        requestInfo(params);
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        //获取履历
        Map<String, String> params = new HashMap<>();
        params.put("u_token", localUserInfo.getToken());
        requestResume(params);
    }

    /**
     * 获取个人资料
     */
    private void requestInfo(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment4, params, headerMap, new CallBackUtil<Fragment4Model>() {
            @Override
            public Fragment4Model onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(Fragment4Model response) {
                hideProgress();
                //头像
                head_portrait = response.getUser_info().getHeadPortrait();
                Glide.with(MyProfileActivity.this).load(URLs.IMGHOST + response.getUser_info().getHeadPortrait())
                        .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                        .into(imageView1);//加载图片
                //姓名
                editText1.setText(response.getUser_info().getUserName());
                //用户id
                textView1.setText(response.getUser_info().getUserId());
                //性别
                if (response.getUser_info().getSetup_info().getU_gender() != null) {
                    if (response.getUser_info().getSetup_info().getU_gender().equals("男")) {
                        u_gender = "男";
                        iv_nan.setImageResource(R.mipmap.ic_xuanzhong);
                        iv_nv.setImageResource(R.mipmap.ic_weixuan);
                    } else {
                        u_gender = "女";
                        iv_nan.setImageResource(R.mipmap.ic_weixuan);
                        iv_nv.setImageResource(R.mipmap.ic_xuanzhong);
                    }
                    //生日
                    birthday = response.getUser_info().getSetup_info().getBirthday();
                    textView2.setText(response.getUser_info().getSetup_info().getBirthday());

                    if (response.getUser_info().getSetup_info().getLunarCalendar() != null && response.getUser_info().getSetup_info().getLunarCalendar().equals("公历")) {
                        lunar_calendar = "公历";
                        iv_gongli.setImageResource(R.mipmap.ic_xuanzhong);
                        iv_nongli.setImageResource(R.mipmap.ic_weixuan);
                    } else {
                        lunar_calendar = "农历";
                        iv_gongli.setImageResource(R.mipmap.ic_weixuan);
                        iv_nongli.setImageResource(R.mipmap.ic_xuanzhong);
                    }
                    //介绍
                    editText3.setText(response.getUser_info().getSetup_info().getPersonal());
                }

            }
        });
    }

    /**
     * 获取履历
     */
    private void requestResume(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.MyResume, params, headerMap, new CallBackUtil<MyResumeModel>() {
            @Override
            public MyResumeModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(MyResumeModel response) {
                hideProgress();
                list = response.getList();
                if (list.size() > 0) {
                    showContentPage();
                    mAdapter = new CommonAdapter<MyResumeModel.ListBean>
                            (MyProfileActivity.this, R.layout.item_myresume, list) {
                        @Override
                        protected void convert(ViewHolder holder, MyResumeModel.ListBean model, int position) {
                            holder.setText(R.id.textView1, model.getPosition());
                            holder.setText(R.id.textView2, model.getYear() + "年");
                            holder.setText(R.id.textView3, model.getCompany());
                            holder.setText(R.id.textView4, model.getEntryTime() + " 至 " + model.getQuitTime());
                            //删除
                            holder.getView(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    showToast("确认删除该履历吗？", "确认", "取消", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                            showProgress(true, "正在删除...");
                                            HashMap<String, String> params2 = new HashMap<>();
                                            params2.put("y_user_resume_id", model.getYUserResumeId());
                                            params2.put("u_token", localUserInfo.getToken());
                                            RequestDelete(params2);
                                        }
                                    }, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                        }
                                    });
                                }
                            });

                            holder.getView(R.id.linearLayout).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("y_user_resume_id", model.getYUserResumeId());
                                    bundle.putString("position", model.getPosition());
                                    bundle.putString("company", model.getCompany());
                                    bundle.putString("quit_time", model.getQuitTime());
                                    bundle.putString("entry_time", model.getEntryTime());
                                    CommonUtil.gotoActivityWithData(MyProfileActivity.this, AddResumeActivity.class, bundle, false);
                                }
                            });
                        }
                    };
                    /*mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            Bundle bundle = new Bundle();
                            bundle.putString("y_user_resume_id", list.get(i).getYUserResumeId());
                            bundle.putString("position", list.get(i).getPosition());
                            bundle.putString("company", list.get(i).getCompany());
                            bundle.putString("quit_time", list.get(i).getQuitTime());
                            bundle.putString("entry_time", list.get(i).getEntryTime());
                            CommonUtil.gotoActivityWithData(MyProfileActivity.this, AddResumeActivity.class, bundle, false);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });*/
                    recyclerView.setAdapter(mAdapter);
                } else {
                    showEmptyPage();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearLayout1:
                //头像
                MyChooseImages.showPhotoDialog(this);
                break;
            case R.id.ll_nan:
                //男
                iv_nan.setImageResource(R.mipmap.ic_xuanzhong);
                iv_nv.setImageResource(R.mipmap.ic_weixuan);
                break;
            case R.id.ll_nv:
                //女
                iv_nan.setImageResource(R.mipmap.ic_weixuan);
                iv_nv.setImageResource(R.mipmap.ic_xuanzhong);
                break;
            case R.id.ll_gongli:
                //公历
                lunar_calendar = "公历";
                iv_gongli.setImageResource(R.mipmap.ic_xuanzhong);
                iv_nongli.setImageResource(R.mipmap.ic_weixuan);
                break;
            case R.id.ll_nongli:
                //农历
                lunar_calendar = "农历";
                iv_gongli.setImageResource(R.mipmap.ic_weixuan);
                iv_nongli.setImageResource(R.mipmap.ic_xuanzhong);

                break;
            case R.id.textView2:
                //年龄
                //年龄
                if (lunar_calendar.equals("公历")) {
                    setDate("请选择出生日期(公历)", textView2, textView2.getText().toString().trim(), false);
                } else {
                    setDate("请选择出生日期(农历)", textView2, textView2.getText().toString().trim(), true);
                }
                break;
            case R.id.textView3:
                //城市
                mPicker.showCityPicker();
                break;
            case R.id.textView4:
                //添加履历
                Bundle bundle = new Bundle();
                bundle.putString("y_user_resume_id", "");
                /*bundle.putString("position", list.get(i).getPosition());
                bundle.putString("company", list.get(i).getCompany());
                bundle.putString("quit_time", list.get(i).getQuitTime());
                bundle.putString("entry_time", list.get(i).getEntryTime());*/
                CommonUtil.gotoActivityWithData(MyProfileActivity.this, AddResumeActivity.class, bundle, false);
                break;
        }
    }

    /**
     * 上传文件 map 方式 暂时不用，用下面list方式
     *
     * @param fileMap
     * @param params
     */
    private void RequestUpFile(Map<String, File> fileMap, Map<String, String> params) {
        OkhttpUtil.okHttpUploadMapFile(URLs.UpFile, fileMap, "image", params, headerMap, new CallBackUtil() {
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
                myToast("头像修改成功");
            }
        });
    }

    /**
     * 上传文件 list 方式
     *
     * @param params
     * @param fileList
     * @param fileKey
     */
    private void RequestUpFile(Map<String, String> params, List<File> fileList, String fileKey) {
        OkhttpUtil.okHttpUploadListFile(URLs.UpFile, params, fileList, fileKey, "image", headerMap, new CallBackUtil<UpFileModel>() {
            @Override
            public UpFileModel onParseResponse(Call call, Response response) {
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
            public void onResponse(UpFileModel response) {
//                myToast("头像修改成功");
                for (String s : response.getList()) {
                    head_portrait = s;
                }
            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle("我的信息");
        titleView.setTitleColor(R.color.white);
        titleView.setBackground(R.color.blue);
        titleView.setLeftBtn(R.mipmap.ic_return_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleView.showRightTextview("保存", R.color.white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (match()) {
                    showProgress(true, "正在提交数据，请稍候...");
                    params.put("u_token", localUserInfo.getToken());
                    params.put("user_name", user_name);
                    params.put("u_gender", u_gender);
                    params.put("birthday", birthday);
                    params.put("lunar_calendar", lunar_calendar);
                    params.put("head_portrait", head_portrait);
                    params.put("city", city);
                    params.put("profession", profession);
                    params.put("personal", personal);
                    RequestChage(params);//修改
                }
            }
        });
    }

    private boolean match() {
        user_name = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(user_name)) {
            myToast("请输入昵称");
            return false;
        }
        birthday = textView2.getText().toString().trim();
        if (TextUtils.isEmpty(birthday)) {
            myToast("请选择出生日期");
            return false;
        }
        /*city = textView3.getText().toString().trim();
        if (TextUtils.isEmpty(city)) {
            myToast("请选择所在城市");
            return false;
        }
        profession = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(profession)) {
            myToast("请输入您的职业");
            return false;
        }*/
        personal = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(personal)) {
            myToast("请填写自我介绍");
            return false;
        }
        return true;
    }

    /**
     * 修改信息
     *
     * @param params
     */
    private void RequestChage(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ChageProfile, params, headerMap, new CallBackUtil<Object>() {
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

    /**
     * 删除履历
     *
     * @param params
     */
    private void RequestDelete(HashMap<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.DeleteResume, params, headerMap, new CallBackUtil<Object>() {
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
                myToast("删除成功");
                requestServer();
            }
        });
    }

    /**
     * 生日
     *
     * @param string
     * @param textView
     * @param date
     */
    private void setDate(String string, TextView textView, String date, boolean isNongLi) {
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


        pvTime1 = new TimePickerBuilder(MyProfileActivity.this, new OnTimeSelectListener() {
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
                .setLunarCalendar(isNongLi)//农历开关
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

    /**
     * *****************************************选择图片********************************************
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri uri = null;
            String imagePath = null;
            switch (requestCode) {
                case REQUEST_CODE_CAPTURE_CAMEIA:
                    //相机
                    uri = Uri.parse("");
                    uri = Uri.fromFile(new File(MyChooseImages.imagepath));
                    imagePath = uri.getPath();
                    break;
                case REQUEST_CODE_PICK_IMAGE:
                    //相册
                    uri = data.getData();
                    //处理得到的url
                    ContentResolver cr = this.getContentResolver();
                    Cursor cursor = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        cursor = cr.query(uri, null, null, null, null, null);
                        if (cursor != null) {
                            cursor.moveToFirst();
                            try {
                                imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                            } catch (Exception e) {
                                e.printStackTrace();
                                myToast(getString(R.string.app_error));
                            } finally {
                                if (cursor != null)
                                    cursor.close();
                            }
                        }

                    } else {
                        imagePath = uri.getPath();
                    }
                    break;
            }
            if (uri != null) {
                MyLogger.i(">>>>>>>>>>获取到的图片路径1：" + imagePath);
                //图片过大解决方法
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);

                imageView1.setImageBitmap(bitmap);
                imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);

//                listFileNames = new ArrayList<>();
//                listFileNames.add("head");

                Uri uri1 = Uri.parse("");
                /*uri1 = Uri.fromFile(new File(imagePath));
                File file1 = new File(FileUtil.getPath(this, uri1));*/
                File file1 = new File(imagePath);
                listFiles = new ArrayList<>();
                File newFile = null;
                try {
                    newFile = new Compressor(this).compressToFile(file1);
                    listFiles.add(newFile);
//                    MyLogger.i(">>>>>选择图片结果>>>>>>>>>" + listFileNames.toString() + ">>>>>>" + listFiles.toString());

                    Map<String, File> fileMap = new HashMap<>();
//                    fileMap.put("picture", newFile);
                    Map<String, String> params = new HashMap<>();
                    params.put("sn", "773EDB6D2715FACF9C93354CAC5B1A3372872DC4D5AC085867C7490E9984D33E");
//                    RequestUpFile(fileMap, params);
                    RequestUpFile(params, listFiles, "picture");

                } catch (IOException e) {
                    e.printStackTrace();
                    myToast(getString(R.string.app_imgerr));
                }
            }
        }

    }


}
