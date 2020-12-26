package com.chetu.engineer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.engineer.R;
import com.chetu.engineer.activity.AddJieCheActivity;
import com.chetu.engineer.activity.DaiFenPeiActivity;
import com.chetu.engineer.activity.DaiFuJianActivity;
import com.chetu.engineer.activity.DaiJieCheActivity;
import com.chetu.engineer.activity.DaiShiGongActivity;
import com.chetu.engineer.activity.JinXingZhongActivity;
import com.chetu.engineer.activity.JiuYuanActivity;
import com.chetu.engineer.activity.MainActivity;
import com.chetu.engineer.activity.SignInActivity;
import com.chetu.engineer.activity.WebContentActivity;
import com.chetu.engineer.activity.YiFuKuanActivity;
import com.chetu.engineer.activity.YiTiCheActivity;
import com.chetu.engineer.activity.YiWanGongActivity;
import com.chetu.engineer.base.BaseFragment;
import com.chetu.engineer.model.Fragment2Model;
import com.chetu.engineer.model.Fragment2Model_DaiFenPei;
import com.chetu.engineer.model.Fragment2Model_DaiJieChe;
import com.chetu.engineer.model.Fragment2Model_JiuYuan;
import com.chetu.engineer.model.Fragment2TabModel;
import com.chetu.engineer.model.Fragment2_TongJiModel;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.popupwindow.PhotoShowDialog;
import com.chetu.engineer.utils.CommonUtil;
import com.chetu.engineer.utils.MyLogger;
import com.cretin.tools.scancode.CaptureActivity;
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

import static android.app.Activity.RESULT_OK;


/**
 * Created by fafukeji01 on 2016/1/6.
 * 工作台
 */
public class Fragment2 extends BaseFragment {
    //tab 数据
    int item = 0;
    RecyclerView rv_tab;
    List<Fragment2TabModel> list_tab = new ArrayList<>();
    CommonAdapter<Fragment2TabModel> mAdapter_tab;

    //列表数据
    int page = 0;
    private RecyclerView recyclerView;
    //待接车数据
    List<Fragment2Model_DaiJieChe.ListBean> list_jieche = new ArrayList<>();
    CommonAdapter<Fragment2Model_DaiJieChe.ListBean> mAdapter_jieche;
    //待分配数据
    List<Fragment2Model_DaiFenPei.ListBean> list_fenpei = new ArrayList<>();
    CommonAdapter<Fragment2Model_DaiFenPei.ListBean> mAdapter_fenpei;

    //待施工=进行中=待复检=待完工=已提车
    List<Fragment2Model.ListBean> list = new ArrayList<>();
    CommonAdapter<Fragment2Model.ListBean> mAdapter1;
    //救援数据
    List<Fragment2Model_JiuYuan.ListBean> list_JiuYuan = new ArrayList<>();
    CommonAdapter<Fragment2Model_JiuYuan.ListBean> mAdapter_JiuYuan;

