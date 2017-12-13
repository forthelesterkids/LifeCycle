package com.lifecycle.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.lifecycle.application.LifecycleApplication;

public class LifecycleParceable implements Parcelable {

    private static final String TAG = "LifecycleParceable";
    private int mData;

    public LifecycleParceable(int mData){
        this.mData = mData;
        LifecycleApplication.addToMap(TAG, "public LifecycleParceable(int mData)");
    }
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mData);
        LifecycleApplication.addToMap(TAG, "public void writeToParcel(Parcel out, int flags)");
    }

    public static final Parcelable.Creator<LifecycleParceable> CREATOR
            = new Parcelable.Creator<LifecycleParceable>() {
        public LifecycleParceable createFromParcel(Parcel in) {
            return new LifecycleParceable(in);
        }

        public LifecycleParceable[] newArray(int size) {
            return new LifecycleParceable[size];
        }
    };

    private LifecycleParceable(Parcel in) {
        mData = in.readInt();
        LifecycleApplication.addToMap(TAG, "private LifecycleParceable(Parcel in)");
    }
}
