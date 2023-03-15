package com.example.phuonglth_sprint_2.service.valid;

public interface IValidService {

    Boolean existsByEmail(String email);
    Boolean existsByPhone(String phone);
}
