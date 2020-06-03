package gateway;

import broker.Patient;
import engine.CSVHandler;
import engine.Encoder;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class launcher {

    public static void main(String[] args) throws IOException, ParseException {
        List<Patient> patients = CSVHandler.readFromFile("src/PatientInfo.csv");
        Encoder encoder = new Encoder();
        Double[][] matrix = new Double[patients.size()][5];
        System.out.println("[");
        for (int i = 0; i < patients.size(); i++) {
            Patient currentPatient = patients.get(i);
            Double[] encodedLine = encoder.convertToDoubleArray(currentPatient);

            matrix[i] = encodedLine;
            System.out.println(Arrays.asList(encodedLine));
        }
        System.out.println("]");
    }
}
