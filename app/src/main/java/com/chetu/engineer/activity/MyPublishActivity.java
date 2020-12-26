package com.chetu.engineer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.Fragment3Model;
import com.chetu.engineer.model.Fragment3Model_tab;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.popupwindow.PhotoShowDialog;
import com.chetu.engineer.utils.DateUtils;
import com.liaoinstan.springview.widget.SpringView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/6/5.
 * 我的发布
 */
public class MyPublishActivity extends BaseActivity {
    int page = 0;
    int item = 0;
    RecyclerView rv_tab1;
    List<Fragment3Model_tab> list_tab = new ArrayList<>();
    CommonAdapter<Fragment3Model_tab> mAdapter_tab1;

    String i_classify = "8";//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
    RecyclerView recyclerView;
    List<Fragment3Model.ListBean> list = new ArrayList<>();
    CommonAdapter<Fragment3Model.ListBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypublish);
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
                params.put("i_classify", i_classify);//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
                params.put("u_token", localUserInfo.getToken());
                Request(params);
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("page", page + "");
                params.put("i_classify", i_classify);//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
                RequestMore(params);
            }
        });
    }

    @Override
    protected void initData() {
        rv_tab1 = findViewByID_My(R.id.rv_tab1);
        LinearLayoutManager llm1 = new LinearLayoutManager(MyPublishActivity.this);
        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        rv_tab1.setLayoutManager(llm1);

        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyPublishActivity.this));

        list_tab.add(new Fragment3Model_tab(R.mipmap.ic_fragment3_1, "热门活动"));
        list_tab.add(new Fragment3Model_tab(R.mipmap.ic_fragment3_2, "求职招聘"));
        list_tab.add(new Fragment3Model_tab(R.mipmap.ic_fragment3_3, "交流圈"));
        list_tab.add(new Fragment3Model_tab(R.mipmap.ic_fragment3_4, "维修案例"));
        list_tab.add(new Fragment3Model_tab(R.mipmap.ic_fragment3_5, "机友求助"));
        list_tab.add(new Fragment3Model_tab(R.mipmap.ic_fragment3_6, "工具租赁"));
        list_tab.add(new Fragment3Model_tab(R.mipmap.ic_fragment3_7, "店铺出租"));
        list_tab.add(new Fragment3Model_tab(R.mipmap.ic_fragment3_8, "库存配件"));
        mAdapter_tab1 = new CommonAdapter<Fragment3Model_tab>
                (MyPublishActivity.this, R.layout.item_fragment3_tab1, list_tab) {
            @Override
            protected void convert(ViewHolder holder, Fragment3Model_tab model, int position) {
                TextView textView = holder.getView(R.id.textView);
                textView.setText(model.getName());
                View view = holder.getView(R.id.view);
                if (item == position) {
                    textView.setTextColor(getResources().getColor(R.color.blue));
                    view.setVisibility(View.VISIBLE);
                } else {
                    textView.setTextColor(getResources().getColor(R.color.black));
                    view.setVisibility(View.INVISIBLE);
                }

            }
        };
        mAdapter_tab1.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                item = i;
                mAdapter_tab1.notifyDataSetChanged();
                switch (list_tab.get(i).getName()) {
                    case "热门活动":
                        i_classify = "8";//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
                        break;
                    case "求职招聘":
                        i_classify = "1";//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
                        break;
                    case "交流圈":
                        i_classify = "7";//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
                        break;
                    case "维修案例":
                        i_classify = "2";//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
                        break;
                    case "机友求助":
                        i_classify = "6";//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
                        break;
                    case "工具租赁":
                        i_classify = "5";//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
                        break;
                    case "店铺出租":
                        i_classify = "3";//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
                        break;
                    case "库存配件":
                        i_classify = "4";//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
                        break;
                }
                requestServer();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        rv_tab1.setAdapter(mAdapter_tab1);

        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 0;
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("i_classify", i_classify);//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
        params.put("u_token", localUserInfo.getToken());
        Request(params);
    }
    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.MyPublish, params, headerMap, new CallBackUtil<Fragment3Model>() {
            @Override
            public Fragment3Model onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
                myToast(err);
            }

            @Override
            public void onResponse(Fragment3Model response) {
                hideProgress();

                list = response.getList();

                if (list.size() > 0) {
                    showContentPage();
                    switch (i_classify){//1为招聘 2为案例 3为店铺出租 4为二手配件 5为工具租赁 6为机友求助 7为技术交流 8为活动
                        case "8":
                            //热门活动
                            mAdapter = new CommonAdapter<Fragment3Model.ListBean>
                                    (MyPublishActivity.this, R.layout.item_remenhuodong, list) {
                                @Override
                                protected void convert(ViewHolder holder, Fragment3Model.ListBean model, int position) {
                                    holder.setText(R.id.tv_title, model.getV_title());
                                    holder.setText(R.id.tv_type, "类型：" + model.getActivity_info().getV_type());
                                    holder.setText(R.id.tv_name, "发起者：" + model.getUser_info().getUserName());
                                    holder.setText(R.id.tv_starttime, "开始时间：" + model.getActivity_info().getV_start_time());
                                    holder.setText(R.id.tv_endtime, "结束时间：" + model.getActivity_info().getV_end_time());
                                    holder.setText(R.id.tv_addr, model.getActivity_info().getV_place());
                                    holder.setText(R.id.tv_content, model.getActivity_info().getV_content());
                                    ImageView iv = holder.getView(R.id.iv);
                                    if (model.getActivity_info().getImgArr().size() > 0) {
                                        Glide.with(MyPublishActivity.this)
                                                .load(URLs.IMGHOST + model.getActivity_info().getImgArr().get(0))
                                                .centerCrop()
//                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                                .placeholder(R.mipmap.loading)//加载站位图
                                                .error(R.mipmap.zanwutupian)//加载失败
                                                .into(iv);//加载图片
                                    }

                                }
                            };
                            break;
                        case "1":
                            //求职招聘
                            mAdapter = new CommonAdapter<Fragment3Model.ListBean>
                                    (MyPublishActivity.this, R.layout.item_qiuzhizhaoping, list) {
                                @Override
                                protected void convert(ViewHolder holder, Fragment3Model.ListBean model, int position) {
                                    holder.setText(R.id.tv_title, model.getV_title());
//                            holder.setText(R.id.tv_money, model.getRecruit_info().getHandsOn() + "-" + model.getRecruit_info().getSalary() + "元");
                                    holder.setText(R.id.tv_money, model.getRecruit_info().getSalary() + "元");
                                    holder.setText(R.id.tv_phonenum, model.getRecruit_info().getTelephone());
                                    holder.setText(R.id.tv_name, model.getRecruit_info().getNameStore());
                                    holder.setText(R.id.tv_addr, "上班地址：" + model.getRecruit_info().getAddress());
                                    holder.setText(R.id.tv_time, model.getCreateDate());
                                }
                            };

                            break;
                        case "7":
                            //交流圈
                            mAdapter = new CommonAdapter<Fragment3Model.ListBean>
                                    (MyPublishActivity.this, R.layout.item_jishujiaoliu, list) {
                                @Override
                                protected void convert(ViewHolder holder, Fragment3Model.ListBean model, int position) {
                                    holder.setText(R.id.tv_title, model.getExchange_info().getV_title());
                                    holder.setText(R.id.tv_text, model.getUser_info().getUserName());
                                    holder.setText(R.id.tv_content, model.getExchange_info().getV_describe());
                                    holder.setText(R.id.tv_shouchang, "" + model.getILike());
                                    holder.setText(R.id.tv_xihuan, "" + model.getIGive());
                                    ImageView iv = holder.getView(R.id.iv);
                                    Glide.with(MyPublishActivity.this)
                                            .load(URLs.IMGHOST + model.getUser_info().getHeadPortrait())
                                            .centerCrop()
//                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                            .placeholder(R.mipmap.loading)//加载站位图
                                            .error(R.mipmap.zanwutupian)//加载失败
                                            .into(iv);//加载图片

                                    //显示时间
                                    TextView tv_time = holder.getView(R.id.tv_time);
                                    try {
                                        Date date = new Date();
                                        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        date = simple.parse(model.getCreateDate());
                                        tv_time.setText(DateUtils.fromToday(date));
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                    //是否有图片
                                    RecyclerView rv_tupian = holder.getView(R.id.rv_tupian);
                                    if (model.getExchange_info().getImgArr() != null && model.getExchange_info().getImgArr().size() > 0) {
                                        rv_tupian.setVisibility(View.VISIBLE);

                                        LinearLayoutManager llm1 = new LinearLayoutManager(MyPublishActivity.this);
                                        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                                        rv_tupian.setLayoutManager(llm1);

                                        ArrayList<String> images = new ArrayList<>();
                                        for (String s : model.getExchange_info().getImgArr()) {
                                            images.add(URLs.IMGHOST + s);
                                        }
                                        CommonAdapter<String> mAdapter_tupian = new CommonAdapter<String>
                                                (MyPublishActivity.this, R.layout.item_img_110_110, images) {
                                            @Override
                                            protected void convert(ViewHolder holder, String model, int position) {
                                                ImageView iv = holder.getView(R.id.iv);
                                                Glide.with(MyPublishActivity.this)
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

                                                PhotoShowDialog photoShowDialog = new PhotoShowDialog(MyPublishActivity.this, images, i);
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
                            break;
                        case "2":
                            //维修案例
                            mAdapter = new CommonAdapter<Fragment3Model.ListBean>
                                    (MyPublishActivity.this, R.layout.item_weixiuanli, list) {
                                @Override
                                protected void convert(ViewHolder holder, Fragment3Model.ListBean model, int position) {
                                    holder.setText(R.id.tv_name, model.getUser_info().getUserName());
//                            holder.setText(R.id.tv_addr, model.getUser_info().getUserName());

                                    holder.setText(R.id.tv_title, model.getCase_info().getV_title());
                                    holder.setText(R.id.tv_content, model.getCase_info().getV_text());

                                    ImageView iv_head = holder.getView(R.id.iv_head);
                                    Glide.with(MyPublishActivity.this)
                                            .load(URLs.IMGHOST + model.getUser_info().getHeadPortrait())
                                            .centerCrop()
//                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                            .placeholder(R.mipmap.loading)//加载站位图
                                            .error(R.mipmap.zanwutupian)//加载失败
                                            .into(iv_head);//加载图片

                                    ImageView iv = holder.getView(R.id.iv);
                                    if (model.getCase_info().getImgArr().size() > 0) {
                                        Glide.with(MyPublishActivity.this)
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

                                }
                            };
                            break;
                        case "6":
                            //机友求助
                            mAdapter = new CommonAdapter<Fragment3Model.ListBean>
                                    (MyPublishActivity.this, R.layout.item_jiyouqiuzhu, list) {
                                @Override
                                protected void convert(ViewHolder holder, Fragment3Model.ListBean model, int position) {
                                    holder.setText(R.id.tv_name, model.getUser_info().getUserName());
                                    holder.setText(R.id.tv_time, model.getCreateDate());
                                    holder.setText(R.id.tv_addr, model.getHelp_info().getV_address());
                                    holder.setText(R.id.tv_title, model.getHelp_info().getV_title());
                                    holder.setText(R.id.tv_content, model.getHelp_info().getV_text());
                                    ImageView iv = holder.getView(R.id.iv);
                                    Glide.with(MyPublishActivity.this)
                                            .load(URLs.IMGHOST + model.getUser_info().getHeadPortrait())
                                            .centerCrop()
//                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                            .placeholder(R.mipmap.loading)//加载站位图
                                            .error(R.mipmap.zanwutupian)//加载失败
                                            .into(iv);//加载图片
                                    //是否有图片
                                    RecyclerView rv_tupian = holder.getView(R.id.rv_tupian);
                                    if (model.getHelp_info().getImgArr() != null && model.getHelp_info().getImgArr().size() > 0) {
                                        rv_tupian.setVisibility(View.VISIBLE);

                                        LinearLayoutManager llm1 = new LinearLayoutManager(MyPublishActivity.this);
                                        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                                        rv_tupian.setLayoutManager(llm1);

                                        ArrayList<String> images = new ArrayList<>();
                                        for (String s : model.getHelp_info().getImgArr()) {
                                            images.add(URLs.IMGHOST + s);
                                        }
                                        CommonAdapter<String> mAdapter_tupian = new CommonAdapter<String>
                                                (MyPublishActivity.this, R.layout.item_img_110_110, images) {
                                            @Override
                                            protected void convert(ViewHolder holder, String model, int position) {
                                                ImageView iv = holder.getView(R.id.iv);
                                                Glide.with(MyPublishActivity.this)
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

                                                PhotoShowDialog photoShowDialog = new PhotoShowDialog(MyPublishActivity.this, images, i);
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
                            break;
                        case "5":
                            //工具租赁
                            mAdapter = new CommonAdapter<Fragment3Model.ListBean>
                                    (MyPublishActivity.this, R.layout.item_gongjuzulin, list) {
                                @Override
                                protected void convert(ViewHolder holder, Fragment3Model.ListBean model, int position) {
                                    holder.setText(R.id.tv_name, model.getUser_info().getUserName());
//                            holder.setText(R.id.tv_addr, model.getUser_info().getUserName());

                                    holder.setText(R.id.tv_title, model.getTool_info().getV_title());
                                    holder.setText(R.id.tv_money, "" + model.getTool_info().getV_price());
                                    holder.setText(R.id.tv_longtime, "出租时长：" + model.getTool_info().getV_duration());
                                    ImageView iv_head = holder.getView(R.id.iv_head);
                                    Glide.with(MyPublishActivity.this)
                                            .load(URLs.IMGHOST + model.getUser_info().getHeadPortrait())
                                            .centerCrop()
//                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                            .placeholder(R.mipmap.loading)//加载站位图
                                            .error(R.mipmap.zanwutupian)//加载失败
                                            .into(iv_head);//加载图片

                                    ImageView iv = holder.getView(R.id.iv);
                                    if (model.getTool_info().getImgArr().size() > 0) {
                                        Glide.with(MyPublishActivity.this)
                                                .load(URLs.IMGHOST + model.getTool_info().getImgArr().get(0))
                                                .centerCrop()
//                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                                .placeholder(R.mipmap.loading)//加载站位图
                                                .error(R.mipmap.zanwutupian)//加载失败
                                                .into(iv);//加载图片
                                    }

                                    //显示时间
                                    TextView tv_time = holder.getView(R.id.tv_time);
                                    tv_time.setText(model.getCreateDate());
                                }
                            };
                            break;
                        case "3":
                            //店铺出租
                            mAdapter = new CommonAdapter<Fragment3Model.ListBean>
                                    (MyPublishActivity.this, R.layout.item_dianpuchuzu, list) {
                                @Override
                                protected void convert(ViewHolder holder, Fragment3Model.ListBean model, int position) {
                                    holder.setText(R.id.tv_name, model.getUser_info().getUserName());
                                    holder.setText(R.id.tv_time, model.getCreateDate());
                                    holder.setText(R.id.tv_addr, model.getLease_info().getV_address());
                                    holder.setText(R.id.tv_address, model.getLease_info().getV_address());
                                    holder.setText(R.id.tv_title, model.getLease_info().getV_title());
                                    holder.setText(R.id.tv_content, model.getLease_info().getV_text());
                                    holder.setText(R.id.tv_money, model.getLease_info().getV_cost() + "/月");
                                    holder.setText(R.id.tv_lianxiren, "联系人：" + model.getUser_info().getUserName());
                                    holder.setText(R.id.tv_phonenum, "联系方式：" + model.getUser_info().getUserPhone());

                                    ImageView iv = holder.getView(R.id.iv);
                                    Glide.with(MyPublishActivity.this)
                                            .load(URLs.IMGHOST + model.getUser_info().getHeadPortrait())
                                            .centerCrop()
//                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                            .placeholder(R.mipmap.loading)//加载站位图
                                            .error(R.mipmap.zanwutupian)//加载失败
                                            .into(iv);//加载图片

                                    //是否有图片
                                    RecyclerView rv_tupian = holder.getView(R.id.rv_tupian);
                                    if (model.getLease_info().getImgArr() != null && model.getLease_info().getImgArr().size() > 0) {
                                        rv_tupian.setVisibility(View.VISIBLE);

                                        LinearLayoutManager llm1 = new LinearLayoutManager(MyPublishActivity.this);
                                        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                                        rv_tupian.setLayoutManager(llm1);

                                        ArrayList<String> images = new ArrayList<>();
                                        for (String s : model.getLease_info().getImgArr()) {
                                            images.add(URLs.IMGHOST + s);
                                        }
                                        CommonAdapter<String> mAdapter_tupian = new CommonAdapter<String>
                                                (MyPublishActivity.this, R.layout.item_img_110_110, images) {
                                            @Override
                                            protected void convert(ViewHolder holder, String model, int position) {
                                                ImageView iv = holder.getView(R.id.iv);
                                                Glide.with(MyPublishActivity.this)
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

                                                PhotoShowDialog photoShowDialog = new PhotoShowDialog(MyPublishActivity.this, images, i);
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
                            break;
                        case "4":
                            //库存配件
                            mAdapter = new CommonAdapter<Fragment3Model.ListBean>
                                    (MyPublishActivity.this, R.layout.item_kucunpeijian, list) {
                                @Override
                                protected void convert(ViewHolder holder, Fragment3Model.ListBean model, int position) {
                                    holder.setText(R.id.tv_name, model.getUser_info().getUserName());
                                    holder.setText(R.id.tv_time, model.getCreateDate());
//                            holder.setText(R.id.tv_addr, model.getParts_info().get);
                                    holder.setText(R.id.tv_title, model.getParts_info().getV_title());
                                    holder.setText(R.id.tv_content, model.getParts_info().getV_text());
                                    holder.setText(R.id.tv_money, model.getParts_info().getV_price());
                                    holder.setText(R.id.tv_date, "购入日期：" + model.getParts_info().getV_bring());

                                    ImageView iv = holder.getView(R.id.iv);
                                    Glide.with(MyPublishActivity.this)
                                            .load(URLs.IMGHOST + model.getUser_info().getHeadPortrait())
                                            .centerCrop()
//                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                            .placeholder(R.mipmap.loading)//加载站位图
                                            .error(R.mipmap.zanwutupian)//加载失败
                                            .into(iv);//加载图片

                                    //是否有图片
                                    RecyclerView rv_tupian = holder.getView(R.id.rv_tupian);
                                    if (model.getParts_info().getImgArr() != null && model.getParts_info().getImgArr().size() > 0) {
                                        rv_tupian.setVisibility(View.VISIBLE);

                                        LinearLayoutManager llm1 = new LinearLayoutManager(MyPublishActivity.this);
                                        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                                        rv_tupian.setLayoutManager(llm1);

                                        ArrayList<String> images = new ArrayList<>();
                                        for (String s : model.getParts_info().getImgArr()) {
                                            images.add(URLs.IMGHOST + s);
                                        }
                                        CommonAdapter<String> mAdapter_tupian = new CommonAdapter<String>
                                                (MyPublishActivity.this, R.layout.item_img_110_110, images) {
                                            @Override
                                            protected void convert(ViewHolder holder, String model, int position) {
                                                ImageView iv = holder.getView(R.id.iv);
                                                Glide.with(MyPublishActivity.this)
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

                                                PhotoShowDialog photoShowDialog = new PhotoShowDialog(MyPublishActivity.this, images, i);
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
                            break;
                    }
                    recyclerView.setAdapter(mAdapter);

                } else {
                    showEmptyPage();
                }
            }
        });

    }

    private void RequestMore(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.MyPublish, params, headerMap, new CallBackUtil<Fragment3Model>() {
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
    protected void updateView() {
        titleView.setTitle("我的发布");
    }
}
