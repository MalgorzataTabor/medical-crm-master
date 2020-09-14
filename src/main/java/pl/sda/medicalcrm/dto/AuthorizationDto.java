package pl.sda.medicalcrm.dto;

import com.google.gson.annotations.SerializedName;

public class AuthorizationDto {

    @SerializedName(value = "username")
    private String username;

    @SerializedName(value = "password")
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
