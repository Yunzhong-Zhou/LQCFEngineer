package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/7/16.
 */
public class MyCommentModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 50
         * yStoreEvalId : 730024302044250112
         * userId : 20180531104813
         * bUserId : 719539276219416576
         * yTechnSedanId : 728681233067802624
         * vCy : 1
         * starC : 5
         * yMsg : 456
         * createDate : 2020-07-07 11:35:56
         * userJson : {"headPortrait":" ","id":0,"userAccount":"6545764","userBalance":0.0,"userHash":"3B2372646663FDC7A81EA8E241CB7946AC74E4C0C9CFD31F750FEE2831528FF5","userId":0,"userIntegral":0,"userName":"龙肆","userPhone":18203048656,"yStoreId":0}
         * orderJson : {"imgStr":"/upload/logo/716723979414405120.jpg","serviceStr":"在前门右叶板子","y_order_id":728916430245855232}
         * user_info : {"id":"0","userId":"0","userBalance":0,"userName":"龙肆","userAccount":"6545764","userIntegral":"0","yStoreId":"0","userHash":"3B2372646663FDC7A81EA8E241CB7946AC74E4C0C9CFD31F750FEE2831528FF5","headPortrait":" ","userPhone":"18203048656"}
         * eval_order_info : {"imgStr":"/upload/logo/716723979414405120.jpg","y_order_id":"728916430245855232","serviceStr":"在前门右叶板子"}
         * reply_List : [{"yStoreEvalReplyId":"733353976950423552","replyMsg":"山沟沟丰富的","userJson":"{\"headPortrait\":\" \",\"id\":0,\"userAccount\":\"6545764\",\"userBalance\":0.0,\"userHash\":\"3B2372646663FDC7A81EA8E241CB7946AC74E4C0C9CFD31F750FEE2831528FF5\",\"userId\":0,\"userIntegral\":0,\"userName\":\"龙肆\",\"userPhone\":18203048656,\"yStoreId\":0}","user_info":{"id":"0","userId":"0","userBalance":0,"userName":"龙肆","userAccount":"6545764","userIntegral":"0","yStoreId":"0","userHash":"3B2372646663FDC7A81EA8E241CB7946AC74E4C0C9CFD31F750FEE2831528FF5","headPortrait":" ","userPhone":"18203048656"}}]
         * yStoreId : 692341585785913344
         * imgStr : /upload/2020-06-26/20200626234259_500551.png
         * imgArr : ["/upload/2020-06-26/20200626234259_500551.png"]
         */

        private String id;
        private String yStoreEvalId;
        private String userId;
        private String bUserId;
        private String yTechnSedanId;
        private int vCy;
        private String starC;
        private String yMsg;
        private String createDate;
        private String userJson;
        private String orderJson;
        private UserInfoBean user_info;
        private EvalOrderInfoBean eval_order_info;
        private String yStoreId;
        private String imgStr;
        private List<ReplyListBean> reply_List;
        private List<String> imgArr;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYStoreEvalId() {
            return yStoreEvalId;
        }

        public void setYStoreEvalId(String yStoreEvalId) {
            this.yStoreEvalId = yStoreEvalId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getBUserId() {
            return bUserId;
        }

        public void setBUserId(String bUserId) {
            this.bUserId = bUserId;
        }

        public String getYTechnSedanId() {
            return yTechnSedanId;
        }

        public void setYTechnSedanId(String yTechnSedanId) {
            this.yTechnSedanId = yTechnSedanId;
        }

        public int getVCy() {
            return vCy;
        }

        public void setVCy(int vCy) {
            this.vCy = vCy;
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

        public String getUserJson() {
            return userJson;
        }

        public void setUserJson(String userJson) {
            this.userJson = userJson;
        }

        public String getOrderJson() {
            return orderJson;
        }

        public void setOrderJson(String orderJson) {
            this.orderJson = orderJson;
        }

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public EvalOrderInfoBean getEval_order_info() {
            return eval_order_info;
        }

        public void setEval_order_info(EvalOrderInfoBean eval_order_info) {
            this.eval_order_info = eval_order_info;
        }

        public String getYStoreId() {
            return yStoreId;
        }

        public void setYStoreId(String yStoreId) {
            this.yStoreId = yStoreId;
        }

        public String getImgStr() {
            return imgStr;
        }

        public void setImgStr(String imgStr) {
            this.imgStr = imgStr;
        }

        public List<ReplyListBean> getReply_List() {
            return reply_List;
        }

        public void setReply_List(List<ReplyListBean> reply_List) {
            this.reply_List = reply_List;
        }

        public List<String> getImgArr() {
            return imgArr;
        }

        public void setImgArr(List<String> imgArr) {
            this.imgArr = imgArr;
        }

        public static class UserInfoBean {
            /**
             * id : 0
             * userId : 0
             * userBalance : 0.0
             * userName : 龙肆
             * userAccount : 6545764
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

        public static class EvalOrderInfoBean {
            /**
             * imgStr : /upload/logo/716723979414405120.jpg
             * y_order_id : 728916430245855232
             * serviceStr : 在前门右叶板子
             */

            private String imgStr;
            private String y_order_id;
            private String serviceStr;

            public String getImgStr() {
                return imgStr;
            }

            public void setImgStr(String imgStr) {
                this.imgStr = imgStr;
            }

            public String getY_order_id() {
                return y_order_id;
            }

            public void setY_order_id(String y_order_id) {
                this.y_order_id = y_order_id;
            }

            public String getServiceStr() {
                return serviceStr;
            }

            public void setServiceStr(String serviceStr) {
                this.serviceStr = serviceStr;
            }
        }

        public static class ReplyListBean {
            /**
             * yStoreEvalReplyId : 733353976950423552
             * replyMsg : 山沟沟丰富的
             * userJson : {"headPortrait":" ","id":0,"userAccount":"6545764","userBalance":0.0,"userHash":"3B2372646663FDC7A81EA8E241CB7946AC74E4C0C9CFD31F750FEE2831528FF5","userId":0,"userIntegral":0,"userName":"龙肆","userPhone":18203048656,"yStoreId":0}
             * user_info : {"id":"0","userId":"0","userBalance":0,"userName":"龙肆","userAccount":"6545764","userIntegral":"0","yStoreId":"0","userHash":"3B2372646663FDC7A81EA8E241CB7946AC74E4C0C9CFD31F750FEE2831528FF5","headPortrait":" ","userPhone":"18203048656"}
             */

            private String yStoreEvalReplyId;
            private String replyMsg;
            private String userJson;
            private UserInfoBeanX user_info;

            public String getYStoreEvalReplyId() {
                return yStoreEvalReplyId;
            }

            public void setYStoreEvalReplyId(String yStoreEvalReplyId) {
                this.yStoreEvalReplyId = yStoreEvalReplyId;
            }

            public String getReplyMsg() {
                return replyMsg;
            }

            public void setReplyMsg(String replyMsg) {
                this.replyMsg = replyMsg;
            }

            public String getUserJson() {
                return userJson;
            }

            public void setUserJson(String userJson) {
                this.userJson = userJson;
            }

            public UserInfoBeanX getUser_info() {
                return user_info;
            }

            public void setUser_info(UserInfoBeanX user_info) {
                this.user_info = user_info;
            }

            public static class UserInfoBeanX {
                /**
                 * id : 0
                 * userId : 0
                 * userBalance : 0.0
                 * userName : 龙肆
                 * userAccount : 6545764
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
}
