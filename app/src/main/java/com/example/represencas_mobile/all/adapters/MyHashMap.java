package com.example.represencas_mobile.all.adapters;

import androidx.annotation.NonNull;

public class MyHashMap {

    private int key;
    private String value;

    public MyHashMap(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    @NonNull
    @Override
    public String toString() {
        return value;
    }
}
