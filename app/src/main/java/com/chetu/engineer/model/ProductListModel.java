package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/10.
 */
public class ProductListModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1022
         * yGoodsId : 692341585785913350
         * gName : 滑垫卡扣式座
         * gPrice : 1000.0
         * orPrice : 0.0
         * gDetails :
         * imgStr : /static/img/20200528151327.png||/static/img/20200528151327.png
         * imgArr : ["/static/img/20200528151327.png","/static/img/20200528151327.png"]
         * gImg : /static/img/20200528151327.png
         * gState : 1
         * createDate : 2019-09-27 10:31:19
         * yClassifyId : 692341585785913344
         * yStoreId : 692341585785913344
         */

        private String id;
        private String yGoodsId;
        private String gName;
        private double gPrice;
        private double orPrice;
        private String gDetails;
        private String imgStr;
        private String gImg;
        private int gState;
        private String createDate;
        private String yClassifyId;
        private String yStoreId;
        private List<String> imgArr;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYGoodsId() {
            return yGoodsId;
        }

        public void setYGoodsId(String yGoodsId) {
            this.yGoodsId = yGoodsId;
        }

        public String getGName() {
            return gName;
        }

        public void setGName(String gName) {
            this.gName = gName;
        }

        public double getGPrice() {
            return gPrice;
        }

        public void setGPrice(double gPrice) {
            this.gPrice = gPrice;
        }

        public double getOrPrice() {
            return orPrice;
        }

        public void setOrPrice(double orPrice) {
            this.orPrice = orPrice;
        }

        public String getGDetails() {
            return gDetails;
        }

        public void setGDetails(String gDetails) {
            this.gDetails = gDetails;
        }

        public String getImgStr() {
            return imgStr;
        }

        public void setImgStr(String imgStr) {
            this.imgStr = imgStr;
        }

        public String getGImg() {
            return gImg;
        }

        public void setGImg(String gImg) {
            this.gImg = gImg;
        }

        public int getGState() {
            return gState;
        }

        public void setGState(int gState) {
            this.gState = gState;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getYClassifyId() {
            return yClassifyId;
        }

        public void setYClassifyId(String yClassifyId) {
            this.yClassifyId = yClassifyId;
        }

        public String getYStoreId() {
            return yStoreId;
        }

        public void setYStoreId(String yStoreId) {
            this.yStoreId = yStoreId;
        }

        public List<String> getImgArr() {
            return imgArr;
        }

        public void setImgArr(List<String> imgArr) {
            this.imgArr = imgArr;
        }
    }
}
