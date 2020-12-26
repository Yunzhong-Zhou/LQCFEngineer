package com.chetu.engineer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.ComplaintListModel;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.popupwindow.PhotoShowDialog;
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
 * Created by zyz on 2020/6/3.
 * 投诉记录
 */
public class ComplaintListActivity extends BaseActivity {
    //列表数据
    int page = 0;
    private RecyclerView recyclerView;
    List<ComplaintListModel.ListBean> list = new ArrayList<>();
    CommonAdapter<ComplaintListModel.ListBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaintlist);
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
                params.put("u_token", localUserInfo.getToken());
                params.put("page", page + "");
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

    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 0;
        Map<String, String> params = new HashMap<>();
        params.put("u_token", localUserInfo.getToken());
        params.put("page", page + "");
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ComplaintList, params, headerMap, new CallBackUtil<ComplaintListModel>() {
            @Override
            public ComplaintListModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
                myToast(err);
            }

            @Override
            public void onResponse(ComplaintListModel response) {
                hideProgress();
                list = response.getList();
                if (list.size() > 0) {
                    showContentPage();
                    mAdapter = new CommonAdapter<ComplaintListModel.ListBean>
                            (ComplaintListActivity.this, R.layout.item_complaintlist, list) {
                        @Override
                        protected void convert(ViewHolder holder, ComplaintListModel.ListBean model, int position) {
                            holder.setText(R.id.tv_time, model.getCreateDate());
                            holder.setText(R.id.tv_name, model.getUser_info().getUserName());

                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(ComplaintListActivity.this).load(URLs.IMGHOST + model.getUser_info().getHeadPortrait())
                                    .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(imageView1);//加载图片

                            holder.setText(R.id.tv_content,model.getVTitle());

                            //横向图片
                            if (model.getImgstr() !=null){
                                String[] strArr = model.getImgstr().split("\\|\\|");
                                List<String> list_img = new ArrayList<>();
                                for (String s : strArr) {
                                    if (!s.equals("")) {
                                        list_img.add(URLs.IMGHOST + s);
                                    }
                                }
                                RecyclerView rv = holder.getView(R.id.rv);
                                LinearLayoutManager llm1 = new LinearLayoutManager(ComplaintListActivity.this);
                                llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                                rv.setLayoutManager(llm1);
                                CommonAdapter<String> ca = new CommonAdapter<String>
                                        (ComplaintListActivity.this, R.layout.item_img_80_60, list_img) {
                                    @Override
                                    protected void convert(ViewHolder holder, String model, int position) {
                                        ImageView iv = holder.getView(R.id.iv);
                                        Glide.with(ComplaintListActivity.this).load(model)
                                                .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                                .placeholder(R.mipmap.loading)//加载站位图
                                                .error(R.mipmap.zanwutupian)//加载失败
                                                .into(iv);//加载图片
                                    }
                                };
                                ca.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                        PhotoShowDialog photoShowDialog = new PhotoShowDialog(ComplaintListActivity.this, list_img, i);
                                        photoShowDialog.show();
                                    }

                                    @Override
                                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                        return false;
                                    }
                                });
                                rv.setAdapter(ca);
                            }


                            holder.getView(R.id.tv_shenshu).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //申述
                                    Bundle bundle = new Bundle();
                                    bundle.putString("parent_id", list.get(position).getYComplaintId());
                                    bundle.putString("user_hash", list.get(position).getUser_info().getUserHash());
                                    CommonUtil.gotoActivityWithData(ComplaintListActivity.this, AppealActivity.class, bundle, false);
                                }
                            });


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
        OkhttpUtil.okHttpPost(URLs.ComplaintList, params, headerMap, new CallBackUtil<ComplaintListModel>() {
            @Override
            public ComplaintListModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(ComplaintListModel response) {
                hideProgress();
                List<ComplaintListModel.ListBean> list1 = new ArrayList<>();
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
        titleView.setTitle("投诉记录");
    }
}
