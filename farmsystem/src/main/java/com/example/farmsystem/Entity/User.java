package com.example.farmsystem.Entity;

import jakarta.persistence.*;
import java.util.List;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", unique = true, nullable = false, length = 50)
    private String userId; // 고유 사용자 ID, 로그인 시 사용

    @Column(name = "mobile_number", length = 15, nullable = false, unique = true)
    private String mobileNumber;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "nickname", length = 10)
    private String nickname;

    @Column(name = "password", nullable = false)
    private String password; // 암호화된 비밀번호

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

