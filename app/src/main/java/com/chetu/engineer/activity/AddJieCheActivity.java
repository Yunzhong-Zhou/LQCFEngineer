package com.chetu.engineer.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import com.chetu.engineer.model.Fragment2Model_DaiJieChe;
import com.chetu.engineer.model.UpFileModel;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.utils.CommonUtil;
import com.chetu.engineer.utils.MyLogger;
import com.chetu.engineer.view.pictureselector.FullyGridLayoutManager;
import com.chetu.engineer.view.pictureselector.GlideCacheEngine;
import com.chetu.engineer.view.pictureselector.GlideEngine;
import com.chetu.engineer.view.pictureselector.GridImageAdapter;
import com.cy.dialog.BaseDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.tools.ScreenUtils;
import com.luck.picture.lib.tools.SdkVersionUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/6/29.
 * 接车
 */
public class AddJieCheActivity extends BaseActivity {
    Fragment2Model_DaiJieChe.ListBean model;
    String y_order_id = "0", license_number = "", owner_name = "", owner_phone = "",
            y_sedan_brand_id = "", frame_number = "", vehicle_mileage = "", comp_insurance_time = "",
            com_insurance_time = "", annual_review_time = "", v_remarks = "";
    EditText editText1, editText2, editText3, editText5, editText6, editText7;
    TextView textView1, textView2, textView3, textView4, tv_confirm, tv_chepai;
    TimePickerView pvTime1;
    /**
     * 选择图片
     */
    RecyclerView mRecyclerView;
    GridImageAdapter mAdapter;
    int maxSelectNum = 6;//选择最多图片数量
    int spanCount = 4;//一行显示张数
    ArrayList<File> listFiles = new ArrayList<>();

