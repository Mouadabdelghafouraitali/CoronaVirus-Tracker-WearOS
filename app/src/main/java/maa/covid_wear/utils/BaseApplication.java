package maa.covid_wear.utils;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import maa.covid_wear.R;


public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ViewPump.init(ViewPump.builder().addInterceptor(new CalligraphyInterceptor(
                new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/DMSans-Medium.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()))
                .build());
        Utils.init(BaseApplication.this);
    }


}