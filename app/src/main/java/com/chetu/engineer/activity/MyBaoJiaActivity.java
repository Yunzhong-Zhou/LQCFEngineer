package com.chetu.engineer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.MyBaoJiaModel;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.utils.CommonUtil;
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
 * Created by zyz on 2020/6/22.
 * 我的报价
 */
public class MyBaoJiaActivity extends BaseActivity {
    String y_store_id = "";
    int page = 0;
    private RecyclerView recyclerView;
    List<MyBaoJiaModel.ListBean> list = new ArrayList<>();
    CommonAdapter<MyBaoJiaModel.ListBean> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybaojia);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestServer();
    }

    @Override
    protected void initView() {
        //刷新
        setSpringViewMore(true);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                Map<String, String> params = new HashMap<>();
                params.put("page", page + "");
                params.put("y_store_id", y_store_id);
                params.put("u_token", localUserInfo.getToken());
                Request(params);
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("y_store_id", y_store_id);
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
        y_store_id = getIntent().getStringExtra("y_store_id");
    }
    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 0;
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("y_store_id", y_store_id);
        params.put("u_token", localUserInfo.getToken());
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.MyBaoJia, params, headerMap, new CallBackUtil<MyBaoJiaModel>() {
            @Override
            public MyBaoJiaModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(MyBaoJiaModel response) {
                hideProgress();
                list = response.getList();
                if (list.size() > 0) {
                    showContentPage();
                    mAdapter = new CommonAdapter<MyBaoJiaModel.ListBean>
                            (MyBaoJiaActivity.this, R.layout.item_mybaojia, list) {
                        @Override
                        protected void convert(ViewHolder holder, MyBaoJiaModel.ListBean model, int position) {
//                            holder.setText(R.id.tv_title,model.getUser_sedan_info().getBrandInfo().getSName());
                            holder.setText(R.id.tv_title,"来自"+model.getUser_info().getUserName()+"的询价");
                            holder.setText(R.id.tv_num,model.getUser_sedan_info().getSNumber());
                            holder.setText(R.id.tv_qingkuangshuoming,"情况说明："+model.getvMsg());
                            holder.setText(R.id.tv_content,model.getServiceName());
                            holder.setText(R.id.tv_time,model.getCreateDate());
                            TextView tv_delete = holder.getView(R.id.tv_delete);
                            TextView tv_chakan = holder.getView(R.id.tv_chakan);
                            /*switch (model.getIsOk()){
                                case 0:
                                    //待发布
                                    tv_delete.setVisibility(View.VISIBLE);
                                    tv_fabu.setVisibility(View.VISIBLE);
                                    tv_baojia.setVisibility(View.VISIBLE);
                                    break;
                                case 1:
                                    //已发布
                                    tv_delete.setVisibility(View.VISIBLE);
                                    tv_fabu.setVisibility(View.GONE);
                                    tv_baojia.setVisibility(View.VISIBLE);
                                    break;
                            }*/
                            tv_delete.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //删除
                                    showToast("确认删除该询价吗？", "确认", "取消", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                            showProgress(true, "正在删除...");
                                            HashMap<String, String> params2 = new HashMap<>();
                                            params2.put("y_inquiry_demand_id", model.getYInquiryDemandId());
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
                            tv_chakan.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //报价
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("detail",model);
                                    CommonUtil.gotoActivityWithData(MyBaoJiaActivity.this,BaoJiaDetailActivity.class,bundle,false);
                                }
                            });

                        }
                    };
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("detail",list.get(i));
                            CommonUtil.gotoActivityWithData(MyBaoJiaActivity.this,BaoJiaDetailActivity.class,bundle,false);
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
        OkhttpUtil.okHttpPost(URLs.MyBaoJia, params, headerMap, new CallBackUtil<MyBaoJiaModel>() {
            @Override
            public MyBaoJiaModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(MyBaoJiaModel response) {
                hideProgress();
                List<MyBaoJiaModel.ListBean> list1 = new ArrayList<>();
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
    /**
     * 删除
     *
     * @param params
     */
    private void RequestDelete(HashMap<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.DeleteBaoJia, params, headerMap, new CallBackUtil<Object>() {
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

    @Override
    protected void updateView() {
        titleView.setTitle("我的报价");
    }
}
