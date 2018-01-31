package com.github.htdangkhoa.library;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by dangkhoa on 1/30/18.
 */

public class TinyRealmObject extends RealmObject implements Serializable {
    @PrimaryKey
    private String id;
    String key, value;

    public TinyRealmObject() {
    }

    public TinyRealmObject(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
