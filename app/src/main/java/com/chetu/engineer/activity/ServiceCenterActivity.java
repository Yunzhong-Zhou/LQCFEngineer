package com.chetu.engineer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.ServiceCenterModel;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.liaoinstan.springview.widget.SpringView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
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
 * Created by zyz on 2020/5/28.
 * 客服中心
 */
public class ServiceCenterActivity extends BaseActivity {
    int page = 0;
    private RecyclerView recyclerView;
    List<ServiceCenterModel.ListBean> list = new ArrayList<>();
    CommonAdapter<ServiceCenterModel.ListBean> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicecenter);
        mImmersionBar.reset()
                .statusBarColor(R.color.background)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
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
                Request(params);
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
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
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ServiceCenter, params, headerMap, new CallBackUtil<ServiceCenterModel>() {
            @Override
            public ServiceCenterModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
                myToast(err);
            }

            @Override
            public void onResponse(ServiceCenterModel response) {
                hideProgress();
                list = response.getList();
                if (list.size() > 0) {
                    showContentPage();
                    mAdapter = new CommonAdapter<ServiceCenterModel.ListBean>
                            (ServiceCenterActivity.this, R.layout.item_servicecenter, list) {
                        @Override
                        protected void convert(ViewHolder holder, ServiceCenterModel.ListBean model, int position) {
                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(ServiceCenterActivity.this)
                                    .load(URLs.IMGHOST + model.getHeadPortrait())
                                    .centerCrop()
                                    .into(imageView1);//加载图片

                            holder.setText(R.id.tv_name, model.getUserName());

                            TextView tv_zaixian = holder.getView(R.id.tv_zaixian);
                            View view = holder.getView(R.id.view);
                            if (model.getIsKf() == 1){
                                tv_zaixian.setText("在线");
                                tv_zaixian.setTextColor(getResources().getColor(R.color.green));
                                view.setBackgroundResource(R.drawable.yuanxing_lvse);
                            }else {
                                tv_zaixian.setText("离线");
                                tv_zaixian.setTextColor(getResources().getColor(R.color.black3));
                                view.setBackgroundResource(R.drawable.yuanxing_huise);
                            }

                        }
                    };
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {

                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                    recyclerView.setAdapter(mAdapter);
                } else {
                    showEmptyPage();
                }
            }
        });
    }

    private void RequestMore(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ServiceCenter, params, headerMap, new CallBackUtil<ServiceCenterModel>() {
            @Override
            public ServiceCenterModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(ServiceCenterModel response) {
                hideProgress();
                List<ServiceCenterModel.ListBean> list1 = new ArrayList<>();
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
        titleView.setTitle("客服列表");
        titleView.setBackground(R.color.background);
    }
}
