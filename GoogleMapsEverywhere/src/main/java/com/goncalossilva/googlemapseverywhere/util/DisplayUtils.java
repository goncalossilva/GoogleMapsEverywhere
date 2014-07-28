package com.goncalossilva.googlemapseverywhere.util;

import android.content.Context;

public class DisplayUtils {
    public static float pxToDp(Context context, float px) {
        return px / (context.getResources().getDisplayMetrics().densityDpi / 160f);
    }
}