    //    FlowLayoutAdapter<String> flowLayoutAdapter;
    List<String> provinceList = new ArrayList<>();
    List<String> abcList = new ArrayList<>();
    int i_province = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addjieche);

        /**
         * 选择图片
         */
        mRecyclerView = findViewByID_My(R.id.recycler);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this,
                spanCount, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount,
                ScreenUtils.dip2px(this, 5), false));//中间值是设置间距
        mAdapter = new GridImageAdapter(AddJieCheActivity.this, onAddPicClickListener);
        if (savedInstanceState != null && savedInstanceState.getParcelableArrayList("selectorList") != null) {
            mAdapter.setList(savedInstanceState.getParcelableArrayList("selectorList"));
        }
        mAdapter.setSelectMax(maxSelectNum);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initView() {
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);
        textView4 = findViewByID_My(R.id.textView4);
        editText5 = findViewByID_My(R.id.editText5);
        editText6 = findViewByID_My(R.id.editText6);
        editText7 = findViewByID_My(R.id.editText7);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        tv_confirm = findViewByID_My(R.id.tv_confirm);
        tv_chepai = findViewByID_My(R.id.tv_chepai);

    }

    @Override
    protected void initData() {
        String[] province = getResources().getStringArray(R.array.province);
        for (String s : province) {
            provinceList.add(s);
        }
        String[] nums = getResources().getStringArray(R.array.nums);
        for (String s : nums) {
            abcList.add(s);
        }

        if (getIntent().getStringExtra("license_number") == null && license_number.equals("")) {
            model = (Fragment2Model_DaiJieChe.ListBean) getIntent().getSerializableExtra("JieChe");
            y_order_id = model.getYOrderId();

            editText2.setText(model.getUser_info().getUserName());
            editText3.setText(model.getUser_info().getUserPhone());
            if (model.getUser_sedan_info() != null) {
                y_sedan_brand_id = model.getUser_sedan_info().getBrandInfo().getYSedanBrandId();

                //车牌
//            editText1.setText(model.getUser_sedan_info().getSNumber());
                license_number = model.getUser_sedan_info().getSNumber();
                String star = license_number.substring(0, 1);
                for (int j = 0; j < provinceList.size(); j++) {
                    if (star.equals(provinceList.get(j))) {
                        MyLogger.i(">>>>>" + star);
                        i_province = j;
                        tv_chepai.setText(license_number.substring(0, 2));
                    }
                }
                editText1.setText(license_number.substring(2, license_number.length()));

                textView4.setText(model.getUser_sedan_info().getBrandInfo().getBrandName() + "\n" +
                        model.getUser_sedan_info().getBrandInfo().getGroupName() +
                        model.getUser_sedan_info().getBrandInfo().getSeriesName() +
                        model.getUser_sedan_info().getBrandInfo().getSName());
            }


            editText7.setText(model.getcMsg());//备注
        } else {
            license_number = getIntent().getStringExtra("license_number");

            if (!license_number.equals("")) {
                String star = license_number.substring(0, 1);
                for (int j = 0; j < provinceList.size(); j++) {
                    if (star.equals(provinceList.get(j))) {
                        MyLogger.i(">>>>>" + star);
                        i_province = j;
                        tv_chepai.setText(star);
                    }
                }
                editText1.setText(license_number.substring(1, license_number.length()));
            }
        }


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_scan:
                //扫描车牌
                /*Intent intent = new Intent(AddJieCheActivity.this, ChePaiScannerActivity.class);
                startActivity(intent);
                finish();*/
                /*Bundle bundle = new Bundle();
                bundle.putBoolean("isJieCheActivity", true);
                CommonUtil.gotoActivityWithData(AddJieCheActivity.this, ChePaiScannerActivity.class, bundle);*/
                Intent intent2 = new Intent(AddJieCheActivity.this, ChePaiScannerActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("type", 10002);
                intent2.putExtras(bundle2);
                startActivityForResult(intent2, 10002, bundle2);
                break;

            case R.id.tv_chepai:
                //绑定车牌
                dialog.contentView(R.layout.dialog_chepai)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))
                        .animType(BaseDialog.AnimInType.BOTTOM)
                        .canceledOnTouchOutside(true)
                        .gravity(Gravity.BOTTOM)
                        .dimAmount(0.5f)
                        .show();
                RecyclerView rv_carnum = dialog.findViewById(R.id.rv_carnum);
                rv_carnum.setLayoutManager(new GridLayoutManager(this, 10));
                CommonAdapter<String> adapter = new CommonAdapter<String>
                        (AddJieCheActivity.this, R.layout.item_flowlayout_chepai, provinceList) {
                    @Override
                    protected void convert(ViewHolder holder, String model, int position) {
                        TextView tv = holder.getView(R.id.tv);
                        tv.setText(model);

                        if (i_province == position)
                            tv.setTextColor(getResources().getColor(R.color.blue));
                        else
                            tv.setTextColor(getResources().getColor(R.color.black));

                    }
                };
                adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                        i_province = position;

                        tv_chepai.setText(provinceList.get(position));
