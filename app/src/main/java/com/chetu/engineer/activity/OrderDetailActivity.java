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
import com.chetu.engineer.model.OrderDetailModel;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.popupwindow.PhotoShowDialog;
import com.chetu.engineer.utils.CommonUtil;
import com.chetu.engineer.utils.MyLogger;
import com.cretin.tools.scancode.CaptureActivity;
import com.cretin.tools.scancode.config.ScanConfig;
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
 * 订单详情
 */
public class OrderDetailActivity extends BaseActivity {
    String y_techn_sedan_id;
    int type = 1;
    OrderDetailModel model;

    private LinearLayout linearLayout1, linearLayout2, linearLayout3, ll_fuwu, ll_jiance, ll_beizhu;
    private TextView textView1, textView2, textView3;
    private View view1, view2, view3;

    //基本信息
    TextView tv_carnum, tv_name, tv_time, tv_carname, tv_carpailiang, tv_carnianfen, tv_carlicheng,
            tv_money, tv_beizhu, tv_jiechename;
    TextView tv_allmoney, tv_confirm, tv_fukuan, tv_addservice, tv_addgoods, tv_addproject;

    //服务列表
    RecyclerView rv_service;
    CommonAdapter<OrderDetailModel.OrderInfoBean.OrderServiceListBean> mAdapter_service;

    //其他商品
    RecyclerView rv_other;
    CommonAdapter<OrderDetailModel.OrderInfoBean.OrderGoodsListBean> mAdapter_other;

