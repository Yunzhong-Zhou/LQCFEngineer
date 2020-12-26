package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/23.
 */
public class ReMenHuoDongModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable{
        /**
         * id : 1062
         * yForumPostsId : 719535865868582912
         * userId : 20180531104813
         * v_title : 标题
         * iClassify : 8
         * iGive : 0
         * iLike : 0
         * vJson :
         * createDate : 2020-06-08 12:58:38
         * activity_info : {"v_title":"标题","v_start_time":"2019-09-27 10:31:19","v_end_time":"2019-09-27 10:31:19","v_place":"活动地点","v_type":"活动类型","v_content":"活动内容","imgstr":"/static/goods/20200527150132.png||/static/goods/20200527150132.png","imgArr":["/static/goods/20200527150132.png","/static/goods/20200527150132.png"]}
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
        private ActivityInfoBean activity_info;
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

        public ActivityInfoBean getActivity_info() {
            return activity_info;
        }

        public void setActivity_info(ActivityInfoBean activity_info) {
            this.activity_info = activity_info;
        }

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public static class ActivityInfoBean implements Serializable{
            /**
             * v_title : 标题
             * v_start_time : 2019-09-27 10:31:19
             * v_end_time : 2019-09-27 10:31:19
             * v_place : 活动地点
             * v_type : 活动类型
             * v_content : 活动内容
             * imgstr : /static/goods/20200527150132.png||/static/goods/20200527150132.png
             * imgArr : ["/static/goods/20200527150132.png","/static/goods/20200527150132.png"]
             */

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

        public static class UserInfoBean implements Serializable{
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
