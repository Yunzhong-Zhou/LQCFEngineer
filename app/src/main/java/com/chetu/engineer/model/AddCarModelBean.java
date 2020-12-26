package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/5/30.
 */
public class AddCarModelBean implements Serializable {
    private List<ListBean> list;
    private List<HostListBean> host_list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<HostListBean> getHost_list() {
        return host_list;
    }

    public void setHost_list(List<HostListBean> host_list) {
        this.host_list = host_list;
    }

    public static class ListBean {
        /**
         * id : 59138
         * ySedanBrandId : 716704191015288832
         * parentId : 0
         * sName : AC Schnitzer
         * sLogo : /upload/head/716723974607732736.jpg
         * sLetter : A
         */

        private String id;
        private String ySedanBrandId;
        private String parentId;
        private String sName;
        private String sLogo;
        private String sLetter;

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

        public String getSLetter() {
            return sLetter;
        }

        public void setSLetter(String sLetter) {
            this.sLetter = sLetter;
        }
    }

    public static class HostListBean {
        /**
         * id : 59138
         * ySedanBrandId : 716704191015288832
         * parentId : 0
         * sName : AC Schnitzer
         * sLogo : /upload/head/716723974607732736.jpg
         * sLetter : A
         */

        private String id;
        private String ySedanBrandId;
        private String parentId;
        private String sName;
        private String sLogo;
        private String sLetter;

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

        public String getSLetter() {
            return sLetter;
        }

        public void setSLetter(String sLetter) {
            this.sLetter = sLetter;
        }
    }
}