//                        flowLayoutAdapter.notifyDataSetChanged();
//                        dialog.dismiss();
                        CommonAdapter<String> adapter_abc = new CommonAdapter<String>
                                (AddJieCheActivity.this, R.layout.item_flowlayout_chepai, abcList) {
                            @Override
                            protected void convert(ViewHolder holder, String model, int position) {
                                TextView tv = holder.getView(R.id.tv);
                                tv.setText(model);
                            }
                        };
                        adapter_abc.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                if (i == abcList.size() - 1) {
                                    rv_carnum.setAdapter(adapter);
                                } else {
                                    tv_chepai.setText(provinceList.get(position) + abcList.get(i));
                                    dialog.dismiss();
                                }

                            }

                            @Override
                            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                return false;
                            }
                        });
                        rv_carnum.setAdapter(adapter_abc);

                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                rv_carnum.setAdapter(adapter);

                break;
            case R.id.editText1:
                //绑定车牌
                dialog.contentView(R.layout.dialog_chepai)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))
                        .animType(BaseDialog.AnimInType.BOTTOM)
                        .canceledOnTouchOutside(true)
                        .gravity(Gravity.BOTTOM)
                        .dimAmount(0.5f)
                        .show();
                RecyclerView rv_carnum1 = dialog.findViewById(R.id.rv_carnum);
                rv_carnum1.setLayoutManager(new GridLayoutManager(this, 10));
                CommonAdapter<String> adapter_abc = new CommonAdapter<String>
                        (AddJieCheActivity.this, R.layout.item_flowlayout_chepai, abcList) {
                    @Override
                    protected void convert(ViewHolder holder, String model, int position) {
                        TextView tv = holder.getView(R.id.tv);
                        tv.setText(model);
                    }
                };
                adapter_abc.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        String s = editText1.getText().toString().trim();
                        if (i == abcList.size() - 1) {
                            if (s.length() > 0) {
                                s = s.substring(0, s.length() - 1);
                            }
                            editText1.setText(s);
                        } else {
                            editText1.setText(s + abcList.get(i));
                        }
                        if (editText1.getText().toString().trim().length() == 6) {
                            editText1.setTextColor(getResources().getColor(R.color.green));
                            dialog.dismiss();
                        } else {
                            editText1.setTextColor(getResources().getColor(R.color.black));
                        }

                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                rv_carnum1.setAdapter(adapter_abc);

                break;
            case R.id.textView4:
                //车辆品牌
                Intent intent1 = new Intent(AddJieCheActivity.this, AddCarModelActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("type", 10001);
                intent1.putExtras(bundle1);
                startActivityForResult(intent1, 10001, bundle1);
                break;
            case R.id.textView1:
                //交强险有效日期
                setDate1("请选择交强险有效日期", textView1, textView1.getText().toString().trim());
                break;
            case R.id.textView2:
                //商业保险有效日期
                setDate1("请选择商业保险有效日期", textView2, textView2.getText().toString().trim());
                break;
            case R.id.textView3:
                //年审时间
                setDate1("请选择年审时间", textView3, textView3.getText().toString().trim());
                break;
            case R.id.tv_confirm:
                //发布
                if (match()) {
                    showProgress(true, getString(R.string.app_loading1));
                    if (listFiles.size() == 0) {
                        Map<String, String> params = new HashMap<>();
                        params.put("u_token", localUserInfo.getToken());
                        params.put("y_order_id", y_order_id);
                        params.put("license_number", license_number);
                        params.put("owner_name", owner_name);
                        params.put("owner_phone", owner_phone);
                        params.put("y_sedan_brand_id", y_sedan_brand_id);
                        params.put("frame_number", frame_number);
                        params.put("vehicle_mileage", vehicle_mileage);
                        params.put("comp_insurance_time", comp_insurance_time);
                        params.put("com_insurance_time", com_insurance_time);
                        params.put("annual_review_time", annual_review_time);
                        params.put("v_remarks", v_remarks);
                        params.put("appe_imgstr", "");
                        RequestUpData(params);
                    } else {
                        Map<String, String> params = new HashMap<>();
                        params.put("sn", "773EDB6D2715FACF9C93354CAC5B1A3372872DC4D5AC085867C7490E9984D33E");
                        RequestUpFile(params, listFiles, "picture");
                    }

                }
                break;
        }
    }

    //上传图片
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
//                hideProgress();
//                myToast("上传图片成功");
                String imgstr = "";
                for (String s : response.getList()) {
                    imgstr += s + "||";
                }
                if (!imgstr.equals("")) {
                    imgstr = imgstr.substring(0, imgstr.length() - 2);
                }
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("y_order_id", y_order_id);
                params.put("license_number", license_number);
                params.put("owner_name", owner_name);
                params.put("owner_phone", owner_phone);
                params.put("y_sedan_brand_id", y_sedan_brand_id);
                params.put("frame_number", frame_number);
                params.put("vehicle_mileage", vehicle_mileage);
                params.put("comp_insurance_time", comp_insurance_time);
                params.put("com_insurance_time", com_insurance_time);
                params.put("annual_review_time", annual_review_time);
                params.put("v_remarks", v_remarks);
                params.put("appe_imgstr", imgstr);
                RequestUpData(params);
            }
        });
    }

    //提交意见-图片用|连接
    private void RequestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.AddJieChe, params, headerMap, new CallBackUtil() {
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

                Intent resultIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putBoolean("isfinish", true);
                resultIntent.putExtras(bundle);
                AddJieCheActivity.this.setResult(RESULT_OK, resultIntent);

                showToast("接车成功", new View.OnClickListener() {
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
        license_number = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(license_number)) {
            showToast("请输入车牌号");
            return false;
        }
        license_number = tv_chepai.getText().toString().trim() + license_number;

        owner_name = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(owner_name)) {
            showToast("请输入车主姓名");
            return false;
        }
        owner_phone = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(owner_phone)) {
            showToast("请输入车主手机号");
            return false;
        }
