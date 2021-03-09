package maa.covid_wear.utils.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ResourceUtils;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;

import maa.covid_wear.R;
import maa.covid_wear.utils.FontUtils;
import maa.covid_wear.utils.interfaces.ItemsCallback;
import maa.covid_wear.utils.model.Items;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {
    private Context context;
    private ArrayList<Items> items;
    private ItemsCallback itemCallback;

    public ItemsAdapter(Context context, ArrayList<Items> items, ItemsCallback itemCallback) {
        this.context = context;
        this.items = items;
        this.itemCallback = itemCallback;
    }

    @NonNull
    @Override

    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemsViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.icon.setImageDrawable(ResourceUtils.getDrawable(items.get(position).getIcon()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private ImageView icon;
        private RelativeLayout item;

        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            icon = itemView.findViewById(R.id.icon);
            item = itemView.findViewById(R.id.item);
            YoYo.with(Techniques.FadeIn).playOn(icon);
            YoYo.with(Techniques.FadeIn).playOn(title);
            title.setTypeface(FontUtils.getBoldFont(context));
            item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemCallback.onItemClicked(items.get(getAdapterPosition()));
        }
    }
}