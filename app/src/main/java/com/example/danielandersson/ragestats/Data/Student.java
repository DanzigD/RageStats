package com.example.danielandersson.ragestats.Data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

/**
 * Created by danielandersson on 2017-07-10.
 */

public class Student implements Parcelable {


    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
    private String mName;
    private String mStatDataKey;
    private HashMap<String, Boolean> mCommentsKeyMap;



    public Student(String name) {
        mName = name;
    }


    public Student() {
    }

    protected Student(Parcel in) {
        this.mName = in.readString();
        this.mStatDataKey = in.readString();
        this.mCommentsKeyMap = (HashMap<String, Boolean>) in.readSerializable();
    }

    public HashMap<String, Boolean> getCommentsKeyMap() {
        return mCommentsKeyMap;
    }

    public void setCommentsKeyMap(HashMap<String, Boolean> commentsKeyMap) {
        mCommentsKeyMap = commentsKeyMap;
    }

    public String getStatDataKey() {
        return mStatDataKey;
    }

    public void setStatDataKey(String statDataKey) {
        mStatDataKey = statDataKey;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mName);
        dest.writeString(this.mStatDataKey);
        dest.writeSerializable(this.mCommentsKeyMap);
    }

    public void addCommentKey(String key) {
        if (mCommentsKeyMap == null) {
            mCommentsKeyMap = new HashMap<String, Boolean>();
        }

        mCommentsKeyMap.put(key, true);
    }
}