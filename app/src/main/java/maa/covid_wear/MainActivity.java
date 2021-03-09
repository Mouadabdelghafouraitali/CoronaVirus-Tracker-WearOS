package maa.covid_wear;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import org.jetbrains.annotations.NotNull;

import maa.covid_wear.ui.activities.CountriesActivity;
import maa.covid_wear.ui.activities.MapActivity;
import maa.covid_wear.ui.activities.OverviewActivity;
import maa.covid_wear.utils.adapters.ItemsAdapter;

import static maa.covid_wear.utils.Constant.getItems;

public class MainActivity extends WearableActivity {


    private DiscreteScrollView mDiscreteScrollView;

    private ImageView noData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();
        mDiscreteScrollView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        mDiscreteScrollView.setAdapter(new ItemsAdapter(getApplicationContext(), getItems(), item -> {
            if (item.getTitle().equalsIgnoreCase("overview"))
                startActivity(new Intent(MainActivity.this, OverviewActivity.class));
            if (item.getTitle().equalsIgnoreCase("countries"))
                startActivity(new Intent(MainActivity.this, CountriesActivity.class));
            if (item.getTitle().equalsIgnoreCase("map"))
                startActivity(new Intent(MainActivity.this, MapActivity.class));
        }));
        // Enables Always-on
        setAmbientEnabled();
    }

    private void initializeView() {
        mDiscreteScrollView = findViewById(R.id.items);
        mDiscreteScrollView.setItemTransitionTimeMillis(120);
        mDiscreteScrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMaxScale(1f)
                .setMinScale(0.8f)
                .setPivotX(Pivot.X.CENTER)
                .setPivotY(Pivot.Y.BOTTOM)
                .build());
        mDiscreteScrollView.setHasFixedSize(true);
    }
}