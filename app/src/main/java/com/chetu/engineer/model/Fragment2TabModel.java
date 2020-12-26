package com.chetu.engineer.model;

import java.io.Serializable;

/**
 * Created by zyz on 2020/6/2.
 */
public class Fragment2TabModel implements Serializable {
    public Fragment2TabModel(String title, int num) {
        this.title = title;
        this.num = num;
    }

    String title;
    int num;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
