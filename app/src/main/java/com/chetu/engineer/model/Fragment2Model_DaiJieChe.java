package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020-05-23.
 */
public class Fragment2Model_DaiJieChe implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable{
        /**
         * id : 1079
         * yOrderId : 728693588870823936
         * userId : 720416799337742336
         * yStoreId : 692341585785913344
         * yUserSedanId : 728681233067802624
         * iClassify : 1
         * gPrice : 25080.0
         * longitude : 104.007521
         * latitude : 30.637048
         * appoinTime :
         * gState : 0
         * createDate : 2020-07-03 19:28:10
         * user_sedan_info : {"yUserSedanId":"728681233067802624","sName":"2017款  Macan Turbo 3.6T","sLogo":"/upload/logo/716723979414405120.jpg","sCy":1,"isF":1,"isDel":0,"reportPoliceJson":"null","jReportPoliceJson":"null","brandJson":"{\"brandName\":\"保时捷\",\"groupName\":\"保时捷\",\"id\":81346,\"parentId\":716704215493246976,\"sLogo\":\"/upload/logo/716723979414405120.jpg\",\"sName\":\"2017款  Macan Turbo 3.6T\",\"seriesName\":\"Macan\",\"vDispla\":\"4T\",\"vYear\":\"2015\",\"ySedanBrandId\":716704763193851904}","sNumber":"川A77777","brandInfo":{"id":"81346","ySedanBrandId":"716704763193851904","parentId":"716704215493246976","sName":"2017款  Macan Turbo 3.6T","sLogo":"/upload/logo/716723979414405120.jpg","seriesName":"Macan","groupName":"保时捷","brandName":"保时捷","vYear":"2015","vDispla":"4T"},"compInsuranceTime":"2026-07-04","comInsuranceTime":"2026-07-04","annualReviewTime":"2022-07-04","createDate":"2020-07-03 19:28:10"}
         * user_info : {"id":"0","userId":"0","userBalance":0,"userName":"周锶俊","userAccount":"1NMYRB","userIntegral":"0","yStoreId":"0","userHash":"504F04C880F2A74235E7CA1801A56461","headPortrait":"/upload/2020-06-26/20200626202107_110858.png","userPhone":"18582808302"}
         * order_goods_list : [{"id":"85","yOrderGoodsId":"728693588925349888","userId":"720416799337742336","isInstall":1,"isService":3,"goodsValue":"黑色||xl","goodsJson":"{\"createDate\":\"2019-09-27 10:31:19\",\"gDesc\":\"适用捷渡凌度360小米家盯盯拍70迈正际行车记录仪降压线停车监控\",\"gIntegral\":0,\"gName\":\"百魅 汽车遮阳挡6件套防晒隔热遮阳板挡阳板加厚前档太阳后挡侧挡\",\"gPrice\":1000.0,\"gState\":1,\"id\":1018,\"imgStr\":\"/upload/pro.png||/upload/pro.png\",\"isIntegral\":0,\"isPopular\":1,\"isSoffer\":1,\"orPrice\":3000.0,\"yClassifyId\":692341585785913344,\"yGoodsId\":692341585785913345,\"yStoreId\":692341585785913344}","yGoodsId":"692341585785913345","yOrderId":"728693588870823936","isIntegral":0,"gNum":1,"gIntegral":"0","gPrice":1180,"createDate":"2020-07-03 19:28:10","goods_info":{"id":"1018","yGoodsId":"692341585785913345","yClassifyId":"692341585785913344","yStoreId":"692341585785913344","gName":"百魅 汽车遮阳挡6件套防晒隔热遮阳板挡阳板加厚前档太阳后挡侧挡","gDesc":"适用捷渡凌度360小米家盯盯拍70迈正际行车记录仪降压线停车监控","gPrice":1000,"orPrice":3000,"imgStr":"/upload/pro.png||/upload/pro.png","gImg":"/upload/pro.png","gState":1,"isPopular":1,"isIntegral":0,"gIntegral":0,"createDate":"2019-09-27 10:31:19","isSoffer":1}}]
         * order_service_list : [{"id":"74","yOrderServiceId":"728693589151842304","yStoreId":"692341585785913344","yStoreServiceId":"692341585785913352","yOrderId":"728693588870823936","iClassify":1,"gPrice":100,"gState":1,"serviceStr":"在前门","servicesJson":"{\"id\":1031,\"isSheet\":1,\"lineupSum\":10,\"pictureStr\":\"/upload/2020-06-02/20200602102222_621580.png\",\"sPrice\":100.0,\"yState\":1,\"yStateValue\":\"在前门\",\"yStoreId\":692341585785913344,\"yStoreServiceId\":692341585785913352}","createDate":"2020-07-03 19:28:10","order_goods_list":[{"id":"86","yOrderGoodsId":"728693589122482176","yStoreServiceId":"692341585785913352","userId":"720416799337742336","isInstall":1,"isService":2,"goodsValue":"红色||xxl","goodsJson":"{\"createDate\":\"2019-09-27 10:31:19\",\"gDesc\":\"适用捷渡凌度360小米家盯盯拍70迈正际行车记录仪降压线停车监控\",\"gIntegral\":0,\"gName\":\"百魅 汽车遮阳挡6件套防晒隔热遮阳板挡阳板加厚前档太阳后挡侧挡\",\"gPrice\":1000.0,\"gState\":1,\"id\":1018,\"imgStr\":\"/upload/pro.png||/upload/pro.png\",\"isIntegral\":0,\"isPopular\":1,\"isSoffer\":1,\"orPrice\":3000.0,\"yClassifyId\":692341585785913344,\"yGoodsId\":692341585785913345,\"yStoreId\":692341585785913344}","yGoodsId":"692341585785913345","yOrderId":"728693588870823936","gPrice":5900,"createDate":"2020-07-03 19:28:10","goods_info":{"id":"1018","yGoodsId":"692341585785913345","yClassifyId":"692341585785913344","yStoreId":"692341585785913344","gName":"百魅 汽车遮阳挡6件套防晒隔热遮阳板挡阳板加厚前档太阳后挡侧挡","gDesc":"适用捷渡凌度360小米家盯盯拍70迈正际行车记录仪降压线停车监控","gPrice":1000,"orPrice":3000,"imgStr":"/upload/pro.png||/upload/pro.png","gImg":"/upload/pro.png","gState":1,"isPopular":1,"isIntegral":0,"gIntegral":0,"createDate":"2019-09-27 10:31:19","isSoffer":1}}],"store_service_info":{"id":"1031","yStoreServiceId":"692341585785913352","yStoreId":"692341585785913344","yState":1,"lineupSum":10,"isSheet":1,"yStateValue":"在前门","sPrice":100,"pictureStr":"/upload/2020-06-02/20200602102222_621580.png"}},{"id":"75","yOrderServiceId":"728693589265088512","yStoreId":"692341585785913344","yStoreServiceId":"692341585785913351","yOrderId":"728693588870823936","iClassify":1,"gPrice":100,"gState":1,"serviceStr":"右叶板子","servicesJson":"{\"id\":1030,\"isSheet\":1,\"lineupSum\":10,\"pictureStr\":\"/upload/2020-06-02/20200602102222_621580.png\",\"sPrice\":100.0,\"yState\":1,\"yStateValue\":\"右叶板子\",\"yStoreId\":692341585785913344,\"yStoreServiceId\":692341585785913351}","createDate":"2020-07-03 19:28:10","order_goods_list":[{"id":"87","yOrderGoodsId":"728693589235728384","yStoreServiceId":"692341585785913351","userId":"720416799337742336","isInstall":1,"isService":2,"goodsValue":"红色||xl","goodsJson":"{\"createDate\":\"2019-09-27 10:31:19\",\"gDesc\":\"适用捷渡凌度360小米家盯盯拍70迈正际行车记录仪降压线停车监控\",\"gIntegral\":0,\"gName\":\"百魅 汽车遮阳挡6件套防晒隔热遮阳板挡阳板加厚前档太阳后挡侧挡\",\"gPrice\":1000.0,\"gState\":1,\"id\":1018,\"imgStr\":\"/upload/pro.png||/upload/pro.png\",\"isIntegral\":0,\"isPopular\":1,\"isSoffer\":1,\"orPrice\":3000.0,\"yClassifyId\":692341585785913344,\"yGoodsId\":692341585785913345,\"yStoreId\":692341585785913344}","yGoodsId":"692341585785913345","yOrderId":"728693588870823936","gPrice":5900,"createDate":"2020-07-03 19:28:10","goods_info":{"id":"1018","yGoodsId":"692341585785913345","yClassifyId":"692341585785913344","yStoreId":"692341585785913344","gName":"百魅 汽车遮阳挡6件套防晒隔热遮阳板挡阳板加厚前档太阳后挡侧挡","gDesc":"适用捷渡凌度360小米家盯盯拍70迈正际行车记录仪降压线停车监控","gPrice":1000,"orPrice":3000,"imgStr":"/upload/pro.png||/upload/pro.png","gImg":"/upload/pro.png","gState":1,"isPopular":1,"isIntegral":0,"gIntegral":0,"createDate":"2019-09-27 10:31:19","isSoffer":1}}],"store_service_info":{"id":"1030","yStoreServiceId":"692341585785913351","yStoreId":"692341585785913344","yState":1,"lineupSum":10,"isSheet":1,"yStateValue":"右叶板子","sPrice":100,"pictureStr":"/upload/2020-06-02/20200602102222_621580.png"}},{"id":"76","yOrderServiceId":"728693589357363200","yStoreId":"692341585785913344","yStoreServiceId":"692341585785913350","yOrderId":"728693588870823936","iClassify":1,"gPrice":100,"gState":1,"serviceStr":"机盖","servicesJson":"{\"id\":1029,\"isSheet\":0,\"lineupSum\":10,\"pictureStr\":\"/upload/2020-06-02/20200602102222_621580.png\",\"sPrice\":100.0,\"yState\":1,\"yStateValue\":\"机盖\",\"yStoreId\":692341585785913344,\"yStoreServiceId\":692341585785913350}","createDate":"2020-07-03 19:28:10","order_goods_list":[{"id":"88","yOrderGoodsId":"728693589311225856","yStoreServiceId":"692341585785913350","userId":"720416799337742336","isInstall":1,"isService":2,"goodsValue":"绿色||ml","goodsJson":"{\"createDate\":\"2019-09-27 10:31:19\",\"gDesc\":\"适用捷渡凌度360小米家盯盯拍70迈正际行车记录仪降压线停车监控\",\"gIntegral\":0,\"gName\":\"百魅 汽车遮阳挡6件套防晒隔热遮阳板挡阳板加厚前档太阳后挡侧挡\",\"gPrice\":1000.0,\"gState\":1,\"id\":1018,\"imgStr\":\"/upload/pro.png||/upload/pro.png\",\"isIntegral\":0,\"isPopular\":1,\"isSoffer\":1,\"orPrice\":3000.0,\"yClassifyId\":692341585785913344,\"yGoodsId\":692341585785913345,\"yStoreId\":692341585785913344}","yGoodsId":"692341585785913345","yOrderId":"728693588870823936","gPrice":11800,"createDate":"2020-07-03 19:28:10","goods_info":{"id":"1018","yGoodsId":"692341585785913345","yClassifyId":"692341585785913344","yStoreId":"692341585785913344","gName":"百魅 汽车遮阳挡6件套防晒隔热遮阳板挡阳板加厚前档太阳后挡侧挡","gDesc":"适用捷渡凌度360小米家盯盯拍70迈正际行车记录仪降压线停车监控","gPrice":1000,"orPrice":3000,"imgStr":"/upload/pro.png||/upload/pro.png","gImg":"/upload/pro.png","gState":1,"isPopular":1,"isIntegral":0,"gIntegral":0,"createDate":"2019-09-27 10:31:19","isSoffer":1}}],"store_service_info":{"id":"1029","yStoreServiceId":"692341585785913350","yStoreId":"692341585785913344","yState":1,"lineupSum":10,"isSheet":0,"yStateValue":"机盖","sPrice":100,"pictureStr":"/upload/2020-06-02/20200602102222_621580.png"}}]
         * isDelivery : 1
         * isPick : 1
         * serviceStr :  |在前门|右叶板子|机盖
         * yTechnSedanId : 720600475505786880
         * okTime : 2020-06-17 17:15:02
         * pickUpTime : 2020-06-17 17:15:50
         */

