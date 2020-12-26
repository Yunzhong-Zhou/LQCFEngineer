package com.chetu.engineer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/6/29.
 */
public class SearchHoModel implements Serializable {
    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
