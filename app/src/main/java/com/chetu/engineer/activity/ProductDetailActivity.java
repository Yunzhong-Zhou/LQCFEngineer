package com.chetu.engineer.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetu.engineer.R;
import com.chetu.engineer.adapter.ImageAdapter;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.PingJiaModel;
import com.chetu.engineer.model.ProductDetailModel;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.popupwindow.PhotoShowDialog;
import com.chetu.engineer.utils.MyLogger;
import com.chetu.engineer.view.LoadingLayout;
import com.chetu.engineer.view.MyDefaultFooter;
import com.chetu.engineer.view.MyDefaultHeader;
import com.cy.cyflowlayoutlibrary.FlowLayout;
import com.cy.cyflowlayoutlibrary.FlowLayoutAdapter;
import com.cy.dialog.BaseDialog;
import com.liaoinstan.springview.widget.SpringView;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.listener.OnPageChangeListener;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zyz on 2020/6/10.
 * 商品详情
 */
public class ProductDetailActivity extends BaseActivity {
    ProductDetailModel model;
    String y_goods_id = "", y_store_id = "0", store_name = "";

    TextView textView_num, textView_moeny;
    int type = 1;
    TextView textView1, textView2, textView3;

    ViewPager viewPager;
    ArrayList<View> pageViews;
    //页面一
    int page = 0;
    View View1;
    Banner banner;
    TextView banner_indicator;
    ArrayList<String> images = new ArrayList<>();
    TextView head1_tv1, head1_tv2, head1_tv3, head1_tv4, head1_tv5, head1_pinglun,tv_storename, tv_phone, tv_addr, tv_juli;

    LoadingLayout loading_layout1;
    SpringView springView1;

    RecyclerView view1_rv;
    List<PingJiaModel.ListBean> list_view1 = new ArrayList<>();//保留前2条数据 list1 = list.subList(0, 2);
    CommonAdapter<PingJiaModel.ListBean> mAdapter_view1;

    ImageView iv_xihuan;
    boolean isShouChange = false;
    String y_user_collection_id = "";

    //页面二
    View View2;
    LinearLayout head2_ll_add;
    WebView webView;

    //页面三
    View View3;
    TextView head3_pinglun;
    RecyclerView view3_rv;
    List<PingJiaModel.ListBean> list_view3 = new ArrayList<>();
    CommonAdapter<PingJiaModel.ListBean> mAdapter_view3;
    LoadingLayout loading_layout3;
    SpringView springView3;
    //规格
    CommonAdapter<ProductDetailModel.SpecificListBeanX> adapter;
    FlowLayoutAdapter<String> flowLayoutAdapter;
    List<Integer> selects = new ArrayList<>();
    int g_num = 1;
    long allmoney = 0;
    String goods_specific_idstr = "", s_value = "", y_store_id1 = "", y_store_service_id = "0", is_install = "1";
    TextView tv_mendian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetail);
    }

    @Override
    protected void initView() {
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView_num = findViewByID_My(R.id.textView_num);
        textView_moeny = findViewByID_My(R.id.textView_moeny);

        /**
         * 布局1
         */
        View1 = View.inflate(ProductDetailActivity.this, R.layout.view_productdetail1, null);
        banner = View1.findViewById(R.id.banner);
        banner_indicator = View1.findViewById(R.id.banner_indicator);

        head1_tv1 = View1.findViewById(R.id.head1_tv1);
        head1_tv3 = View1.findViewById(R.id.head1_tv3);
        head1_tv4 = View1.findViewById(R.id.head1_tv4);
        head1_tv5 = View1.findViewById(R.id.head1_tv5);

        iv_xihuan = View1.findViewById(R.id.iv_xihuan);

        head1_tv2 = View1.findViewById(R.id.head1_tv2);
        head1_tv2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//中划线
        head1_tv2.getPaint().setAntiAlias(true); //去掉锯齿

        tv_storename = View1.findViewById(R.id.tv_storename);
        tv_phone = View1.findViewById(R.id.tv_phone);
        tv_addr = View1.findViewById(R.id.tv_addr);
        tv_juli = View1.findViewById(R.id.tv_juli);

        //评论列表
        head1_pinglun = View1.findViewById(R.id.head1_pinglun);
        view1_rv = View1.findViewById(R.id.view1_rv);
        view1_rv.setLayoutManager(new LinearLayoutManager(this));

        //刷新
        loading_layout1 = View1.findViewById(R.id.loading_layout1);
        springView1 = View1.findViewById(R.id.springView1);
        springView1.setHeader(new MyDefaultHeader(this));
        springView1.setFooter(new MyDefaultFooter(this));

        springView1.setEnableFooter(false);//不需要加载更多
        springView1.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                Map<String, String> params = new HashMap<>();
                params.put("y_goods_id", y_goods_id);
                params.put("u_token", localUserInfo.getToken());
                Request(params);

            }

            @Override
            public void onLoadmore() {
            }
        });

        /**
         * 布局2
         */
        View2 = View.inflate(ProductDetailActivity.this, R.layout.view_productdetail2, null);
