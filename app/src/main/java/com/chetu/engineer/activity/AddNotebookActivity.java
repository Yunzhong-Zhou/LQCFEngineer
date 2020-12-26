package com.chetu.engineer.activity;

import android.os.Bundle;

import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;

/**
 * Created by zyz on 2020/5/26.
 * 添加记事本
 */
public class AddNotebookActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnotebook);

        mImmersionBar.reset()
                .statusBarColor(R.color.background)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {
        titleView.setTitle("添加记录");
        titleView.setBackground(R.color.background);
    }
}
