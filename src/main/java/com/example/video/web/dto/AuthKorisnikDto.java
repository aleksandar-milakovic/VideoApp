package com.example.video.web.dto;

import com.sun.istack.NotNull;

public class AuthKorisnikDto {

    @NotNull
    private String username;

    @NotNull
    private String password;

    public AuthKorisnikDto() {}

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}
