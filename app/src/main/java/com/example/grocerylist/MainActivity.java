package com.example.grocerylist;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView shoppingListRecyclerView;
    private ShoppingListAdapter shoppingListAdapter;

    private ArrayList<ShoppingListItem> shoppingListItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shoppingListRecyclerView = findViewById(R.id.rvShoppingList);
        shoppingListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        shoppingListAdapter = new ShoppingListAdapter(shoppingListItems, new ShoppingListAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(int position) {
                showEditDialog(position);
            }

            @Override
            public void onDeleteClick(int position) {
                shoppingListItems.remove(position);
                shoppingListAdapter.notifyItemRemoved(position);
            }
        });
        shoppingListRecyclerView.setAdapter(shoppingListAdapter);

        findViewById(R.id.btnAddItem).setOnClickListener(v -> showAddDialog());
        findViewById(R.id.btnSortAlphabetically).setOnClickListener(v -> sortByAlphabeticalOrder());
        findViewById(R.id.btnSortByTime).setOnClickListener(v -> sortByTimestamp());
    }

    private void showAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_add_edit_item, null);
        EditText itemNameEditText = view.findViewById(R.id.txtItemNameEdit);
        EditText extraInfoEditText = view.findViewById(R.id.txtItemExtraInfoEdit);
        builder.setView(view)
                .setTitle("Lisää")
                .setPositiveButton("Tallenna", (dialog, which) -> {
                    String itemName = itemNameEditText.getText().toString();
                    String extraInfo = extraInfoEditText.getText().toString();
                    shoppingListItems.add(new ShoppingListItem(itemName, extraInfo));
                    shoppingListAdapter.notifyItemInserted(shoppingListItems.size() - 1);
                })
                .setNegativeButton("Peruuta", (dialog, which) -> dialog.dismiss())
                .create().show();
    }

    private void showEditDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_add_edit_item, null);
        EditText itemNameEditText = view.findViewById(R.id.txtItemNameEdit);
        EditText extraInfoEditText = view.findViewById(R.id.txtItemExtraInfoEdit);
        ShoppingListItem currentItem = shoppingListItems.get(position);
        itemNameEditText.setText(currentItem.getItemName());
        extraInfoEditText.setText(currentItem.getExtraInfo());
        builder.setView(view)
                .setTitle("Muokkaa")
                .setPositiveButton("Tallenna", (dialog, which) -> {
                    String itemName = itemNameEditText.getText().toString();
                    String extraInfo = extraInfoEditText.getText().toString();
                    currentItem.setItemName(itemName);
                    currentItem.setExtraInfo(extraInfo);
                    shoppingListAdapter.notifyItemChanged(position);
                })
                .setNegativeButton("Peruuta", (dialog, which) -> dialog.dismiss())
                .create().show();
    }

    private void sortByAlphabeticalOrder() {
        shoppingListItems.sort((item1, item2) -> item1.getItemName().compareToIgnoreCase(item2.getItemName()));
        shoppingListAdapter.notifyDataSetChanged();
    }

    private void sortByTimestamp() {
        shoppingListItems.sort((item1, item2) -> Long.compare(item2.getTimestamp(), item1.getTimestamp()));
        shoppingListAdapter.notifyDataSetChanged();
    }
}