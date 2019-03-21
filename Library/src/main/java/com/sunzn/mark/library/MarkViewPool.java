package com.sunzn.mark.library;

import android.util.SparseArray;
import android.view.View;

public class MarkViewPool {

    private static SparseArray<View> sViewPool;

    static {
        sViewPool = new SparseArray<>();
    }

    public static View getView(int res) {
        return sViewPool.get(res);
    }

    public static void putView(int res, View view) {
        sViewPool.put(res, view);
    }

}
