package com.chetu.engineer.model;

import java.io.Serializable;

/**
 * Created by Mr.X on 2020/7/12.
 */
public class FristAppModel implements Serializable {
    /**
     * IMGURL : http://frb.yxy5g.com/
     * conf_info : {"id":"1","hHeadPortrait":"15/20190915101837_989190.jpg","kImgUrl":"/upload/2019-09-15/20190915101837_989190.jpg","kf_user_id":"718774820086480896","kf_info":{"userId":"718774820086480896","userBalance":0,"yStoreId":"692341585785913344","userName":"xxx","userAccount":"RF9AL0","uToken":"95834D0BA5DAAA051EC7227B2FA66DE0392A2F9ED124E9899FBDF8B04FF667AC","isTechn":1,"userIntegral":"0","userHash":"5F71987EBBC44C9ADCB4578D1ED034A4","headPortrait":"/upload/timg.jpg","userPhone":"18203048659","userJson":"{\"birthday\":\"1985.12.25\",\"personal\":\"个人简介\",\"u_gender\":\"男\"}","techJson":"{\"star\":0,\"working\":1}","isAuth":1,"isKf":1,"isPickup":1},"alipay_PRIVATE_KEY":"MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC2Haff+ewirdQDYx0mIKOkRcE6HwZSq0bYJxA10EzOOint/bnIa9nLm1Dh1OtWYvxYMsHD6EPe4icD+sDdKCG6krnl2sfyJF1HbwvbAIt0c5l8ATsrO6HHYzFKTArHmB+BOQa5x66eQFzQ9cOa8Js3DVVgHBd5CWCOObdUoSN7Nhvfc2hs/kiwEH01LGPY/2DDZzvQvYq3vOH5aAFZ82ALU13fFs9DJ5d21rwDiK12ZufnK9MhU8j4TbOoCAPn73EQxX+uyP5Rg0/7gVxp+X449D7IVsN3oyR2aTHRsGYAEHoYoKrI5S1I5ZJhtMAO4qV4UbmqaMU9dJln5u7fWIghAgMBAAECggEAfot+0jiUp8hH8Q6W+55kd0ptKRjTyO/w61PBhNM3+3kZF6FdzfF7RmqnOjU8Tiu8EwE5+QpU1vQojjBYgZRYU4+PEzQCCbTRCEkWYNgxDvsgpLUqSzFFignX/zt1phmDksRwcvlY0egBVsXfZLNZ91gRghYjDVzBtjJOvMS3pe3YJKzT5FO3ppWMo8rcFIegX0SWCY71OsdbKAnSdOUJ3IJ5wdahTnPC0SMb1lIObEk6KuzLFjFFRbkRjTdhsXeVAJil2s1Pof9cgRigOmN85z9QhijvEgv5LFsM7FWqVMYi/q92QcBIfdsCu0mcmwfemC1XxypgYFgLBtmzbBYmDQKBgQDdtnS/Hh3RWreA23d4atf8qwfALOFLj4/feFy+TewG1/F2RSzFBeYQNMGBD9DP85ZdXd9yCFCrrbxOb7ljkIcFZ6U2QMpMseK1nnrp2BUjq/LBgsA5lMD83MXbjZSXU051HyvYjF7JgggzyAq1tE97f7fFBCtzaKBt48GpetjAxwKBgQDSR5IRDCPRcIm2Tc2WdsMaXn2p03Jjbb6xV+2VOudxHgqHARh+5yfl7mrxORD6x89XWeircV6oqTGUfuFpSOcZgwlTbBl49N9mB529FtImRCY0ezQwLudJMsYIGg5bWJ4QtSbg8mP2RKTqIZX7u/Fx9eGDP53/AUPLjR0Oct1X1wKBgC2ZhCN2pz1SwiEfgk/i8a9G4d41nNS0nyugDrsZPa0epsvjaFTRZML6hr3rqc/LUqa7UuSaLzVQE0WylteSdyK2cXVaGAF0KKOgB7mmtppSmCgSo0R0gsi/A3anhOMmwCE4mwT7wdcCBnvX7dP3VM2atAoyVFl1MGHJkSAxYvkrAoGBAI4YKw42A+Y9g/GbNbfdXQ3umEAwUHX77QZuAm/7M/ZudHKyPNlUXtOzfrSu+VhduTCv4tgnbe3PF1/fNXct+mx07dJfk/72iheqQd9LEoslNiP0tVlx+HywQL2szdLQWU+0+ia75Oy/iilktLM9V3TI0LEeRifRtPxRyTbAS+5VAoGACE8MiuEWqko4jqeR7bgpkGGlzyJEPfvzX35twFBiR1+dyBeYCHYIxxYoKUEc7ESNcBSrtyj7pKeB6Bt5jxwvY2Wg9f7is23Yey/SS/TDJGWC8PN2gDoMo6FtVIGOSexDklvabV5THd5CXt1HqJXIh5SYYkLXMYfPkSN1bNk4F2Q=","alipay_PUBLIC_KEY":"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAth2n3/nsIq3UA2MdJiCjpEXBOh8GUqtG2CcQNdBMzjop7f25yGvZy5tQ4dTrVmL8WDLBw+hD3uInA/rA3SghupK55drH8iRdR28L2wCLdHOZfAE7Kzuhx2MxSkwKx5gfgTkGuceunkBc0PXDmvCbNw1VYBwXeQlgjjm3VKEjezYb33NobP5IsBB9NSxj2P9gw2c70L2Kt7zh+WgBWfNgC1Nd3xbPQyeXdta8A4itdmbn5yvTIVPI+E2zqAgD5+9xEMV/rsj+UYNP+4Fcafl+OPQ+yFbDd6Mkdmkx0bBmABB6GKCqyOUtSOWSYbTADuKleFG5qmjFPXSZZ+bu31iIIQIDAQAB","alipay_APPID":"2021001164691212","weChat_APPID":"wxf3169961af7c5e80","weChat_APPSECRET":"99030d94941f2c449b72330128b05ce8","weChat_MCHID":"1574690931","weChat_API_KEY":"RZ3nocGO4LtqleXv1Z859n86ERPVZuK1"}
     */

