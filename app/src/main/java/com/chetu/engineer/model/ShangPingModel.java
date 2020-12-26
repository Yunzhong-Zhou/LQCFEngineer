package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/5/26.
 */
public class ShangPingModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * yUserFootprintId : 722569085925195776
         * userId : 714547022807433216
         * yId : 692341585785913346
         * category : 1
         * createDate : 2020-06-16 21:51:34
         * goods_info : {"yGoodsId":"692341585785913346","gName":"汽车行车记录仪降压线通用小米70迈360专用停车监控车载usb电源线","gPrice":1000,"orPrice":0,"gDetails":"","imgStr":"/upload/pro.png||/upload/pro.png","imgArr":["/upload/pro.png","/upload/pro.png"],"gImg":"/upload/pro.png"}
         */

        private String yUserFootprintId;
        private String userId;
        private String yId;
        private int category;
        private String createDate;
        private GoodsInfoBean goods_info;

        public String getYUserFootprintId() {
            return yUserFootprintId;
        }

        public void setYUserFootprintId(String yUserFootprintId) {
            this.yUserFootprintId = yUserFootprintId;
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

        public GoodsInfoBean getGoods_info() {
            return goods_info;
        }

        public void setGoods_info(GoodsInfoBean goods_info) {
            this.goods_info = goods_info;
        }

        public static class GoodsInfoBean {
            /**
             * yGoodsId : 692341585785913346
             * gName : 汽车行车记录仪降压线通用小米70迈360专用停车监控车载usb电源线
             * gPrice : 1000.0
             * orPrice : 0.0
             * gDetails :
             * imgStr : /upload/pro.png||/upload/pro.png
             * imgArr : ["/upload/pro.png","/upload/pro.png"]
             * gImg : /upload/pro.png
             */

            private String yGoodsId;
            private String gName;
            private String gDesc;
            private double gPrice;
            private double orPrice;
            private String gDetails;
            private String imgStr;
            private String gImg;
            private List<String> imgArr;

            public String getgDesc() {
                return gDesc;
            }

            public void setgDesc(String gDesc) {
                this.gDesc = gDesc;
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

            public List<String> getImgArr() {
                return imgArr;
            }

            public void setImgArr(List<String> imgArr) {
                this.imgArr = imgArr;
            }
        }
    }
}
