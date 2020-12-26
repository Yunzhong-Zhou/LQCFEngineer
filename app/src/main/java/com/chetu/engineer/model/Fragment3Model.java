package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/5/25.
 */
public class Fragment3Model implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable{
        /**
         * id : 1075
         * yForumPostsId : 725351301054988288
         * userId : 719539276219416576
         * v_title : 阿斯顿马丁
         * iClassify : 8
         * iGive : 0
         * iLike : 0
         * vJson :
         * createDate : 2020-06-24 14:07:06
         * activity_info : {"v_title":"阿斯顿马丁","v_start_time":"2020-06-24","v_end_time":"2037-03-18","v_place":"阿斯顿阿斯顿项目","v_type":"啊水电费阿斯顿","v_content":"啊是多大阿斯顿阿斯顿阿斯顿","imgstr":"/upload/2020-06-24/20200624140706_193302.jpg||/upload/2020-06-24/20200624140706_451730.jpg||/upload/2020-06-24/20200624140706_741676.jpeg||/upload/2020-06-24/20200624140706_726200.jpeg||/upload/2020-06-24/20200624140706_396687.jpeg||/upload/2020-06-24/20200624140706_412154.jpg","imgArr":["/upload/2020-06-24/20200624140706_193302.jpg","/upload/2020-06-24/20200624140706_451730.jpg","/upload/2020-06-24/20200624140706_741676.jpeg","/upload/2020-06-24/20200624140706_726200.jpeg","/upload/2020-06-24/20200624140706_396687.jpeg","/upload/2020-06-24/20200624140706_412154.jpg"]}
         * user_info : {"id":"0","userId":"719539276219416576","userBalance":0,"userName":"阿斯顿马丁","userIntegral":"0","yStoreId":"0","headPortrait":"/upload/2020-06-18/20200618164348_110881.jpg","userPhone":"18306043085"}
         * is_give : 0
         * is_like : 0
         */

        private String id;
        private String yForumPostsId;
        private String userId;
        private String v_title;
        private int iClassify;
        private int iGive;
        private int iLike;
        private String vJson;
        private String createDate;
        private ActivityInfoBean activity_info;
        private RecruitInfoBean recruit_info;
        private ExchangeInfoBean exchange_info;
        private CaseInfoBean case_info;
        private HelpInfoBean help_info;
        private ToolInfoBean tool_info;
        private LeaseInfoBean lease_info;
        private PartsInfoBean parts_info;
        private UserInfoBean user_info;



        private int is_give;
        private int is_like;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYForumPostsId() {
            return yForumPostsId;
        }

