package com.coder4.mvndeplic.parser;

import java.util.ArrayList;
import java.util.List;

public class LicParseItem {

    private String dep;

    private List<String> licenses = new ArrayList<>();

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public List<String> getLicenses() {
        return licenses;
    }

    public void addLicense(String license) {
        this.licenses.add(license);
    }
}
