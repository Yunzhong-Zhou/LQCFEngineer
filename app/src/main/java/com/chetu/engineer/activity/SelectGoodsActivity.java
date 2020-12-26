package com.chetu.engineer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.SelectGoodsModel;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.utils.MyLogger;
import com.cy.cyflowlayoutlibrary.FlowLayout;
import com.cy.cyflowlayoutlibrary.FlowLayoutAdapter;
import com.cy.dialog.BaseDialog;
import com.liaoinstan.springview.widget.SpringView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/7/3.
 * 选择商品
 */
public class SelectGoodsActivity extends BaseActivity {
    int page = 0;
    String y_techn_sedan_id = "", y_order_service_id = "", y_order_id = "", y_store_service_id = "", y_store_id = "";
    RecyclerView recyclerView1;
    List<SelectGoodsModel.ListBean> list1 = new ArrayList<>();
    CommonAdapter<SelectGoodsModel.ListBean> mAdapter1;


    RecyclerView[] rv;
    CommonAdapter[] adapter;
    List<Integer>[] selects;
    int[] g_num;
    String[] goods_specific_idstr, s_value, price, is_install;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectgoods);
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
        //刷新
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                Map<String, String> params = new HashMap<>();
                params.put("page", page + "");
                params.put("is_techn", "1");
                params.put("y_store_id", y_store_id);
