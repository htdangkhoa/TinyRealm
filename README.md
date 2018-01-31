# TinyRealm [![](https://jitpack.io/v/htdangkhoa/TinyRealm.svg)](https://jitpack.io/#htdangkhoa/TinyRealm)
This project is a simple NoSQL client for Android is used Realm database.

## Feature
  - **Simple:** TinyRealm is designed with key - value format. It's realy easy to use.
  - **Fast:** TinyRealm is faster than even raw SQLite on common operations while maintaining an extremely rich feature set.
  
## Installation
Add the JitPack repository to your root build.gradle at the end of repositories:
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
Add the dependency:
```gradle
dependencies {
        compile 'com.github.htdangkhoa:TinyRealm:v1.0.0'
}
```

## Usage
```java
/**
* Initialize realm.
**/
new TinyRealm()
      .init(context)
      .getRealmInstance(TinyRealm.MODE.DEFAULT, null);

/**
* Save string value.
**/
TinyRealm.putString("testString", "hello world!!!");

/**
* Save boolean value.
**/
TinyRealm.putBoolean("testBoolean", true);

/**
* Save integer value.
**/
TinyRealm.putInt("testInt", 10);

/**
* Save long value.
**/
TinyRealm.putLong("testLong", 999999999999L);

/**
* Save float value.
**/
TinyRealm.putFloat("testFloat", 10.5f);

/**
* Save double value.
**/
TinyRealm.putDouble("testDouble", 10.99999);

/**
* Save list object value.
**/
List<String> testList = new ArrayList<>();
testList.add("A");
testList.add("B");
testList.add("C");
TinyRealm.putList("testList", new ArrayList<Object>(testList));

/**
* Find value.
**/
TinyRealm.findOne("testString");

/**
* Find list TinyRealmObject.
**/
TinyRealm.find("key");

/**
* Update value.
**/
TinyRealm.update("key", "new_value");

/**
* Delete value.
**/
TinyRealm.delete("key");

/**
* Delete all values.
**/
TinyRealm.deleteAll();
```
