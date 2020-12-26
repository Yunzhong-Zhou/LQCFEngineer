package com.chetu.engineer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.MyWalletModel;
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
 * Created by zyz on 2020/6/21.
 * 交易明细
 */
public class TransactionDetailActivity extends BaseActivity {
    int page = 0, is_integral = 1;//1为资金明细 2为积分明细
    private RecyclerView recyclerView;
    List<MyWalletModel.ListBean> list = new ArrayList<>();
    CommonAdapter<MyWalletModel.ListBean> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactiondetail);
        mImmersionBar.reset()
                .statusBarColor(R.color.blue)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
//                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
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
                params.put("is_integral", is_integral + "");
                params.put("u_token", localUserInfo.getToken());
                Request(params);
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("page", page + "");
                params.put("is_integral", is_integral + "");
                RequestMore(params);
            }
        });

        recyclerView = findViewByID_My(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    protected void initData() {
        requestServer();
    }
    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 0;
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("is_integral", is_integral + "");
        params.put("u_token", localUserInfo.getToken());
        //获取列表
        Request(params);
    }
    /**
     * 获取交易明细
     *
     * @param params
     */
    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.MyWallet, params, headerMap, new CallBackUtil<MyWalletModel>() {
            @Override
            public MyWalletModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(MyWalletModel response) {
                hideProgress();
                list = response.getList();
                if (list.size() > 0) {
                    showContentPage();
                    mAdapter = new CommonAdapter<MyWalletModel.ListBean>
                            (TransactionDetailActivity.this, R.layout.item_mywallet, list) {
                        @Override
                        protected void convert(ViewHolder holder, MyWalletModel.ListBean model, int position) {
                            holder.setText(R.id.tv_title, model.getMsg());
                            holder.setText(R.id.tv_time, model.getCreateDate());
                            holder.setText(R.id.tv_balance, "余额：¥" + model.getReMoney());
                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            TextView tv_money = holder.getView(R.id.tv_money);
                            if (model.getNature() == 1) {//1收入 2支出
                                imageView1.setImageResource(R.mipmap.ic_moenyjia);
                                tv_money.setText("+¥" + model.getMoney());

                            } else {
                                imageView1.setImageResource(R.mipmap.ic_moneyjian);
                                tv_money.setText("-¥" + model.getMoney());
                            }
                        }
                    };
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {

                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                    recyclerView.setAdapter(mAdapter);
                } else {
                    showEmptyPage();
                }
            }
        });
    }

    private void RequestMore(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.MyCar, params, headerMap, new CallBackUtil<MyWalletModel>() {
            @Override
            public MyWalletModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(MyWalletModel response) {
                hideProgress();
                List<MyWalletModel.ListBean> list1 = new ArrayList<>();
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
        titleView.setTitle("交易明细");
        titleView.setTitleColor(R.color.white);
        titleView.setLeftBtn(R.mipmap.ic_return_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleView.setBackground(R.color.blue);
    }
}
