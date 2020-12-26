package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.X on 2020/7/11.
 */
public class SignInListModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 821
         * ySigninId : 727100553120710656
         * userId : 719539276219416576
         * workTime : 09:58
         * closingTime :
         * weekDay : 星期一
         * createDate : 2020-06-29
         */

        private String id;
        private String ySigninId;
        private String userId;
        private String workTime;
        private String closingTime;
        private String weekDay;
        private String createDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYSigninId() {
            return ySigninId;
        }

        public void setYSigninId(String ySigninId) {
            this.ySigninId = ySigninId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getWorkTime() {
            return workTime;
        }

        public void setWorkTime(String workTime) {
            this.workTime = workTime;
        }

        public String getClosingTime() {
            return closingTime;
        }

        public void setClosingTime(String closingTime) {
            this.closingTime = closingTime;
        }

        public String getWeekDay() {
            return weekDay;
        }

        public void setWeekDay(String weekDay) {
            this.weekDay = weekDay;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
