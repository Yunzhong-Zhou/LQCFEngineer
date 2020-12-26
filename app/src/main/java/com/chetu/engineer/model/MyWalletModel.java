package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/21.
 */
public class MyWalletModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 129
         * yBillId : 694545532487467008
         * userId : 719539276219416576
         * money : 0.0
         * reMoney : 12.0
         * isIntegral : 1
         * category : 9
         * msg : 消费赠送
         * nature : 2
         * createDate : 2020-03-31 13:55:58
         */

        private String id;
        private String yBillId;
        private String userId;
        private double money;
        private double reMoney;
        private int isIntegral;
        private int category;
        private String msg;
        private int nature;
        private String createDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYBillId() {
            return yBillId;
        }

        public void setYBillId(String yBillId) {
            this.yBillId = yBillId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public double getReMoney() {
            return reMoney;
        }

        public void setReMoney(double reMoney) {
            this.reMoney = reMoney;
        }

        public int getIsIntegral() {
            return isIntegral;
        }

        public void setIsIntegral(int isIntegral) {
            this.isIntegral = isIntegral;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getNature() {
            return nature;
        }

        public void setNature(int nature) {
            this.nature = nature;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