    private String IMGURL;
    private ConfInfoBean conf_info;

    public String getIMGURL() {
        return IMGURL;
    }

    public void setIMGURL(String IMGURL) {
        this.IMGURL = IMGURL;
    }

    public ConfInfoBean getConf_info() {
        return conf_info;
    }

    public void setConf_info(ConfInfoBean conf_info) {
        this.conf_info = conf_info;
    }

    public static class ConfInfoBean {
        /**
         * id : 1
         * hHeadPortrait : 15/20190915101837_989190.jpg
         * kImgUrl : /upload/2019-09-15/20190915101837_989190.jpg
         * kf_user_id : 718774820086480896
         * kf_info : {"userId":"718774820086480896","userBalance":0,"yStoreId":"692341585785913344","userName":"xxx","userAccount":"RF9AL0","uToken":"95834D0BA5DAAA051EC7227B2FA66DE0392A2F9ED124E9899FBDF8B04FF667AC","isTechn":1,"userIntegral":"0","userHash":"5F71987EBBC44C9ADCB4578D1ED034A4","headPortrait":"/upload/timg.jpg","userPhone":"18203048659","userJson":"{\"birthday\":\"1985.12.25\",\"personal\":\"个人简介\",\"u_gender\":\"男\"}","techJson":"{\"star\":0,\"working\":1}","isAuth":1,"isKf":1,"isPickup":1}
         * alipay_PRIVATE_KEY : MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC2Haff+ewirdQDYx0mIKOkRcE6HwZSq0bYJxA10EzOOint/bnIa9nLm1Dh1OtWYvxYMsHD6EPe4icD+sDdKCG6krnl2sfyJF1HbwvbAIt0c5l8ATsrO6HHYzFKTArHmB+BOQa5x66eQFzQ9cOa8Js3DVVgHBd5CWCOObdUoSN7Nhvfc2hs/kiwEH01LGPY/2DDZzvQvYq3vOH5aAFZ82ALU13fFs9DJ5d21rwDiK12ZufnK9MhU8j4TbOoCAPn73EQxX+uyP5Rg0/7gVxp+X449D7IVsN3oyR2aTHRsGYAEHoYoKrI5S1I5ZJhtMAO4qV4UbmqaMU9dJln5u7fWIghAgMBAAECggEAfot+0jiUp8hH8Q6W+55kd0ptKRjTyO/w61PBhNM3+3kZF6FdzfF7RmqnOjU8Tiu8EwE5+QpU1vQojjBYgZRYU4+PEzQCCbTRCEkWYNgxDvsgpLUqSzFFignX/zt1phmDksRwcvlY0egBVsXfZLNZ91gRghYjDVzBtjJOvMS3pe3YJKzT5FO3ppWMo8rcFIegX0SWCY71OsdbKAnSdOUJ3IJ5wdahTnPC0SMb1lIObEk6KuzLFjFFRbkRjTdhsXeVAJil2s1Pof9cgRigOmN85z9QhijvEgv5LFsM7FWqVMYi/q92QcBIfdsCu0mcmwfemC1XxypgYFgLBtmzbBYmDQKBgQDdtnS/Hh3RWreA23d4atf8qwfALOFLj4/feFy+TewG1/F2RSzFBeYQNMGBD9DP85ZdXd9yCFCrrbxOb7ljkIcFZ6U2QMpMseK1nnrp2BUjq/LBgsA5lMD83MXbjZSXU051HyvYjF7JgggzyAq1tE97f7fFBCtzaKBt48GpetjAxwKBgQDSR5IRDCPRcIm2Tc2WdsMaXn2p03Jjbb6xV+2VOudxHgqHARh+5yfl7mrxORD6x89XWeircV6oqTGUfuFpSOcZgwlTbBl49N9mB529FtImRCY0ezQwLudJMsYIGg5bWJ4QtSbg8mP2RKTqIZX7u/Fx9eGDP53/AUPLjR0Oct1X1wKBgC2ZhCN2pz1SwiEfgk/i8a9G4d41nNS0nyugDrsZPa0epsvjaFTRZML6hr3rqc/LUqa7UuSaLzVQE0WylteSdyK2cXVaGAF0KKOgB7mmtppSmCgSo0R0gsi/A3anhOMmwCE4mwT7wdcCBnvX7dP3VM2atAoyVFl1MGHJkSAxYvkrAoGBAI4YKw42A+Y9g/GbNbfdXQ3umEAwUHX77QZuAm/7M/ZudHKyPNlUXtOzfrSu+VhduTCv4tgnbe3PF1/fNXct+mx07dJfk/72iheqQd9LEoslNiP0tVlx+HywQL2szdLQWU+0+ia75Oy/iilktLM9V3TI0LEeRifRtPxRyTbAS+5VAoGACE8MiuEWqko4jqeR7bgpkGGlzyJEPfvzX35twFBiR1+dyBeYCHYIxxYoKUEc7ESNcBSrtyj7pKeB6Bt5jxwvY2Wg9f7is23Yey/SS/TDJGWC8PN2gDoMo6FtVIGOSexDklvabV5THd5CXt1HqJXIh5SYYkLXMYfPkSN1bNk4F2Q=
         * alipay_PUBLIC_KEY : MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAth2n3/nsIq3UA2MdJiCjpEXBOh8GUqtG2CcQNdBMzjop7f25yGvZy5tQ4dTrVmL8WDLBw+hD3uInA/rA3SghupK55drH8iRdR28L2wCLdHOZfAE7Kzuhx2MxSkwKx5gfgTkGuceunkBc0PXDmvCbNw1VYBwXeQlgjjm3VKEjezYb33NobP5IsBB9NSxj2P9gw2c70L2Kt7zh+WgBWfNgC1Nd3xbPQyeXdta8A4itdmbn5yvTIVPI+E2zqAgD5+9xEMV/rsj+UYNP+4Fcafl+OPQ+yFbDd6Mkdmkx0bBmABB6GKCqyOUtSOWSYbTADuKleFG5qmjFPXSZZ+bu31iIIQIDAQAB
         * alipay_APPID : 2021001164691212
         * weChat_APPID : wxf3169961af7c5e80
         * weChat_APPSECRET : 99030d94941f2c449b72330128b05ce8
         * weChat_MCHID : 1574690931
         * weChat_API_KEY : RZ3nocGO4LtqleXv1Z859n86ERPVZuK1
         */

