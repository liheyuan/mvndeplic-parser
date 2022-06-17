package com.coder4.mvndeplic.parser;

import java.util.ArrayList;
import java.util.List;

public class LicParseItem {

    private LicDepItem dep;

    private List<String> licenses = new ArrayList<>();

    public LicDepItem getDep() {
        return dep;
    }

    public void setDep(LicDepItem dep) {
        this.dep = dep;
    }

    public List<String> getLicenses() {
        return licenses;
    }

    public void addLicense(String license) {
        this.licenses.add(license);
    }
}
