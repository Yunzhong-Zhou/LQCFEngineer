package com.chetu.engineer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.ProductListModel;
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

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/6/10.
 * 商品列表
 */
public class ProductListActivity extends BaseActivity {
    String keyws = "", y_classify_id = "";
    int page = 0;
    RecyclerView recyclerView1;
    List<ProductListModel.ListBean> list1 = new ArrayList<>();
    CommonAdapter<ProductListModel.ListBean> mAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlist);
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
                params.put("keyws", keyws);
                params.put("y_classify_id", y_classify_id);
//                params.put("u_token", localUserInfo.getToken());
                Request(params);
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
//                params.put("u_token", localUserInfo.getToken());
                params.put("page", page + "");
                params.put("keyws", keyws);
                params.put("y_classify_id", y_classify_id);
                RequestMore(params);
            }
        });
        recyclerView1 = findViewByID_My(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new GridLayoutManager(this, 2));

    }

    @Override
    protected void initData() {
        y_classify_id = getIntent().getStringExtra("id");
        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 0;
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("keyws", keyws);
        params.put("y_classify_id", y_classify_id);
//        params.put("u_token", localUserInfo.getToken());
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ProductList, params, headerMap, new CallBackUtil<ProductListModel>() {
            @Override
            public ProductListModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(ProductListModel response) {
                hideProgress();
                list1 = response.getList();
                if (list1.size() > 0) {
                    showContentPage();
                    mAdapter1 = new CommonAdapter<ProductListModel.ListBean>
                            (ProductListActivity.this, R.layout.item_productlist, list1) {
                        @Override
                        protected void convert(ViewHolder holder, ProductListModel.ListBean model, int position) {
                            //logo
                            ImageView imageView = holder.getView(R.id.imageView);
                            Glide.with(ProductListActivity.this).load(URLs.IMGHOST + model.getGImg())
                                    .centerCrop()
                                    .apply(RequestOptions.bitmapTransform(new
                                            RoundedCorners(CommonUtil.dip2px(ProductListActivity.this,5))))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(imageView);//加载图片

                            holder.setText(R.id.textView1, model.getGName());
                            holder.setText(R.id.textView2, model.getGPrice() + "");
                            TextView textView3 = holder.getView(R.id.textView3);
                            textView3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //购买
                                }
                            });
                        }
                    };
                    mAdapter1.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            Bundle bundle = new Bundle();
                            bundle.putString("y_goods_id", list1.get(i).getYGoodsId());
                            CommonUtil.gotoActivityWithData(ProductListActivity.this, ProductDetailActivity.class, bundle, false);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                    recyclerView1.setAdapter(mAdapter1);
                } else {
                    showEmptyPage();
                }
            }
        });
    }

    /**
     * 加载更多
     *
     * @param params
     */
    private void RequestMore(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ProductList, params, headerMap, new CallBackUtil<ProductListModel>() {
            @Override
            public ProductListModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
//                myToast(err);
                page--;
            }

            @Override
            public void onResponse(ProductListModel response) {
                hideProgress();
                List<ProductListModel.ListBean> list_1 = new ArrayList<>();
                list_1 = response.getList();
                if (list_1.size() == 0) {
                    page--;
                    myToast(getString(R.string.app_nomore));
                } else {
                    list1.addAll(list_1);
                    mAdapter1.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle("商品列表");
    }
}
