package com.chetu.engineer;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.hjq.toast.ToastUtils;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.List;

import androidx.multidex.MultiDex;

/**
 * Created by zyz on 2018/1/18.
 */

public class MyApplication extends Application {
    // 上下文菜单
    private static Context mContext;

    private static MyApplication myApplication;

    public static MyApplication getInstance() {
        return myApplication;
    }

    @Override
    public final void onCreate() {
        super.onCreate();

        mContext = this;
        myApplication = this;

        //腾讯bugly 异常上报初始化-建议在测试阶段建议设置成true，发布时设置为false。
        CrashReport.initCrashReport(getApplicationContext(), "5ee0dd5813", false);

        /*//非wifi情况下，主动下载x5内核
        QbSdk.setDownloadWithoutWifi(false);
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                MyLogger.i("5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。" + arg0);
            }

            @Override
            public void onCoreInitFinished() {

            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);*/

        //toast初始化
        ToastUtils.init(this);

        /*//推送初始化
        MobSDK.init(getApplicationContext());
        //控制用户隐私授权的结果
        MobSDK.submitPolicyGrantResult(true, null);
        //防止多进程注册多次  可以在MainActivity或者其他页面注册MobPushReceiver
        String processName = getProcessName(this);
        if (getPackageName().equals(processName)) {
            MobPush.addPushReceiver(new MobPushReceiver() {
                @Override
                public void onCustomMessageReceive(Context context, MobPushCustomMessage message) {
                    //接收自定义消息(透传)
                    MyLogger.i("接收自定义消息(透传)onCustomMessageReceive:" + message.toString());
                }

                @Override
                public void onNotifyMessageReceive(Context context, MobPushNotifyMessage message) {
                    //接收通知消息
                    MyLogger.i("接收通知消息MobPush onNotifyMessageReceive:" + message.toString());

                }

                @Override
                public void onNotifyMessageOpenedReceive(Context context, MobPushNotifyMessage message) {
                    //接收通知消息被点击事件
                    MyLogger.i("接收通知消息被点击事件MobPush onNotifyMessageOpenedReceive:" + message.toString());
                    Message msg = new Message();
//                msg.obj = "Click Message:" + message.toString();
//                msg.obj = "Click Message:" + message.getTitle();
//                msg.obj = "Click Message:" + message.getContent();
                    switch (message.getExtrasMap().get("type")) {
                        case "1":
                            //网页
                            msg.what = 1;
                            msg.obj = message.getExtrasMap().get("url");
                            break;
                        case "2":
                            //订单详情
                            msg.what = 2;
                            msg.obj = message.getExtrasMap().get("symbol");
                            break;
                    }
                    handler.sendMessage(msg);
                }

                @Override
                public void onTagsCallback(Context context, String[] tags, int operation, int errorCode) {
                    //接收tags的增改删查操作
                    MyLogger.i("接收tags的增改删查操作onTagsCallback:" + operation + "  " + errorCode);
                }

                @Override
                public void onAliasCallback(Context context, String alias, int operation, int errorCode) {
                    //接收alias的增改删查操作
                    MyLogger.i("接收alias的增改删查操作onAliasCallback:" + alias + "  " + operation + "  " + errorCode);
                }
            });

            handler = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    Bundle bundle = new Bundle();
                    switch (msg.what) {
                        case 1:
                            //网页
                            MyLogger.i(">>>>>>>>网页：" + msg.obj.toString());
                            Intent i = new Intent(mContext, WebContentActivity.class);
                            bundle.putString("url", msg.obj.toString());
                            i.putExtras(bundle);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            mContext.startActivity(i);
                            break;
                        case 2:
                            //订单详情
                            MyLogger.i(">>>>>>>>>symbol:" + msg.obj.toString());
//                        Intent i2 = new Intent(context, PredictionDetailActivity_MPChart.class);
                            Intent i2 = new Intent(mContext, PredictionDetailActivity.class);
                            bundle.putString("symbol", msg.obj.toString());
                            i2.putExtras(bundle);
                            i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            mContext.startActivity(i2);
                            break;
                        default:
                            break;
                    }

                    //当其它dialog未关闭的时候，再次显示dialog，会造成其他dialog无法dismiss的现象，建议使用toast
//			if(PushDeviceHelper.getInstance().isNotificationEnabled()) {
//				Toast.makeText(MainActivity.this, "回调信息\n" + (String) msg.OBJ, Toast.LENGTH_SHORT).show();
//			} else {//当做比通知栏后，toast是无法显示的
//				new DialogShell(MainActivity.this).autoDismissDialog(0, "回调信息\n" + (String)msg.OBJ, 2);
//			}

                    return false;
                }
            });
        }*/

        /**
         * 对于7.0以下，需要在Application创建的时候进行语言切换
         */
        /*String language = SpUtil.getInstance(this).getString(SpUtil.LANGUAGE);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            LanguageUtil.changeAppLanguage(mContext, language);
        }

        new ScreenAdaptation(this, 828, 1792).register();
//        new ScreenAdaptation(this,720,1280).register();*/

    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(this);//方法数超过64k
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }
    private String getProcessName(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo proInfo : runningApps) {
            if (proInfo.pid == android.os.Process.myPid()) {
                if (proInfo.processName != null) {
                    return proInfo.processName;
                }
            }
        }
        return null;
    }
}
