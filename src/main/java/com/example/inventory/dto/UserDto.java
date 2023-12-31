package com.example.inventory.dto;

import com.example.inventory.enums.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;
    private String name;
    private String phoneNumber;
    private JobStatus jobStatus;

}
