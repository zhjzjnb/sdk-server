package com.zsdk.server.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * The JSON format of this class is the communication protocol between the
 * back end server and javascript in front end
 */

public class Data<T> {
    private int total;
    private List<T> rows = new ArrayList<T>();

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