    //检测项目
    RecyclerView rv_jiance;
    CommonAdapter<OrderDetailModel.TestingDetailsListBean> mAdapter_jiance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetail);
        mImmersionBar.reset()
                .statusBarColor(R.color.background)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestServer();
    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                Map<String, String> params = new HashMap<>();
                params.put("y_techn_sedan_id", y_techn_sedan_id);
                params.put("u_token", localUserInfo.getToken());
                Request(params);
            }

            @Override
            public void onLoadmore() {
            }
        });
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout3 = findViewByID_My(R.id.linearLayout3);
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);

        ll_fuwu = findViewByID_My(R.id.ll_fuwu);
        ll_beizhu = findViewByID_My(R.id.ll_beizhu);
        ll_jiance = findViewByID_My(R.id.ll_jiance);

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        view3 = findViewByID_My(R.id.view3);

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
        tv_jiechename = findViewByID_My(R.id.tv_jiechename);

        tv_allmoney = findViewByID_My(R.id.tv_allmoney);
        tv_confirm = findViewByID_My(R.id.tv_confirm);
        tv_fukuan = findViewByID_My(R.id.tv_fukuan);

        tv_addservice = findViewByID_My(R.id.tv_addservice);
        tv_addgoods = findViewByID_My(R.id.tv_addgoods);
        tv_addproject = findViewByID_My(R.id.tv_addproject);

        //服务列表
        rv_service = findViewByID_My(R.id.rv_service);
        rv_service.setLayoutManager(new LinearLayoutManager(this));
        //其他商品
        rv_other = findViewByID_My(R.id.rv_other);
        rv_other.setLayoutManager(new LinearLayoutManager(this));
        //检测项目
        rv_jiance = findViewByID_My(R.id.rv_jiance);
        rv_jiance.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        y_techn_sedan_id = getIntent().getStringExtra("y_techn_sedan_id");

    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading));
        Map<String, String> params = new HashMap<>();
        params.put("y_techn_sedan_id", y_techn_sedan_id);
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
//                showEmptyPage();
                myToast(err);
            }

            @Override
            public void onResponse(OrderDetailModel response) {
                hideProgress();
                model = response;

                switch (model.getOrder_info().getGState()) {
                    case 2:
                        titleView.setTitle("待施工");
                        tv_time.setText("接车时间：" + model.getTechn_sedan_info().getCreateDate());//预约时间
                        tv_confirm.setText("立即施工");
                        break;
                    case 3:
                        titleView.setTitle("进行中");
                        tv_time.setText("施工时间：" + model.getTechn_sedan_info().getCreateDate());//预约时间
                        tv_confirm.setText("全部完工");
                        break;
                    case 4:
                        titleView.setTitle("待复检");
                        tv_time.setText("完工时间：" + model.getTechn_sedan_info().getCreateDate());//预约时间
                        tv_confirm.setText("检查完毕");
                        tv_addservice.setVisibility(View.GONE);
                        tv_addgoods.setVisibility(View.GONE);
                        tv_addproject.setVisibility(View.GONE);
                        break;
                    case 5:
                        titleView.setTitle("已完工");
                        tv_time.setText("复检时间：" + model.getTechn_sedan_info().getCreateDate());//预约时间

                        if (model.getOrder_info().getIsDelivery() == 1) {
                            tv_confirm.setText("送车到家");
                        } else {
                            tv_confirm.setText("联系客户");
                        }

                        tv_fukuan.setVisibility(View.VISIBLE);
                        if (model.getTechn_sedan_info().getIsPay() == 0) {
                            tv_fukuan.setText("客户未付款");
                            tv_fukuan.setClickable(true);
                        } else {
                            tv_fukuan.setText("客户已付款");
                            tv_fukuan.setClickable(false);
                        }

                        tv_addservice.setVisibility(View.GONE);
                        tv_addgoods.setVisibility(View.GONE);
                        tv_addproject.setVisibility(View.GONE);
                        break;
                    case 6:
                        titleView.setTitle("已提车");
                        tv_time.setText("提车时间：" + model.getTechn_sedan_info().getCreateDate());//预约时间
                        tv_confirm.setText("客户已提车");
                        tv_confirm.setBackgroundResource(R.drawable.yuanjiao_5_huise);

                        tv_addservice.setVisibility(View.GONE);
                        tv_addgoods.setVisibility(View.GONE);
                        tv_addproject.setVisibility(View.GONE);
                        break;
                }
                //基本信息
                tv_carnum.setText(model.getTechn_sedan_info().getLicenseNumber());//车牌
                tv_name.setText(model.getTechn_sedan_info().getOwnerName());//昵称
                tv_carname.setText(model.getTechn_sedan_info().getBrandInfo().getGroupName());//车辆名称
                tv_carpailiang.setText("排量：" + model.getTechn_sedan_info().getBrandInfo().getVDispla());
                tv_carnianfen.setText("年份：" + model.getTechn_sedan_info().getBrandInfo().getVYear());
                tv_carlicheng.setText("里程：" + model.getTechn_sedan_info().getVehicleMileage());
                tv_money.setText("¥" + model.getOrder_price());
                tv_jiechename.setText("接车人：" + model.getTechn_sedan_info().getJ_user_info().getUserName());
                tv_beizhu.setText(model.getTechn_sedan_info().getVRemarks());//备注

                tv_allmoney.setText("¥" + model.getOrder_price());

                /**
                 * 服务列表
                 */
                mAdapter_service = new CommonAdapter<OrderDetailModel.OrderInfoBean.OrderServiceListBean>(
                        OrderDetailActivity.this, R.layout.item_service, model.getOrder_info().getOrder_service_list()) {
                    @Override
                    protected void convert(ViewHolder holder, OrderDetailModel.OrderInfoBean.OrderServiceListBean bean, int position) {
                        //信息
                        holder.setText(R.id.servicename, bean.getServiceStr());
                        holder.setText(R.id.servicemoney, "¥" + bean.getGPrice());
//                holder.setText(R.id.serviceallmoney, "¥" + bean.get);
                        //商品列表
                        RecyclerView rv_s = holder.getView(R.id.rv_s);
                        LinearLayoutManager llm1 = new LinearLayoutManager(OrderDetailActivity.this);
                        llm1.setOrientation(LinearLayoutManager.VERTICAL);// 设置 recyclerview 布局方式为横向布局
                        rv_s.setLayoutManager(llm1);
                        CommonAdapter<OrderDetailModel.OrderInfoBean.OrderServiceListBean.OrderGoodsListBeanX> ca_s =
                                new CommonAdapter<OrderDetailModel.OrderInfoBean.OrderServiceListBean.OrderGoodsListBeanX>
                                        (OrderDetailActivity.this, R.layout.item_orderdetail_goods, bean.getOrder_goods_list()) {
                                    @Override
                                    protected void convert(ViewHolder holder, OrderDetailModel.OrderInfoBean.OrderServiceListBean.OrderGoodsListBeanX model1, int p) {
                                        ImageView iv = holder.getView(R.id.iv);
                                        Glide.with(OrderDetailActivity.this).load(URLs.IMGHOST + model1.getGoods_info().getGImg())
                                                .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                                .placeholder(R.mipmap.loading)//加载站位图
                                                .error(R.mipmap.zanwutupian)//加载失败
                                                .into(iv);//加载图片
                                        holder.setText(R.id.goodsname, model1.getGoods_info().getGName());
                                        holder.setText(R.id.goodsguige, "商品规格：" + model1.getGoodsValue());
//                                        num = model1.getGNum();
                                        holder.setText(R.id.goodsnum, "x" + model1.getGNum());
                                        holder.setText(R.id.goodsmoney, "¥" + model1.getGPrice());

                                        //横向图片
//                                        String[] strArr = model1.getGoods_info().getImgStr().split("\\|\\|");
                                        List<String> list_img = new ArrayList<>();
                                        if (model1.getGoods_info().getImgArr() != null) {
                                            for (String s : model1.getGoods_info().getImgArr()) {
                                                if (!s.equals("")) {
                                                    list_img.add(URLs.IMGHOST + s);
                                                }
                                            }
                                        }

                                        RecyclerView rv = holder.getView(R.id.rv);
                                        LinearLayoutManager llm1 = new LinearLayoutManager(OrderDetailActivity.this);
                                        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                                        rv.setLayoutManager(llm1);
                                        CommonAdapter<String> ca = new CommonAdapter<String>
                                                (OrderDetailActivity.this, R.layout.item_img_80_80, list_img) {
                                            @Override
                                            protected void convert(ViewHolder holder, String model, int position) {
                                                ImageView iv = holder.getView(R.id.iv);
                                                Glide.with(OrderDetailActivity.this).load(model)
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
                                                PhotoShowDialog photoShowDialog = new PhotoShowDialog(OrderDetailActivity.this, list_img, i);
                                                photoShowDialog.show();
                                            }

                                            @Override
                                            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                                return false;
                                            }
                                        });
                                        rv.setAdapter(ca);
                                        //删除商品
                                        if (model.getOrder_info().getGState() >= 4) {
                                            holder.getView(R.id.delete_goods).setVisibility(View.GONE);
                                        }
                                        holder.getView(R.id.delete_goods).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                showToast("确认删除该商品吗？", "确认", "取消", new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        dialog.dismiss();
                                                        Map<String, String> params = new HashMap<>();
                                                        params.put("u_token", localUserInfo.getToken());
                                                        params.put("y_order_goods_id", model1.getYOrderGoodsId());
                                                        RequestDeleteGoods(params);
                                                    }
                                                }, new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        dialog.dismiss();
                                                    }
                                                });
                                            }
                                        });
                                    }
                                };
                        rv_s.setAdapter(ca_s);
                        //添加商品按钮
                        TextView addgoods = holder.getView(R.id.addgoods);

                        switch (model.getOrder_info().getGState()) {
                            case 2:
                                addgoods.setText("添加商品");
                                addgoods.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Bundle bundle2 = new Bundle();
                                        bundle2.putString("y_store_id", model.getOrder_info().getYStoreId());
                                        bundle2.putString("y_order_id", model.getOrder_info().getYOrderId());
                                        bundle2.putString("y_store_service_id", bean.getYStoreServiceId());
                                        bundle2.putString("y_techn_sedan_id", model.getOrder_info().getYTechnSedanId());
                                        bundle2.putString("y_order_service_id", bean.getYOrderServiceId());
                                        CommonUtil.gotoActivityWithData(OrderDetailActivity.this, SelectGoodsActivity.class, bundle2, false);
                                    }
                                });
                                break;
                            case 3:
                                addgoods.setText("开始施工");
                                addgoods.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        addgoods.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                showToast("确认开始施工吗？", "确认", "取消", new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        dialog.dismiss();
                                                        Map<String, String> params = new HashMap<>();
                                                        params.put("u_token", localUserInfo.getToken());
                                                        params.put("y_order_service_id", bean.getYOrderServiceId());
                                                        params.put("g_state", "2");//1为进行施工中 2为已完成  3已检查
                                                        RequestChangeServiceType(params);
                                                    }
                                                }, new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        dialog.dismiss();
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                                break;
                            case 4:
                                if (bean.getGState() == 3) {
                                    addgoods.setVisibility(View.GONE);
                                } else {
                                    addgoods.setVisibility(View.VISIBLE);
                                }
                                addgoods.setText("检查");
                                addgoods.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        showToast("确认服务已完成吗？", "确认", "取消", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dialog.dismiss();
                                                Map<String, String> params = new HashMap<>();
                                                params.put("u_token", localUserInfo.getToken());
                                                params.put("y_order_service_id", bean.getYOrderServiceId());
                                                params.put("g_state", "3");//1为进行施工中 2为已完成  3已检查
                                                RequestChangeServiceType(params);
                                            }
                                        }, new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dialog.dismiss();
                                            }
                                        });
                                    }
                                });
                                break;
                            default:
                                holder.getView(R.id.delete_service).setVisibility(View.GONE);
                                addgoods.setVisibility(View.GONE);
                                break;
                        }
                        //删除项目
                        if (model.getOrder_info().getGState() >= 4) {
                            holder.getView(R.id.delete_service).setVisibility(View.GONE);
                        }
                        holder.getView(R.id.delete_service).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showToast("该服务下有" + bean.getOrder_goods_list().size() + "个商品，确认一起删除吗？", "确认", "取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                        Map<String, String> params = new HashMap<>();
                                        params.put("u_token", localUserInfo.getToken());
                                        params.put("y_order_service_id", bean.getYOrderServiceId());
                                        RequestDeleteService(params);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                            }
                        });
                    }
                };
                rv_service.setAdapter(mAdapter_service);

                /**
                 * 其他商品
                 */
                mAdapter_other = new CommonAdapter<OrderDetailModel.OrderInfoBean.OrderGoodsListBean>
                        (OrderDetailActivity.this, R.layout.item_orderdetail_goods, model.getOrder_info().getOrder_goods_list()) {
                    @Override
                    protected void convert(ViewHolder holder, OrderDetailModel.OrderInfoBean.OrderGoodsListBean model1, int p) {
                        ImageView iv = holder.getView(R.id.iv);
                        Glide.with(OrderDetailActivity.this).load(URLs.IMGHOST + model1.getGoods_info().getGImg())
                                .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                .placeholder(R.mipmap.loading)//加载站位图
                                .error(R.mipmap.zanwutupian)//加载失败
                                .into(iv);//加载图片
                        holder.setText(R.id.goodsname, model1.getGoods_info().getGName());
                        holder.setText(R.id.goodsguige, "商品规格：" + model1.getGoodsValue());
//                                        num = model1.getGNum();
                        holder.setText(R.id.goodsnum, "x" + model1.getGNum());
                        holder.setText(R.id.goodsmoney, "¥" + model1.getGPrice());

                        //横向图片
//                        String[] strArr = model1.getGoods_info().getImgStr().split("\\|\\|");
                        List<String> list_img = new ArrayList<>();
                        if (model1.getGoods_info().getImgArr() != null) {
                            for (String s : model1.getGoods_info().getImgArr()) {
                                if (!s.equals("")) {
                                    list_img.add(URLs.IMGHOST + s);
                                }
                            }
                        }

                        RecyclerView rv = holder.getView(R.id.rv);
                        LinearLayoutManager llm1 = new LinearLayoutManager(OrderDetailActivity.this);
                        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                        rv.setLayoutManager(llm1);
                        CommonAdapter<String> ca = new CommonAdapter<String>
                                (OrderDetailActivity.this, R.layout.item_img_80_80, list_img) {
                            @Override
                            protected void convert(ViewHolder holder, String model, int position) {
                                ImageView iv = holder.getView(R.id.iv);
                                Glide.with(OrderDetailActivity.this).load(model)
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
                                PhotoShowDialog photoShowDialog = new PhotoShowDialog(OrderDetailActivity.this, list_img, i);
                                photoShowDialog.show();
                            }

                            @Override
                            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                return false;
                            }
                        });
                        rv.setAdapter(ca);

                        //删除商品
                        if (model.getOrder_info().getGState() >= 4) {
                            holder.getView(R.id.delete_goods).setVisibility(View.GONE);
                        }
                        holder.getView(R.id.delete_goods).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showToast("确认删除该商品吗？", "确认", "取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                        Map<String, String> params = new HashMap<>();
                                        params.put("u_token", localUserInfo.getToken());
                                        params.put("y_order_goods_id", model1.getYOrderGoodsId());
                                        RequestDeleteGoods(params);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                            }
                        });
                    }
                }

                ;
                rv_other.setAdapter(mAdapter_other);

                /**
                 * 检测项目
                 */
                mAdapter_jiance = new CommonAdapter<OrderDetailModel.TestingDetailsListBean>(
                        OrderDetailActivity.this, R.layout.item_orderdetail_jiance, model.getTesting_details_list()) {
                    @Override
                    protected void convert(ViewHolder
                                                   holder, OrderDetailModel.TestingDetailsListBean jianceBean, int position) {
                        TextView tv_confirm = holder.getView(R.id.tv_confirm);
                        TextView tv_chageproject = holder.getView(R.id.tv_chageproject);
                        if (jianceBean.getIsConfirm() == 0) {//待确认
                            holder.setText(R.id.tv_zhuangtai, "客户待确认");
                            tv_confirm.setVisibility(View.GONE);
                            tv_chageproject.setVisibility(View.VISIBLE);
                        } else {
                            holder.setText(R.id.tv_zhuangtai, "客户已确认");
                            tv_confirm.setVisibility(View.VISIBLE);
                            tv_chageproject.setVisibility(View.GONE);
                        }
                        switch (jianceBean.getVState()) {
                            case 0://待确认
                                tv_confirm.setText("进行施工");
                                break;
                            case 1:
                                tv_confirm.setText("施工完成");
                                break;
                            case 2:
                                tv_confirm.setText("检查完毕");
                                break;
                            default:
                                tv_confirm.setVisibility(View.GONE);
                                break;
                        }
                        holder.setText(R.id.tv_time, "提交时间:" + jianceBean.getCreateDate());
                        holder.setText(R.id.tv_title, jianceBean.getVTitle());
                        holder.setText(R.id.tv_money, jianceBean.getVPrice() + "");
                        if (jianceBean.getIsReplace() == 0) {
                            holder.setText(R.id.tv_type, "维修");
                        } else {
                            holder.setText(R.id.tv_type, "更换");
                        }
                        //横向图片
//                        String[] strArr = model1.getGoods_info().getImgStr().split("\\|\\|");
                        List<String> list_img = new ArrayList<>();
                        for (String s : jianceBean.getImgArr()) {
                            if (!s.equals("")) {
                                list_img.add(URLs.IMGHOST + s);
                            }
                        }
                        RecyclerView rv = holder.getView(R.id.rv);
                        LinearLayoutManager llm1 = new LinearLayoutManager(OrderDetailActivity.this);
                        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                        rv.setLayoutManager(llm1);
                        CommonAdapter<String> ca = new CommonAdapter<String>
                                (OrderDetailActivity.this, R.layout.item_img_80_80, list_img) {
                            @Override
                            protected void convert(ViewHolder holder, String model, int position) {
                                ImageView iv = holder.getView(R.id.iv);
                                Glide.with(OrderDetailActivity.this).load(model)
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
                                PhotoShowDialog photoShowDialog = new PhotoShowDialog(OrderDetailActivity.this, list_img, i);
                                photoShowDialog.show();
                            }

                            @Override
                            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                return false;
                            }
                        });
                        rv.setAdapter(ca);

                        tv_confirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //进行施工
                                String msg = "";
                                switch (jianceBean.getVState()) {
                                    case 0://待确认
                                        msg = "确认开始进行施工吗？";
                                        break;
                                    case 1:
                                        msg = "确认已经完成了吗？";
                                        break;
                                    case 2:
                                        msg = "确认已经检查完成了吗？";
                                        break;

                                }
                                showToast(msg, "确认", "取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                        Map<String, String> params = new HashMap<>();

                                        params.put("u_token", localUserInfo.getToken());
                                        params.put("y_testing_details_id", jianceBean.getYTestingDetailsId());
                                        /*params.put("y_store_service_id", jianceBean.getYStoreServiceId());
                                        params.put("v_title", jianceBean.getVTitle());
                                        params.put("v_price", jianceBean.getVPrice() + "");
                                        params.put("is_replace", jianceBean.getIsReplace() + "");
                                        params.put("imgstr", jianceBean.getImgStr());*/
                                        params.put("v_state", (jianceBean.getVState() + 1) + "");//1为正在施工 2为已经完成  3已复检
                                        RequestChangeProjectType(params);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                            }
                        });
                        tv_chageproject.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //修改
                                Bundle bundle = new Bundle();
                                bundle.putString("y_techn_sedan_id", "");
                                bundle.putSerializable("SelectService", jianceBean);
                                CommonUtil.gotoActivityWithData(OrderDetailActivity.this, AddProjectActivity.class, bundle, false);
                            }
                        });
                        //删除商品
                        if (model.getOrder_info().getGState() >= 4) {
                            holder.getView(R.id.delete_project).setVisibility(View.GONE);
                        }
                        holder.getView(R.id.delete_project).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showToast("确认删除该检测项目吗？", "确认", "取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                        Map<String, String> params = new HashMap<>();
                                        params.put("u_token", localUserInfo.getToken());
                                        params.put("y_testing_details_id", jianceBean.getYTestingDetailsId());
                                        RequestDeleteProject(params);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                            }
                        });
                    }
                }

                ;
                rv_jiance.setAdapter(mAdapter_jiance);
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
            case R.id.linearLayout3:
                type = 3;
                changeUI();
                break;
            case R.id.ll_feiyong:
                //费用
                Bundle bundle4 = new Bundle();
                bundle4.putString("y_techn_sedan_id", y_techn_sedan_id);
                CommonUtil.gotoActivityWithData(OrderDetailActivity.this, CouponActivity.class, bundle4, false);
                break;
            case R.id.iv_message:
                String url = URLs.KFHOST + "/#/pages/chetu-kf/chetu-kf?token=" + localUserInfo.getToken() +
                        "&receive_user_hash=" + model.getOrder_info().getUser_info().getUserHash() +
                        "&nickName=" + model.getOrder_info().getUser_info().getUserName() +
                        "&headerPic=" + URLs.IMGHOST + model.getOrder_info().getUser_info().getHeadPortrait();
                Bundle bundle = new Bundle();
                bundle.putString("url", url);
                CommonUtil.gotoActivityWithData(OrderDetailActivity.this, WebContentActivity.class, bundle, false);
                break;
            case R.id.iv_call:
                //拨打电话
                showToast("确认拨打 " + model.getTechn_sedan_info().getOwnerPhone() + " 吗？", "确认", "取消",
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
                                intent.setData(Uri.parse("tel:" + model.getTechn_sedan_info().getOwnerPhone()));
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
            case R.id.tv_addservice:
                //添加服务
                Bundle bundle1 = new Bundle();
                bundle1.putString("y_order_id", model.getOrder_info().getYOrderId());
                bundle1.putString("y_store_id", model.getOrder_info().getYStoreId());
                CommonUtil.gotoActivityWithData(OrderDetailActivity.this, AddServiceActivity.class, bundle1, false);
                break;
            case R.id.tv_addgoods:
                //添加商品
                Bundle bundle2 = new Bundle();
                bundle2.putString("y_store_id", model.getOrder_info().getYStoreId());
                bundle2.putString("y_order_id", model.getOrder_info().getYOrderId());
                bundle2.putString("y_store_service_id", "");
                bundle2.putString("y_techn_sedan_id", model.getOrder_info().getYTechnSedanId());
                bundle2.putString("y_order_service_id", "");
                CommonUtil.gotoActivityWithData(OrderDetailActivity.this, SelectGoodsActivity.class, bundle2, false);
                break;
            case R.id.tv_addproject:
                //添加检测项目
                Bundle bundle3 = new Bundle();
                bundle3.putString("y_techn_sedan_id", y_techn_sedan_id);
                CommonUtil.gotoActivityWithData(OrderDetailActivity.this, AddProjectActivity.class, bundle3, false);
                break;
            case R.id.tv_confirm:
                String msg = "";
                switch (model.getOrder_info().getGState()) {
                    case 2:
                        msg = "确认立即施工了吗？";
                        break;
                    case 3:
                        msg = "确认已完工了吗？";
                        break;
                    case 4:
                        msg = "确认检查完毕了吗？";
                        break;
                    case 5:
                        if (model.getOrder_info().getIsDelivery() == 1) {
                            msg = "确认已送车到家了吗？";
                        } else {
                            msg = "确认已联系客户了吗？";
                        }
                        break;
                    case 6:
                        return;
//                        break;
                }
                showToast(msg, "确认", "取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Map<String, String> params = new HashMap<>();
                        params.put("u_token", localUserInfo.getToken());
                        params.put("y_techn_sedan_id", y_techn_sedan_id);
                        params.put("g_state", (model.getOrder_info().getGState() + 1) + "");//2为待施工 3为进行中 4待复检  5为待完工 6为已提车  7为付款
                        RequestChageType(params, 1);
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                break;

            case R.id.tv_fukuan:
                //付款
                showToast("确认客户已付款吗？", "确认", "取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Map<String, String> params = new HashMap<>();
                        params.put("u_token", localUserInfo.getToken());
                        params.put("y_techn_sedan_id", y_techn_sedan_id);
                        params.put("g_state", "7");//2为待施工 3为进行中 4待复检  5为待完工 6为已提车  7为付款
                        RequestChageType(params, 7);
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                break;

        }
    }

    /**
     * 改变订单状态
     *
     * @param params
     */
    private void RequestChageType(Map<String, String> params, int type) {
        OkhttpUtil.okHttpPost(URLs.ChageOrderType, params, headerMap, new CallBackUtil<String>() {
            @Override
            public String onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
//                myToast("修改订单状态成功");
                if (type == 7) {
                    requestServer();
                } else {
                    finish();
                }

            }
        });
    }

    /**
     * 删除服务
     *
     * @param params
     */
    private void RequestDeleteService(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.DeleteService, params, headerMap, new CallBackUtil<String>() {
            @Override
            public String onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                myToast("删除成功");
                requestServer();
            }
        });
    }

    /**
     * 删除商品
     *
     * @param params
     */
    private void RequestDeleteGoods(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.DeleteGoods, params, headerMap, new CallBackUtil<String>() {
            @Override
            public String onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                myToast("删除成功");
                requestServer();
            }
        });
    }

    /**
     * 删除项目
     *
     * @param params
     */
    private void RequestDeleteProject(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.DeleteProject, params, headerMap, new CallBackUtil<String>() {
            @Override
            public String onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                myToast("删除成功");
                requestServer();
            }
        });
    }

    /**
     * 修改项目状态
     *
     * @param params
     */
    private void RequestChangeProjectType(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ChageProjectType, params, headerMap, new CallBackUtil() {
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
                myToast("修改检测项目状态成功");
                requestServer();
            }
        });
    }

    /**
     * 修改服务状态
     *
     * @param params
     */
    private void RequestChangeServiceType(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ChageServiceType, params, headerMap, new CallBackUtil() {
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
                myToast("修改服务状态成功");
                requestServer();
            }
        });
    }

    private void changeUI() {
        switch (type) {
            case 1:
                //服务
                textView1.setTextColor(getResources().getColor(R.color.blue));
                view1.setVisibility(View.VISIBLE);
                textView2.setTextColor(getResources().getColor(R.color.black));
                view2.setVisibility(View.INVISIBLE);
                textView3.setTextColor(getResources().getColor(R.color.black));
                view3.setVisibility(View.INVISIBLE);
                ll_fuwu.setVisibility(View.VISIBLE);
                ll_jiance.setVisibility(View.GONE);
                ll_beizhu.setVisibility(View.GONE);
                break;
            case 2:
                //检测
                textView1.setTextColor(getResources().getColor(R.color.black));
                view1.setVisibility(View.INVISIBLE);
                textView2.setTextColor(getResources().getColor(R.color.blue));
                view2.setVisibility(View.VISIBLE);
                textView3.setTextColor(getResources().getColor(R.color.black));
                view3.setVisibility(View.INVISIBLE);
                ll_fuwu.setVisibility(View.GONE);
                ll_jiance.setVisibility(View.VISIBLE);
                ll_beizhu.setVisibility(View.GONE);
                break;
            case 3:
                //备注
                textView1.setTextColor(getResources().getColor(R.color.black));
                view1.setVisibility(View.INVISIBLE);
                textView2.setTextColor(getResources().getColor(R.color.black));
                view2.setVisibility(View.INVISIBLE);
                textView3.setTextColor(getResources().getColor(R.color.blue));
                view3.setVisibility(View.VISIBLE);
                ll_fuwu.setVisibility(View.GONE);
                ll_jiance.setVisibility(View.GONE);
                ll_beizhu.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setBackground(R.color.background);
        titleView.setRightBtn(R.mipmap.ic_scan_black, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //扫一扫
                ScanConfig config = new ScanConfig()
                        .setShowFlashlight(true)//是否需要打开闪光灯
                        .setShowGalary(true)//是否需要打开相册
                        .setNeedRing(true);//是否需要提示音
                //ScanConfig 也可以不配置 默认都是打开
                CaptureActivity.launch(OrderDetailActivity.this, config);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == CaptureActivity.REQUEST_CODE_SCAN) {
            // 扫描二维码回传
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    //获取扫描结果
                    Bundle bundle = data.getExtras();
                    String result = bundle.getString(CaptureActivity.EXTRA_SCAN_RESULT);
                    MyLogger.i("扫码返回", result);
                    showProgress(true, "正在使用优惠券，请稍候...");
                    Map<String, String> params = new HashMap<>();
                    params.put("u_token", localUserInfo.getToken());
                    params.put("y_techn_sedan_id", y_techn_sedan_id);
                    params.put("y_user_coupon_id", result);
                    RequestYouHuiQuan(params);
                }
            }
        }
    }
    /**
     * 修改服务状态
     *
     * @param params
     */
    private void RequestYouHuiQuan(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.USE_Coupon, params, headerMap, new CallBackUtil() {
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
                myToast("优惠券使用成功");
                requestServer();
            }
        });
    }
}
