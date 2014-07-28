package com.goncalossilva.googlemapseverywhere.util;

import com.goncalossilva.googlemapseverywhere.R;

import android.content.Context;
import android.graphics.Color;

public class ColorUtils {
    public static String getCss(Context context, int color) {
        return context.getString(
                R.string.color_css,
                Color.red(color),
                Color.green(color),
                Color.blue(color),
                Color.alpha(color) / 255f);
    }
}
