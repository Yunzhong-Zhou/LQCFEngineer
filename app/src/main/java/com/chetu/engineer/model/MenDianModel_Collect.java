package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/5/26.
 */
public class MenDianModel_Collect implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * yUserCollectionId : 723471994321895424
         * userId : 714547022807433216
         * yId : 692341585785913350
         * category : 2
         * createDate : 2020-06-19 09:39:24
         * store_info : {"yStoreId":"692341585785913350","vName":"东莞清溪公园店","review":"8分","keywsr":"保养 修车 服务","address":"深圳市布沙路59号","longitude":"116.49798","latitude":"39.916485","picture":"/upload/store/222.png","phone":"  400-2333-1123","vLevel":"B级","introduce":"听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。","charactStr":"/upload/store/222.png||/upload/store/222.png","pictureStr":"/upload/store/222.png||/upload/store/222.png"}
         */

        private String yUserCollectionId;
        private String userId;
        private String yId;
        private int category;
        private String createDate;
        private StoreInfoBean store_info;

        public String getYUserCollectionId() {
            return yUserCollectionId;
        }

        public void setYUserCollectionId(String yUserCollectionId) {
            this.yUserCollectionId = yUserCollectionId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getYId() {
            return yId;
        }

        public void setYId(String yId) {
            this.yId = yId;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public StoreInfoBean getStore_info() {
            return store_info;
        }

        public void setStore_info(StoreInfoBean store_info) {
            this.store_info = store_info;
        }

        public static class StoreInfoBean {
            /**
             * yStoreId : 692341585785913350
             * vName : 东莞清溪公园店
             * review : 8分
             * keywsr : 保养 修车 服务
             * address : 深圳市布沙路59号
             * longitude : 116.49798
             * latitude : 39.916485
             * picture : /upload/store/222.png
             * phone :   400-2333-1123
             * vLevel : B级
             * introduce : 听广告几年了，终于体验了特色服务。轮胎是其专长。听说11年开始，线上业务。16年开始线下。现在全国1500多家店。
             * charactStr : /upload/store/222.png||/upload/store/222.png
             * pictureStr : /upload/store/222.png||/upload/store/222.png
             */

            private String yStoreId;
            private String vName;
            private String review;
            private String keywsr;
            private String address;
            private String longitude;
            private String latitude;
            private String picture;
            private String phone;
            private String vLevel;
            private String introduce;
            private String charactStr;
            private String pictureStr;

            public String getYStoreId() {
                return yStoreId;
            }

            public void setYStoreId(String yStoreId) {
                this.yStoreId = yStoreId;
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

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
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

            public String getPictureStr() {
                return pictureStr;
            }

            public void setPictureStr(String pictureStr) {
                this.pictureStr = pictureStr;
            }
        }
    }
}
