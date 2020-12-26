package com.chetu.engineer.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.StoreDetailModel_WenDa;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.utils.CommonUtil;
import com.cy.dialog.BaseDialog;
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
 * Created by zyz on 2020/7/1.
 * 提问列表
 */
public class TiWenListActivity extends BaseActivity {
    int page = 0;
    String y_store_id = "";

    RecyclerView recyclerView;
    List<StoreDetailModel_WenDa.ListBean> list = new ArrayList<>();
    CommonAdapter<StoreDetailModel_WenDa.ListBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiwenlist);
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
                params.put("y_store_id", y_store_id);
                params.put("u_token", localUserInfo.getToken());
                Request(params);
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
                params.put("page", page + "");
                params.put("y_store_id", y_store_id);
                params.put("u_token", localUserInfo.getToken());
                RequestMore(params);
            }
        });
        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        y_store_id = getIntent().getStringExtra("y_store_id");
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 0;
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("y_store_id", y_store_id);
        params.put("u_token", localUserInfo.getToken());
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.StoreDetail_WenDa, params, headerMap, new CallBackUtil<StoreDetailModel_WenDa>() {
            @Override
            public StoreDetailModel_WenDa onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
//                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(StoreDetailModel_WenDa response) {
                hideProgress();
                list = response.getList();
                /*for (int i = 0; i < list.size(); i++) {
                    list.get(i).setExpand(false);//设置不展开
                }*/
                if (list.size() > 0) {
                    showContentPage();
                    mAdapter = new CommonAdapter<StoreDetailModel_WenDa.ListBean>
                            (TiWenListActivity.this, R.layout.item_tiwenlist_header, list) {
                        @Override
                        protected void convert(ViewHolder holder, StoreDetailModel_WenDa.ListBean model, int position) {
                            ImageView iv_head1 = holder.getView(R.id.iv_head1);
                            Glide.with(TiWenListActivity.this).load(URLs.IMGHOST + model.getUser_info().getHeadPortrait())
                                    .centerCrop()
//                                    .apply(RequestOptions.bitmapTransform(new
//                                            RoundedCorners(CommonUtil.dip2px(TiWenListActivity.this, 5))))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(iv_head1);//加载图片
                            holder.setText(R.id.tv_title1, model.getMsg());
                            holder.setText(R.id.tv_name, model.getUser_info().getUserName());

                            LinearLayout ll_huida = holder.getView(R.id.ll_huida);

                            if (model.getC_list().size() > 0) {
                                //有回答
                                ll_huida.setVisibility(View.VISIBLE);
                                holder.setText(R.id.tv_title2, model.getC_list().get(0).getMsg());
                                ImageView iv_head2 = holder.getView(R.id.iv_head2);
                                Glide.with(TiWenListActivity.this).load(URLs.IMGHOST + model.getC_list().get(0).getUser_info().getHeadPortrait())
                                        .centerCrop()
//                                    .apply(RequestOptions.bitmapTransform(new
//                                            RoundedCorners(CommonUtil.dip2px(TiWenListActivity.this, 5))))
                                        .placeholder(R.mipmap.loading)//加载站位图
                                        .error(R.mipmap.zanwutupian)//加载失败
                                        .into(iv_head2);//加载图片


                                LinearLayout ll_huida_head = holder.getView(R.id.ll_huida_head);
                                TextView tv_quanbu1 = holder.getView(R.id.tv_quanbu1);
                                TextView tv_quanbu2 = holder.getView(R.id.tv_quanbu2);
                                tv_quanbu1.setText("全部" + model.getC_list().size() + "个回答");
                                tv_quanbu2.setText("全部" + model.getC_list().size() + "个回答");

                                RecyclerView rv_huida = holder.getView(R.id.rv_huida);
                                rv_huida.setLayoutManager(new LinearLayoutManager(TiWenListActivity.this));
                                if (model.isExpand()) {
                                    //展开
                                    ll_huida_head.setVisibility(View.GONE);
                                    tv_quanbu1.setVisibility(View.GONE);
                                    tv_quanbu2.setVisibility(View.VISIBLE);
                                    rv_huida.setVisibility(View.VISIBLE);

                                    CommonAdapter<StoreDetailModel_WenDa.ListBean.CListBean> ca = new CommonAdapter<StoreDetailModel_WenDa.ListBean.CListBean>
                                            (TiWenListActivity.this, R.layout.item_tiwenlist_huida, model.getC_list()) {
                                        @Override
                                        protected void convert(ViewHolder holder, StoreDetailModel_WenDa.ListBean.CListBean model, int position) {
                                            holder.setText(R.id.textView1, model.getUser_info().getUserName());
                                            holder.setText(R.id.textView2, model.getCreateDate());
                                            holder.setText(R.id.textView3, model.getMsg());

                                            ImageView imageView1 = holder.getView(R.id.imageView);
                                            Glide.with(TiWenListActivity.this)
                                                    .load(URLs.IMGHOST + model.getUser_info().getHeadPortrait())
                                                    .centerCrop()
                                                    .placeholder(R.mipmap.loading)//加载站位图
                                                    .error(R.mipmap.zanwutupian)//加载失败
                                                    .into(imageView1);//加载图片
                                        }
                                    };
                                    rv_huida.setAdapter(ca);

                                } else {
                                    //未展开
                                    ll_huida_head.setVisibility(View.VISIBLE);
                                    tv_quanbu1.setVisibility(View.VISIBLE);
                                    tv_quanbu2.setVisibility(View.GONE);
                                    rv_huida.setVisibility(View.GONE);
                                }

                            } else {
                                //没有回答
                                ll_huida.setVisibility(View.GONE);
                            }
                            holder.getView(R.id.tv_quanbu1).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //展开
                                    /*for (int i = 0; i < list.size(); i++) {
                                        if (list.get(position).isExpand() == false) {
                                            list.get(position).setExpand(true);
                                        } else {
                                            list.get(position).setExpand(false);
                                        }
                                    }*/
                                    if (model.isExpand() == false) {
                                        model.setExpand(true);
                                    }

                                    mAdapter.notifyDataSetChanged();
                                }
                            });
                            holder.getView(R.id.tv_quanbu2).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //收起
                                    if (model.isExpand() == true) {
                                        model.setExpand(false);
                                    }

                                    mAdapter.notifyDataSetChanged();
                                }
                            });
                            holder.getView(R.id.tv_huida).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //回答
                                    dialog = new BaseDialog(TiWenListActivity.this);
                                    dialog.contentView(R.layout.dialog_edit)
                                            .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                    ViewGroup.LayoutParams.WRAP_CONTENT))
                                            .animType(BaseDialog.AnimInType.CENTER)
                                            .canceledOnTouchOutside(true)
                                            .dimAmount(0.8f)
                                            .show();
                                    TextView tv_title = dialog.findViewById(R.id.tv_title);
                                    tv_title.setText("回答" + model.getUser_info().getUserName());
                                    final EditText editText1 = dialog.findViewById(R.id.editText1);
                                    editText1.setHint("请输入回答内容");
