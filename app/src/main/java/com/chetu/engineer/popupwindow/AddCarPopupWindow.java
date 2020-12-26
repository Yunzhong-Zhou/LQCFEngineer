package com.chetu.engineer.popupwindow;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chetu.engineer.R;
import com.chetu.engineer.model.AddCarModelBean;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.utils.LocalUserInfo;
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
 * description：选择车型弹窗
 */
public class AddCarPopupWindow extends PopupWindow {
    public static String y_sedan_brand_id = "", pingpai = "", xinghao = "";
    private Context mContext;
    private View view;

    int page = 1;//层级
    int i1 = -1;
    int i2 = -1;
    int i3 = -1;

    RecyclerView rv1;
    List<AddCarModelBean.ListBean> list = new ArrayList<>();
    RecyclerView rv2;
    RecyclerView rv3;

    String logo = "";
    //    String brand = "";
    String xinghao1 = "", xinghao2 = "", xinghao3 = "";

    private ProgressDialog pd;

    public AddCarPopupWindow(Context mContext, List<AddCarModelBean.ListBean> list, String id, String logo, String brand) {
        this.view = LayoutInflater.from(mContext).inflate(R.layout.pop_addcar, null);

        this.mContext = mContext;
        this.list = list;
        this.logo = logo;
        this.pingpai = brand;

        y_sedan_brand_id = id;
        xinghao = "";

        initView(view);
        initData();

        // 设置外部可点击
        this.setOutsideTouchable(false);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        this.view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = view.findViewById(R.id.pop_layout).getTop();
                int height1 = view.findViewById(R.id.pop_layout).getBottom();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                    if (y > height1) {
                        dismiss();
                    }
                }

