package com.chetu.engineer.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.SignInListModel;
import com.chetu.engineer.model.SignInTodayModel;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/6/8.
 * 签到
 */
public class SignInActivity extends BaseActivity {
    int page = 0, year = 0, month = 0;
    SignInTodayModel model;

    RecyclerView recyclerView;
    List<SignInListModel.ListBean> list = new ArrayList<>();
    CommonAdapter<SignInListModel.ListBean> mAdapter;
    HeaderAndFooterWrapper mHeaderAndFooterWrapper;

    TimePickerView pvTime1;
    View headerView;
    LinearLayout ll_shangban, ll_xiaban;
    TextView head1_year, head1_month;

    LinearLayout invis;
    TextView invis_year, invis_month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
                params.put("year", year + "");
                params.put("month", month + "");
                params.put("page", page + "");
                Request(params);
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
                params.put("u_token", localUserInfo.getToken());
                params.put("year", year + "");
                params.put("month", month + "");
                params.put("page", page + "");
                RequestMore(params);
            }
        });

        //头部布局
        headerView = View.inflate(SignInActivity.this, R.layout.head_signin, null);
        ll_shangban = headerView.findViewById(R.id.ll_shangban);
        ll_xiaban = headerView.findViewById(R.id.ll_xiaban);
        head1_year = headerView.findViewById(R.id.head1_year);
        head1_month = headerView.findViewById(R.id.head1_month);


        //悬浮布局
        invis = findViewByID_My(R.id.invis);
        invis_year = invis.findViewById(R.id.invis_year);
        invis_month = invis.findViewById(R.id.invis_month);

        //列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        //添加头部必须先设置Adapter
        mAdapter = new CommonAdapter<SignInListModel.ListBean>
                (SignInActivity.this, R.layout.item_signin, list) {
            @Override
            protected void convert(ViewHolder holder, SignInListModel.ListBean model, int position) {

            }
        };
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mAdapter);
        mHeaderAndFooterWrapper.addHeaderView(headerView);
