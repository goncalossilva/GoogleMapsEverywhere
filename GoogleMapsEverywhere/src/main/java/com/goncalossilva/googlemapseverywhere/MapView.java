package com.goncalossilva.googlemapseverywhere;

import com.goncalossilva.googlemapseverywhere.model.CameraPosition;
import com.goncalossilva.googlemapseverywhere.model.JavaScriptBridge;
import com.goncalossilva.googlemapseverywhere.model.LatLng;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MapView extends FrameLayout {
    private GoogleMapView mGoogleMapView;
    private GoogleMapOptions mGoogleMapOptions;

    public MapView(Context context) {
        super(context);
    }

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MapView(Context context, GoogleMapOptions options) {
        super(context);
        mGoogleMapOptions = options;
    }

    public void onCreate(Bundle savedInstanceState) {
        mGoogleMapView = new GoogleMapView(getContext(), savedInstanceState, mGoogleMapOptions);
        addView(mGoogleMapView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    public void onStart() {
        // Do nothing.
    }

    public void onResume() {
        // Do nothing.
    }

    public void onLowMemory() {
        // Do nothing.
    }

    public void onSaveInstanceState(Bundle outState) {
        mGoogleMapView.onSaveInstanceState(outState);
    }

    public void onPause() {
        // Do nothing.
    }

    public void onStop() {
        // Do nothing.
    }

    public void onDestroy() {
        removeView(mGoogleMapView);
        mGoogleMapView.destroy();
    }

    public GoogleMap getMap() {
        return mGoogleMapView != null ? mGoogleMapView.getMap() : null;
    }

    private static class GoogleMapView extends WebView {
        private static final String ID_MAP = "map-canvas";

        private static final String KEY_CENTER = ":google_map_view_center";
        private static final String KEY_ZOOM = ":google_map_view_zoom";

        private static final LatLng DEFAULT_CENTER = new LatLng(0f, 0f);
        private static final float DEFAULT_ZOOM = 2;

        private LatLng mCenter;
        private float mZoom;

        private JavaScriptInterface mJavaScriptInterface;

        private GoogleMap mGoogleMap;

        public GoogleMapView(Context context, Bundle savedInstanceState, GoogleMapOptions options) {
            super(context);

            WebSettings settings = getSettings();

            // Enable JavaScript.
            settings.setJavaScriptEnabled(true);

            // Automatically grant location permissions.
            settings.setGeolocationEnabled(true);
            setWebChromeClient(new WebChromeClient() {
                @Override
                public void onGeolocationPermissionsShowPrompt(String origin,
                        GeolocationPermissions.Callback callback) {
                    callback.invoke(origin, true, false);
                }
            });

            // Extract API_KEY from meta-data.
            String apiKey;
            try {
                ApplicationInfo applicationInfo =
                        context.getPackageManager().getApplicationInfo(
                                context.getPackageName(),
                                PackageManager.GET_META_DATA);
                apiKey = applicationInfo.metaData.getString("com.google.android.maps.v3.API_KEY");
            } catch (NullPointerException | PackageManager.NameNotFoundException e) {
                throw new IllegalStateException(
                        "com.google.android.maps.v3.API_KEY must be set in AndroidManifest.xml");
            }

            // Bind JavaScript interface.
            mJavaScriptInterface = new JavaScriptInterface(apiKey);
            addJavascriptInterface(mJavaScriptInterface, "JavaScriptInterface");

            // Set options, center, and zoom. If options are specified, center / zoom fetch from it. If not, options
            // are created based on center / zoom which in turn depend on the savedInstanceState.
            if (options != null) {
                mCenter = options.getCamera().target;
                mZoom = options.getCamera().zoom;
            } else {
                if (savedInstanceState != null) {
                    mCenter = savedInstanceState.getParcelable(KEY_CENTER);
                    mZoom = savedInstanceState.getFloat(KEY_ZOOM);
                } else {
                    mCenter = DEFAULT_CENTER;
                    mZoom = DEFAULT_ZOOM;
                }
                options =
                        new GoogleMapOptions().camera(new CameraPosition.Builder().target(mCenter).zoom(mZoom).build());
            }

            // Create GoogleMap object.
            mGoogleMap = new GoogleMap(context.getApplicationContext(), mJavaScriptInterface, ID_MAP, options);
        }

        @Override
        protected void onSizeChanged(int w, int h, int ow, int oh) {
            super.onSizeChanged(w, h, ow, oh);


            if(w != 0 && h != 0 && ow == 0 && oh == 0)  {
                // Load the URL.
                loadUrl("file:///android_asset/base_map.html");
            }
            else {
                // Notify map about the resize.
                mJavaScriptInterface.evaluateJavascript(getContext().getString(R.string.map_trigger_resize));
            }
        }

        public GoogleMap getMap() {
            return mGoogleMap;
        }

        private void onSaveInstanceState(Bundle outState) {
            outState.putParcelable(KEY_CENTER, mCenter);
            outState.putFloat(KEY_ZOOM, mZoom);
        }

        private class JavaScriptInterface implements JavaScriptBridge {
            private String mApiKey;

            private boolean mInitialized = false;

            private List<String> mPendingScripts = new ArrayList<>();

            private GoogleMap.OnMapLoadedCallback mOnMapLoadedCallback;
            private GoogleMap.OnMapClickListener mOnMapClickListener;
            private GoogleMap.OnMapLongClickListener mOnMapLongClickListener;

            public JavaScriptInterface(String apiKey) {
                mApiKey = apiKey;
            }

            @JavascriptInterface
            public String getApiKey() {
                return mApiKey;
            }

            @JavascriptInterface
            public String getMapId() {
                return ID_MAP;
            }

            @JavascriptInterface
            public synchronized void setInitialized() {
                if (mPendingScripts.size() == 0) {
                    mInitialized = true;
                } else {
                    post(new Runnable() {
                        @Override
                        public void run() {
                            mInitialized = true;

                            Iterator<String> it = mPendingScripts.iterator();
                            while (it.hasNext()) {
                                evaluateJavascript(it.next());
                                it.remove();
                            }
                        }
                    });
                }
            }

            @Override
            public synchronized void evaluateJavascript(String script) {
                if (mInitialized) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        GoogleMapView.this.evaluateJavascript(script, null);
                    } else {
                        loadUrl("javascript:" + script);
                    }
                } else {
                    mPendingScripts.add(script);
                }
            }

            @JavascriptInterface
            public void onMapLoaded() {
                post(new Runnable() {
                    @Override
                    public void run() {
                        if (mOnMapLoadedCallback != null) {
                            mOnMapLoadedCallback.onMapLoaded();
                        }
                    }
                });
            }

            @JavascriptInterface
            public void onMapClick(final double latitude, final double longitude) {
                post(new Runnable() {
                    @Override
                    public void run() {
                        if (mOnMapClickListener != null) {
                            mOnMapClickListener.onMapClick(new LatLng(latitude, longitude));
                        }
                    }
                });
            }

            @JavascriptInterface
            public void onMapLongClick(final double latitude, final double longitude) {
                post(new Runnable() {
                    @Override
                    public void run() {
                        if (mOnMapLongClickListener != null) {
                            mOnMapLongClickListener.onMapLongClick(new LatLng(latitude, longitude));
                        }
                    }
                });
            }

            @JavascriptInterface
            public void setCenterZoom(double latitude, double longitude, float zoom) {
                mCenter = new LatLng(latitude, longitude);
                mZoom = zoom;
            }

            @Override
            public void setOnMapLoadedCallback(GoogleMap.OnMapLoadedCallback callback) {
                mOnMapLoadedCallback = callback;
            }

            @Override
            public void setOnMapClickListener(GoogleMap.OnMapClickListener listener) {
                mOnMapClickListener = listener;
            }

            @Override
            public void setOnMapLongClickListener(GoogleMap.OnMapLongClickListener listener) {
                mOnMapLongClickListener = listener;
            }
        }
    }
}
