package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/7/20.
 */
public class StoreServiceModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1027
         * yStoreServiceId : 692341585785913348
         * yStoreId : 692341585785913344
         * yState : 1
         * lineupSum : 10
         * isSheet : 1
         * yStateValue : 钣喷
         * sPrice : 100.0
         * pictureStr : /upload/server/6876515644458.png
         * clist : [{"id":"1032","yStoreServiceId":"692341585785913353","yStoreId":"692341585785913344","yState":1,"lineupSum":10,"isSheet":1,"yStateValue":"右前门","sPrice":100,"pictureStr":"/upload/2020-06-02/20200602102222_621580.png"},{"id":"1031","yStoreServiceId":"692341585785913352","yStoreId":"692341585785913344","yState":1,"lineupSum":10,"isSheet":1,"yStateValue":"在前门","sPrice":100,"pictureStr":"/upload/2020-06-02/20200602102222_621580.png"},{"id":"1030","yStoreServiceId":"692341585785913351","yStoreId":"692341585785913344","yState":1,"lineupSum":10,"isSheet":1,"yStateValue":"右叶板子","sPrice":100,"pictureStr":"/upload/2020-06-02/20200602102222_621580.png"},{"id":"1029","yStoreServiceId":"692341585785913350","yStoreId":"692341585785913344","yState":1,"lineupSum":10,"isSheet":0,"yStateValue":"机盖","sPrice":100,"pictureStr":"/upload/2020-06-02/20200602102222_621580.png"},{"id":"1028","yStoreServiceId":"692341585785913349","yStoreId":"692341585785913344","yState":1,"lineupSum":10,"isSheet":1,"yStateValue":"左叶板子","sPrice":100,"pictureStr":"/upload/2020-06-02/20200602102222_621580.png"}]
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
        private List<ClistBean> clist;

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

        public List<ClistBean> getClist() {
            return clist;
        }

        public void setClist(List<ClistBean> clist) {
            this.clist = clist;
        }

        public static class ClistBean {
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
             */
            private boolean isgouxuan = false;

            public boolean isIsgouxuan() {
                return isgouxuan;
            }

            public void setIsgouxuan(boolean isgouxuan) {
                this.isgouxuan = isgouxuan;
            }

            private String id;
            private String yStoreServiceId;
            private String yStoreId;
            private int yState;
            private int lineupSum;
            private int isSheet;
            private String yStateValue;
            private double sPrice;
            private String pictureStr;

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
        }
    }
}
