package domain;

import domain.enums.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Applicant {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
    private String city;
    private String region;
    private String schoolName;
    private byte[] certificate;
    private Role role;
    private boolean isBlocked;
    List<Application> applicationList;
    List<String> localList;

    public static Applicant loginApplicant(String email, String password, Role role) {
        Applicant newApplicant = new Applicant();
        newApplicant.email = email;
        newApplicant.password = password;
        newApplicant.role = role;
        return newApplicant;
    }

    public static Applicant createApplicant(String name, String middleName, String surname, String email, String password,
                                            String city, String state, String school, byte[] certificate, Role role, boolean isBlocked,
                                            List<Application> applicationList, String[] locals) {
        Applicant newApplicant = new Applicant();
        newApplicant.firstName = name;
        newApplicant.middleName = middleName;
        newApplicant.lastName = surname;
        newApplicant.email = email;
        newApplicant.password = password;
        newApplicant.city = city;
        newApplicant.region = state;
        newApplicant.schoolName = school;
        newApplicant.certificate = certificate;
        newApplicant.role = role;
        newApplicant.isBlocked = isBlocked;
        newApplicant.applicationList = new ArrayList<>();
        newApplicant.applicationList.addAll(applicationList);
        newApplicant.localList = new ArrayList<>();
        newApplicant.localList.addAll(Arrays.asList(locals));
        return newApplicant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public byte[] getCertificate() {
        return certificate;
    }

    public void setCertificate(byte[] certificate) {
        this.certificate = certificate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public List<Application> getApplicationList() {
        return applicationList;
    }

    public void setApplicationList(List<Application> applicationList) {
        this.applicationList = applicationList;
    }

    public List<String> getLocalList() {
        return localList;
    }

    public void setLocalList(List<String> localList) {
        this.localList = localList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Applicant applicant = (Applicant) o;
        return id == applicant.id && isBlocked == applicant.isBlocked && Objects.equals(firstName, applicant.firstName) && Objects.equals(middleName, applicant.middleName) && Objects.equals(lastName, applicant.lastName) && Objects.equals(email, applicant.email) && Objects.equals(password, applicant.password) && Objects.equals(city, applicant.city) && Objects.equals(region, applicant.region) && Objects.equals(schoolName, applicant.schoolName) && Arrays.equals(certificate, applicant.certificate) && role == applicant.role && Objects.equals(applicationList, applicant.applicationList);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, firstName, middleName, lastName, email, password, city, region, schoolName, role, isBlocked, applicationList);
        result = 31 * result + Arrays.hashCode(certificate);
        return result;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", certificate=" + Arrays.toString(certificate) +
                ", role=" + role +
                ", isBlocked=" + isBlocked +
                ", applicationList=" + applicationList +
                ", localList=" + localList +
                '}';
    }
}
