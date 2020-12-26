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
 * Created by zyz on 2020/6/23.
 * 店铺出租
 */
public class DianPuChuZuActivity extends BaseActivity {
    int page = 0;
    String keys = "", y_circle_id = "0";

    TextView tv_quanbu, tv_zuixin, tv_addr;
    private RecyclerView recyclerView;
    List<Fragment3Model.ListBean> list = new ArrayList<>();
    CommonAdapter<Fragment3Model.ListBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remenhuodong);
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
                params.put("i_classify", "3");//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
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
                params.put("i_classify", "3");//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
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
        params.put("i_classify", "3");//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
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
                            (DianPuChuZuActivity.this, R.layout.item_dianpuchuzu, list) {
                        @Override
                        protected void convert(ViewHolder holder, Fragment3Model.ListBean model, int position) {
                            holder.setText(R.id.tv_name, model.getUser_info().getUserName());
                            holder.setText(R.id.tv_time, model.getCreateDate());
                            holder.setText(R.id.tv_addr, model.getLease_info().getV_address());
                            holder.setText(R.id.tv_address, model.getLease_info().getV_address());
                            holder.setText(R.id.tv_title, model.getLease_info().getV_title());
                            holder.setText(R.id.tv_content, model.getLease_info().getV_text());
                            holder.setText(R.id.tv_money, model.getLease_info().getV_cost() + "/月");
                            holder.setText(R.id.tv_lianxiren, "联系人：" + model.getLease_info().getContacts());
                            holder.setText(R.id.tv_phonenum, "联系方式：" + model.getLease_info().getContact_infor());

                            ImageView iv = holder.getView(R.id.iv);
                            Glide.with(DianPuChuZuActivity.this)
                                    .load(URLs.IMGHOST + model.getUser_info().getHeadPortrait())
                                    .centerCrop()
//                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(iv);//加载图片

                            /*//显示时间
                            TextView tv_time = holder.getView(R.id.tv_time);
                            try {
                                Date date = new Date();
                                SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                date = simple.parse(model.getCreateDate());
                                tv_time.setText(DateUtils.fromToday(date));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }*/

                            //是否有图片
                            RecyclerView rv_tupian = holder.getView(R.id.rv_tupian);
                            if (model.getLease_info().getImgArr() != null && model.getLease_info().getImgArr().size() > 0) {
                                rv_tupian.setVisibility(View.VISIBLE);

                                LinearLayoutManager llm1 = new LinearLayoutManager(DianPuChuZuActivity.this);
                                llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                                rv_tupian.setLayoutManager(llm1);

                                ArrayList<String> images = new ArrayList<>();
                                for (String s : model.getLease_info().getImgArr()) {
                                    images.add(URLs.IMGHOST + s);
                                }
                                CommonAdapter<String> mAdapter_tupian = new CommonAdapter<String>
                                        (DianPuChuZuActivity.this, R.layout.item_img_110_110, images) {
                                    @Override
                                    protected void convert(ViewHolder holder, String model, int position) {
                                        ImageView iv = holder.getView(R.id.iv);
                                        Glide.with(DianPuChuZuActivity.this)
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

                                        PhotoShowDialog photoShowDialog = new PhotoShowDialog(DianPuChuZuActivity.this, images, i);
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


                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("DianPuChuZhuDetail", list.get(i));
                            CommonUtil.gotoActivityWithData(DianPuChuZuActivity.this, DianPuChuZhuDetailActivity.class, bundle, false);
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
        titleView.setTitle("店铺出租");
        titleView.showRightTextview("发布", R.color.blue, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.gotoActivity(DianPuChuZuActivity.this, AddDianPuChuZuActivity.class, false);
            }
        });
    }
}
