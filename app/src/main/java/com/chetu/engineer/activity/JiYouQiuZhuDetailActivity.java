package com.chetu.engineer.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.Fragment3Model;
import com.chetu.engineer.model.JishuJiaoLiuDetailModel;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.popupwindow.PhotoShowDialog;
import com.chetu.engineer.utils.CommonUtil;
import com.chetu.engineer.utils.DateUtils;
import com.cy.dialog.BaseDialog;
import com.liaoinstan.springview.widget.SpringView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/6/27.
 * 机友求助详情
 */
public class JiYouQiuZhuDetailActivity extends BaseActivity {
    int page = 0;
    //    JiYouQiuZhuModel.ListBean model;
    Fragment3Model.ListBean model;
    ImageView iv, iv_shoucang, tv_dianzan;
    TextView tv_title, tv_text, tv_time, tv_content;

    RecyclerView rv_tupian, recyclerView;

    List<JishuJiaoLiuDetailModel.ListBean> list = new ArrayList<>();
    CommonAdapter<JishuJiaoLiuDetailModel.ListBean> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiyouqiuzhudetail);
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
                params.put("y_forum_posts_id", model.getYForumPostsId());
                params.put("u_token", localUserInfo.getToken());
                Request(params);
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
                params.put("page", page + "");
                params.put("y_forum_posts_id", model.getYForumPostsId());
                params.put("u_token", localUserInfo.getToken());
                Request(params);
            }
        });
        iv_shoucang = findViewByID_My(R.id.iv_shoucang);
        tv_dianzan = findViewByID_My(R.id.tv_dianzan);
        iv = findViewByID_My(R.id.iv);
        tv_title = findViewByID_My(R.id.tv_title);
        tv_text = findViewByID_My(R.id.tv_text);
        tv_time = findViewByID_My(R.id.tv_time);
        tv_content = findViewByID_My(R.id.tv_content);
        rv_tupian = findViewByID_My(R.id.rv_tupian);
        LinearLayoutManager llm1 = new LinearLayoutManager(JiYouQiuZhuDetailActivity.this);
        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        rv_tupian.setLayoutManager(llm1);

        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        model = (Fragment3Model.ListBean) getIntent().getSerializableExtra("JiYouQiuZhuDetail");
        Glide.with(this)
                .load(URLs.IMGHOST + model.getUser_info().getHeadPortrait())
                .centerCrop()
