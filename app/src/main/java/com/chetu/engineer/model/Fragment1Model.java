package com.chetu.engineer.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020-05-23.
 */
public class Fragment1Model implements Serializable {
    private List<BannerListBean> banner_list;
    private List<InquiryListBean> inquiry_list;
    private List<GoodsListBean> goods_list;
//    private List<CircleListBean> circle_list;
    private List<RecruitListBean> recruit_list;
    private List<ToolLeasingBean> tool_leasing;
//    private List<ForumListBean> forum_list;


    public List<BannerListBean> getBanner_list() {
        return banner_list;
    }

    public void setBanner_list(List<BannerListBean> banner_list) {
        this.banner_list = banner_list;
    }

    public List<InquiryListBean> getInquiry_list() {
        return inquiry_list;
    }

    public void setInquiry_list(List<InquiryListBean> inquiry_list) {
        this.inquiry_list = inquiry_list;
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

//    public List<CircleListBean> getCircle_list() {
//        return circle_list;
//    }

//    public void setCircle_list(List<CircleListBean> circle_list) {
//        this.circle_list = circle_list;
//    }

    public List<RecruitListBean> getRecruit_list() {
        return recruit_list;
    }

    public void setRecruit_list(List<RecruitListBean> recruit_list) {
        this.recruit_list = recruit_list;
    }

    public List<ToolLeasingBean> getTool_leasing() {
        return tool_leasing;
    }

    public void setTool_leasing(List<ToolLeasingBean> tool_leasing) {
        this.tool_leasing = tool_leasing;
    }

    /*public List<ForumListBean> getForum_list() {
        return forum_list;
    }

    public void setForum_list(List<ForumListBean> forum_list) {
        this.forum_list = forum_list;
    }*/

    public static class BannerListBean implements Serializable{
        /**
         * id : 173
         * yPhotoId : 692341585785913346
         * yServiceId : 692341585785913344
         * category : 0
         * msg : 技师图片
         * imgurl : /upload/index/index6.png
         * createDate : 2019-09-27 10:31:19
         */

        private String id;
        private String yPhotoId;
        private String yServiceId;
        private int category;
        private String msg;
        private String imgurl;
        private String createDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYPhotoId() {
            return yPhotoId;
        }

        public void setYPhotoId(String yPhotoId) {
            this.yPhotoId = yPhotoId;
        }

        public String getYServiceId() {
            return yServiceId;
        }

        public void setYServiceId(String yServiceId) {
            this.yServiceId = yServiceId;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }

    public static class InquiryListBean implements Serializable{
        /**
         * id : 1154
         * yInquiryDemandId : 724950991308324864
         * userId : 714547022807433216
         * yUserSedanId : 724585460164198400
         * serviceName : 小保养套餐/大保养套餐/空调滤清器/节气门清洗/发动机清洗
         * serviceIdStr : 692341585785913349,692341585785913350,692341585785913351,692341585785913352,692341585785913353
         * storeIdStr : 692341585785913348,692341585785913344,692341585785913345
         * userSedanJson : {"brandJson":"{\"brandName\":\"阿斯顿・马丁\",\"groupName\":\"阿斯顿・马丁\",\"id\":91506,\"parentId\":716704210728517632,\"sLogo\":\"/upload/logo/716723976784576512.jpg\",\"sName\":\"2019款 AMR 荣耀限量版\",\"seriesName\":\"阿斯顿・马丁DB11\",\"ySedanBrandId\":716705042324783104}","createDate":"2020-06-22 11:23:55","id":1088,"isDel":0,"isF":1,"jReportPoliceJson":"{\"createDate\":\"2020-04-15 08:44:18\",\"id\":1047,\"telephone\":\"400-2123-2211\",\"vName\":\"人寿保险公司\",\"vNameNature\":\"普通保险\",\"yReportPoliceId\":699902914180677632}","reportPoliceJson":"{\"createDate\":\"2020-04-15 08:44:18\",\"id\":1047,\"telephone\":\"400-2123-2211\",\"vName\":\"人寿保险公司\",\"vNameNature\":\"普通保险\",\"yReportPoliceId\":699902914180677632}","sCy":1,"sLogo":"/upload/logo/716723976784576512.jpg","sName":"2019款 AMR 荣耀限量版","sNumber":"粤A12345","userId":714547022807433216,"userPhone":"18306043086","yUserSedanId":724585460164198400}
         * isOk : 1
         * createDate : 2020-06-23 11:36:25
         * user_info : {"id":"0","userId":"714547022807433216","userBalance":0,"userName":"阿斯顿马丁","userIntegral":"0","yStoreId":"0","userHash":"36635C17B0E19DD1E14A92D8895B47CD","headPortrait":"/upload/2020-06-13/20200613085845_638405.jpeg","userPhone":"18306043086"}
         * user_sedan_info : {"id":"1088","yUserSedanId":"724585460164198400","userId":"714547022807433216","sName":"2019款 AMR 荣耀限量版","sLogo":"/upload/logo/716723976784576512.jpg","sCy":1,"isF":1,"isDel":0,"reportPoliceJson":"{\"createDate\":\"2020-04-15 08:44:18\",\"id\":1047,\"telephone\":\"400-2123-2211\",\"vName\":\"人寿保险公司\",\"vNameNature\":\"普通保险\",\"yReportPoliceId\":699902914180677632}","jReportPoliceJson":"{\"createDate\":\"2020-04-15 08:44:18\",\"id\":1047,\"telephone\":\"400-2123-2211\",\"vName\":\"人寿保险公司\",\"vNameNature\":\"普通保险\",\"yReportPoliceId\":699902914180677632}","brandJson":"{\"brandName\":\"阿斯顿・马丁\",\"groupName\":\"阿斯顿・马丁\",\"id\":91506,\"parentId\":716704210728517632,\"sLogo\":\"/upload/logo/716723976784576512.jpg\",\"sName\":\"2019款 AMR 荣耀限量版\",\"seriesName\":\"阿斯顿・马丁DB11\",\"ySedanBrandId\":716705042324783104}","userPhone":"18306043086","sNumber":"粤A12345","createDate":"2020-06-22 11:23:55"}
         * project_list : [{"id":"1528","yInquiryDemandProjectId":"724950991497068544","yInquiryDemandId":"724950991308324864","vTitle":"小保养套餐","createDate":"2020-06-23 11:36:25","isService":1,"offer_list":[{"id":"1065","yInquiryDemandProjectOfferId":"724953777886461952","yInquiryDemandProjectId":"724950991497068544","yInquiryDemandId":"724950991308324864","userId":"719539276219416576","yStoreId":"692341585785913345","isService":0,"storeJson":"{\"address\":\"凤凰山附近\",\"charactStr\":\"/upload/store/222.png||/upload/store/222.png\",\"id\":1018,\"introduce\":\"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。\",\"isIndex\":1,\"keywsr\":\"技术 修车 实\",\"latitude\":\"39.916485\",\"longitude\":\"116.49798\",\"phone\":\"  400-2333-1123\",\"pictureStr\":\"/upload/store/222.png||/upload/store/222.png\",\"review\":\"4.5\",\"slogan\":\"维修商店测试 美容首单50\",\"userId\":719539276219416576,\"vLevel\":\"A级\",\"vName\":\"维修商店测试\",\"yStoreId\":692341585785913345}","userJson":"{\"headPortrait\":\"/upload/2020-06-18/20200618164348_110881.jpg\",\"id\":0,\"userAccount\":\"THANU4\",\"userBalance\":0.0,\"userHash\":\"843B1049032A7E7C9F7D2E2CBE6D37A6\",\"userId\":0,\"userIntegral\":0,\"userName\":\"阿斯顿马丁\",\"yStoreId\":0}","vPrice":100,"createDate":"2020-06-23 11:47:29","store_info":{"id":"1018","yStoreId":"692341585785913345","userId":"719539276219416576","vName":"维修商店测试","review":"4.5","keywsr":"技术 修车 实","address":"凤凰山附近","longitude":"116.49798","latitude":"39.916485","phone":"  400-2333-1123","vLevel":"A级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"/upload/store/222.png||/upload/store/222.png","pictureStr":"/upload/store/222.png||/upload/store/222.png","slogan":"维修商店测试 美容首单50","isIndex":1}}]},{"id":"1527","yInquiryDemandProjectId":"724950991476097024","yInquiryDemandId":"724950991308324864","vTitle":"大保养套餐","createDate":"2020-06-23 11:36:25","isService":1,"offer_list":[{"id":"1066","yInquiryDemandProjectOfferId":"724953933386088448","yInquiryDemandProjectId":"724950991476097024","yInquiryDemandId":"724950991308324864","userId":"719539276219416576","yStoreId":"692341585785913345","isService":0,"storeJson":"{\"address\":\"凤凰山附近\",\"charactStr\":\"/upload/store/222.png||/upload/store/222.png\",\"id\":1018,\"introduce\":\"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。\",\"isIndex\":1,\"keywsr\":\"技术 修车 实\",\"latitude\":\"39.916485\",\"longitude\":\"116.49798\",\"phone\":\"  400-2333-1123\",\"pictureStr\":\"/upload/store/222.png||/upload/store/222.png\",\"review\":\"4.5\",\"slogan\":\"维修商店测试 美容首单50\",\"userId\":719539276219416576,\"vLevel\":\"A级\",\"vName\":\"维修商店测试\",\"yStoreId\":692341585785913345}","userJson":"{\"headPortrait\":\"/upload/2020-06-18/20200618164348_110881.jpg\",\"id\":0,\"userAccount\":\"THANU4\",\"userBalance\":0.0,\"userHash\":\"843B1049032A7E7C9F7D2E2CBE6D37A6\",\"userId\":0,\"userIntegral\":0,\"userName\":\"阿斯顿马丁\",\"yStoreId\":0}","vPrice":200,"createDate":"2020-06-23 11:48:06","store_info":{"id":"1018","yStoreId":"692341585785913345","userId":"719539276219416576","vName":"维修商店测试","review":"4.5","keywsr":"技术 修车 实","address":"凤凰山附近","longitude":"116.49798","latitude":"39.916485","phone":"  400-2333-1123","vLevel":"A级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"/upload/store/222.png||/upload/store/222.png","pictureStr":"/upload/store/222.png||/upload/store/222.png","slogan":"维修商店测试 美容首单50","isIndex":1}}]},{"id":"1526","yInquiryDemandProjectId":"724950991463514112","yInquiryDemandId":"724950991308324864","vTitle":"空调滤清器","createDate":"2020-06-23 11:36:25","isService":1,"offer_list":[{"id":"1067","yInquiryDemandProjectOfferId":"724953948108095488","yInquiryDemandProjectId":"724950991463514112","yInquiryDemandId":"724950991308324864","userId":"719539276219416576","yStoreId":"692341585785913345","isService":0,"storeJson":"{\"address\":\"凤凰山附近\",\"charactStr\":\"/upload/store/222.png||/upload/store/222.png\",\"id\":1018,\"introduce\":\"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。\",\"isIndex\":1,\"keywsr\":\"技术 修车 实\",\"latitude\":\"39.916485\",\"longitude\":\"116.49798\",\"phone\":\"  400-2333-1123\",\"pictureStr\":\"/upload/store/222.png||/upload/store/222.png\",\"review\":\"4.5\",\"slogan\":\"维修商店测试 美容首单50\",\"userId\":719539276219416576,\"vLevel\":\"A级\",\"vName\":\"维修商店测试\",\"yStoreId\":692341585785913345}","userJson":"{\"headPortrait\":\"/upload/2020-06-18/20200618164348_110881.jpg\",\"id\":0,\"userAccount\":\"THANU4\",\"userBalance\":0.0,\"userHash\":\"843B1049032A7E7C9F7D2E2CBE6D37A6\",\"userId\":0,\"userIntegral\":0,\"userName\":\"阿斯顿马丁\",\"yStoreId\":0}","vPrice":300,"createDate":"2020-06-23 11:48:10","store_info":{"id":"1018","yStoreId":"692341585785913345","userId":"719539276219416576","vName":"维修商店测试","review":"4.5","keywsr":"技术 修车 实","address":"凤凰山附近","longitude":"116.49798","latitude":"39.916485","phone":"  400-2333-1123","vLevel":"A级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"/upload/store/222.png||/upload/store/222.png","pictureStr":"/upload/store/222.png||/upload/store/222.png","slogan":"维修商店测试 美容首单50","isIndex":1}}]},{"id":"1525","yInquiryDemandProjectId":"724950991446736896","yInquiryDemandId":"724950991308324864","vTitle":"节气门清洗","createDate":"2020-06-23 11:36:25","isService":1,"offer_list":[{"id":"1068","yInquiryDemandProjectOfferId":"724953964247777280","yInquiryDemandProjectId":"724950991446736896","yInquiryDemandId":"724950991308324864","userId":"719539276219416576","yStoreId":"692341585785913345","isService":0,"storeJson":"{\"address\":\"凤凰山附近\",\"charactStr\":\"/upload/store/222.png||/upload/store/222.png\",\"id\":1018,\"introduce\":\"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。\",\"isIndex\":1,\"keywsr\":\"技术 修车 实\",\"latitude\":\"39.916485\",\"longitude\":\"116.49798\",\"phone\":\"  400-2333-1123\",\"pictureStr\":\"/upload/store/222.png||/upload/store/222.png\",\"review\":\"4.5\",\"slogan\":\"维修商店测试 美容首单50\",\"userId\":719539276219416576,\"vLevel\":\"A级\",\"vName\":\"维修商店测试\",\"yStoreId\":692341585785913345}","userJson":"{\"headPortrait\":\"/upload/2020-06-18/20200618164348_110881.jpg\",\"id\":0,\"userAccount\":\"THANU4\",\"userBalance\":0.0,\"userHash\":\"843B1049032A7E7C9F7D2E2CBE6D37A6\",\"userId\":0,\"userIntegral\":0,\"userName\":\"阿斯顿马丁\",\"yStoreId\":0}","vPrice":400,"createDate":"2020-06-23 11:48:14","store_info":{"id":"1018","yStoreId":"692341585785913345","userId":"719539276219416576","vName":"维修商店测试","review":"4.5","keywsr":"技术 修车 实","address":"凤凰山附近","longitude":"116.49798","latitude":"39.916485","phone":"  400-2333-1123","vLevel":"A级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"/upload/store/222.png||/upload/store/222.png","pictureStr":"/upload/store/222.png||/upload/store/222.png","slogan":"维修商店测试 美容首单50","isIndex":1}}]},{"id":"1524","yInquiryDemandProjectId":"724950991434153984","yInquiryDemandId":"724950991308324864","vTitle":"发动机清洗","createDate":"2020-06-23 11:36:25","isService":1,"offer_list":[{"id":"1069","yInquiryDemandProjectOfferId":"724953980307767296","yInquiryDemandProjectId":"724950991434153984","yInquiryDemandId":"724950991308324864","userId":"719539276219416576","yStoreId":"692341585785913345","isService":0,"storeJson":"{\"address\":\"凤凰山附近\",\"charactStr\":\"/upload/store/222.png||/upload/store/222.png\",\"id\":1018,\"introduce\":\"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。\",\"isIndex\":1,\"keywsr\":\"技术 修车 实\",\"latitude\":\"39.916485\",\"longitude\":\"116.49798\",\"phone\":\"  400-2333-1123\",\"pictureStr\":\"/upload/store/222.png||/upload/store/222.png\",\"review\":\"4.5\",\"slogan\":\"维修商店测试 美容首单50\",\"userId\":719539276219416576,\"vLevel\":\"A级\",\"vName\":\"维修商店测试\",\"yStoreId\":692341585785913345}","userJson":"{\"headPortrait\":\"/upload/2020-06-18/20200618164348_110881.jpg\",\"id\":0,\"userAccount\":\"THANU4\",\"userBalance\":0.0,\"userHash\":\"843B1049032A7E7C9F7D2E2CBE6D37A6\",\"userId\":0,\"userIntegral\":0,\"userName\":\"阿斯顿马丁\",\"yStoreId\":0}","vPrice":500,"createDate":"2020-06-23 11:48:17","store_info":{"id":"1018","yStoreId":"692341585785913345","userId":"719539276219416576","vName":"维修商店测试","review":"4.5","keywsr":"技术 修车 实","address":"凤凰山附近","longitude":"116.49798","latitude":"39.916485","phone":"  400-2333-1123","vLevel":"A级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"/upload/store/222.png||/upload/store/222.png","pictureStr":"/upload/store/222.png||/upload/store/222.png","slogan":"维修商店测试 美容首单50","isIndex":1}}]},{"id":"1523","yInquiryDemandProjectId":"724950991329296384","yInquiryDemandId":"724950991308324864","vTitle":"啊水电费我的","imgsrt":"/upload/2020-06-23/20200623113615_838459.jpg||/upload/2020-06-23/20200623113615_146047.jpg||/upload/2020-06-23/20200623113615_676577.jpeg||/upload/2020-06-23/20200623113615_131834.jpeg","imgArr":["/upload/2020-06-23/20200623113615_838459.jpg","/upload/2020-06-23/20200623113615_146047.jpg","/upload/2020-06-23/20200623113615_676577.jpeg","/upload/2020-06-23/20200623113615_131834.jpeg"],"createDate":"2020-06-23 11:36:25","isService":0,"offer_list":[{"id":"1070","yInquiryDemandProjectOfferId":"724953998523629568","yInquiryDemandProjectId":"724950991329296384","yInquiryDemandId":"724950991308324864","userId":"719539276219416576","yStoreId":"692341585785913345","isService":0,"storeJson":"{\"address\":\"凤凰山附近\",\"charactStr\":\"/upload/store/222.png||/upload/store/222.png\",\"id\":1018,\"introduce\":\"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。\",\"isIndex\":1,\"keywsr\":\"技术 修车 实\",\"latitude\":\"39.916485\",\"longitude\":\"116.49798\",\"phone\":\"  400-2333-1123\",\"pictureStr\":\"/upload/store/222.png||/upload/store/222.png\",\"review\":\"4.5\",\"slogan\":\"维修商店测试 美容首单50\",\"userId\":719539276219416576,\"vLevel\":\"A级\",\"vName\":\"维修商店测试\",\"yStoreId\":692341585785913345}","userJson":"{\"headPortrait\":\"/upload/2020-06-18/20200618164348_110881.jpg\",\"id\":0,\"userAccount\":\"THANU4\",\"userBalance\":0.0,\"userHash\":\"843B1049032A7E7C9F7D2E2CBE6D37A6\",\"userId\":0,\"userIntegral\":0,\"userName\":\"阿斯顿马丁\",\"yStoreId\":0}","vPrice":600,"createDate":"2020-06-23 11:48:22","store_info":{"id":"1018","yStoreId":"692341585785913345","userId":"719539276219416576","vName":"维修商店测试","review":"4.5","keywsr":"技术 修车 实","address":"凤凰山附近","longitude":"116.49798","latitude":"39.916485","phone":"  400-2333-1123","vLevel":"A级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"/upload/store/222.png||/upload/store/222.png","pictureStr":"/upload/store/222.png||/upload/store/222.png","slogan":"维修商店测试 美容首单50","isIndex":1}}]}]
         * isOffer : 1
         */

        private String id;
        private String yInquiryDemandId;
        private String userId;
        private String yUserSedanId;
        private String serviceName;
        private String serviceIdStr;
        private String storeIdStr;
        private String userSedanJson;
        private int isOk;
        private String vMsg;

        public String getvMsg() {
            return vMsg;
        }

        public void setvMsg(String vMsg) {
            this.vMsg = vMsg;
        }

        private String createDate;
        private UserInfoBean user_info;
        private UserSedanInfoBean user_sedan_info;
        private int isOffer;
        private List<ProjectListBean> project_list;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYInquiryDemandId() {
            return yInquiryDemandId;
        }

        public void setYInquiryDemandId(String yInquiryDemandId) {
            this.yInquiryDemandId = yInquiryDemandId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getYUserSedanId() {
            return yUserSedanId;
        }

        public void setYUserSedanId(String yUserSedanId) {
            this.yUserSedanId = yUserSedanId;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getServiceIdStr() {
            return serviceIdStr;
        }

        public void setServiceIdStr(String serviceIdStr) {
            this.serviceIdStr = serviceIdStr;
        }

        public String getStoreIdStr() {
            return storeIdStr;
        }

        public void setStoreIdStr(String storeIdStr) {
            this.storeIdStr = storeIdStr;
        }

        public String getUserSedanJson() {
            return userSedanJson;
        }

        public void setUserSedanJson(String userSedanJson) {
            this.userSedanJson = userSedanJson;
        }

        public int getIsOk() {
            return isOk;
        }

        public void setIsOk(int isOk) {
            this.isOk = isOk;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public UserSedanInfoBean getUser_sedan_info() {
            return user_sedan_info;
        }

        public void setUser_sedan_info(UserSedanInfoBean user_sedan_info) {
            this.user_sedan_info = user_sedan_info;
        }

        public int getIsOffer() {
            return isOffer;
        }

        public void setIsOffer(int isOffer) {
            this.isOffer = isOffer;
        }

        public List<ProjectListBean> getProject_list() {
            return project_list;
        }

        public void setProject_list(List<ProjectListBean> project_list) {
            this.project_list = project_list;
        }

        public static class UserInfoBean implements Serializable{
            /**
             * id : 0
             * userId : 714547022807433216
             * userBalance : 0.0
             * userName : 阿斯顿马丁
             * userIntegral : 0
             * yStoreId : 0
             * userHash : 36635C17B0E19DD1E14A92D8895B47CD
             * headPortrait : /upload/2020-06-13/20200613085845_638405.jpeg
             * userPhone : 18306043086
             */

            private String id;
            private String userId;
            private double userBalance;
            private String userName;
            private String userIntegral;
            private String yStoreId;
            private String userHash;
            private String headPortrait;
            private String userPhone;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public double getUserBalance() {
                return userBalance;
            }

            public void setUserBalance(double userBalance) {
                this.userBalance = userBalance;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserIntegral() {
                return userIntegral;
            }

            public void setUserIntegral(String userIntegral) {
                this.userIntegral = userIntegral;
            }

            public String getYStoreId() {
                return yStoreId;
            }

            public void setYStoreId(String yStoreId) {
                this.yStoreId = yStoreId;
            }

            public String getUserHash() {
                return userHash;
            }

            public void setUserHash(String userHash) {
                this.userHash = userHash;
            }

            public String getHeadPortrait() {
                return headPortrait;
            }

            public void setHeadPortrait(String headPortrait) {
                this.headPortrait = headPortrait;
            }

            public String getUserPhone() {
                return userPhone;
            }

            public void setUserPhone(String userPhone) {
                this.userPhone = userPhone;
            }
        }

        public static class UserSedanInfoBean implements Serializable{
            /**
             * id : 1088
             * yUserSedanId : 724585460164198400
             * userId : 714547022807433216
             * sName : 2019款 AMR 荣耀限量版
             * sLogo : /upload/logo/716723976784576512.jpg
             * sCy : 1
             * isF : 1
             * isDel : 0
             * reportPoliceJson : {"createDate":"2020-04-15 08:44:18","id":1047,"telephone":"400-2123-2211","vName":"人寿保险公司","vNameNature":"普通保险","yReportPoliceId":699902914180677632}
             * jReportPoliceJson : {"createDate":"2020-04-15 08:44:18","id":1047,"telephone":"400-2123-2211","vName":"人寿保险公司","vNameNature":"普通保险","yReportPoliceId":699902914180677632}
             * brandJson : {"brandName":"阿斯顿・马丁","groupName":"阿斯顿・马丁","id":91506,"parentId":716704210728517632,"sLogo":"/upload/logo/716723976784576512.jpg","sName":"2019款 AMR 荣耀限量版","seriesName":"阿斯顿・马丁DB11","ySedanBrandId":716705042324783104}
             * userPhone : 18306043086
             * sNumber : 粤A12345
             * createDate : 2020-06-22 11:23:55
             */

            private String id;
            private String yUserSedanId;
            private String userId;
            private String sName;
            private String sLogo;
            private int sCy;
            private int isF;
            private int isDel;
            private String reportPoliceJson;
            private String jReportPoliceJson;
            private String brandJson;
            private String userPhone;
            private String sNumber;
            private String createDate;
            private BrandInfoBean brandInfo;

            public BrandInfoBean getBrandInfo() {
                return brandInfo;
            }

            public void setBrandInfo(BrandInfoBean brandInfo) {
                this.brandInfo = brandInfo;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getYUserSedanId() {
                return yUserSedanId;
            }

            public void setYUserSedanId(String yUserSedanId) {
                this.yUserSedanId = yUserSedanId;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getSName() {
                return sName;
            }

            public void setSName(String sName) {
                this.sName = sName;
            }

            public String getSLogo() {
                return sLogo;
            }

            public void setSLogo(String sLogo) {
                this.sLogo = sLogo;
            }

            public int getSCy() {
                return sCy;
            }

            public void setSCy(int sCy) {
                this.sCy = sCy;
            }

            public int getIsF() {
                return isF;
            }

            public void setIsF(int isF) {
                this.isF = isF;
            }

            public int getIsDel() {
                return isDel;
            }

            public void setIsDel(int isDel) {
                this.isDel = isDel;
            }

            public String getReportPoliceJson() {
                return reportPoliceJson;
            }

            public void setReportPoliceJson(String reportPoliceJson) {
                this.reportPoliceJson = reportPoliceJson;
            }

            public String getJReportPoliceJson() {
                return jReportPoliceJson;
            }

            public void setJReportPoliceJson(String jReportPoliceJson) {
                this.jReportPoliceJson = jReportPoliceJson;
            }

            public String getBrandJson() {
                return brandJson;
            }

            public void setBrandJson(String brandJson) {
                this.brandJson = brandJson;
            }

            public String getUserPhone() {
                return userPhone;
            }

            public void setUserPhone(String userPhone) {
                this.userPhone = userPhone;
            }

            public String getSNumber() {
                return sNumber;
            }

            public void setSNumber(String sNumber) {
                this.sNumber = sNumber;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }
            public static class BrandInfoBean implements Serializable{
                /**
                 * id : 91506
                 * ySedanBrandId : 716705042324783104
                 * parentId : 716704210728517632
                 * sName : 2019款 AMR 荣耀限量版
                 * sLogo : /upload/logo/716723976784576512.jpg
                 * seriesName : 阿斯顿・马丁DB11
                 * groupName : 阿斯顿・马丁
                 * brandName : 阿斯顿・马丁
                 */

                private String id;
                private String ySedanBrandId;
                private String parentId;
                private String sName;
                private String sLogo;
                private String seriesName;
                private String groupName;
                private String brandName;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getYSedanBrandId() {
                    return ySedanBrandId;
                }

                public void setYSedanBrandId(String ySedanBrandId) {
                    this.ySedanBrandId = ySedanBrandId;
                }

                public String getParentId() {
                    return parentId;
                }

                public void setParentId(String parentId) {
                    this.parentId = parentId;
                }

                public String getSName() {
                    return sName;
                }

                public void setSName(String sName) {
                    this.sName = sName;
                }

                public String getSLogo() {
                    return sLogo;
                }

                public void setSLogo(String sLogo) {
                    this.sLogo = sLogo;
                }

                public String getSeriesName() {
                    return seriesName;
                }

                public void setSeriesName(String seriesName) {
                    this.seriesName = seriesName;
                }

                public String getGroupName() {
                    return groupName;
                }

                public void setGroupName(String groupName) {
                    this.groupName = groupName;
                }

                public String getBrandName() {
                    return brandName;
                }

                public void setBrandName(String brandName) {
                    this.brandName = brandName;
                }
            }
        }

        public static class ProjectListBean implements Serializable{
            /**
             * id : 1528
             * yInquiryDemandProjectId : 724950991497068544
             * yInquiryDemandId : 724950991308324864
             * vTitle : 小保养套餐
             * createDate : 2020-06-23 11:36:25
             * isService : 1
             * offer_list : [{"id":"1065","yInquiryDemandProjectOfferId":"724953777886461952","yInquiryDemandProjectId":"724950991497068544","yInquiryDemandId":"724950991308324864","userId":"719539276219416576","yStoreId":"692341585785913345","isService":0,"storeJson":"{\"address\":\"凤凰山附近\",\"charactStr\":\"/upload/store/222.png||/upload/store/222.png\",\"id\":1018,\"introduce\":\"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。\",\"isIndex\":1,\"keywsr\":\"技术 修车 实\",\"latitude\":\"39.916485\",\"longitude\":\"116.49798\",\"phone\":\"  400-2333-1123\",\"pictureStr\":\"/upload/store/222.png||/upload/store/222.png\",\"review\":\"4.5\",\"slogan\":\"维修商店测试 美容首单50\",\"userId\":719539276219416576,\"vLevel\":\"A级\",\"vName\":\"维修商店测试\",\"yStoreId\":692341585785913345}","userJson":"{\"headPortrait\":\"/upload/2020-06-18/20200618164348_110881.jpg\",\"id\":0,\"userAccount\":\"THANU4\",\"userBalance\":0.0,\"userHash\":\"843B1049032A7E7C9F7D2E2CBE6D37A6\",\"userId\":0,\"userIntegral\":0,\"userName\":\"阿斯顿马丁\",\"yStoreId\":0}","vPrice":100,"createDate":"2020-06-23 11:47:29","store_info":{"id":"1018","yStoreId":"692341585785913345","userId":"719539276219416576","vName":"维修商店测试","review":"4.5","keywsr":"技术 修车 实","address":"凤凰山附近","longitude":"116.49798","latitude":"39.916485","phone":"  400-2333-1123","vLevel":"A级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"/upload/store/222.png||/upload/store/222.png","pictureStr":"/upload/store/222.png||/upload/store/222.png","slogan":"维修商店测试 美容首单50","isIndex":1}}]
             * imgsrt : /upload/2020-06-23/20200623113615_838459.jpg||/upload/2020-06-23/20200623113615_146047.jpg||/upload/2020-06-23/20200623113615_676577.jpeg||/upload/2020-06-23/20200623113615_131834.jpeg
             * imgArr : ["/upload/2020-06-23/20200623113615_838459.jpg","/upload/2020-06-23/20200623113615_146047.jpg","/upload/2020-06-23/20200623113615_676577.jpeg","/upload/2020-06-23/20200623113615_131834.jpeg"]
             */

            private String id;
            private String yInquiryDemandProjectId;
            private String yInquiryDemandId;
            private String vTitle;
            private String createDate;
            private int isService;
            private String imgsrt;
            private List<OfferListBean> offer_list;
            private List<String> imgArr;
            private String vMsg;

            private String price;

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getvMsg() {
                return vMsg;
            }

            public void setvMsg(String vMsg) {
                this.vMsg = vMsg;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getYInquiryDemandProjectId() {
                return yInquiryDemandProjectId;
            }

            public void setYInquiryDemandProjectId(String yInquiryDemandProjectId) {
                this.yInquiryDemandProjectId = yInquiryDemandProjectId;
            }

            public String getYInquiryDemandId() {
                return yInquiryDemandId;
            }

            public void setYInquiryDemandId(String yInquiryDemandId) {
                this.yInquiryDemandId = yInquiryDemandId;
            }

            public String getVTitle() {
                return vTitle;
            }

            public void setVTitle(String vTitle) {
                this.vTitle = vTitle;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public int getIsService() {
                return isService;
            }

            public void setIsService(int isService) {
                this.isService = isService;
            }

            public String getImgsrt() {
                return imgsrt;
            }

            public void setImgsrt(String imgsrt) {
                this.imgsrt = imgsrt;
            }

            public List<OfferListBean> getOffer_list() {
                return offer_list;
            }

            public void setOffer_list(List<OfferListBean> offer_list) {
                this.offer_list = offer_list;
            }

            public List<String> getImgArr() {
                return imgArr;
            }

            public void setImgArr(List<String> imgArr) {
                this.imgArr = imgArr;
            }

            public static class OfferListBean implements Serializable{
                /**
                 * id : 1065
                 * yInquiryDemandProjectOfferId : 724953777886461952
                 * yInquiryDemandProjectId : 724950991497068544
                 * yInquiryDemandId : 724950991308324864
                 * userId : 719539276219416576
                 * yStoreId : 692341585785913345
                 * isService : 0
                 * storeJson : {"address":"凤凰山附近","charactStr":"/upload/store/222.png||/upload/store/222.png","id":1018,"introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","isIndex":1,"keywsr":"技术 修车 实","latitude":"39.916485","longitude":"116.49798","phone":"  400-2333-1123","pictureStr":"/upload/store/222.png||/upload/store/222.png","review":"4.5","slogan":"维修商店测试 美容首单50","userId":719539276219416576,"vLevel":"A级","vName":"维修商店测试","yStoreId":692341585785913345}
                 * userJson : {"headPortrait":"/upload/2020-06-18/20200618164348_110881.jpg","id":0,"userAccount":"THANU4","userBalance":0.0,"userHash":"843B1049032A7E7C9F7D2E2CBE6D37A6","userId":0,"userIntegral":0,"userName":"阿斯顿马丁","yStoreId":0}
                 * vPrice : 100.0
                 * createDate : 2020-06-23 11:47:29
                 * store_info : {"id":"1018","yStoreId":"692341585785913345","userId":"719539276219416576","vName":"维修商店测试","review":"4.5","keywsr":"技术 修车 实","address":"凤凰山附近","longitude":"116.49798","latitude":"39.916485","phone":"  400-2333-1123","vLevel":"A级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"/upload/store/222.png||/upload/store/222.png","pictureStr":"/upload/store/222.png||/upload/store/222.png","slogan":"维修商店测试 美容首单50","isIndex":1}
                 */

                private String id;
                private String yInquiryDemandProjectOfferId;
                private String yInquiryDemandProjectId;
                private String yInquiryDemandId;
                private String userId;
                private String yStoreId;
                private int isService;
                private String storeJson;
                private String userJson;
                private String vPrice;
                private String createDate;
                private StoreInfoBean store_info;
                /**
                 * user_info : {"id":"0","userId":"0","userBalance":0,"userName":"龙肆","userAccount":"6545764","userIntegral":"0","yStoreId":"0","userHash":"36635C17B0E19DD1E14A92D8895B47C9","headPortrait":"/upload/2020-10-15/20201015175931_273079.jpg","userPhone":"18203048656"}
                 */

                private UserInfoBean user_info;


                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getYInquiryDemandProjectOfferId() {
                    return yInquiryDemandProjectOfferId;
                }

                public void setYInquiryDemandProjectOfferId(String yInquiryDemandProjectOfferId) {
                    this.yInquiryDemandProjectOfferId = yInquiryDemandProjectOfferId;
                }

                public String getYInquiryDemandProjectId() {
                    return yInquiryDemandProjectId;
                }

                public void setYInquiryDemandProjectId(String yInquiryDemandProjectId) {
                    this.yInquiryDemandProjectId = yInquiryDemandProjectId;
                }

                public String getYInquiryDemandId() {
                    return yInquiryDemandId;
                }

                public void setYInquiryDemandId(String yInquiryDemandId) {
                    this.yInquiryDemandId = yInquiryDemandId;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getYStoreId() {
                    return yStoreId;
                }

                public void setYStoreId(String yStoreId) {
                    this.yStoreId = yStoreId;
                }

                public int getIsService() {
                    return isService;
                }

                public void setIsService(int isService) {
                    this.isService = isService;
                }

                public String getStoreJson() {
                    return storeJson;
                }

                public void setStoreJson(String storeJson) {
                    this.storeJson = storeJson;
                }

                public String getUserJson() {
                    return userJson;
                }

                public void setUserJson(String userJson) {
                    this.userJson = userJson;
                }

                public String getVPrice() {
                    return vPrice;
                }

                public void setVPrice(String vPrice) {
                    this.vPrice = vPrice;
                }

                public String getCreateDate() {
                    return createDate;
                }

                public void setCreateDate(String createDate) {
                    this.createDate = createDate;
                }

                public StoreInfoBean getStore_info() {
                    return store_info;
                }

                public void setStore_info(StoreInfoBean store_info) {
                    this.store_info = store_info;
                }

                public UserInfoBean getUser_info() {
                    return user_info;
                }

                public void setUser_info(UserInfoBean user_info) {
                    this.user_info = user_info;
                }

                public static class StoreInfoBean implements Serializable{
                    /**
                     * id : 1018
                     * yStoreId : 692341585785913345
                     * userId : 719539276219416576
                     * vName : 维修商店测试
                     * review : 4.5
                     * keywsr : 技术 修车 实
                     * address : 凤凰山附近
                     * longitude : 116.49798
                     * latitude : 39.916485
                     * phone :   400-2333-1123
                     * vLevel : A级
                     * introduce : 听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。
                     * charactStr : /upload/store/222.png||/upload/store/222.png
                     * pictureStr : /upload/store/222.png||/upload/store/222.png
                     * slogan : 维修商店测试 美容首单50
                     * isIndex : 1
                     */

                    private String id;
                    private String yStoreId;
                    private String userId;
                    private String vName;
                    private String review;
                    private String keywsr;
                    private String address;
                    private String longitude;
                    private String latitude;
                    private String phone;
                    private String vLevel;
                    private String introduce;
                    private String charactStr;
                    private String pictureStr;
                    private String slogan;
                    private int isIndex;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getYStoreId() {
                        return yStoreId;
                    }

                    public void setYStoreId(String yStoreId) {
                        this.yStoreId = yStoreId;
                    }

                    public String getUserId() {
                        return userId;
                    }

                    public void setUserId(String userId) {
                        this.userId = userId;
                    }

                    public String getVName() {
                        return vName;
                    }

                    public void setVName(String vName) {
                        this.vName = vName;
                    }

                    public String getReview() {
                        return review;
                    }

                    public void setReview(String review) {
                        this.review = review;
                    }

                    public String getKeywsr() {
                        return keywsr;
                    }

                    public void setKeywsr(String keywsr) {
                        this.keywsr = keywsr;
                    }

                    public String getAddress() {
                        return address;
                    }

                    public void setAddress(String address) {
                        this.address = address;
                    }

                    public String getLongitude() {
                        return longitude;
                    }

                    public void setLongitude(String longitude) {
                        this.longitude = longitude;
                    }

                    public String getLatitude() {
                        return latitude;
                    }

                    public void setLatitude(String latitude) {
                        this.latitude = latitude;
                    }

                    public String getPhone() {
                        return phone;
                    }

                    public void setPhone(String phone) {
                        this.phone = phone;
                    }

                    public String getVLevel() {
                        return vLevel;
                    }

                    public void setVLevel(String vLevel) {
                        this.vLevel = vLevel;
                    }

                    public String getIntroduce() {
                        return introduce;
                    }

                    public void setIntroduce(String introduce) {
                        this.introduce = introduce;
                    }

                    public String getCharactStr() {
                        return charactStr;
                    }

                    public void setCharactStr(String charactStr) {
                        this.charactStr = charactStr;
                    }

                    public String getPictureStr() {
                        return pictureStr;
                    }

                    public void setPictureStr(String pictureStr) {
                        this.pictureStr = pictureStr;
                    }

                    public String getSlogan() {
                        return slogan;
                    }

                    public void setSlogan(String slogan) {
                        this.slogan = slogan;
                    }

                    public int getIsIndex() {
                        return isIndex;
                    }

                    public void setIsIndex(int isIndex) {
                        this.isIndex = isIndex;
                    }
                }

                public static class UserInfoBean implements Serializable{
                    /**
                     * id : 0
                     * userId : 0
                     * userBalance : 0.0
                     * userName : 龙肆
                     * userAccount : 6545764
                     * userIntegral : 0
                     * yStoreId : 0
                     * userHash : 36635C17B0E19DD1E14A92D8895B47C9
                     * headPortrait : /upload/2020-10-15/20201015175931_273079.jpg
                     * userPhone : 18203048656
                     */

                    @SerializedName("id")
                    private String idX;
                    @SerializedName("userId")
                    private String userIdX;
                    private double userBalance;
                    private String userName;
                    private String userAccount;
                    private String userIntegral;
                    @SerializedName("yStoreId")
                    private String yStoreIdX;
                    private String userHash;
                    private String headPortrait;
                    private String userPhone;

                    public String getIdX() {
                        return idX;
                    }

                    public void setIdX(String idX) {
                        this.idX = idX;
                    }

                    public String getUserIdX() {
                        return userIdX;
                    }

                    public void setUserIdX(String userIdX) {
                        this.userIdX = userIdX;
                    }

                    public double getUserBalance() {
                        return userBalance;
                    }

                    public void setUserBalance(double userBalance) {
                        this.userBalance = userBalance;
                    }

                    public String getUserName() {
                        return userName;
                    }

                    public void setUserName(String userName) {
                        this.userName = userName;
                    }

                    public String getUserAccount() {
                        return userAccount;
                    }

                    public void setUserAccount(String userAccount) {
                        this.userAccount = userAccount;
                    }

                    public String getUserIntegral() {
                        return userIntegral;
                    }

                    public void setUserIntegral(String userIntegral) {
                        this.userIntegral = userIntegral;
                    }

                    public String getYStoreIdX() {
                        return yStoreIdX;
                    }

                    public void setYStoreIdX(String yStoreIdX) {
                        this.yStoreIdX = yStoreIdX;
                    }

                    public String getUserHash() {
                        return userHash;
                    }

                    public void setUserHash(String userHash) {
                        this.userHash = userHash;
                    }

                    public String getHeadPortrait() {
                        return headPortrait;
                    }

                    public void setHeadPortrait(String headPortrait) {
                        this.headPortrait = headPortrait;
                    }

                    public String getUserPhone() {
                        return userPhone;
                    }

                    public void setUserPhone(String userPhone) {
                        this.userPhone = userPhone;
                    }
                }
            }
        }
    }

    public static class GoodsListBean implements Serializable{
        /**
         * id : 1019
         * yGoodsId : 692341585785913346
         * yClassifyId : 692341585785913344
         * yStoreId : 692341585785913346
         * gName : 汽车行车记录仪降压线通用小米70迈360专用停车监控车载usb电源线
         * gDesc : 现代名图后备箱垫全包围专用防水定制装饰垫现代名图汽车尾箱垫子
         * gPrice : 1000.0
         * orPrice : 3000.0
         * imgStr : /upload/pro.png||/upload/pro.png
         * gImg : /upload/pro.png
         * gState : 1
         * isPopular : 0
         * isIntegral : 0
         * gIntegral : 0
         * createDate : 2019-09-27 10:31:19
         * isSoffer : 1
         */

        private String id;
        private String yGoodsId;
        private String yClassifyId;
        private String yStoreId;
        private String gName;
        private String gDesc;
        private double gPrice;
        private double orPrice;
        private String imgStr;
        private String gImg;
        private int gState;
        private int isPopular;
        private int isIntegral;
        private int gIntegral;
        private String createDate;
        private int isSoffer;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYGoodsId() {
            return yGoodsId;
        }

        public void setYGoodsId(String yGoodsId) {
            this.yGoodsId = yGoodsId;
        }

        public String getYClassifyId() {
            return yClassifyId;
        }

        public void setYClassifyId(String yClassifyId) {
            this.yClassifyId = yClassifyId;
        }

        public String getYStoreId() {
            return yStoreId;
        }

        public void setYStoreId(String yStoreId) {
            this.yStoreId = yStoreId;
        }

        public String getGName() {
            return gName;
        }

        public void setGName(String gName) {
            this.gName = gName;
        }

        public String getGDesc() {
            return gDesc;
        }

        public void setGDesc(String gDesc) {
            this.gDesc = gDesc;
        }

        public double getGPrice() {
            return gPrice;
        }

        public void setGPrice(double gPrice) {
            this.gPrice = gPrice;
        }

        public double getOrPrice() {
            return orPrice;
        }

        public void setOrPrice(double orPrice) {
            this.orPrice = orPrice;
        }

        public String getImgStr() {
            return imgStr;
        }

        public void setImgStr(String imgStr) {
            this.imgStr = imgStr;
        }

        public String getGImg() {
            return gImg;
        }

        public void setGImg(String gImg) {
            this.gImg = gImg;
        }

        public int getGState() {
            return gState;
        }

        public void setGState(int gState) {
            this.gState = gState;
        }

        public int getIsPopular() {
            return isPopular;
        }

        public void setIsPopular(int isPopular) {
            this.isPopular = isPopular;
        }

        public int getIsIntegral() {
            return isIntegral;
        }

        public void setIsIntegral(int isIntegral) {
            this.isIntegral = isIntegral;
        }

        public int getGIntegral() {
            return gIntegral;
        }

        public void setGIntegral(int gIntegral) {
            this.gIntegral = gIntegral;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getIsSoffer() {
            return isSoffer;
        }

        public void setIsSoffer(int isSoffer) {
            this.isSoffer = isSoffer;
        }
    }

    /*public static class CircleListBean implements Serializable{
        *//**
         * id : 107
         * yCircleId : 693478964160626688
         * userId : 20180531104813
         * tName : 宝马专修交流圈
         * vLogo : /upload/logo/333.jpg
         * vMsg : 宝马专修交流圈
         * createDate : 2020-03-28 15:17:49
         * sum : 4
         *//*

        private String id;
        private String yCircleId;
        private String userId;
        private String tName;
        private String vLogo;
        private String vMsg;
        private String createDate;
        private int sum;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYCircleId() {
            return yCircleId;
        }

        public void setYCircleId(String yCircleId) {
            this.yCircleId = yCircleId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getTName() {
            return tName;
        }

        public void setTName(String tName) {
            this.tName = tName;
        }

        public String getVLogo() {
            return vLogo;
        }

        public void setVLogo(String vLogo) {
            this.vLogo = vLogo;
        }

        public String getVMsg() {
            return vMsg;
        }

        public void setVMsg(String vMsg) {
            this.vMsg = vMsg;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }
    }*/

    public static class RecruitListBean implements Serializable{
        /**
         * id : 1119
         * yForumPostsId : 781132832062832640
         * userId : 819539276219416576
         * v_title : 出招汽车维修
         * iClassify : 1
         * iGive : 0
         * iLike : 0
         * vJson :
         * createDate : 2020-11-25 12:22:59
         * recruit_info : {"position":"出招汽车维修","nameStore":"天下无敌","telephone":"56688999","salary":"40000\u2014900000","handsOn":"工程部开局刚才看见下车不看看发刚v买了个吃口饭过来查干湖把你老公#","address":"才能江湖告急牛逼"}
         * user_info : {"id":"0","userId":"819539276219416576","userBalance":0,"userName":"龙肆店铺技师","userIntegral":"0","yStoreId":"0","headPortrait":"/upload/2020-06-11/20200611090404_650532.png","userPhone":"13530039446"}
         * is_give : 0
         * is_like : 0
         */

        private String id;
        private String yForumPostsId;
        private String userId;
        private String v_title;
        private int iClassify;
        private int iGive;
        private int iLike;
        private String vJson;
        private String createDate;
        private RecruitInfoBean recruit_info;
        private UserInfoBean user_info;
        private int is_give;
        private int is_like;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYForumPostsId() {
            return yForumPostsId;
        }

        public void setYForumPostsId(String yForumPostsId) {
            this.yForumPostsId = yForumPostsId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getV_title() {
            return v_title;
        }

        public void setV_title(String v_title) {
            this.v_title = v_title;
        }

        public int getIClassify() {
            return iClassify;
        }

        public void setIClassify(int iClassify) {
            this.iClassify = iClassify;
        }

        public int getIGive() {
            return iGive;
        }

        public void setIGive(int iGive) {
            this.iGive = iGive;
        }

        public int getILike() {
            return iLike;
        }

        public void setILike(int iLike) {
            this.iLike = iLike;
        }

        public String getVJson() {
            return vJson;
        }

        public void setVJson(String vJson) {
            this.vJson = vJson;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public RecruitInfoBean getRecruit_info() {
            return recruit_info;
        }

        public void setRecruit_info(RecruitInfoBean recruit_info) {
            this.recruit_info = recruit_info;
        }

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public int getIs_give() {
            return is_give;
        }

        public void setIs_give(int is_give) {
            this.is_give = is_give;
        }

        public int getIs_like() {
            return is_like;
        }

        public void setIs_like(int is_like) {
            this.is_like = is_like;
        }

        public static class RecruitInfoBean implements Serializable{
            /**
             * position : 出招汽车维修
             * nameStore : 天下无敌
             * telephone : 56688999
             * salary : 40000—900000
             * handsOn : 工程部开局刚才看见下车不看看发刚v买了个吃口饭过来查干湖把你老公#
             * address : 才能江湖告急牛逼
             */

            private String position;
            private String nameStore;
            private String telephone;
            private String salary;
            private String handsOn;
            private String address;

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getNameStore() {
                return nameStore;
            }

            public void setNameStore(String nameStore) {
                this.nameStore = nameStore;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getSalary() {
                return salary;
            }

            public void setSalary(String salary) {
                this.salary = salary;
            }

            public String getHandsOn() {
                return handsOn;
            }

            public void setHandsOn(String handsOn) {
                this.handsOn = handsOn;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }

        public static class UserInfoBean implements Serializable{
            /**
             * id : 0
             * userId : 819539276219416576
             * userBalance : 0.0
             * userName : 龙肆店铺技师
             * userIntegral : 0
             * yStoreId : 0
             * headPortrait : /upload/2020-06-11/20200611090404_650532.png
             * userPhone : 13530039446
             */

            private String id;
            private String userId;
            private double userBalance;
            private String userName;
            private String userIntegral;
            private String yStoreId;
            private String headPortrait;
            private String userPhone;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public double getUserBalance() {
                return userBalance;
            }

            public void setUserBalance(double userBalance) {
                this.userBalance = userBalance;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserIntegral() {
                return userIntegral;
            }

            public void setUserIntegral(String userIntegral) {
                this.userIntegral = userIntegral;
            }

            public String getYStoreId() {
                return yStoreId;
            }

            public void setYStoreId(String yStoreId) {
                this.yStoreId = yStoreId;
            }

            public String getHeadPortrait() {
                return headPortrait;
            }

            public void setHeadPortrait(String headPortrait) {
                this.headPortrait = headPortrait;
            }

            public String getUserPhone() {
                return userPhone;
            }

            public void setUserPhone(String userPhone) {
                this.userPhone = userPhone;
            }
        }
    }

    public static class ToolLeasingBean implements Serializable{
        /**
         * id : 1104
         * yForumPostsId : 768226579795935232
         * userId : 20180531104813
         * v_title : 太阳膜
         * iClassify : 5
         * iGive : 0
         * iLike : 0
         * vJson :
         * createDate : 2020-10-20 21:38:09
         * tool_info : {"v_title":"太阳膜","v_price":"15","v_specifi":"天","v_duration":"5","v_text":"独角戏百度百家新疆城建","imgstr":"/upload/2020-10-20/20201020213807_466951.jpeg||/upload/2020-10-20/20201020213807_587324.jpeg||/upload/2020-10-20/20201020213807_429257.jpeg","imgArr":["/upload/2020-10-20/20201020213807_466951.jpeg","/upload/2020-10-20/20201020213807_587324.jpeg","/upload/2020-10-20/20201020213807_429257.jpeg"]}
         * user_info : {"id":"0","userId":"20180531104813","userBalance":0,"userIntegral":"0","yStoreId":"0"}
         * is_give : 0
         * is_like : 0
         */

        private String id;
        private String yForumPostsId;
        private String userId;
        private String v_title;
        private int iClassify;
        private int iGive;
        private int iLike;
        private String vJson;
        private String createDate;
        private ToolInfoBean tool_info;
        private UserInfoBeanX user_info;
        private int is_give;
        private int is_like;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYForumPostsId() {
            return yForumPostsId;
        }

        public void setYForumPostsId(String yForumPostsId) {
            this.yForumPostsId = yForumPostsId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getV_title() {
            return v_title;
        }

        public void setV_title(String v_title) {
            this.v_title = v_title;
        }

        public int getIClassify() {
            return iClassify;
        }

        public void setIClassify(int iClassify) {
            this.iClassify = iClassify;
        }

        public int getIGive() {
            return iGive;
        }

        public void setIGive(int iGive) {
            this.iGive = iGive;
        }

        public int getILike() {
            return iLike;
        }

        public void setILike(int iLike) {
            this.iLike = iLike;
        }

        public String getVJson() {
            return vJson;
        }

        public void setVJson(String vJson) {
            this.vJson = vJson;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public ToolInfoBean getTool_info() {
            return tool_info;
        }

        public void setTool_info(ToolInfoBean tool_info) {
            this.tool_info = tool_info;
        }

        public UserInfoBeanX getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBeanX user_info) {
            this.user_info = user_info;
        }

        public int getIs_give() {
            return is_give;
        }

        public void setIs_give(int is_give) {
            this.is_give = is_give;
        }

        public int getIs_like() {
            return is_like;
        }

        public void setIs_like(int is_like) {
            this.is_like = is_like;
        }

        public static class ToolInfoBean implements Serializable{
            /**
             * v_title : 太阳膜
             * v_price : 15
             * v_specifi : 天
             * v_duration : 5
             * v_text : 独角戏百度百家新疆城建
             * imgstr : /upload/2020-10-20/20201020213807_466951.jpeg||/upload/2020-10-20/20201020213807_587324.jpeg||/upload/2020-10-20/20201020213807_429257.jpeg
             * imgArr : ["/upload/2020-10-20/20201020213807_466951.jpeg","/upload/2020-10-20/20201020213807_587324.jpeg","/upload/2020-10-20/20201020213807_429257.jpeg"]
             */

            private String v_title;
            private String v_price;
            private String v_specifi;
            private String v_duration;
            private String v_text;
            private String imgstr;
            private List<String> imgArr;

            public String getV_title() {
                return v_title;
            }

            public void setV_title(String v_title) {
                this.v_title = v_title;
            }

            public String getV_price() {
                return v_price;
            }

            public void setV_price(String v_price) {
                this.v_price = v_price;
            }

            public String getV_specifi() {
                return v_specifi;
            }

            public void setV_specifi(String v_specifi) {
                this.v_specifi = v_specifi;
            }

            public String getV_duration() {
                return v_duration;
            }

            public void setV_duration(String v_duration) {
                this.v_duration = v_duration;
            }

            public String getV_text() {
                return v_text;
            }

            public void setV_text(String v_text) {
                this.v_text = v_text;
            }

            public String getImgstr() {
                return imgstr;
            }

            public void setImgstr(String imgstr) {
                this.imgstr = imgstr;
            }

            public List<String> getImgArr() {
                return imgArr;
            }

            public void setImgArr(List<String> imgArr) {
                this.imgArr = imgArr;
            }
        }

        public static class UserInfoBeanX implements Serializable{
            /**
             * id : 0
             * userId : 819539276219416576
             * userBalance : 0.0
             * userName : 龙肆店铺技师
             * userIntegral : 0
             * yStoreId : 0
             * headPortrait : /upload/2020-06-11/20200611090404_650532.png
             * userPhone : 13530039446
             */

            private String id;
            private String userId;
            private double userBalance;
            private String userName;
            private String userIntegral;
            private String yStoreId;
            private String headPortrait;
            private String userPhone;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public double getUserBalance() {
                return userBalance;
            }

            public void setUserBalance(double userBalance) {
                this.userBalance = userBalance;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserIntegral() {
                return userIntegral;
            }

            public void setUserIntegral(String userIntegral) {
                this.userIntegral = userIntegral;
            }

            public String getYStoreId() {
                return yStoreId;
            }

            public void setYStoreId(String yStoreId) {
                this.yStoreId = yStoreId;
            }

            public String getHeadPortrait() {
                return headPortrait;
            }

            public void setHeadPortrait(String headPortrait) {
                this.headPortrait = headPortrait;
            }

            public String getUserPhone() {
                return userPhone;
            }

            public void setUserPhone(String userPhone) {
                this.userPhone = userPhone;
            }
        }
    }

    /*public static class ForumListBean implements Serializable{
        *//**
         * id : 1075
         * yForumPostsId : 725351301054988288
         * userId : 719539276219416576
         * v_title : 阿斯顿马丁
         * iClassify : 8
         * iGive : 0
         * iLike : 0
         * vJson : {"imgstr":"/upload/2020-06-24/20200624140706_193302.jpg||/upload/2020-06-24/20200624140706_451730.jpg||/upload/2020-06-24/20200624140706_741676.jpeg||/upload/2020-06-24/20200624140706_726200.jpeg||/upload/2020-06-24/20200624140706_396687.jpeg||/upload/2020-06-24/20200624140706_412154.jpg","v_content":"啊是多大阿斯顿阿斯顿阿斯顿","v_end_time":"2037-03-18","v_place":"阿斯顿阿斯顿项目","v_start_time":"2020-06-24","v_title":"阿斯顿马丁","v_type":"啊水电费阿斯顿"}
         * createDate : 2020-06-24 14:07:06
         * activity_info : {"v_title":"阿斯顿马丁","v_start_time":"2020-06-24","v_end_time":"2037-03-18","v_place":"阿斯顿阿斯顿项目","v_type":"啊水电费阿斯顿","v_content":"啊是多大阿斯顿阿斯顿阿斯顿","imgstr":"/upload/2020-06-24/20200624140706_193302.jpg||/upload/2020-06-24/20200624140706_451730.jpg||/upload/2020-06-24/20200624140706_741676.jpeg||/upload/2020-06-24/20200624140706_726200.jpeg||/upload/2020-06-24/20200624140706_396687.jpeg||/upload/2020-06-24/20200624140706_412154.jpg","imgArr":["/upload/2020-06-24/20200624140706_193302.jpg","/upload/2020-06-24/20200624140706_451730.jpg","/upload/2020-06-24/20200624140706_741676.jpeg","/upload/2020-06-24/20200624140706_726200.jpeg","/upload/2020-06-24/20200624140706_396687.jpeg","/upload/2020-06-24/20200624140706_412154.jpg"]}
         * user_info : {"id":"0","userId":"719539276219416576","userBalance":0,"userName":"阿斯顿马丁","userIntegral":"0","yStoreId":"0","headPortrait":"/upload/2020-06-18/20200618164348_110881.jpg","userPhone":"18306043085"}
         * is_give : 0
         * is_like : 0
         *//*

        private String id;
        private String yForumPostsId;
        private String userId;
        private String v_title;
        private int iClassify;
        private int iGive;
        private int iLike;
        private String vJson;
        private String createDate;
        private ActivityInfoBean activity_info;
        private UserInfoBeanX user_info;
        private int is_give;
        private int is_like;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYForumPostsId() {
            return yForumPostsId;
        }

        public void setYForumPostsId(String yForumPostsId) {
            this.yForumPostsId = yForumPostsId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getV_title() {
            return v_title;
        }

        public void setV_title(String v_title) {
            this.v_title = v_title;
        }

        public int getIClassify() {
            return iClassify;
        }

        public void setIClassify(int iClassify) {
            this.iClassify = iClassify;
        }

        public int getIGive() {
            return iGive;
        }

        public void setIGive(int iGive) {
            this.iGive = iGive;
        }

        public int getILike() {
            return iLike;
        }

        public void setILike(int iLike) {
            this.iLike = iLike;
        }

        public String getVJson() {
            return vJson;
        }

        public void setVJson(String vJson) {
            this.vJson = vJson;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public ActivityInfoBean getActivity_info() {
            return activity_info;
        }

        public void setActivity_info(ActivityInfoBean activity_info) {
            this.activity_info = activity_info;
        }

        public UserInfoBeanX getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBeanX user_info) {
            this.user_info = user_info;
        }

        public int getIs_give() {
            return is_give;
        }

        public void setIs_give(int is_give) {
            this.is_give = is_give;
        }

        public int getIs_like() {
            return is_like;
        }

        public void setIs_like(int is_like) {
            this.is_like = is_like;
        }

        public static class ActivityInfoBean implements Serializable{
            *//**
             * v_title : 阿斯顿马丁
             * v_start_time : 2020-06-24
             * v_end_time : 2037-03-18
             * v_place : 阿斯顿阿斯顿项目
             * v_type : 啊水电费阿斯顿
             * v_content : 啊是多大阿斯顿阿斯顿阿斯顿
             * imgstr : /upload/2020-06-24/20200624140706_193302.jpg||/upload/2020-06-24/20200624140706_451730.jpg||/upload/2020-06-24/20200624140706_741676.jpeg||/upload/2020-06-24/20200624140706_726200.jpeg||/upload/2020-06-24/20200624140706_396687.jpeg||/upload/2020-06-24/20200624140706_412154.jpg
             * imgArr : ["/upload/2020-06-24/20200624140706_193302.jpg","/upload/2020-06-24/20200624140706_451730.jpg","/upload/2020-06-24/20200624140706_741676.jpeg","/upload/2020-06-24/20200624140706_726200.jpeg","/upload/2020-06-24/20200624140706_396687.jpeg","/upload/2020-06-24/20200624140706_412154.jpg"]
             *//*

            private String v_title;
            private String v_start_time;
            private String v_end_time;
            private String v_place;
            private String v_type;
            private String v_content;
            private String imgstr;
            private List<String> imgArr;

            public String getV_title() {
                return v_title;
            }

            public void setV_title(String v_title) {
                this.v_title = v_title;
            }

            public String getV_start_time() {
                return v_start_time;
            }

            public void setV_start_time(String v_start_time) {
                this.v_start_time = v_start_time;
            }

            public String getV_end_time() {
                return v_end_time;
            }

            public void setV_end_time(String v_end_time) {
                this.v_end_time = v_end_time;
            }

            public String getV_place() {
                return v_place;
            }

            public void setV_place(String v_place) {
                this.v_place = v_place;
            }

            public String getV_type() {
                return v_type;
            }

            public void setV_type(String v_type) {
                this.v_type = v_type;
            }

            public String getV_content() {
                return v_content;
            }

            public void setV_content(String v_content) {
                this.v_content = v_content;
            }

            public String getImgstr() {
                return imgstr;
            }

            public void setImgstr(String imgstr) {
                this.imgstr = imgstr;
            }

            public List<String> getImgArr() {
                return imgArr;
            }

            public void setImgArr(List<String> imgArr) {
                this.imgArr = imgArr;
            }
        }

        public static class UserInfoBeanX implements Serializable{
            *//**
             * id : 0
             * userId : 719539276219416576
             * userBalance : 0.0
             * userName : 阿斯顿马丁
             * userIntegral : 0
             * yStoreId : 0
             * headPortrait : /upload/2020-06-18/20200618164348_110881.jpg
             * userPhone : 18306043085
             *//*

            private String id;
            private String userId;
            private double userBalance;
            private String userName;
            private String userIntegral;
            private String yStoreId;
            private String headPortrait;
            private String userPhone;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public double getUserBalance() {
                return userBalance;
            }

            public void setUserBalance(double userBalance) {
                this.userBalance = userBalance;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserIntegral() {
                return userIntegral;
            }

            public void setUserIntegral(String userIntegral) {
                this.userIntegral = userIntegral;
            }

            public String getYStoreId() {
                return yStoreId;
            }

            public void setYStoreId(String yStoreId) {
                this.yStoreId = yStoreId;
            }

            public String getHeadPortrait() {
                return headPortrait;
            }

            public void setHeadPortrait(String headPortrait) {
                this.headPortrait = headPortrait;
            }

            public String getUserPhone() {
                return userPhone;
            }

            public void setUserPhone(String userPhone) {
                this.userPhone = userPhone;
            }
        }
    }*/
}
