package com.sh.mvc.member.model.entity;

import java.time.LocalDate;
import java.util.List;

public class Member {
    private String id;
    private String password;
    private String name;
    // U, A 두개의 값만 가지는 enum클래스 Role 생성
    // Role이라는 클래스가 이미 존재하니 import되는것에 유의! (됐다면 import된 구문 삭제)
    private Role role; // U, A
    // M, F 두개의 값만 가지는 enum클래스 Gender 생성
    private Gender gender; // M, F
    private LocalDate birthday;
    private String email;
    private String phone;
    private List<String> hobby;
    private int point;
    private LocalDate regDate;

    // 생성자, 필드, getter/setter toString 모두 Alt+insert로 만들수있다
    public Member() {
    }

    public Member(String id, String password, String name, Role role, Gender gender, LocalDate birthday, String email, String phone, List<String> hobby, int point, LocalDate regDate) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.role = role;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.hobby = hobby;
        this.point = point;
        this.regDate = regDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", hobby=" + hobby +
                ", point=" + point +
                ", regDate=" + regDate +
                '}';
    }
}