        private String id;
        private String hHeadPortrait;
        private String kImgUrl;
        private String kf_user_id;
        private KfInfoBean kf_info;
        private String alipay_PRIVATE_KEY;
        private String alipay_PUBLIC_KEY;
        private String alipay_APPID;
        private String weChat_APPID;
        private String weChat_APPSECRET;
        private String weChat_MCHID;
        private String weChat_API_KEY;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getHHeadPortrait() {
            return hHeadPortrait;
        }

        public void setHHeadPortrait(String hHeadPortrait) {
            this.hHeadPortrait = hHeadPortrait;
        }

        public String getKImgUrl() {
            return kImgUrl;
        }

        public void setKImgUrl(String kImgUrl) {
            this.kImgUrl = kImgUrl;
        }

        public String getKf_user_id() {
            return kf_user_id;
        }

        public void setKf_user_id(String kf_user_id) {
            this.kf_user_id = kf_user_id;
        }

        public KfInfoBean getKf_info() {
            return kf_info;
        }

        public void setKf_info(KfInfoBean kf_info) {
            this.kf_info = kf_info;
        }

        public String getAlipay_PRIVATE_KEY() {
            return alipay_PRIVATE_KEY;
        }

        public void setAlipay_PRIVATE_KEY(String alipay_PRIVATE_KEY) {
            this.alipay_PRIVATE_KEY = alipay_PRIVATE_KEY;
        }

