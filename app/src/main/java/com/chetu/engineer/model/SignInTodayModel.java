package com.chetu.engineer.model;

import java.io.Serializable;

/**
 * Created by Mr.X on 2020/7/11.
 */
public class SignInTodayModel implements Serializable {
    /**
     * signin_info : {"id":"825","ySigninId":"731468197965856768","userId":"719539276219416576","workTime":"11:13","closingTime":"11:14","weekDay":"星期六","createDate":"2020-07-11"}
     */

    private SigninInfoBean signin_info;

    public SigninInfoBean getSignin_info() {
        return signin_info;
    }

    public void setSignin_info(SigninInfoBean signin_info) {
        this.signin_info = signin_info;
    }

    public static class SigninInfoBean {
        /**
         * id : 825
         * ySigninId : 731468197965856768
         * userId : 719539276219416576
         * workTime : 11:13
         * closingTime : 11:14
         * weekDay : 星期六
         * createDate : 2020-07-11
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
