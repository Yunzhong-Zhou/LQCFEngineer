package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/9.
 */
public class ServiceListModel_All implements Serializable {
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
         * yServiceId : 692341585785913344
         * yParentId : 0
         * vName : 保养
         * vImg : /upload/index/index1.png
         * isIndex : 1
         * createDate : 2020-03-25 11:58:17
         * v_list : [{"id":"1022","yServiceId":"692341585785913349","yParentId":"692341585785913344","vName":"小保养套餐","createDate":"2020-03-25 11:58:17","v_list":[{"id":"1029","yServiceId":"692341585785913354","yParentId":"692341585785913349","vName":"保养三级","createDate":"2020-03-25 11:58:17"},{"id":"1030","yServiceId":"692341585785913355","yParentId":"692341585785913349","vName":"保养三级","createDate":"2020-03-25 11:58:17"}]},{"id":"1023","yServiceId":"692341585785913350","yParentId":"692341585785913344","vName":"大保养","createDate":"2020-03-25 11:58:17","v_list":[{"id":"1033","yServiceId":"730114639173517312","yParentId":"692341585785913350","vName":"111","createDate":"2020-07-07 17:34:54"}]},{"id":"1024","yServiceId":"692341585785913351","yParentId":"692341585785913344","vName":"空调滤清器","createDate":"2020-03-25 11:58:17","v_list":[]},{"id":"1025","yServiceId":"692341585785913352","yParentId":"692341585785913344","vName":"节气门清洗","createDate":"2020-03-25 11:58:17","v_list":[]},{"id":"1026","yServiceId":"692341585785913353","yParentId":"692341585785913344","vName":"发动机清洗","createDate":"2020-03-25 11:58:17","v_list":[]}]
         */

        private String id;
        private String yServiceId;
        private String yParentId;
        private String vName;
        private String vImg;
        private int isIndex;
        private String createDate;
        private List<VListBeanX> v_list;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYServiceId() {
            return yServiceId;
        }

        public void setYServiceId(String yServiceId) {
            this.yServiceId = yServiceId;
        }

        public String getYParentId() {
            return yParentId;
        }

        public void setYParentId(String yParentId) {
            this.yParentId = yParentId;
        }

        public String getVName() {
            return vName;
        }

        public void setVName(String vName) {
            this.vName = vName;
        }

        public String getVImg() {
            return vImg;
        }

        public void setVImg(String vImg) {
            this.vImg = vImg;
        }

        public int getIsIndex() {
            return isIndex;
        }

        public void setIsIndex(int isIndex) {
            this.isIndex = isIndex;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public List<VListBeanX> getV_list() {
            return v_list;
        }

        public void setV_list(List<VListBeanX> v_list) {
            this.v_list = v_list;
        }

        public static class VListBeanX {
            /**
             * id : 1022
             * yServiceId : 692341585785913349
             * yParentId : 692341585785913344
             * vName : 小保养套餐
             * createDate : 2020-03-25 11:58:17
             * v_list : [{"id":"1029","yServiceId":"692341585785913354","yParentId":"692341585785913349","vName":"保养三级","createDate":"2020-03-25 11:58:17"},{"id":"1030","yServiceId":"692341585785913355","yParentId":"692341585785913349","vName":"保养三级","createDate":"2020-03-25 11:58:17"}]
             */

            private boolean isgouxuan = false;

            public boolean isIsgouxuan() {
                return isgouxuan;
            }

            public void setIsgouxuan(boolean isgouxuan) {
                this.isgouxuan = isgouxuan;
            }

            private String id;
            private String yServiceId;
            private String yParentId;
            private String vName;
            private String createDate;
            private List<VListBean> v_list;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getYServiceId() {
                return yServiceId;
            }

            public void setYServiceId(String yServiceId) {
                this.yServiceId = yServiceId;
            }

            public String getYParentId() {
                return yParentId;
            }

            public void setYParentId(String yParentId) {
                this.yParentId = yParentId;
            }

            public String getVName() {
                return vName;
            }

            public void setVName(String vName) {
                this.vName = vName;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public List<VListBean> getV_list() {
                return v_list;
            }

            public void setV_list(List<VListBean> v_list) {
                this.v_list = v_list;
            }

            public static class VListBean {
                /**
                 * id : 1029
                 * yServiceId : 692341585785913354
                 * yParentId : 692341585785913349
                 * vName : 保养三级
                 * createDate : 2020-03-25 11:58:17
                 */

                private boolean isgouxuan = false;

                public boolean isIsgouxuan() {
                    return isgouxuan;
                }

                public void setIsgouxuan(boolean isgouxuan) {
                    this.isgouxuan = isgouxuan;
                }

                private String id;
                private String yServiceId;
                private String yParentId;
                private String vName;
                private String createDate;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getYServiceId() {
                    return yServiceId;
                }

                public void setYServiceId(String yServiceId) {
                    this.yServiceId = yServiceId;
                }

                public String getYParentId() {
                    return yParentId;
                }

                public void setYParentId(String yParentId) {
                    this.yParentId = yParentId;
                }

                public String getVName() {
                    return vName;
                }

                public void setVName(String vName) {
                    this.vName = vName;
                }

                public String getCreateDate() {
                    return createDate;
                }

                public void setCreateDate(String createDate) {
                    this.createDate = createDate;
                }
            }
        }
    }
}
