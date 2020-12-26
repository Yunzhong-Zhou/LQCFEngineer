package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/14.
 */
public class PingJiaModel implements Serializable {
    /**
     * list : [{"id":"1","goodsEvalId":"692341585785913344","userId":"20180531104813","yGoodsId":"692341585785913344","yStoreId":"692341585785913344","yOrderId":"0","starC":"3","yMsg":"测试","createDate":"2019-09-27 10:31:19","y_user":{"id":"0","userId":"20180531104813","userBalance":0,"userName":"龙肆","userIntegral":"0","yStoreId":"0","userHash":"3B2372646663FDC7A81EA8E241CB7946AC74E4C0C9CFD31F750FEE2831528FF5","headPortrait":" ","userPhone":"18203048656"},"imgStr":"/upload/pro.png||/upload/pro.png","imgArr":["/upload/pro.png","/upload/pro.png"]}]
     * count : 0
     */

    private int count;
    private List<ListBean> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1
         * goodsEvalId : 692341585785913344
         * userId : 20180531104813
         * yGoodsId : 692341585785913344
         * yStoreId : 692341585785913344
         * yOrderId : 0
         * starC : 3
         * yMsg : 测试
         * createDate : 2019-09-27 10:31:19
         * y_user : {"id":"0","userId":"20180531104813","userBalance":0,"userName":"龙肆","userIntegral":"0","yStoreId":"0","userHash":"3B2372646663FDC7A81EA8E241CB7946AC74E4C0C9CFD31F750FEE2831528FF5","headPortrait":" ","userPhone":"18203048656"}
         * imgStr : /upload/pro.png||/upload/pro.png
         * imgArr : ["/upload/pro.png","/upload/pro.png"]
         */

        private String id;
        private String goodsEvalId;
        private String userId;
        private String yGoodsId;
        private String yStoreId;
        private String yOrderId;
        private String starC;
        private String yMsg;
        private String createDate;
        private YUserBean y_user;
        private String imgStr;
        private List<String> imgArr;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoodsEvalId() {
            return goodsEvalId;
        }

        public void setGoodsEvalId(String goodsEvalId) {
            this.goodsEvalId = goodsEvalId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getYGoodsId() {
            return yGoodsId;
        }

        public void setYGoodsId(String yGoodsId) {
            this.yGoodsId = yGoodsId;
        }

        public String getYStoreId() {
            return yStoreId;
        }

        public void setYStoreId(String yStoreId) {
            this.yStoreId = yStoreId;
        }

        public String getYOrderId() {
            return yOrderId;
        }

        public void setYOrderId(String yOrderId) {
            this.yOrderId = yOrderId;
        }

        public String getStarC() {
            return starC;
        }

        public void setStarC(String starC) {
            this.starC = starC;
        }

        public String getYMsg() {
            return yMsg;
        }

        public void setYMsg(String yMsg) {
            this.yMsg = yMsg;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public YUserBean getY_user() {
            return y_user;
        }

        public void setY_user(YUserBean y_user) {
            this.y_user = y_user;
        }

        public String getImgStr() {
            return imgStr;
        }

        public void setImgStr(String imgStr) {
            this.imgStr = imgStr;
        }

        public List<String> getImgArr() {
            return imgArr;
        }

        public void setImgArr(List<String> imgArr) {
            this.imgArr = imgArr;
        }

        public static class YUserBean {
            /**
             * id : 0
             * userId : 20180531104813
             * userBalance : 0.0
             * userName : 龙肆
             * userIntegral : 0
             * yStoreId : 0
             * userHash : 3B2372646663FDC7A81EA8E241CB7946AC74E4C0C9CFD31F750FEE2831528FF5
             * headPortrait :
             * userPhone : 18203048656
             */

            private String id;
            private String userId;
            private double userBalance;
            private String userName;
            private String userIntegral;
            private String yStoreId;
            private String userHash;
            private String headPortrait;
            private String userPhone;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public double getUserBalance() {
                return userBalance;
            }

            public void setUserBalance(double userBalance) {
                this.userBalance = userBalance;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserIntegral() {
                return userIntegral;
            }

            public void setUserIntegral(String userIntegral) {
                this.userIntegral = userIntegral;
            }

            public String getYStoreId() {
                return yStoreId;
            }

            public void setYStoreId(String yStoreId) {
                this.yStoreId = yStoreId;
            }

            public String getUserHash() {
                return userHash;
            }

            public void setUserHash(String userHash) {
                this.userHash = userHash;
            }

            public String getHeadPortrait() {
                return headPortrait;
            }

            public void setHeadPortrait(String headPortrait) {
                this.headPortrait = headPortrait;
            }

            public String getUserPhone() {
                return userPhone;
            }

            public void setUserPhone(String userPhone) {
                this.userPhone = userPhone;
            }
        }
    }
}
