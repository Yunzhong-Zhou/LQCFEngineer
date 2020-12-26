package com.chetu.engineer.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.SearchHoModel;
import com.chetu.engineer.model.SearchModel;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.utils.CommonUtil;
import com.chetu.engineer.utils.MyLogger;
import com.chetu.engineer.utils.SaveArrayListUtil;
import com.cy.cyflowlayoutlibrary.FlowLayout;
import com.cy.cyflowlayoutlibrary.FlowLayoutAdapter;
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
 * Created by zyz on 2020/6/1.
 * 搜索
 */
public class SearchActivity extends BaseActivity {
    ArrayList<String> searchList = new ArrayList<>();
    String keys = "";
    EditText et_search;
    ImageView iv_delete;
    FlowLayout flowLayout;
    List<String> searchHotList = new ArrayList<>();

    RecyclerView rv_history;
    CommonAdapter<String> mAdapter_history;

    RecyclerView recyclerView;
    CommonAdapter<SearchModel.ListBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mImmersionBar.reset()
                .statusBarColor(R.color.background)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @Override
    protected void initView() {
        flowLayout = findViewByID_My(R.id.flowLayout);

        rv_history = findViewByID_My(R.id.rv_history);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        rv_history.setLayoutManager(mLinearLayoutManager);

        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        iv_delete = findViewByID_My(R.id.iv_delete);
        et_search = findViewByID_My(R.id.editText_search);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /*ArrayList<Contact> temp = new ArrayList<>();
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
                }*/


            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    iv_delete.setVisibility(View.GONE);
                } else {
                    iv_delete.setVisibility(View.VISIBLE);
                }
            }
        });
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_search.setText("");
                recyclerView.setVisibility(View.GONE);

                //再次获取焦点
                et_search.setFocusable(true);
                et_search.setFocusableInTouchMode(true);
                et_search.requestFocus();
                et_search.findFocus();
                CommonUtil.showInput(SearchActivity.this,et_search);//弹出键盘
            }
        });
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    MyLogger.i(">>>>>>>>输入后：" + et_search.getText().toString().trim());
                    //关闭软键盘
                    CommonUtil.hideInput(SearchActivity.this);
                    //do something
                    if (!et_search.getText().toString().trim().equals("")) {
                        keys = et_search.getText().toString().trim();
                        requestServer();
                    } else {
                        myToast("请输入需要搜索的内容");
                    }
                    return false;   //返回true，保留软键盘;false，隐藏软键盘
                }
                return false;
            }
        });
    }

    @Override
    protected void initData() {
//        keys = getIntent().getStringExtra("keys");
        ShowHistory();
        //获取热门搜索关键词
        RequestHot(params);
    }

    /**
     * 热门数据
     *
     * @param params
     */
    private void RequestHot(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.SearchHot, params, headerMap, new CallBackUtil<SearchHoModel>() {
            @Override
            public SearchHoModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
//                myToast(err);
            }

            @Override
            public void onResponse(SearchHoModel response) {
                hideProgress();
                searchHotList = response.getList();
                FlowLayoutAdapter<String> flowLayoutAdapter1 =
                        new FlowLayoutAdapter<String>
                                (response.getList()) {
                            @Override
                            public void bindDataToView(ViewHolder holder, int position,
                                                       String bean) {
                                TextView tv1 = holder.getView(R.id.tv1);
                                tv1.setText(bean);
                            }

                            @Override
                            public void onItemClick(int position, String bean) {
//                        showToast("点击" + position);
                                keys = bean;
                                requestServer();
                            }

                            @Override
                            public int getItemLayoutID(int position, String bean) {
                                return R.layout.item_searchhot;
                            }
                        };
                flowLayout.setAdapter(flowLayoutAdapter1);
            }
        });
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, "正在搜索...");
        et_search.setText(keys);

        SaveArrayListUtil.saveArrayList(SearchActivity.this, searchList, keys);
        //获取服务项目和banner
        HashMap<String, String> params = new HashMap<>();
        params.put("keys", keys);
        RequestSearchList(params);

        ShowHistory();
    }


    /**
     * 展示搜索数据
     *
     * @param params
     */
    private void RequestSearchList(HashMap<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Search, params, headerMap, new CallBackUtil<SearchModel>() {
            @Override
            public SearchModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
//                myToast(err);
                recyclerView.setVisibility(View.GONE);
            }

            @Override
            public void onResponse(SearchModel response) {
                hideProgress();
                if (response.getList().size() > 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    mAdapter = new CommonAdapter<SearchModel.ListBean>
                            (SearchActivity.this, R.layout.item_search, response.getList()) {
                        @Override
                        protected void convert(ViewHolder holder, SearchModel.ListBean model, int position) {
                            holder.setText(R.id.textView, model.getVName());
                            ImageView imageView1 = holder.getView(R.id.imageView);
                            Glide.with(SearchActivity.this)
                                    .load(URLs.IMGHOST + model.getPicture())
                                    .centerCrop()
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(imageView1);//加载图片
                        }
                    };
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            keys = searchList.get(i);
                            requestServer();
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                    recyclerView.setAdapter(mAdapter);
                } else {
                    recyclerView.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * 展示历史数据
     */
    private void ShowHistory() {
        searchList = SaveArrayListUtil.getSearchArrayList(SearchActivity.this);
        MyLogger.i(">>>>>>" + searchList.toString());
        mAdapter_history = new CommonAdapter<String>
                (SearchActivity.this, R.layout.item_search_history, searchList) {
            @Override
            protected void convert(ViewHolder holder, String model, int position) {
                holder.setText(R.id.textView, model);
                holder.getView(R.id.imageView).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        searchList.remove(position);
                        SaveArrayListUtil.removeArrayList(SearchActivity.this, searchList);
                        mAdapter_history.notifyDataSetChanged();
                    }
                });
            }
        };
        mAdapter_history.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                keys = searchList.get(i);
                requestServer();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        rv_history.setAdapter(mAdapter_history);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.right_btn:
                recyclerView.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }


}
