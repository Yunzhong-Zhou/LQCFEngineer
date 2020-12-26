package com.chetu.engineer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.LunTanModel_Collect;
import com.chetu.engineer.model.MenDianModel_Collect;
import com.chetu.engineer.model.ShangPingModel_Collect;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
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
 * 我的收藏
 */
public class MyCollectionActivity extends BaseActivity {
    int type = 1,category = 1;//1为商品  2为商家  3为论坛
    LinearLayout linearLayout1, linearLayout2, linearLayout3;
    TextView textView1, textView2, textView3;
    View view1, view2, view3;

    //列表数据
    int page1 = 0, page2 = 0, page3 = 0;
    private RecyclerView recyclerView;
    List<ShangPingModel_Collect.ListBean> list1 = new ArrayList<>();
    CommonAdapter<ShangPingModel_Collect.ListBean> mAdapter1;

    List<MenDianModel_Collect.ListBean> list2 = new ArrayList<>();
    CommonAdapter<MenDianModel_Collect.ListBean> mAdapter2;

    List<LunTanModel_Collect.ListBean> list3 = new ArrayList<>();
    CommonAdapter<LunTanModel_Collect.ListBean> mAdapter3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycollection);
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
                } else if (type == 2) {
                    page2 = 0;
                    params.put("page", page2 + "");
                    Request2(params);
                } else if (type == 3) {
                    page3 = 0;
                    params.put("page", page3 + "");
                    Request3(params);
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
                } else if (type == 2) {
                    page2++;
                    params.put("page", page2 + "");
                    RequestMore2(params);
                }else if (type == 3) {
                    page3++;
                    params.put("page", page3 + "");
                    RequestMore3(params);
                }
            }
        });

        recyclerView = findViewByID_My(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        view3 = findViewByID_My(R.id.view3);

    }

    @Override
    protected void initData() {
        requestServer();
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
        } else if (type == 2) {
            page2 = 0;
            params.put("page", page2 + "");
            Request3(params);
        } else if (type == 3) {
            page3 = 0;
            params.put("page", page3 + "");
            Request2(params);
        }
    }

    /**
     * 商品
     *
     * @param params
     */
    private void Request1(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Collect, params, headerMap, new CallBackUtil<ShangPingModel_Collect>() {
            @Override
            public ShangPingModel_Collect onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(ShangPingModel_Collect response) {
                hideProgress();
                list1 = response.getList();
                if (list1.size() > 0) {
                    showContentPage();
                    mAdapter1 = new CommonAdapter<ShangPingModel_Collect.ListBean>
                            (MyCollectionActivity.this, R.layout.item_shangping, list1) {
                        @Override
                        protected void convert(ViewHolder holder, ShangPingModel_Collect.ListBean model, int position) {
                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(MyCollectionActivity.this)
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
                                    showToast("确认删除该收藏吗？", "确认", "取消", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                            showProgress(true, "正在删除...");
                                            HashMap<String, String> params2 = new HashMap<>();
                                            params2.put("y_user_collection_id", model.getYUserCollectionId());
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
        OkhttpUtil.okHttpPost(URLs.Collect, params, headerMap, new CallBackUtil<ShangPingModel_Collect>() {
            @Override
            public ShangPingModel_Collect onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page1--;
            }

            @Override
            public void onResponse(ShangPingModel_Collect response) {
                hideProgress();
                List<ShangPingModel_Collect.ListBean> list_1 = new ArrayList<>();
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
     * 门店
     *
     * @param params
     */
    private void Request2(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Collect, params, headerMap, new CallBackUtil<MenDianModel_Collect>() {
            @Override
            public MenDianModel_Collect onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(MenDianModel_Collect response) {
                hideProgress();
                list2 = response.getList();
                if (list2.size() > 0) {
                    showContentPage();
                    mAdapter2 = new CommonAdapter<MenDianModel_Collect.ListBean>
                            (MyCollectionActivity.this, R.layout.item_mendian, list2) {
                        @Override
                        protected void convert(ViewHolder holder, MenDianModel_Collect.ListBean model, int position) {
                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(MyCollectionActivity.this)
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
                                    showToast("确认删除该收藏吗？", "确认", "取消", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                            showProgress(true, "正在删除...");
                                            HashMap<String, String> params2 = new HashMap<>();
                                            params2.put("y_user_collection_id", model.getYUserCollectionId());
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
        OkhttpUtil.okHttpPost(URLs.Collect, params, headerMap, new CallBackUtil<MenDianModel_Collect>() {
            @Override
            public MenDianModel_Collect onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page2--;
            }

            @Override
            public void onResponse(MenDianModel_Collect response) {
                hideProgress();
                List<MenDianModel_Collect.ListBean> list_1 = new ArrayList<>();
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
     * 论坛
     *
     * @param params
     */
    private void Request3(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Collect, params, headerMap, new CallBackUtil<LunTanModel_Collect>() {
            @Override
            public LunTanModel_Collect onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(LunTanModel_Collect response) {
                hideProgress();
                list3 = response.getList();
                if (list3.size() > 0) {
                    showContentPage();
                    mAdapter3 = new CommonAdapter<LunTanModel_Collect.ListBean>
                            (MyCollectionActivity.this, R.layout.item_mendian, list3) {
                        @Override
                        protected void convert(ViewHolder holder, LunTanModel_Collect.ListBean model, int position) {
                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(MyCollectionActivity.this)
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
                                    showToast("确认删除该收藏吗？", "确认", "取消", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                            showProgress(true, "正在删除...");
                                            HashMap<String, String> params2 = new HashMap<>();
                                            params2.put("y_user_collection_id", model.getYUserCollectionId());
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

    private void RequestMore3(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Collect, params, headerMap, new CallBackUtil<LunTanModel_Collect>() {
            @Override
            public LunTanModel_Collect onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page3--;
            }

            @Override
            public void onResponse(LunTanModel_Collect response) {
                hideProgress();
                List<LunTanModel_Collect.ListBean> list_1 = new ArrayList<>();
                list_1 = response.getList();
                if (list_1.size() == 0) {
                    page3--;
                    myToast(getString(R.string.app_nomore));
                } else {
                    list3.addAll(list_1);
                    mAdapter3.notifyDataSetChanged();
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
        OkhttpUtil.okHttpPost(URLs.DeleteCollect, params, headerMap, new CallBackUtil<Object>() {
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
            case R.id.linearLayout1:
                //商品
                type = 1;
                category = 1;//1为商品  2为商家  3为论坛
                changeUI();
                break;
            case R.id.linearLayout2:
                //论坛
                type = 2;
                category = 3;//1为商品  2为商家  3为论坛
                changeUI();
                break;
            case R.id.linearLayout3:
                //门店
                type = 3;
                category = 2;//1为商品  2为商家  3为论坛
                changeUI();
                break;
        }
    }

    private void changeUI() {
        switch (type) {
            case 1:
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);
                requestServer();
                break;
            case 2:
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.INVISIBLE);
                requestServer();
                break;
            case 3:
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.VISIBLE);
                requestServer();
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle("我的收藏");
    }
}
