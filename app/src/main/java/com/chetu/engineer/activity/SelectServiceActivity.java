package com.chetu.engineer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.ServiceListModel_All;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
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
 * Created by zyz on 2020/7/7.
 */
public class SelectServiceActivity extends BaseActivity {
    String y_store_service_id = "", service_name = "";

    int i1 = 0, i2 = 0;
    RecyclerView rv_tab1;
    List<ServiceListModel_All.ListBean> list_tab1 = new ArrayList<>();
    CommonAdapter<ServiceListModel_All.ListBean> mAdapter_tab1;

    RecyclerView rv_tab2;
    List<ServiceListModel_All.ListBean.VListBeanX> list_tab2 = new ArrayList<>();
    CommonAdapter<ServiceListModel_All.ListBean.VListBeanX> mAdapter_tab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectservice);
    }

    @Override
    protected void initView() {
        rv_tab1 = findViewByID_My(R.id.rv_tab1);
        rv_tab1.setLayoutManager(new LinearLayoutManager(this));

        rv_tab2 = findViewByID_My(R.id.rv_tab2);
        rv_tab2.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
//        page = 0;
        //获取服务tab
        HashMap<String, String> params2 = new HashMap<>();
        params2.put("parent_id", "0");
        params2.put("keys", "");
        RequestService(params2);
    }

    private void RequestService(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ServiceList_all, params, headerMap, new CallBackUtil<ServiceListModel_All>() {
            @Override
            public ServiceListModel_All onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(ServiceListModel_All response) {
                hideProgress();
                list_tab1 = response.getList();
                mAdapter_tab1 = new CommonAdapter<ServiceListModel_All.ListBean>(SelectServiceActivity.this, R.layout.item_selectservice_tab1, list_tab1) {
                    @Override
                    protected void convert(ViewHolder holder, ServiceListModel_All.ListBean model, int position) {
                        View view = holder.getView(R.id.view);
                        TextView tv_title = holder.getView(R.id.tv_title);
                        tv_title.setText(model.getVName());
                        if (i1 == position) {
                            view.setVisibility(View.VISIBLE);
                            tv_title.setBackgroundResource(R.color.white);
                            tv_title.setTextColor(getResources().getColor(R.color.blue));

                            list_tab2 = list_tab1.get(i1).getV_list();
                            if (list_tab2.size() > 0) {
                                y_store_service_id = list_tab2.get(0).getYServiceId();
                                service_name = list_tab2.get(0).getVName();

                                showContentPage();
                                mAdapter_tab2 = new CommonAdapter<ServiceListModel_All.ListBean.VListBeanX>(SelectServiceActivity.this, R.layout.item_selectservice_tab2, list_tab2) {
                                    @Override
                                    protected void convert(ViewHolder holder, ServiceListModel_All.ListBean.VListBeanX listBean, int position) {
                                        holder.setText(R.id.textView, listBean.getVName());
                                        ImageView imageView = holder.getView(R.id.imageView);
                                        if (i2 == position) {
                                            imageView.setImageResource(R.mipmap.ic_xuanzhong);
                                        } else {
                                            imageView.setImageResource(R.mipmap.ic_weixuan);
                                        }
                                    }
                                };
                                mAdapter_tab2.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                        i2 = i;
                                        mAdapter_tab2.notifyDataSetChanged();
                                        y_store_service_id = list_tab2.get(i2).getYServiceId();
                                        service_name = list_tab2.get(i2).getVName();
                                    }

                                    @Override
                                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                        return false;
                                    }
                                });
                                rv_tab2.setAdapter(mAdapter_tab2);
                            } else {
                                showEmptyPage();
                            }

                        } else {
                            view.setVisibility(View.INVISIBLE);
                            tv_title.setBackgroundResource(R.color.bg_gray);
                            tv_title.setTextColor(getResources().getColor(R.color.black));
                        }
                    }
                };
                mAdapter_tab1.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        i1 = i;
                        mAdapter_tab1.notifyDataSetChanged();
                        y_store_service_id = "";
                        service_name = "";
                        i2 = 0;
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                rv_tab1.setAdapter(mAdapter_tab1);


            }
        });

    }

    @Override
    protected void updateView() {
        titleView.setTitle("选择检测项目");
        titleView.showRightTextview("确认", R.color.blue, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!y_store_service_id.equals("")) {
                    Intent resultIntent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("y_store_service_id", y_store_service_id);
                    bundle.putString("service_name", service_name);
                    resultIntent.putExtras(bundle);
                    SelectServiceActivity.this.setResult(RESULT_OK, resultIntent);
                    finish();
                } else {
                    myToast("请选择服务");
                }
            }
        });
    }
}
