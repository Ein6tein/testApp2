package com.example.testApp2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser {

    public List<ListItem> readItems(File target) {
        List<ListItem> listItems = new ArrayList<ListItem>();

        Pattern p = Pattern.compile("([a-zA-Z\\s]+)\\s([\\d]+)");
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(target));

            String line;
            while ((line = br.readLine()) != null) {
                Matcher m = p.matcher(line);
                while (m.find()) {
                    listItems.add(new ListItem(m.group(1), m.group(2)));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return listItems;
    }

    public void writeItems(final List<ListItem> listItems,
                           final File file) {
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(file));
            for (ListItem item : listItems) {
                bw.append(item.getName()).append(" ").append(item.getCount());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
