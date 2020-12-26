package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/23.
 */
public class DianPuChuZuModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1064
         * yForumPostsId : 724314451532054528
         * userId : 20180531104813
         * v_title : Eqweqw
         * iClassify : 3
         * iGive : 0
         * iLike : 0
         * vJson :
         * createDate : 2020-06-21 17:27:02
         * lease_info : {"v_title":"Eqweqw","contacts":"Weewrew","contact_infor":"13213123","v_cost":"32132","v_address":"3rwerew","v_text":"Grgfdgdfgdfg","imgstr":"/upload/2020-06-21/20200621172702_290517.jpg","imgArr":["/upload/2020-06-21/20200621172702_290517.jpg"]}
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
        private LeaseInfoBean lease_info;
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

        public LeaseInfoBean getLease_info() {
            return lease_info;
        }

        public void setLease_info(LeaseInfoBean lease_info) {
            this.lease_info = lease_info;
        }

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public static class LeaseInfoBean {
            /**
             * v_title : Eqweqw
             * contacts : Weewrew
             * contact_infor : 13213123
             * v_cost : 32132
             * v_address : 3rwerew
             * v_text : Grgfdgdfgdfg
             * imgstr : /upload/2020-06-21/20200621172702_290517.jpg
             * imgArr : ["/upload/2020-06-21/20200621172702_290517.jpg"]
             */

            private String v_title;
            private String contacts;
            private String contact_infor;
            private String v_cost;
            private String v_address;
            private String v_text;
            private String imgstr;
            private List<String> imgArr;

            public String getV_title() {
                return v_title;
            }

            public void setV_title(String v_title) {
                this.v_title = v_title;
            }

            public String getContacts() {
                return contacts;
            }

            public void setContacts(String contacts) {
                this.contacts = contacts;
            }

            public String getContact_infor() {
                return contact_infor;
            }

            public void setContact_infor(String contact_infor) {
                this.contact_infor = contact_infor;
            }

            public String getV_cost() {
                return v_cost;
            }

            public void setV_cost(String v_cost) {
                this.v_cost = v_cost;
            }

            public String getV_address() {
                return v_address;
            }

            public void setV_address(String v_address) {
                this.v_address = v_address;
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
