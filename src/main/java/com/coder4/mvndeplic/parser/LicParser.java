package com.coder4.mvndeplic.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LicParser {

    private static Pattern PAT = Pattern.compile("(\\(.+?\\))");

    public static LicParseResult parse(File file) throws FileNotFoundException {
        try (Scanner scan = new Scanner(file)) {
            return parse(scan);
        }
    }

    public static LicParseResult parse(String str) {
        try (Scanner scan = new Scanner(str)) {
            return parse(scan);
        }
    }

    public static LicParseResult parse(Scanner scan) {

        LicParseResult result = new LicParseResult();

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            // Step 1: Trim
            line = line.trim();

            // Step 2 : Find all in ()
            Matcher m = PAT.matcher(line);
            List<String> list = new ArrayList<>();
            while (m.find()) {
                // Remove ( and )
                String str = m.group();
                if (str.length() > 2) {
                    list.add(str.substring(1, str.length() - 1));
                }
            }

            if (list.size() >= 2) {
                LicParseItem item = new LicParseItem();
                // Parse dep
                String depStr = list.get(list.size() - 1);
                String[] parts1 = depStr.split(" - ");
                if (parts1.length != 2) {
                    continue;
                }
                String [] parts2 = parts1[0].trim().split(":");
                if (parts2.length != 3) {
                    continue;
                }
                LicDepItem dep = new LicDepItem();
                dep.setGroupId(parts2[0]);
                dep.setArtifactId(parts2[1]);
                dep.setVersion(parts2[2]);
                item.setDep(dep);
                // other license
                list.stream().limit(list.size() - 1).forEach(item::addLicense);
                result.addItem(item);
            }
        }

        return result;
    }

}
