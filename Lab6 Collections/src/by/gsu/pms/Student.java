package by.gsu.pms;

import java.time.LocalDate;

public class Student implements Comparable<Student> {
    String firstName;
    String lastName;
    String patronymic;
    LocalDate birthday;
    String address;
    String phoneNumber;
    String faculty;
    String year;
    String group;

    public Student(String firstName, String lastName, String patronymic, LocalDate birthday, String address, String phoneNumber, String faculty, String year, String group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.faculty = faculty;
        this.year = year;
        this.group = group;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPatronymic() {
        return patronymic;
    }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
    public LocalDate getBirthday() {
        return birthday;
    }
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getFaculty() {
        return faculty;
    }
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public int compareTo(Student o) {
        return this.lastName.compareTo(o.lastName);
    }

    @Override
    public String toString() {
        return "%s %s %s %s %s %s %s %s %s".formatted(
                this.lastName,this.firstName,this.patronymic,this.birthday,
                this.address,this.phoneNumber,this.faculty,this.year,this.group);
    }
}
