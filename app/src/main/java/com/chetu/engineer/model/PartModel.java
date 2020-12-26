package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/27.
 */
public class PartModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1019
         * yGoodsId : 692341585785913346
         * yClassifyId : 692341585785913344
         * yStoreId : 692341585785913346
         * gName : 汽车行车记录仪降压线通用小米70迈360专用停车监控车载usb电源线
         * gDesc : 现代名图后备箱垫全包围专用防水定制装饰垫现代名图汽车尾箱垫子
         * gPrice : 1000
         * orPrice : 3000
         * imgStr : /upload/2020-07-09/20200709100328_746898.png||/upload/2020-07-09/20200709100325_581730.png
         * imgArr : ["/upload/2020-07-09/20200709100328_746898.png","/upload/2020-07-09/20200709100325_581730.png"]
         * gImg : /upload/2020-07-09/20200709100328_746898.png
         * gState : 1
         * isPopular : 0
         * isIntegral : 0
         * gIntegral : 0
         * createDate : 2019-09-27 10:31:19
         * isSoffer : 1
         */

        private String id;
        private String yGoodsId;
        private String yClassifyId;
        private String yStoreId;
        private String gName;
        private String gDesc;
        private int gPrice;
        private int orPrice;
        private String imgStr;
        private String gImg;
        private int gState;
        private int isPopular;
        private int isIntegral;
        private int gIntegral;
        private String createDate;
        private int isSoffer;
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

        public int getGPrice() {
            return gPrice;
        }

        public void setGPrice(int gPrice) {
            this.gPrice = gPrice;
        }

        public int getOrPrice() {
            return orPrice;
        }

        public void setOrPrice(int orPrice) {
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

        public List<String> getImgArr() {
            return imgArr;
        }

        public void setImgArr(List<String> imgArr) {
            this.imgArr = imgArr;
        }
    }
}
