package com.projekat.notification_service.ms_sm_ac.dto;

public class TokenRequestDto {

    private String username;
    private String password;

    public TokenRequestDto () {

    }

    public TokenRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
