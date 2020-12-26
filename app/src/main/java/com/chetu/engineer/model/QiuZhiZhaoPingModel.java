package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/23.
 */
public class QiuZhiZhaoPingModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1050
         * yForumPostsId : 719500078963752960
         * userId : 20180531104813
         * v_title : position
         * iClassify : 1
         * iGive : 200
         * iLike : 200
         * vJson :
         * createDate : 2020-06-08 10:36:26
         * recruit_info : {"position":"position","nameStore":"namestore","telephone":"telephone","salary":"salary","handsOn":"","address":"address"}
         * user_info : {"id":"0","userId":"20180531104813","userBalance":0,"userName":"龙肆","userIntegral":"0","yStoreId":"0","headPortrait":" ","userPhone":"18203048656"}
         */

        private String id;
        private String yForumPostsId;
        private String userId;
        private String v_title;
        private int iClassify;
        private String iGive;
        private String iLike;
        private String vJson;
        private String createDate;
        private RecruitInfoBean recruit_info;
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

        public String getIGive() {
            return iGive;
        }

        public void setIGive(String iGive) {
            this.iGive = iGive;
        }

        public String getILike() {
            return iLike;
        }

        public void setILike(String iLike) {
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

        public static class RecruitInfoBean {
            /**
             * position : position
             * nameStore : namestore
             * telephone : telephone
             * salary : salary
             * handsOn :
             * address : address
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
