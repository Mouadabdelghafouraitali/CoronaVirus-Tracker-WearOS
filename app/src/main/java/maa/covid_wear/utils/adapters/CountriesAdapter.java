package maa.covid_wear.utils.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import maa.covid_wear.R;
import maa.covid_wear.utils.interfaces.CountryCallback;
import maa.covid_wear.utils.model.BriefCountriesData;
import maa.covid_wear.utils.model.BriefCountryData;
import maa.covid_wear.utils.model.Countries;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ItemsViewHolder> {
    private Context context;
    private ArrayList<BriefCountriesData> briefCountriesData;
    private CountryCallback countryCallback;
    private ArrayList<Countries> data;

    public CountriesAdapter(Context context, ArrayList<BriefCountriesData> briefCountriesData, CountryCallback countryCallback, ArrayList<Countries> data) {
        this.context = context;
        this.briefCountriesData = briefCountriesData;
        this.countryCallback = countryCallback;
        this.data = data;
    }

    @NonNull
    @Override

    public CountriesAdapter.ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountriesAdapter.ItemsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_country, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CountriesAdapter.ItemsViewHolder holder, int position) {
        Glide.with(context).load(briefCountriesData.get(position).getFlag()).into(holder.img);
        holder.name.setText(briefCountriesData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return briefCountriesData.size();
    }

    class ItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        private CircleImageView img;
        private RelativeLayout country;

        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            img = itemView.findViewById(R.id.flag);
            country = itemView.findViewById(R.id.country);
            country.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            BriefCountryData briefCountryData = new BriefCountryData(data.get(getAdapterPosition()).getCases(),
                    data.get(getAdapterPosition()).getRecovered(),
                    data.get(getAdapterPosition()).getDeaths(),
                    data.get(getAdapterPosition()).getActive());
            countryCallback.onCountryClicked(briefCountryData);
        }
    }
}