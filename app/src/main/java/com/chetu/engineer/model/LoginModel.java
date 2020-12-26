package com.chetu.engineer.model;

import java.io.Serializable;

/**
 * Created by zyz on 2020-05-23.
 */
public class LoginModel implements Serializable {
    /**
     * user_info : {"userId":"719539276219416576","userBalance":0,"yStoreId":"692341585785913345","userName":"阿斯顿马丁","userAccount":"THANU4","uToken":"5DE553AD790C51A2C63CF23D5B4D9A729BEED37928799C7CBFD76C8EF173E22C","isTechn":1,"userIntegral":"0","userHash":"843B1049032A7E7C9F7D2E2CBE6D37A6","headPortrait":"/upload/2020-06-18/20200618164348_110881.jpg","userPhone":"18306043085","userJson":"{\"birthday\":\"1990-04-04\",\"personal\":\"介绍\",\"u_gender\":\"男\"}","techJson":"{\"star\":0,\"working\":1}","authJson":"{\"identity_back\":\"/upload/2020-06-18/20200618202429_524181.jpg\",\"identity_card\":\"422577445776457643\",\"identity_positive\":\"/upload/2020-06-18/20200618202350_845918.jpg\",\"real_name\":\"阿斯顿马丁\"}","isAuth":1,"auth_info":{"real_name":"阿斯顿马丁","identity_card":"422577445776457643","identity_positive":"/upload/2020-06-18/20200618202350_845918.jpg","identity_back":"/upload/2020-06-18/20200618202429_524181.jpg"},"setup_info":{"u_gender":"男","birthday":"1990-04-04","personal":"介绍"},"tech_info":{"star":0,"working":1},"store_info":{"id":"1018","yStoreId":"692341585785913345","userId":"719539276219416576","vName":"维修商店测试","review":"4.5","keywsr":"技术 修车 实","address":"凤凰山附近","longitude":"116.49798","latitude":"39.916485","phone":"  400-2333-1123","vLevel":"A级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"/upload/store/222.png||/upload/store/222.png","pictureStr":"/upload/store/222.png||/upload/store/222.png","slogan":"维修商店测试 美容首单50","isIndex":1},"isPickup":1,"user_sedan_info":{}}
     */

    private UserInfoBean user_info;

