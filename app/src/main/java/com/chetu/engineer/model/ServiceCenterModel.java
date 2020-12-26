package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/6.
 */
public class ServiceCenterModel implements Serializable {
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
         * userName : 龙肆技师
         * uToken : 096DC2A3D177D683E0A124CAA17D7FB54B034F67FF7B53C8A8799B8C0DB14550
         * isKf : 1
         * userIntegral : 0
         * yStoreId : 0
         * headPortrait : /upload/timg.jpg
         */

        private String id;
        private String userId;
        private double userBalance;
        private String userName;
        private String uToken;
        private int isKf;
        private String userIntegral;
        private String yStoreId;
        private String headPortrait;

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

        public String getUToken() {
            return uToken;
        }

        public void setUToken(String uToken) {
            this.uToken = uToken;
        }

        public int getIsKf() {
            return isKf;
        }

        public void setIsKf(int isKf) {
            this.isKf = isKf;
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
    }
}
