package com.chetu.engineer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.StoreServiceModel;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
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
 * Created by Mr.Z on 2020/7/15.
 * //添加服务
 */
public class AddServiceActivity extends BaseActivity {
    //添加询价项目
    /*JSONArray jsonArray = new JSONArray();
    RecyclerView recyclerView1;
    CommonAdapter<AddServiceModel> mAdapter1;
    List<AddServiceModel> list1 = new ArrayList<>();*/
    /**
     * 服务内容
     */
    String y_order_id = "", y_store_id = "";
    RecyclerView recyclerView_sv;
    CommonAdapter<StoreServiceModel.ListBean> mAdapter_sv;
    List<StoreServiceModel.ListBean> list_sv = new ArrayList<>();
    int i1 = 0, i2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addservice);
        mImmersionBar.reset()
                .statusBarColor(R.color.background)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @Override
    protected void initView() {
        //服务列表
        recyclerView_sv = findViewByID_My(R.id.recyclerView_sv);
        recyclerView_sv.setLayoutManager(new LinearLayoutManager(this));
        //询价项目
        /*recyclerView1 = findViewByID_My(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));*/

        findViewByID_My(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //选择的服务
                JSONArray jsonArray = new JSONArray();
                for (StoreServiceModel.ListBean listBean : list_sv) {
                    for (StoreServiceModel.ListBean.ClistBean vListBean : listBean.getClist()) {
                        if (vListBean.isIsgouxuan()) {
                            try {
                                JSONObject object1 = new JSONObject();
                                object1.put("y_order_id", y_order_id);
                                object1.put("y_store_service_id", vListBean.getYStoreServiceId());
                                jsonArray.put(object1);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                if (jsonArray.length() > 0) {
                    showToast("确认添加吗？", "确认", "取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            showProgress(true, "正在添加，请稍候...");
                            Map<String, String> params = new HashMap<>();
                            params.put("u_token", localUserInfo.getToken());
                            params.put("order_server_json", jsonArray.toString());
                            RequestAdd(params);
                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                } else {
                    myToast("请选择服务");
                }

            }
        });
    }


    @Override
    protected void initData() {
        y_store_id = getIntent().getStringExtra("y_store_id");
        y_order_id = getIntent().getStringExtra("y_order_id");
        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading));
        //获取服务项目
        HashMap<String, String> params2 = new HashMap<>();
        params2.put("y_store_id", y_store_id);
        params2.put("parent_id", "0");
        RequestService(params2, 0);
    }

    /**
     * 获取筛选列表
     *
     * @param params
     * @param type
     */
    private void RequestService(HashMap<String, String> params, int type) {
        OkhttpUtil.okHttpPost(URLs.ServiceList_Store, params, headerMap, new CallBackUtil<StoreServiceModel>() {
            @Override
            public StoreServiceModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
            }

            @Override
            public void onResponse(StoreServiceModel response) {
                hideProgress();
                list_sv = response.getList();
                mAdapter_sv = new CommonAdapter<StoreServiceModel.ListBean>
                        (AddServiceActivity.this, R.layout.item_carservice_sv, list_sv) {
                    @Override
                    protected void convert(ViewHolder holder, StoreServiceModel.ListBean model, int position) {
                        holder.setText(R.id.title, model.getYStateValue());

                        RecyclerView rv = holder.getView(R.id.rv);
                        rv.setLayoutManager(new LinearLayoutManager(AddServiceActivity.this));
                        ImageView iv = holder.getView(R.id.iv);

                        if (i1 == position) {
                            iv.setImageResource(R.mipmap.ic_down_black);
                            if (list_sv.size() > 0) {
                                rv.setVisibility(View.VISIBLE);
                            }

                            CommonAdapter<StoreServiceModel.ListBean.ClistBean> ca = new CommonAdapter<StoreServiceModel.ListBean.ClistBean>
                                    (AddServiceActivity.this, R.layout.item_carservice_sv_child, model.getClist()) {
                                @Override
                                protected void convert(ViewHolder holder, StoreServiceModel.ListBean.ClistBean listBean, int item) {
                                    holder.setText(R.id.textView, listBean.getYStateValue());
                                    ImageView imageView = holder.getView(R.id.imageView);
                                    if (listBean.isIsgouxuan()) {
                                        imageView.setImageResource(R.mipmap.ic_xuanzhong);
                                    } else {
                                        imageView.setImageResource(R.mipmap.ic_weixuan);
                                    }
                                }
                            };
                            ca.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    if (!model.getClist().get(i).isIsgouxuan())
                                        model.getClist().get(i).setIsgouxuan(true);
                                    else model.getClist().get(i).setIsgouxuan(false);

                                    ca.notifyDataSetChanged();
                                }

                                @Override
                                public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    return false;
                                }
                            });
                            rv.setAdapter(ca);
                        } else {
                            iv.setImageResource(R.mipmap.ic_next_black);
                            rv.setVisibility(View.GONE);
                        }
                    }
                };
                mAdapter_sv.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        if (i != i1) {
                            i1 = i;
                            mAdapter_sv.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                recyclerView_sv.setAdapter(mAdapter_sv);
            }
        });
    }

    /**
     * 添加服务
     *
     * @param params
     */
    private void RequestAdd(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ADDService, params, headerMap, new CallBackUtil<Object>() {
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

    @Override
    protected void updateView() {
        titleView.setTitle("添加服务项目");
        titleView.setBackground(R.color.background);
    }
}
