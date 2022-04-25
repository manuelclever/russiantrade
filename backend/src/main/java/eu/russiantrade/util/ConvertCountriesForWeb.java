package eu.russiantrade.util;

import java.io.*;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

public class ConvertCountriesForWeb {
    private static final String SOURCE_PATH = FileSystems.getDefault()
            .getPath("backend", "src", "main", "resources", "countries.txt").toAbsolutePath().toString();
    private static final String TARGET_PATH_JSX = FileSystems.getDefault()
            .getPath("webapp", "webpage", "src", "resources", "data", "countriesDropdown.txt")
            .toAbsolutePath().toString();private static final String TARGET_PATH_IMPORT = FileSystems.getDefault()
            .getPath("webapp", "webpage", "src", "resources", "data", "countriesImportImg.txt")
            .toAbsolutePath().toString();
    public static final String IMAGE_PATH = FileSystems.getDefault()
            .getPath("webapp", "webpage", "src", "resources", "images", "svg_flags")
            .toAbsolutePath().toString();

    public static void main(String[] args) {
        convert();
    }

    public static void convert() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(SOURCE_PATH));

            List<String[]> countries = new ArrayList<>();
            while(reader.ready()) {
                String[] country = reader.readLine().split("_");
                if(country[0].charAt(0) != '#') {
                    countries.add(country);
                }
            }
            countries.sort((o1, o2) -> o1[1].compareTo(o2[1]));

            writeJSXSyntax(countries);
            writeImportImageSyntax(countries);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writeJSXSyntax(List<String[]> countries) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(TARGET_PATH_JSX));

        writer.write("const countryOptions: CountryOption[] = [\n");
        for(int i = 0; i < countries.size(); i++) {
            String[] country = countries.get(i);
            String fullName = country[1].toLowerCase().replace("\s", "_");
            String iso = country[2].toLowerCase();
            writer.write(
                    "\t{ value: '" + fullName + "',label: (\n" +
                            "\t\t<div className='label'>\n" +
                            "\t\t\t<img src={" + iso + "_flag} alt='" +
                                fullName + "_flag' style={style" + ".img}/>\n" +
                            "\t\t\t<span>" + country[1] + "</span>\n" +
                            "\t\t</div>\n" +
                            "\t)},\n");
        }
        writer.write("];");
        writer.close();
    }

    private static void writeImportImageSyntax(List<String[]> countries) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(TARGET_PATH_IMPORT));

        for(int i = 0; i < countries.size(); i++) {
            String[] country = countries.get(i);
            String fullName = country[1].toLowerCase().replace("\s", "-");
            String iso = country[2].toLowerCase();

            writer.write("import " + iso + "_flag from './../../resources/images/svg_flags/" +
                fullName + ".svg;\n");
        }
        writer.close();
    }
}
