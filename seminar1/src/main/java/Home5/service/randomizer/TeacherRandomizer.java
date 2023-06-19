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
public class TeacherRandomizer extends DataReader {

    Random random;

    public TeacherRandomizer() throws IOException {
        this.random = new Random();
    }

    public List<String> getData() {
        List<String> teacherDataList = new ArrayList<>();
        String fullName = "";
        int age = 0;
        String phoneNumber = "";
        int gender;

        gender = random.nextInt(2);
        if (gender == 0) {
            fullName = getLoadedWomenFirstNames().get(random.nextInt(getLoadedWomenFirstNames().size()))
                    + " "
                    + getLoadedWomenLastNames().get(random.nextInt(getLoadedWomenLastNames().size()));
            age = random.nextInt(27, 67);
            phoneNumber = PhoneNumberRandomizer.getPhoneNumber();
        }
        if (gender == 1) {
            fullName = getLoadedMenFirstNames().get(random.nextInt(getLoadedMenFirstNames().size()))
                    + " "
                    + getLoadedMenLastNames().get(random.nextInt(getLoadedMenLastNames().size()));
            age = random.nextInt(27, 67);
            phoneNumber = PhoneNumberRandomizer.getPhoneNumber();
        }
        teacherDataList.add(fullName);
        teacherDataList.add(String.valueOf(age));
        teacherDataList.add(phoneNumber);

        return teacherDataList;
    }
}