//        mHeaderAndFooterWrapper1.addHeaderView(headerView2);
        recyclerView.setAdapter(mHeaderAndFooterWrapper);

        //滑动监听
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mLinearLayoutManager.findFirstVisibleItemPosition() >= 1) {
                    invis.setVisibility(View.VISIBLE);
                } else {
                    invis.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void initData() {
        Calendar calendar = Calendar.getInstance();
        //年
        year = calendar.get(Calendar.YEAR);
        //月
        month = calendar.get(Calendar.MONTH) + 1;

        head1_year.setText(year + "年");
        invis_year.setText(year + "年");
        head1_month.setText(month + "月");
        invis_month.setText(month + "月");

        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();

        //获取当天签到情况
        Map<String, String> params1 = new HashMap<>();
        params1.put("u_token", localUserInfo.getToken());
        RequestToday(params1);
        //获取签到分页
        page = 0;
        Map<String, String> params = new HashMap<>();
        params.put("u_token", localUserInfo.getToken());
        params.put("year", year + "");
        params.put("month", month + "");
        params.put("page", page + "");
        Request(params);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_shangban:
                showToast("确认上班打开吗？", "取消", "确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        showProgress(false, "正在打卡，请稍候...");
                        Map<String, String> params = new HashMap<>();
                        params.put("u_token", localUserInfo.getToken());
                        RequestSigninUp(params);
                    }
                });
                break;
            case R.id.ll_xiaban:
                showToast("检查下您的工作是否完成\n下班工序是否完成！", "再检查下", "确认下班", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        showProgress(false, "正在打卡，请稍候...");
                        Map<String, String> params = new HashMap<>();
                        params.put("u_token", localUserInfo.getToken());
                        RequestSigninUp(params);
                    }
                });
                break;
            case R.id.head1_year:
            case R.id.head1_month:
            case R.id.invis_year:
            case R.id.invis_month:
                setDate("请选择日期", year, month);
                break;
        }
    }

    /**
     * 当天签到情况
     *
     * @param params
     */
    private void RequestToday(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.SignIn_Today, params, headerMap, new CallBackUtil<SignInTodayModel>() {
            @Override
            public SignInTodayModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {

            }

            @Override
            public void onResponse(SignInTodayModel response) {

            }
        });
    }

    /**
     * 获取签到列表
     *
     * @param params
     */
    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.SignIn_List, params, headerMap, new CallBackUtil<SignInListModel>() {
            @Override
            public SignInListModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showContentPage();//有头部数据，显示详情
//                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(SignInListModel response) {
                hideProgress();
                showContentPage();
                list = response.getList();
                if (list.size() > 0) {
                    showContentPage();
                    mAdapter = new CommonAdapter<SignInListModel.ListBean>
                            (SignInActivity.this, R.layout.item_signin, list) {
                        @Override
                        protected void convert(ViewHolder holder, SignInListModel.ListBean model, int position) {
                            holder.setText(R.id.time,model.getCreateDate()+" "+model.getWeekDay());

                            LinearLayout shangban = holder.getView(R.id.shangban);
                            if (!model.getWorkTime().equals("")){
                                shangban.setVisibility(View.VISIBLE);
                                holder.setText(R.id.shangban_time,"上班打卡 "+model.getWorkTime());
                            }else {
                                shangban.setVisibility(View.INVISIBLE);
                            }

                            LinearLayout xiaban = holder.getView(R.id.xiaban);
                            if (!model.getClosingTime().equals("")){
                                xiaban.setVisibility(View.VISIBLE);
                                holder.setText(R.id.xiaban_time,"下班打卡 "+model.getClosingTime());
                            }else {
                                xiaban.setVisibility(View.INVISIBLE);
                            }

                            //添加数据
                            /*LinearLayout ll_add = holder.getView(R.id.ll_add);
                            ll_add.removeAllViews();
                            for (int i = 0; i < list.size(); i++) {
                                //添加数据
                                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                LayoutInflater inflater = LayoutInflater.from(SignInActivity.this);
                                View view = inflater.inflate(R.layout.item_signin_add, null, false);
                                view.setLayoutParams(lp);

                                //实例化子页面的控件
                            TextView tv_id = (TextView) view.findViewById(R.id.tv_id);
                            TextView tv_dizhi = (TextView) view.findViewById(R.id.tv_dizhi);
                            TextView tv_delete = (TextView) view.findViewById(R.id.tv_delete);
                            TextView tv_tujingdian_name = (TextView) view.findViewById(R.id.tv_tujingdian_name);
                            TextView tv_tujingdian_mobile = (TextView) view.findViewById(R.id.tv_tujingdian_mobile);

                            tv_id.setText(addr_id);
                            tv_dizhi.setText(addr);
                            tv_tujingdian_name.setText("收货人：" + name);
                            tv_tujingdian_mobile.setText("电话号码：" + mobile);

                                ll_add.addView(view);
                            }*/


                        }
                    };
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            MyLogger.i(">>>>>>>>>>" + i);//position = 0 + header个数
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                    mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mAdapter);
                    mHeaderAndFooterWrapper.addHeaderView(headerView);
//        recyclerView.setAdapter(mHeaderAndFooterWrapper1);
                    recyclerView.setAdapter(mHeaderAndFooterWrapper);
                }
            }
        });
    }

    private void RequestMore(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.SignIn_List, params, headerMap, new CallBackUtil<SignInListModel>() {
            @Override
            public SignInListModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(SignInListModel response) {
                hideProgress();
                List<SignInListModel.ListBean> list1 = new ArrayList<>();
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

    /**
     * 签到提交
     *
     * @param params
     */
    private void RequestSigninUp(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.SignIn_Up, params, headerMap, new CallBackUtil<String>() {
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
                myToast("打卡成功");
                requestServer();

            }
        });
    }

    /**
     * 生日
     *
     * @param string
     */
    private void setDate(String string, int y, int m) {
        Calendar calendar = Calendar.getInstance();
        /*//获取当前时间
        Calendar calendar = Calendar.getInstance();
        //年
        int year = calendar.get(Calendar.YEAR);
        //月
        int month = calendar.get(Calendar.MONTH);
        //日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        //分钟
        int minute = calendar.get(Calendar.MINUTE);
        //秒
        int second = calendar.get(Calendar.SECOND);*/

        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        /*if (!date.equals("")) {
            String[] strArr = date.split("-");//拆分日期 得到年月日
            selectedDate.set(Integer.valueOf(strArr[0]), Integer.valueOf(strArr[1]) - 1, Integer.valueOf(strArr[2]));
        }*/
        selectedDate.set(y, m - 1, 1);

        //正确设置方式 原因：注意事项有说明
//        startDate.set(year, month, day);
        startDate.set(2000, 0, 1);

        //当前时间加3天
//        calendar.add(Calendar.YEAR, 100);
//        endDate.set(year, month, 1);
        endDate.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));


        pvTime1 = new TimePickerBuilder(SignInActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                //年
                SimpleDateFormat sdf_y = new SimpleDateFormat("yyyy");
                year = Integer.valueOf(sdf_y.format(date));
                //月
                SimpleDateFormat sdf_m = new SimpleDateFormat("MM");
                month = Integer.valueOf(sdf_m.format(date));

                head1_year.setText(year + "年");
                invis_year.setText(year + "年");
                head1_month.setText(month + "月");
                invis_month.setText(month + "月");

                requestServer();


            }
        })
                .setType(new boolean[]{true, true, false, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentTextSize(15)//滚轮文字大小
                .setTitleSize(16)//标题文字大小
                .setTitleText(string)//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
                .setTitleColor(getResources().getColor(R.color.black2))//标题文字颜色
                .setSubmitColor(getResources().getColor(R.color.blue))//确定按钮文字颜色
                .setCancelColor(getResources().getColor(R.color.blue))//取消按钮文字颜色
                .setTitleBgColor(getResources().getColor(R.color.black5))//标题背景颜色 Night mode
                .setBgColor(getResources().getColor(R.color.white))//滚轮背景颜色 Night mode
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(true)//是否显示为对话框样式
                .build();

        Dialog mDialog = pvTime1.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);
            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime1.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.1f);
            }
        }
        pvTime1.show();
    }

    @Override
    protected void updateView() {
        titleView.setTitle("签到");
    }
}