                return true;
            }
        });
        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);

        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0xb0000000);
        ColorDrawable dw = new ColorDrawable(mContext.getResources().getColor(R.color.transparentblack3));
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
//        this.setBackgroundDrawable(new BitmapDrawable());//无色

        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.take_pop_anim);
    }

    private void initView(View view) {
        //logo
        ImageView iv_logo = view.findViewById(R.id.iv_logo);
        Glide.with(mContext)
                .load(logo)
                .centerCrop()
                .into(iv_logo);//加载图片
        //品牌
        TextView tv_brand = (TextView) view.findViewById(R.id.tv_brand);
        tv_brand.setText(pingpai);
        //确定
        TextView tv_confirm = (TextView) view.findViewById(R.id.tv_confirm);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (page == 1) {
                    /*showProgress(true, mContext.getString(R.string.app_loading1));
                    Map<String, String> params = new HashMap<>();
                    params.put("y_sedan_brand_id", y_sedan_brand_id);
                    params.put("s_number", "");//车牌
                    params.put("s_cy", "2");//1为个人  2为公司
                    params.put("u_token", LocalUserInfo.getInstance(mContext).getToken());
                    RequestUpData(params);*/
                    xinghao = pingpai;

                    dismiss();
                } else if (page > 1) {
                    /*showProgress(true, mContext.getString(R.string.app_loading1));
                    Map<String, String> params = new HashMap<>();
                    params.put("y_sedan_brand_id", y_sedan_brand_id);
                    params.put("s_number", "");//车牌
                    params.put("s_cy", "2");//1为个人  2为公司
                    params.put("u_token", LocalUserInfo.getInstance(mContext).getToken());
                    RequestUpData(params);*/
                    xinghao = xinghao1 + xinghao2 + xinghao3;

                    dismiss();
                } else {
                    Toast.makeText(mContext, "请选择具体的车型", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //列表
        rv1 = view.findViewById(R.id.rv1);
        rv1.setLayoutManager(new LinearLayoutManager(mContext));
        rv2 = view.findViewById(R.id.rv2);
        rv2.setLayoutManager(new LinearLayoutManager(mContext));
        rv3 = view.findViewById(R.id.rv3);
        rv3.setLayoutManager(new LinearLayoutManager(mContext));
    }

    private void initData() {
        //第一个列表
        CommonAdapter<AddCarModelBean.ListBean> mAdapter1 = new CommonAdapter<AddCarModelBean.ListBean>
                (mContext, R.layout.item_pop_list, list) {
            @Override
            protected void convert(ViewHolder holder, AddCarModelBean.ListBean listBean, int position) {
                TextView textView1 = holder.getView(R.id.textView1);
                textView1.setText(listBean.getSName());
                if (i1 == position) {
                    textView1.setTextColor(mContext.getResources().getColor(R.color.blue));
                } else {
                    textView1.setTextColor(mContext.getResources().getColor(R.color.black));
                }
            }
        };
        mAdapter1.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                i1 = i;
                mAdapter1.notifyDataSetChanged();
                page = 2;
                Map<String, String> params = new HashMap<>();
                params.put("parent_id", list.get(i).getYSedanBrandId());
                params.put("u_token", LocalUserInfo.getInstance(mContext).getToken());
                Request1(params);
                xinghao1 = list.get(i).getSName();
                y_sedan_brand_id = list.get(i).getYSedanBrandId();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        rv1.setAdapter(mAdapter1);
    }

    /**
     * 获取2级、3级列表
     *
     * @param params
     */
    private void Request1(Map<String, String> params) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("apikey", URLs.APIKEY);
        headerMap.put("hversion", URLs.HVERSION);
        OkhttpUtil.okHttpPost(URLs.CarNameList, params, headerMap, new CallBackUtil<AddCarModelBean>() {
            @Override
            public AddCarModelBean onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
                if (page == 2) {
                    page = 1;
                    rv2.setVisibility(View.GONE);
                    rv3.setVisibility(View.GONE);
                } else {
                    page = 2;
                    rv2.setVisibility(View.VISIBLE);
                    rv3.setVisibility(View.GONE);
                }
            }

            @Override
            public void onResponse(AddCarModelBean response) {
                switch (page) {
                    case 2:
                        i2 = -1;
                        rv2.setVisibility(View.VISIBLE);
                        rv3.setVisibility(View.GONE);
                        //2层列表
                        CommonAdapter<AddCarModelBean.ListBean> mAdapter2 = new CommonAdapter<AddCarModelBean.ListBean>
                                (mContext, R.layout.item_pop_list, response.getList()) {
                            @Override
                            protected void convert(ViewHolder holder, AddCarModelBean.ListBean listBean, int position) {
                                TextView textView1 = holder.getView(R.id.textView1);
                                textView1.setText(listBean.getSName());
                                if (i2 == position) {
                                    textView1.setTextColor(mContext.getResources().getColor(R.color.blue));
                                } else {
                                    textView1.setTextColor(mContext.getResources().getColor(R.color.black));
                                }
                            }
                        };
                        mAdapter2.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                i2 = i;
                                mAdapter2.notifyDataSetChanged();
                                page = 3;
                                Map<String, String> params = new HashMap<>();
                                params.put("parent_id", response.getList().get(i).getYSedanBrandId());
                                params.put("u_token", LocalUserInfo.getInstance(mContext).getToken());
                                Request1(params);
                                xinghao2 = response.getList().get(i).getSName();
                                y_sedan_brand_id = response.getList().get(i).getYSedanBrandId();
                            }

                            @Override
                            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                return false;
                            }
                        });
                        rv2.setAdapter(mAdapter2);
                        break;
                    case 3:
                        //3层列表
                        i3 = -1;
                        rv2.setVisibility(View.VISIBLE);
                        rv3.setVisibility(View.VISIBLE);
                        CommonAdapter<AddCarModelBean.ListBean> mAdapter3 = new CommonAdapter<AddCarModelBean.ListBean>
                                (mContext, R.layout.item_pop_list, response.getList()) {
                            @Override
                            protected void convert(ViewHolder holder, AddCarModelBean.ListBean listBean, int position) {

                                TextView textView1 = holder.getView(R.id.textView1);
                                textView1.setText(listBean.getSName());
                                if (i3 == position) {
                                    textView1.setTextColor(mContext.getResources().getColor(R.color.blue));
                                } else {
                                    textView1.setTextColor(mContext.getResources().getColor(R.color.black));
                                }
                            }
                        };
                        mAdapter3.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                y_sedan_brand_id = response.getList().get(i).getYSedanBrandId();
                                i3 = i;
                                mAdapter3.notifyDataSetChanged();
                                page = 4;
                                xinghao3 = response.getList().get(i).getSName();
                            }

                            @Override
                            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                return false;
                            }
                        });
                        rv3.setAdapter(mAdapter3);
                        break;
                }
            }
        });
    }

    /**
     * 添加车辆
     *
     * @param params
     */
    private void RequestUpData(Map<String, String> params) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("apikey", URLs.APIKEY);
        headerMap.put("hversion", URLs.HVERSION);
        OkhttpUtil.okHttpPost(URLs.AddCar, params, headerMap, new CallBackUtil<AddCarModelBean>() {
            @Override
            public AddCarModelBean onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(AddCarModelBean response) {
                hideProgress();
                Toast.makeText(mContext, "添加成功", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }

    public void showProgress(boolean flag, String message) {
        if (pd == null) {
            pd = new ProgressDialog(mContext);
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setCancelable(flag);
            pd.setCanceledOnTouchOutside(false);
            pd.setMessage(message);
            pd.show();
        } else {
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setCancelable(flag);
            pd.setCanceledOnTouchOutside(false);
            pd.setMessage(message);
            pd.show();
        }
    }

    public void hideProgress() {
        if (pd == null) {
            return;
        }
        if (pd.isShowing()) {
            pd.dismiss();
        }
    }

}
