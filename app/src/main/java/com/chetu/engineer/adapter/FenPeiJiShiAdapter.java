package com.chetu.engineer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.engineer.R;
import com.chetu.engineer.model.JiShiListModel;
import com.chetu.engineer.net.URLs;

import java.util.List;

/**
 * Created by zyz on 2016/7/6.
 * Email：1125213018@qq.com
 * description：分配技师 adapter
 */
public class FenPeiJiShiAdapter extends BaseAdapter {
    private Context context;
    private List<JiShiListModel.ListBean> list;
    private int selectIndex = 0;

    public FenPeiJiShiAdapter(Context context, List<JiShiListModel.ListBean> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public void setSelectItem(int selectItem) {
        this.selectIndex = selectItem;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_storedetail_jishi, null);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.imageView = convertView.findViewById(R.id.imageView);
            holder.ratingbar = convertView.findViewById(R.id.ratingbar);
            holder.linearLayout = convertView.findViewById(R.id.linearLayout);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_name.setText(list.get(position).getUserName());
        holder.tv_time.setText("入驻时间：" + list.get(position).getCreateDate());
        Glide.with(context)
                .load(URLs.IMGHOST + list.get(position).getHeadPortrait())
                .centerCrop()
                .placeholder(R.mipmap.loading)//加载站位图
                .error(R.mipmap.zanwutupian)//加载失败
                .into(holder.imageView);//加载图片
        holder.ratingbar.setRating(Float.valueOf(list.get(position).getTech_info().getStar()));
        if (list.get(position).isXuanZhong()){
            holder.linearLayout.setBackgroundResource(R.drawable.yuanjiao_5_qianlanse1);
        }else {
            holder.linearLayout.setBackgroundResource(R.drawable.yuanjiao_5_baise);
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView tv_name,tv_time;
        ImageView imageView;
        RatingBar ratingbar;
        LinearLayout linearLayout;
    }
}
