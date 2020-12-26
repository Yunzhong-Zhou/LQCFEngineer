package com.chetu.engineer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.engineer.R;
import com.chetu.engineer.activity.ComplaintListActivity;
import com.chetu.engineer.activity.FootprintActivity;
import com.chetu.engineer.activity.InviteActivity;
import com.chetu.engineer.activity.MyBaoJiaActivity;
import com.chetu.engineer.activity.MyCollectionActivity;
import com.chetu.engineer.activity.MyCommentActivity;
import com.chetu.engineer.activity.MyPublishActivity;
import com.chetu.engineer.activity.MyWalletActivity;
import com.chetu.engineer.activity.PersonalSettingActivity;
import com.chetu.engineer.activity.ServiceCenterActivity;
import com.chetu.engineer.activity.SetUpActivity;
import com.chetu.engineer.activity.VerifiedActivity;
import com.chetu.engineer.activity.WebContentActivity;
import com.chetu.engineer.base.BaseFragment;
import com.chetu.engineer.model.Fragment4Model;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.utils.CommonUtil;
import com.cy.cyflowlayoutlibrary.FlowLayout;
import com.cy.cyflowlayoutlibrary.FlowLayoutAdapter;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

import static com.chetu.engineer.net.URLs.IMGHOST;

/**
 * Created by fafukeji01 on 2016/1/6.
 * 我的
 */
public class Fragment4 extends BaseFragment {
    Fragment4Model model;
    ImageView iv_shezhi, imageView1;

    TextView textView1, textView2;
    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6,
            linearLayout7, linearLayout8, linearLayout9, linearLayout10, linearLayout11,linearLayout12;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        /*if (MainActivity.item == 3) {
            requestServer();
        }*/

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        /*if (MainActivity.item == 3) {
            requestServer();
        }*/
    }

    @Override
    protected void initView(View view) {
//        CommonUtil.setMargins(findViewByID_My(R.id.headView),0, (int) CommonUtil.getStatusBarHeight(getActivity()),0,0);
        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                requestCenter(params);
            }

            @Override
            public void onLoadmore() {

            }
        });
        iv_shezhi = findViewByID_My(R.id.iv_shezhi);
        iv_shezhi.setOnClickListener(this);

        imageView1 = findViewByID_My(R.id.imageView1);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);

        if (!localUserInfo.getNickname().equals("")) {
            textView1.setText(localUserInfo.getNickname());
        }
//        textView2.setText(localUserInfo.getPhonenumber());

        if (!localUserInfo.getUserImage().equals(""))
            Glide.with(getActivity())
                    .load(IMGHOST + localUserInfo.getUserImage())
                    .centerCrop()
