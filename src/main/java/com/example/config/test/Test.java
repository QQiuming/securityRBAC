package com.example.config.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
public static void main(String[] args) {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    System.out.println(bCryptPasswordEncoder.encode("1234567"));
}
}
