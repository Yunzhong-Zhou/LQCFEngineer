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
import com.chetu.engineer.model.Fragment1Model;
import com.chetu.engineer.net.URLs;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Mr.Z on 2020/12/13.
 * 求职招聘
 */
public class QiuZhiZhaoPingDetailActivity extends BaseActivity {
    //    ReMenHuoDongModel.ListBean model;
    Fragment1Model.RecruitListBean model;
    ImageView iv;
    TextView tv_name, tv_title, tv_type, tv_addr, tv_starttime, tv_endtime, tv_content;
    RecyclerView rv_tupian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qiuzhizhaopingdetail);
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
        LinearLayoutManager llm1 = new LinearLayoutManager(QiuZhiZhaoPingDetailActivity.this);
        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        rv_tupian.setLayoutManager(llm1);
    }

    @Override
    protected void initData() {
        model = (Fragment1Model.RecruitListBean) getIntent().getSerializableExtra("QiuZhiZhaoPingDetail");
        Glide.with(this)
                .load(URLs.IMGHOST + model.getUser_info().getHeadPortrait())
                .centerCrop()
//                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                .placeholder(R.mipmap.loading)//加载站位图
                .error(R.mipmap.zanwutupian)//加载失败
                .into(iv);//加载图片
        tv_name.setText(model.getUser_info().getUserName());

        tv_title.setText("招聘职位：" + model.getV_title());
        tv_type.setText("门店名称：" + model.getRecruit_info().getNameStore());
        tv_addr.setText("联系电话：" + model.getRecruit_info().getTelephone());
        tv_starttime.setText("薪资：" + model.getRecruit_info().getSalary());
        tv_endtime.setText("地址：" + model.getRecruit_info().getAddress());
        tv_content.setText("职位要求：" + model.getRecruit_info().getSalary());

        /*if (model.getActivity_info().getImgArr() != null && model.getActivity_info().getImgArr().size() > 0) {
            rv_tupian.setVisibility(View.VISIBLE);
            ArrayList<String> images = new ArrayList<>();
            for (String s : model.getActivity_info().getImgArr()) {
                images.add(URLs.IMGHOST + s);
            }
            CommonAdapter<String> mAdapter_tupian = new CommonAdapter<String>
                    (QiuZhiZhaoPingDetailActivity.this, R.layout.item_img_110_110, images) {
                @Override
                protected void convert(ViewHolder holder, String model, int position) {
                    ImageView iv = holder.getView(R.id.iv);
                    Glide.with(QiuZhiZhaoPingDetailActivity.this)
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

                    PhotoShowDialog photoShowDialog = new PhotoShowDialog(QiuZhiZhaoPingDetailActivity.this, images, i);
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
        }*/
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
        titleView.setTitle("求职招聘");
    }
}