        public String getAlipay_PUBLIC_KEY() {
            return alipay_PUBLIC_KEY;
        }

        public void setAlipay_PUBLIC_KEY(String alipay_PUBLIC_KEY) {
            this.alipay_PUBLIC_KEY = alipay_PUBLIC_KEY;
        }

        public String getAlipay_APPID() {
            return alipay_APPID;
        }

        public void setAlipay_APPID(String alipay_APPID) {
            this.alipay_APPID = alipay_APPID;
        }

        public String getWeChat_APPID() {
            return weChat_APPID;
        }

        public void setWeChat_APPID(String weChat_APPID) {
            this.weChat_APPID = weChat_APPID;
        }

        public String getWeChat_APPSECRET() {
            return weChat_APPSECRET;
        }

        public void setWeChat_APPSECRET(String weChat_APPSECRET) {
            this.weChat_APPSECRET = weChat_APPSECRET;
        }

        public String getWeChat_MCHID() {
            return weChat_MCHID;
        }

        public void setWeChat_MCHID(String weChat_MCHID) {
            this.weChat_MCHID = weChat_MCHID;
        }

        public String getWeChat_API_KEY() {
            return weChat_API_KEY;
        }

        public void setWeChat_API_KEY(String weChat_API_KEY) {
            this.weChat_API_KEY = weChat_API_KEY;
        }

        public static class KfInfoBean {
            /**
             * userId : 718774820086480896
             * userBalance : 0.0
             * yStoreId : 692341585785913344
             * userName : xxx
             * userAccount : RF9AL0
             * uToken : 95834D0BA5DAAA051EC7227B2FA66DE0392A2F9ED124E9899FBDF8B04FF667AC
             * isTechn : 1
             * userIntegral : 0
             * userHash : 5F71987EBBC44C9ADCB4578D1ED034A4
             * headPortrait : /upload/timg.jpg
             * userPhone : 18203048659
             * userJson : {"birthday":"1985.12.25","personal":"个人简介","u_gender":"男"}
             * techJson : {"star":0,"working":1}
             * isAuth : 1
             * isKf : 1
             * isPickup : 1
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
            private int isAuth;
            private int isKf;
            private int isPickup;

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

            public int getIsPickup() {
                return isPickup;
            }

            public void setIsPickup(int isPickup) {
                this.isPickup = isPickup;
            }
        }
    }
}
