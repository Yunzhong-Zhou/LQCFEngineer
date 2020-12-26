package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020-05-23.
 */
public class Fragment2Model implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable{
        /**
         * id : 1040
         * yTechnSedanId : 728917123266510848
         * yOrderId : 728916430245855232
         * ySedanBrandId : 716704763193851904
         * yStoreId : 692341585785913344
         * userId : 719539276219416576
         * licenseNumber : 川A77777
         * ownerName : 周锶俊
         * ownerPhone : 18582808302
         * frameNumber : 车架号
         * vehicleMileage : 100000
         * compInsuranceTime : 2023-07-04
         * comInsuranceTime : 2030-07-04
         * annualReviewTime : 2021-07-04
         * vRemarks : 备注
         * sedanBrandJson : {"brandName":"保时捷","groupName":"保时捷","id":81346,"parentId":716704215493246976,"sLogo":"/upload/logo/716723979414405120.jpg","sName":"2017款  Macan Turbo 3.6T","seriesName":"Macan","vDispla":"4T","vYear":"2015","ySedanBrandId":716704763193851904}
         * gPrice : 8460.0
         * isOrder : 1
         * createDate : 2020-07-04 10:16:24
         * serviceStr : |在前门|右叶板子
         * gState : 2
         * techn_info : {"id":"0","userId":"719539276219416576","userBalance":0,"userName":"阿斯顿马丁","userIntegral":"0","yStoreId":"0","userHash":"843B1049032A7E7C9F7D2E2CBE6D37A6","headPortrait":"/upload/2020-06-18/20200618164348_110881.jpg","userPhone":"18306043085"}
         * order_info : {"id":"1081","yOrderId":"728916430245855232","userId":"720416799337742336","yStoreId":"692341585785913344","yTechnSedanId":"728917123266510848","yUserSedanId":"728681233067802624","iClassify":1,"gPrice":8460,"longitude":"104.007352","latitude":"30.637283","gState":1,"createDate":"2020-07-04 10:13:39","isDelivery":0,"isPick":0}
         * distributeJson : [{"headPortrait":"/upload/2020-06-18/20200618164348_110881.jpg","id":0,"userBalance":0.0,"userHash":"843B1049032A7E7C9F7D2E2CBE6D37A6","userId":0,"userIntegral":0,"userName":"阿斯顿马丁","yStoreId":692341585785913344},{"headPortrait":"/upload/2020-06-26/20200626202107_110858.png","id":0,"userBalance":0.0,"userHash":"504F04C880F2A74235E7CA1801A56461","userId":0,"userIntegral":0,"userName":"周锶俊","yStoreId":692341585785913344}]
         * brandInfo : {"id":"81346","ySedanBrandId":"716704763193851904","parentId":"716704215493246976","sName":"2017款  Macan Turbo 3.6T","sLogo":"/upload/logo/716723979414405120.jpg","seriesName":"Macan","groupName":"保时捷","brandName":"保时捷","vYear":"2015","vDispla":"4T"}
         * appeImgstr : /upload/2020-07-04/20200704101624_931309.jpeg||/upload/2020-07-04/20200704101624_865000.jpeg||/upload/2020-07-04/20200704101624_858316.jpg||/upload/2020-07-04/20200704101624_801332.jpg
         * estimateTime : 2020-06-17 17:15:50
         * rewardMoney : 10.0
         */

        private String id;
        private String yTechnSedanId;
        private String yOrderId;
        private String ySedanBrandId;
        private String yStoreId;
        private String userId;
        private String licenseNumber;
        private String ownerName;
        private String ownerPhone;
        private String frameNumber;
        private String vehicleMileage;
        private String compInsuranceTime;
        private String comInsuranceTime;
        private String annualReviewTime;
        private String vRemarks;
        private String sedanBrandJson;
        private double gPrice;
        private int isOrder;
        private String createDate;
        private String serviceStr;
        private int gState;
        private int isPay;
        private TechnInfoBean techn_info;
        private OrderInfoBean order_info;
        private String distributeJson;
        private BrandInfoBean brandInfo;
        private String appeImgstr;
        private String estimateTime;
        private double rewardMoney;

        public int getIsPay() {
            return isPay;
        }

