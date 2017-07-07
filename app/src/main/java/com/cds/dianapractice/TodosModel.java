package com.cds.dianapractice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fazal on 7/7/2017.
 */

public class TodosModel {
    private List<Data> todos;

    public List<Data> getTodos() {
        return todos;
    }
}
