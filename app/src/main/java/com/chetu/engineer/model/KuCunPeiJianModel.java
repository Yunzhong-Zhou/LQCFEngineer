package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/23.
 */
public class KuCunPeiJianModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1053
         * yForumPostsId : 719500115466780672
         * userId : 20180531104813
         * v_title : 标题
         * iClassify : 4
         * iGive : 200
         * iLike : 200
         * vJson :
         * createDate : 2020-06-08 10:36:35
         * parts_info : {"v_title":"标题","v_text":"转租","v_bring":"","v_price":"1000","imgstr":" /static/goods/20200527150132.png||/static/goods/20200527150132.png","imgArr":[" /static/goods/20200527150132.png","/static/goods/20200527150132.png"]}
         * user_info : {"id":"0","userId":"20180531104813","userBalance":0,"userName":"龙肆","userIntegral":"0","yStoreId":"0","headPortrait":" ","userPhone":"18203048656"}
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
        private PartsInfoBean parts_info;
        private UserInfoBean user_info;

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

        public PartsInfoBean getParts_info() {
            return parts_info;
        }

        public void setParts_info(PartsInfoBean parts_info) {
            this.parts_info = parts_info;
        }

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public static class PartsInfoBean {
            /**
             * v_title : 标题
             * v_text : 转租
             * v_bring :
             * v_price : 1000
             * imgstr :  /static/goods/20200527150132.png||/static/goods/20200527150132.png
             * imgArr : [" /static/goods/20200527150132.png","/static/goods/20200527150132.png"]
             */

            private String v_title;
            private String v_text;
            private String v_bring;
            private String v_price;
            private String imgstr;
            private List<String> imgArr;

            public String getV_title() {
                return v_title;
            }

            public void setV_title(String v_title) {
                this.v_title = v_title;
            }

            public String getV_text() {
                return v_text;
            }

            public void setV_text(String v_text) {
                this.v_text = v_text;
            }

            public String getV_bring() {
                return v_bring;
            }

            public void setV_bring(String v_bring) {
                this.v_bring = v_bring;
            }

            public String getV_price() {
                return v_price;
            }

            public void setV_price(String v_price) {
                this.v_price = v_price;
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

        public static class UserInfoBean {
            /**
             * id : 0
             * userId : 20180531104813
             * userBalance : 0.0
             * userName : 龙肆
             * userIntegral : 0
             * yStoreId : 0
             * headPortrait :
             * userPhone : 18203048656
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
}