//                editText1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                                    dialog.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (!editText1.getText().toString().trim().equals("")) {
                                                CommonUtil.hideSoftKeyboard_fragment(v, TiWenListActivity.this);
                                                dialog.dismiss();
                                                showProgress(true, getString(R.string.app_loading1));
                                                Map<String, String> params = new HashMap<>();
                                                params.put("msg", editText1.getText().toString().trim());
//                                                params.put("y_parent_id", model.getYParentId());
                                                params.put("y_parent_id", model.getYStoreQuesAnsId());//用户提问 为0  回答应填写y_store_ques_ans_id
                                                params.put("y_store_id", model.getYStoreId());
                                                params.put("u_token", localUserInfo.getToken());
                                                RequestUpData(params);
                                            } else {
                                                myToast("请输入回复内容");
                                            }
                                        }
                                    });
                                    dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                        }
                                    });
                                }
                            });
                        }
                    };
                    /*mAdapter1.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            Bundle bundle = new Bundle();
                            bundle.putString("y_goods_id", list1.get(i).getYGoodsId());
                            CommonUtil.gotoActivityWithData(ProductListActivity.this, ProductDetailActivity.class, bundle, false);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });*/
                    recyclerView.setAdapter(mAdapter);
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
        OkhttpUtil.okHttpPost(URLs.StoreDetail_WenDa, params, headerMap, new CallBackUtil<StoreDetailModel_WenDa>() {
            @Override
            public StoreDetailModel_WenDa onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
//                myToast(err);
                myToast(getString(R.string.app_nomore));
                page--;
            }

            @Override
            public void onResponse(StoreDetailModel_WenDa response) {
                hideProgress();
                List<StoreDetailModel_WenDa.ListBean> list_1 = new ArrayList<>();
                list_1 = response.getList();
                if (list_1.size() == 0) {
                    page--;
                    myToast(getString(R.string.app_nomore));
                } else {
                    list.addAll(list_1);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void RequestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.HuiDa, params, headerMap, new CallBackUtil() {
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
                myToast("回答成功");
                requestServer();
            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle("门店问答");
        /*titleView.showRightTextview("发起提问", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("y_store_id", y_store_id);
                CommonUtil.gotoActivityWithData(TiWenListActivity.this, AddTiWenActivity.class, bundle, false);
            }
        });*/
    }
}
