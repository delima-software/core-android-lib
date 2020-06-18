# Add library

[![Alt](https://jitpack.io/v/org.bitbucket.virtualsoft-libs/core-android-lib.svg)](https://jitpack.io/#org.bitbucket.virtualsoft-libs/core-android-lib)

## Jitpack repository

Add the jitpack maven repository on root build.gradle:

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

## Dependency

Add the following line to app level build.gradle:

```groovy
implementation "org.bitbucket.virtualsoft-libs:core-android-lib:$latest_lib_version"
```
