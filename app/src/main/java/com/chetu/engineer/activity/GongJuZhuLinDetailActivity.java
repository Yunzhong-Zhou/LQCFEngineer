package com.chetu.engineer.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.Fragment3Model;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.popupwindow.PhotoShowDialog;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Mr.Z on 2020/12/13.
 */
public class GongJuZhuLinDetailActivity extends BaseActivity {
    //    ReMenHuoDongModel.ListBean model;
    Fragment3Model.ListBean model;
    ImageView iv;
    TextView tv_name, tv_title, tv_type, tv_addr, tv_starttime, tv_endtime, tv_content;
    RecyclerView rv_tupian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gongjuzhulindetail);
    }

    @Override
    protected void initView() {
        iv = findViewByID_My(R.id.iv);
        tv_title = findViewByID_My(R.id.tv_title);
        tv_name = findViewByID_My(R.id.tv_name);
        tv_type = findViewByID_My(R.id.tv_type);
        tv_addr = findViewByID_My(R.id.tv_addr);
        tv_starttime = findViewByID_My(R.id.tv_starttime);
        tv_endtime = findViewByID_My(R.id.tv_endtime);
        tv_content = findViewByID_My(R.id.tv_content);
        rv_tupian = findViewByID_My(R.id.rv_tupian);
        LinearLayoutManager llm1 = new LinearLayoutManager(GongJuZhuLinDetailActivity.this);
        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        rv_tupian.setLayoutManager(llm1);
    }

    @Override
    protected void initData() {
        model = (Fragment3Model.ListBean) getIntent().getSerializableExtra("GongJuZhuLinDetail");
        Glide.with(this)
                .load(URLs.IMGHOST + model.getUser_info().getHeadPortrait())
                .centerCrop()
//                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                .placeholder(R.mipmap.loading)//加载站位图
                .error(R.mipmap.zanwutupian)//加载失败
                .into(iv);//加载图片
        tv_name.setText(model.getUser_info().getUserName());

        tv_title.setText("标题：" + model.getTool_info().getV_title());
        tv_type.setText("价格：" + model.getTool_info().getV_specifi());
        tv_addr.setText("规格：" + model.getTool_info().getV_duration());
        tv_starttime.setText("租赁时长：" + model.getTool_info().getV_duration());
        tv_endtime.setText("正文：" + model.getTool_info().getV_text());
//        tv_content.setText("活动内容：" + model.getActivity_info().getV_content());

        if (model.getTool_info().getImgArr() != null && model.getTool_info().getImgArr().size() > 0) {
            rv_tupian.setVisibility(View.VISIBLE);
            ArrayList<String> images = new ArrayList<>();
            for (String s : model.getTool_info().getImgArr()) {
                images.add(URLs.IMGHOST + s);
            }
            CommonAdapter<String> mAdapter_tupian = new CommonAdapter<String>
                    (GongJuZhuLinDetailActivity.this, R.layout.item_img_110_110, images) {
                @Override
                protected void convert(ViewHolder holder, String model, int position) {
                    ImageView iv = holder.getView(R.id.iv);
                    Glide.with(GongJuZhuLinDetailActivity.this)
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

                    PhotoShowDialog photoShowDialog = new PhotoShowDialog(GongJuZhuLinDetailActivity.this, images, i);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_call:
                //拨打电话
                showToast("确认拨打 " + model.getUser_info().getUserPhone() + " 吗？", "确认", "取消",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                //创建打电话的意图
                                Intent intent = new Intent();
                                //设置拨打电话的动作
                                intent.setAction(Intent.ACTION_CALL);//直接拨出电话
//                               intent.setAction(Intent.ACTION_DIAL);//只调用拨号界面，不拨出电话
                                //设置拨打电话的号码
                                intent.setData(Uri.parse("tel:" + model.getUser_info().getUserPhone()));
                                //开启打电话的意图
                                startActivity(intent);
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                break;
            case R.id.iv_message:
                //聊天


                break;

        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle("工具租赁");
    }
}
