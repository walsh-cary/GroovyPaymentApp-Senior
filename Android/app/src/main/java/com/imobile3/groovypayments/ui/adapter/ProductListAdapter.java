package com.imobile3.groovypayments.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.imobile3.groovypayments.R;
import com.imobile3.groovypayments.data.model.Product;
import com.imobile3.groovypayments.rules.ProductRules;
import com.imobile3.groovypayments.utils.StateListHelper;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductListAdapter
        extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private Context mContext;
    private AdapterCallback mCallbacks;
    private List<Product> mItems;

    public interface AdapterCallback {

        void onProductClick(Product product);
    }

    public ProductListAdapter(
            @NonNull Context context,
            @NonNull List<Product> products,
            @NonNull AdapterCallback callback) {
        mContext = context;
        mCallbacks = callback;
        mItems = products;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.product_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product item = mItems.get(position);
        ProductRules rules = new ProductRules(item);

        holder.img.setImageResource(rules.getIcon().drawableRes);
        holder.img.setBackground(
                ContextCompat.getDrawable(mContext, rules.getColor().colorRes));

        // Configure label and description.
        holder.label.setText(item.getName());
        holder.label.setTextColor(
                StateListHelper.getTextColorSelector(mContext, R.color.black_space));
        holder.description.setText(rules.getDescription(Locale.getDefault()));
        holder.description.setTextColor(
                StateListHelper.getTextColorSelector(mContext, R.color.gray_down_pour));
        holder.label.setText(item.getName());

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ViewGroup container;
        TextView label,description;
        ImageView img;

        ViewHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            img = itemView.findViewById(R.id.icon_image);
            label = itemView.findViewById(R.id.label);
            description = itemView.findViewById(R.id.description);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v == container) {
                mCallbacks.onProductClick(mItems.get(getAdapterPosition()));
            }
        }
    }

    public List<Product> getItems() {
        return mItems;
    }

    public void setItems(@Nullable List<Product> items) {
        mItems = items != null ? items : new ArrayList<>();
        notifyDataSetChanged();
    }
}
