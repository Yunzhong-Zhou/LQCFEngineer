package com.chetu.engineer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.Fragment3Model;
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
 * Created by zyz on 2020/6/23.
 * 维修案例
 */
public class WeiXiuAnLiActivity extends BaseActivity {
    int page = 0;
    String keys = "", y_circle_id = "0";

    TextView tv_quanbu, tv_zuixin, tv_addr;
    private RecyclerView recyclerView;
    List<Fragment3Model.ListBean> list = new ArrayList<>();
    CommonAdapter<Fragment3Model.ListBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jishujiaoliu);
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
                params.put("keys", keys);
                params.put("y_circle_id", y_circle_id);//圈子id
                params.put("i_classify", "2");//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
                params.put("u_token", localUserInfo.getToken());
                Request(params);
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("page", page + "");
                params.put("keys", keys);
                params.put("y_circle_id", y_circle_id);//圈子id
                params.put("i_classify", "2");//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
                RequestMore(params);
            }
        });

        recyclerView = findViewByID_My(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        tv_quanbu = findViewByID_My(R.id.tv_quanbu);
        tv_zuixin = findViewByID_My(R.id.tv_zuixin);
        tv_addr = findViewByID_My(R.id.tv_addr);
        if (!localUserInfo.getCityname().equals("")) {
            tv_addr.setText(localUserInfo.getCityname());
        }
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
        params.put("page", page + "");
        params.put("keys", keys);
        params.put("y_circle_id", y_circle_id);//圈子id
        params.put("i_classify", "2");//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
        params.put("u_token", localUserInfo.getToken());
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment3, params, headerMap, new CallBackUtil<Fragment3Model>() {
            @Override
            public Fragment3Model onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(Fragment3Model response) {
                hideProgress();
                list = response.getList();
                if (list.size() > 0) {
                    showContentPage();
                    mAdapter = new CommonAdapter<Fragment3Model.ListBean>
                            (WeiXiuAnLiActivity.this, R.layout.item_weixiuanli, list) {
                        @Override
                        protected void convert(ViewHolder holder, Fragment3Model.ListBean model, int position) {
                            holder.setText(R.id.tv_name, model.getUser_info().getUserName());
//                            holder.setText(R.id.tv_addr, model.getUser_info().getUserName());

                            holder.setText(R.id.tv_title, model.getCase_info().getV_title());
                            holder.setText(R.id.tv_content, model.getCase_info().getV_text());

                            ImageView iv_head = holder.getView(R.id.iv_head);
                            Glide.with(WeiXiuAnLiActivity.this)
                                    .load(URLs.IMGHOST + model.getUser_info().getHeadPortrait())
                                    .centerCrop()
//                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(iv_head);//加载图片

                            ImageView iv = holder.getView(R.id.iv);
                            if (model.getCase_info().getImgArr().size() > 0) {
                                Glide.with(WeiXiuAnLiActivity.this)
                                        .load(URLs.IMGHOST + model.getCase_info().getImgArr().get(0))
                                        .centerCrop()
//                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                        .placeholder(R.mipmap.loading)//加载站位图
                                        .error(R.mipmap.zanwutupian)//加载失败
                                        .into(iv);//加载图片
                            }

                            //显示时间
                            TextView tv_time = holder.getView(R.id.tv_time);
                            tv_time.setText(model.getCreateDate());

                            /*try {
                                Date date = new Date();
                                SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                date = simple.parse(model.getCreateDate());
                                tv_time.setText(DateUtils.fromToday(date));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }*/
                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("WeiXiuAnLiDetail", list.get(i));
                            CommonUtil.gotoActivityWithData(WeiXiuAnLiActivity.this, WeiXiuAnLiDetailActivity.class, bundle, false);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                } else {
                    showEmptyPage();
                }
            }
        });
    }

    private void RequestMore(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment3, params, headerMap, new CallBackUtil<Fragment3Model>() {
            @Override
            public Fragment3Model onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(Fragment3Model response) {
                hideProgress();
                List<Fragment3Model.ListBean> list1 = new ArrayList<>();
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_addr:
                //选择地址
                break;
            case R.id.rl_search:
            case R.id.et_search:
                //搜索
                CommonUtil.gotoActivity(this, SearchActivity.class);
                break;
            case R.id.tv_quanbu:
                //全部
                tv_quanbu.setTextColor(getResources().getColor(R.color.black));
                tv_zuixin.setTextColor(getResources().getColor(R.color.black3));

                break;
            case R.id.tv_zuixin:
                //最新
                tv_quanbu.setTextColor(getResources().getColor(R.color.black3));
                tv_zuixin.setTextColor(getResources().getColor(R.color.black));

                break;

        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle("维修案例");
        titleView.showRightTextview("发布", R.color.blue, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.gotoActivity(WeiXiuAnLiActivity.this, AddWeiXiuAnLiActivity.class, false);
            }
        });
    }
}