//        head2_ll_add = View2.findViewById(R.id.head2_ll_add);
        webView = View2.findViewById(R.id.webView);

        /**
         * 布局3
         */
        View3 = View.inflate(ProductDetailActivity.this, R.layout.view_productdetail3, null);
        head3_pinglun = View3.findViewById(R.id.head3_pinglun);
        view3_rv = View3.findViewById(R.id.view3_rv);
        view3_rv.setLayoutManager(new LinearLayoutManager(this));

        loading_layout3 = View3.findViewById(R.id.loading_layout3);
        springView3 = View3.findViewById(R.id.springView3);
        springView3.setHeader(new MyDefaultHeader(this));
        springView3.setFooter(new MyDefaultFooter(this));

        springView3.setEnableFooter(true);//不需要加载更多
        springView3.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                Map<String, String> params = new HashMap<>();
                params.put("y_goods_id", y_goods_id);
                params.put("u_token", localUserInfo.getToken());
                Request(params);
            }

            @Override
            public void onLoadmore() {
                page++;
                Map<String, String> params = new HashMap<>();
                params.put("y_goods_id", y_goods_id);
                params.put("y_store_id", y_store_id);
                params.put("page", page + "");
//                params.put("u_token", localUserInfo.getToken());
                RequestPingJiaMore(params);
            }
        });
        /**
         * viewPager
         */
        pageViews = new ArrayList<View>();
        pageViews.add(View1);
        pageViews.add(View2);
        pageViews.add(View3);
        viewPager = findViewByID_My(R.id.viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                tv_currentPosition.setText(position + 1 + "/" + imgList.size());
                type = position + 1;
                changeUI();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //数据适配器
        PagerAdapter mPagerAdapter = new PagerAdapter() {

            @Override
            //获取当前窗体界面数
            public int getCount() {
                // TODO Auto-generated method stub
                return pageViews.size();
            }

            @Override
            //判断是否由对象生成界面
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            //使从ViewGroup中移出当前View
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(pageViews.get(position));
            }

            //返回一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中
            public View instantiateItem(ViewGroup container, int position) {
                container.addView(pageViews.get(position));
                return pageViews.get(position);
            }
        };
        //绑定适配器
        viewPager.setAdapter(mPagerAdapter);
        //设置viewPager的初始界面为第一个界面
        viewPager.setCurrentItem(0);
    }

    @Override
    protected void initData() {
        y_goods_id = getIntent().getStringExtra("y_goods_id");
        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading));
        page = 1;
        Map<String, String> params = new HashMap<>();
        params.put("y_goods_id", y_goods_id);
        params.put("u_token", localUserInfo.getToken());
        Request(params);


    }

    /**
     * 获取详情数据
     *
     * @param params
     */
    private void Request(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ProductDetail, params, headerMap, new CallBackUtil<ProductDetailModel>() {
            @Override
            public ProductDetailModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                if (springView1 != null) {
                    springView1.onFinishFreshAndLoad();
                }
                if (springView3 != null) {
                    springView3.onFinishFreshAndLoad();
                }
                showEmptyPage();
//                myToast(err);
            }

            @Override
            public void onResponse(ProductDetailModel response) {
                model = response;
//                hideProgress();
//                showContentPage();

                y_store_id = response.getInfo().getYStoreId();
                y_store_service_id = "0";//商品不用填,下单服务需要填
                y_goods_id = response.getInfo().getYGoodsId();

                allmoney = (long) response.getInfo().getGPrice();
                textView_moeny.setText("¥" + allmoney);
                g_num = 1;
                textView_num.setText(g_num + "");


                /**
                 * 第一页-商品信息
                 */
                //banner
                images.clear();
                for (String s : response.getInfo().getImgArr()) {
                    images.add(URLs.IMGHOST + s);
                }
                if (images.size() > 0) {
                    banner_indicator.setText("1/" + images.size());
                } else {
                    banner_indicator.setText("0/" + images.size());
                }
                //TODO 父控件滑动时，banner切换会获取焦点，然后自动全部显示。不想让banner获取焦点可以给父控件加上：
                // android:focusable="true"
                // android:focusableInTouchMode="true"
                banner.addBannerLifecycleObserver(ProductDetailActivity.this)//添加生命周期观察者
                        .setDelayTime(3000)//设置轮播时间
//                .setBannerGalleryEffect(10, 10)//为banner添加画廊效果
                        .setAdapter(new ImageAdapter(images))
//                .setIndicator(new RectangleIndicator(StoreDetailActivity.this))
//                .setIndicator(new BaseIndicator(StoreDetailActivity.this))
                        .setIndicatorGravity(IndicatorConfig.Direction.RIGHT)
                        .start();
                banner.addOnPageChangeListener(new OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    }

                    @Override
                    public void onPageSelected(int position) {
                        banner_indicator.setText((position + 1) + "/" + images.size());
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(Object data, int position) {
                        /*ZoomIMGPopupWindow popupwindow = new ZoomIMGPopupWindow(ProductDetailActivity.this,
                                URLs.IMGHOST + response.getInfo().getStore_step_two().getImg_positive());*/
//                        ViewPagerPhotoViewActivity.startThisActivity(images, position, ProductDetailActivity.this);
                        PhotoShowDialog photoShowDialog = new PhotoShowDialog(ProductDetailActivity.this, images, position);
                        photoShowDialog.show();
                    }
                });
                head1_tv1.setText("¥" + response.getInfo().getGPrice());
                head1_tv2.setText("¥" + response.getInfo().getOrPrice());
                head1_tv3.setText(response.getInfo().getGName());
                head1_tv4.setText(response.getInfo().getGName());
                //是否喜欢
                if (response.getCollection_info() != null && response.getCollection_info().getYUserCollectionId() != null) {
                    if (!response.getCollection_info().getYUserCollectionId().equals("")) {
                        y_user_collection_id = response.getCollection_info().getYUserCollectionId();
                        isShouChange = true;
                        iv_xihuan.setImageResource(R.mipmap.ic_xin_yixuan);
                    }
                } else {
                    y_user_collection_id = "";
                    isShouChange = false;
                    iv_xihuan.setImageResource(R.mipmap.ic_xin_weixuan);
                }

                tv_storename.setText(response.getStore_info().getVName());//店铺名称
                tv_phone.setText(response.getStore_info().getPhone());//店铺电话
                tv_addr.setText(response.getStore_info().getAddress());//店铺地址
//                tv_juli.setText("距离" + response.getInfo().getDistance() + "m");//距离

                /**
                 * 第二页-详情
                 */
                WebSettings wSet = webView.getSettings();
                wSet.setJavaScriptEnabled(true);
                //适配屏幕
                StringBuilder sb = new StringBuilder();
                sb.append(getHtmlData(response.getInfo().getGDetails()));
                //加载HTML代码
                webView.loadDataWithBaseURL(null, sb.toString(), "text/html", "UTF-8", null);

                /**
                 * 第三页-评论
                 */
                Map<String, String> params = new HashMap<>();
                params.put("y_goods_id", y_goods_id);
                params.put("y_store_id", "");
                params.put("page", page + "");
//                params.put("u_token", localUserInfo.getToken());
                RequestPingJia(params);
            }
        });
    }

    /**
     * 获取评价
     *
     * @param params
     */
    private void RequestPingJia(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.PingJiaList, params, headerMap, new CallBackUtil<PingJiaModel>() {
            @Override
            public PingJiaModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                if (springView1 != null) {
                    springView1.onFinishFreshAndLoad();
                }
                if (springView3 != null) {
                    springView3.onFinishFreshAndLoad();
                }
//                myToast(err);
            }

            @Override
            public void onResponse(PingJiaModel response) {
                hideProgress();
                if (springView1 != null) {
                    springView1.onFinishFreshAndLoad();
                }
                if (springView3 != null) {
                    springView3.onFinishFreshAndLoad();
                }

                if (response.getList().size() != 0) {
                    loading_layout1.showContent();
                    loading_layout3.showContent();

                    if (response.getList().size() >= 2) {
                        list_view1 = response.getList().subList(0, 1);//保留前2条数据
                    } else {
                        list_view1 = response.getList();
                    }

                    list_view3 = response.getList();
                    head1_pinglun.setText("用户评论（" + response.getCount() + "）");
                    head3_pinglun.setText("用户评论（" + response.getCount() + "）");

                    mAdapter_view1 = new CommonAdapter<PingJiaModel.ListBean>
                            (ProductDetailActivity.this, R.layout.item_productdetail, list_view1) {
                        @Override
                        protected void convert(ViewHolder holder, PingJiaModel.ListBean model, int position) {
                        /*//用户评论
                        TextView pinglun = holder.getView(R.id.pinglun);
                        pinglun.setText("用户评论（" + list.size() + "）");
                        if (position == 2)
                            pinglun.setVisibility(View.VISIBLE);
                        else
                            pinglun.setVisibility(View.GONE);*/

                            //信息
                            holder.setText(R.id.tv_name, model.getY_user().getUserName());
                            holder.setText(R.id.tv_time, model.getCreateDate());
                            holder.setText(R.id.tv_content, model.getYMsg());
                            RatingBar ratingbar = holder.getView(R.id.ratingbar);
                            ratingbar.setRating(Float.valueOf(model.getStarC()));
                            ImageView iv = holder.getView(R.id.iv);
                            Glide.with(ProductDetailActivity.this).load(model)
                                    .centerCrop()
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(iv);

                            //横向图片
                            RecyclerView rv = holder.getView(R.id.rv);
                            LinearLayoutManager llm1 = new LinearLayoutManager(ProductDetailActivity.this);
                            llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                            rv.setLayoutManager(llm1);
                            CommonAdapter<String> ca = new CommonAdapter<String>
                                    (ProductDetailActivity.this, R.layout.item_img_80_60, images) {
                                @Override
                                protected void convert(ViewHolder holder, String model, int position) {
                                    ImageView iv = holder.getView(R.id.iv);
                                    Glide.with(ProductDetailActivity.this).load(model)
//                            .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                            .placeholder(R.mipmap.loading)//加载站位图
                                            .error(R.mipmap.zanwutupian)//加载失败
                                            .into(iv);//加载图片
                                }
                            };
                            rv.setAdapter(ca);
                        }
                    };
                    view1_rv.setAdapter(mAdapter_view1);

                    mAdapter_view3 = new CommonAdapter<PingJiaModel.ListBean>
                            (ProductDetailActivity.this, R.layout.item_productdetail, list_view3) {
                        @Override
                        protected void convert(ViewHolder holder, PingJiaModel.ListBean model, int position) {
                        /*//用户评论
                        TextView pinglun = holder.getView(R.id.pinglun);
                        pinglun.setText("用户评论（" + list.size() + "）");
                        if (position == 2)
                            pinglun.setVisibility(View.VISIBLE);
                        else
                            pinglun.setVisibility(View.GONE);*/

                            //信息
                            holder.setText(R.id.tv_name, model.getY_user().getUserName());
                            holder.setText(R.id.tv_time, model.getCreateDate());
                            holder.setText(R.id.tv_content, model.getYMsg());
                            RatingBar ratingbar = holder.getView(R.id.ratingbar);
                            ratingbar.setRating(Float.valueOf(model.getStarC()));
                            ImageView iv = holder.getView(R.id.iv);
                            Glide.with(ProductDetailActivity.this).load(model)
                                    .centerCrop()
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(iv);

                            //横向图片
                            List<String> list_img = new ArrayList<>();
                            for (String s : model.getImgArr()) {
                                list_img.add(URLs.IMGHOST + s);
                            }
                            RecyclerView rv = holder.getView(R.id.rv);
                            LinearLayoutManager llm1 = new LinearLayoutManager(ProductDetailActivity.this);
                            llm1.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
                            rv.setLayoutManager(llm1);
                            CommonAdapter<String> ca = new CommonAdapter<String>
                                    (ProductDetailActivity.this, R.layout.item_img_80_80, list_img) {
                                @Override
                                protected void convert(ViewHolder holder, String model, int position) {
                                    ImageView iv = holder.getView(R.id.iv);
                                    Glide.with(ProductDetailActivity.this).load(model)
//                            .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                            .placeholder(R.mipmap.loading)//加载站位图
                                            .error(R.mipmap.zanwutupian)//加载失败
                                            .into(iv);//加载图片
                                }
                            };
                            rv.setAdapter(ca);
                        }
                    };
                    view3_rv.setAdapter(mAdapter_view3);
                } else {
                    loading_layout1.showEmpty();
                    loading_layout3.showEmpty();
                }
            }
        });
    }


    /**
     * 获取评价=更多
     *
     * @param params
     */
    private void RequestPingJiaMore(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.PingJiaList, params, headerMap, new CallBackUtil<PingJiaModel>() {
            @Override
            public PingJiaModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
//                hideProgress();
                if (springView1 != null) {
                    springView1.onFinishFreshAndLoad();
                }
                if (springView3 != null) {
                    springView3.onFinishFreshAndLoad();
                }
                myToast(err);
                page--;
            }

            @Override
            public void onResponse(PingJiaModel response) {
//                hideProgress();
                if (springView1 != null) {
                    springView1.onFinishFreshAndLoad();
                }
                if (springView3 != null) {
                    springView3.onFinishFreshAndLoad();
                }

                List<PingJiaModel.ListBean> list1 = new ArrayList<>();
                list1 = response.getList();
                if (list1.size() == 0) {
                    page--;
                    myToast(getString(R.string.app_nomore));
                } else {
                    list_view3.addAll(list1);
                    mAdapter_view3.notifyDataSetChanged();
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
            case R.id.iv_xihuan:
                //收藏
                isShouChange = !isShouChange;
                if (isShouChange) {
                    iv_xihuan.setImageResource(R.mipmap.ic_xin_yixuan);
                    Map<String, String> params = new HashMap<>();
                    params.put("u_token", localUserInfo.getToken());
                    params.put("y_id", y_goods_id);
                    params.put("category", "1");//1为商品收藏 2为商家收藏 3为论坛收藏
                    RequestShouChang(params);
                } else {
                    iv_xihuan.setImageResource(R.mipmap.ic_xin_weixuan);
                    Map<String, String> params = new HashMap<>();
                    params.put("u_token", localUserInfo.getToken());
                    params.put("y_user_collection_id", y_user_collection_id);
                    RequestQuXiaoShouChang(params);
                }
                break;
            case R.id.head1_tv5:
                //选择规格
                showDialog();
                break;
            case R.id.textView1:
                //商品
                viewPager.setCurrentItem(0);

                type = 1;
                changeUI();
                break;
            case R.id.textView2:
                //详情
                viewPager.setCurrentItem(1);

                type = 2;
                changeUI();
                break;
            case R.id.textView3:
            case R.id.head1_pinglun:
                //评价
                viewPager.setCurrentItem(2);

                type = 3;
                changeUI();
                break;

            case R.id.textView_goumai:
                if (match()) {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("u_token", localUserInfo.getToken());
                    params.put("y_store_id", y_store_id1);
                    params.put("y_store_service_id", y_store_service_id);
                    params.put("y_goods_id", y_goods_id);
                    params.put("is_service", "3");//1为服务  2为服务下边的商品 3为独立商品
                    params.put("g_num", g_num + "");
                    params.put("goods_specific_idstr", goods_specific_idstr);
                    params.put("s_value", s_value);
                    params.put("is_install", is_install);
                    RequestAdd(params);
                }
                break;
            default:

                break;
        }
    }

    /**
     * 下单
     *
     * @param params
     */
    private void RequestAdd(HashMap<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ADDShop, params, headerMap, new CallBackUtil<Object>() {
            @Override
            public Object onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(Object response) {
                hideProgress();
                showToast("加入购物车成功", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        finish();
                    }
                });

            }
        });
    }

    /**
     * 显示规格弹窗
     */
    private void showDialog() {
        BaseDialog dialog1 = new BaseDialog(ProductDetailActivity.this);
        dialog1.contentView(R.layout.dialog_productdetail)
                .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT))
                .animType(BaseDialog.AnimInType.BOTTOM)
                .canceledOnTouchOutside(false)
                .gravity(Gravity.BOTTOM)
                .dimAmount(0.7f)
                .show();

        TextView tv_title = dialog1.findViewById(R.id.tv_title);
        tv_title.setText(model.getInfo().getGName());
        TextView tv_money = dialog1.findViewById(R.id.tv_money);
        tv_money.setText("¥" + model.getInfo().getGPrice());
        ImageView iv = dialog1.findViewById(R.id.iv);
        Glide.with(ProductDetailActivity.this).load(URLs.IMGHOST + model.getInfo().getGImg())
                .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                .placeholder(R.mipmap.loading)//加载站位图
                .error(R.mipmap.zanwutupian)//加载失败
                .into(iv);//加载图片

        TextView tv_tab = dialog1.findViewById(R.id.tv_tab);

        tv_mendian = dialog1.findViewById(R.id.tv_mendian);
        tv_mendian.setText(store_name);

        TextView tv_num = dialog1.findViewById(R.id.tv_num);
        tv_num.setText(g_num + "");

        TextView tv_anzhuang = dialog1.findViewById(R.id.tv_anzhuang);
        TextView tv_buanzhuang = dialog1.findViewById(R.id.tv_buanzhuang);
        tv_anzhuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //安装
                is_install = "1";
                tv_anzhuang.setTextColor(getResources().getColor(R.color.blue));
                tv_anzhuang.setBackgroundResource(R.drawable.yuanjiaobiankuang_15_lanse);
                tv_buanzhuang.setTextColor(getResources().getColor(R.color.black));
                tv_buanzhuang.setBackgroundResource(R.drawable.yuanjiao_15_huise1);

            }
        });
        tv_buanzhuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //不安装
                is_install = "2";
                tv_anzhuang.setTextColor(getResources().getColor(R.color.black));
                tv_anzhuang.setBackgroundResource(R.drawable.yuanjiao_15_huise1);
                tv_buanzhuang.setTextColor(getResources().getColor(R.color.blue));
                tv_buanzhuang.setBackgroundResource(R.drawable.yuanjiaobiankuang_15_lanse);

            }
        });

        RecyclerView rv = dialog1.findViewById(R.id.rv);
        selects.clear();
        for (ProductDetailModel.SpecificListBeanX bean : model.getSpecific_list()) {
            selects.add(0);
        }
        rv.setLayoutManager(new LinearLayoutManager(ProductDetailActivity.this));
        adapter = new CommonAdapter<ProductDetailModel.SpecificListBeanX>
                (ProductDetailActivity.this, R.layout.item_dialog_guige, model.getSpecific_list()) {
            @Override
            protected void convert(ViewHolder holder, ProductDetailModel.SpecificListBeanX model, int position) {
                holder.setText(R.id.tv, model.getSName());
//                    String[] strArr = model.getSValue().split("\\|\\|");
                List<String> tabs = new ArrayList<>();
                for (ProductDetailModel.SpecificListBeanX.SpecificListBean s : model.getSpecific_List()) {
                    tabs.add(s.getPName());
                }
                flowLayoutAdapter = new FlowLayoutAdapter<String>(tabs) {
                    @Override
                    public void bindDataToView(ViewHolder holder, int i, String bean) {
                        TextView tv = holder.getView(R.id.tv);
                        tv.setText(bean);
                        if (selects.get(position) == i) {
                            tv.setTextColor(getResources().getColor(R.color.blue));
                            tv.setBackgroundResource(R.drawable.yuanjiaobiankuang_15_lanse);
                        } else {
                            tv.setTextColor(getResources().getColor(R.color.black));
                            tv.setBackgroundResource(R.drawable.yuanjiao_15_huise1);
                        }
                    }

                    @Override
                    public void onItemClick(int i, String bean) {
                        selects.set(position, i);
                        adapter.notifyDataSetChanged();
                        //计算及显示
                        addView(tv_tab, tv_money, g_num);
                    }

                    @Override
                    public int getItemLayoutID(int position, String bean) {
                        return R.layout.item_guige_flowlayout;
                    }
                };
                ((FlowLayout) holder.getView(R.id.flowLayout)).setAdapter(flowLayoutAdapter);

            }
        };
        rv.setAdapter(adapter);
        dialog1.findViewById(R.id.tv_jian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //减号
                if (g_num > 1) {
                    g_num--;
                    tv_num.setText(g_num + "");
                    addView(tv_tab, tv_money, g_num);
                }
            }
        });
        dialog1.findViewById(R.id.tv_jia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //加号
//                        if (num < 100) {
                g_num++;
                tv_num.setText(g_num + "");

                addView(tv_tab, tv_money, g_num);
//                        }
            }
        });
        tv_mendian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //门店
                /*Intent intent1 = new Intent(ProductDetailActivity.this, SelectStoreActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("type", 10001);
                intent1.putExtras(bundle1);
                startActivityForResult(intent1, 10001, bundle1);*/
            }
        });
        dialog1.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
        addView(tv_tab, tv_money, g_num);
    }

    /**
     * 计算
     *
     * @param tv_tab
     * @param tv_money
     * @param num
     */
    private void addView(TextView tv_tab, TextView tv_money, int num) {
        goods_specific_idstr = "";
        s_value = "";
        double tabMoney = 0;
        for (int i = 0; i < model.getSpecific_list().size(); i++) {
//            String[] strArr = model.getSpecific_list().get(i).getSValue().split("\\|\\|");
            for (int j = 0; j < model.getSpecific_list().get(i).getSpecific_List().size(); j++) {
                if (selects.get(i) == j) {
                    s_value += model.getSpecific_list().get(i).getSpecific_List().get(j).getPName() + "||";
                    goods_specific_idstr += model.getSpecific_list().get(i).getSpecific_List().get(j).getYGoodsSpecificId() + "||";
                    tabMoney += model.getSpecific_list().get(i).getSpecific_List().get(j).getSPrice();
                }
            }
        }

        goods_specific_idstr = goods_specific_idstr.substring(0, goods_specific_idstr.length() - 2);
        MyLogger.i(">>>>>>" + goods_specific_idstr);
        s_value = s_value.substring(0, s_value.length() - 2);
        tv_tab.setText(s_value);

        allmoney = (long) ((model.getInfo().getGPrice() + tabMoney) * num);
        tv_money.setText("¥" + allmoney);

        textView_moeny.setText("¥" + allmoney);
        textView_num.setText(g_num + "");
        head1_tv5.setText(s_value);
    }

    private boolean match() {
        if (goods_specific_idstr.equals("")) {
            myToast("请先选择规格");
            return false;
        }

        if (is_install.equals("1")) {
            if (y_store_id1.equals("")) {
                myToast("请选择门店");
                return false;
            }
        }

        return true;
    }

    private void changeUI() {
        switch (type) {
            case 1:
                textView1.setTextColor(getResources().getColor(R.color.blue));
                textView2.setTextColor(getResources().getColor(R.color.black2));
                textView3.setTextColor(getResources().getColor(R.color.black2));
                break;
            case 2:
                textView1.setTextColor(getResources().getColor(R.color.black2));
                textView2.setTextColor(getResources().getColor(R.color.blue));
                textView3.setTextColor(getResources().getColor(R.color.black2));
                break;
            case 3:
                textView1.setTextColor(getResources().getColor(R.color.black2));
                textView2.setTextColor(getResources().getColor(R.color.black2));
                textView3.setTextColor(getResources().getColor(R.color.blue));
                break;
        }
    }

    /**
     * 收藏
     *
     * @param params
     */
    private void RequestShouChang(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.ShouChang, params, headerMap, new CallBackUtil<Object>() {
            @Override
            public Object onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
            }

            @Override
            public void onResponse(Object response) {
                requestServer();
            }
        });
    }

    /**
     * 取消收藏
     *
     * @param params
     */
    private void RequestQuXiaoShouChang(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.QuXiaoShouChang, params, headerMap, new CallBackUtil<Object>() {
            @Override
            public Object onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {

            }

            @Override
            public void onResponse(Object response) {
            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10001:
                //选择门店
                if (data != null) {
                    Bundle bundle1 = data.getExtras();
                    y_store_id1 = bundle1.getString("y_store_id1");
//                    tv_carname.setText(bundle1.getString("store_name"));
                    store_name = bundle1.getString("store_name");
                    tv_mendian.setText(store_name);
                }
                break;
        }

    }

    /**
     * 富文本适配
     */
    private String getHtmlData(String bodyHTML) {
        String head = "<head>"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> "
                + "<style>img{max-width: 100%; width:auto; height:auto;}</style>"
                + "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }
}
