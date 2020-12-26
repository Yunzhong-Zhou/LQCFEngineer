package com.chetu.engineer.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chetu.engineer.R;
import com.chetu.engineer.base.BaseActivity;
import com.chetu.engineer.model.Fragment4Model;
import com.chetu.engineer.model.UpFileModel;
import com.chetu.engineer.net.URLs;
import com.chetu.engineer.okhttp.CallBackUtil;
import com.chetu.engineer.okhttp.OkhttpUtil;
import com.chetu.engineer.utils.MyChooseImages;
import com.chetu.engineer.utils.MyLogger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.zelory.compressor.Compressor;
import okhttp3.Call;
import okhttp3.Response;

import static com.chetu.engineer.net.URLs.IMGHOST;
import static com.chetu.engineer.utils.MyChooseImages.REQUEST_CODE_CAPTURE_CAMEIA;
import static com.chetu.engineer.utils.MyChooseImages.REQUEST_CODE_PICK_IMAGE;

/**
 * Created by zyz on 2020/6/2.
 * 实名认证
 */
public class VerifiedActivity extends BaseActivity {
    ArrayList<File> listFiles = new ArrayList<>();

    String imgType = "", real_name = "", identity_card = "",
            identity_positive = "", identity_back = "", user_img = "", user_img_c = "";

    EditText et_name, et_cardnum;
    ImageView iv_zheng, iv_fan, iv_benren, iv_shouchi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verified);
        mImmersionBar.reset()
                .statusBarColor(R.color.background)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
