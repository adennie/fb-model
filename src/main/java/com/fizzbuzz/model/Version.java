package com.fizzbuzz.model;

public class Version {

    private final String mMajor; // null implies this type is not versioned
    private final String mMinor; // null or "" means any minor version

    public Version(final String major, final String minor) {
        mMajor = major;
        mMinor = minor;

    }

    public String getMajor() {
        return mMajor;
    }

    public String getMinor() {
        return mMinor;
    }
}
