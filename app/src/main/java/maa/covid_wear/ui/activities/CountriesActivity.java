package maa.covid_wear.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import maa.covid_wear.R;
import maa.covid_wear.ui.views.ImageTextButton;
import maa.covid_wear.utils.adapters.CountriesAdapter;
import maa.covid_wear.utils.model.BriefCountriesData;
import maa.covid_wear.utils.model.BriefCountryData;
import maa.covid_wear.utils.model.Countries;
import maa.covid_wear.utils.network.Client;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountriesActivity extends WearableActivity {

    private ProgressBar loading;
    private DiscreteScrollView mDiscreteScrollView;
    private ImageTextButton noData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);
        initializeViews();
        getData();
        noData.setOnClickListener(v -> getData());
        setAmbientEnabled();
    }

    private void getData() {
        Client.getClient().getAllCountries().enqueue(new Callback<ArrayList<Countries>>() {
            @Override
            public void onResponse(@NotNull Call<ArrayList<Countries>> call, @NotNull Response<ArrayList<Countries>> response) {
                if (response.body() != null)
                    setAdapter(response.body());
                loading.setVisibility(View.GONE);
                mDiscreteScrollView.setVisibility(View.VISIBLE);
                if (noData.getVisibility() == View.VISIBLE) noData.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<Countries>> call, @NotNull Throwable t) {
                mDiscreteScrollView.setVisibility(View.GONE);
                loading.setVisibility(View.GONE);
                noData.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setAdapter(ArrayList<Countries> data) {
        ArrayList<BriefCountriesData> briefCountriesData = new ArrayList<>();
        for (int i = 0; i < data.size(); i++)
            briefCountriesData.add(new BriefCountriesData(data.get(i).getCountry(), data.get(i).getCountryInfo().getFlag()));
        mDiscreteScrollView.setAdapter(new CountriesAdapter(getApplicationContext(), briefCountriesData, this::goTo, data));
    }

    private void goTo(BriefCountryData briefData) {
        Intent go = new Intent(CountriesActivity.this, CountryActivity.class);
        go.putExtra("todayCases", briefData.getCases());
        go.putExtra("recovered", briefData.getRecovered());
        go.putExtra("deaths", briefData.getDeaths());
        go.putExtra("active", briefData.getActive());
        startActivity(go);
    }


    private void initializeViews() {
        noData = findViewById(R.id.noData);
        loading = findViewById(R.id.loading);
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