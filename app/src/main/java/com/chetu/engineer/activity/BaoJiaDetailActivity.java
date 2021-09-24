package com.chetu.engineer.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.MyBaoJiaModel;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.popupwindow.PhotoShowDialog;
import com.chetu.engineer.utils.MyLogger;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/6/19.
 * 报价详情
 */
public class BaoJiaDetailActivity extends BaseActivity {
    MyBaoJiaModel.ListBean model;
    ImageView iv_carlogo;
    TextView tv_carname, tv_carnum, tv_cardetail, tv_qingkuangshuoming,tv_confirm ;
    RecyclerView recyclerView;

    String y_inquiry_demand_project_id_str = "", v_price_str = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baojiadetail);
        mImmersionBar.reset()
                .statusBarColor(R.color.background)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @Override
    protected void initView() {
        iv_carlogo = findViewByID_My(R.id.iv_carlogo);
        tv_carname = findViewByID_My(R.id.tv_carname);
        tv_carnum = findViewByID_My(R.id.tv_carnum);
        tv_cardetail = findViewByID_My(R.id.tv_cardetail);
        tv_qingkuangshuoming = findViewByID_My(R.id.tv_qingkuangshuoming);
        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tv_confirm = findViewByID_My(R.id.tv_confirm);

        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提交报价
                y_inquiry_demand_project_id_str = "";
                v_price_str = "";
                for (MyBaoJiaModel.ListBean.ProjectListBean listBean : model.getProject_list()) {

                    if (listBean.getPrice() != null && !listBean.getPrice().equals("")) {
                        y_inquiry_demand_project_id_str += listBean.getYInquiryDemandProjectId() + "||";
                        v_price_str += listBean.getPrice() + "||";

                    }
                }
                if (!y_inquiry_demand_project_id_str.equals("")) {
                    y_inquiry_demand_project_id_str = y_inquiry_demand_project_id_str.substring(0, y_inquiry_demand_project_id_str.length() - 2);
                    MyLogger.i(">>>>>>>>服务ID：" + y_inquiry_demand_project_id_str);
                    v_price_str = v_price_str.substring(0, v_price_str.length() - 2);
                    MyLogger.i(">>>>>>>>服务报价：" + v_price_str);

                    showProgress(true, "正在提交报价...");
                    params.put("u_token", localUserInfo.getToken());
                    params.put("y_inquiry_demand_project_id_str", y_inquiry_demand_project_id_str);
                    params.put("v_price_str", v_price_str);
                    RequestUp(params);
                }else {
                    myToast("暂无报价更新");
                }

            }
        });
    }

    @Override
    protected void initData() {
        model = (MyBaoJiaModel.ListBean) getIntent().getSerializableExtra("detail");
        titleView.setTitle("来自" + model.getUser_info().getUserName() + "的询价");

        tv_carname.setText(model.getUser_sedan_info().getBrandInfo().getSeriesName());
        tv_carnum.setText(model.getUser_sedan_info().getSNumber());
        tv_cardetail.setText(model.getUser_sedan_info().getBrandInfo().getSName());
        Glide.with(this).load(URLs.IMGHOST + model.getUser_sedan_info().getSLogo())
                .centerCrop()
                .into(iv_carlogo);//加载图片
        tv_qingkuangshuoming.setText("情况说明：" + model.getvMsg());
        CommonAdapter<MyBaoJiaModel.ListBean.ProjectListBean> mAdapter = new CommonAdapter<MyBaoJiaModel.ListBean.ProjectListBean>
                (BaoJiaDetailActivity.this, R.layout.item_baojiadetail, model.getProject_list()) {
            @Override
            protected void convert(ViewHolder holder, MyBaoJiaModel.ListBean.ProjectListBean model, int position) {
                holder.setText(R.id.tv_title, model.getVTitle());
                if (model.getvMsg()!=null&&!model.getvMsg().equals("#")) {
                    holder.setText(R.id.tv_qingkuangshuoming, "项目说明：" + model.getvMsg());
                } else {
                    holder.setText(R.id.tv_qingkuangshuoming, "项目说明：暂无项目说明");
                }
                TextView tv_daibaojia = holder.getView(R.id.tv_baojia);
                EditText et_baojia = holder.getView(R.id.et_baojia);

                //是否有图片
                RecyclerView rv_tupian = holder.getView(R.id.rv_tupian);
                if (model.getImgArr() != null && model.getImgArr().size() > 0) {
                    rv_tupian.setVisibility(View.VISIBLE);

                    LinearLayoutManager llm1 = new LinearLayoutManager(BaoJiaDetailActivity.this);
                    llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                    rv_tupian.setLayoutManager(llm1);

                    ArrayList<String> images = new ArrayList<>();
                    for (String s : model.getImgArr()) {
                        images.add(URLs.IMGHOST + s);
                    }
                    CommonAdapter<String> mAdapter_tupian = new CommonAdapter<String>
                            (BaoJiaDetailActivity.this, R.layout.item_img_110_110, images) {
                        @Override
                        protected void convert(ViewHolder holder, String model, int position) {
                            ImageView iv = holder.getView(R.id.iv);
                            Glide.with(BaoJiaDetailActivity.this)
                                    .load(model)
                                    .centerCrop()
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(iv);//加载图片
                        }
                    };
                    mAdapter_tupian.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {

                            PhotoShowDialog photoShowDialog = new PhotoShowDialog(BaoJiaDetailActivity.this, images, i);
                            photoShowDialog.show();
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                    rv_tupian.setAdapter(mAdapter_tupian);

                } else {
                    rv_tupian.setVisibility(View.GONE);
                }

                //是否有报价
                if (model.getOffer_list() != null && model.getOffer_list().size() > 0) {//有报价
                    tv_daibaojia.setVisibility(View.VISIBLE);
                    et_baojia.setVisibility(View.GONE);
                    tv_daibaojia.setText(model.getOffer_list().get(0).getUser_info().getUserName()+"-" + model.getOffer_list().get(0).getVPrice());
                    tv_confirm.setVisibility(View.GONE);
                } else {
                    //无报价
                    tv_daibaojia.setVisibility(View.GONE);
                    et_baojia.setVisibility(View.VISIBLE);
                    tv_confirm.setVisibility(View.VISIBLE);
                }

                //提交报价
                /*et_baojia.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEND) {
                            if (!et_baojia.getText().toString().trim().equals("")) {
                                showProgress(true, "正在提交报价...");
                                params.put("u_token", localUserInfo.getToken());
                                params.put("y_inquiry_demand_project_id", model.getYInquiryDemandProjectId());
                                params.put("v_price", et_baojia.getText().toString().trim());
                                RequestUp(params);
                            } else {
                                myToast("请输入报价");
                            }
                        }
                        return true;
                    }
                });*/
                et_baojia.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if (!editable.toString().trim().equals("")) {
                            model.setPrice(editable.toString().trim());
                            MyLogger.i(">>>>>>>" + model.getPrice());
                        }
                    }
                });

            }
        };
        recyclerView.setAdapter(mAdapter);
    }

    /**
     * 报价
     *
     * @param params
     */
    private void RequestUp(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.UpBaoJia, params, headerMap, new CallBackUtil<Object>() {
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
                myToast("报价成功");
//                requestServer();
                finish();
            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setBackground(R.color.background);
    }
}
