package com.goncalossilva.googlemapseverywhere.model;

import android.os.Parcel;
import android.os.Parcelable;

public class LatLngBounds implements Parcelable {
    public final LatLng northeast;
    public final LatLng southwest;

    public LatLngBounds(LatLng southwest, LatLng northeast) {
        this.southwest = southwest;
        this.northeast = northeast;
    }

    public LatLng getCenter() {
        double latitudeCenter = (southwest.latitude + northeast.latitude) / 2D;
        double longitudeCenter;
        if (southwest.longitude <= northeast.longitude) {
            longitudeCenter = (northeast.longitude + southwest.longitude) / 2D;
        }
        else {
            longitudeCenter = (northeast.longitude + 360D + southwest.longitude) / 2D;
        }
        return new LatLng(latitudeCenter, longitudeCenter);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.northeast, 0);
        dest.writeParcelable(this.southwest, 0);
    }

    private LatLngBounds(Parcel in) {
        this.northeast = in.readParcelable(LatLng.class.getClassLoader());
        this.southwest = in.readParcelable(LatLng.class.getClassLoader());
    }

    public static final Parcelable.Creator<LatLngBounds> CREATOR = new Parcelable.Creator<LatLngBounds>() {
        public LatLngBounds createFromParcel(Parcel source) {
            return new LatLngBounds(source);
        }

        public LatLngBounds[] newArray(int size) {
            return new LatLngBounds[size];
        }
    };
}
