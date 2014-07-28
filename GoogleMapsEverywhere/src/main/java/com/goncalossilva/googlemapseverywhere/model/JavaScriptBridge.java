package com.goncalossilva.googlemapseverywhere.model;

import com.goncalossilva.googlemapseverywhere.GoogleMap;

/* TODO: Make this invisible from the API. */
public interface JavaScriptBridge {
    public void evaluateJavascript(String script);

    public void setOnMapLoadedCallback(GoogleMap.OnMapLoadedCallback callback);
    public void setOnMapClickListener(GoogleMap.OnMapClickListener listener);
    public void setOnMapLongClickListener(GoogleMap.OnMapLongClickListener listener);
}
