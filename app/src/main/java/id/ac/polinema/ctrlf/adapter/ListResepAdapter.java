package id.ac.polinema.ctrlf.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import id.ac.polinema.ctrlf.R;
import id.ac.polinema.ctrlf.model.Recipe;

public class ListResepAdapter extends RecyclerView.Adapter<ListResepAdapter.ViewHolder> {

    private static final int IV_LEFT = 1;
    private static final int IV_RIGHT = 2;
    //    final private ListItemListener itemListener;
//    private Context context;
    private ArrayList<Recipe> items;

    //    public ListResepAdapter(ArrayList<Recipe> items, ListItemListener itemListener) {
//        this.items = items;
//        this.itemListener = itemListener;
//    }
    public ListResepAdapter(ArrayList<Recipe> items) {
        this.items = items;

    }

    @NonNull
    @Override
    public ListResepAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext())
        View v = null;
        if (viewType == IV_LEFT) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        } else if (viewType == IV_RIGHT) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_rev, parent, false);
        }
        return new ViewHolder(v);
    }

    @Override
    public int getItemViewType(int position) {
        return (position % 2 == 0) ? IV_RIGHT : IV_LEFT;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ListResepAdapter.ViewHolder holder, int position) {
        Recipe r = items.get(position);
        holder.tvNamaResepList.setText(r.getLabel());
        holder.tvKaloriResepList.setText("Kalori : " + r.getCalories().toString() + " KCal");
        String path = r.getImage();
        Picasso.get().load(path).into(holder.ivResepList);
    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }

//    @Override
//    public void onClick(View v) {
//
//    }
//
//    interface ListItemListener{
//        void onListItemClick(int position);
//    }

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
