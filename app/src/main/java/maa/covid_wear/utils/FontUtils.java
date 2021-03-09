package maa.covid_wear.utils;

import android.content.Context;
import android.graphics.Typeface;

public class FontUtils {

    public static Typeface getBoldFont(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Uni Sans Heavy.otf");
    }

    public static Typeface getNormalFont(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/DMSans-Medium.ttf");
    }
}