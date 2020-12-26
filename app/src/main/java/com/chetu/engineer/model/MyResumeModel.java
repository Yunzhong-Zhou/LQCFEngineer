package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/18.
 */
public class MyResumeModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 14
         * yUserResumeId : 723248925049356288
         * userId : 719539276219416576
         * position : 阿斯顿马丁
         * company : 阿斯顿马丁路德金
         * quitTime : 2020-06-18
         * entryTime : 1983-06-18
         * createDate : 2020-06-18 18:53:01
         * year : 37
         */

        private String id;
        private String yUserResumeId;
        private String userId;
        private String position;
        private String company;
        private String quitTime;
        private String entryTime;
        private String createDate;
        private int year;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYUserResumeId() {
            return yUserResumeId;
        }

        public void setYUserResumeId(String yUserResumeId) {
            this.yUserResumeId = yUserResumeId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getQuitTime() {
            return quitTime;
        }

        public void setQuitTime(String quitTime) {
            this.quitTime = quitTime;
        }

        public String getEntryTime() {
            return entryTime;
        }

        public void setEntryTime(String entryTime) {
            this.entryTime = entryTime;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
    }
}
