package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/7/4.
 */
public class JiShiListModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 0
         * userId : 0
         * userBalance : 0.0
         * userName : 阿斯顿马丁
         * userIntegral : 0
         * yStoreId : 692341585785913344
         * userHash : 843B1049032A7E7C9F7D2E2CBE6D37A6
         * headPortrait : /upload/2020-06-18/20200618164348_110881.jpg
         * createDate : 2020-06-08 13:12:11
         * techJson : {"star":0,"working":1}
         * tech_info : {"star":0,"working":1}
         */

        boolean isXuanZhong = false;

        public boolean isXuanZhong() {
            return isXuanZhong;
        }

        public void setXuanZhong(boolean xuanZhong) {
            isXuanZhong = xuanZhong;
        }

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
    }
}