    public UserInfoBean getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfoBean user_info) {
        this.user_info = user_info;
    }

    public static class UserInfoBean {
        /**
         * userId : 719539276219416576
         * userBalance : 0.0
         * yStoreId : 692341585785913345
         * userName : 阿斯顿马丁
         * userAccount : THANU4
         * uToken : 5DE553AD790C51A2C63CF23D5B4D9A729BEED37928799C7CBFD76C8EF173E22C
         * isTechn : 1
         * userIntegral : 0
         * userHash : 843B1049032A7E7C9F7D2E2CBE6D37A6
         * headPortrait : /upload/2020-06-18/20200618164348_110881.jpg
         * userPhone : 18306043085
         * userJson : {"birthday":"1990-04-04","personal":"介绍","u_gender":"男"}
         * techJson : {"star":0,"working":1}
         * authJson : {"identity_back":"/upload/2020-06-18/20200618202429_524181.jpg","identity_card":"422577445776457643","identity_positive":"/upload/2020-06-18/20200618202350_845918.jpg","real_name":"阿斯顿马丁"}
         * isAuth : 1
         * auth_info : {"real_name":"阿斯顿马丁","identity_card":"422577445776457643","identity_positive":"/upload/2020-06-18/20200618202350_845918.jpg","identity_back":"/upload/2020-06-18/20200618202429_524181.jpg"}
         * setup_info : {"u_gender":"男","birthday":"1990-04-04","personal":"介绍"}
         * tech_info : {"star":0,"working":1}
         * store_info : {"id":"1018","yStoreId":"692341585785913345","userId":"719539276219416576","vName":"维修商店测试","review":"4.5","keywsr":"技术 修车 实","address":"凤凰山附近","longitude":"116.49798","latitude":"39.916485","phone":"  400-2333-1123","vLevel":"A级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"/upload/store/222.png||/upload/store/222.png","pictureStr":"/upload/store/222.png||/upload/store/222.png","slogan":"维修商店测试 美容首单50","isIndex":1}
         * isPickup : 1
         * user_sedan_info : {}
         */

        private String userId;
        private double userBalance;
        private String yStoreId;
        private String userName;
        private String userAccount;
        private String uToken;
        private int isTechn;
        private String userIntegral;
        private String userHash;
        private String headPortrait;
        private String userPhone;
        private String userJson;
        private String techJson;
        private String authJson;
        private int isAuth;
        private AuthInfoBean auth_info;
        private SetupInfoBean setup_info;
        private TechInfoBean tech_info;
        private StoreInfoBean store_info;
        private int isPickup;
        private int isDistri;
        private UserSedanInfoBean user_sedan_info;

        public int getIsDistri() {
            return isDistri;
        }

        public void setIsDistri(int isDistri) {
            this.isDistri = isDistri;
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

        public String getUserIntegral() {
            return userIntegral;
        }

        public void setUserIntegral(String userIntegral) {
            this.userIntegral = userIntegral;
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

        public String getAuthJson() {
            return authJson;
        }

        public void setAuthJson(String authJson) {
            this.authJson = authJson;
        }

        public int getIsAuth() {
            return isAuth;
        }

        public void setIsAuth(int isAuth) {
            this.isAuth = isAuth;
        }

        public AuthInfoBean getAuth_info() {
            return auth_info;
        }

        public void setAuth_info(AuthInfoBean auth_info) {
            this.auth_info = auth_info;
        }

        public SetupInfoBean getSetup_info() {
            return setup_info;
        }

        public void setSetup_info(SetupInfoBean setup_info) {
            this.setup_info = setup_info;
        }

        public TechInfoBean getTech_info() {
            return tech_info;
        }

        public void setTech_info(TechInfoBean tech_info) {
            this.tech_info = tech_info;
        }

        public StoreInfoBean getStore_info() {
            return store_info;
        }

        public void setStore_info(StoreInfoBean store_info) {
            this.store_info = store_info;
        }

        public int getIsPickup() {
            return isPickup;
        }

        public void setIsPickup(int isPickup) {
            this.isPickup = isPickup;
        }

        public UserSedanInfoBean getUser_sedan_info() {
            return user_sedan_info;
        }

        public void setUser_sedan_info(UserSedanInfoBean user_sedan_info) {
            this.user_sedan_info = user_sedan_info;
        }

        public static class AuthInfoBean {
            /**
             * real_name : 阿斯顿马丁
             * identity_card : 422577445776457643
             * identity_positive : /upload/2020-06-18/20200618202350_845918.jpg
             * identity_back : /upload/2020-06-18/20200618202429_524181.jpg
             */

            private String real_name;
            private String identity_card;
            private String identity_positive;
            private String identity_back;

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }

            public String getIdentity_card() {
                return identity_card;
            }

            public void setIdentity_card(String identity_card) {
                this.identity_card = identity_card;
            }

            public String getIdentity_positive() {
                return identity_positive;
            }

            public void setIdentity_positive(String identity_positive) {
                this.identity_positive = identity_positive;
            }

            public String getIdentity_back() {
                return identity_back;
            }

            public void setIdentity_back(String identity_back) {
                this.identity_back = identity_back;
            }
        }

        public static class SetupInfoBean {
            /**
             * u_gender : 男
             * birthday : 1990-04-04
             * personal : 介绍
             */

            private String u_gender;
            private String birthday;
            private String personal;

            public String getU_gender() {
                return u_gender;
            }

            public void setU_gender(String u_gender) {
                this.u_gender = u_gender;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getPersonal() {
                return personal;
            }

            public void setPersonal(String personal) {
                this.personal = personal;
            }
        }

        public static class TechInfoBean {
            /**
             * star : 0
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

        public static class StoreInfoBean {
        }

        public static class UserSedanInfoBean {
        }
    }
}
