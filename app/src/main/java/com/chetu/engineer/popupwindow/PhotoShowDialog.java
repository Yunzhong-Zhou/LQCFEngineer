package com.chetu.engineer.popupwindow;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.chetu.engineer.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * 查看大图的dialog
 */

public class PhotoShowDialog extends Dialog {
    PhotoView photoView;
    private Context mContext;
    private List<String> photoLists;
    private int mPosition;
    private ViewPager vp;
    private TextView tv;

    public PhotoShowDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    public PhotoShowDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }


    public PhotoShowDialog(Context context, List<String> photoLists, int position) {
        this(context, R.style.Pic_Dialog);
        this.photoLists = photoLists;
        this.mPosition = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_zoomimg_list);
        init();
    }

    private void init() {
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        vp = findViewById(R.id.viewPager);
        tv = findViewById(R.id.textView);
        vp.setAdapter(new VpAdapter(mContext));
        vp.setCurrentItem(mPosition);
        tv.setText(vp.getCurrentItem() + 1 + "/" + photoLists.size());
        tv.setVisibility(photoLists.size() == 1 ? View.INVISIBLE : View.VISIBLE);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv.setText(position + 1 + "/" + photoLists.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class VpAdapter extends PagerAdapter {
        Context context;

        public VpAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return photoLists.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(context, R.layout.dialog_zoomimg, null);
            photoView = view.findViewById(R.id.img);
            photoView.enable();
            /*RequestOptions options = new RequestOptions()
                    .placeholder(R.mipmap.loading)
                    .error(R.mipmap.zanwutupian);
            Glide.with(context)
                    .load(photoLists.get(position))
                    .apply(options)
                    .transition(new DrawableTransitionOptions().crossFade())
                    .into(photoView);*/
            Glide.with(context).load(photoLists.get(position))
//                    .centerCrop()
//                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                    .placeholder(R.mipmap.loading)//加载站位图
                    .error(R.mipmap.zanwutupian)//加载失败
                    .into(photoView);//加载图片

            photoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PhotoShowDialog.this.dismiss();

                }
            });

            ((ViewPager) container).addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }
    }
}
