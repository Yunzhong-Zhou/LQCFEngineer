package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/9.
 */
public class StoreDetailModel implements Serializable {
    /**
     * store_tech_list : [{"id":"0","userId":"0","userBalance":0,"userName":"","userIntegral":"0","yStoreId":"692341585785913344","userHash":"7E5194837D4B3BBE125C07F8FA16ED0A","headPortrait":"/upload/timg.jpg","createDate":"2020-05-29 15:03:05","techJson":" {\"star\":\"4\",\"working\":\"1\"}","tech_info":{"star":4,"working":1}},{"id":"0","userId":"0","userBalance":0,"userName":"","userIntegral":"0","yStoreId":"692341585785913344","userHash":"B52CF117B3218C7AD48568134544695C","headPortrait":"/upload/timg.jpg","createDate":"2020-05-31 14:25:16","techJson":" {\"star\":\"4\",\"working\":\"1\"}","tech_info":{"star":4,"working":1}},{"id":"0","userId":"0","userBalance":0,"userName":"","userIntegral":"0","yStoreId":"692341585785913344","userHash":"5F71987EBBC44C9ADCB4578D1ED034A4","headPortrait":"/upload/timg.jpg","createDate":"2020-06-06 10:34:31","techJson":"{\"star\":0,\"working\":1}","tech_info":{"star":0,"working":1}},{"id":"0","userId":"0","userBalance":0,"userName":"","userIntegral":"0","yStoreId":"692341585785913344","userHash":"9523B35869B893EDC7529DF626CA6B04","headPortrait":"/upload/head/719242188466159616.jpg","createDate":"2020-06-07 17:31:40","techJson":"{\"star\":0,\"working\":1}","tech_info":{"star":0,"working":1}},{"id":"0","userId":"0","userBalance":0,"userName":"周锶俊","userIntegral":"0","yStoreId":"692341585785913344","userHash":"504F04C880F2A74235E7CA1801A56461","headPortrait":"/upload/2020-06-13/20200613091533_407285.png","createDate":"2020-06-10 23:19:09","techJson":"{\"star\":0,\"working\":1}","tech_info":{"star":0,"working":1}},{"id":"0","userId":"0","userBalance":0,"userName":"","userIntegral":"0","yStoreId":"692341585785913344","userHash":"018CEC7943E6A744F4701ADEC73EE8CA","headPortrait":" ","createDate":"2020-06-18 13:55:53","techJson":"{\"star\":0,\"working\":1}","tech_info":{"star":0,"working":1}}]
     * store_service_list : [{"id":"1023","yStoreServiceId":"692341585785913344","yStoreId":"692341585785913344","yState":1,"lineupSum":10,"isSheet":0,"yStateValue":"维修","sPrice":100,"pictureStr":"/static/img/20200528151327.png"}]
     * info : {"id":"1017","yStoreId":"692341585785913344","userId":"20180531104813","vName":"龙肆商店测试","review":"4.5分","keywsr":"保养 修车 服务","address":"深圳市光明新区公明街道东环大道松白工业区C区2栋一层106号","longitude":"116.49798","latitude":"39.916485","distance":" ","phone":" 400-2333-1123","vLevel":"A级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"/upload/store/222.png||/upload/store/222.png","pictureStr":"/upload/store/222.png||/upload/store/222.png","slogan":"商店测试  洗车首单10","pictureArr":["/upload/store/222.png","/upload/store/222.png"],"charactArr":["/upload/store/222.png","/upload/store/222.png"],"orderSum":4,"isIndex":1,"isCollection":1,"colle_info":{"id":"29","yUserCollectionId":"724191712410337280","userId":"714547022807433216","yId":"692341585785913344","category":2,"createDate":"2020-06-21 09:19:19"}}
     */

    private InfoBean info;
    private List<StoreTechListBean> store_tech_list;
    private List<StoreServiceListBean> store_service_list;
    /**
     * kf_user_info : {"userId":"714547022807433216","userBalance":0,"yStoreId":"714547022807433216","userName":"阿斯顿马丁","userAccount":"Y11P3D","uToken":"878FFBB91EB36C913E76875CFB338E035AC9046CF8BD6A4D568B2A1A8A79ACDE","isTechn":0,"userHash":"36635C17B0E19DD1E14A92D8895B47CD","headPortrait":"/upload/2020-06-13/20200613085845_638405.jpeg","userPhone":"18306043086","userJson":"{\"birthday\":\"2002-07-13\",\"u_gender\":\"男\"}","techJson":" {\"star\":\"4\",\"working\":\"1\"}","isAuth":1,"isKf":0}
     */

