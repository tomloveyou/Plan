package com.example.hp.testapplication;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author yl
 * @version 1.0
 * @date 2017/12/6 10:07
 */
public class Fly<T> implements Serializable {
    @SerializedName("track")
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
