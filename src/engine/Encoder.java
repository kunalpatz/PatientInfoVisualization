package engine;

import broker.Patient;

import java.util.*;
import java.util.Map.Entry;


public class Encoder {
    private Map<String, Integer> sexEncodedValues = new LinkedHashMap<>();
    private Map<String, Integer> caseEncodedValues = new LinkedHashMap<>();
    private Map<String, Integer> stateEncodedValues = new LinkedHashMap<>();

    public Double[] convertToDoubleArray(Patient patient) {
        if (patient == null) {
            return null;
        }
        Double[] result = new Double[6];
        if (patient.getAge() == null) {
            return result;
        }
        result[0] = Double.valueOf(patient.getAge());
        result[1] = encodeValue(sexEncodedValues, patient.getSex());
        result[2] = encodeValue(caseEncodedValues, patient.getInfectionCases());

        Integer monthAndDayCombinationSymptom = getMonthAndDayCombination(patient.getSymptomOnSetDate());
        result[3] = Double.valueOf(monthAndDayCombinationSymptom);
        Integer monthAndDayCombinationConfirmed = getMonthAndDayCombination(patient.getConfirmedDate());
        result[4] = Double.valueOf(monthAndDayCombinationConfirmed);
        result[5] = encodeValue(stateEncodedValues, patient.getState());
        return result;


    }

    public Patient convertToPatient(Double[] doubleArray) {
        Patient patient = new Patient();
        patient.setAge(doubleArray[0].intValue());
        patient.setSex(extractFromMapKey(doubleArray[1].intValue(), this.sexEncodedValues));
        patient.setInfectionCases(extractFromMapKey(doubleArray[2].intValue(), this.caseEncodedValues));

        patient.setSymptomOnSetDate(extractFromEncodedDate(doubleArray[3]));
        patient.setConfirmedDate(extractFromEncodedDate(doubleArray[4]));

        return patient;
    }

    private Date extractFromEncodedDate(Double encodedDate) {

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Double intermediate = encodedDate / 100;
        System.out.println("intermediate : " + intermediate);
        int integerPart = intermediate.intValue();
        System.out.println("integerPart : " + integerPart);
        Double remainder = (intermediate - integerPart) * 100;
        System.out.println("remainder : " + remainder);

        cal.set(Calendar.DAY_OF_MONTH, Long.valueOf(Math.round(remainder)).intValue());
        cal.set(Calendar.MONTH, integerPart - 1);
        return cal.getTime();

    }


    private String extractFromMapKey(Integer value, Map<String, Integer> map) {
        Set<Entry<String, Integer>> entries = map.entrySet();
        for (Entry<String, Integer> entry : entries) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return "";
    }


    private Integer getMonthAndDayCombination(Date date) {
        if (date == null) {
            return 0;
        }
        Integer month = extractFromDate(date, Calendar.MONTH);
        Integer day = extractFromDate(date, Calendar.DAY_OF_MONTH);
        Integer monthAndDayCombination = (month + 1) * 100 + day;
        return monthAndDayCombination;
    }

    private Integer extractFromDate(Date date, int calendarField) {
        Calendar onsetSymptomCal = Calendar.getInstance();
        onsetSymptomCal.setTime(date);
        return onsetSymptomCal.get(calendarField);
    }

    private Double encodeValue(Map<String, Integer> map, String rawValue) {
        Integer value = map.get(rawValue);
        if (value == null) {
            value = map.size() + 1;
            map.put(rawValue, value);

        }
        return Double.valueOf(value);
    }
}

