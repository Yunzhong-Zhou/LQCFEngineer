package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/7/2.
 */
public class ServiceListModel_Store implements Serializable {
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
         * yStoreServiceId : 692341585785913353
         * yStoreId : 692341585785913344
         * yState : 1
         * lineupSum : 10
         * isSheet : 1
         * yStateValue : 右前门
         * sPrice : 100.0
         * pictureStr : /upload/2020-06-02/20200602102222_621580.png
         * clist : []
         */

        private String id;
        private String yStoreServiceId;
        private String yStoreId;
        private int yState;
        private int lineupSum;
        private int isSheet;
        private String yStateValue;
        private double sPrice;
        private String pictureStr;
        private List<?> clist;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYStoreServiceId() {
            return yStoreServiceId;
        }

        public void setYStoreServiceId(String yStoreServiceId) {
            this.yStoreServiceId = yStoreServiceId;
        }

        public String getYStoreId() {
            return yStoreId;
        }

        public void setYStoreId(String yStoreId) {
            this.yStoreId = yStoreId;
        }

        public int getYState() {
            return yState;
        }

        public void setYState(int yState) {
            this.yState = yState;
        }

        public int getLineupSum() {
            return lineupSum;
        }

        public void setLineupSum(int lineupSum) {
            this.lineupSum = lineupSum;
        }

        public int getIsSheet() {
            return isSheet;
        }

        public void setIsSheet(int isSheet) {
            this.isSheet = isSheet;
        }

        public String getYStateValue() {
            return yStateValue;
        }

        public void setYStateValue(String yStateValue) {
            this.yStateValue = yStateValue;
        }

        public double getSPrice() {
            return sPrice;
        }

        public void setSPrice(double sPrice) {
            this.sPrice = sPrice;
        }

        public String getPictureStr() {
            return pictureStr;
        }

        public void setPictureStr(String pictureStr) {
            this.pictureStr = pictureStr;
        }

        public List<?> getClist() {
            return clist;
        }

        public void setClist(List<?> clist) {
            this.clist = clist;
        }
    }
}