//        frame_number = editText4.getText().toString().trim();
        if (TextUtils.isEmpty(y_sedan_brand_id)) {
            showToast("请输入品牌");
            return false;
        }
        frame_number = editText5.getText().toString().trim();
        /*if (TextUtils.isEmpty(frame_number)) {
            showToast("请输入车架号");
            return false;
        }*/
        vehicle_mileage = editText6.getText().toString().trim();
       /* if (TextUtils.isEmpty(vehicle_mileage)) {
            showToast("请输入车辆里程");
            return false;
        }*/
        comp_insurance_time = textView1.getText().toString().trim();
        /*if (TextUtils.isEmpty(comp_insurance_time)) {
            showToast("请选择交强险有效日期");
            return false;
        }*/
        com_insurance_time = textView2.getText().toString().trim();
        /*if (TextUtils.isEmpty(com_insurance_time)) {
            showToast("请选择商业保险有效日期");
            return false;
        }*/
        annual_review_time = textView3.getText().toString().trim();
       /* if (TextUtils.isEmpty(annual_review_time)) {
            showToast("请选择年审时间");
            return false;
        }*/
        v_remarks = editText7.getText().toString().trim();
        /*if (TextUtils.isEmpty(v_remarks)) {
            showToast("请输入备注");
            return false;
        }*/
        /*if (listFiles.size() == 0) {
            showToast("请上传图片");
            return false;
        }*/
        return true;
    }

    @Override
    protected void updateView() {
        titleView.setTitle("接车");

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
        calendar.add(Calendar.YEAR, 100);
//        endDate.set(year, month, day);
        endDate.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));


        pvTime1 = new TimePickerBuilder(AddJieCheActivity.this, new OnTimeSelectListener() {
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

    /**
     * *****************************************选择图片********************************************
     */
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            // 进入相册
            PictureSelector.create(AddJieCheActivity.this)
                    .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                    .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style v2.3.3后 建议使用setPictureStyle()动态方式
                    .isWeChatStyle(false)// 是否开启微信图片选择风格
                    .isUseCustomCamera(false)// 是否使用自定义相机
//                        .setLanguage(language)// 设置语言，默认中文
                    .isPageStrategy(true)// 是否开启分页策略 & 每页多少条；默认开启
                    .isWithVideoImage(true)// 图片和视频是否可以同选,只在ofAll模式下有效
                    .isMaxSelectEnabledMask(true)// 选择数到了最大阀值列表是否启用蒙层效果
                    .loadCacheResourcesCallback(GlideCacheEngine.createCacheEngine())// 获取图片资源缓存，主要是解决华为10部分机型在拷贝文件过多时会出现卡的问题，这里可以判断只在会出现一直转圈问题机型上使用
//                        .setOutputCameraPath()// 自定义相机输出目录，只针对Android Q以下，例如 Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) +  File.separator + "Camera" + File.separator;
                    //.setButtonFeatures(CustomCameraView.BUTTON_STATE_BOTH)// 设置自定义相机按钮状态
                    .maxSelectNum(maxSelectNum)// 最大图片选择数量
                    .minSelectNum(1)// 最小选择数量
                    //.maxVideoSelectNum(1) // 视频最大选择数量
                    //.minVideoSelectNum(1)// 视频最小选择数量
                    .imageSpanCount(4)// 每行显示个数
                    .isReturnEmpty(false)// 未选择数据时点击按钮是否可以返回
                    .closeAndroidQChangeWH(true)//如果图片有旋转角度则对换宽高,默认为true
                    .closeAndroidQChangeVideoWH(!SdkVersionUtils.checkedAndroid_Q())// 如果视频有旋转角度则对换宽高,默认为false
                    //.isAndroidQTransform(false)// 是否需要处理Android Q 拷贝至应用沙盒的操作，只针对compress(false); && .isEnableCrop(false);有效,默认处理
                    .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)// 设置相册Activity方向，不设置默认使用系统
                    .isOriginalImageControl(false)// 是否显示原图控制按钮，如果设置为true则用户可以自由选择是否使用原图，压缩、裁剪功能将会失效
                    .selectionMode(PictureConfig.MULTIPLE)// 多选PictureConfig.MULTIPLE or 单选 PictureConfig.SINGLE
                    .isSingleDirectReturn(true)// 单选模式下是否直接返回，PictureConfig.SINGLE模式下有效
                    .isPreviewImage(true)// 是否可预览图片
                    .isPreviewVideo(true)// 是否可预览视频
                    //.querySpecifiedFormatSuffix(PictureMimeType.ofJPEG())// 查询指定后缀格式资源
                    .isCamera(true)// 是否显示拍照按钮
                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                    //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg,Android Q使用PictureMimeType.PNG_Q
                    .isEnableCrop(false)// 是否裁剪
                    .isCompress(true)// 是否压缩
                    .compressQuality(60)// 图片压缩后输出质量 0~ 100
                    .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                    .synOrAsy(true)//同步true或异步false 压缩 默认同步
                    //.queryMaxFileSize(10)// 只查多少M以内的图片、视频、音频  单位M
                    //.compressSavePath(getPath())//压缩图片保存地址
                    //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效 注：已废弃
                    //.glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度 注：已废弃
                    .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示
                    .isGif(true)// 是否显示gif图片
                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                    .circleDimmedLayer(false)// 是否圆形裁剪
                    //.setCircleDimmedBorderColor(ContextCompat.getColor(getApplicationContext(), R.color.app_color_white))// 设置圆形裁剪边框色值
                    //.setCircleStrokeWidth(3)// 设置圆形裁剪边框粗细
                    .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                    .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                    .isOpenClickSound(false)// 是否开启点击声音
                    .selectionData(mAdapter.getData())// 是否传入已选图片
                    .isDragFrame(true)// 是否可拖动裁剪框(固定)
                    //.videoMinSecond(10)// 查询多少秒以内的视频
                    //.videoMaxSecond(15)// 查询多少秒以内的视频
                    //.recordVideoSecond(10)//录制视频秒数 默认60s
                    .cutOutQuality(90)// 裁剪输出质量 默认100
                    .minimumCompressSize(100)// 小于多少kb的图片不压缩
                    //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                    //.cropImageWideHigh()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                    .rotateEnabled(true) // 裁剪是否可旋转图片
                    .scaleEnabled(true)// 裁剪是否可放大缩小图片
                    .forResult(new MyResultCallback(mAdapter));
        }
    };

    /**
     * 返回结果回调
     */
    private class MyResultCallback implements OnResultCallbackListener<LocalMedia> {
        private WeakReference<GridImageAdapter> mAdapterWeakReference;

        public MyResultCallback(GridImageAdapter adapter) {
            super();
            this.mAdapterWeakReference = new WeakReference<>(adapter);
        }

        @Override
        public void onResult(List<LocalMedia> result) {
            listFiles.clear();
            for (LocalMedia media : result) {
                MyLogger.i(">>>>>>压缩地址：" + media.getCompressPath());
                File file = new File(media.getCompressPath());
                listFiles.add(file);
                // TODO 可以通过PictureSelectorExternalUtils.getExifInterface();方法获取一些额外的资源信息，如旋转角度、经纬度等信息
            }
            if (mAdapterWeakReference.get() != null) {
                mAdapterWeakReference.get().setList(result);
                mAdapterWeakReference.get().notifyDataSetChanged();
            }
        }

        @Override
        public void onCancel() {
//            Log.i(TAG, "图片选择取消");
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
                    textView4.setText(pingpai + "\n" + xinghao);
                }
                break;
            case 10002:
                if (data != null) {
                    license_number = data.getExtras().getString("license_number");
                    if (!license_number.equals("")) {
                        String star = license_number.substring(0, 1);
                        for (int j = 0; j < provinceList.size(); j++) {
                            if (star.equals(provinceList.get(j))) {
                                MyLogger.i(">>>>>" + star);
                                i_province = j;
                                tv_chepai.setText(license_number.substring(0, 2));
                            }
                        }
                        editText1.setText(license_number.substring(2, license_number.length()));
                        if (editText1.getText().toString().trim().length() == 6) {
                            editText1.setTextColor(getResources().getColor(R.color.green));
                        } else {
                            editText1.setTextColor(getResources().getColor(R.color.black));
                        }
                    }
                }

                break;
        }

    }
}
