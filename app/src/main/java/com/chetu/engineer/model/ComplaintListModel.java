package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/19.
 */
public class ComplaintListModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * yComplaintId : 719289104528834560
         * yOrderId : 717682411411865600
         * yStoreId : 692341585785913344
         * parentId : 0
         * userId : 20180531104813
         * bUserId : 663341570698772480
         * vTitle : 发动机没修好
         * imgstr : /static/img/20200528151327.png||/static/img/20200528151327.png
         * vState : 0
         * createDate : 2020-06-07 20:38:06
         * user_info : {"id":"0","userId":"20180531104813","userBalance":0,"userName":"龙肆","userIntegral":"0","yStoreId":"0","userHash":"3B2372646663FDC7A81EA8E241CB7946AC74E4C0C9CFD31F750FEE2831528FF5","headPortrait":"/upload/2020-07-12/20200712101732_309412.jpg","userPhone":"18203048656"}
         */

        private String yComplaintId;
        private String yOrderId;
        private String yStoreId;
        private String parentId;
        private String userId;
        private String bUserId;
        private String vTitle;
        private String imgstr;
        private int vState;
        private String createDate;
        private UserInfoBean user_info;

        public String getYComplaintId() {
            return yComplaintId;
        }

        public void setYComplaintId(String yComplaintId) {
            this.yComplaintId = yComplaintId;
        }

        public String getYOrderId() {
            return yOrderId;
        }

        public void setYOrderId(String yOrderId) {
            this.yOrderId = yOrderId;
        }

        public String getYStoreId() {
            return yStoreId;
        }

        public void setYStoreId(String yStoreId) {
            this.yStoreId = yStoreId;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getBUserId() {
            return bUserId;
        }

        public void setBUserId(String bUserId) {
            this.bUserId = bUserId;
        }

        public String getVTitle() {
            return vTitle;
        }

        public void setVTitle(String vTitle) {
            this.vTitle = vTitle;
        }

        public String getImgstr() {
            return imgstr;
        }

        public void setImgstr(String imgstr) {
            this.imgstr = imgstr;
        }

        public int getVState() {
            return vState;
        }

        public void setVState(int vState) {
            this.vState = vState;
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