    RelativeLayout rl_qiandao, rl_duijiangji, rl_jieche, rl_xiaoxi;
    TextView tv_xiaoxinum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        StatusBarUtil.setTransparent(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void onStart() {
        super.onStart();
        /*if (MainActivity.item == 1) {
            requestServer();
        }*/
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.item == 1) {
            //获取列表数据
            requestServer();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        /*if (MainActivity.item == 1) {
            requestServer();
        }*/
    }

    @Override
    protected void initView(View view) {
        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
        //刷新
        setSpringViewMore(true);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //获取统计数量
                Map<String, String> params1 = new HashMap<>();
                params1.put("u_token", localUserInfo.getToken());
                params1.put("y_store_id", localUserInfo.getBelongid());
                RequestTongJi(params1);

                page = 0;
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("page", page + "");
                switch (item) {
                    case 0:
                        //待接车
                        params.put("y_store_id", localUserInfo.getBelongid());
                        Request1(params);
                        break;
                    case 1:
                        //待分配
                        params.put("y_store_id", localUserInfo.getBelongid());
                        Request2(params);
                        break;
                    case 2:
                        //待施工
                    case 3:
                        //进行中
                    case 4:
                        //待复检
                    case 5:
                        //已完工
                        params.put("g_state", item + "");
                        Request3(params);
                        break;
                    case 6:
                        //已提车- 换了位置 为 7已付款
                        params.put("g_state", "7");
                        Request3(params);
                        break;
                    case 7:
                        //已付款--换了位置 为 6已提车
                        params.put("g_state", "6");
                        Request3(params);
                        break;
                    case 8:
                        //救援
                        RequestJiuYuan(params);
                        break;
                }
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("page", page + "");
                switch (item) {//2为待施工 3为进行中 4待复检  5为待完工 6为已提车  7为付款
                    case 0:
                        //待接车
                        /*params.put("y_store_id", localUserInfo.getBelongid());
                        RequestMore1(params);*/
                        hideProgress();
                        myToast("没有更多数据了");
                        break;
                    case 1:
                        //待分配
                       /* params.put("y_store_id", localUserInfo.getBelongid());
                        RequestMore2(params);*/
                        hideProgress();
                        myToast("没有更多数据了");
                        break;
                    case 2:
                        //待施工
                    case 3:
                        //进行中
                    case 4:
                        //待复检
                    case 5:
                        //已完工
                        params.put("g_state", item + "");
                        Request3(params);
                        break;
                    case 6:
                        //已提车- 换了位置 为 7已付款
                        params.put("g_state", "7");
                        Request3(params);
                        break;
                    case 7:
                        //已付款--换了位置 为 6已提车
                        params.put("g_state", "6");
                        Request3(params);
                        break;
                    case 8:
                        //救援
                        RequestJiuYuanMore(params);
                        break;
                }
            }
        });

        rv_tab = findViewByID_My(R.id.rv_tab);
        LinearLayoutManager llm_tab = new LinearLayoutManager(getActivity());
        rv_tab.setLayoutManager(llm_tab);


        recyclerView = findViewByID_My(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLinearLayoutManager);


        rl_qiandao = findViewByID_My(R.id.rl_qiandao);
        rl_duijiangji = findViewByID_My(R.id.rl_duijiangji);
        rl_jieche = findViewByID_My(R.id.rl_jieche);
        rl_qiandao.setOnClickListener(this);
        rl_duijiangji.setOnClickListener(this);
        rl_jieche.setOnClickListener(this);

        rl_xiaoxi = findViewByID_My(R.id.rl_xiaoxi);
        rl_xiaoxi.setOnClickListener(this);
        tv_xiaoxinum = findViewByID_My(R.id.tv_xiaoxinum);

    }

    @Override
    protected void initData() {
        requestServer();
        list_tab.add(new Fragment2TabModel("待接车", 0));
        list_tab.add(new Fragment2TabModel("待分配", 0));
        list_tab.add(new Fragment2TabModel("待施工", 0));
        list_tab.add(new Fragment2TabModel("进行中", 0));
        list_tab.add(new Fragment2TabModel("待复检", 0));
        list_tab.add(new Fragment2TabModel("已完工", 0));

        list_tab.add(new Fragment2TabModel("已付款", 0));
        list_tab.add(new Fragment2TabModel("已提车", 0));


        list_tab.add(new Fragment2TabModel("救援", 0));

        mAdapter_tab = new CommonAdapter<Fragment2TabModel>
                (getActivity(), R.layout.item_fragment2_tab, list_tab) {
            @Override
            protected void convert(ViewHolder holder, Fragment2TabModel model, int position) {
                TextView tv_title = holder.getView(R.id.tv_title);
                TextView tv_num = holder.getView(R.id.tv_num);
                View view = holder.getView(R.id.view);
                tv_title.setText(model.getTitle());
                tv_num.setText(model.getNum() + "");

                if (item == position) {
                    view.setVisibility(View.VISIBLE);
                    tv_title.setTextColor(getResources().getColor(R.color.blue));
                } else {
                    view.setVisibility(View.INVISIBLE);
                    tv_title.setTextColor(getResources().getColor(R.color.black));
                }

            }
        };
        mAdapter_tab.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                if (item != i) {
                    item = i;
                    mAdapter_tab.notifyDataSetChanged();
                    requestServer();
                }

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        rv_tab.setAdapter(mAdapter_tab);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_qiandao:
                //签到
                CommonUtil.gotoActivity(getActivity(), SignInActivity.class);
                break;
            case R.id.rl_duijiangji:
                //对讲机
                break;
            case R.id.rl_jieche:
                //扫描接车
               /* ScanConfig config = new ScanConfig()
                        .setShowFlashlight(true)//是否需要打开闪光灯
                        .setShowGalary(true)//是否需要打开相册
                        .setNeedRing(true);//是否需要提示音
                //ScanConfig 也可以不配置 默认都是打开
                CaptureActivity.launch(this, config);*/

               /* Bundle bundle = new Bundle();
                bundle.putBoolean("isJieCheActivity", false);
                CommonUtil.gotoActivityWithData(getActivity(), ChePaiScannerActivity.class, bundle);*/

                Bundle bundle1 = new Bundle();
                bundle1.putString("license_number", "");
                CommonUtil.gotoActivityWithData(getActivity(), AddJieCheActivity.class, bundle1);
                break;
            case R.id.rl_xiaoxi:
                String url = URLs.KFHOST + "/#/pages/chetu-kf/chetu-kf?token=" + localUserInfo.getToken() +
                        "&kf_userHash=" + localUserInfo.getKfuserhash() +
                        "&nickName=" + localUserInfo.getKfname() +
                        "&headerPic=" + localUserInfo.getKfhead();
                /*WebUtilsConfig config1 =
                        new WebUtilsConfig()
                                .setTitleBackgroundColor(R.color.colorPrimary)//设置标题栏背景色
//                                .setBackText("关闭")//设置返回按钮的文案
                                .setBackBtnRes(R.mipmap.ic_return_black)//设置返回按钮的图标
//                                .setMoreBtnRes(R.mipmap.more_web)//设置更多按钮的图标
                                .setShowBackText(false)//设置是否显示返回按钮的文案
                                .setShowMoreBtn(false)//设置是否显示更多按钮
                                .setShowTitleLine(false)//设置是否显示标题下面的分割线
                                .setShowTitleView(true)//设置是否显示标题栏，网页是全屏的时候可以选择隐藏标题栏
//                                .setTitleBackgroundRes(getResources().getColor(R.color.white))//设置标题栏背景资源
                                .setTitleBackgroundColor(R.color.white)
//                                .setBackTextColor(-1)//设置返回按钮的文案颜色
                                .setTitleTextColor(R.color.black)//设置标题文字颜色
                                .setStateBarTextColorDark(true)//设置状态栏文字颜色是否是暗色，如果你设置了标题栏背景颜色为白色，这里需要设置true，否则状态栏看不到文案了
                                .setTitleLineColor(R.color.app_title_color);//设置标题栏下面的分割线的颜色
                OpenWebActivity.openWebView(getActivity(), url, config1);*/
                Bundle bundle = new Bundle();
                bundle.putString("url", url);
                CommonUtil.gotoActivityWithData(getActivity(), WebContentActivity.class, bundle, false);
                /*//聊天列表
                String url1 = URLs.KFHOST + "/#/pages/chetu-kf/chat_list?token=" + localUserInfo.getToken();
                Bundle bundle1 = new Bundle();
                bundle1.putString("url", url1);
                CommonUtil.gotoActivityWithData(getActivity(), WebContentActivity.class, bundle1, false);*/
                break;
        }
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        //获取统计数量
        Map<String, String> params1 = new HashMap<>();
        params1.put("u_token", localUserInfo.getToken());
        params1.put("y_store_id", localUserInfo.getBelongid());
        RequestTongJi(params1);
        page = 0;
        Map<String, String> params = new HashMap<>();
        params.put("u_token", localUserInfo.getToken());
        params.put("page", page + "");
        switch (item) {
            case 0:
                //待接车
                params.put("y_store_id", localUserInfo.getBelongid());
                Request1(params);
                break;
            case 1:
                //待分配
                params.put("y_store_id", localUserInfo.getBelongid());
                Request2(params);
                break;
            case 2:
                //待施工
            case 3:
                //进行中
            case 4:
                //待复检
            case 5:
                //已完工
                params.put("g_state", item + "");
                Request3(params);
                break;
            case 6:
                //已提车- 换了位置 为 7已付款
                params.put("g_state", "7");
                Request3(params);
                break;
            case 7:
                //已付款--换了位置 为 6已提车
                params.put("g_state", "6");
                Request3(params);
                break;
            case 8:
                //救援
                RequestJiuYuan(params);
                break;
        }
    }

    /**
     * 获取统计数据
     */
    private void RequestTongJi(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment2_TongJi, params, headerMap, new CallBackUtil<Fragment2_TongJiModel>() {
            @Override
            public Fragment2_TongJiModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
//                hideProgress();
//                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(Fragment2_TongJiModel response) {
//                hideProgress();
                list_tab.clear();
                list_tab.add(new Fragment2TabModel("待接车", response.getDai_jie_che_sum()));
                list_tab.add(new Fragment2TabModel("待分配", response.getDai_fen_pai_sum()));
                list_tab.add(new Fragment2TabModel("待施工", response.getDai_shi_gong_sum()));
                list_tab.add(new Fragment2TabModel("进行中", response.getJin_xing_zhong_sum()));
                list_tab.add(new Fragment2TabModel("待复检", response.getDai_fu_jian_sum()));
                list_tab.add(new Fragment2TabModel("已完工", response.getYi_wan_gong_sum()));

                list_tab.add(new Fragment2TabModel("已付款", response.getIs_pay_sum()));
                list_tab.add(new Fragment2TabModel("已提车", response.getYi_ti_che_sum()));

                list_tab.add(new Fragment2TabModel("救援", response.getJiu_yuan_sum()));
                mAdapter_tab.notifyDataSetChanged();

               /* int num = response.getDai_jie_che_sum()
                        +response.getDai_fen_pai_sum()
                        +response.getDai_shi_gong_sum()
                        +response.getJin_xing_zhong_sum()
                        +response.getDai_fu_jian_sum()
                        +response.getYi_wan_gong_sum()
                        +response.getIs_pay_sum()
                        +response.getYi_ti_che_sum()
                        +response.getJiu_yuan_sum();*/
                ((MainActivity) getActivity()).getNavigationBar().setMsgPointCount(1, response.getDai_shi_gong_sum());
            }
        });
    }

    /**
     * 待接车数据
     */
    private void Request1(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment2_0, params, headerMap, new CallBackUtil<Fragment2Model_DaiJieChe>() {
            @Override
            public Fragment2Model_DaiJieChe onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(Fragment2Model_DaiJieChe response) {
                hideProgress();
                list_jieche = response.getList();
                if (list_jieche.size() > 0) {
                    showContentPage();
                    mAdapter_jieche = new CommonAdapter<Fragment2Model_DaiJieChe.ListBean>
                            (getActivity(), R.layout.item_fragment2, list_jieche) {
                        @Override
                        protected void convert(ViewHolder holder, Fragment2Model_DaiJieChe.ListBean model, int position) {
                            holder.setText(R.id.tv_id, "订单编号："+model.getYOrderId());
                            if (model.getUser_sedan_info() !=null){
                                holder.setText(R.id.tv_carnum, model.getUser_sedan_info().getSNumber());//车牌
                            }

                            TextView tv_jieche = holder.getView(R.id.tv_jieche);
                            if (model.getIsPick() == 1) {
                                tv_jieche.setText("需上门接车");//是否接车
                                tv_jieche.setTextColor(getResources().getColor(R.color.red));
                            } else {
                                tv_jieche.setText("车辆到店");//是否接车
                                tv_jieche.setTextColor(getResources().getColor(R.color.black3));
                            }

                            holder.setText(R.id.tv_name, model.getUser_info().getUserName());//昵称

                            if (!model.getAppoinTime().equals("")) {
                                holder.setText(R.id.tv_time, "预约时间:" + model.getAppoinTime());//预约时间
                            } else {
                                holder.setText(R.id.tv_time, "客户到店");//预约时间
                            }
//                            holder.setText(R.id.tv_fukuan,model.get);//是否付款

                            holder.setText(R.id.tv_content, model.getServiceStr());//服务项目
                            TextView tv_btn = holder.getView(R.id.tv_btn);
                            tv_btn.setText("立即接车");
                            tv_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("JieChe", model);
                                    CommonUtil.gotoActivityWithData(getActivity(), AddJieCheActivity.class, bundle, false);
                                }
                            });
                        }
                    };
                    mAdapter_jieche.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            jumpPage(i);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                    recyclerView.setAdapter(mAdapter_jieche);

                } else {
                    showEmptyPage();
                }
            }
        });
    }

    private void RequestMore1(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment2_0, params, headerMap, new CallBackUtil<Fragment2Model_DaiJieChe>() {
            @Override
            public Fragment2Model_DaiJieChe onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(Fragment2Model_DaiJieChe response) {
                hideProgress();
                List<Fragment2Model_DaiJieChe.ListBean> list1 = new ArrayList<>();
                list1 = response.getList();
                if (list1.size() == 0) {
                    page--;
                    myToast(getString(R.string.app_nomore));
                } else {
                    list_jieche.addAll(list1);
                    mAdapter_jieche.notifyDataSetChanged();
                }
            }
        });
    }

    /**
     * 待分配数据
     */
    private void Request2(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment2_1, params, headerMap, new CallBackUtil<Fragment2Model_DaiFenPei>() {
            @Override
            public Fragment2Model_DaiFenPei onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(Fragment2Model_DaiFenPei response) {
                hideProgress();
                list_fenpei = response.getList();
                if (list_fenpei.size() > 0) {
                    showContentPage();
                    mAdapter_fenpei = new CommonAdapter<Fragment2Model_DaiFenPei.ListBean>
                            (getActivity(), R.layout.item_fragment2, list_fenpei) {
                        @Override
                        protected void convert(ViewHolder holder, Fragment2Model_DaiFenPei.ListBean model, int position) {
                            holder.setText(R.id.tv_id, "订单编号："+model.getYOrderId());
                            holder.setText(R.id.tv_carnum, model.getLicenseNumber());//车牌

                            TextView tv_jieche = holder.getView(R.id.tv_jieche);
                            tv_jieche.setText("接车人：" + model.getTechn_info().getUserName());//接车
                            tv_jieche.setTextColor(getResources().getColor(R.color.black3));

                            holder.setText(R.id.tv_name, model.getOwnerName());//昵称
                            holder.setText(R.id.tv_time, "接车时间:" + model.getCreateDate());//预约时间

//                            holder.setText(R.id.tv_fukuan,model.get);//是否付款

                            holder.setText(R.id.tv_content, model.getServiceStr());//服务项目
                            TextView tv_btn = holder.getView(R.id.tv_btn);
                            if (localUserInfo.getUserJob().equals("1")) {
                                tv_btn.setText("立即分配");
                            } else {
                                tv_btn.setText("一键领车");
                            }
                            /*tv_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("AddJieChe", model);
                                    CommonUtil.gotoActivityWithData(getActivity(), AddJieCheActivity.class, bundle, false);
                                }
                            });*/

                        }
                    };
                    mAdapter_fenpei.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            jumpPage(i);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                    recyclerView.setAdapter(mAdapter_fenpei);
                } else {
                    showEmptyPage();
                }
            }
        });
    }

    private void RequestMore2(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment2_1, params, headerMap, new CallBackUtil<Fragment2Model_DaiFenPei>() {
            @Override
            public Fragment2Model_DaiFenPei onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(Fragment2Model_DaiFenPei response) {
                hideProgress();
                List<Fragment2Model_DaiFenPei.ListBean> list1 = new ArrayList<>();
                list1 = response.getList();
                if (list1.size() == 0) {
                    page--;
                    myToast(getString(R.string.app_nomore));
                } else {
                    list_fenpei.addAll(list1);
                    mAdapter_fenpei.notifyDataSetChanged();
                }
            }
        });
    }

    /**
     * 待施工=进行中=待复检=待完工=已提车
     */
    private void Request3(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment2_2, params, headerMap, new CallBackUtil<Fragment2Model>() {
            @Override
            public Fragment2Model onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(Fragment2Model response) {
                hideProgress();
                list = response.getList();
                if (list.size() > 0) {
                    showContentPage();
                    mAdapter1 = new CommonAdapter<Fragment2Model.ListBean>
                            (getActivity(), R.layout.item_fragment2, list) {
                        @Override
                        protected void convert(ViewHolder holder, Fragment2Model.ListBean model, int position) {
                            holder.setText(R.id.tv_id, "订单编号："+model.getYOrderId());
                            holder.setText(R.id.tv_carnum, model.getLicenseNumber());//车牌
                            TextView tv_jieche = holder.getView(R.id.tv_jieche);
                            tv_jieche.setText("接车人：" + model.getTechn_info().getUserName());//接车
                            tv_jieche.setTextColor(getResources().getColor(R.color.red));
                            holder.setText(R.id.tv_name, model.getOwnerName());//昵称


                            /*if (!model.getAppoinTime().equals("")){

                            }*/
                            if (model.getIsPay() == 0) {
                                holder.setText(R.id.tv_fukuan, "未付款");//是否付款
                            } else {
                                holder.setText(R.id.tv_fukuan, "已付款");//是否付款
                            }


                            holder.setText(R.id.tv_content, model.getServiceStr());//服务项目
                            TextView tv_btn = holder.getView(R.id.tv_btn);

                            tv_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String tishi = "";
                                    int g_state = item + 1;
                                    switch (item) {
                                        case 2:
                                            //待施工
                                            tishi = "确认立即施工了吗？";
                                            showToast(tishi, "确认", "取消", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    dialog.dismiss();
                                                    Map<String, String> params = new HashMap<>();
                                                    params.put("u_token", localUserInfo.getToken());
                                                    params.put("y_techn_sedan_id", model.getYTechnSedanId());
                                                    params.put("g_state", g_state + "");//2为待施工 3为进行中 4待复检  5为待完工 6为已提车  7为付款
                                                    RequestChageType(params);
                                                }
                                            }, new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    dialog.dismiss();
                                                }
                                            });
                                            break;
                                        case 3:
                                            //进行中
//                                            tishi = "确认完成施工了吗？";
                                            jumpPage(position);
                                            break;
                                        case 4:
                                            //待复检
                                            tishi = "确认完成复检了吗？";
                                            showToast(tishi, "确认", "取消", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    dialog.dismiss();
                                                    Map<String, String> params = new HashMap<>();
                                                    params.put("u_token", localUserInfo.getToken());
                                                    params.put("y_techn_sedan_id", model.getYTechnSedanId());
                                                    params.put("g_state", g_state + "");//2为待施工 3为进行中 4待复检  5为待完工 6为已提车  7为付款
                                                    RequestChageType(params);
                                                }
                                            }, new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    dialog.dismiss();
                                                }
                                            });
                                            break;
                                        case 5:
                                            //已完工
                                            if (model.getOrder_info().getIsDelivery() == 1) {
                                                //送车到家
                                                tishi = "确认已经送车到家了吗？";
                                            } else {
                                                tishi = "确认联系客户了吗？";
                                            }
                                            showToast(tishi, "确认", "取消", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    dialog.dismiss();
                                                    Map<String, String> params = new HashMap<>();
                                                    params.put("u_token", localUserInfo.getToken());
                                                    params.put("y_techn_sedan_id", model.getYTechnSedanId());
                                                    params.put("g_state", g_state + "");//2为待施工 3为进行中 4待复检  5为待完工 6为已提车  7为付款
                                                    RequestChageType(params);
                                                }
                                            }, new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    dialog.dismiss();
                                                }
                                            });
                                            break;

                                        case 6:
                                            //已提车  换了位置 为 7已付款
                                            return;
                                        case 7:
                                            //已付款
                                            return;
                                    }

                                }
                            });
                            switch (item) {
                                case 2:
                                    //待施工
                                    holder.setText(R.id.tv_time, "分配时间:" + model.getCreateDate());
                                    tv_btn.setBackgroundResource(R.drawable.yuanjiao_5_lanse);
                                    tv_btn.setText("立即施工");
                                    break;
                                case 3:
                                    //进行中
                                    holder.setText(R.id.tv_time, "施工时间:" + model.getCreateDate());
                                    tv_btn.setBackgroundResource(R.drawable.yuanjiao_5_lanse);
                                    tv_btn.setText("完成施工");
                                    break;
                                case 4:
                                    //待复检
                                    holder.setText(R.id.tv_time, "完工时间:" + model.getCreateDate());
                                    tv_btn.setBackgroundResource(R.drawable.yuanjiao_5_lanse);
                                    tv_btn.setText("完成复检");
                                    break;
                                case 5:
                                    //已完工
                                    holder.setText(R.id.tv_time, "复检时间:" + model.getCreateDate());
                                    if (model.getOrder_info() != null && model.getOrder_info().getIsDelivery() == 1) {
                                        //送车到家
                                        tv_btn.setBackgroundResource(R.drawable.yuanjiao_5_heise);
                                        tv_btn.setText("送车到家");
                                    } else {
                                        tv_btn.setBackgroundResource(R.drawable.yuanjiao_5_lanse);
                                        tv_btn.setText("提车");//联系客户改为 提车
                                    }
                                    break;
                                case 6:
                                    //已提车    换了位置 为 7已付款
                                    tv_btn.setVisibility(View.GONE);
                                    break;
                                case 7:
                                    //已付款   换为  已提车
                                    holder.setText(R.id.tv_time, "提车时间:" + model.getCreateDate());
                                    tv_btn.setBackgroundResource(R.drawable.yuanjiao_5_heise);
                                    tv_btn.setText("已提车");
                                    return;
                                case 8:
                                    //救援
                                    break;
                            }
                        }
                    };
                    mAdapter1.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            jumpPage(i);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                    recyclerView.setAdapter(mAdapter1);

                } else {
                    showEmptyPage();
                }
            }
        });
    }


    private void RequestMore3(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment2_2, params, headerMap, new CallBackUtil<Fragment2Model>() {
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
                    mAdapter1.notifyDataSetChanged();
                }
            }
        });
    }

    /**
     * 救援数据
     */
    private void RequestJiuYuan(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment2_3, params, headerMap, new CallBackUtil<Fragment2Model_JiuYuan>() {
            @Override
            public Fragment2Model_JiuYuan onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(Fragment2Model_JiuYuan response) {
                hideProgress();
                list_JiuYuan = response.getList();
                if (list_JiuYuan.size() > 0) {
                    showContentPage();
                    mAdapter_JiuYuan = new CommonAdapter<Fragment2Model_JiuYuan.ListBean>
                            (getActivity(), R.layout.item_fragment2_jiuyuan, list_JiuYuan) {
                        @Override
                        protected void convert(ViewHolder holder, Fragment2Model_JiuYuan.ListBean model, int position) {
                            holder.setText(R.id.tv_id, "订单编号："+model.getYRescueId());
                            holder.setText(R.id.tv_carnum, model.getUser_sedan_info().getSNumber());//车牌
                            holder.setText(R.id.tv_name, model.getFullName());//昵称
                            holder.setText(R.id.tv_type, "类型：" + model.getMType());//类型
                            holder.setText(R.id.tv_content, "描述：" + model.getCarCondition());//描述
                            holder.setText(R.id.tv_addr, "地址：" + model.getAddress());//地址

                            ArrayList<String> images = new ArrayList<>();
                            for (String s : model.getImgArr()) {
                                images.add(URLs.IMGHOST + s);
                            }
                            RecyclerView rv = holder.getView(R.id.rv);
                            LinearLayoutManager llm1 = new LinearLayoutManager(getActivity());
                            llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                            rv.setLayoutManager(llm1);
                            CommonAdapter<String> ca = new CommonAdapter<String>
                                    (getActivity(), R.layout.item_img_80_80, images) {
                                @Override
                                protected void convert(ViewHolder holder, String s, int item) {
                                    ImageView iv = holder.getView(R.id.iv);
                                    Glide.with(getActivity()).load(s)
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
                                    PhotoShowDialog photoShowDialog = new PhotoShowDialog(getActivity(), images, i);
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
                    mAdapter_JiuYuan.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            jumpPage(i);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                    recyclerView.setAdapter(mAdapter_JiuYuan);

                } else {
                    showEmptyPage();
                }
            }
        });
    }

    private void RequestJiuYuanMore(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment2_3, params, headerMap, new CallBackUtil<Fragment2Model_JiuYuan>() {
            @Override
            public Fragment2Model_JiuYuan onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(Fragment2Model_JiuYuan response) {
                hideProgress();
                List<Fragment2Model_JiuYuan.ListBean> list1 = new ArrayList<>();
                list1 = response.getList();
                if (list1.size() == 0) {
                    page--;
                    myToast(getString(R.string.app_nomore));
                } else {
                    list_JiuYuan.addAll(list1);
                    mAdapter_JiuYuan.notifyDataSetChanged();
                }
            }
        });
    }

    /**
     * 改变订单状态
     *
     * @param params
     */
    private void RequestChageType(Map<String, String> params) {
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
                requestServer();
            }
        });
    }

    /**
     * 跳转页面
     *
     * @param i
     */
    private void jumpPage(int i) {
        Bundle bundle = new Bundle();
        switch (item) {
            case 0:
                //待接车
                bundle.putSerializable("JieChe", list_jieche.get(i));
                CommonUtil.gotoActivityWithData(getActivity(), DaiJieCheActivity.class, bundle, false);
                break;
            case 1:
                //待分配
                bundle.putString("y_techn_sedan_id", list_fenpei.get(i).getYTechnSedanId());
                CommonUtil.gotoActivityWithData(getActivity(), DaiFenPeiActivity.class, bundle, false);
                break;
            case 2:
                //待施工
                bundle.putString("y_techn_sedan_id", list.get(i).getYTechnSedanId());
                CommonUtil.gotoActivityWithData(getActivity(), DaiShiGongActivity.class, bundle, false);
                break;
            case 3:
                //进行中
                bundle.putString("y_techn_sedan_id", list.get(i).getYTechnSedanId());
                CommonUtil.gotoActivityWithData(getActivity(), JinXingZhongActivity.class, bundle, false);
                break;
            case 4:
                //待复检
                bundle.putString("y_techn_sedan_id", list.get(i).getYTechnSedanId());
                CommonUtil.gotoActivityWithData(getActivity(), DaiFuJianActivity.class, bundle, false);
                break;
            case 5:
                //已完工
                bundle.putString("y_techn_sedan_id", list.get(i).getYTechnSedanId());
                CommonUtil.gotoActivityWithData(getActivity(), YiWanGongActivity.class, bundle, false);
                break;
            case 6:
                //已提车 -换为  已付款
                bundle.putString("y_techn_sedan_id", list.get(i).getYTechnSedanId());
                CommonUtil.gotoActivityWithData(getActivity(), YiFuKuanActivity.class, bundle, false);

//                CommonUtil.gotoActivityWithData(getActivity(), OrderDetailActivity.class, bundle, false);
                break;
            case 7:
                //已付款 -- 换为  已提车
                bundle.putString("y_techn_sedan_id", list.get(i).getYTechnSedanId());
                CommonUtil.gotoActivityWithData(getActivity(), YiTiCheActivity.class, bundle, false);
                break;
            case 8:
                //救援
//                bundle.putString("id", list.get(i).getYTechnSedanId());
                bundle.putSerializable("jiuyuan", list_JiuYuan.get(i));
                CommonUtil.gotoActivityWithData(getActivity(), JiuYuanActivity.class, bundle, false);
                break;

        }

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
                }
            }
        }
    }

}
