package com.chetu.engineer.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.Fragment2Model_DaiJieChe;
import com.chetu.engineer.model.OrderDetailModel;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.popupwindow.PhotoShowDialog;
import com.chetu.engineer.utils.CommonUtil;
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
 * Created by zyz on 2020/5/28.
 * 待接车
 */
public class DaiJieCheActivity extends BaseActivity {
    int type = 1;
    Fragment2Model_DaiJieChe.ListBean model;

    private LinearLayout linearLayout1, linearLayout2, ll_fuwu, ll_beizhu;
    private TextView textView1, textView2;
    private View view1, view2;

    //基本信息
    TextView tv_carnum, tv_name, tv_time, tv_carname, tv_carpailiang, tv_carnianfen, tv_carlicheng,
            tv_money, tv_beizhu,tv_address;

    //服务列表
    RecyclerView rv_service;
    CommonAdapter<Fragment2Model_DaiJieChe.ListBean.OrderServiceListBean> mAdapter_service;

    //其他商品
    RecyclerView rv_other;
    CommonAdapter<Fragment2Model_DaiJieChe.ListBean.OrderGoodsListBean> mAdapter_other;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daijieche);
        mImmersionBar.reset()
                .statusBarColor(R.color.background)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                Map<String, String> params = new HashMap<>();
                params.put("y_techn_sedan_id", model.getYTechnSedanId());
                params.put("u_token", localUserInfo.getToken());
                Request(params);
            }

            @Override
            public void onLoadmore() {
            }
        });
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        ll_fuwu = findViewByID_My(R.id.ll_fuwu);
        ll_beizhu = findViewByID_My(R.id.ll_beizhu);

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);

        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);

        //基本信息
        tv_carnum = findViewByID_My(R.id.tv_carnum);
        tv_name = findViewByID_My(R.id.tv_name);
        tv_time = findViewByID_My(R.id.tv_time);
        tv_carname = findViewByID_My(R.id.tv_carname);
        tv_carpailiang = findViewByID_My(R.id.tv_carpailiang);
        tv_carnianfen = findViewByID_My(R.id.tv_carnianfen);
        tv_carlicheng = findViewByID_My(R.id.tv_carlicheng);
        tv_money = findViewByID_My(R.id.tv_money);
        tv_beizhu = findViewByID_My(R.id.tv_beizhu);
        tv_address = findViewByID_My(R.id.tv_address);

        //服务列表
        rv_service = findViewByID_My(R.id.rv_service);
        rv_service.setLayoutManager(new LinearLayoutManager(this));
        //其他商品
        rv_other = findViewByID_My(R.id.rv_other);
        rv_other.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        //        requestServer();
        model = (Fragment2Model_DaiJieChe.ListBean) getIntent().getSerializableExtra("JieChe");
        //基本信息

        TextView tv_id = findViewByID_My(R.id.tv_id);
        tv_id.setText("订单编号："+model.getYOrderId());

        tv_name.setText(model.getUser_info().getUserName());//昵称
        if (!model.getAppoinTime().equals("")) {
            tv_time.setText("预约时间：" + model.getAppoinTime());//预约时间
        } else {
            tv_time.setText("客户到店");//预约时间
        }


        tv_address.setText("接车地址："+model.getDeliveryAddress());//接车地址

        if (model.getUser_sedan_info() !=null){
            tv_carnum.setText(model.getUser_sedan_info().getSNumber());//车牌

            tv_carname.setText(model.getUser_sedan_info().getBrandInfo().getSName());//车辆名称
            tv_carpailiang.setText("排量：" + model.getUser_sedan_info().getBrandInfo().getVDispla());
            tv_carnianfen.setText("年份：" + model.getUser_sedan_info().getBrandInfo().getVYear());
//        tv_carlicheng.setText("里程："+model.getUser_sedan_info().getBrandInfo().getVYear());
        }

        tv_money.setText("¥" + model.getGPrice());

        tv_beizhu.setText(model.getcMsg());//备注

        mAdapter_service = new CommonAdapter<Fragment2Model_DaiJieChe.ListBean.OrderServiceListBean>(
                this, R.layout.item_service, model.getOrder_service_list()) {
            @Override
            protected void convert(ViewHolder holder, Fragment2Model_DaiJieChe.ListBean.OrderServiceListBean bean, int position) {
                holder.getView(R.id.delete_service).setVisibility(View.GONE);
                holder.getView(R.id.addgoods).setVisibility(View.GONE);
                //信息
                holder.setText(R.id.servicename, bean.getServiceStr());
                holder.setText(R.id.servicemoney, "¥" + bean.getGPrice());
//                holder.setText(R.id.serviceallmoney, "¥" + bean.get);
                //商品列表
                RecyclerView rv_s = holder.getView(R.id.rv_s);
                LinearLayoutManager llm1 = new LinearLayoutManager(DaiJieCheActivity.this);
                llm1.setOrientation(LinearLayoutManager.VERTICAL);// 设置 recyclerview 布局方式为横向布局
                rv_s.setLayoutManager(llm1);
                CommonAdapter<Fragment2Model_DaiJieChe.ListBean.OrderServiceListBean.OrderGoodsListBeanX> ca_s =
                        new CommonAdapter<Fragment2Model_DaiJieChe.ListBean.OrderServiceListBean.OrderGoodsListBeanX>
                                (DaiJieCheActivity.this, R.layout.item_orderdetail_goods, bean.getOrder_goods_list()) {
                            @Override
                            protected void convert(ViewHolder holder, Fragment2Model_DaiJieChe.ListBean.OrderServiceListBean.OrderGoodsListBeanX model1, int p) {
                                holder.getView(R.id.delete_goods).setVisibility(View.GONE);
                                ImageView iv = holder.getView(R.id.iv);
                                Glide.with(DaiJieCheActivity.this).load(URLs.IMGHOST + model1.getGoods_info().getGImg())
                                        .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                        .placeholder(R.mipmap.loading)//加载站位图
                                        .error(R.mipmap.zanwutupian)//加载失败
                                        .into(iv);//加载图片
                                holder.setText(R.id.goodsname, model1.getGoods_info().getGName());
                                holder.setText(R.id.goodsguige, "商品规格：" + model1.getGoodsValue());
//                                        num = model1.getGNum();
//                                holder.setText(R.id.goodsnum, "x" + model1.getGNum());
                                holder.setText(R.id.goodsmoney, "¥" + model1.getGPrice());

                                //横向图片
                                String[] strArr = model1.getGoods_info().getImgStr().split("\\|\\|");
                                List<String> list_img = new ArrayList<>();
                                for (String s : strArr) {
                                    if (!s.equals("")) {
                                        list_img.add(URLs.IMGHOST + s);
                                    }
                                }
                                RecyclerView rv = holder.getView(R.id.rv);
                                LinearLayoutManager llm1 = new LinearLayoutManager(DaiJieCheActivity.this);
                                llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                                rv.setLayoutManager(llm1);
                                CommonAdapter<String> ca = new CommonAdapter<String>
                                        (DaiJieCheActivity.this, R.layout.item_img_80_80, list_img) {
                                    @Override
                                    protected void convert(ViewHolder holder, String model, int position) {
                                        ImageView iv = holder.getView(R.id.iv);
                                        Glide.with(DaiJieCheActivity.this).load(model)
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
                                        PhotoShowDialog photoShowDialog = new PhotoShowDialog(DaiJieCheActivity.this, list_img, i);
                                        photoShowDialog.show();
                                    }

                                    @Override
                                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                        return false;
                                    }
                                });
                                rv.setAdapter(ca);

                            }
                        };
                rv_s.setAdapter(ca_s);
            }
        };
        rv_service.setAdapter(mAdapter_service);

        mAdapter_other = new CommonAdapter<Fragment2Model_DaiJieChe.ListBean.OrderGoodsListBean>
                (DaiJieCheActivity.this, R.layout.item_orderdetail_goods, model.getOrder_goods_list()) {
            @Override
            protected void convert(ViewHolder holder, Fragment2Model_DaiJieChe.ListBean.OrderGoodsListBean model1, int p) {
                holder.getView(R.id.delete_goods).setVisibility(View.GONE);
                ImageView iv = holder.getView(R.id.iv);
                Glide.with(DaiJieCheActivity.this).load(URLs.IMGHOST + model1.getGoods_info().getGImg())
                        .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(iv);//加载图片
                holder.setText(R.id.goodsname, model1.getGoods_info().getGName());
                holder.setText(R.id.goodsguige, "商品规格：" + model1.getGoodsValue());
//                                        num = model1.getGNum();
//                                holder.setText(R.id.goodsnum, "x" + model1.getGNum());
                holder.setText(R.id.goodsmoney, "¥" + model1.getGPrice());

                //横向图片
                String[] strArr = model1.getGoods_info().getImgStr().split("\\|\\|");
                List<String> list_img = new ArrayList<>();
                for (String s : strArr) {
                    if (!s.equals("")) {
                        list_img.add(URLs.IMGHOST + s);
                    }
                }
                RecyclerView rv = holder.getView(R.id.rv);
                LinearLayoutManager llm1 = new LinearLayoutManager(DaiJieCheActivity.this);
                llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                rv.setLayoutManager(llm1);
                CommonAdapter<String> ca = new CommonAdapter<String>
                        (DaiJieCheActivity.this, R.layout.item_img_80_80, list_img) {
                    @Override
                    protected void convert(ViewHolder holder, String model, int position) {
                        ImageView iv = holder.getView(R.id.iv);
                        Glide.with(DaiJieCheActivity.this).load(model)
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
                        PhotoShowDialog photoShowDialog = new PhotoShowDialog(DaiJieCheActivity.this, list_img, i);
                        photoShowDialog.show();
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                rv.setAdapter(ca);

            }
        };
        rv_other.setAdapter(mAdapter_other);
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading));
        Map<String, String> params = new HashMap<>();
        params.put("y_techn_sedan_id", model.getYTechnSedanId());
        params.put("u_token", localUserInfo.getToken());
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.OrderDetail, params, headerMap, new CallBackUtil<OrderDetailModel>() {
            @Override
            public OrderDetailModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
                myToast(err);
            }

            @Override
            public void onResponse(OrderDetailModel response) {
                hideProgress();
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.linearLayout1:
                type = 1;
                changeUI();
                break;
            case R.id.linearLayout2:
                type = 2;
                changeUI();
                break;
            case R.id.iv_message:
                String url = URLs.KFHOST + "/#/pages/chetu-kf/chetu-kf?token=" + localUserInfo.getToken() +
                        "&receive_user_hash=" + model.getUser_info().getUserHash()+
                        "&nickName="+model.getUser_info().getUserName()+
                        "&headerPic="+ URLs.IMGHOST+model.getUser_info().getHeadPortrait();
                Bundle bundle = new Bundle();
                bundle.putString("url", url);
                CommonUtil.gotoActivityWithData(DaiJieCheActivity.this, WebContentActivity.class, bundle, false);
                break;
            case R.id.iv_call:
                //拨打电话
                showToast("确认拨打 " + model.getUser_info().getUserPhone() + " 吗？", "确认", "取消",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                //创建打电话的意图
                                Intent intent = new Intent();
                                //设置拨打电话的动作
                                intent.setAction(Intent.ACTION_CALL);//直接拨出电话
//                               intent.setAction(Intent.ACTION_DIAL);//只调用拨号界面，不拨出电话
                                //设置拨打电话的号码
                                intent.setData(Uri.parse("tel:" + model.getUser_info().getUserPhone()));
                                //开启打电话的意图
                                startActivity(intent);
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                break;
            case R.id.tv_confirm:
                //接车
                Intent intent = new Intent();
                intent.setClass(DaiJieCheActivity.this, AddJieCheActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("JieChe", model);
                intent.putExtras(bundle1);
                startActivityForResult(intent, 10002);
                /*Bundle bundle1 = new Bundle();
                bundle1.putSerializable("JieChe", model);
                CommonUtil.gotoActivityWithData(DaiJieCheActivity.this, AddJieCheActivity.class, bundle1, false);*/
                break;
        }
    }

    private void changeUI() {
        switch (type) {
            case 1:
                //服务
                textView1.setTextColor(getResources().getColor(R.color.blue));
                view1.setVisibility(View.VISIBLE);
                textView2.setTextColor(getResources().getColor(R.color.black));
                view2.setVisibility(View.INVISIBLE);
                ll_fuwu.setVisibility(View.VISIBLE);
                ll_beizhu.setVisibility(View.GONE);
                break;
            case 2:
                //备注
                textView1.setTextColor(getResources().getColor(R.color.black));
                view1.setVisibility(View.INVISIBLE);
                textView2.setTextColor(getResources().getColor(R.color.blue));
                view2.setVisibility(View.VISIBLE);
                ll_fuwu.setVisibility(View.GONE);
                ll_beizhu.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle("待接车");
        titleView.setBackground(R.color.background);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case 10002:
        //是否销毁页面
        if (data != null) {
            Bundle bundle1 = data.getExtras();
            boolean isfinish = bundle1.getBoolean("isfinish");
            if (isfinish) {
                finish();
            }
//                }
//                break;
        }

    }
}
