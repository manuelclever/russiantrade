package eu.russiantrade.util;

import java.io.*;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

public class ConvertCountriesForWeb {
    private static final String SOURCE_PATH = FileSystems.getDefault()
            .getPath("backend", "src", "main", "resources", "countries.txt").toAbsolutePath().toString();
    private static final String TARGET_PATH = FileSystems.getDefault()
            .getPath("webapp", "webpage", "src", "resources", "data", "countriesDropdown.txt")
            .toAbsolutePath().toString();

    public static void main(String[] args) {
        convert();
    }

    public static void convert() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(SOURCE_PATH));
            BufferedWriter writer = new BufferedWriter(new FileWriter(TARGET_PATH));

            List<String> countries = new ArrayList<>();

            while(reader.ready()) {
                String[] country = reader.readLine().split("_");
                if(country[0].charAt(0) != '#') {
                    countries.add(country[1]);
                }
            }
            countries.sort(String::compareTo);

            for(int i = 0; i < countries.size(); i++) {
                if(i < countries.size()-1) {
                    writer.write(countries.get(i) + "\n");
                } else {
                    writer.write(countries.get(i));
                }
            }

            writer.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
