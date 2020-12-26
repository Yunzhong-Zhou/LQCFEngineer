package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/20.
 */
public class Fragment2Model_JiuYuan implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable{
        /**
         * id : 1059
         * yRescueId : 738496816575676416
         * userId : 719539276219416576
         * yStoreId : 692341585785913344
         * yUserSedanId : 726432330045980672
         * fullName : 阿斯顿马丁
         * telephone : 18306043086
         * address : address
         * mType : 救援类型
         * carCondition : 车况描述
         * userSedanJson : {"annualReviewTime":"2020-06-29","brandJson":"{\"brandName\":\"保时捷\",\"groupName\":\"保时捷\",\"id\":89395,\"parentId\":716704215585521664,\"sLogo\":\"/upload/logo/716723979414405120.jpg\",\"sName\":\"2016款 Cayenne S E-Hybrid 3.0T\",\"seriesName\":\"Cayenne新能源\",\"vDispla\":\"4T\",\"vYear\":\"2015\",\"ySedanBrandId\":716704981389934592}","comInsuranceTime":"2020-06-29","compInsuranceTime":"2020-06-29","createDate":"2020-06-27 13:42:43","id":1099,"isDel":1,"isF":1,"jReportPoliceJson":"null","reportPoliceJson":"null","sCy":2,"sLogo":"/upload/logo/716723979414405120.jpg","sName":"2016款 Cayenne S E-Hybrid 3.0T","sNumber":"川A66666","userId":720416799337742336,"yUserSedanId":726432330045980672}
         * carImg : /upload/2020-06-20/20200620140152_82907.png||/upload/2020-06-20/20200620140152_747666.png||/upload/2020-06-20/20200620140152_769970.png||/upload/2020-06-20/20200620140152_777172.png||/upload/2020-06-20/20200620140152_285882.png||/upload/2020-06-20/20200620140152_842592.jpg
         * createDate : 2020-07-30 20:42:41
         * longitude : 116.49798
         * latitude : 39.916485
         * imgArr : ["/upload/2020-06-20/20200620140152_82907.png","/upload/2020-06-20/20200620140152_747666.png","/upload/2020-06-20/20200620140152_769970.png","/upload/2020-06-20/20200620140152_777172.png","/upload/2020-06-20/20200620140152_285882.png","/upload/2020-06-20/20200620140152_842592.jpg"]
         * user_sedan_info : {"id":"1099","yUserSedanId":"726432330045980672","userId":"720416799337742336","sName":"2016款 Cayenne S E-Hybrid 3.0T","sLogo":"/upload/logo/716723979414405120.jpg","sCy":2,"isF":1,"isDel":1,"reportPoliceJson":"null","jReportPoliceJson":"null","brandJson":"{\"brandName\":\"保时捷\",\"groupName\":\"保时捷\",\"id\":89395,\"parentId\":716704215585521664,\"sLogo\":\"/upload/logo/716723979414405120.jpg\",\"sName\":\"2016款 Cayenne S E-Hybrid 3.0T\",\"seriesName\":\"Cayenne新能源\",\"vDispla\":\"4T\",\"vYear\":\"2015\",\"ySedanBrandId\":716704981389934592}","sNumber":"川A66666","brandInfo":{"id":"89395","ySedanBrandId":"716704981389934592","parentId":"716704215585521664","sName":"2016款 Cayenne S E-Hybrid 3.0T","sLogo":"/upload/logo/716723979414405120.jpg","seriesName":"Cayenne新能源","groupName":"保时捷","brandName":"保时捷","vYear":"2015","vDispla":"4T"},"compInsuranceTime":"2020-06-29","comInsuranceTime":"2020-06-29","annualReviewTime":"2020-06-29","createDate":"2020-06-27 13:42:43"}
         * user_info : {"id":"0","userId":"0","userBalance":0,"userName":"技师阿斯顿马丁","userAccount":"THANU4","userIntegral":"0","yStoreId":"0","userHash":"843B1049032A7E7C9F7D2E2CBE6D37A6","headPortrait":"/upload/2020-06-11/20200611090404_650532.png","userPhone":"18306043085"}
         * vState : 0
         */

