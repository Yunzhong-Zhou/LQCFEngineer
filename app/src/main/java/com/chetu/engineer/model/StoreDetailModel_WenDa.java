package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/22.
 */
public class StoreDetailModel_WenDa implements Serializable {
    private int sum;

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1024
         * yStoreQuesAnsId : 715885772770639872
         * yParentId : 0
         * yStoreId : 692341585785913344
         * userId : 20180531104813
         * msg : 变速箱质量如何？
         * user_info : {"id":"0","userId":"20180531104813","userBalance":0,"userName":"龙肆","userIntegral":"0","yStoreId":"0","headPortrait":" ","userPhone":"18203048656"}
         * c_list : [{"id":"1023","yStoreQuesAnsId":"715885019494612992","yParentId":"715885772770639872","yStoreId":"692341585785913344","userId":"20180531104813","msg":"回答变速箱质量如何？","user_info":{"id":"0","userId":"20180531104813","userBalance":0,"userName":"龙肆","userIntegral":"0","yStoreId":"0","headPortrait":" ","userPhone":"18203048656"},"createDate":"2020-05-29 11:11:29"}]
         * createDate : 2020-05-29 11:14:28
         */
        private boolean isExpand = false;//表示展开和收起的状态。

        public boolean isExpand() {
            return isExpand;
        }

        public void setExpand(boolean expand) {
            isExpand = expand;
        }


        private String id;
        private String yStoreQuesAnsId;
        private String yParentId;
        private String yStoreId;
        private String userId;
        private String msg;
        private UserInfoBean user_info;
        private String createDate;
        private List<CListBean> c_list;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYStoreQuesAnsId() {
            return yStoreQuesAnsId;
        }

        public void setYStoreQuesAnsId(String yStoreQuesAnsId) {
            this.yStoreQuesAnsId = yStoreQuesAnsId;
        }

        public String getYParentId() {
            return yParentId;
        }

        public void setYParentId(String yParentId) {
            this.yParentId = yParentId;
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

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public List<CListBean> getC_list() {
            return c_list;
        }

        public void setC_list(List<CListBean> c_list) {
            this.c_list = c_list;
        }

        public static class UserInfoBean {
            /**
             * id : 0
             * userId : 20180531104813
             * userBalance : 0.0
             * userName : 龙肆
             * userIntegral : 0
             * yStoreId : 0
             * headPortrait :
             * userPhone : 18203048656
             */

            private String id;
            private String userId;
            private double userBalance;
            private String userName;
            private String userIntegral;
            private String yStoreId;
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

        public static class CListBean {
            /**
             * id : 1023
             * yStoreQuesAnsId : 715885019494612992
             * yParentId : 715885772770639872
             * yStoreId : 692341585785913344
             * userId : 20180531104813
             * msg : 回答变速箱质量如何？
             * user_info : {"id":"0","userId":"20180531104813","userBalance":0,"userName":"龙肆","userIntegral":"0","yStoreId":"0","headPortrait":" ","userPhone":"18203048656"}
             * createDate : 2020-05-29 11:11:29
             */

            private String id;
            private String yStoreQuesAnsId;
            private String yParentId;
            private String yStoreId;
            private String userId;
            private String msg;
            private UserInfoBeanX user_info;
            private String createDate;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getYStoreQuesAnsId() {
                return yStoreQuesAnsId;
            }

            public void setYStoreQuesAnsId(String yStoreQuesAnsId) {
                this.yStoreQuesAnsId = yStoreQuesAnsId;
            }

            public String getYParentId() {
                return yParentId;
            }

            public void setYParentId(String yParentId) {
                this.yParentId = yParentId;
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

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }

            public UserInfoBeanX getUser_info() {
                return user_info;
            }

            public void setUser_info(UserInfoBeanX user_info) {
                this.user_info = user_info;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public static class UserInfoBeanX {
                /**
                 * id : 0
                 * userId : 20180531104813
                 * userBalance : 0.0
                 * userName : 龙肆
                 * userIntegral : 0
                 * yStoreId : 0
                 * headPortrait :
                 * userPhone : 18203048656
                 */

                private String id;
                private String userId;
                private double userBalance;
                private String userName;
                private String userIntegral;
                private String yStoreId;
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
}
