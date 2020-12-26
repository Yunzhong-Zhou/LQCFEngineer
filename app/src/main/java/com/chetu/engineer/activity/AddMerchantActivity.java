package com.chetu.engineer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;

/**
 * Created by zyz on 2020/5/26.
 * 添加商户
 */
public class AddMerchantActivity extends BaseActivity {
    int type = 1;//进度

    LinearLayout linearLayout1, linearLayout2, linearLayout3,ll_type1,ll_type2,ll_type3;
    TextView textView1, textView2, textView3;
    View view1, view2, view3;

    //第一步
    EditText et1_nick, et1_firmname, et1_firmaddr, et1_firmweb, et1_name, et1_phonenum, et1_email;
    TextView tv1_next;

    //第二步
    EditText et2_name, et2_num, et2_phone;
    TextView tv2_cardtype, tv2_date1, tv2_date2, tv2_next;
    LinearLayout ll2_shi, ll2_fou, ll2_up1load1, ll2_up1load2;
    ImageView iv2_shi, iv2_fou, iv2_card1, iv2_card2;
    //第三步
    EditText et3_creditcode, et3_yinyefanwei, et3_organizationcode, et3_qiyejieshao;
    TextView tv3_frimname, tv3_frimaddr, tv3_date, tv3_save, tv3_upload;
    LinearLayout ll3_up1load1, ll3_up1load2, ll3_up1load3;
    ImageView iv3_photo1, iv3_photo2, iv3_photo3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmerchant);
    }

    @Override
    protected void initView() {
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout3 = findViewByID_My(R.id.linearLayout3);
        ll_type1 = findViewByID_My(R.id.ll_type1);
        ll_type2 = findViewByID_My(R.id.ll_type2);
        ll_type3 = findViewByID_My(R.id.ll_type3);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        view3 = findViewByID_My(R.id.view3);

        //第一步
        et1_nick = findViewByID_My(R.id.et1_nick);
        et1_firmname = findViewByID_My(R.id.et1_firmname);
        et1_firmaddr = findViewByID_My(R.id.et1_firmaddr);
        et1_firmweb = findViewByID_My(R.id.et1_firmweb);
        et1_name = findViewByID_My(R.id.et1_name);
        et1_phonenum = findViewByID_My(R.id.et1_phonenum);
        et1_email = findViewByID_My(R.id.et1_email);
        tv1_next = findViewByID_My(R.id.tv1_next);

        //第二步
        et2_name = findViewByID_My(R.id.et2_name);
        et2_num = findViewByID_My(R.id.et2_num);
        et2_phone = findViewByID_My(R.id.et2_phone);
        tv2_cardtype = findViewByID_My(R.id.tv2_cardtype);
        tv2_date1 = findViewByID_My(R.id.tv2_date1);
        tv2_date2 = findViewByID_My(R.id.tv2_date2);
        tv2_next = findViewByID_My(R.id.tv2_next);
        ll2_shi = findViewByID_My(R.id.ll2_shi);
        ll2_fou = findViewByID_My(R.id.ll2_fou);
        ll2_up1load1 = findViewByID_My(R.id.ll2_up1load1);
        ll2_up1load2 = findViewByID_My(R.id.ll2_up1load2);
        iv2_shi = findViewByID_My(R.id.iv2_shi);
        iv2_fou = findViewByID_My(R.id.iv2_fou);
        iv2_card1 = findViewByID_My(R.id.iv2_card1);
        iv2_card2 = findViewByID_My(R.id.iv2_card2);
        //第三步
        et3_creditcode = findViewByID_My(R.id.et3_creditcode);
        et3_yinyefanwei = findViewByID_My(R.id.et3_yinyefanwei);
        et3_organizationcode = findViewByID_My(R.id.et3_organizationcode);
        et3_qiyejieshao = findViewByID_My(R.id.et3_qiyejieshao);
        tv3_frimname = findViewByID_My(R.id.tv3_frimname);
        tv3_frimaddr = findViewByID_My(R.id.tv3_frimaddr);
        tv3_date = findViewByID_My(R.id.tv3_date);
        tv3_save = findViewByID_My(R.id.tv3_save);
        tv3_upload = findViewByID_My(R.id.tv3_upload);
        ll3_up1load1 = findViewByID_My(R.id.ll3_up1load1);
        ll3_up1load2 = findViewByID_My(R.id.ll3_up1load2);
        ll3_up1load3 = findViewByID_My(R.id.ll3_up1load3);
        iv3_photo1 = findViewByID_My(R.id.iv3_photo1);
        iv3_photo2 = findViewByID_My(R.id.iv3_photo2);
        iv3_photo3 = findViewByID_My(R.id.iv3_photo3);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_type1:
                type = 1;
                changeUI();
                break;
            case R.id.tv1_next:
            case R.id.ll_type2:
                type = 2;
                changeUI();
                break;
            case R.id.tv2_next:
            case R.id.ll_type3:
                type = 3;
                changeUI();
                break;
        }
    }

    private void changeUI() {
        switch (type) {
            case 1:
                //第一步
                textView1.setTextColor(getResources().getColor(R.color.white));
                textView1.setBackgroundResource(R.drawable.yuanjiao_15_lanse);
                view1.setBackgroundResource(R.color.orange);
                textView2.setTextColor(getResources().getColor(R.color.grey));
                textView2.setBackgroundResource(R.color.transparent);
                view2.setBackgroundResource(R.color.grey);
                textView3.setTextColor(getResources().getColor(R.color.grey));
                textView3.setBackgroundResource(R.color.transparent);
                view3.setBackgroundResource(R.color.grey);

                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.GONE);
                linearLayout3.setVisibility(View.GONE);

                break;
            case 2:
                //第二步
                textView1.setTextColor(getResources().getColor(R.color.white));
                textView1.setBackgroundResource(R.drawable.yuanjiao_15_lanse);
                view1.setBackgroundResource(R.color.orange);
                textView2.setTextColor(getResources().getColor(R.color.white));
                textView2.setBackgroundResource(R.drawable.yuanjiao_15_lanse);
                view2.setBackgroundResource(R.color.orange);
                textView3.setTextColor(getResources().getColor(R.color.grey));
                textView3.setBackgroundResource(R.color.transparent);
                view3.setBackgroundResource(R.color.grey);

                linearLayout1.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.GONE);
                break;
            case 3:
                //第三步
                textView1.setTextColor(getResources().getColor(R.color.white));
                textView1.setBackgroundResource(R.drawable.yuanjiao_15_lanse);
                view1.setBackgroundResource(R.color.orange);
                textView2.setTextColor(getResources().getColor(R.color.white));
                textView2.setBackgroundResource(R.drawable.yuanjiao_15_lanse);
                view2.setBackgroundResource(R.color.orange);
                textView3.setTextColor(getResources().getColor(R.color.white));
                textView3.setBackgroundResource(R.drawable.yuanjiao_15_lanse);
                view3.setBackgroundResource(R.color.orange);

                linearLayout1.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.GONE);
                linearLayout3.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected void updateView() {

    }
}
