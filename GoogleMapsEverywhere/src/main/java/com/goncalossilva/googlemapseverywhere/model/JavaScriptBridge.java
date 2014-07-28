package com.goncalossilva.googlemapseverywhere.model;

import com.goncalossilva.googlemapseverywhere.OnMapClickListener;
import com.goncalossilva.googlemapseverywhere.OnMapLoadedCallback;
import com.goncalossilva.googlemapseverywhere.OnMapLongClickListener;

/* TODO: Make this invisible from the API. */
public interface JavaScriptBridge {
    public void evaluateJavascript(String script);

    public void setOnMapLoadedCallback(OnMapLoadedCallback callback);
    public void setOnMapClickListener(OnMapClickListener listener);
    public void setOnMapLongClickListener(OnMapLongClickListener listener);
}
