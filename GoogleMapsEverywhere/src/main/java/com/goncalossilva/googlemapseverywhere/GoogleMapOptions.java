package com.goncalossilva.googlemapseverywhere;

import com.goncalossilva.googlemapseverywhere.model.CameraPosition;

import android.os.Parcel;
import android.os.Parcelable;

public final class GoogleMapOptions implements Parcelable {
    private CameraPosition mCameraPosition;

    public GoogleMapOptions() {

    }

    public GoogleMapOptions camera(CameraPosition camera) {
        mCameraPosition = camera;
        return this;
    }

    public CameraPosition getCamera() {
        return mCameraPosition;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mCameraPosition, 0);
    }

    private GoogleMapOptions(Parcel in) {
        mCameraPosition = in.readParcelable(CameraPosition.class.getClassLoader());
    }

    public static final Parcelable.Creator<GoogleMapOptions> CREATOR = new Parcelable.Creator<GoogleMapOptions>() {
        public GoogleMapOptions createFromParcel(Parcel source) {
            return new GoogleMapOptions(source);
        }

        public GoogleMapOptions[] newArray(int size) {
            return new GoogleMapOptions[size];
        }
    };
}