//                    .placeholder(R.mipmap.headimg)//加载站位图
//                    .error(R.mipmap.headimg)//加载失败
                    .into(imageView1);//加载图片

        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout3 = findViewByID_My(R.id.linearLayout3);
        linearLayout4 = findViewByID_My(R.id.linearLayout4);
        linearLayout5 = findViewByID_My(R.id.linearLayout5);
        linearLayout6 = findViewByID_My(R.id.linearLayout6);
        linearLayout7 = findViewByID_My(R.id.linearLayout7);
        linearLayout8 = findViewByID_My(R.id.linearLayout8);
        linearLayout9 = findViewByID_My(R.id.linearLayout9);
        linearLayout10 = findViewByID_My(R.id.linearLayout10);
        linearLayout11 = findViewByID_My(R.id.linearLayout11);
        linearLayout12 = findViewByID_My(R.id.linearLayout12);

        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);
        linearLayout4.setOnClickListener(this);
        linearLayout5.setOnClickListener(this);
        linearLayout6.setOnClickListener(this);
        linearLayout7.setOnClickListener(this);
        linearLayout8.setOnClickListener(this);
        linearLayout9.setOnClickListener(this);
        linearLayout10.setOnClickListener(this);
        linearLayout11.setOnClickListener(this);
        linearLayout12.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        requestServer();
    }

    private void requestCenter(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment4, params, headerMap, new CallBackUtil<Fragment4Model>() {
            @Override
            public Fragment4Model onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(Fragment4Model response) {
                hideProgress();
                model = response;

                //保存头像
                localUserInfo.setUserImage(response.getUser_info().getHeadPortrait());
                if (!response.getUser_info().getHeadPortrait().equals("") && getActivity() != null)
                    Glide.with(getActivity()).load(IMGHOST + localUserInfo.getUserImage())
                            .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                            .into(imageView1);//加载图片
                //保存昵称
                localUserInfo.setNickname(response.getUser_info().getUserName());
                if (!localUserInfo.getNickname().equals("")) {
                    textView1.setText(localUserInfo.getNickname());
                }
                //保存电话号码
                localUserInfo.setPhoneNumber(response.getUser_info().getUserPhone());
                //保存职位
                localUserInfo.setUserJob(response.getUser_info().getIsDistri()+"");//为1、有分配权限

                //保存y_store_id
                localUserInfo.setBelongid(response.getUser_info().getYStoreId());

                //保存userid
                localUserInfo.setUserId(response.getUser_info().getUserId());

                if (response.getUser_info().getStore_info().getVName() != null) {
                    //显示店铺信息
                    textView2.setText(response.getUser_info().getStore_info().getVName());
                    //拆分标签数据
                    String[] strArr1 = response.getUser_info().getStore_info().getKeywsr().split(" ");//拆分空格
                    List<String> tabs = new ArrayList<>();
                    for (String s : strArr1) {
                        tabs.add(s);
                    }
                    FlowLayoutAdapter<String> flowLayoutAdapter1 =
                            new FlowLayoutAdapter<String>
                                    (tabs) {
                                @Override
                                public void bindDataToView(FlowLayoutAdapter.ViewHolder holder, int position, String bean) {
                                    holder.setText(R.id.tv, bean);
                                }

                                @Override
                                public void onItemClick(int position, String bean) {
//                        showToast("点击" + position);
                                }

                                @Override
                                public int getItemLayoutID(int position, String bean) {
                                    return R.layout.item_fragment4_flowlayout1;
                                }
                            };
                    ((FlowLayout) findViewByID_My(R.id.flowLayout1)).setAdapter(flowLayoutAdapter1);
                }

            }
        });
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading));
        Map<String, String> params = new HashMap<>();
        params.put("u_token", localUserInfo.getToken());
        requestCenter(params);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_shezhi:
                //设置
                CommonUtil.gotoActivity(getActivity(), SetUpActivity.class);
                break;
            case R.id.linearLayout1:
                //个性设置
                CommonUtil.gotoActivity(getActivity(), PersonalSettingActivity.class);
                break;
            case R.id.linearLayout2:
                //实名认证
                CommonUtil.gotoActivity(getActivity(), VerifiedActivity.class);
                break;
            case R.id.linearLayout3:
                //我的发布
                CommonUtil.gotoActivity(getActivity(), MyPublishActivity.class);
                break;
            case R.id.linearLayout4:
                //我的钱包
                CommonUtil.gotoActivity(getActivity(), MyWalletActivity.class);
                break;
            case R.id.linearLayout5:
                //我的收藏
                CommonUtil.gotoActivity(getActivity(), MyCollectionActivity.class);
                break;
            case R.id.linearLayout6:
                //我的足迹
                CommonUtil.gotoActivity(getActivity(), FootprintActivity.class);
//                CommonUtil.gotoActivity(getActivity(), CouponActivity.class);
                break;
            case R.id.linearLayout11:
                //我的报价
                Bundle bundle = new Bundle();
                bundle.putString("y_store_id", model.getUser_info().getYStoreId());
                CommonUtil.gotoActivityWithData(getActivity(), MyBaoJiaActivity.class, bundle, false);
                break;
            case R.id.linearLayout7:
                //评价管理
                CommonUtil.gotoActivity(getActivity(), MyCommentActivity.class);
                break;
            case R.id.linearLayout8:
                //投诉记录
                CommonUtil.gotoActivity(getActivity(), ComplaintListActivity.class);
                break;
            case R.id.linearLayout9:
                //邀请好友
                CommonUtil.gotoActivity(getActivity(), InviteActivity.class);
                /*Intent share_intent1 = new Intent();
                share_intent1.setAction(Intent.ACTION_SEND);
//                    share_intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                share_intent1.setType("text/plain");
//                share_intent1.putExtra(Intent.EXTRA_TEXT, model.getShare().getText() + "\n"
//                        + model.getShare().getUrl());
                share_intent1.putExtra(Intent.EXTRA_TEXT, "我发现一个很好用的APP" + "\n"
                        + "www.xxxxxx.com");
                share_intent1 = Intent.createChooser(share_intent1, "分享");
                startActivity(share_intent1);*/
                break;
            case R.id.linearLayout10:
                //客服中心
                CommonUtil.gotoActivity(getActivity(), ServiceCenterActivity.class);
//                CommonUtil.gotoActivity(getActivity(), MyGarageActivity.class);
//                CommonUtil.gotoActivity(getActivity(), AddMerchantActivity.class);
                break;
            case R.id.linearLayout12:
                //聊天列表
                String url1 = URLs.KFHOST + "/#/pages/chetu-kf/chat_list?token=" + localUserInfo.getToken();
                Bundle bundle1 = new Bundle();
                bundle1.putString("url", url1);
                CommonUtil.gotoActivityWithData(getActivity(), WebContentActivity.class, bundle1, false);
                break;
        }
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
