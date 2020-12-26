package com.chetu.engineer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.AddCarModelBean;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.popupwindow.AddCarPopupWindow;
import com.chetu.engineer.utils.CommonUtil;
import com.chetu.engineer.utils.MyLogger;
import com.chetu.engineer.view.sidebar.Contact;
import com.chetu.engineer.view.sidebar.ContactAdapter;
import com.chetu.engineer.view.sidebar.HanziToPinyin;
import com.chetu.engineer.view.sidebar.SideBar;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/5/29.
 * 添加车辆
 */
public class AddCarModelActivity extends BaseActivity implements PopupWindow.OnDismissListener {
    //热门列表
    View headView;
    RecyclerView recyclerView1;
    List<AddCarModelBean.HostListBean> list1 = new ArrayList<>();
    CommonAdapter<AddCarModelBean.HostListBean> mAdapter1;

    /**
     * 带索引、搜索的列表
     */
    private ListView mListView;
    private ArrayList<Contact> datas = new ArrayList<>();
    private ContactAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectcar);
    }

    @Override
    protected void initView() {
        headView = View.inflate(this, R.layout.head_addcar, null);
        recyclerView1 = headView.findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new GridLayoutManager(this, 5));

        /**
         * 带索引、搜索的列表
         */
        mListView = findViewByID_My(R.id.school_friend_member);
        SideBar mSideBar = (SideBar) findViewById(R.id.school_friend_sidrbar);
        TextView mDialog = (TextView) findViewById(R.id.school_friend_dialog);
        EditText mSearchInput = (EditText) findViewById(R.id.school_friend_member_search_input);
        mSideBar.setTextView(mDialog);
        mSideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                int position = 0;
                // 该字母首次出现的位置
                if (mAdapter != null) {
                    position = mAdapter.getPositionForSection(s.charAt(0));
                }
                if (position != -1) {
                    mListView.setSelection(position);
                } else if (s.contains("#")) {
                    mListView.setSelection(0);
                }
            }
        });
        mSearchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<Contact> temp = new ArrayList<>();
                for (Contact data : datas) {
                    if (data.getName().contains(s) || data.getPinyin().contains(s)) {
                        temp.add(data);
                    }
                }
                if (mAdapter != null) {
                    mAdapter = new ContactAdapter(AddCarModelActivity.this, temp);
                    mListView.setAdapter(mAdapter);

                    //再次获取焦点
                    mSearchInput.setFocusable(true);
                    mSearchInput.setFocusableInTouchMode(true);
                    mSearchInput.requestFocus();
                    mSearchInput.findFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mSearchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //关闭软键盘
                    CommonUtil.hideInput(AddCarModelActivity.this);
                    //doSearch();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void initData() {
        //获取汽车品牌
        showProgress(true, getString(R.string.app_loading));
        Map<String, String> params = new HashMap<>();
        params.put("parent_id", "0");
        params.put("u_token", localUserInfo.getToken());
        Request(params);
    }

    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.CarNameList, params, headerMap, new CallBackUtil<AddCarModelBean>() {
            @Override
            public AddCarModelBean onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(AddCarModelBean response) {

                //耗时操作
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        AddCarModelActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //热门列表
                                list1 = response.getHost_list();
                                mAdapter1 = new CommonAdapter<AddCarModelBean.HostListBean>
                                        (AddCarModelActivity.this, R.layout.item_addcar_hot, list1) {
                                    @Override
                                    protected void convert(ViewHolder holder, AddCarModelBean.HostListBean model, int position) {
                                        holder.setText(R.id.textView, model.getSName());
                                        ImageView imageView = holder.getView(R.id.imageView);
//                        if (!model.getSLogo().equals(""))
                                        Glide.with(AddCarModelActivity.this)
                                                .load(URLs.IMGHOST + model.getSLogo())
                                                .centerCrop()
//                    .placeholder(R.mipmap.headimg)//加载站位图
//                    .error(R.mipmap.headimg)//加载失败
                                                .into(imageView);//加载图片
                                    }
                                };
                                mAdapter1.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                        Map<String, String> params = new HashMap<>();
                                        params.put("parent_id", list1.get(i).getYSedanBrandId());
                                        params.put("u_token", localUserInfo.getToken());
                                        Request1(params, list1.get(i).getYSedanBrandId(), URLs.IMGHOST + list1.get(i).getSLogo(), list1.get(i).getSName());
                                    }

                                    @Override
                                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                        return false;
                                    }
                                });
                                recyclerView1.setAdapter(mAdapter1);

                                //索引列表
                                datas.clear();
                                for (AddCarModelBean.ListBean bean : response.getList()) {
                                    Contact data = new Contact();
                                    data.setName(bean.getSName());
                                    data.setUrl(URLs.IMGHOST + bean.getSLogo());
                                    data.setId(bean.getYSedanBrandId());
                                    data.setPinyin(HanziToPinyin.getPinYin(data.getName()));
                                    datas.add(data);
                                }
                                /*//虚拟数据
                                Contact data = new Contact();
                                data.setName("12阿斯顿马丁");
                                data.setUrl(URLs.IMGHOST + response.getList().get(0).getSLogo());
                                data.setId(response.getList().get(0).getYSedanBrandId());
                                data.setPinyin(HanziToPinyin.getPinYin(data.getName()));
                                datas.add(data);
                                Contact data_1 = new Contact();
                                data_1.setName("阿斯顿马丁");
                                data_1.setUrl(URLs.IMGHOST + response.getList().get(0).getSLogo());
                                data_1.setId(response.getList().get(0).getYSedanBrandId());
                                data_1.setPinyin(HanziToPinyin.getPinYin(data_1.getName()));
                                datas.add(data_1);
                                Contact data1 = new Contact();
                                data1.setName("宝马");
                                data1.setUrl(URLs.IMGHOST + response.getList().get(0).getSLogo());
                                data1.setId(response.getList().get(0).getYSedanBrandId());
                                data1.setPinyin(HanziToPinyin.getPinYin(data1.getName()));
                                datas.add(data1);
                                Contact data2 = new Contact();
                                data2.setName("大众");
                                data2.setUrl(URLs.IMGHOST + response.getList().get(0).getSLogo());
                                data2.setId(response.getList().get(0).getYSedanBrandId());
                                data2.setPinyin(HanziToPinyin.getPinYin(data2.getName()));
                                datas.add(data2);
                                Contact data3 = new Contact();
                                data3.setName("丰田");
                                data3.setUrl(URLs.IMGHOST + response.getList().get(0).getSLogo());
                                data3.setId(response.getList().get(0).getYSedanBrandId());
                                data3.setPinyin(HanziToPinyin.getPinYin(data3.getName()));
                                datas.add(data3);
                                Contact data4 = new Contact();
                                data4.setName("现代");
                                data4.setUrl(URLs.IMGHOST + response.getList().get(0).getSLogo());
                                data4.setId(response.getList().get(0).getYSedanBrandId());
                                data4.setPinyin(HanziToPinyin.getPinYin(data4.getName()));
                                datas.add(data4);
                                Contact data5 = new Contact();
                                data5.setName("日产");
                                data5.setUrl(URLs.IMGHOST + response.getList().get(0).getSLogo());
                                data5.setId(response.getList().get(0).getYSedanBrandId());
                                data5.setPinyin(HanziToPinyin.getPinYin(data5.getName()));
                                datas.add(data5);
                                Contact data6 = new Contact();
                                data6.setName("奔驰");
                                data6.setUrl(URLs.IMGHOST + response.getList().get(0).getSLogo());
                                data6.setId(response.getList().get(0).getYSedanBrandId());
                                data6.setPinyin(HanziToPinyin.getPinYin(data6.getName()));
                                datas.add(data6);*/


                                //添加头部，必须设置adapter，不然不会显示,头部不可点击
                                mListView.addHeaderView(headView, null, false);

                                mAdapter = new ContactAdapter(AddCarModelActivity.this, datas);
                                mListView.setAdapter(mAdapter);
                                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Map<String, String> params = new HashMap<>();
                                        params.put("parent_id", datas.get(position - 1).getId());
                                        params.put("u_token", localUserInfo.getToken());
                                        Request1(params, datas.get(position - 1).getId(), datas.get(position - 1).getUrl(), datas.get(position - 1).getName());
                                    }
                                });

                                hideProgress();
                            }
                        });
                    }
                }).start();

            }
        });
    }

    private void Request1(Map<String, String> params, String id, String logo, String brand) {
        OkhttpUtil.okHttpPost(URLs.CarNameList, params, headerMap, new CallBackUtil<AddCarModelBean>() {
            @Override
            public AddCarModelBean onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(AddCarModelBean response) {
                hideProgress();
                /**
                 * 弹出弹窗
                 */
                AddCarPopupWindow popupwindow = new AddCarPopupWindow(AddCarModelActivity.this, response.getList(), id, logo, brand);
                popupwindow.showAtLocation(AddCarModelActivity.this.findViewById(R.id.linearLayout), Gravity.CENTER, 0, 0);
                popupwindow.setOnDismissListener(AddCarModelActivity.this);
            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle("选择车型");
    }

    @Override
    public void onDismiss() {
        //popupwindow 消失监听
        MyLogger.i("选择的车型", "id:" + AddCarPopupWindow.y_sedan_brand_id + "\n车型：" + AddCarPopupWindow.pingpai + AddCarPopupWindow.xinghao);
        if (!AddCarPopupWindow.xinghao.equals("")) {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("y_sedan_brand_id", AddCarPopupWindow.y_sedan_brand_id);
            bundle.putString("pingpai", AddCarPopupWindow.pingpai);
            bundle.putString("xinghao", AddCarPopupWindow.xinghao);
            resultIntent.putExtras(bundle);
            AddCarModelActivity.this.setResult(RESULT_OK, resultIntent);
            finish();
        }

    }
}
