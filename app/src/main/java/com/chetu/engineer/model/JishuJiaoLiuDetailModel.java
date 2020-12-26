package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/29.
 */
public class JishuJiaoLiuDetailModel implements Serializable {

    /**
     * info : {"id":"1078","yForumPostsId":"726416857220775936","yCircleId":"0","userId":"719539276219416576","iClassify":7,"iGive":0,"iLike":0,"vJson":"{\"imgstr\":\"/upload/2020-06-27/20200627124113_77409.jpg||/upload/2020-06-27/20200627124113_357598.jpeg||/upload/2020-06-27/20200627124113_52015.jpeg||/upload/2020-06-27/20200627124113_365314.jpg||/upload/2020-06-27/20200627124113_558689.jpg||/upload/2020-06-27/20200627124113_690747.jpeg\",\"v_describe\":\"描述\",\"v_text\":\"正文\",\"v_title\":\"标题\"}","createDate":"2020-06-27 12:41:14","is_give":0,"is_like":0}
     * list : [{"id":"1067","yForumReplyId":"734490890705305600","yForumPostsId":"726416857220775936","userId":"20180531104813","vTitle":"发的发的","createDate":"2020-07-19 19:24:34","user_info":{"id":"0","userId":"20180531104813","userBalance":0,"userName":"龙肆","userIntegral":"0","yStoreId":"0","userHash":"3B2372646663FDC7A81EA8E241CB7946AC74E4C0C9CFD31F750FEE2831528FF5","headPortrait":"/upload/2020-07-12/20200712101732_309412.jpg","userPhone":"18203048656"}}]
     */

    private InfoBean info;
    private List<ListBean> list;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class InfoBean {
        /**
         * id : 1078
         * yForumPostsId : 726416857220775936
         * yCircleId : 0
         * userId : 719539276219416576
         * iClassify : 7
         * iGive : 0
         * iLike : 0
         * vJson : {"imgstr":"/upload/2020-06-27/20200627124113_77409.jpg||/upload/2020-06-27/20200627124113_357598.jpeg||/upload/2020-06-27/20200627124113_52015.jpeg||/upload/2020-06-27/20200627124113_365314.jpg||/upload/2020-06-27/20200627124113_558689.jpg||/upload/2020-06-27/20200627124113_690747.jpeg","v_describe":"描述","v_text":"正文","v_title":"标题"}
         * createDate : 2020-06-27 12:41:14
         * is_give : 0
         * is_like : 0
         */

        private String id;
        private String yForumPostsId;
        private String yCircleId;
        private String userId;
        private int iClassify;
        private int iGive;
        private int iLike;
        private String vJson;
        private String createDate;
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
    }

    public static class ListBean {
        /**
         * id : 1067
         * yForumReplyId : 734490890705305600
         * yForumPostsId : 726416857220775936
         * userId : 20180531104813
         * vTitle : 发的发的
         * createDate : 2020-07-19 19:24:34
         * user_info : {"id":"0","userId":"20180531104813","userBalance":0,"userName":"龙肆","userIntegral":"0","yStoreId":"0","userHash":"3B2372646663FDC7A81EA8E241CB7946AC74E4C0C9CFD31F750FEE2831528FF5","headPortrait":"/upload/2020-07-12/20200712101732_309412.jpg","userPhone":"18203048656"}
         */

        private String id;
        private String yForumReplyId;
        private String yForumPostsId;
        private String userId;
        private String vTitle;
        private String createDate;
        private UserInfoBean user_info;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYForumReplyId() {
            return yForumReplyId;
        }

        public void setYForumReplyId(String yForumReplyId) {
            this.yForumReplyId = yForumReplyId;
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

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public static class UserInfoBean {
            /**
             * id : 0
             * userId : 20180531104813
             * userBalance : 0.0
             * userName : 龙肆
             * userIntegral : 0
             * yStoreId : 0
             * userHash : 3B2372646663FDC7A81EA8E241CB7946AC74E4C0C9CFD31F750FEE2831528FF5
             * headPortrait : /upload/2020-07-12/20200712101732_309412.jpg
             * userPhone : 18203048656
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
    }
}
