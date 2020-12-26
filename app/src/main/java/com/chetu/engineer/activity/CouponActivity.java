package com.chetu.engineer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.CouponModel;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.utils.CommonUtil;
import com.liaoinstan.springview.widget.SpringView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/5/26.
 * 优惠券
 */
public class CouponActivity extends BaseActivity {
    String y_techn_sedan_id = "";
    int page = 0;
    private RecyclerView recyclerView;
    List<CouponModel.ListBean> list = new ArrayList<>();
    CommonAdapter<CouponModel.ListBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    protected void initView() {
        //刷新
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                Map<String, String> params = new HashMap<>();
                params.put("page", page + "");
                params.put("u_token", localUserInfo.getToken());
                params.put("y_techn_sedan_id", y_techn_sedan_id);
                Request(params);
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("y_techn_sedan_id", y_techn_sedan_id);
                params.put("page", page + "");
                RequestMore(params);
            }
        });

        recyclerView = findViewByID_My(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    protected void initData() {
        y_techn_sedan_id = getIntent().getStringExtra("y_techn_sedan_id");
        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 0;
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("u_token", localUserInfo.getToken());
        params.put("y_techn_sedan_id", y_techn_sedan_id);
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Coupon, params, headerMap, new CallBackUtil<CouponModel>() {
            @Override
            public CouponModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(CouponModel response) {
                hideProgress();
                list = response.getList();
                if (list.size() > 0) {
                    showContentPage();
                    mAdapter = new CommonAdapter<CouponModel.ListBean>
                            (CouponActivity.this, R.layout.item_coupon, list) {
                        @Override
                        protected void convert(ViewHolder holder, CouponModel.ListBean model, int position) {
                            TextView tv_money = holder.getView(R.id.tv_money);
                            TextView tv_title = holder.getView(R.id.tv_title);
                            TextView tv_time = holder.getView(R.id.tv_time);
                            TextView tv_shiyong = holder.getView(R.id.tv_shiyong);
                            TextView tv_content = holder.getView(R.id.tv_content);
                            tv_money.setText("¥" + model.getCMoney());
                            tv_title.setText(model.getCTitle());
                            tv_content.setText(model.getCMsg());
                            LinearLayout ll_btn = holder.getView(R.id.ll_btn);

                            if (!model.getCreateDate().equals("") && !model.getCEndTime().equals("")) {
                                String[] startTime = model.getCreateDate().split(" ");
                                String[] endTime = model.getCEndTime().split(" ");
                                //判断是否使用
                                if (model.getIsUse() == 0) {//未使用
                                    //判断是否过期
                                    if (CommonUtil.dataOne_1(model.getCreateDate()) < CommonUtil.dataOne_1(model.getCEndTime())) {//没过期
                                        //计算剩余天数
                                        String s = CommonUtil.timedate2(CommonUtil.dataOne_1(model.getCEndTime()) - CommonUtil.dataOne_1(model.getCreateDate()));

                                        tv_shiyong.setText("立即使用");
                                        tv_shiyong.setBackgroundResource(R.drawable.yuanjiaobiankuang_5_baise);
                                        tv_time.setText("有效日期：" + endTime[0] + "（剩余" + s + "）");

                                        tv_shiyong.setTextColor(getResources().getColor(R.color.white));
                                        tv_money.setTextColor(getResources().getColor(R.color.white));
                                        tv_time.setTextColor(getResources().getColor(R.color.white));
                                        tv_content.setTextColor(getResources().getColor(R.color.white));
                                        tv_title.setTextColor(getResources().getColor(R.color.white));

                                        /*ll_btn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Bundle bundle = new Bundle();
                                                bundle.putString("y_user_coupon_id", model.getYUserCouponId());
                                                CommonUtil.gotoActivityWithData(CouponActivity.this, CouponQRCodeActivity.class, bundle, false);
                                            }
                                        });*/

                                    } else {//已过期
                                        tv_shiyong.setText("已过期");
                                        tv_shiyong.setBackgroundResource(R.color.transparent);
                                        tv_time.setText("有效日期：" + endTime[0]);

                                        tv_shiyong.setTextColor(getResources().getColor(R.color.white2));
                                        tv_money.setTextColor(getResources().getColor(R.color.white2));
                                        tv_time.setTextColor(getResources().getColor(R.color.white2));
                                        tv_content.setTextColor(getResources().getColor(R.color.white2));
                                        tv_title.setTextColor(getResources().getColor(R.color.white2));
                                    }
                                } else {//已使用
                                    tv_shiyong.setText("已使用");
                                    tv_shiyong.setBackgroundResource(R.color.transparent);
                                    tv_time.setText("有效日期：" + endTime[0]);

                                    tv_shiyong.setTextColor(getResources().getColor(R.color.white2));
                                    tv_money.setTextColor(getResources().getColor(R.color.white2));
                                    tv_time.setTextColor(getResources().getColor(R.color.white2));
                                    tv_content.setTextColor(getResources().getColor(R.color.white2));
                                    tv_title.setTextColor(getResources().getColor(R.color.white2));
                                }
                            }
                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                } else {
                    showEmptyPage();
                }
            }
        });
    }

    private void RequestMore(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment3, params, headerMap, new CallBackUtil<CouponModel>() {
            @Override
            public CouponModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(CouponModel response) {
                hideProgress();
                List<CouponModel.ListBean> list1 = new ArrayList<>();
                list1 = response.getList();
                if (list1.size() == 0) {
                    page--;
                    myToast(getString(R.string.app_nomore));
                } else {
                    list.addAll(list1);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle("已使用优惠券");
    }
}
