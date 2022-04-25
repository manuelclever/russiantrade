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

            List<String[]> countries = new ArrayList<>();
            while(reader.ready()) {
                String[] country = reader.readLine().split("_");
                if(country[0].charAt(0) != '#') {
                    countries.add(country);
                }
            }
            countries.sort((o1, o2) -> o1[1].compareTo(o2[1]));

            writer.write("const countryOptions: CountryOption[] = [\n");
            for(int i = 0; i < countries.size(); i++) {
                String[] country = countries.get(i);
                writer.write(
                        "\t{ value: '" + country[1] + "',label: (\n" +
                            "\t\t<div className='label'>\n" +
                                "\t\t\t<img src={" + country[2].toLowerCase() + "_flag} alt='" + country[1] +
                                    "_flag' style={style" + ".img}/>\n" +
                                "\t\t\t<span>" + country[1] + "</span>\n" +
                            "\t\t</div>\n" +
                        "\t)},\n");
            }
            writer.write("];");
            writer.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
