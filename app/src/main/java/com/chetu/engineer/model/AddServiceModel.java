package com.chetu.engineer.model;

import java.io.Serializable;

/**
 * Created by zyz on 2020/6/19.
 */
public class AddServiceModel implements Serializable {
    private String v_title;
    private String imgStr;

   /* public AddXunJiaModel(String v_title, String imgStr) {
        this.v_title = v_title;
        this.imgStr = imgStr;
    }*/

    public String getV_title() {
        return v_title;
    }

    public void setV_title(String v_title) {
        this.v_title = v_title;
    }

    public String getImgStr() {
        return imgStr;
    }

    public void setImgStr(String imgStr) {
        this.imgStr = imgStr;
    }
}
