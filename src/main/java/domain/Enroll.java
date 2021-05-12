package domain;

import domain.enums.Role;

import java.util.List;

public class Enroll {
    private long id;
    private String name;
    private String middleName;
    private String surname;
    private String email;
    private String password;
    private Role role;
    private Address address;
    private School school;
    private boolean isBlocked;
    List<Application> applicationList;
}
