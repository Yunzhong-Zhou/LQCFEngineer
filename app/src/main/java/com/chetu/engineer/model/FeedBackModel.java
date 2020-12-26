package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/5/29.
 */
public class FeedBackModel implements Serializable {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1017
         * yUserFeedbackClassifyId : 692341585785913344
         * yName : 产品相关
         */

        private String id;
        private String yUserFeedbackClassifyId;
        private String yName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYUserFeedbackClassifyId() {
            return yUserFeedbackClassifyId;
        }

        public void setYUserFeedbackClassifyId(String yUserFeedbackClassifyId) {
            this.yUserFeedbackClassifyId = yUserFeedbackClassifyId;
        }

        public String getYName() {
            return yName;
        }

        public void setYName(String yName) {
            this.yName = yName;
        }
    }
}
