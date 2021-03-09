package maa.covid_wear.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;

import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import maa.covid_wear.R;
import maa.covid_wear.utils.adapters.OverviewAdapter;
import maa.covid_wear.utils.model.BriefData;

public class CountryActivity extends WearableActivity {

    private DiscreteScrollView mDiscreteScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        initializeViews();
        getData();
        setAmbientEnabled();
    }

    public void getData() {
        float todayCases = getIntent().getFloatExtra("todayCases", 0);
        float recovered = getIntent().getFloatExtra("recovered", 0);
        float deaths = getIntent().getFloatExtra("deaths", 0);
        float active = getIntent().getFloatExtra("active", 0);
        ArrayList<BriefData> briefData = new ArrayList<>();
        briefData.add(new BriefData("Total case", todayCases, "#c71112"));
        briefData.add(new BriefData("Total\nRecovered", recovered, "#669901"));
        briefData.add(new BriefData("Total Deaths", deaths, "#010101"));
        briefData.add(new BriefData("Total Active", active, "#f59b31"));
        mDiscreteScrollView.setAdapter(new OverviewAdapter(getApplicationContext(), briefData));
    }


    private void initializeViews() {
        mDiscreteScrollView = findViewById(R.id.itemsOverView);
        mDiscreteScrollView.setItemTransitionTimeMillis(120);
        mDiscreteScrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMaxScale(1f)
                .setMinScale(0.8f)
                .setPivotX(Pivot.X.CENTER)
                .setPivotY(Pivot.Y.BOTTOM)
                .build());
        mDiscreteScrollView.setHasFixedSize(true);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}