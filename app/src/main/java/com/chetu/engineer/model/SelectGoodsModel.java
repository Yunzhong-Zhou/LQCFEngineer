package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/7/3.
 */
public class SelectGoodsModel implements Serializable {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1018
         * yGoodsId : 692341585785913345
         * yClassifyId : 692341585785913344
         * yStoreId : 692341585785913344
         * gName : 百魅 汽车遮阳挡6件套防晒隔热遮阳板挡阳板加厚前档太阳后挡侧挡
         * gDesc : 适用捷渡凌度360小米家盯盯拍70迈正际行车记录仪降压线停车监控
         * gPrice : 1000.0
         * orPrice : 3000.0
         * imgStr : /upload/pro.png||/upload/pro.png
         * gImg : /upload/pro.png
         * gState : 1
         * isPopular : 1
         * isIntegral : 0
         * gIntegral : 0
         * createDate : 2019-09-27 10:31:19
         * isSoffer : 1
         * specific_list : [{"id":"1030","yGoodsSpecificTypeId":"692341585785913346","yGoodsId":"692341585785913345","sName":"颜色","createDate":"2019-09-27 10:31:19","specific_List":[{"yGoodsSpecificId":"692341585785913355","yGoodsSpecificTypeId":"692341585785913346","yGoodsId":"692341585785913345","pName":"红色","sPrice":90},{"yGoodsSpecificId":"692341585785913354","yGoodsSpecificTypeId":"692341585785913346","yGoodsId":"692341585785913345","pName":"黑色","sPrice":90},{"yGoodsSpecificId":"692341585785913353","yGoodsSpecificTypeId":"692341585785913346","yGoodsId":"692341585785913345","pName":"绿色","sPrice":90}]},{"id":"1031","yGoodsSpecificTypeId":"692341585785913347","yGoodsId":"692341585785913345","sName":"尺寸","createDate":"2019-09-27 10:31:19","specific_List":[{"yGoodsSpecificId":"692341585785913352","yGoodsSpecificTypeId":"692341585785913347","yGoodsId":"692341585785913345","pName":"xxl","sPrice":90},{"yGoodsSpecificId":"692341585785913351","yGoodsSpecificTypeId":"692341585785913347","yGoodsId":"692341585785913345","pName":"xl","sPrice":90},{"yGoodsSpecificId":"692341585785913350","yGoodsSpecificTypeId":"692341585785913347","yGoodsId":"692341585785913345","pName":"ml","sPrice":90}]}]
         */

        private boolean isXuanZhong = false;

        public boolean isXuanZhong() {
            return isXuanZhong;
        }

        public void setXuanZhong(boolean xuanZhong) {
            isXuanZhong = xuanZhong;
        }

        private String id;
        private String yGoodsId;
        private String yClassifyId;
        private String yStoreId;
        private String gName;
        private String gDesc;
        private double gPrice;
        private double orPrice;
        private String imgStr;
        private String gImg;
        private int gState;
        private int isPopular;
        private int isIntegral;
        private int gIntegral;
        private String createDate;
        private int isSoffer;
        private List<SpecificListBeanX> specific_list;

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

        public String getGName() {
            return gName;
        }

        public void setGName(String gName) {
            this.gName = gName;
        }

        public String getGDesc() {
            return gDesc;
        }

        public void setGDesc(String gDesc) {
            this.gDesc = gDesc;
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

        public int getIsPopular() {
            return isPopular;
        }

        public void setIsPopular(int isPopular) {
            this.isPopular = isPopular;
        }

        public int getIsIntegral() {
            return isIntegral;
        }

        public void setIsIntegral(int isIntegral) {
            this.isIntegral = isIntegral;
        }

        public int getGIntegral() {
            return gIntegral;
        }

        public void setGIntegral(int gIntegral) {
            this.gIntegral = gIntegral;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getIsSoffer() {
            return isSoffer;
        }

        public void setIsSoffer(int isSoffer) {
            this.isSoffer = isSoffer;
        }

        public List<SpecificListBeanX> getSpecific_list() {
            return specific_list;
        }

        public void setSpecific_list(List<SpecificListBeanX> specific_list) {
            this.specific_list = specific_list;
        }

        public static class SpecificListBeanX {
            /**
             * id : 1030
             * yGoodsSpecificTypeId : 692341585785913346
             * yGoodsId : 692341585785913345
             * sName : 颜色
             * createDate : 2019-09-27 10:31:19
             * specific_List : [{"yGoodsSpecificId":"692341585785913355","yGoodsSpecificTypeId":"692341585785913346","yGoodsId":"692341585785913345","pName":"红色","sPrice":90},{"yGoodsSpecificId":"692341585785913354","yGoodsSpecificTypeId":"692341585785913346","yGoodsId":"692341585785913345","pName":"黑色","sPrice":90},{"yGoodsSpecificId":"692341585785913353","yGoodsSpecificTypeId":"692341585785913346","yGoodsId":"692341585785913345","pName":"绿色","sPrice":90}]
             */

            private String id;
            private String yGoodsSpecificTypeId;
            private String yGoodsId;
            private String sName;
            private String createDate;
            private List<SpecificListBean> specific_List;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getYGoodsSpecificTypeId() {
                return yGoodsSpecificTypeId;
            }

            public void setYGoodsSpecificTypeId(String yGoodsSpecificTypeId) {
                this.yGoodsSpecificTypeId = yGoodsSpecificTypeId;
            }

            public String getYGoodsId() {
                return yGoodsId;
            }

            public void setYGoodsId(String yGoodsId) {
                this.yGoodsId = yGoodsId;
            }

            public String getSName() {
                return sName;
            }

            public void setSName(String sName) {
                this.sName = sName;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public List<SpecificListBean> getSpecific_List() {
                return specific_List;
            }

            public void setSpecific_List(List<SpecificListBean> specific_List) {
                this.specific_List = specific_List;
            }

            public static class SpecificListBean {
                /**
                 * yGoodsSpecificId : 692341585785913355
                 * yGoodsSpecificTypeId : 692341585785913346
                 * yGoodsId : 692341585785913345
                 * pName : 红色
                 * sPrice : 90.0
                 */

                private String yGoodsSpecificId;
                private String yGoodsSpecificTypeId;
                private String yGoodsId;
                private String pName;
                private double sPrice;

                public String getYGoodsSpecificId() {
                    return yGoodsSpecificId;
                }

                public void setYGoodsSpecificId(String yGoodsSpecificId) {
                    this.yGoodsSpecificId = yGoodsSpecificId;
                }

                public String getYGoodsSpecificTypeId() {
                    return yGoodsSpecificTypeId;
                }

                public void setYGoodsSpecificTypeId(String yGoodsSpecificTypeId) {
                    this.yGoodsSpecificTypeId = yGoodsSpecificTypeId;
                }

                public String getYGoodsId() {
                    return yGoodsId;
                }

                public void setYGoodsId(String yGoodsId) {
                    this.yGoodsId = yGoodsId;
                }

                public String getPName() {
                    return pName;
                }

                public void setPName(String pName) {
                    this.pName = pName;
                }

                public double getSPrice() {
                    return sPrice;
                }

                public void setSPrice(double sPrice) {
                    this.sPrice = sPrice;
                }
            }
        }
    }
}