//                params.put("u_token", localUserInfo.getToken());
                Request(params);
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
//                params.put("u_token", localUserInfo.getToken());
                params.put("page", page + "");
                params.put("is_techn", "1");
                params.put("y_store_id", y_store_id);
                RequestMore(params);
            }
        });
        recyclerView1 = findViewByID_My(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        findViewByID_My(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //确认添加
                showProgress(true, getString(R.string.app_loading1));
                JSONArray jsonArray = new JSONArray();
                for (int i = 0; i < list1.size(); i++) {
                    if (list1.get(i).isXuanZhong()) {
                        try {
                            JSONObject object1 = new JSONObject();
                            object1.put("y_store_service_id", y_store_service_id);
                            object1.put("y_order_id", y_order_id);
                            object1.put("y_goods_id", list1.get(i).getYGoodsId());
                            /*if (!y_store_service_id.equals("")) {
                                object1.put("is_service", "2");//1为服务  2为服务下边的商品 3为独立商品
                            } else {
                                object1.put("is_service", "3");//1为服务  2为服务下边的商品 3为独立商品
                            }*/
                            object1.put("num", g_num[i]);
                            object1.put("s_value", s_value[i]);
//                            object1.put("goods_specific_idstr", goods_specific_idstr[i]);
                            object1.put("price", price[i]);
                            jsonArray.put(object1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("order_server_goods_json", jsonArray.toString());
                RequestXiaDan(params);
            }
        });
    }

    @Override
    protected void initData() {
        y_techn_sedan_id = getIntent().getStringExtra("y_techn_sedan_id");
        y_order_service_id = getIntent().getStringExtra("y_order_service_id");

        y_store_service_id = getIntent().getStringExtra("y_store_service_id");
        y_store_id = getIntent().getStringExtra("y_store_id");
        y_order_id = getIntent().getStringExtra("y_order_id");

    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 0;
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("is_techn", "1");
        params.put("y_store_id", y_store_id);
//        params.put("u_token", localUserInfo.getToken());
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.SelectGoods, params, headerMap, new CallBackUtil<SelectGoodsModel>() {
            @Override
            public SelectGoodsModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(SelectGoodsModel response) {
                hideProgress();
                list1 = response.getList();
                if (list1.size() > 0) {
                    showContentPage();
                    rv = new RecyclerView[list1.size()];
                    adapter = new CommonAdapter[list1.size()];
                    selects = new ArrayList[list1.size()];
                    g_num = new int[list1.size()];
                    goods_specific_idstr = new String[list1.size()];
                    price = new String[list1.size()];
                    s_value = new String[list1.size()];
                    is_install = new String[list1.size()];

                    mAdapter1 = new CommonAdapter<SelectGoodsModel.ListBean>
                            (SelectGoodsActivity.this, R.layout.item_selectgoods, list1) {
                        @Override
                        protected void convert(ViewHolder holder, SelectGoodsModel.ListBean model, int position) {
                            //logo
                            ImageView imageView = holder.getView(R.id.imageView);
                            Glide.with(SelectGoodsActivity.this).load(URLs.IMGHOST + model.getGImg())
                                    .centerCrop()
//                                    .apply(RequestOptions.bitmapTransform(new
//                                            RoundedCorners(CommonUtil.dip2px(SelectGoodsActivity.this, 5))))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(imageView);//加载图片

                            holder.setText(R.id.tv_title, model.getGName());
                            holder.setText(R.id.tv_money, model.getGPrice() + "");
                            TextView tv_guige = holder.getView(R.id.tv_guige);


                            is_install[position] = "1";
                            g_num[position] = 1;
                            selects[position] = new ArrayList<>();
                            if (model.getSpecific_list().size() > 0) {
                                for (SelectGoodsModel.ListBean.SpecificListBeanX b : model.getSpecific_list()) {
                                    selects[position].add(0);
                                }
                            }


                            tv_guige.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //规格
                                    showDialog(tv_guige, model, position);
                                }
                            });
                        }
                    };

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
        OkhttpUtil.okHttpPost(URLs.SelectGoods, params, headerMap, new CallBackUtil<SelectGoodsModel>() {
            @Override
            public SelectGoodsModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
//                myToast(err);
                page--;
            }

            @Override
            public void onResponse(SelectGoodsModel response) {
                hideProgress();
                List<SelectGoodsModel.ListBean> list_1 = new ArrayList<>();
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

    /**
     * 下单-添加购物车
     *
     * @param params
     */
    private void RequestXiaDan(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ADDGoods, params, headerMap, new CallBackUtil<Object>() {
            @Override
            public Object onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
            }

            @Override
            public void onResponse(Object response) {
                hideProgress();
                showToast("添加成功", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        finish();
                    }
                });
            }
        });
    }

    /**
     * 显示规格弹窗
     */
    private void showDialog(TextView tv_guige, SelectGoodsModel.ListBean model, int position) {
        BaseDialog dialog1 = new BaseDialog(SelectGoodsActivity.this);
        dialog1.contentView(R.layout.dialog_selectgoods)
                .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT))
                .animType(BaseDialog.AnimInType.BOTTOM)
                .canceledOnTouchOutside(true)
                .gravity(Gravity.BOTTOM)
                .dimAmount(0.7f)
                .show();

        TextView tv_title = dialog1.findViewById(R.id.tv_title);
        tv_title.setText(model.getGName());
        TextView tv_money = dialog1.findViewById(R.id.tv_money);
        tv_money.setText("¥" + model.getGPrice());
        ImageView iv = dialog1.findViewById(R.id.iv);
        Glide.with(SelectGoodsActivity.this).load(URLs.IMGHOST + model.getGImg())
                .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                .placeholder(R.mipmap.loading)//加载站位图
                .error(R.mipmap.zanwutupian)//加载失败
                .into(iv);//加载图片

        TextView tv_tab = dialog1.findViewById(R.id.tv_tab);

        TextView tv_num = dialog1.findViewById(R.id.tv_num);
        tv_num.setText(g_num[position] + "");

        TextView tv_anzhuang = dialog1.findViewById(R.id.tv_anzhuang);
        TextView tv_buanzhuang = dialog1.findViewById(R.id.tv_buanzhuang);
        tv_anzhuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //安装
                is_install[position] = "1";
                tv_anzhuang.setTextColor(getResources().getColor(R.color.blue));
                tv_anzhuang.setBackgroundResource(R.drawable.yuanjiaobiankuang_15_lanse);
                tv_buanzhuang.setTextColor(getResources().getColor(R.color.black));
                tv_buanzhuang.setBackgroundResource(R.drawable.yuanjiao_15_huise1);

            }
        });
        tv_buanzhuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //不安装
                is_install[position] = "0";
                tv_anzhuang.setTextColor(getResources().getColor(R.color.black));
                tv_anzhuang.setBackgroundResource(R.drawable.yuanjiao_15_huise1);
                tv_buanzhuang.setTextColor(getResources().getColor(R.color.blue));
                tv_buanzhuang.setBackgroundResource(R.drawable.yuanjiaobiankuang_15_lanse);
            }
        });

        rv[position] = dialog1.findViewById(R.id.rv);
        rv[position].setLayoutManager(new LinearLayoutManager(SelectGoodsActivity.this));

        adapter[position] = new CommonAdapter<SelectGoodsModel.ListBean.SpecificListBeanX>
                (SelectGoodsActivity.this, R.layout.item_dialog_guige, model.getSpecific_list()) {
            @Override
            protected void convert(ViewHolder holder, SelectGoodsModel.ListBean.SpecificListBeanX model1, int item) {
                holder.setText(R.id.tv, model1.getSName());
//                    String[] strArr = model.getSValue().split("\\|\\|");
                if (model1.getSpecific_List().size() > 0) {
                    List<String> tabs = new ArrayList<>();
                    for (SelectGoodsModel.ListBean.SpecificListBeanX.SpecificListBean s : model1.getSpecific_List()) {
                        tabs.add(s.getPName());
                    }

                    FlowLayoutAdapter flowLayoutAdapter = new FlowLayoutAdapter<String>(tabs) {
                        @Override
                        public void bindDataToView(ViewHolder holder, int i, String bean) {
                            TextView tv = holder.getView(R.id.tv);
                            tv.setText(bean);
                            if (selects[position].get(item) == i) {
                                tv.setTextColor(getResources().getColor(R.color.blue));
                                tv.setBackgroundResource(R.drawable.yuanjiaobiankuang_15_lanse);
                            } else {
                                tv.setTextColor(getResources().getColor(R.color.black));
                                tv.setBackgroundResource(R.drawable.yuanjiao_15_huise1);
                            }
                        }

                        @Override
                        public void onItemClick(int i, String bean) {
                            selects[position].set(item, i);
                            adapter[position].notifyDataSetChanged();
                            //计算及显示
                            addView(tv_guige, model, tv_tab, tv_money, g_num[position], position);
                        }

                        @Override
                        public int getItemLayoutID(int position, String bean) {
                            return R.layout.item_guige_flowlayout;
                        }
                    };
                    ((FlowLayout) holder.getView(R.id.flowLayout)).setAdapter(flowLayoutAdapter);
                }
            }
        };
        rv[position].setAdapter(adapter[position]);
        dialog1.findViewById(R.id.tv_jian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //减号
                if (g_num[position] > 1) {
                    g_num[position]--;
                    tv_num.setText(g_num[position] + "");
                    addView(tv_guige, model, tv_tab, tv_money, g_num[position], position);
                }
            }
        });
        dialog1.findViewById(R.id.tv_jia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //加号
//                        if (num < 100) {
                g_num[position]++;
                tv_num.setText(g_num[position] + "");

                addView(tv_guige, model, tv_tab, tv_money, g_num[position], position);
//                        }
            }
        });
        dialog1.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
        addView(tv_guige, model, tv_tab, tv_money, g_num[position], position);
    }

    /**
     * 计算
     *
     * @param tv_tab
     * @param tv_money
     * @param num
     */
    private void addView(TextView tv_guige, SelectGoodsModel.ListBean model, TextView tv_tab, TextView tv_money, int num, int position) {
        goods_specific_idstr[position] = "";
        s_value[position] = "";
        double tabMoney = 0;
        for (int i = 0; i < model.getSpecific_list().size(); i++) {
//            String[] strArr = model.getSpecific_list().get(i).getSValue().split("\\|\\|");
            for (int j = 0; j < model.getSpecific_list().get(i).getSpecific_List().size(); j++) {
                if (selects[position].get(i) == j) {
                    s_value[position] += model.getSpecific_list().get(i).getSpecific_List().get(j).getPName() + "||";
                    goods_specific_idstr[position] += model.getSpecific_list().get(i).getSpecific_List().get(j).getYGoodsSpecificId() + "||";
                    tabMoney += model.getSpecific_list().get(i).getSpecific_List().get(j).getSPrice();

                    price[position] = (model.getGPrice() + tabMoney) + "";
                }
            }
        }

        if (goods_specific_idstr[position].length() > 0) {
            goods_specific_idstr[position] = goods_specific_idstr[position].substring(0, goods_specific_idstr[position].length() - 2);
            MyLogger.i(">>>>>>" + goods_specific_idstr[position]);
            s_value[position] = s_value[position].substring(0, s_value[position].length() - 2);
        }

        tv_tab.setText(s_value[position]);

        price[position] = (model.getGPrice() + tabMoney) + "";
        double allmoney = (long) ((model.getGPrice() + tabMoney) * num);
        tv_money.setText("¥" + allmoney);

//        textView_moeny.setText("¥" + allmoney);
//        textView_num.setText(g_num + "");
//        head1_tv5.setText(s_value);
        tv_guige.setText(s_value[position] + "  数量:" + num + "   ¥" + allmoney);

        list1.get(position).setXuanZhong(true);
    }

    @Override
    protected void updateView() {
        titleView.setTitle("选择商品");
        titleView.setBackground(R.color.background);
        titleView.showRightTextview("自助添加", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SelectGoodsActivity.this, AddGoodsActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("y_techn_sedan_id", y_techn_sedan_id);
                bundle1.putString("y_order_service_id", y_order_service_id);
                intent.putExtras(bundle1);
                startActivityForResult(intent, 10002);
                /*Bundle bundle = new Bundle();

                CommonUtil.gotoActivityWithData(SelectGoodsActivity.this, AddGoodsActivity.class, bundle, false);*/
            }
        });
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
