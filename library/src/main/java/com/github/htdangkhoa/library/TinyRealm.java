package com.github.htdangkhoa.library;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by dangkhoa on 1/30/18.
 */

public class TinyRealm {
    public enum MODE {
        DEFAULT,
        ENCRYPTED,
        MANUAL
    }
    static Realm realm;

    /**
     * Init Realm database.
     * @param context
     * */
    public TinyRealm init(Context context) {
        io.realm.Realm.init(context);
        return this;
    }

    /**
     * Get default Realm configuration.
     * */
    public static RealmConfiguration getEncryptConfiguration() {
        byte[] key = new byte[64];
        new SecureRandom().nextBytes(key);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .encryptionKey(key)
                .build();

        return realmConfiguration;
    }

    /**
     * Get realm instance with mode (DEFAULT | ENCRYPTED | MANUAL).
     * @param mode
     * @param configuration
     * */
    public TinyRealm getRealmInstance(@NonNull MODE mode, @Nullable RealmConfiguration configuration) {
        switch (mode) {
            case DEFAULT: {
                realm = Realm.getDefaultInstance();
                break;
            }
            case ENCRYPTED: {
                realm = Realm.getInstance(getEncryptConfiguration());
                break;
            }
            case MANUAL: {
                if (configuration == null) throw new RuntimeException("RealmConfiguration is being null, please initialize RealmConfiguration.");

                realm = Realm.getInstance(configuration);
                break;
            }
        }

        return this;
    }

    /**
     * Get realm.
     * */
    public Realm getRealm() {
        checkRealm();

        return realm;
    }

    /**
     * Check realm is being null.
     * */
    private static void checkRealm() {
        if (realm == null) throw new RuntimeException("realm is being null, please call `getRealmInstance(MODE, RealmConfiguration)` before.");

        return;
    }

    /**
     * Put list object.
     * @param key
     * @param values
     * @param gson
     * */
    private static void putListObject(@NonNull String key, @Nullable List<Object> values, @NonNull Gson gson) {
        ArrayList<String> objects = new ArrayList<>();
        for (Object value : values) {
            objects.add(gson.toJson(value));
        }

        putListString(key, objects);
    }

    /**
     * Put list string.
     * @param key
     * @param values
     * */
    private static void putListString(@NonNull String key, @NonNull ArrayList<String> values) {
        String[] strings = values.toArray(new String[values.size()]);
        putString(key, TextUtils.join("‚‗‚", strings));
    }

    /**
     * Get list object.
     * @param key
     * @param aClass
     * @param gson
     * */
    private static ArrayList<Object> getListObject(@NonNull String key, @NonNull Class<?> aClass, @NonNull Gson gson) {
        ArrayList<String> strings = getListString(key);
        ArrayList<Object> objects = new ArrayList<Object>();

        for (String s : strings) {
            Object value = gson.fromJson(s, aClass);
            objects.add(value);
        }

        return objects;
    }

    /**
     * Get list string.
     * @param key
     * */
    private static ArrayList<String> getListString(String key) {
        TinyRealmObject tinyRealmObject = findOne(key);

        if (tinyRealmObject != null) {
            return new ArrayList<String>(
                Arrays.asList(TextUtils.split(tinyRealmObject.getValue(), "‚‗‚")));
        }

        return new ArrayList<>();
    }

    /**
     * Save string.
     * @param key
     * @param value
     * */
    public static void putString(@NonNull String key, @Nullable String value) {
        checkRealm();

        realm.beginTransaction();
        TinyRealmObject tinyRealmObject = realm.createObject(TinyRealmObject.class, UUID.randomUUID().toString());
        tinyRealmObject.setKey(key);
        tinyRealmObject.setValue(value);
        realm.commitTransaction();
    }

    /**
     * Save integer.
     * @param key
     * @param value
     * */
    public static void putInt(@NonNull String key, @Nullable int value) {
        checkRealm();

        realm.beginTransaction();
        TinyRealmObject tinyRealmObject = realm.createObject(TinyRealmObject.class, UUID.randomUUID().toString());
        tinyRealmObject.setKey(key);
        tinyRealmObject.setValue(String.valueOf(value));
        realm.commitTransaction();
    }