        public void setYForumPostsId(String yForumPostsId) {
            this.yForumPostsId = yForumPostsId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getV_title() {
            return v_title;
        }

        public void setV_title(String v_title) {
            this.v_title = v_title;
        }

        public int getIClassify() {
            return iClassify;
        }

        public void setIClassify(int iClassify) {
            this.iClassify = iClassify;
        }

        public int getIGive() {
            return iGive;
        }

        public void setIGive(int iGive) {
            this.iGive = iGive;
        }

        public int getILike() {
            return iLike;
        }

        public void setILike(int iLike) {
            this.iLike = iLike;
        }

        public String getVJson() {
            return vJson;
        }

        public void setVJson(String vJson) {
            this.vJson = vJson;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public ActivityInfoBean getActivity_info() {
            return activity_info;
        }

        public void setActivity_info(ActivityInfoBean activity_info) {
            this.activity_info = activity_info;
        }

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public int getIs_give() {
            return is_give;
        }

        public void setIs_give(int is_give) {
            this.is_give = is_give;
        }

        public int getIs_like() {
            return is_like;
        }

        public void setIs_like(int is_like) {
            this.is_like = is_like;
        }

        public static class ActivityInfoBean implements Serializable{
            /**
             * v_title : 阿斯顿马丁
             * v_start_time : 2020-06-24
             * v_end_time : 2037-03-18
             * v_place : 阿斯顿阿斯顿项目
             * v_type : 啊水电费阿斯顿
             * v_content : 啊是多大阿斯顿阿斯顿阿斯顿
             * imgstr : /upload/2020-06-24/20200624140706_193302.jpg||/upload/2020-06-24/20200624140706_451730.jpg||/upload/2020-06-24/20200624140706_741676.jpeg||/upload/2020-06-24/20200624140706_726200.jpeg||/upload/2020-06-24/20200624140706_396687.jpeg||/upload/2020-06-24/20200624140706_412154.jpg
             * imgArr : ["/upload/2020-06-24/20200624140706_193302.jpg","/upload/2020-06-24/20200624140706_451730.jpg","/upload/2020-06-24/20200624140706_741676.jpeg","/upload/2020-06-24/20200624140706_726200.jpeg","/upload/2020-06-24/20200624140706_396687.jpeg","/upload/2020-06-24/20200624140706_412154.jpg"]
             */

            private String v_title;
            private String v_start_time;
            private String v_end_time;
            private String v_place;
            private String v_type;
            private String v_content;
            private String imgstr;
            private List<String> imgArr;

            public String getV_title() {
                return v_title;
            }

            public void setV_title(String v_title) {
                this.v_title = v_title;
            }

            public String getV_start_time() {
                return v_start_time;
            }

            public void setV_start_time(String v_start_time) {
                this.v_start_time = v_start_time;
            }

            public String getV_end_time() {
                return v_end_time;
            }

            public void setV_end_time(String v_end_time) {
                this.v_end_time = v_end_time;
            }

            public String getV_place() {
                return v_place;
            }

            public void setV_place(String v_place) {
                this.v_place = v_place;
            }

            public String getV_type() {
                return v_type;
            }

            public void setV_type(String v_type) {
                this.v_type = v_type;
            }

            public String getV_content() {
                return v_content;
            }

            public void setV_content(String v_content) {
                this.v_content = v_content;
            }

            public String getImgstr() {
                return imgstr;
            }

            public void setImgstr(String imgstr) {
                this.imgstr = imgstr;
            }

            public List<String> getImgArr() {
                return imgArr;
            }

            public void setImgArr(List<String> imgArr) {
                this.imgArr = imgArr;
            }
        }

        public RecruitInfoBean getRecruit_info() {
            return recruit_info;
        }

        public void setRecruit_info(RecruitInfoBean recruit_info) {
            this.recruit_info = recruit_info;
        }
        public static class RecruitInfoBean implements Serializable{
            /**
             * position : position
             * nameStore : namestore
             * telephone : telephone
             * salary : salary
             * handsOn :
             * address : address
             */

            private String position;
            private String nameStore;
            private String telephone;
            private String salary;
            private String handsOn;
            private String address;

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getNameStore() {
                return nameStore;
            }

            public void setNameStore(String nameStore) {
                this.nameStore = nameStore;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getSalary() {
                return salary;
            }

            public void setSalary(String salary) {
                this.salary = salary;
            }

            public String getHandsOn() {
                return handsOn;
            }

            public void setHandsOn(String handsOn) {
                this.handsOn = handsOn;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }

        public PartsInfoBean getParts_info() {
            return parts_info;
        }

        public void setParts_info(PartsInfoBean parts_info) {
            this.parts_info = parts_info;
        }

        public static class PartsInfoBean implements Serializable{
            /**
             * v_title : 标题
             * v_text : 转租
             * v_bring :
             * v_price : 1000
             * imgstr :  /static/goods/20200527150132.png||/static/goods/20200527150132.png
             * imgArr : [" /static/goods/20200527150132.png","/static/goods/20200527150132.png"]
             */

            private String v_title;
            private String v_text;
            private String v_bring;
            private String v_price;
            private String imgstr;
            private List<String> imgArr;

            public String getV_title() {
                return v_title;
            }

            public void setV_title(String v_title) {
                this.v_title = v_title;
            }

            public String getV_text() {
                return v_text;
            }

            public void setV_text(String v_text) {
                this.v_text = v_text;
            }

            public String getV_bring() {
                return v_bring;
            }

            public void setV_bring(String v_bring) {
                this.v_bring = v_bring;
            }

            public String getV_price() {
                return v_price;
            }

            public void setV_price(String v_price) {
                this.v_price = v_price;
            }

            public String getImgstr() {
                return imgstr;
            }

            public void setImgstr(String imgstr) {
                this.imgstr = imgstr;
            }

            public List<String> getImgArr() {
                return imgArr;
            }

            public void setImgArr(List<String> imgArr) {
                this.imgArr = imgArr;
            }
        }
        public LeaseInfoBean getLease_info() {
            return lease_info;
        }

        public void setLease_info(LeaseInfoBean lease_info) {
            this.lease_info = lease_info;
        }
        public static class LeaseInfoBean implements Serializable{
            /**
             * v_title : Eqweqw
             * contacts : Weewrew
             * contact_infor : 13213123
             * v_cost : 32132
             * v_address : 3rwerew
             * v_text : Grgfdgdfgdfg
             * imgstr : /upload/2020-06-21/20200621172702_290517.jpg
             * imgArr : ["/upload/2020-06-21/20200621172702_290517.jpg"]
             */

            private String v_title;
            private String contacts;
            private String contact_infor;
            private String v_cost;
            private String v_address;
            private String v_text;
            private String imgstr;
            private List<String> imgArr;

            public String getV_title() {
                return v_title;
            }

            public void setV_title(String v_title) {
                this.v_title = v_title;
            }

            public String getContacts() {
                return contacts;
            }

            public void setContacts(String contacts) {
                this.contacts = contacts;
            }

            public String getContact_infor() {
                return contact_infor;
            }

            public void setContact_infor(String contact_infor) {
                this.contact_infor = contact_infor;
            }

            public String getV_cost() {
                return v_cost;
            }

            public void setV_cost(String v_cost) {
                this.v_cost = v_cost;
            }

            public String getV_address() {
                return v_address;
            }

            public void setV_address(String v_address) {
                this.v_address = v_address;
            }

            public String getV_text() {
                return v_text;
            }

            public void setV_text(String v_text) {
                this.v_text = v_text;
            }

            public String getImgstr() {
                return imgstr;
            }

            public void setImgstr(String imgstr) {
                this.imgstr = imgstr;
            }

            public List<String> getImgArr() {
                return imgArr;
            }

            public void setImgArr(List<String> imgArr) {
                this.imgArr = imgArr;
            }
        }
        public ToolInfoBean getTool_info() {
            return tool_info;
        }

        public void setTool_info(ToolInfoBean tool_info) {
            this.tool_info = tool_info;
        }
        public static class ToolInfoBean implements Serializable{
            /**
             * v_title : Ewe
             * v_price : ewe
             * v_specifi : ewew
             * v_duration : ewew
             * v_text : Fsfsdf
             * imgstr : /upload/2020-06-21/20200621174302_301656.jpg||/upload/2020-06-21/20200621174302_250612.jpg
             * imgArr : ["/upload/2020-06-21/20200621174302_301656.jpg","/upload/2020-06-21/20200621174302_250612.jpg"]
             */

            private String v_title;
            private String v_price;
            private String v_specifi;
            private String v_duration;
            private String v_text;
            private String imgstr;
            private List<String> imgArr;

            public String getV_title() {
                return v_title;
            }

            public void setV_title(String v_title) {
                this.v_title = v_title;
            }

            public String getV_price() {
                return v_price;
            }

            public void setV_price(String v_price) {
                this.v_price = v_price;
            }

            public String getV_specifi() {
                return v_specifi;
            }

            public void setV_specifi(String v_specifi) {
                this.v_specifi = v_specifi;
            }

            public String getV_duration() {
                return v_duration;
            }

            public void setV_duration(String v_duration) {
                this.v_duration = v_duration;
            }

            public String getV_text() {
                return v_text;
            }

            public void setV_text(String v_text) {
                this.v_text = v_text;
            }

            public String getImgstr() {
                return imgstr;
            }

            public void setImgstr(String imgstr) {
                this.imgstr = imgstr;
            }

            public List<String> getImgArr() {
                return imgArr;
            }

            public void setImgArr(List<String> imgArr) {
                this.imgArr = imgArr;
            }
        }
        public HelpInfoBean getHelp_info() {
            return help_info;
        }

        public void setHelp_info(HelpInfoBean help_info) {
            this.help_info = help_info;
        }
        public static class HelpInfoBean implements Serializable{
            /**
             * v_title : 标题
             * v_text : 1000
             * v_address : 规格
             * imgstr :  /static/goods/20200527150132.png||/static/goods/20200527150132.png
             * imgArr : [" /static/goods/20200527150132.png","/static/goods/20200527150132.png"]
             */

            private String v_title;
            private String v_text;
            private String v_address;
            private String imgstr;
            private List<String> imgArr;

            public String getV_title() {
                return v_title;
            }

            public void setV_title(String v_title) {
                this.v_title = v_title;
            }

            public String getV_text() {
                return v_text;
            }

            public void setV_text(String v_text) {
                this.v_text = v_text;
            }

            public String getV_address() {
                return v_address;
            }

            public void setV_address(String v_address) {
                this.v_address = v_address;
            }

            public String getImgstr() {
                return imgstr;
            }

            public void setImgstr(String imgstr) {
                this.imgstr = imgstr;
            }

            public List<String> getImgArr() {
                return imgArr;
            }

            public void setImgArr(List<String> imgArr) {
                this.imgArr = imgArr;
            }
        }
        public CaseInfoBean getCase_info() {
            return case_info;
        }

        public void setCase_info(CaseInfoBean case_info) {
            this.case_info = case_info;
        }
        public static class CaseInfoBean implements Serializable{
            /**
             * v_title : Wee
             * v_text : Fdfsdfsdfsdfsd
             * imgstr : /upload/2020-06-21/20200621173552_976217.jpg||/upload/2020-06-21/20200621173552_909208.jpg
             * imgArr : ["/upload/2020-06-21/20200621173552_976217.jpg","/upload/2020-06-21/20200621173552_909208.jpg"]
             */

            private String v_title;
            private String v_text;
            private String imgstr;
            private List<String> imgArr;

            public String getV_title() {
                return v_title;
            }

            public void setV_title(String v_title) {
                this.v_title = v_title;
            }

            public String getV_text() {
                return v_text;
            }

            public void setV_text(String v_text) {
                this.v_text = v_text;
            }

            public String getImgstr() {
                return imgstr;
            }

            public void setImgstr(String imgstr) {
                this.imgstr = imgstr;
            }

            public List<String> getImgArr() {
                return imgArr;
            }

            public void setImgArr(List<String> imgArr) {
                this.imgArr = imgArr;
            }
        }
        public ExchangeInfoBean getExchange_info() {
            return exchange_info;
        }

        public void setExchange_info(ExchangeInfoBean exchange_info) {
            this.exchange_info = exchange_info;
        }
        public static class ExchangeInfoBean implements Serializable{
            /**
             * v_title : Try
             * v_text : Gguuugu
             * v_describe : Yuguguuggiii
             * imgstr : /upload/2020-06-21/20200621181008_123387.jpg||/upload/2020-06-21/20200621181008_454599.jpg
             * imgArr : ["/upload/2020-06-21/20200621181008_123387.jpg","/upload/2020-06-21/20200621181008_454599.jpg"]
             */

            private String v_title;
            private String v_text;
            private String v_describe;
            private String imgstr;
            private List<String> imgArr;

            public String getV_title() {
                return v_title;
            }

            public void setV_title(String v_title) {
                this.v_title = v_title;
            }

            public String getV_text() {
                return v_text;
            }

            public void setV_text(String v_text) {
                this.v_text = v_text;
            }

            public String getV_describe() {
                return v_describe;
            }

            public void setV_describe(String v_describe) {
                this.v_describe = v_describe;
            }

            public String getImgstr() {
                return imgstr;
            }

            public void setImgstr(String imgstr) {
                this.imgstr = imgstr;
            }

            public List<String> getImgArr() {
                return imgArr;
            }

            public void setImgArr(List<String> imgArr) {
                this.imgArr = imgArr;
            }
        }

        public static class UserInfoBean implements Serializable{
            /**
             * id : 0
             * userId : 719539276219416576
             * userBalance : 0.0
             * userName : 阿斯顿马丁
             * userIntegral : 0
             * yStoreId : 0
             * headPortrait : /upload/2020-06-18/20200618164348_110881.jpg
             * userPhone : 18306043085
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