        public void setIsPay(int isPay) {
            this.isPay = isPay;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYTechnSedanId() {
            return yTechnSedanId;
        }

        public void setYTechnSedanId(String yTechnSedanId) {
            this.yTechnSedanId = yTechnSedanId;
        }

        public String getYOrderId() {
            return yOrderId;
        }

        public void setYOrderId(String yOrderId) {
            this.yOrderId = yOrderId;
        }

        public String getYSedanBrandId() {
            return ySedanBrandId;
        }

        public void setYSedanBrandId(String ySedanBrandId) {
            this.ySedanBrandId = ySedanBrandId;
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

        public String getLicenseNumber() {
            return licenseNumber;
        }

        public void setLicenseNumber(String licenseNumber) {
            this.licenseNumber = licenseNumber;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public String getOwnerPhone() {
            return ownerPhone;
        }

        public void setOwnerPhone(String ownerPhone) {
            this.ownerPhone = ownerPhone;
        }

        public String getFrameNumber() {
            return frameNumber;
        }

        public void setFrameNumber(String frameNumber) {
            this.frameNumber = frameNumber;
        }

        public String getVehicleMileage() {
            return vehicleMileage;
        }

        public void setVehicleMileage(String vehicleMileage) {
            this.vehicleMileage = vehicleMileage;
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

        public String getVRemarks() {
            return vRemarks;
        }

        public void setVRemarks(String vRemarks) {
            this.vRemarks = vRemarks;
        }

        public String getSedanBrandJson() {
            return sedanBrandJson;
        }

        public void setSedanBrandJson(String sedanBrandJson) {
            this.sedanBrandJson = sedanBrandJson;
        }

        public double getGPrice() {
            return gPrice;
        }

        public void setGPrice(double gPrice) {
            this.gPrice = gPrice;
        }

        public int getIsOrder() {
            return isOrder;
        }

        public void setIsOrder(int isOrder) {
            this.isOrder = isOrder;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getServiceStr() {
            return serviceStr;
        }

        public void setServiceStr(String serviceStr) {
            this.serviceStr = serviceStr;
        }

        public int getGState() {
            return gState;
        }

        public void setGState(int gState) {
            this.gState = gState;
        }

        public TechnInfoBean getTechn_info() {
            return techn_info;
        }

        public void setTechn_info(TechnInfoBean techn_info) {
            this.techn_info = techn_info;
        }

        public OrderInfoBean getOrder_info() {
            return order_info;
        }

        public void setOrder_info(OrderInfoBean order_info) {
            this.order_info = order_info;
        }

        public String getDistributeJson() {
            return distributeJson;
        }

        public void setDistributeJson(String distributeJson) {
            this.distributeJson = distributeJson;
        }

        public BrandInfoBean getBrandInfo() {
            return brandInfo;
        }

        public void setBrandInfo(BrandInfoBean brandInfo) {
            this.brandInfo = brandInfo;
        }

        public String getAppeImgstr() {
            return appeImgstr;
        }

        public void setAppeImgstr(String appeImgstr) {
            this.appeImgstr = appeImgstr;
        }

        public String getEstimateTime() {
            return estimateTime;
        }

        public void setEstimateTime(String estimateTime) {
            this.estimateTime = estimateTime;
        }

        public double getRewardMoney() {
            return rewardMoney;
        }

        public void setRewardMoney(double rewardMoney) {
            this.rewardMoney = rewardMoney;
        }

        public static class TechnInfoBean implements Serializable{
            /**
             * id : 0
             * userId : 719539276219416576
             * userBalance : 0.0
             * userName : 阿斯顿马丁
             * userIntegral : 0
             * yStoreId : 0
             * userHash : 843B1049032A7E7C9F7D2E2CBE6D37A6
             * headPortrait : /upload/2020-06-18/20200618164348_110881.jpg
             * userPhone : 18306043085
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

        public static class OrderInfoBean implements Serializable{
            /**
             * id : 1081
             * yOrderId : 728916430245855232
             * userId : 720416799337742336
             * yStoreId : 692341585785913344
             * yTechnSedanId : 728917123266510848
             * yUserSedanId : 728681233067802624
             * iClassify : 1
             * gPrice : 8460.0
             * longitude : 104.007352
             * latitude : 30.637283
             * gState : 1
             * createDate : 2020-07-04 10:13:39
             * isDelivery : 0
             * isPick : 0
             */

            private String id;
            private String yOrderId;
            private String userId;
            private String yStoreId;
            private String yTechnSedanId;
            private String yUserSedanId;
            private int iClassify;
            private double gPrice;
            private String longitude;
            private String latitude;
            private int gState;
            private String createDate;
            private int isDelivery;
            private int isPick;

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

            public String getYTechnSedanId() {
                return yTechnSedanId;
            }

            public void setYTechnSedanId(String yTechnSedanId) {
                this.yTechnSedanId = yTechnSedanId;
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
}
