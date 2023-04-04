package com.example.grocerylist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {
    private List<ShoppingListItem> shoppingList;
    private OnItemClickListener itemClickListener;

    public ShoppingListAdapter(List<ShoppingListItem> shoppingList, OnItemClickListener itemClickListener) {
        this.shoppingList = shoppingList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_user_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShoppingListItem item = shoppingList.get(position);
        holder.itemName.setText(item.getItemName());
        holder.extraInfo.setText(item.getExtraInfo());
        holder.editIcon.setOnClickListener(v -> itemClickListener.onEditClick(position));
        holder.deleteIcon.setOnClickListener(v -> itemClickListener.onDeleteClick(position));
    }

    @Override
    public int getItemCount() {
        return shoppingList.size();
    }

    public interface OnItemClickListener {
        void onEditClick(int position);
        void onDeleteClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView extraInfo;
        ImageView editIcon;
        ImageView deleteIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.txtItemName);
            extraInfo = itemView.findViewById(R.id.txtItemExtraInformation);
            editIcon = itemView.findViewById(R.id.ivEditIcon);
            deleteIcon = itemView.findViewById(R.id.ivDeleteIcon);
        }
    }
}