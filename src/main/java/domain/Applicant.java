package domain;

import domain.enums.Role;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Applicant entity.
 *
 * @author A.Savchuk.
 */
public class Applicant implements Serializable {
    private static final long serialVersionUID = 1569874589654123658L;
    private int id;
    private String email;
    private String password;
    private Role role;
    private String firstName;
    private String middleName;
    private String lastName;
    private String city;
    private String region;
    private String schoolName;
    private byte[] certificate;
    private boolean isBlocked;
    List<Application> applicationList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Applicant applicant = (Applicant) o;
        return id == applicant.id && isBlocked == applicant.isBlocked && Objects.equals(email, applicant.email)
                && Objects.equals(password, applicant.password) && role == applicant.role
                && Objects.equals(firstName, applicant.firstName) && Objects.equals(middleName, applicant.middleName)
                && Objects.equals(lastName, applicant.lastName) && Objects.equals(city, applicant.city)
                && Objects.equals(region, applicant.region) && Objects.equals(schoolName, applicant.schoolName)
                && Arrays.equals(certificate, applicant.certificate) && Objects.equals(applicationList, applicant.applicationList);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, email, password, role, firstName, middleName, lastName, city, region, schoolName,
                isBlocked, applicationList);
        result = 31 * result + Arrays.hashCode(certificate);
        return result;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", certificate=" + Arrays.toString(certificate) +
                ", isBlocked=" + isBlocked +
                ", applicationList=" + applicationList +
                '}';
    }
}
