package maa.covid_wear.ui.activities;

import android.content.Context;
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
import maa.covid_wear.utils.adapters.OverviewAdapter;
import maa.covid_wear.utils.model.AllData;
import maa.covid_wear.utils.model.BriefData;
import maa.covid_wear.utils.network.Client;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OverviewActivity extends WearableActivity {


    private ProgressBar loading;
    private DiscreteScrollView mDiscreteScrollView;
    private ImageTextButton noData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        initializeViews();
        getData();
        noData.setOnClickListener(v -> getData());
        setAmbientEnabled();
    }

    public void getData() {
        Client.getClient().getAllData().enqueue(new Callback<AllData>() {
            @Override
            public void onResponse(@NotNull Call<AllData> call, @NotNull Response<AllData> response) {
                if (response.body() != null)
                    setAdapter(response.body());
                loading.setVisibility(View.GONE);
                mDiscreteScrollView.setVisibility(View.VISIBLE);
                if (noData.getVisibility() == View.VISIBLE) noData.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NotNull Call<AllData> call, @NotNull Throwable t) {
                mDiscreteScrollView.setVisibility(View.GONE);
                loading.setVisibility(View.GONE);
                noData.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setAdapter(AllData data) {
        ArrayList<BriefData> briefData = new ArrayList<>();
        briefData.add(new BriefData("Total case", data.getCases(), "#c71112"));
        briefData.add(new BriefData("Total\nRecovered", data.getRecovered(), "#669901"));
        briefData.add(new BriefData("Total Deaths", data.getDeaths(), "#010101"));
        briefData.add(new BriefData("Total Active", data.getActive(), "#f59b31"));
        mDiscreteScrollView.setAdapter(new OverviewAdapter(getApplicationContext(), briefData));
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