package com.chetu.engineer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.Fragment2Model;
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
 * 邀请有奖
 */
public class InviteActivity extends BaseActivity {
    //列表数据
    int page = 0;
    private RecyclerView recyclerView;
    List<Fragment2Model.ListBean> list = new ArrayList<>();
    CommonAdapter<Fragment2Model.ListBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        mImmersionBar.reset()
                .statusBarColor(R.color.background)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
//        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
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
                params.put("u_token", localUserInfo.getToken());
                /*params.put("y_parent_id", y_parent_id);
                params.put("y_service_id", y_service_id);
                params.put("page", page + "");
                params.put("longitude", longitude);
                params.put("latitude", latitude);*/
                Request(params);
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                /*params.put("y_parent_id", y_parent_id);
                params.put("y_service_id", y_service_id);
                params.put("page", page + "");
                params.put("longitude", longitude);
                params.put("latitude", latitude);*/
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
        params.put("u_token", localUserInfo.getToken());
        /*params.put("y_parent_id", y_parent_id);
        params.put("y_service_id", y_service_id);
        params.put("page", page + "");
        params.put("longitude", longitude);
        params.put("latitude", latitude);*/
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment3, params, headerMap, new CallBackUtil<Fragment2Model>() {
            @Override
            public Fragment2Model onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
                myToast(err);
            }

            @Override
            public void onResponse(Fragment2Model response) {
                hideProgress();
                list = response.getList();
                if (list.size() > 0) {
                    showContentPage();
                    mAdapter = new CommonAdapter<Fragment2Model.ListBean>
                            (InviteActivity.this, R.layout.item_invite, list) {
                        @Override
                        protected void convert(ViewHolder holder, Fragment2Model.ListBean model, int position) {
                            TextView tv_qiansan = holder.getView(R.id.tv_qiansan);
                            TextView tv_num = holder.getView(R.id.tv_num);
                            if (position == 0) {
                                tv_qiansan.setText("1");
                                tv_qiansan.setBackgroundResource(R.mipmap.bg_num1);
                                tv_qiansan.setVisibility(View.VISIBLE);
                                tv_num.setVisibility(View.GONE);
                            } else if (position == 1) {
                                tv_qiansan.setText("2");
                                tv_qiansan.setBackgroundResource(R.mipmap.bg_num2);
                                tv_qiansan.setVisibility(View.VISIBLE);
                                tv_num.setVisibility(View.GONE);
                            } else if (position == 2) {
                                tv_qiansan.setText("3");
                                tv_qiansan.setBackgroundResource(R.mipmap.bg_num3);
                                tv_qiansan.setVisibility(View.VISIBLE);
                                tv_num.setVisibility(View.GONE);
                            } else {
                                tv_qiansan.setVisibility(View.GONE);
                                tv_num.setVisibility(View.VISIBLE);
                                tv_num.setText("" + (position + 1));
                            }

                       /* TextView tv1 = holder.getView(R.id.tv1);
                        TextView tv2 = holder.getView(R.id.tv2);
                        LinearLayout ll = holder.getView(R.id.ll);
                        tv1.setText(model.getName());
                        tv2.setText(model.getName());

                        if (item == position) {
                            ll.setVisibility(View.VISIBLE);
                            tv1.setVisibility(View.GONE);
                        } else {
                            ll.setVisibility(View.GONE);
                            tv1.setVisibility(View.VISIBLE);
                        }*/

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
        OkhttpUtil.okHttpPost(URLs.Fragment3, params, headerMap, new CallBackUtil<Fragment2Model>() {
            @Override
            public Fragment2Model onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(Fragment2Model response) {
                hideProgress();
                List<Fragment2Model.ListBean> list1 = new ArrayList<>();
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
        titleView.setTitle("邀请有礼");
        titleView.setBackground(R.color.background);
        titleView.setRightBtn(R.mipmap.ic_wenhao_red, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
