package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/20.
 */
public class CouponModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1032
         * yUserCouponId : 692341585785913344
         * yStoreId : 692341585785913344
         * yStoreServiceId : 692341585785913344
         * userId : 714547022807433216
         * cMoney : 100.0
         * cMsg : 龙肆店铺使用
         * cTitle : 满100使用
         * cEndTime : 2019-09-27 10:31:19
         * isUse : 1
         * cLimitMoney : 300.0
         * createDate : 2019-09-27 10:31:19
         */

        private String id;
        private String yUserCouponId;
        private String yStoreId;
        private String yStoreServiceId;
        private String userId;
        private String cMoney;
        private String cMsg;
        private String cTitle;
        private String cEndTime;
        private int isUse;
        private String cLimitMoney;
        private String createDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYUserCouponId() {
            return yUserCouponId;
        }

        public void setYUserCouponId(String yUserCouponId) {
            this.yUserCouponId = yUserCouponId;
        }

        public String getYStoreId() {
            return yStoreId;
        }

        public void setYStoreId(String yStoreId) {
            this.yStoreId = yStoreId;
        }

        public String getYStoreServiceId() {
            return yStoreServiceId;
        }

        public void setYStoreServiceId(String yStoreServiceId) {
            this.yStoreServiceId = yStoreServiceId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getCMoney() {
            return cMoney;
        }

        public void setCMoney(String cMoney) {
            this.cMoney = cMoney;
        }

        public String getCMsg() {
            return cMsg;
        }

        public void setCMsg(String cMsg) {
            this.cMsg = cMsg;
        }

        public String getCTitle() {
            return cTitle;
        }

        public void setCTitle(String cTitle) {
            this.cTitle = cTitle;
        }

        public String getCEndTime() {
            return cEndTime;
        }

        public void setCEndTime(String cEndTime) {
            this.cEndTime = cEndTime;
        }

        public int getIsUse() {
            return isUse;
        }

        public void setIsUse(int isUse) {
            this.isUse = isUse;
        }

        public String getCLimitMoney() {
            return cLimitMoney;
        }

        public void setCLimitMoney(String cLimitMoney) {
            this.cLimitMoney = cLimitMoney;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