    private KfUserInfoBean kf_user_info;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public List<StoreTechListBean> getStore_tech_list() {
        return store_tech_list;
    }

    public void setStore_tech_list(List<StoreTechListBean> store_tech_list) {
        this.store_tech_list = store_tech_list;
    }

    public List<StoreServiceListBean> getStore_service_list() {
        return store_service_list;
    }

    public void setStore_service_list(List<StoreServiceListBean> store_service_list) {
        this.store_service_list = store_service_list;
    }

    public KfUserInfoBean getKf_user_info() {
        return kf_user_info;
    }

    public void setKf_user_info(KfUserInfoBean kf_user_info) {
        this.kf_user_info = kf_user_info;
    }

    public static class InfoBean implements Serializable{
        /**
         * id : 1017
         * yStoreId : 692341585785913344
         * userId : 20180531104813
         * vName : 龙肆商店测试
         * review : 4.5分
         * keywsr : 保养 修车 服务
         * address : 深圳市光明新区公明街道东环大道松白工业区C区2栋一层106号
         * longitude : 116.49798
         * latitude : 39.916485
         * distance :
         * phone :  400-2333-1123
         * vLevel : A级
         * introduce : 听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。
         * charactStr : /upload/store/222.png||/upload/store/222.png
         * pictureStr : /upload/store/222.png||/upload/store/222.png
         * slogan : 商店测试  洗车首单10
         * pictureArr : ["/upload/store/222.png","/upload/store/222.png"]
         * charactArr : ["/upload/store/222.png","/upload/store/222.png"]
         * orderSum : 4
         * isIndex : 1
         * isCollection : 1
         * colle_info : {"id":"29","yUserCollectionId":"724191712410337280","userId":"714547022807433216","yId":"692341585785913344","category":2,"createDate":"2020-06-21 09:19:19"}
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
        private String distance;
        private String phone;
        private String vLevel;
        private String introduce;
        private String charactStr;
        private String pictureStr;
        private String slogan;
        private int orderSum;
        private int isIndex;
        private int isCollection;
        private ColleInfoBean colle_info;
        private List<String> pictureArr;
        private List<String> charactArr;

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

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
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

        public int getOrderSum() {
            return orderSum;
        }

        public void setOrderSum(int orderSum) {
            this.orderSum = orderSum;
        }

        public int getIsIndex() {
            return isIndex;
        }

        public void setIsIndex(int isIndex) {
            this.isIndex = isIndex;
        }

        public int getIsCollection() {
            return isCollection;
        }

        public void setIsCollection(int isCollection) {
            this.isCollection = isCollection;
        }

        public ColleInfoBean getColle_info() {
            return colle_info;
        }

        public void setColle_info(ColleInfoBean colle_info) {
            this.colle_info = colle_info;
        }

        public List<String> getPictureArr() {
            return pictureArr;
        }

        public void setPictureArr(List<String> pictureArr) {
            this.pictureArr = pictureArr;
        }

        public List<String> getCharactArr() {
            return charactArr;
        }

        public void setCharactArr(List<String> charactArr) {
            this.charactArr = charactArr;
        }

        public static class ColleInfoBean implements Serializable{
            /**
             * id : 29
             * yUserCollectionId : 724191712410337280
             * userId : 714547022807433216
             * yId : 692341585785913344
             * category : 2
             * createDate : 2020-06-21 09:19:19
             */

            private String id;
            private String yUserCollectionId;
            private String userId;
            private String yId;
            private int category;
            private String createDate;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getYUserCollectionId() {
                return yUserCollectionId;
            }

            public void setYUserCollectionId(String yUserCollectionId) {
                this.yUserCollectionId = yUserCollectionId;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getYId() {
                return yId;
            }

            public void setYId(String yId) {
                this.yId = yId;
            }

            public int getCategory() {
                return category;
            }

            public void setCategory(int category) {
                this.category = category;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }
        }
    }

    public static class StoreTechListBean implements Serializable{
        /**
         * id : 0
         * userId : 0
         * userBalance : 0.0
         * userName :
         * userIntegral : 0
         * yStoreId : 692341585785913344
         * userHash : 7E5194837D4B3BBE125C07F8FA16ED0A
         * headPortrait : /upload/timg.jpg
         * createDate : 2020-05-29 15:03:05
         * techJson :  {"star":"4","working":"1"}
         * tech_info : {"star":4,"working":1}
         */

        private String id;
        private String userId;
        private double userBalance;
        private String userName;
        private String userIntegral;
        private String yStoreId;
        private String userHash;
        private String headPortrait;
        private String createDate;
        private String techJson;
        private TechInfoBean tech_info;

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

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getTechJson() {
            return techJson;
        }

