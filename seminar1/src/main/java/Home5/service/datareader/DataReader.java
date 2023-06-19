package Home5.service.datareader;


import lombok.Data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class DataReader {

    private final List<String> loadedMenFirstNames;
    private final List<String> loadedMenLastNames;
    private final List<String> loadedWomenFirstNames;
    private final List<String> loadedWomenLastNames;
    private final List<String> fileNames;
    private final List<List<String>> listNames;
    private final List<String> studentClassNames;

    private final String pathToFiles = "./src/main/java/homework5/repository/data/";

    public DataReader() throws IOException {
        this.loadedMenFirstNames = new ArrayList<>();
        this.loadedMenLastNames = new ArrayList<>();
        this.loadedWomenFirstNames = new ArrayList<>();
        this.loadedWomenLastNames = new ArrayList<>();
        this.fileNames = new ArrayList<>();
        this.listNames = new ArrayList<>();
        this.studentClassNames = new ArrayList<>();

        fileNames.add("men_first_names.txt");
        listNames.add(loadedMenFirstNames);
        fileNames.add("men_last_names.txt");
        listNames.add(loadedMenLastNames);
        fileNames.add("women_first_names.txt");
        listNames.add(loadedWomenFirstNames);
        fileNames.add("women_last_names.txt");
        listNames.add(loadedWomenLastNames);
        fileNames.add("student_class_titles.txt");
        listNames.add(studentClassNames);

        for (int i = 0; i < fileNames.size(); i++) {
            BufferedReader br = new BufferedReader(new FileReader(pathToFiles+fileNames.get(i)));
            try {
                String line;
                while ((line = br.readLine()) != null) {
                    listNames.get(i).add(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}