/*
 * Copyright (c) 2015 张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chetu.engineer.view.sidebar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.engineer.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 列表适配器
 */
public class ContactAdapter extends BaseAdapter implements SectionIndexer {
    private ArrayList<Contact> datas;
    private Context context;

    public ContactAdapter(Context context, ArrayList<Contact> mDatas) {
        super();
        this.context = context;
        this.datas = mDatas;

        if (datas == null) {
            datas = new ArrayList<>();
        }
        Collections.sort(datas);//排序
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_contact, null);
            holder.contact_title = convertView.findViewById(R.id.contact_title);
            holder.contact_catalog = convertView.findViewById(R.id.contact_catalog);
            holder.contact_line = convertView.findViewById(R.id.contact_line);

            holder.contact_head = convertView.findViewById(R.id.contact_head);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.contact_title.setText(datas.get(position).getName());
        if (!datas.get(position).getUrl().equals(""))
            Glide.with(context)
                    .load(datas.get(position).getUrl())
                    .centerCrop()
//                    .placeholder(R.mipmap.headimg)//加载站位图
//                    .error(R.mipmap.headimg)//加载失败
                    .into(holder.contact_head);//加载图片

        //如果是第0个那么一定显示#号
        if (position == 0) {
            holder.contact_catalog.setVisibility(View.VISIBLE);
//            holder.contact_catalog.setText("A");
            holder.contact_catalog.setText("" + datas.get(position).getFirstChar());
//            holder.contact_line.setVisibility(View.VISIBLE);
        } else {
            //如果和上一个item的首字母不同，则认为是新分类的开始
            Contact prevData = datas.get(position - 1);
            if (datas.get(position).getFirstChar() != prevData.getFirstChar()) {
                holder.contact_catalog.setVisibility(View.VISIBLE);
                holder.contact_catalog.setText("" + datas.get(position).getFirstChar());
//                holder.contact_line.setVisibility(View.VISIBLE);
            } else {
                holder.contact_catalog.setVisibility(View.GONE);
//                holder.contact_line.setVisibility(View.GONE);
            }
        }

        return convertView;
    }


    private static class ViewHolder {
        TextView contact_title, contact_catalog, contact_line;
        ImageView contact_head;
    }

    /*@Override
    public void convert(AdapterHolder holder, Contact item, boolean isScrolling, int position) {

        holder.setText(R.id.contact_title, item.getName());
        ImageView headImg = holder.getView(R.id.contact_head);
        if (isScrolling) {
            kjb.displayCacheOrDefult(headImg, item.getUrl(), R.drawable.default_head_rect);
        } else {
            kjb.displayWithLoadBitmap(headImg, item.getUrl(), R.drawable.default_head_rect);
        }

        TextView tvLetter = holder.getView(R.id.contact_catalog);
        TextView tvLine = holder.getView(R.id.contact_line);

        //如果是第0个那么一定显示#号
        if (position == 0) {
            tvLetter.setVisibility(View.VISIBLE);
            tvLetter.setText("#");
            tvLine.setVisibility(View.VISIBLE);
        } else {

            //如果和上一个item的首字母不同，则认为是新分类的开始
            Contact prevData = datas.get(position - 1);
            if (item.getFirstChar() != prevData.getFirstChar()) {
                tvLetter.setVisibility(View.VISIBLE);
                tvLetter.setText("" + item.getFirstChar());
                tvLine.setVisibility(View.VISIBLE);
            } else {
                tvLetter.setVisibility(View.GONE);
                tvLine.setVisibility(View.GONE);
            }
        }
    }*/

    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        Contact item = datas.get(position);
        return item.getFirstChar();
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            char firstChar = datas.get(i).getFirstChar();
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Object[] getSections() {
        return null;
    }

}
