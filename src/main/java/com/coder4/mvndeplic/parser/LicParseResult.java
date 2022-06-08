package com.coder4.mvndeplic.parser;

import java.util.ArrayList;
import java.util.List;

public class LicParseResult {

    private List<LicParseItem> items = new ArrayList<>();

    public List<LicParseItem> getItems() {
        return items;
    }

    public void addItem(LicParseItem item) {
        items.add(item);
    }
}
