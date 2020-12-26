package com.chetu.engineer.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2020/9/19.
 */
public class JiuYuanTypeModel implements Serializable {
    /**
     * state : 1
     * store_info : {"id":"1042","yStoreId":"692341585785913347","userId":"715627408480272384","kfUserId":"715627408480272384","vName":"龙陆商店测试","review":"4.6","keywsr":"技术 修车 实","address":"深圳市光明新区公明街道东环大道松白工业区C区2栋一层106号","longitude":"116.49798","latitude":"39.916485","phone":" 400-2333-1123","vLevel":"C级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"||/upload/2020-07-09/20200709110807_317759.jpg||/upload/2020-07-09/20200709110817_957557.jpg||/upload/2020-07-09/20200709110819_726210.jpg","busHours":"早上9点至晚上7点","pictureStr":"||/upload/2020-07-09/20200709110836_560635.jpg||/upload/2020-07-09/20200709110838_972720.jpg||/upload/2020-07-09/20200709110842_192873.jpg","slogan":"商店测试  洗车首单10","isIndex":1}
     */

    private int state;
    private StoreInfoBean store_info;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public StoreInfoBean getStore_info() {
        return store_info;
    }

    public void setStore_info(StoreInfoBean store_info) {
        this.store_info = store_info;
    }

    public static class StoreInfoBean {
        /**
         * id : 1042
         * yStoreId : 692341585785913347
         * userId : 715627408480272384
         * kfUserId : 715627408480272384
         * vName : 龙陆商店测试
         * review : 4.6
         * keywsr : 技术 修车 实
         * address : 深圳市光明新区公明街道东环大道松白工业区C区2栋一层106号
         * longitude : 116.49798
         * latitude : 39.916485
         * phone :  400-2333-1123
         * vLevel : C级
         * introduce : 听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。
         * charactStr : ||/upload/2020-07-09/20200709110807_317759.jpg||/upload/2020-07-09/20200709110817_957557.jpg||/upload/2020-07-09/20200709110819_726210.jpg
         * busHours : 早上9点至晚上7点
         * pictureStr : ||/upload/2020-07-09/20200709110836_560635.jpg||/upload/2020-07-09/20200709110838_972720.jpg||/upload/2020-07-09/20200709110842_192873.jpg
         * slogan : 商店测试  洗车首单10
         * isIndex : 1
         */

        private String id;
        private String yStoreId;
        private String userId;
        private String kfUserId;
        private String vName;
        private String review;
        private String keywsr;
        private String address;
        private String longitude;
        private String latitude;
        private String phone;
        private String vLevel;
        private String introduce;
        private String charactStr;
        private String busHours;
        private String pictureStr;
        private String slogan;
        private int isIndex;
        private String picture;

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYStoreId() {
            return yStoreId;
        }

        public void setYStoreId(String yStoreId) {
            this.yStoreId = yStoreId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getKfUserId() {
            return kfUserId;
        }

        public void setKfUserId(String kfUserId) {
            this.kfUserId = kfUserId;
        }

        public String getVName() {
            return vName;
        }

        public void setVName(String vName) {
            this.vName = vName;
        }

        public String getReview() {
            return review;
        }

        public void setReview(String review) {
            this.review = review;
        }

        public String getKeywsr() {
            return keywsr;
        }

        public void setKeywsr(String keywsr) {
            this.keywsr = keywsr;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getVLevel() {
            return vLevel;
        }

        public void setVLevel(String vLevel) {
            this.vLevel = vLevel;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getCharactStr() {
            return charactStr;
        }

        public void setCharactStr(String charactStr) {
            this.charactStr = charactStr;
        }

        public String getBusHours() {
            return busHours;
        }

        public void setBusHours(String busHours) {
            this.busHours = busHours;
        }

        public String getPictureStr() {
            return pictureStr;
        }

        public void setPictureStr(String pictureStr) {
            this.pictureStr = pictureStr;
        }

        public String getSlogan() {
            return slogan;
        }

        public void setSlogan(String slogan) {
            this.slogan = slogan;
        }

        public int getIsIndex() {
            return isIndex;
        }

        public void setIsIndex(int isIndex) {
            this.isIndex = isIndex;
        }
    }
}
