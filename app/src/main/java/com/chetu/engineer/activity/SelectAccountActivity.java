package com.chetu.engineer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.MyBankCardModel;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.utils.CommonUtil;
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
 * Created by zyz on 2020/6/12.
 * 选择账户
 */
public class SelectAccountActivity extends BaseActivity {
    int i = -1, type = 0;
    TextView textView;
    private RecyclerView recyclerView;
    List<MyBankCardModel.ListBean> list = new ArrayList<>();
    CommonAdapter<MyBankCardModel.ListBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectaccount);
        mImmersionBar.reset()
                .statusBarColor(R.color.blue)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
//                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestServer();
    }

    @Override
    protected void initView() {
        textView = findViewByID_My(R.id.textView);
        recyclerView = findViewByID_My(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    protected void initData() {
        type = getIntent().getIntExtra("type", 0);
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        Map<String, String> params = new HashMap<>();
        params.put("u_token", localUserInfo.getToken());
        //获取银行卡
        RequestBankCard(params);
    }

    /**
     * 获取交易明细
     *
     * @param params
     */
    private void RequestBankCard(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.MyBankCard, params, headerMap, new CallBackUtil<MyBankCardModel>() {
            @Override
            public MyBankCardModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(MyBankCardModel response) {
                hideProgress();
                list = response.getList();
                textView.setText("我的卡片 共" + list.size() + "张");
                if (list.size() > 0) {
                    showContentPage();
                    mAdapter = new CommonAdapter<MyBankCardModel.ListBean>
                            (SelectAccountActivity.this, R.layout.item_mybankcard, list) {
                        @Override
                        protected void convert(ViewHolder holder, MyBankCardModel.ListBean model, int position) {
                            holder.setText(R.id.tv_bankname, model.getVBank());
                            TextView tv_num = holder.getView(R.id.tv_num);
                            if (model.getVAccount() != null && !model.getVAccount().equals("")) {
                                tv_num.setText("******" + model.getVAccount().substring(model.getVAccount().length() - 4));
                                String[] strArr = model.getVAccount().split(" ");
                                for (int i = 0; i < strArr.length; i++) {
                                    if (i == (strArr.length - 1)) {
                                        tv_num.setText("******" + strArr[i]);
                                    }
                                }
                            }
                            holder.setText(R.id.tv_banktype, model.getVCay());

                            LinearLayout ll_jiebang = holder.getView(R.id.ll_jiebang);
                            TextView tv_shouqi = holder.getView(R.id.tv_shouqi);
                            if (i == position) {
                                ll_jiebang.setVisibility(View.VISIBLE);
                                tv_shouqi.setVisibility(View.VISIBLE);
                            } else {
                                ll_jiebang.setVisibility(View.GONE);
                                tv_shouqi.setVisibility(View.GONE);
                            }

                            holder.getView(R.id.tv_jiebang).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    showToast("确认解绑该银行卡吗？", "确认", "取消", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                            showProgress(true, "正在删除...");
                                            HashMap<String, String> params2 = new HashMap<>();
                                            params2.put("y_with_account_id", model.getYWithAccountId());
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

                            tv_shouqi.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    i = -1;
                                    mAdapter.notifyDataSetChanged();
                                }
                            });


                        }
                    };
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int itme) {
                            if (i == itme) {
                                if (type == 10001) {
                                    Intent resultIntent = new Intent();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("bankname", list.get(itme).getVBank());
//                                    bundle.putString("banknum", list.get(itme).getVAccount());
                                    bundle.putString("banknum", list.get(itme).getVAccount().substring(list.get(itme).getVAccount().length() - 4));

                                    bundle.putString("banktype", list.get(itme).getVCay());
                                    bundle.putString("bankid", list.get(itme).getYWithAccountId());
                                    resultIntent.putExtras(bundle);
                                    SelectAccountActivity.this.setResult(RESULT_OK, resultIntent);
                                    finish();
                                }
                            } else {
                                i = itme;
                                mAdapter.notifyDataSetChanged();
                            }
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

    /**
     * 解绑
     *
     * @param params
     */
    private void RequestDelete(HashMap<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.DeleteBankCard, params, headerMap, new CallBackUtil<Object>() {
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
                myToast("解绑成功");
                requestServer();
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.rl_add:
                //添加银行卡
                CommonUtil.gotoActivity(this, AddBankCardActivity.class, false);
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle("选择提现账户");
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