    /**
     * Save long.
     * @param key
     * @param value
     * */
    public static void putLong(@NonNull String key, @Nullable long value) {
        checkRealm();

        realm.beginTransaction();
        TinyRealmObject tinyRealmObject = realm.createObject(TinyRealmObject.class, UUID.randomUUID().toString());
        tinyRealmObject.setKey(key);
        tinyRealmObject.setValue(String.valueOf(value));
        realm.commitTransaction();
    }

    /**
     * Save float.
     * @param key
     * @param value
     * */
    public static void putFloat(@NonNull String key, @Nullable float value) {
        checkRealm();

        realm.beginTransaction();
        TinyRealmObject tinyRealmObject = realm.createObject(TinyRealmObject.class, UUID.randomUUID().toString());
        tinyRealmObject.setKey(key);
        tinyRealmObject.setValue(String.valueOf(value));
        realm.commitTransaction();
    }

    /**
     * Save double.
     * @param key
     * @param value
     * */
    public static void putDouble(@NonNull String key, @Nullable double value) {
        checkRealm();

        realm.beginTransaction();
        TinyRealmObject tinyRealmObject = realm.createObject(TinyRealmObject.class, UUID.randomUUID().toString());
        tinyRealmObject.setKey(key);
        tinyRealmObject.setValue(String.valueOf(value));
        realm.commitTransaction();
    }

    /**
     * Save boonlean.
     * @param key
     * @param value
     * */
    public static void putBoolean(@NonNull String key, @Nullable boolean value) {
        checkRealm();

        realm.beginTransaction();
        TinyRealmObject tinyRealmObject = realm.createObject(TinyRealmObject.class, UUID.randomUUID().toString());
        tinyRealmObject.setKey(key);
        tinyRealmObject.setValue(String.valueOf(value));
        realm.commitTransaction();
    }

    /**
     * Save list.
     * @param key
     * @param values
     * */
    public static void putList(@NonNull String key, @Nullable List<Object> values) {
        putListObject(key, values, new Gson());
    }

    /**
     * Find all records.
     * @param key
     * */
    public static List<TinyRealmObject> find(@NonNull String key) {
        checkRealm();

        List<TinyRealmObject> objects = realm.where(TinyRealmObject.class)
                .equalTo("key", key)
                .findAll();

        return objects;
    }

    /**
     * Find one record.
     * @param key
     * */
    public static TinyRealmObject findOne(@NonNull String key) {
        checkRealm();

        TinyRealmObject tinyRealmObject = realm.where(TinyRealmObject.class)
                .equalTo("key", key)
                .findFirst();

        return tinyRealmObject;
    }

    /**
     * Find list record.
     * @param key
     * @param aClass
     * */
    public static ArrayList<Object> findList(@NonNull String key, @NonNull Class<?> aClass) {
        return getListObject(key, aClass, new Gson());
    }

    /**
     * Update record.
     * @param key
     * @param newValue
     * */
    public static TinyRealmObject update(@NonNull String key, @Nullable String newValue) {
        checkRealm();

        realm.beginTransaction();
        TinyRealmObject tinyRealmObject = findOne(key);
        if (tinyRealmObject == null) throw new RuntimeException("Data of " + key + " is being null, please save data before.");

        tinyRealmObject.setValue(newValue);
        realm.commitTransaction();

        return tinyRealmObject;
    }

    /**
     * Update multiple records.
     * @param key
     * @param newValue
     * */
    public static List<TinyRealmObject> multipleUpdate(@NonNull String key, @Nullable String newValue) {
        checkRealm();

        realm.beginTransaction();
        List<TinyRealmObject> objects = find(key);
        for (TinyRealmObject tinyRealmObject : objects) {
            tinyRealmObject.setValue(newValue);
        }
        realm.commitTransaction();

        return objects;
    }

    /**
     * Delete record.
     * @param key
     * */
    public static TinyRealmObject delete(@NonNull String key) {
        checkRealm();

        realm.beginTransaction();
        TinyRealmObject tinyRealmObject = findOne(key);
        tinyRealmObject.deleteFromRealm();
        realm.commitTransaction();

        return tinyRealmObject;
    }

    /**
     * Delete all records.
     * */
    public static void deleteAll() {
        checkRealm();

        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    /**
     * Manual execute transaction.
     * @param transaction
     * */
    public static void execute(io.realm.Realm.Transaction transaction) {
        checkRealm();

        realm.beginTransaction();
        realm.executeTransaction(transaction);
    }
}
