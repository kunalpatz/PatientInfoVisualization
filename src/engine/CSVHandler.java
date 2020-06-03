package engine;

import broker.Patient;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CSVHandler {

    public static List<Patient> readFromFile(String fileLocation) throws IOException, ParseException {

        List<String> patientsData = Files.readAllLines(new File(fileLocation).toPath());
        List<Patient> patientsList = new ArrayList<>();
        patientsData.remove(0);

        for (String line : patientsData) {
            String[] parts = line.split(",");
            Patient patient = new Patient();

            Integer age = computeAge(parts);
            patient.setAge(age);

            patient.setSex(parts[2]);
            patient.setInfectionCases(parts[9]);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (!"".equals(parts[13])) {
                patient.setSymptomOnSetDate(sdf.parse(parts[13]));
            }
            if (!"".equals(parts[14])) {
                patient.setConfirmedDate(sdf.parse(parts[14]));
            }

            patient.setState(parts[17]);
            patientsList.add(patient);

        }

        return patientsList;
    }

    private static Integer computeAge(String[] parts) {
        String birthYear = parts[3];
        String ageApproximation = parts[4];
        Integer age = null;
        if ("".equals(birthYear.strip())) {
            if ("".equals(ageApproximation.strip())) {
                System.out.println("Age is missing");
            } else {
                age = Integer.valueOf(ageApproximation.substring(0, 1));
            }
        } else {
            Integer year = Integer.valueOf(birthYear);
            Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
            age = currentYear - year;
        }
        return age;
    }
}
