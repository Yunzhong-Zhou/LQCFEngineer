package com.chetu.engineer.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.PingJiaModel;
import com.chetu.engineer.model.StoreDetailModel;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
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
 * Created by zyz on 2020/7/1.
 * 评论列表
 */
public class PingLunListActivity extends BaseActivity {
    StoreDetailModel storeDetailModel;
    int page = 0;
    String y_store_id = "";

    RecyclerView recyclerView;
    List<PingJiaModel.ListBean> list_pinglun = new ArrayList<>();
    CommonAdapter<PingJiaModel.ListBean> mAdapter_pinglun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiwenlist);
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
                params.put("y_goods_id", "0");
                params.put("y_store_id", y_store_id);
                params.put("u_token", localUserInfo.getToken());
                Request(params);
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
                params.put("page", page + "");
                params.put("y_goods_id", "0");
                params.put("y_store_id", y_store_id);
                params.put("u_token", localUserInfo.getToken());
                RequestMore(params);
            }
        });
        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        y_store_id = getIntent().getStringExtra("y_store_id");
        storeDetailModel = (StoreDetailModel) getIntent().getSerializableExtra("storeDetailModel");
        requestServer();
    }
    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 0;
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("y_goods_id", "0");
        params.put("y_store_id", y_store_id);
        params.put("u_token", localUserInfo.getToken());
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.PingJiaList, params, headerMap, new CallBackUtil<PingJiaModel>() {
            @Override
            public PingJiaModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
//                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(PingJiaModel response) {
                hideProgress();
                list_pinglun = response.getList();
                if (list_pinglun.size() > 0) {
                    showContentPage();
                    mAdapter_pinglun = new CommonAdapter<PingJiaModel.ListBean>
                            (PingLunListActivity.this, R.layout.item_productdetail, list_pinglun) {
                        @Override
                        protected void convert(ViewHolder holder, PingJiaModel.ListBean model, int position) {
                            //信息
                            holder.setText(R.id.tv_name, model.getY_user().getUserName());
                            holder.setText(R.id.tv_time, model.getCreateDate());
                            holder.setText(R.id.tv_content, model.getYMsg());
                            RatingBar ratingbar = holder.getView(R.id.ratingbar);
                            ratingbar.setRating(Float.valueOf(model.getStarC()));
                            ImageView iv = holder.getView(R.id.iv);
                            Glide.with(PingLunListActivity.this).load(model)
                                    .centerCrop()
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(iv);

                            //横向图片
                            List<String> list_img = new ArrayList<>();
                            for (String s : model.getImgArr()) {
                                list_img.add(URLs.IMGHOST + s);
                            }
                            RecyclerView rv = holder.getView(R.id.rv);
                            LinearLayoutManager llm1 = new LinearLayoutManager(PingLunListActivity.this);
                            llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                            rv.setLayoutManager(llm1);
                            CommonAdapter<String> ca = new CommonAdapter<String>
                                    (PingLunListActivity.this, R.layout.item_img_80_80, list_img) {
                                @Override
                                protected void convert(ViewHolder holder, String model, int position) {
                                    ImageView iv = holder.getView(R.id.iv);
                                    Glide.with(PingLunListActivity.this).load(model)
//                            .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                            .placeholder(R.mipmap.loading)//加载站位图
                                            .error(R.mipmap.zanwutupian)//加载失败
                                            .into(iv);//加载图片
                                }
                            };
                            rv.setAdapter(ca);
                        }
                    };
                    recyclerView.setAdapter(mAdapter_pinglun);
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
        OkhttpUtil.okHttpPost(URLs.PingJiaList, params, headerMap, new CallBackUtil<PingJiaModel>() {
            @Override
            public PingJiaModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
//                myToast(err);
                myToast(getString(R.string.app_nomore));
                page--;
            }

            @Override
            public void onResponse(PingJiaModel response) {
                hideProgress();
                List<PingJiaModel.ListBean> list_1 = new ArrayList<>();
                list_1 = response.getList();
                if (list_1.size() == 0) {
                    page--;
                    myToast(getString(R.string.app_nomore));
                } else {
                    list_pinglun.addAll(list_1);
                    mAdapter_pinglun.notifyDataSetChanged();
                }
            }
        });
    }
    @Override
    protected void updateView() {
        titleView.setTitle("门店评价");
        /*titleView.showRightTextview("发布评价", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle1 = new Bundle();
                bundle1.putString("y_store_id", y_store_id);
                bundle1.putSerializable("storeDetailModel",storeDetailModel);
                CommonUtil.gotoActivityWithData(PingLunListActivity.this, AddPingJiaActivity.class, bundle1, false);
            }
        });*/
    }
}