//        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
    }

    @Override
    protected void initView() {
        et_name = findViewByID_My(R.id.et_name);
        et_cardnum = findViewByID_My(R.id.et_cardnum);
        iv_zheng = findViewByID_My(R.id.iv_zheng);
        iv_fan = findViewByID_My(R.id.iv_fan);
        iv_benren = findViewByID_My(R.id.iv_benren);
        iv_shouchi = findViewByID_My(R.id.iv_shouchi);

    }

    @Override
    protected void initData() {
        showProgress(true, getString(R.string.app_loading));
        Map<String, String> params = new HashMap<>();
        params.put("u_token", localUserInfo.getToken());
        requestCenter(params);
    }

    private void requestCenter(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Fragment4, params, headerMap, new CallBackUtil<Fragment4Model>() {
            @Override
            public Fragment4Model onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                myToast(err);
            }

            @Override
            public void onResponse(Fragment4Model response) {
                hideProgress();
                et_name.setText(response.getUser_info().getAuth_info().getReal_name());
                et_cardnum.setText(response.getUser_info().getAuth_info().getIdentity_card());
                identity_positive = response.getUser_info().getAuth_info().getIdentity_positive();
                Glide.with(VerifiedActivity.this).load(IMGHOST + identity_positive)
                        .centerCrop()
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(iv_zheng);//加载图片
                identity_back = response.getUser_info().getAuth_info().getIdentity_back();
                Glide.with(VerifiedActivity.this).load(IMGHOST + identity_back)
                        .centerCrop()
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(iv_fan);//加载图片

                user_img = response.getUser_info().getAuth_info().getUser_img();
                Glide.with(VerifiedActivity.this).load(IMGHOST + user_img)
                        .centerCrop()
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(iv_benren);//加载图片
                user_img_c = response.getUser_info().getAuth_info().getUser_img_c();
                Glide.with(VerifiedActivity.this).load(IMGHOST + user_img_c)
                        .centerCrop()
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(iv_shouchi);//加载图片
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_zheng:
                //正面
                imgType = "identity_positive";
                MyChooseImages.showPhotoDialog(this);
                break;
            case R.id.iv_fan:
                //反面
                imgType = "identity_back";
                MyChooseImages.showPhotoDialog(this);
                break;
            case R.id.iv_benren:
                //本人
                imgType = "user_img";
                MyChooseImages.showPhotoDialog(this);
                break;
            case R.id.iv_shouchi:
                //手持
                imgType = "user_img_c";
                MyChooseImages.showPhotoDialog(this);
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle("实名认证");
        titleView.setBackground(R.color.background);
        titleView.showRightTextview("保存", R.color.blue, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (match()) {
                    showProgress(true, getString(R.string.app_loading1));
                    Map<String, String> params = new HashMap<>();
                    params.put("u_token", localUserInfo.getToken());
                    params.put("real_name", real_name);
                    params.put("identity_card", identity_card);
                    params.put("identity_positive", identity_positive);
                    params.put("identity_back", identity_back);
                    params.put("user_img", user_img);
                    params.put("user_img_c", user_img_c);
                    RequestUpData(params);
                }
            }
        });
    }


    private void RequestUpData(Map<String, String> params) {
        OkhttpUtil.okHttpPost(URLs.Verified, params, headerMap, new CallBackUtil<Object>() {
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
                myToast("提交成功");
                finish();
//                myToast("添加成功");
//                finish();
            }
        });
    }

    private boolean match() {
        real_name = et_name.getText().toString().trim();
        if (TextUtils.isEmpty(real_name)) {
            myToast("请输入姓名");
            return false;
        }
        identity_card = et_cardnum.getText().toString().trim();
        if (TextUtils.isEmpty(identity_card)) {
            myToast("请输入身份证号");
            return false;
        }

        if (TextUtils.isEmpty(identity_positive)) {
            myToast("请上传正面身份证照片");
            return false;
        }
        if (TextUtils.isEmpty(identity_back)) {
            myToast("请上传反面身份证照片");
            return false;
        }
        if (TextUtils.isEmpty(user_img)) {
            myToast("请上传本人照片");
            return false;
        }
        if (TextUtils.isEmpty(user_img_c)) {
            myToast("请上传手持身份证照片");
            return false;
        }
        return true;
    }

    private void RequestUpFile(Map<String, String> params, List<File> fileList, String fileKey) {
        OkhttpUtil.okHttpUploadListFile(URLs.UpFile, params, fileList, fileKey, "image", headerMap, new CallBackUtil<UpFileModel>() {
            @Override
            public UpFileModel onParseResponse(Call call, Response response) {
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e, String err) {
                hideProgress();
                if (!err.equals("")) {
                    showToast(err);
                }
            }

            @Override
            public void onResponse(UpFileModel response) {
                hideProgress();
//                myToast("提交成功，等待审核");
                for (String s : response.getList()) {
                    switch (imgType) {
                        case "identity_positive":
                            identity_positive = s;
                            Glide.with(VerifiedActivity.this).load(URLs.IMGHOST + s)
                                    .centerCrop()
                                    .into(iv_zheng);//加载图片
                            break;
                        case "identity_back":
                            identity_back = s;
                            Glide.with(VerifiedActivity.this).load(URLs.IMGHOST + s)
                                    .centerCrop()
                                    .into(iv_fan);//加载图片
                            break;
                        case "user_img":
                            user_img = s;
                            Glide.with(VerifiedActivity.this).load(URLs.IMGHOST + s)
                                    .centerCrop()
                                    .into(iv_benren);//加载图片
                            break;
                        case "user_img_c":
                            user_img_c = s;
                            Glide.with(VerifiedActivity.this).load(URLs.IMGHOST + s)
                                    .centerCrop()
                                    .into(iv_shouchi);//加载图片
                            break;
                    }
                }
            }
        });
    }

    /**
     * *****************************************选择图片********************************************
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri uri = null;
            String imagePath = null;
            switch (requestCode) {
                case REQUEST_CODE_CAPTURE_CAMEIA:
                    //相机
                    uri = Uri.parse("");
                    uri = Uri.fromFile(new File(MyChooseImages.imagepath));
                    imagePath = uri.getPath();
                    break;
                case REQUEST_CODE_PICK_IMAGE:
                    //相册
                    uri = data.getData();
                    //处理得到的url
                    ContentResolver cr = this.getContentResolver();
                    Cursor cursor = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        cursor = cr.query(uri, null, null, null, null, null);
                        if (cursor != null) {
                            cursor.moveToFirst();
                            try {
                                imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                            } catch (Exception e) {
                                e.printStackTrace();
                                myToast(getString(R.string.app_error));
                            } finally {
                                if (cursor != null)
                                    cursor.close();
                            }
                        }

                    } else {
                        imagePath = uri.getPath();
                    }
                    break;
            }
            if (uri != null) {
                MyLogger.i(">>>>>>>>>>获取到的图片路径1：" + imagePath);
                //图片过大解决方法
               /* BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);*/

                File file1 = new File(imagePath);
                listFiles = new ArrayList<>();
                File newFile = null;
                try {
                    newFile = new Compressor(this).compressToFile(file1);
                    listFiles.add(newFile);
//                    MyLogger.i(">>>>>选择图片结果>>>>>>>>>" + listFileNames.toString() + ">>>>>>" + listFiles.toString());
                    showProgress(true, "正在上传图片，请稍候...");
//                    Map<String, File> fileMap = new HashMap<>();
//                    fileMap.put("picture", newFile);
                    Map<String, String> params = new HashMap<>();
                    params.put("sn", "773EDB6D2715FACF9C93354CAC5B1A3372872DC4D5AC085867C7490E9984D33E");
//                    RequestUpFile(fileMap, params);
                    RequestUpFile(params, listFiles, "picture");//key不需要变

                } catch (IOException e) {
                    e.printStackTrace();
                    myToast(getString(R.string.app_imgerr));
                }
            }
        }

    }
}
