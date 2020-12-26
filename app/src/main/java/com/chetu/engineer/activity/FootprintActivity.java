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
import com.chetu.engineer.model.MenDianModel;
import com.chetu.engineer.model.ShangPingModel;
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
 * Created by zyz on 2020/5/26.
 * 足迹
 */
public class FootprintActivity extends BaseActivity {
    int type = 1, page1 = 0, page2 = 0, category = 1;//1为商品  2为商家
    TextView textView1, textView2;
    private RecyclerView recyclerView;
    List<ShangPingModel.ListBean> list1 = new ArrayList<>();
    CommonAdapter<ShangPingModel.ListBean> mAdapter1;
//    FootpriintAdapter mAdapter1;//吸顶效果

    List<MenDianModel.ListBean> list2 = new ArrayList<>();
    CommonAdapter<MenDianModel.ListBean> mAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_footprint);
        mImmersionBar.reset()
                .statusBarColor(R.color.white)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @Override
    protected void initView() {
        //刷新
        setSpringViewMore(true);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("category", category + "");
                if (type == 1) {
                    page1 = 0;
                    params.put("page", page1 + "");
                    Request1(params);
                } else {
                    page2 = 0;
                    params.put("page", page2 + "");
                    Request2(params);
                }

            }

            @Override
            public void onLoadmore() {
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("category", category + "");
                if (type == 1) {
                    page1++;
                    params.put("page", page1 + "");
                    RequestMore1(params);
                } else {
                    page2++;
                    params.put("page", page2 + "");
                    RequestMore2(params);
                }
            }
        });
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);

        recyclerView = findViewByID_My(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    protected void initData() {
        requestServer();
        /*mAdapter1 = new CommonAdapter<FootprintModel.ListBean>
                (FootprintActivity.this, R.layout.item_footprint_title, list1) {
            @Override
            protected void convert(ViewHolder holder, FootprintModel.ListBean model, int position) {
                RecyclerView rv = holder.getView(R.id.rv);
                rv.setLayoutManager(new LinearLayoutManager(FootprintActivity.this));
                List<String> list = new ArrayList<>();
                list.add("");
                list.add("");
                list.add("");
                list.add("");
                CommonAdapter<String> mAdapter = new CommonAdapter<String>(FootprintActivity.this, R.layout.item_shangping, list) {
                    @Override
                    protected void convert(ViewHolder holder, String s, int position) {

                    }
                };
                rv.setAdapter(mAdapter);
            }
        };
        mAdapter1.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        recyclerView.setAdapter(mAdapter1);*/

    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        Map<String, String> params = new HashMap<>();
        params.put("u_token", localUserInfo.getToken());
        params.put("category", category + "");
        if (type == 1) {
            page1 = 0;
            params.put("page", page1 + "");
            Request1(params);
        } else {
            page2 = 0;
            params.put("page", page2 + "");
            Request2(params);
        }
    }

    /**
     * 商品
     *
     * @param params
     */
    private void Request1(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Footprint, params, headerMap, new CallBackUtil<ShangPingModel>() {
            @Override
            public ShangPingModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(ShangPingModel response) {
                hideProgress();
                list1 = response.getList();
                if (list1.size() > 0) {
                    showContentPage();
                    mAdapter1 = new CommonAdapter<ShangPingModel.ListBean>
                            (FootprintActivity.this, R.layout.item_shangping, list1) {
                        @Override
                        protected void convert(ViewHolder holder, ShangPingModel.ListBean model, int position) {
                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(FootprintActivity.this)
                                    .load(URLs.IMGHOST + model.getGoods_info().getGImg())
                                    .centerCrop()
                                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(imageView1);//加载图片
                            holder.setText(R.id.tv_name, model.getGoods_info().getGName());

                            //拆分日期  年     月日    \n    时分秒
                            String[] strArr1 = model.getCreateDate().split(" ");//拆分空格把日期和时间分开
                            String[] strArr2 = strArr1[0].split("-");//拆分日期 得到年月日
//                            String year = strArr2[0];//年
                            String day = strArr2[1] + "月" + strArr2[2] + "日";//提取月日
//                            String time = strArr1[1];//时间
//                            holder.setText(R.id.tv_time, day + "\n" + time);
                            holder.setText(R.id.tv_time, day);
                            holder.setText(R.id.tv_content, model.getGoods_info().getgDesc());
                            holder.setText(R.id.tv_money, "¥" + model.getGoods_info().getGPrice());

                            //删除
                            holder.getView(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    showToast("确认删除该足迹吗？", "确认", "取消", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                            showProgress(true, "正在删除...");
                                            HashMap<String, String> params2 = new HashMap<>();
                                            params2.put("y_user_footprint_id", model.getYUserFootprintId());
                                            params2.put("u_token", localUserInfo.getToken());
                                            RequestDelete(params2);
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
                    mAdapter1.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            Bundle bundle = new Bundle();
                            bundle.putString("y_goods_id", list1.get(i).getGoods_info().getYGoodsId());
                            CommonUtil.gotoActivityWithData(FootprintActivity.this, ProductDetailActivity.class, bundle, false);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                    recyclerView.setAdapter(mAdapter1);
                    /*
                    //吸顶效果
                    mAdapter1 = new FootpriintAdapter(FootprintActivity.this, list1);
                    mAdapter1.setOnHeaderClickListener(new GroupedRecyclerViewAdapter.OnHeaderClickListener() {
                        @Override
                        public void onHeaderClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder,
                                                  int groupPosition) {

                        }
                    });

                    mAdapter1.setOnChildClickListener(new GroupedRecyclerViewAdapter.OnChildClickListener() {
                        @Override
                        public void onChildClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder,
                                                 int groupPosition, int childPosition) {
                        }
                    });
                    recyclerView.setAdapter(mAdapter1);*/


                } else {
                    showEmptyPage();
                }
            }
        });

    }

    private void RequestMore1(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Footprint, params, headerMap, new CallBackUtil<ShangPingModel>() {
            @Override
            public ShangPingModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page1--;
            }

            @Override
            public void onResponse(ShangPingModel response) {
                hideProgress();
                List<ShangPingModel.ListBean> list_1 = new ArrayList<>();
                list_1 = response.getList();
                if (list_1.size() == 0) {
                    page1--;
                    myToast(getString(R.string.app_nomore));
                } else {
                    list1.addAll(list_1);
                    mAdapter1.notifyDataSetChanged();
                }
            }
        });
    }

    /**
     * 商家
     *
     * @param params
     */
    private void Request2(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Footprint, params, headerMap, new CallBackUtil<MenDianModel>() {
            @Override
            public MenDianModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(MenDianModel response) {
                hideProgress();
                list2 = response.getList();
                if (list2.size() > 0) {
                    showContentPage();
                    mAdapter2 = new CommonAdapter<MenDianModel.ListBean>
                            (FootprintActivity.this, R.layout.item_mendian, list2) {
                        @Override
                        protected void convert(ViewHolder holder, MenDianModel.ListBean model, int position) {
                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(FootprintActivity.this)
                                    .load(URLs.IMGHOST + model.getStore_info().getPicture())
                                    .centerCrop()
                                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(imageView1);//加载图片
                            holder.setText(R.id.tv_name, model.getStore_info().getVName());
                            holder.setText(R.id.tv_fen, model.getStore_info().getReview());
                            holder.setText(R.id.tv_content, model.getStore_info().getIntroduce());
                            holder.setText(R.id.tv_addr, model.getStore_info().getAddress());
                            //删除
                            holder.getView(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    showToast("确认删除该足迹吗？", "确认", "取消", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                            showProgress(true, "正在删除...");
                                            HashMap<String, String> params2 = new HashMap<>();
                                            params2.put("y_user_footprint_id", model.getYUserFootprintId());
                                            params2.put("u_token", localUserInfo.getToken());
                                            RequestDelete(params2);
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
                    mAdapter2.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            Bundle bundle = new Bundle();
                            bundle.putString("id", list2.get(i).getStore_info().getYStoreId());
                            bundle.putString("longitude", localUserInfo.getLongitude());
                            bundle.putString("latitude", localUserInfo.getLatitude());
                            CommonUtil.gotoActivityWithData(FootprintActivity.this, StoreDetailActivity.class, bundle, false);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                    recyclerView.setAdapter(mAdapter2);
                } else {
                    showEmptyPage();
                }
            }
        });

    }

    private void RequestMore2(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Footprint, params, headerMap, new CallBackUtil<MenDianModel>() {
            @Override
            public MenDianModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page2--;
            }

            @Override
            public void onResponse(MenDianModel response) {
                hideProgress();
                List<MenDianModel.ListBean> list_1 = new ArrayList<>();
                list_1 = response.getList();
                if (list_1.size() == 0) {
                    page2--;
                    myToast(getString(R.string.app_nomore));
                } else {
                    list2.addAll(list_1);
                    mAdapter2.notifyDataSetChanged();
                }
            }
        });
    }

    /**
     * 删除
     *
     * @param params
     */
    private void RequestDelete(HashMap<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.DeleteFootprint, params, headerMap, new CallBackUtil<Object>() {
            @Override
            public Object onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(Object response) {
                hideProgress();
                myToast("删除成功");
                requestServer();
            }
        });
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.textView1:
                //商品
                type = 1;
                category = 1;//1为商品  2为商家
                textView1.setTextColor(getResources().getColor(R.color.white));
                textView1.setBackgroundResource(R.drawable.yuanjiao_5_lanse_left);
                textView2.setTextColor(getResources().getColor(R.color.black));
                textView2.setBackgroundResource(R.drawable.yuanjiaobiankuang_5_huise_right);
                requestServer();
                break;
            case R.id.textView2:
                //商家
                type = 2;
                category = 2;//1为商品  2为商家
                textView1.setTextColor(getResources().getColor(R.color.black));
                textView1.setBackgroundResource(R.drawable.yuanjiaobiankuang_5_huise_left);
                textView2.setTextColor(getResources().getColor(R.color.white));
                textView2.setBackgroundResource(R.drawable.yuanjiao_5_lanse_right);
                requestServer();
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }
}
