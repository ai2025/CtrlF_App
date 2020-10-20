package id.ac.polinema.ctrlf.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.ac.polinema.ctrlf.R;
import id.ac.polinema.ctrlf.model.Resep;

public class ListResepAdapter extends RecyclerView.Adapter<ListResepAdapter.ViewHolder> {

    private static final int IV_LEFT = 1;
    private static final int IV_RIGHT = 2;
    private Context context;
    private List<Resep> items;

    public ListResepAdapter(Context context, List<Resep> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ListResepAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = null;
        if (viewType == IV_LEFT) {
            v = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        } else if (viewType == IV_RIGHT) {
            v = LayoutInflater.from(context).inflate(R.layout.item_list_rev, parent, false);
        }
        return new ListResepAdapter.ViewHolder(v);
    }

    @Override
    public int getItemViewType(int position) {
        return (position % 2 == 0) ? IV_RIGHT : IV_LEFT;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ListResepAdapter.ViewHolder holder, int position) {
        Resep r = items.get(position);
        holder.tvNamaResepList.setText(r.getNamaResep());
        holder.tvKaloriResepList.setText("Kalori : " + r.getKaloriResep() + " KCal");
        Picasso.get().load(r.getImgResep()).into(holder.ivResepList);
    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivResepList;
        TextView tvNamaResepList, tvKaloriResepList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivResepList = itemView.findViewById(R.id.ivResepList);
            tvNamaResepList = itemView.findViewById(R.id.tvNamaResepList);
            tvKaloriResepList = itemView.findViewById(R.id.tvKaloriResepList);
        }
    }
}
