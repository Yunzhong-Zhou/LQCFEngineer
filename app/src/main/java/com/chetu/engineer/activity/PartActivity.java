package com.chetu.engineer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.PartModel;
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
 * Created by zyz on 2020/6/4.
 * 配件
 */
public class PartActivity extends BaseActivity {
    String keyws = "", y_classify_id = "0",v_sort= "1";//1为综合 2这价格升序 3为价格降序  4为销量降序  5为销量升序

    int page = 0;
    private RecyclerView recyclerView;
    List<PartModel.ListBean> list = new ArrayList<>();
    CommonAdapter<PartModel.ListBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part);
    }

    @Override
    protected void initView() {
//        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
        //刷新
        setSpringViewMore(true);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                Map<String, String> params = new HashMap<>();
//                params.put("u_token", localUserInfo.getToken());
                params.put("page", page + "");
                params.put("keyws", keyws);
                params.put("y_classify_id", y_classify_id);
                params.put("v_sort", v_sort);
                Request(params);
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
//                params.put("u_token", localUserInfo.getToken());
                params.put("v_sort", v_sort);
                params.put("page", page + "");
                params.put("keyws", keyws);
                params.put("y_classify_id", y_classify_id);
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
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.left_btn:
                finish();
                break;
        }
    }
    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 0;
        Map<String, String> params = new HashMap<>();
        params.put("v_sort", v_sort);
        params.put("page", page + "");
        params.put("keyws", keyws);
        params.put("y_classify_id", y_classify_id);
//        params.put("u_token", localUserInfo.getToken());
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ProductList, params, headerMap, new CallBackUtil<PartModel>() {
            @Override
            public PartModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
                myToast(err);
            }

            @Override
            public void onResponse(PartModel response) {
                hideProgress();
                list = response.getList();
                if (list.size() > 0) {
                    showContentPage();
                    mAdapter = new CommonAdapter<PartModel.ListBean>
                            (PartActivity.this, R.layout.item_part, list) {
                        @Override
                        protected void convert(ViewHolder holder, PartModel.ListBean model, int position) {
                            holder.setText(R.id.tv_name,model.getGName());
                            holder.setText(R.id.tv_content, model.getGDesc());
                            holder.setText(R.id.tv_money,"" + model.getGPrice());
                            ImageView imageView = holder.getView(R.id.imageView);
                            Glide.with(PartActivity.this).load(URLs.IMGHOST + model.getGImg())
                                    .centerCrop()
//                                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(imageView);//加载图片
                        }
                    };
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            Bundle bundle = new Bundle();
                            bundle.putString("y_goods_id", list.get(i).getYGoodsId());
                            CommonUtil.gotoActivityWithData(PartActivity.this, ProductDetailActivity.class, bundle, false);
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
        OkhttpUtil.okHttpPost(URLs.ProductList, params, headerMap, new CallBackUtil<PartModel>() {
            @Override
            public PartModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(PartModel response) {
                hideProgress();
                List<PartModel.ListBean> list1 = new ArrayList<>();
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
        titleView.setVisibility(View.GONE);
    }
}
