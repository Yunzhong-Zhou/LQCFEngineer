package com.chetu.engineer.net;

/**
 * URL路径处理类
 */
public class URLs {
    //测试地址
    /*public static String IMGHOST = "http://img.leqi.cool/";//图片地址
    public static String HOST = "http://app.leqi.cool";//接口地址
    public static String KFHOST = "http://chat.leqi.cool";//客服地址*/
    //正式地址
    public static String IMGHOST = "http://img.leqi.cool/";//图片地址
    public static String HOST = "http://app.leqi.cool";//接口地址
    public static String KFHOST = "http://chat.leqi.cool";//客服地址


    public static final String PROJECT_NAME = "";
    public static final String API = "";

    //headrs验证信息
    public static final String APIKEY = "773EDB6D2715FACF9C93354CAC5B1A3372872DC4D5AC085867C7490E9984D33E";
    public static final String HVERSION = "1.0";
    //第一次加载
    public static final String FristApp = HOST+"/api/v1/public/get_conf_info";
    //更新
    public static final String Upgrade = HOST + "/api/article/sys-upgrade";
    //引导页
    public static final String Guide = HOST + "/api/app-banner/index";
    //发送验证码
    public static final String Code = HOST + "/api/v1/public/get_auth_code";
    //登录
    public static final String Login = HOST + "/api/v1/user/landing";
    //一键登录
    public static final String Login1 = HOST + "/api/member/quick-login";
    //绑定手机号
    public static final String BindingPhone = HOST + "/api/v1/user/binding_phone";
    //图片上传
    public static final String UpFile = HOST + "/apiupload/picture";
    //热门搜索
    public static final String SearchHot = HOST+"/api/v1/public/hot_list_all";
    //搜索
    public static final String Search = HOST+"/api/v1/public/get_store_search_list";
    //收藏
    public static final String ShouChang = HOST+"/api/v1/user/user_collection_increase";
    //取消收藏
    public static final String QuXiaoShouChang = HOST+"/api/v1/user/user_collection_del";
    //加入购物车
    public static final String ADDShop = HOST+"/api/v1/public/user_cart_add";
    //评价列表
    public static final String PingJiaList = HOST+"/api/v1/public/get_goods_eval_list";
    //服务分类列表
    public static final String ServiceList_all = HOST+"/api/v1/public/service_list_all";
    /**
    *********************************首页*****************************************
     */
    public static final String Fragment1 = HOST + "/api/v1/techn/index_date";
    //商品列表
    public static final String ProductList = HOST+"/api/v1/public/get_goods_list";
    //商品详情
    public static final String ProductDetail= HOST+"/api/v1/public/get_goods_details";
    /**
     * ********************************工作台*****************************************
     */
    //待接车
    public static final String Fragment2_0 = HOST + "/api/v1/techn/techn_accept_order_list";
    //接车
    public static final String AddJieChe = HOST + "/api/v1/techn/techn_sedan_increase";
    //待分配
    public static final String Fragment2_1 = HOST + "/api/v1/techn/techn_sedan_list";
    //待施工=进行中=待复检=待完工=已提车
    public static final String Fragment2_2 = HOST + "/api/v1/techn/techn_sedan_waitfor_list";
    //添加项目
    public static final String AddProject = HOST + "/api/v1/techn/techn_testing_details_increase";
    //修改项目
    public static final String ChageProject = HOST + "/api/v1/techn/techn_testing_details_modify";
    //修改项目状态
    public static final String ChageProjectType = HOST + "/api/v1/techn/techn_sedan_testing_details_modify_state";
    //修改服务状态
    public static final String ChageServiceType = HOST + "/api/v1/techn/techn_sedan_order_service_modify_state";
    //修改订单状态
    public static final String ChageOrderType = HOST + "/api/v1/techn/techn_sedan_modify_state";
    //救援列表
    public static final String Fragment2_3 = HOST + "/api/v1/techn/techn_rescue_list";
    //救援状态
    public static final String JiuYuan_Type = HOST + "/api/v1/techn/techn_rescue_query";
    //立即救援
    public static final String JiuYuan_Now = HOST + "/api/v1/techn/techn_rescue_save";
    //统计数据
    public static final String Fragment2_TongJi = HOST + "/api/v1/techn/console_statistics";
    //签到-提交
    public static final String SignIn_Up = HOST + "/api/v1/techn/techn_signin";
    //签到-当天签到情况
    public static final String SignIn_Today = HOST + "/api/v1/techn/techn_signin_day";
    //签到-记录
    public static final String SignIn_List = HOST + "/api/v1/techn/techn_signin_list_page";
    //预计完成时间
    public static final String YuJiTime = HOST+"/api/v1/techn/save_estimate_time";
    //预计保养时间
    public static final String BaoYangTime = HOST+"/api/v1/techn/set_maintain_time";
    //修改服务内容
    public static final String ChangeService = HOST+"/api/v1/techn/techn_sedan_order_service_modify_str";
    /**
     * ********************************论坛*****************************************
     */
    public static final String Fragment3 = HOST + "/api/v1/techn/techn_forum_post_page_list";
    //发布活动
    public static final String AddHuoDong = HOST + "/api/v1/techn/techn_activity_increase";
    //发布招聘
    public static final String AddZhaoPin = HOST + "/api/v1/techn/techn_forum_recruit_increase";
    //发布技术交流
    public static final String AddJiShuJiaoLiu = HOST + "/api/v1/techn/techn_exchange_increase";
    //技术交流详情
    public static final String JiShuJiaoLiuDetail = HOST + "/api/v1/techn/techn_post_details";
    //技术交流详情-回复
    public static final String JiShuJiaoLiuDetail_HuiFu = HOST + "/api/v1/techn/techn_forum_reply_increase";
    //技术交流详情-加入
    public static final String JiShuJiaoLiuDetail_JiaRu = HOST + "/api/v1/techn/techn_circle_plus";
    //发布维修案例
    public static final String AddWeiXiuAnLi = HOST + "/api/v1/techn/techn_forum_case_increase";
    //发布机友求助
    public static final String AddJiYouQiuZhu = HOST + "/api/v1/techn/techn_help_increase";
    //机友求助-回复
    public static final String JiYouQiuZhu_HuiFu = HOST + "/api/v1/techn/techn_forum_reply_increase";
    //发布工具租赁
    public static final String AddGongJuZuLin = HOST + "/api/v1/techn/techn_tool_increase";
    //发布店铺出租
    public static final String AddDianPuChuZu = HOST + "/api/v1/techn/techn_shop_lease_increase";
    //发布库存配件
    public static final String AddKuCunPeiJian = HOST + "/api/v1/techn/techn_parts_increase";
    //喜欢
    public static final String Like = HOST + "/api/v1/techn/techn_activity_fabulous";
    /**
     * ********************************我的*****************************************
     */
    public static final String Fragment4 = HOST + "/api/v1/user/get_user_info";
    //修改个人信息
    public static final String ChageProfile = HOST + "/api/v1/user/user_setup";
    //修改手机号
    public static final String ChagePhone = HOST + "/api/v1/user/binding_phone";
    //修改支付密码
    public static final String ChagePayPassWord = HOST + "/api/v1/techn/techn_pay_pwd";
    //解绑门店
    public static final String UnbundleStore = HOST + "/api/v1/techn/techn_unlock_store";
    //绑定银行卡、支付宝
    public static final String BindingAccount = HOST + "/api/v1/techn/with_account_increase";
    //我的履历
    public static final String MyResume = HOST + "/api/v1/techn/user_resume_list";
    //添加履历
    public static final String AddResume = HOST + "/api/v1/techn/user_resume_add";
    //删除履历
    public static final String DeleteResume = HOST + "/api/v1/techn/user_resume_del";
    //修改履历
    public static final String ChageResume = HOST + "/api/v1/techn/user_resume_modify";
    //实名认证
    public static final String Verified = HOST + "/api/v1/techn/techn_auth";
    //意见反馈分类
    public static final String FeedBack_List = HOST + "/api/v1/public/get_feedback_classify_list";
    //意见反馈提交
    public static final String FeedBack = HOST + "/api/v1/public/increase_user_feedback";
    //版本说明
    public static final String Version = HOST + "/api/v1/h5/version";
    //余额
    public static final String Balance = HOST + "/api/v1/user/get_user_balance";
    //我的钱包
    public static final String MyWallet = HOST + "/api/v1/user/get_user_detailed_list";
    //我的银行卡
    public static final String MyBankCard = HOST + "/api/v1/techn/with_account_list";
    //解绑银行卡
    public static final String DeleteBankCard = HOST + "/api/v1/techn/with_account_del";
    //我的车辆
    public static final String MyCar = HOST + "/api/v1/user/user_sedan_list";
    //获取汽车品牌
    public static final String CarNameList = HOST + "/api/v1/public/user_sedan_brand_list";
    //添加车辆
    public static final String AddCar = HOST + "/api/v1/user/user_sedan_add";
    //足迹列表
    public static final String Footprint = HOST + "/api/v1/user/user_footprint_list_page";
    //门店详情
    public static final String StoreDetail = HOST+"/api/v1/public/get_store_details";
    //问答
    public static final String StoreDetail_WenDa = HOST+"/api/v1/public/user_store_ques_ans_list";
    //门店服务分类列表
    public static final String ServiceList_Store = HOST+"/api/v1/public/get_store_service_content";
    //回复问答
    public static final String HuiDa = HOST+"/api/v1/public/user_store_ques_ans_add";
    //删除足迹
    public static final String DeleteFootprint = HOST + "/api/v1/user/user_footprint_del";
    //收藏列表
    public static final String Collect = HOST + "/api/v1/user/user_collection_list_page";
    //删除收藏
    public static final String DeleteCollect = HOST + "/api/v1/user/user_collection_del";
    //我的报价
    public static final String MyBaoJia = HOST + "/api/v1/techn/techn_inquiry_list";
    //我的发布
    public static final String MyPublish = HOST + "/api/v1/techn/techn_wo_release_list";
    //删除报价
    public static final String DeleteBaoJia = HOST + "/api/v1/techn/techn_inquiry_list";
    //提交报价
    public static final String UpBaoJia = HOST + "/api/v1/user/inquiry_demand_project_offer";
    //订单详情
    public static final String OrderDetail = HOST + "/api/v1/techn/techn_sedan_details";
    //添加服务
    public static final String ADDService = HOST + "/api/v1/techn/add_order_server";
    //店铺商品列表-选择商品
    public static final String SelectGoods = HOST+"/api/v1/public/get_store_goods";
    //添加商品
    public static final String ADDGoods = HOST + "/api/v1/techn/add_order_server_goods";
    //添加商品-自助添加
    public static final String ADDGoods_zizhu = HOST + "/api/v1/techn/add_goods";
    //删除服务
    public static final String DeleteService = HOST + "/api/v1/techn/techn_sedan_order_service_del";
    //删除商品
    public static final String DeleteGoods = HOST + "/api/v1/techn/techn_sedan_goods_del";
    //删除项目
    public static final String DeleteProject = HOST + "/api/v1/techn/testing_details_del";
    //店铺所属技师
    public static final String JiShiList = HOST + "/api/v1/techn/techn_store_list";
    //分配技师
    public static final String FenPeiJiShi = HOST + "/api/v1/techn/techn_sedan_distribute";
    //我的评价
    public static final String MyComment = HOST + "/api/v1/techn/techn_eval_list";
    //投诉记录
    public static final String ComplaintList = HOST + "/api/v1/techn/techn_complaint_list";
    //客服列表
    public static final String ServiceCenter = HOST + "/api/v1/user/get_kf_list";
    //申述
    public static final String Appeal = HOST + "/api/v1/techn/techn_complaint_increase";
    //回复
    public static final String Reply = HOST + "/api/v1/techn/techn_eval_add_reply";
    //优惠券
    public static final String Coupon = HOST+"/api/v1/user/user_coupon_list";
    //使用优惠券
    public static final String USE_Coupon = HOST+"/api/v1/techn/use_coupon";
    /**拼接请求路径
     *@PARAM URI
     * @RETURN
     */
    public static String getURL(String uri) {
        return HOST + PROJECT_NAME + API + uri;
    }
}
