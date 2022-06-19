package com.example.applelogin;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicesResponse {
    private String state;
    private String code;
    private String id_token;
    private String user;
}
