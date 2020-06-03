package broker;

import java.util.Date;

public class Patient {
    private Integer age;
    private String sex;
    private String infectionCases;
    private Date symptomOnSetDate;
    private Date confirmedDate;
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getAge() {
        return age;
    }


    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getInfectionCases() {
        return infectionCases;
    }

    public void setInfectionCases(String infectionCases) {
        this.infectionCases = infectionCases;
    }

    public Date getSymptomOnSetDate() {
        return symptomOnSetDate;
    }

    public void setSymptomOnSetDate(Date symptomOnSetDate) {
        this.symptomOnSetDate = symptomOnSetDate;
    }

    public Date getConfirmedDate() {
        return confirmedDate;
    }

    public void setConfirmedDate(Date confirmedDate) {
        this.confirmedDate = confirmedDate;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "age=" + age +
                ", sex='" + sex + '\'' +
                ", infectionCases='" + infectionCases + '\'' +
                ", symptomOnSetDate=" + symptomOnSetDate +
                ", confirmedDate=" + confirmedDate +
                ", state='" + state + '\'' +
                '}';
    }
}