        public void setTechJson(String techJson) {
            this.techJson = techJson;
        }

        public TechInfoBean getTech_info() {
            return tech_info;
        }

        public void setTech_info(TechInfoBean tech_info) {
            this.tech_info = tech_info;
        }

        public static class TechInfoBean implements Serializable{
            /**
             * star : 4
             * working : 1
             */

            private int star;
            private int working;

            public int getStar() {
                return star;
            }

            public void setStar(int star) {
                this.star = star;
            }

            public int getWorking() {
                return working;
            }

            public void setWorking(int working) {
                this.working = working;
            }
        }
    }

    public static class StoreServiceListBean implements Serializable{
        /**
         * id : 1023
         * yStoreServiceId : 692341585785913344
         * yStoreId : 692341585785913344
         * yState : 1
         * lineupSum : 10
         * isSheet : 0
         * yStateValue : 维修
         * sPrice : 100.0
         * pictureStr : /static/img/20200528151327.png
         */

        private String id;
        private String yStoreServiceId;
        private String yStoreId;
        private int yState;
        private int lineupSum;
        private int isSheet;
        private String yStateValue;
        private double sPrice;
        private String pictureStr;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYStoreServiceId() {
            return yStoreServiceId;
        }

        public void setYStoreServiceId(String yStoreServiceId) {
            this.yStoreServiceId = yStoreServiceId;
        }

        public String getYStoreId() {
            return yStoreId;
        }

        public void setYStoreId(String yStoreId) {
            this.yStoreId = yStoreId;
        }

        public int getYState() {
            return yState;
        }

        public void setYState(int yState) {
            this.yState = yState;
        }

        public int getLineupSum() {
            return lineupSum;
        }

        public void setLineupSum(int lineupSum) {
            this.lineupSum = lineupSum;
        }

        public int getIsSheet() {
            return isSheet;
        }

        public void setIsSheet(int isSheet) {
            this.isSheet = isSheet;
        }

        public String getYStateValue() {
            return yStateValue;
        }

        public void setYStateValue(String yStateValue) {
            this.yStateValue = yStateValue;
        }

        public double getSPrice() {
            return sPrice;
        }

        public void setSPrice(double sPrice) {
            this.sPrice = sPrice;
        }

        public String getPictureStr() {
            return pictureStr;
        }

        public void setPictureStr(String pictureStr) {
            this.pictureStr = pictureStr;
        }
    }

    public static class KfUserInfoBean {
        /**
         * userId : 714547022807433216
         * userBalance : 0.0
         * yStoreId : 714547022807433216
         * userName : 阿斯顿马丁
         * userAccount : Y11P3D
         * uToken : 878FFBB91EB36C913E76875CFB338E035AC9046CF8BD6A4D568B2A1A8A79ACDE
         * isTechn : 0
         * userHash : 36635C17B0E19DD1E14A92D8895B47CD
         * headPortrait : /upload/2020-06-13/20200613085845_638405.jpeg
         * userPhone : 18306043086
         * userJson : {"birthday":"2002-07-13","u_gender":"男"}
         * techJson :  {"star":"4","working":"1"}
         * isAuth : 1
         * isKf : 0
         */

        private String userId;
        private double userBalance;
        private String yStoreId;
        private String userName;
        private String userAccount;
        private String uToken;
        private int isTechn;
        private String userHash;
        private String headPortrait;
        private String userPhone;
        private String userJson;
        private String techJson;
        private int isAuth;
        private int isKf;

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

        public String getYStoreId() {
            return yStoreId;
        }

        public void setYStoreId(String yStoreId) {
            this.yStoreId = yStoreId;
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

        public String getUToken() {
            return uToken;
        }

        public void setUToken(String uToken) {
            this.uToken = uToken;
        }

        public int getIsTechn() {
            return isTechn;
        }

        public void setIsTechn(int isTechn) {
            this.isTechn = isTechn;
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

        public String getUserJson() {
            return userJson;
        }

        public void setUserJson(String userJson) {
            this.userJson = userJson;
        }

        public String getTechJson() {
            return techJson;
        }

        public void setTechJson(String techJson) {
            this.techJson = techJson;
        }

        public int getIsAuth() {
            return isAuth;
        }

        public void setIsAuth(int isAuth) {
            this.isAuth = isAuth;
        }

        public int getIsKf() {
            return isKf;
        }

        public void setIsKf(int isKf) {
            this.isKf = isKf;
        }
    }
}
