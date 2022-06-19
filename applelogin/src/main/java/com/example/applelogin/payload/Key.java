package com.example.applelogin.payload;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Key {

    private String kty;
    private String kid;
    private String use;
    private String alg;
    private String n;
    private String e;


}