        private String id;
        private String yRescueId;
        private String userId;
        private String yStoreId;
        private String yUserSedanId;
        private String fullName;
        private String telephone;
        private String address;
        private String mType;
        private String carCondition;
        private String userSedanJson;
        private String carImg;
        private String createDate;
        private String longitude;
        private String latitude;
        private UserSedanInfoBean user_sedan_info;
        private UserInfoBean user_info;
        private int vState;
        private List<String> imgArr;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYRescueId() {
            return yRescueId;
        }

        public void setYRescueId(String yRescueId) {
            this.yRescueId = yRescueId;
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

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMType() {
            return mType;
        }

        public void setMType(String mType) {
            this.mType = mType;
        }

        public String getCarCondition() {
            return carCondition;
        }

        public void setCarCondition(String carCondition) {
            this.carCondition = carCondition;
        }

        public String getUserSedanJson() {
            return userSedanJson;
        }

        public void setUserSedanJson(String userSedanJson) {
            this.userSedanJson = userSedanJson;
        }

        public String getCarImg() {
            return carImg;
        }

        public void setCarImg(String carImg) {
            this.carImg = carImg;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
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

        public int getVState() {
            return vState;
        }

        public void setVState(int vState) {
            this.vState = vState;
        }

        public List<String> getImgArr() {
            return imgArr;
        }

        public void setImgArr(List<String> imgArr) {
            this.imgArr = imgArr;
        }

        public static class UserSedanInfoBean implements Serializable{
            /**
             * id : 1099
             * yUserSedanId : 726432330045980672
             * userId : 720416799337742336
             * sName : 2016款 Cayenne S E-Hybrid 3.0T
             * sLogo : /upload/logo/716723979414405120.jpg
             * sCy : 2
             * isF : 1
             * isDel : 1
             * reportPoliceJson : null
             * jReportPoliceJson : null
             * brandJson : {"brandName":"保时捷","groupName":"保时捷","id":89395,"parentId":716704215585521664,"sLogo":"/upload/logo/716723979414405120.jpg","sName":"2016款 Cayenne S E-Hybrid 3.0T","seriesName":"Cayenne新能源","vDispla":"4T","vYear":"2015","ySedanBrandId":716704981389934592}
             * sNumber : 川A66666
             * brandInfo : {"id":"89395","ySedanBrandId":"716704981389934592","parentId":"716704215585521664","sName":"2016款 Cayenne S E-Hybrid 3.0T","sLogo":"/upload/logo/716723979414405120.jpg","seriesName":"Cayenne新能源","groupName":"保时捷","brandName":"保时捷","vYear":"2015","vDispla":"4T"}
             * compInsuranceTime : 2020-06-29
             * comInsuranceTime : 2020-06-29
             * annualReviewTime : 2020-06-29
             * createDate : 2020-06-27 13:42:43
             */

            private String id;
            private String yUserSedanId;
            private String userId;
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

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getYUserSedanId() {
                return yUserSedanId;
            }

            public void setYUserSedanId(String yUserSedanId) {
                this.yUserSedanId = yUserSedanId;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
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
                 * id : 89395
                 * ySedanBrandId : 716704981389934592
                 * parentId : 716704215585521664
                 * sName : 2016款 Cayenne S E-Hybrid 3.0T
                 * sLogo : /upload/logo/716723979414405120.jpg
                 * seriesName : Cayenne新能源
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
             * userName : 技师阿斯顿马丁
             * userAccount : THANU4
             * userIntegral : 0
             * yStoreId : 0
             * userHash : 843B1049032A7E7C9F7D2E2CBE6D37A6
             * headPortrait : /upload/2020-06-11/20200611090404_650532.png
             * userPhone : 18306043085
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
    }
}
