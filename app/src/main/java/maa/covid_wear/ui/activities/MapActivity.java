package maa.covid_wear.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import maa.covid_wear.R;
import maa.covid_wear.ui.views.ImageTextButton;
import maa.covid_wear.utils.model.Countries;
import maa.covid_wear.utils.network.Client;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapActivity extends WearableActivity {


    private GoogleMap mMap;
    private ProgressBar loading;
    private ImageTextButton noData;
    private MapFragment mapFragment;
    private RelativeLayout container;
    private ArrayList<Countries> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
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
                    data = response.body();


                mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
                mapFragment.getMapAsync(googleMap -> {
                    if (googleMap != null) {
                        mMap = googleMap;
                        mMap.setOnMarkerClickListener(marker -> {
                            int position = Integer.parseInt(marker.getSnippet());
                            Intent go = new Intent(MapActivity.this, CountryActivity.class);
                            ToastUtils.showShort(data.get(position).getCountry());
                            go.putExtra("todayCases", (float) data.get(position).getCases());
                            go.putExtra("recovered", (float) data.get(position).getRecovered());
                            go.putExtra("deaths", (float) data.get(position).getDeaths());
                            go.putExtra("active", (float) data.get(position).getActive());
                            startActivity(go);
                            return true;
                        });
                        for (int i = 0; i < data.size(); i++) {
                            googleMap.addMarker(new MarkerOptions()
                                    .position(new LatLng((float) data.get(i).getCountryInfo().getLat(), (float) data.get(i).getCountryInfo().getCountryInfoLong()))
                                    .title(data.get(i).getCountry())
                                    .snippet(String.valueOf(i)));
                            Log.d("MAP_COOR", "" + data.get(i).getCountryInfo().getLat() + " // " + data.get(i).getCountryInfo().getCountryInfoLong());
                        }

                    }
                });
                loading.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
                if (noData.getVisibility() == View.VISIBLE) noData.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<Countries>> call, @NotNull Throwable t) {
                container.setVisibility(View.GONE);
                loading.setVisibility(View.GONE);
                noData.setVisibility(View.VISIBLE);
            }
        });
    }


    private void initializeViews() {
        noData = findViewById(R.id.noData);
        loading = findViewById(R.id.loading);
        container = findViewById(R.id.container);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }


}