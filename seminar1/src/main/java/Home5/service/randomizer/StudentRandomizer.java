package Home5.service.randomizer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import Home5.service.datareader.DataReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@EqualsAndHashCode(callSuper = true)
public class StudentRandomizer extends DataReader {

    Random random;

    public StudentRandomizer() throws IOException {
        this.random = new Random();
    }

    public List<String> getData() {
        List<String> studentDataList = new ArrayList<>();
        String fullName = "";
        int age = 0;
        String phoneNumber = "";
        int gender;

        gender = random.nextInt(2);
        if (gender == 0) {
            fullName = getLoadedWomenFirstNames().get(random.nextInt(getLoadedWomenFirstNames().size()))
                    + " "
                    + getLoadedWomenLastNames().get(random.nextInt(getLoadedWomenLastNames().size()));
            age = random.nextInt(16, 21);
            phoneNumber = PhoneNumberRandomizer.getPhoneNumber();
        }
        if (gender == 1) {
            fullName = getLoadedMenFirstNames().get(random.nextInt(getLoadedMenFirstNames().size()))
                    + " "
                    + getLoadedMenLastNames().get(random.nextInt(getLoadedMenLastNames().size()));
            age = random.nextInt(16, 21);
            phoneNumber = PhoneNumberRandomizer.getPhoneNumber();
        }
        studentDataList.add(fullName);
        studentDataList.add(String.valueOf(age));
        studentDataList.add(phoneNumber);


        return studentDataList;
    }

}