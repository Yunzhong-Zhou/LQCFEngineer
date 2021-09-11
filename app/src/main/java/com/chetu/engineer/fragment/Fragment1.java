package com.chetu.engineer.fragment;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chetu.engineer.R;
import com.chetu.engineer.activity.BaoJiaDetailActivity1;
import com.chetu.engineer.activity.GongJuZhuLinDetailActivity1;
import com.chetu.engineer.activity.GongJuZuLinActivity;
import com.chetu.engineer.activity.JiYouQiuZhuActivity;
import com.chetu.engineer.activity.MainActivity;
import com.chetu.engineer.activity.MyBaoJiaActivity;
import com.chetu.engineer.activity.PartActivity;
import com.chetu.engineer.activity.ProductDetailActivity;
import com.chetu.engineer.activity.ProductListActivity;
import com.chetu.engineer.activity.QiuZhiZhaoPingActivity;
import com.chetu.engineer.activity.QiuZhiZhaoPingDetailActivity;
import com.chetu.engineer.adapter.ImageAdapter;
import com.chetu.engineer.base.BaseFragment;
import com.chetu.engineer.model.Fragment1Model;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.popupwindow.PhotoShowDialog;
import com.chetu.engineer.utils.CommonUtil;
import com.liaoinstan.springview.widget.SpringView;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by fafukeji01 on 2016/1/6.
 * 首页
 */

public class Fragment1 extends BaseFragment {
    Banner banner;
    List<String> images = new ArrayList<>();
    int page1 = 0, page2 = 0;

    RelativeLayout rl_peijian, rl_qiuzhu;