        private String id;
        private String yOrderId;
        private String userId;
        private String yStoreId;
        private String yUserSedanId;
        private int iClassify;
        private double gPrice;
        private String longitude;
        private String latitude;
        private String appoinTime;
        private String sendAddress;

        private int gState;
        private String createDate;
        private UserSedanInfoBean user_sedan_info;
        private UserInfoBean user_info;
        private int isDelivery;
        private int isPick;
        private String serviceStr;
        private String cMsg;
        private String deliveryAddress;
        private String yTechnSedanId;
        private String okTime;
        private String pickUpTime;
        private List<OrderGoodsListBean> order_goods_list;
        private List<OrderServiceListBean> order_service_list;

        public String getDeliveryAddress() {
            return deliveryAddress;
        }

        public void setDeliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
        }

        public String getcMsg() {
            return cMsg;
        }

        public void setcMsg(String cMsg) {
            this.cMsg = cMsg;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYOrderId() {
            return yOrderId;
        }

        public void setYOrderId(String yOrderId) {
            this.yOrderId = yOrderId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getYStoreId() {
            return yStoreId;
        }

        public void setYStoreId(String yStoreId) {
            this.yStoreId = yStoreId;
        }

        public String getYUserSedanId() {
            return yUserSedanId;
        }

        public void setYUserSedanId(String yUserSedanId) {
            this.yUserSedanId = yUserSedanId;
        }

        public int getIClassify() {
            return iClassify;
        }

        public void setIClassify(int iClassify) {
            this.iClassify = iClassify;
        }

        public double getGPrice() {
            return gPrice;
        }

        public void setGPrice(double gPrice) {
            this.gPrice = gPrice;
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

        public String getAppoinTime() {
            return appoinTime;
        }

        public void setAppoinTime(String appoinTime) {
            this.appoinTime = appoinTime;
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

        public UserSedanInfoBean getUser_sedan_info() {
            return user_sedan_info;
        }

        public void setUser_sedan_info(UserSedanInfoBean user_sedan_info) {
            this.user_sedan_info = user_sedan_info;
        }

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public int getIsDelivery() {
            return isDelivery;
        }

        public void setIsDelivery(int isDelivery) {
            this.isDelivery = isDelivery;
        }

        public int getIsPick() {
            return isPick;
        }

        public void setIsPick(int isPick) {
            this.isPick = isPick;
        }

        public String getServiceStr() {
            return serviceStr;
        }

        public void setServiceStr(String serviceStr) {
            this.serviceStr = serviceStr;
        }

        public String getYTechnSedanId() {
            return yTechnSedanId;
        }

        public void setYTechnSedanId(String yTechnSedanId) {
            this.yTechnSedanId = yTechnSedanId;
        }

        public String getOkTime() {
            return okTime;
        }

        public void setOkTime(String okTime) {
            this.okTime = okTime;
        }

        public String getPickUpTime() {
            return pickUpTime;
        }

        public void setPickUpTime(String pickUpTime) {
            this.pickUpTime = pickUpTime;
        }

        public List<OrderGoodsListBean> getOrder_goods_list() {
            return order_goods_list;
        }

        public void setOrder_goods_list(List<OrderGoodsListBean> order_goods_list) {
            this.order_goods_list = order_goods_list;
        }

        public List<OrderServiceListBean> getOrder_service_list() {
            return order_service_list;
        }

        public void setOrder_service_list(List<OrderServiceListBean> order_service_list) {
            this.order_service_list = order_service_list;
        }

        public static class UserSedanInfoBean implements Serializable{
            /**
             * yUserSedanId : 728681233067802624
             * sName : 2017款  Macan Turbo 3.6T
             * sLogo : /upload/logo/716723979414405120.jpg
             * sCy : 1
             * isF : 1
             * isDel : 0
             * reportPoliceJson : null
             * jReportPoliceJson : null
             * brandJson : {"brandName":"保时捷","groupName":"保时捷","id":81346,"parentId":716704215493246976,"sLogo":"/upload/logo/716723979414405120.jpg","sName":"2017款  Macan Turbo 3.6T","seriesName":"Macan","vDispla":"4T","vYear":"2015","ySedanBrandId":716704763193851904}
             * sNumber : 川A77777
             * brandInfo : {"id":"81346","ySedanBrandId":"716704763193851904","parentId":"716704215493246976","sName":"2017款  Macan Turbo 3.6T","sLogo":"/upload/logo/716723979414405120.jpg","seriesName":"Macan","groupName":"保时捷","brandName":"保时捷","vYear":"2015","vDispla":"4T"}
             * compInsuranceTime : 2026-07-04
             * comInsuranceTime : 2026-07-04
             * annualReviewTime : 2022-07-04
             * createDate : 2020-07-03 19:28:10
             */

            private String yUserSedanId;
            private String sName;
            private String sLogo;
            private int sCy;
            private int isF;
            private int isDel;
            private String reportPoliceJson;
            private String jReportPoliceJson;
            private String brandJson;
            private String sNumber;
            private BrandInfoBean brandInfo;
            private String compInsuranceTime;
            private String comInsuranceTime;
            private String annualReviewTime;
            private String createDate;

            public String getYUserSedanId() {
                return yUserSedanId;
            }

            public void setYUserSedanId(String yUserSedanId) {
                this.yUserSedanId = yUserSedanId;
            }

            public String getSName() {
                return sName;
            }

            public void setSName(String sName) {
                this.sName = sName;
            }

            public String getSLogo() {
                return sLogo;
            }

            public void setSLogo(String sLogo) {
                this.sLogo = sLogo;
            }

            public int getSCy() {
                return sCy;
            }

            public void setSCy(int sCy) {
                this.sCy = sCy;
            }

            public int getIsF() {
                return isF;
            }

            public void setIsF(int isF) {
                this.isF = isF;
            }

            public int getIsDel() {
                return isDel;
            }

            public void setIsDel(int isDel) {
                this.isDel = isDel;
            }

            public String getReportPoliceJson() {
                return reportPoliceJson;
            }

            public void setReportPoliceJson(String reportPoliceJson) {
                this.reportPoliceJson = reportPoliceJson;
            }

            public String getJReportPoliceJson() {
                return jReportPoliceJson;
            }

            public void setJReportPoliceJson(String jReportPoliceJson) {
                this.jReportPoliceJson = jReportPoliceJson;
            }

            public String getBrandJson() {
                return brandJson;
            }

            public void setBrandJson(String brandJson) {
                this.brandJson = brandJson;
            }

            public String getSNumber() {
                return sNumber;
            }

            public void setSNumber(String sNumber) {
                this.sNumber = sNumber;
            }

            public BrandInfoBean getBrandInfo() {
                return brandInfo;
            }

            public void setBrandInfo(BrandInfoBean brandInfo) {
                this.brandInfo = brandInfo;
            }

            public String getCompInsuranceTime() {
                return compInsuranceTime;
            }

            public void setCompInsuranceTime(String compInsuranceTime) {
                this.compInsuranceTime = compInsuranceTime;
            }

            public String getComInsuranceTime() {
                return comInsuranceTime;
            }

            public void setComInsuranceTime(String comInsuranceTime) {
                this.comInsuranceTime = comInsuranceTime;
            }

            public String getAnnualReviewTime() {
                return annualReviewTime;
            }

            public void setAnnualReviewTime(String annualReviewTime) {
                this.annualReviewTime = annualReviewTime;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public static class BrandInfoBean implements Serializable{
                /**
                 * id : 81346
                 * ySedanBrandId : 716704763193851904
                 * parentId : 716704215493246976
                 * sName : 2017款  Macan Turbo 3.6T
                 * sLogo : /upload/logo/716723979414405120.jpg
                 * seriesName : Macan
                 * groupName : 保时捷
                 * brandName : 保时捷
                 * vYear : 2015
                 * vDispla : 4T
                 */

                private String id;
                private String ySedanBrandId;
                private String parentId;
                private String sName;
                private String sLogo;
                private String seriesName;
                private String groupName;
                private String brandName;
                private String vYear;
                private String vDispla;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getYSedanBrandId() {
                    return ySedanBrandId;
                }

                public void setYSedanBrandId(String ySedanBrandId) {
                    this.ySedanBrandId = ySedanBrandId;
                }

                public String getParentId() {
                    return parentId;
                }

                public void setParentId(String parentId) {
                    this.parentId = parentId;
                }

                public String getSName() {
                    return sName;
                }

                public void setSName(String sName) {
                    this.sName = sName;
                }

                public String getSLogo() {
                    return sLogo;
                }

                public void setSLogo(String sLogo) {
                    this.sLogo = sLogo;
                }

                public String getSeriesName() {
                    return seriesName;
                }

                public void setSeriesName(String seriesName) {
                    this.seriesName = seriesName;
                }

                public String getGroupName() {
                    return groupName;
                }

                public void setGroupName(String groupName) {
                    this.groupName = groupName;
                }

                public String getBrandName() {
                    return brandName;
                }

                public void setBrandName(String brandName) {
                    this.brandName = brandName;
                }

                public String getVYear() {
                    return vYear;
                }

                public void setVYear(String vYear) {
                    this.vYear = vYear;
                }

                public String getVDispla() {
                    return vDispla;
                }

                public void setVDispla(String vDispla) {
                    this.vDispla = vDispla;
                }
            }
        }

        public static class UserInfoBean implements Serializable{
            /**
             * id : 0
             * userId : 0
             * userBalance : 0.0
             * userName : 周锶俊
             * userAccount : 1NMYRB
             * userIntegral : 0
             * yStoreId : 0
             * userHash : 504F04C880F2A74235E7CA1801A56461
             * headPortrait : /upload/2020-06-26/20200626202107_110858.png
             * userPhone : 18582808302
             */

            private String id;
            private String userId;
            private double userBalance;
            private String userName;
            private String userAccount;
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

            public String getUserAccount() {
                return userAccount;
            }

            public void setUserAccount(String userAccount) {
                this.userAccount = userAccount;
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

        public static class OrderGoodsListBean implements Serializable{
            /**
             * id : 85
             * yOrderGoodsId : 728693588925349888
             * userId : 720416799337742336
             * isInstall : 1
             * isService : 3
             * goodsValue : 黑色||xl
             * goodsJson : {"createDate":"2019-09-27 10:31:19","gDesc":"适用捷渡凌度360小米家盯盯拍70迈正际行车记录仪降压线停车监控","gIntegral":0,"gName":"百魅 汽车遮阳挡6件套防晒隔热遮阳板挡阳板加厚前档太阳后挡侧挡","gPrice":1000.0,"gState":1,"id":1018,"imgStr":"/upload/pro.png||/upload/pro.png","isIntegral":0,"isPopular":1,"isSoffer":1,"orPrice":3000.0,"yClassifyId":692341585785913344,"yGoodsId":692341585785913345,"yStoreId":692341585785913344}
             * yGoodsId : 692341585785913345
             * yOrderId : 728693588870823936
             * isIntegral : 0
             * gNum : 1
             * gIntegral : 0
             * gPrice : 1180.0
             * createDate : 2020-07-03 19:28:10
             * goods_info : {"id":"1018","yGoodsId":"692341585785913345","yClassifyId":"692341585785913344","yStoreId":"692341585785913344","gName":"百魅 汽车遮阳挡6件套防晒隔热遮阳板挡阳板加厚前档太阳后挡侧挡","gDesc":"适用捷渡凌度360小米家盯盯拍70迈正际行车记录仪降压线停车监控","gPrice":1000,"orPrice":3000,"imgStr":"/upload/pro.png||/upload/pro.png","gImg":"/upload/pro.png","gState":1,"isPopular":1,"isIntegral":0,"gIntegral":0,"createDate":"2019-09-27 10:31:19","isSoffer":1}
             */

            private String id;
            private String yOrderGoodsId;
            private String userId;
            private int isInstall;
            private int isService;
            private String goodsValue;
            private String goodsJson;
            private String yGoodsId;
            private String yOrderId;
            private int isIntegral;
            private int gNum;
            private String gIntegral;
            private double gPrice;
            private String createDate;
            private GoodsInfoBean goods_info;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getYOrderGoodsId() {
                return yOrderGoodsId;
            }

            public void setYOrderGoodsId(String yOrderGoodsId) {
                this.yOrderGoodsId = yOrderGoodsId;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public int getIsInstall() {
                return isInstall;
            }

            public void setIsInstall(int isInstall) {
                this.isInstall = isInstall;
            }

            public int getIsService() {
                return isService;
            }

            public void setIsService(int isService) {
                this.isService = isService;
            }

            public String getGoodsValue() {
                return goodsValue;
            }

            public void setGoodsValue(String goodsValue) {
                this.goodsValue = goodsValue;
            }

            public String getGoodsJson() {
                return goodsJson;
            }

            public void setGoodsJson(String goodsJson) {
                this.goodsJson = goodsJson;
            }

            public String getYGoodsId() {
                return yGoodsId;
            }

            public void setYGoodsId(String yGoodsId) {
                this.yGoodsId = yGoodsId;
            }

            public String getYOrderId() {
                return yOrderId;
            }

            public void setYOrderId(String yOrderId) {
                this.yOrderId = yOrderId;
            }

            public int getIsIntegral() {
                return isIntegral;
            }

            public void setIsIntegral(int isIntegral) {
                this.isIntegral = isIntegral;
            }

            public int getGNum() {
                return gNum;
            }

            public void setGNum(int gNum) {
                this.gNum = gNum;
            }

            public String getGIntegral() {
                return gIntegral;
            }

            public void setGIntegral(String gIntegral) {
                this.gIntegral = gIntegral;
            }

            public double getGPrice() {
                return gPrice;
            }

            public void setGPrice(double gPrice) {
                this.gPrice = gPrice;
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

            public static class GoodsInfoBean implements Serializable{
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
                 */

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
            }
        }

        public static class OrderServiceListBean implements Serializable{
            /**
             * id : 74
             * yOrderServiceId : 728693589151842304
             * yStoreId : 692341585785913344
             * yStoreServiceId : 692341585785913352
             * yOrderId : 728693588870823936
             * iClassify : 1
             * gPrice : 100.0
             * gState : 1
             * serviceStr : 在前门
             * servicesJson : {"id":1031,"isSheet":1,"lineupSum":10,"pictureStr":"/upload/2020-06-02/20200602102222_621580.png","sPrice":100.0,"yState":1,"yStateValue":"在前门","yStoreId":692341585785913344,"yStoreServiceId":692341585785913352}
             * createDate : 2020-07-03 19:28:10
             * order_goods_list : [{"id":"86","yOrderGoodsId":"728693589122482176","yStoreServiceId":"692341585785913352","userId":"720416799337742336","isInstall":1,"isService":2,"goodsValue":"红色||xxl","goodsJson":"{\"createDate\":\"2019-09-27 10:31:19\",\"gDesc\":\"适用捷渡凌度360小米家盯盯拍70迈正际行车记录仪降压线停车监控\",\"gIntegral\":0,\"gName\":\"百魅 汽车遮阳挡6件套防晒隔热遮阳板挡阳板加厚前档太阳后挡侧挡\",\"gPrice\":1000.0,\"gState\":1,\"id\":1018,\"imgStr\":\"/upload/pro.png||/upload/pro.png\",\"isIntegral\":0,\"isPopular\":1,\"isSoffer\":1,\"orPrice\":3000.0,\"yClassifyId\":692341585785913344,\"yGoodsId\":692341585785913345,\"yStoreId\":692341585785913344}","yGoodsId":"692341585785913345","yOrderId":"728693588870823936","gPrice":5900,"createDate":"2020-07-03 19:28:10","goods_info":{"id":"1018","yGoodsId":"692341585785913345","yClassifyId":"692341585785913344","yStoreId":"692341585785913344","gName":"百魅 汽车遮阳挡6件套防晒隔热遮阳板挡阳板加厚前档太阳后挡侧挡","gDesc":"适用捷渡凌度360小米家盯盯拍70迈正际行车记录仪降压线停车监控","gPrice":1000,"orPrice":3000,"imgStr":"/upload/pro.png||/upload/pro.png","gImg":"/upload/pro.png","gState":1,"isPopular":1,"isIntegral":0,"gIntegral":0,"createDate":"2019-09-27 10:31:19","isSoffer":1}}]
             * store_service_info : {"id":"1031","yStoreServiceId":"692341585785913352","yStoreId":"692341585785913344","yState":1,"lineupSum":10,"isSheet":1,"yStateValue":"在前门","sPrice":100,"pictureStr":"/upload/2020-06-02/20200602102222_621580.png"}
             */

            private String id;
            private String yOrderServiceId;
            private String yStoreId;
            private String yStoreServiceId;
            private String yOrderId;
            private int iClassify;
            private double gPrice;
            private int gState;
            private String serviceStr;
            private String servicesJson;
            private String createDate;
            private StoreServiceInfoBean store_service_info;
            private List<OrderGoodsListBeanX> order_goods_list;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getYOrderServiceId() {
                return yOrderServiceId;
            }

            public void setYOrderServiceId(String yOrderServiceId) {
                this.yOrderServiceId = yOrderServiceId;
            }

            public String getYStoreId() {
                return yStoreId;
            }

            public void setYStoreId(String yStoreId) {
                this.yStoreId = yStoreId;
            }

            public String getYStoreServiceId() {
                return yStoreServiceId;
            }

            public void setYStoreServiceId(String yStoreServiceId) {
                this.yStoreServiceId = yStoreServiceId;
            }

            public String getYOrderId() {
                return yOrderId;
            }

            public void setYOrderId(String yOrderId) {
                this.yOrderId = yOrderId;
            }

            public int getIClassify() {
                return iClassify;
            }

            public void setIClassify(int iClassify) {
                this.iClassify = iClassify;
            }

            public double getGPrice() {
                return gPrice;
            }

            public void setGPrice(double gPrice) {
                this.gPrice = gPrice;
            }

            public int getGState() {
                return gState;
            }

            public void setGState(int gState) {
                this.gState = gState;
            }

            public String getServiceStr() {
                return serviceStr;
            }

            public void setServiceStr(String serviceStr) {
                this.serviceStr = serviceStr;
            }

            public String getServicesJson() {
                return servicesJson;
            }

            public void setServicesJson(String servicesJson) {
                this.servicesJson = servicesJson;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public StoreServiceInfoBean getStore_service_info() {
                return store_service_info;
            }

            public void setStore_service_info(StoreServiceInfoBean store_service_info) {
                this.store_service_info = store_service_info;
            }

            public List<OrderGoodsListBeanX> getOrder_goods_list() {
                return order_goods_list;
            }

            public void setOrder_goods_list(List<OrderGoodsListBeanX> order_goods_list) {
                this.order_goods_list = order_goods_list;
            }

            public static class StoreServiceInfoBean implements Serializable{
                /**
                 * id : 1031
                 * yStoreServiceId : 692341585785913352
                 * yStoreId : 692341585785913344
                 * yState : 1
                 * lineupSum : 10
                 * isSheet : 1
                 * yStateValue : 在前门
                 * sPrice : 100.0
                 * pictureStr : /upload/2020-06-02/20200602102222_621580.png
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

            public static class OrderGoodsListBeanX implements Serializable{
                /**
                 * id : 86
                 * yOrderGoodsId : 728693589122482176
                 * yStoreServiceId : 692341585785913352
                 * userId : 720416799337742336
                 * isInstall : 1
                 * isService : 2
                 * goodsValue : 红色||xxl
                 * goodsJson : {"createDate":"2019-09-27 10:31:19","gDesc":"适用捷渡凌度360小米家盯盯拍70迈正际行车记录仪降压线停车监控","gIntegral":0,"gName":"百魅 汽车遮阳挡6件套防晒隔热遮阳板挡阳板加厚前档太阳后挡侧挡","gPrice":1000.0,"gState":1,"id":1018,"imgStr":"/upload/pro.png||/upload/pro.png","isIntegral":0,"isPopular":1,"isSoffer":1,"orPrice":3000.0,"yClassifyId":692341585785913344,"yGoodsId":692341585785913345,"yStoreId":692341585785913344}
                 * yGoodsId : 692341585785913345
                 * yOrderId : 728693588870823936
                 * gPrice : 5900.0
                 * createDate : 2020-07-03 19:28:10
                 * goods_info : {"id":"1018","yGoodsId":"692341585785913345","yClassifyId":"692341585785913344","yStoreId":"692341585785913344","gName":"百魅 汽车遮阳挡6件套防晒隔热遮阳板挡阳板加厚前档太阳后挡侧挡","gDesc":"适用捷渡凌度360小米家盯盯拍70迈正际行车记录仪降压线停车监控","gPrice":1000,"orPrice":3000,"imgStr":"/upload/pro.png||/upload/pro.png","gImg":"/upload/pro.png","gState":1,"isPopular":1,"isIntegral":0,"gIntegral":0,"createDate":"2019-09-27 10:31:19","isSoffer":1}
                 */

                private String id;
                private String yOrderGoodsId;
                private String yStoreServiceId;
                private String userId;
                private int isInstall;
                private int isService;
                private String goodsValue;
                private String goodsJson;
                private String yGoodsId;
                private String yOrderId;
                private double gPrice;
                private String createDate;
                private GoodsInfoBeanX goods_info;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getYOrderGoodsId() {
                    return yOrderGoodsId;
                }

                public void setYOrderGoodsId(String yOrderGoodsId) {
                    this.yOrderGoodsId = yOrderGoodsId;
                }

                public String getYStoreServiceId() {
                    return yStoreServiceId;
                }

                public void setYStoreServiceId(String yStoreServiceId) {
                    this.yStoreServiceId = yStoreServiceId;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public int getIsInstall() {
                    return isInstall;
                }

                public void setIsInstall(int isInstall) {
                    this.isInstall = isInstall;
                }

                public int getIsService() {
                    return isService;
                }

                public void setIsService(int isService) {
                    this.isService = isService;
                }

                public String getGoodsValue() {
                    return goodsValue;
                }

                public void setGoodsValue(String goodsValue) {
                    this.goodsValue = goodsValue;
                }

                public String getGoodsJson() {
                    return goodsJson;
                }

                public void setGoodsJson(String goodsJson) {
                    this.goodsJson = goodsJson;
                }

                public String getYGoodsId() {
                    return yGoodsId;
                }

                public void setYGoodsId(String yGoodsId) {
                    this.yGoodsId = yGoodsId;
                }

                public String getYOrderId() {
                    return yOrderId;
                }

                public void setYOrderId(String yOrderId) {
                    this.yOrderId = yOrderId;
                }

                public double getGPrice() {
                    return gPrice;
                }

                public void setGPrice(double gPrice) {
                    this.gPrice = gPrice;
                }

                public String getCreateDate() {
                    return createDate;
                }

                public void setCreateDate(String createDate) {
                    this.createDate = createDate;
                }

                public GoodsInfoBeanX getGoods_info() {
                    return goods_info;
                }

                public void setGoods_info(GoodsInfoBeanX goods_info) {
                    this.goods_info = goods_info;
                }

                public static class GoodsInfoBeanX implements Serializable{
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
                     */

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
                }
            }
        }
    }
}
