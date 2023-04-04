package com.example.grocerylist;

public class ShoppingListItem {
    private String itemName;
    private String extraInfo;

    private long timestamp;

    public ShoppingListItem(String itemName, String extraInfo) {
        this.itemName = itemName;
        this.extraInfo = extraInfo;
        this.timestamp = System.currentTimeMillis();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