    TextView tv_more1, tv_more2, tv_more3, tv_more4;
    RecyclerView recyclerView1;
    List<Fragment1Model.InquiryListBean> list1 = new ArrayList<>();
    CommonAdapter<Fragment1Model.InquiryListBean> mAdapter1;
    RecyclerView recyclerView2;
    List<Fragment1Model.GoodsListBean> list2 = new ArrayList<>();
    CommonAdapter<Fragment1Model.GoodsListBean> mAdapter2;
    RecyclerView recyclerView3;
    List<Fragment1Model.RecruitListBean> list3 = new ArrayList<>();
    CommonAdapter<Fragment1Model.RecruitListBean> mAdapter3;
    RecyclerView recyclerView4;
    List<Fragment1Model.ToolLeasingBean> list4 = new ArrayList<>();
    CommonAdapter<Fragment1Model.ToolLeasingBean> mAdapter4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.item == 0) {
            requestServer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        /*if (MainActivity.item == 0) {
            requestServer();
        }*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initView(View view) {
//        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
        //刷新
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page1 = 0;
                page2 = 0;
                Map<String, String> params = new HashMap<>();
                params.put("page", page1 + "");
                params.put("u_token", localUserInfo.getToken());
                Request(params);
            }

            @Override
            public void onLoadmore() {
            }
        });

        rl_peijian = findViewByID_My(R.id.rl_peijian);
        rl_peijian.setOnClickListener(this);
        rl_qiuzhu = findViewByID_My(R.id.rl_qiuzhu);
        rl_qiuzhu.setOnClickListener(this);

        banner = findViewByID_My(R.id.banner);

        recyclerView1 = findViewByID_My(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView2 = findViewByID_My(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView3 = findViewByID_My(R.id.recyclerView3);
        recyclerView3.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView4 = findViewByID_My(R.id.recyclerView4);
//        recyclerView4.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView4.setLayoutManager(new LinearLayoutManager(getActivity()));

        tv_more1 = findViewByID_My(R.id.tv_more1);
        tv_more1.setOnClickListener(this);
        tv_more2 = findViewByID_My(R.id.tv_more2);
        tv_more2.setOnClickListener(this);
        tv_more3 = findViewByID_My(R.id.tv_more3);
        tv_more3.setOnClickListener(this);
        tv_more4 = findViewByID_My(R.id.tv_more4);
        tv_more4.setOnClickListener(this);


    }

    @Override
    protected void initData() {
//        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page1 = 0;
        page2 = 0;
        Map<String, String> params = new HashMap<>();
        params.put("u_token", localUserInfo.getToken());
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment1, params, headerMap, new CallBackUtil<Fragment1Model>() {
            @Override
            public Fragment1Model onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
                myToast(err);
            }

            @Override
            public void onResponse(Fragment1Model response) {
                hideProgress();
                /**
                 * banner
                 */
                /*images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
                images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");*/
                images.clear();
                for (int i = 0; i < response.getBanner_list().size(); i++) {
                    images.add(URLs.IMGHOST + response.getBanner_list().get(i).getImgurl());
                }
                banner.addBannerLifecycleObserver(getActivity())//添加生命周期观察者
                        .setDelayTime(3000)//设置轮播时间
//                .setBannerGalleryEffect(10, 10)//为banner添加画廊效果
                        .setAdapter(new ImageAdapter(images))
                        .setIndicator(new CircleIndicator(getActivity()))
                        .start();
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(Object data, int position) {
                        PhotoShowDialog photoShowDialog = new PhotoShowDialog(getActivity(), images, position);
                        photoShowDialog.show();
                    }
                });

                /**
                 * 客户需求
                 */
                list1 = response.getInquiry_list();
                mAdapter1 = new CommonAdapter<Fragment1Model.InquiryListBean>
                        (getActivity(), R.layout.item_fragment1_1, list1) {
                    @Override
                    protected void convert(ViewHolder holder, Fragment1Model.InquiryListBean model, int position) {
                        holder.setText(R.id.tv_name, model.getUser_info().getUserName());
                        holder.setText(R.id.tv_title, "项目名称：" + model.getServiceName());
                        holder.setText(R.id.tv_time, "发布时间：" + model.getCreateDate());
                        ImageView iv = holder.getView(R.id.iv);
                        Glide.with(getActivity())
                                .load(URLs.IMGHOST + model.getUser_info().getHeadPortrait())
                                .centerCrop()
//                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                .placeholder(R.mipmap.loading)//加载站位图
                                .error(R.mipmap.zanwutupian)//加载失败
                                .into(iv);//加载图片
                    }
                };
                mAdapter1.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("detail",list1.get(i));
                        CommonUtil.gotoActivityWithData(getActivity(), BaoJiaDetailActivity1.class,bundle,false);
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                recyclerView1.setAdapter(mAdapter1);
                /**
                 * 特价商品
                 */
                list2 = response.getGoods_list();
                mAdapter2 = new CommonAdapter<Fragment1Model.GoodsListBean>
                        (getActivity(), R.layout.item_fragment1_gridview1, list2) {
                    @Override
                    protected void convert(ViewHolder holder, Fragment1Model.GoodsListBean model, int position) {
                        TextView tv3 = holder.getView(R.id.textView3);
                        tv3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//中划线
                        tv3.getPaint().setAntiAlias(true); //去掉锯齿
                        tv3.setText("¥" + model.getOrPrice());

                        holder.setText(R.id.textView1, model.getGName());
                        holder.setText(R.id.textView2, "¥" + model.getGPrice() + "");
                        ImageView imageView = holder.getView(R.id.imageView);
                        Glide.with(getActivity()).load(URLs.IMGHOST + model.getGImg())
                                .centerCrop()
                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(CommonUtil.dip2px(getActivity(),5))))
                                .placeholder(R.mipmap.loading)//加载站位图
                                .error(R.mipmap.zanwutupian)//加载失败
                                .into(imageView);//加载图片
                    }
                };
                mAdapter2.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        Bundle bundle = new Bundle();
                        bundle.putString("y_goods_id", list2.get(i).getYGoodsId());
                        CommonUtil.gotoActivityWithData(getActivity(), ProductDetailActivity.class, bundle, false);
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                recyclerView2.setAdapter(mAdapter2);

                /**
                 * 交流圈 item_fragment1_3 -求职招聘 item_qiuzhizhaoping
                 */
                list3 = response.getRecruit_list();
                mAdapter3 = new CommonAdapter<Fragment1Model.RecruitListBean>
                        (getActivity(), R.layout.item_qiuzhizhaoping, list3) {
                    @Override
                    protected void convert(ViewHolder holder, Fragment1Model.RecruitListBean model, int position) {
                        /*ImageView imageView = holder.getView(R.id.imageView);
                        Glide.with(getActivity()).load(URLs.IMGHOST + model.getVLogo())
                                .centerCrop()
                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(CommonUtil.dip2px(getActivity(),5))))
                                .placeholder(R.mipmap.loading)//加载站位图
                                .error(R.mipmap.zanwutupian)//加载失败
                                .into(imageView);//加载图片
                        holder.setText(R.id.textView1, model.getTName());
                        holder.setText(R.id.textView2, model.getSum() + "人参与");

                        holder.getView(R.id.iv_add).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //添加
                                showToast("确认加入该交流圈吗？", "确认", "取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                        showProgress(true,getString(R.string.app_loading1));
                                        Map<String, String> params = new HashMap<>();
                                        params.put("y_circle_id", model.getYCircleId());
                                        params.put("u_token", localUserInfo.getToken());
                                        RequestUpData(params);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                            }
                        });*/

                        holder.setText(R.id.tv_title, model.getV_title());
//                            holder.setText(R.id.tv_money, model.getRecruit_info().getHandsOn() + "-" + model.getRecruit_info().getSalary() + "元");
                        holder.setText(R.id.tv_money, model.getRecruit_info().getSalary() + "元");
                        holder.setText(R.id.tv_phonenum, model.getRecruit_info().getTelephone());
                        holder.setText(R.id.tv_name, model.getRecruit_info().getNameStore());
                        holder.setText(R.id.tv_addr, "上班地址：" + model.getRecruit_info().getAddress());
                        holder.setText(R.id.tv_time, model.getCreateDate());
                    }
                };
                mAdapter3.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("QiuZhiZhaoPingDetail", list3.get(i));
                        CommonUtil.gotoActivityWithData(getActivity(), QiuZhiZhaoPingDetailActivity.class, bundle, false);
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                recyclerView3.setAdapter(mAdapter3);

                /**
                 * 热门活动 item_fragment1_gridview2 - 工具租赁 item_gongjuzulin
                 */
                list4 = response.getTool_leasing();
                mAdapter4 = new CommonAdapter<Fragment1Model.ToolLeasingBean>
                        (getActivity(), R.layout.item_gongjuzulin, list4) {
                    @Override
                    protected void convert(ViewHolder holder, Fragment1Model.ToolLeasingBean model, int position) {
                        /*holder.setText(R.id.textView1, model.getActivity_info().getV_title());
                        ImageView imageView = holder.getView(R.id.imageView);
                        if (model.getActivity_info().getImgArr().size() > 0)
                            Glide.with(getActivity()).load(URLs.IMGHOST + model.getActivity_info().getImgArr().get(0))
                                    .centerCrop()
                                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(14)))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(imageView);//加载图片*/

                        holder.setText(R.id.tv_name, model.getUser_info().getUserName());
//                            holder.setText(R.id.tv_addr, model.getUser_info().getUserName());

                        holder.setText(R.id.tv_title, model.getTool_info().getV_title());
                        holder.setText(R.id.tv_money, "" + model.getTool_info().getV_price());
                        holder.setText(R.id.tv_longtime, "出租时长：" + model.getTool_info().getV_duration());
                        ImageView iv_head = holder.getView(R.id.iv_head);
                        Glide.with(getActivity())
                                .load(URLs.IMGHOST + model.getUser_info().getHeadPortrait())
                                .centerCrop()
//                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                .placeholder(R.mipmap.loading)//加载站位图
                                .error(R.mipmap.zanwutupian)//加载失败
                                .into(iv_head);//加载图片

                        ImageView iv = holder.getView(R.id.iv);
                        if (model.getTool_info().getImgArr().size() > 0) {
                            Glide.with(getActivity())
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
                mAdapter4.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("GongJuZhuLinDetail", list4.get(i));
                        CommonUtil.gotoActivityWithData(getActivity(), GongJuZhuLinDetailActivity1.class, bundle, false);
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                recyclerView4.setAdapter(mAdapter4);
                /*if (list1.size() > 0) {
                    showContentPage();
                } else {
                    showEmptyPage();
                }*/
            }
        });
    }

    private void RequestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.JiShuJiaoLiuDetail_JiaRu, params, headerMap, new CallBackUtil() {
            @Override
            public Object onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                if (!err.equals("")) {
                    showToast(err);
                }
            }

            @Override
            public void onResponse(Object response) {
                hideProgress();
                /*showToast("回复成功", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        requestServer();
                    }
                });*/
                myToast("加入成功");
                requestServer();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_peijian:
                //配件
                CommonUtil.gotoActivity(getActivity(), PartActivity.class);
                break;
            case R.id.rl_qiuzhu:
                //求助
                CommonUtil.gotoActivity(getActivity(), JiYouQiuZhuActivity.class);
                break;
            case R.id.tv_more1:
                //客户需求-更多1
                if (list1.size() > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putString("y_store_id", list1.get(0).getUser_info().getYStoreId());
                    CommonUtil.gotoActivityWithData(getActivity(), MyBaoJiaActivity.class, bundle, false);
                } else {
                    myToast("没有更多数据了");
                }

                break;
            case R.id.tv_more2:
                //特价商品-更多2
                if (list2.size() > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", list2.get(0).getYClassifyId());
                    CommonUtil.gotoActivityWithData(getActivity(), ProductListActivity.class, bundle);
                } else {
                    myToast("没有更多数据了");
                }

                break;
            case R.id.tv_more3:
                //求职招聘-更多3
                CommonUtil.gotoActivity(getActivity(), QiuZhiZhaoPingActivity.class);
//                CommonUtil.gotoActivity(getActivity(), JiShuJiaoLiuActivity.class);
                break;
            case R.id.tv_more4:
                //工具租赁-更多4
                CommonUtil.gotoActivity(getActivity(), GongJuZuLinActivity.class);
//                CommonUtil.gotoActivity(getActivity(), ReMenHuoDongActivity.class);
                break;


        }
    }

    @Override
    protected void updateView() {

    }

}
