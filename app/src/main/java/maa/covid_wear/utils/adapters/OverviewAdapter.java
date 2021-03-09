package maa.covid_wear.utils.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import java.util.ArrayList;
import java.util.Locale;

import maa.covid_wear.R;
import maa.covid_wear.utils.FontUtils;
import maa.covid_wear.utils.model.BriefData;

public class OverviewAdapter extends RecyclerView.Adapter<OverviewAdapter.ItemsViewHolder> {
    private Context context;
    private ArrayList<BriefData> briefData;


    public OverviewAdapter(Context context, ArrayList<BriefData> briefData) {
        this.context = context;
        this.briefData = briefData;

    }


    @NonNull
    @Override

    public OverviewAdapter.ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OverviewAdapter.ItemsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_overview, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull OverviewAdapter.ItemsViewHolder holder, int position) {
        int number = (int) briefData.get(position).getNumber();
        holder.label.setText(briefData.get(position).getTitle());
        holder.tickerView.setText(String.format(Locale.ENGLISH, "%,d", number));
        holder.tickerView.setTextColor(Color.parseColor(briefData.get(position).getColor()));
        holder.label.setTextColor(Color.parseColor(briefData.get(position).getColor()));
    }

    @Override
    public int getItemCount() {
        return briefData.size();
    }

    class ItemsViewHolder extends RecyclerView.ViewHolder {
        private TickerView tickerView;
        private TextView label;
        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            tickerView = itemView.findViewById(R.id.tickerView);
            label = itemView.findViewById(R.id.label);
            label.setTypeface(FontUtils.getBoldFont(context));
            tickerView.setTypeface(FontUtils.getBoldFont(context));
            tickerView.setCharacterLists(TickerUtils.provideNumberList());
            tickerView.setAnimationDuration(1000);
        }

    }
}