//                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                .placeholder(R.mipmap.loading)//加载站位图
                .error(R.mipmap.zanwutupian)//加载失败
                .into(iv);//加载图片
        tv_title.setText(model.getHelp_info().getV_title());
        tv_text.setText(model.getUser_info().getUserName());
        tv_content.setText(model.getHelp_info().getV_text());
        try {
            Date date = new Date();
            SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = simple.parse(model.getCreateDate());
            tv_time.setText(DateUtils.fromToday(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (model.getHelp_info().getImgArr() != null && model.getHelp_info().getImgArr().size() > 0) {
            rv_tupian.setVisibility(View.VISIBLE);
            ArrayList<String> images = new ArrayList<>();
            for (String s : model.getHelp_info().getImgArr()) {
                images.add(URLs.IMGHOST + s);
            }
            CommonAdapter<String> mAdapter_tupian = new CommonAdapter<String>
                    (JiYouQiuZhuDetailActivity.this, R.layout.item_img_110_110, images) {
                @Override
                protected void convert(ViewHolder holder, String model, int position) {
                    ImageView iv = holder.getView(R.id.iv);
                    Glide.with(JiYouQiuZhuDetailActivity.this)
                            .load(model)
                            .centerCrop()
                            .placeholder(R.mipmap.loading)//加载站位图
                            .error(R.mipmap.zanwutupian)//加载失败
                            .into(iv);//加载图片
                }
            };
            mAdapter_tupian.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {

                    PhotoShowDialog photoShowDialog = new PhotoShowDialog(JiYouQiuZhuDetailActivity.this, images, i);
                    photoShowDialog.show();
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                    return false;
                }
            });
            rv_tupian.setAdapter(mAdapter_tupian);

        } else {
            rv_tupian.setVisibility(View.GONE);
        }

        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 0;
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("y_forum_posts_id", model.getYForumPostsId());
        params.put("u_token", localUserInfo.getToken());
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.JiShuJiaoLiuDetail, params, headerMap, new CallBackUtil<JishuJiaoLiuDetailModel>() {
            @Override
            public JishuJiaoLiuDetailModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(JishuJiaoLiuDetailModel response) {
                hideProgress();
                if (response.getInfo().getIs_like() == 0) {
                    iv_shoucang.setImageResource(R.mipmap.ic_shoucang_0);
                } else {
                    iv_shoucang.setImageResource(R.mipmap.ic_shoucang_1);
                }
                if (response.getInfo().getIs_give() == 0) {
                    tv_dianzan.setImageResource(R.mipmap.ic_dianzan_0);
                } else {
                    tv_dianzan.setImageResource(R.mipmap.ic_dianzan_1);
                }

                list = response.getList();
                if (list.size() > 0) {
                    showContentPage();
                    mAdapter = new CommonAdapter<JishuJiaoLiuDetailModel.ListBean>
                            (JiYouQiuZhuDetailActivity.this, R.layout.item_jishujiaoliu_huifu, list) {
                        @Override
                        protected void convert(ViewHolder holder, JishuJiaoLiuDetailModel.ListBean model, int position) {
                            holder.setText(R.id.tv_title, model.getUser_info().getUserName());
                            holder.setText(R.id.tv_time, model.getCreateDate());
                            holder.setText(R.id.tv_content, model.getVTitle());
//                            holder.setText(R.id.tv_num, "" + model.getILike());
                            ImageView iv = holder.getView(R.id.iv);
                            Glide.with(JiYouQiuZhuDetailActivity.this)
                                    .load(URLs.IMGHOST + model.getUser_info().getHeadPortrait())
                                    .centerCrop()
//                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(iv);//加载图片

                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                } else {
                    showEmptyPage();
                }
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
            case R.id.iv_shoucang:
                //收藏
                Map<String, String> params1 = new HashMap<>();
                params1.put("is_like", "2");//1为点赞  2为喜欢
                params1.put("y_forum_posts_id", model.getYForumPostsId());
                params1.put("u_token", localUserInfo.getToken());
                RequestLike(params1);
                break;
            case R.id.tv_dianzan:
                //点赞
                Map<String, String> params = new HashMap<>();
                params.put("is_like", "1");//1为点赞  2为喜欢
                params.put("y_forum_posts_id", model.getYForumPostsId());
                params.put("u_token", localUserInfo.getToken());
                RequestLike(params);
                break;
            case R.id.tv_confirm:
                //回复
                dialog = new BaseDialog(this);
                dialog.contentView(R.layout.dialog_edit)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))
                        .animType(BaseDialog.AnimInType.CENTER)
                        .canceledOnTouchOutside(true)
                        .dimAmount(0.8f)
                        .show();
                TextView tv_title = dialog.findViewById(R.id.tv_title);
                tv_title.setText("回复" + model.getUser_info().getUserName());
                final EditText editText1 = dialog.findViewById(R.id.editText1);
                editText1.setHint("请输入回复内容");
//                editText1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                dialog.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!editText1.getText().toString().trim().equals("")) {
                            CommonUtil.hideSoftKeyboard_fragment(v, JiYouQiuZhuDetailActivity.this);
                            dialog.dismiss();
                            showProgress(true, getString(R.string.app_loading1));
                            Map<String, String> params = new HashMap<>();
                            params.put("v_title", editText1.getText().toString().trim());
                            params.put("y_forum_posts_id", model.getYForumPostsId());
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
                break;
        }
    }

    private void RequestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.JiShuJiaoLiuDetail_HuiFu, params, headerMap, new CallBackUtil() {
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
                myToast("回复成功");
                requestServer();
            }
        });
    }

    /**
     * 收藏、点赞
     *
     * @param params
     */
    private void RequestLike(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Like, params, headerMap, new CallBackUtil() {
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
                requestServer();
            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }
}
