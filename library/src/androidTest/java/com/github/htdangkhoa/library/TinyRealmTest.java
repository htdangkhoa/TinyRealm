package com.github.htdangkhoa.library;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmConfiguration;

import static org.junit.Assert.assertEquals;

/**
 * Created by dangkhoa on 1/30/18.
 */

@RunWith(AndroidJUnit4.class)
public class TinyRealmTest {
    Context context = InstrumentationRegistry.getTargetContext();

    @Test
    public void testDefaultRealmInstance() {
        new TinyRealm()
                .init(context)
                .getRealmInstance(TinyRealm.MODE.DEFAULT, null);

        TinyRealm.deleteAll();

        TinyRealm.putString("testString", "hello world!!!");
        assertEquals("hello world!!!", TinyRealm.findOne("testString").getValue());

        TinyRealm.putBoolean("testBoolean", true);
        assertEquals(true, Boolean.parseBoolean(TinyRealm.findOne("testBoolean").getValue()));

        TinyRealm.putInt("testInt", 10);
        assertEquals(10, Integer.parseInt(TinyRealm.findOne("testInt").getValue()));

        TinyRealm.putLong("testLong", 999999999999L);
        assertEquals(999999999999L, Long.parseLong(TinyRealm.findOne("testLong").getValue()));

        TinyRealm.putFloat("testFloat", 10.5f);
        assertEquals(10.5f, Float.parseFloat(TinyRealm.findOne("testFloat").getValue()), 0.0f);

        TinyRealm.putDouble("testDouble", 10.99999);
        assertEquals(10.99999, Double.parseDouble(TinyRealm.findOne("testDouble").getValue()), 0.0f);

        List<String> testList = new ArrayList<>();
        testList.add("A");
        testList.add("B");
        testList.add("C");
        TinyRealm.putList("testList", new ArrayList<Object>(testList));
        assertEquals(testList, TinyRealm.findList("testList", String.class));
    }

    @Test
    public void testEncryptedRealmInstance() {
        new TinyRealm()
                .init(context)
                .getRealmInstance(TinyRealm.MODE.ENCRYPTED, null);

        TinyRealm.deleteAll();

        TinyRealm.putString("testString", "hello world!!!");
        assertEquals("hello world!!!", TinyRealm.findOne("testString").getValue());

        TinyRealm.putBoolean("testBoolean", true);
        assertEquals(true, Boolean.parseBoolean(TinyRealm.findOne("testBoolean").getValue()));

        TinyRealm.putInt("testInt", 10);
        assertEquals(10, Integer.parseInt(TinyRealm.findOne("testInt").getValue()));

        TinyRealm.putLong("testLong", 999999999999L);
        assertEquals(999999999999L, Long.parseLong(TinyRealm.findOne("testLong").getValue()));

        TinyRealm.putFloat("testFloat", 10.5f);
        assertEquals(10.5f, Float.parseFloat(TinyRealm.findOne("testFloat").getValue()), 0.0f);

        TinyRealm.putDouble("testDouble", 10.99999);
        assertEquals(10.99999, Double.parseDouble(TinyRealm.findOne("testDouble").getValue()), 0.0f);

        List<String> testList = new ArrayList<>();
        testList.add("A");
        testList.add("B");
        testList.add("C");
        TinyRealm.putList("testList", new ArrayList<Object>(testList));
        assertEquals(testList, TinyRealm.findList("testList", String.class));
    }

    @Test
    public void testManualRealmInstance() {
        byte[] keys = new byte[64];
        new SecureRandom().nextBytes(keys);

        TinyRealm tinyRealm = new TinyRealm().init(context);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("test")
                .encryptionKey(keys)
                .build();
        tinyRealm.getRealmInstance(TinyRealm.MODE.MANUAL, realmConfiguration);

        TinyRealm.deleteAll();

        TinyRealm.putString("testString", "hello world!!!");
        assertEquals("hello world!!!", TinyRealm.findOne("testString").getValue());

        TinyRealm.putBoolean("testBoolean", true);
        assertEquals(true, Boolean.parseBoolean(TinyRealm.findOne("testBoolean").getValue()));

        TinyRealm.putInt("testInt", 10);
        assertEquals(10, Integer.parseInt(TinyRealm.findOne("testInt").getValue()));

        TinyRealm.putLong("testLong", 999999999999L);
        assertEquals(999999999999L, Long.parseLong(TinyRealm.findOne("testLong").getValue()));

        TinyRealm.putFloat("testFloat", 10.5f);
        assertEquals(10.5f, Float.parseFloat(TinyRealm.findOne("testFloat").getValue()), 0.0f);

        TinyRealm.putDouble("testDouble", 10.99999);
        assertEquals(10.99999, Double.parseDouble(TinyRealm.findOne("testDouble").getValue()), 0.0f);

        List<String> testList = new ArrayList<>();
        testList.add("A");
        testList.add("B");
        testList.add("C");
        TinyRealm.putList("testList", new ArrayList<Object>(testList));
        assertEquals(testList, TinyRealm.findList("testList", String.class));
    }
}
