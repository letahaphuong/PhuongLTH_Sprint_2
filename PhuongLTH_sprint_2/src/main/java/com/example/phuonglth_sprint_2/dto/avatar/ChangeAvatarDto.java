package com.example.phuonglth_sprint_2.dto.avatar;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class ChangeAvatarDto {
    private String avatar;


    public ChangeAvatarDto() {
    }

    public ChangeAvatarDto(String avatar) {
        this.avatar = avatar;

    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
