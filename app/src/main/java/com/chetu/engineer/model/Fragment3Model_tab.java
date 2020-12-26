package com.chetu.engineer.model;

import java.io.Serializable;

/**
 * Created by zyz on 2020/6/23.
 */
public class Fragment3Model_tab implements Serializable {
    int image;
    String name;

    public Fragment3Model_tab(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
