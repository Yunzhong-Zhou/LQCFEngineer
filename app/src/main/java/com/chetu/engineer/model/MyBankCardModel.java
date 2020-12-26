package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/21.
 */
public class MyBankCardModel implements Serializable {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1044
         * yWithAccountId : 724354577662476288
         * userId : 719539276219416576
         * vAccount : 6217 5674 1688 2561 774
         * vBank : 中国银行
         * vUsName : 阿斯顿
         * vCay : 借记卡
         * createDate : 2020-06-21 20:06:29
         */

        private String id;
        private String yWithAccountId;
        private String userId;
        private String vAccount;
        private String vBank;
        private String vUsName;
        private String vCay;
        private String createDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYWithAccountId() {
            return yWithAccountId;
        }

        public void setYWithAccountId(String yWithAccountId) {
            this.yWithAccountId = yWithAccountId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getVAccount() {
            return vAccount;
        }

        public void setVAccount(String vAccount) {
            this.vAccount = vAccount;
        }

        public String getVBank() {
            return vBank;
        }

        public void setVBank(String vBank) {
            this.vBank = vBank;
        }

        public String getVUsName() {
            return vUsName;
        }

        public void setVUsName(String vUsName) {
            this.vUsName = vUsName;
        }

        public String getVCay() {
            return vCay;
        }

        public void setVCay(String vCay) {
            this.vCay = vCay;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
