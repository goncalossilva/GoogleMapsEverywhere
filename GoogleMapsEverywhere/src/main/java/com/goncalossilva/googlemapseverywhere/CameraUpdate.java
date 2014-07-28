package com.goncalossilva.googlemapseverywhere;

import com.goncalossilva.googlemapseverywhere.model.JavaScriptBridge;
import com.goncalossilva.googlemapseverywhere.model.LatLng;
import com.goncalossilva.googlemapseverywhere.util.LatLngBoundsUtils;
import com.goncalossilva.googlemapseverywhere.util.LatLngUtils;

import android.content.Context;

public final class CameraUpdate {
    private Scriptable mScriptable;

    CameraUpdate(Scriptable scriptable) {
        mScriptable = scriptable;
    }

    public void apply(Context context, JavaScriptBridge javaScriptBridge) {
        javaScriptBridge.evaluateJavascript(mScriptable.build(context));
    }

    static class LatLngZoom {
        private LatLng mLatLng;
        private float mZoom;

        public LatLngZoom(LatLng latLng, float zoom) {
            mLatLng = latLng;
            mZoom = zoom;
        }

        public CameraUpdate build() {
            return new CameraUpdate(new Scriptable() {
                @Override
                public String build(Context context) {
                    return context.getString(
                            R.string.camera_update_lat_lng_zoom_js, LatLngUtils.getJs(context, mLatLng), mZoom);
                }
            });
        }
    }

    static class LatLngBounds {
        private com.goncalossilva.googlemapseverywhere.model.LatLngBounds mLatLngBounds;

        public LatLngBounds(com.goncalossilva.googlemapseverywhere.model.LatLngBounds latLngBounds) {
            mLatLngBounds = latLngBounds;
        }

        public CameraUpdate build() {
            return new CameraUpdate(new Scriptable() {
                @Override
                public String build(Context context) {
                    return context.getString(
                            R.string.camera_update_lat_lng_bounds_js, LatLngBoundsUtils.getJs(context, mLatLngBounds));
                }
            });
        }
    }

    private static interface Scriptable {
        public String build(Context context);
    }
}
