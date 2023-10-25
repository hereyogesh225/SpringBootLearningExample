package com.dummy.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UserAccount {

    @NotNull(groups = BasicInfo.class)
    @Size(min = 4, max = 15, groups = BasicInfo.class)
    private String password;

    @NotBlank(groups = BasicInfo.class)
    private String name;

    @Min(value = 18, message = "Age should not be less than 18", groups = AdvanceInfo.class)
    private int age;

    @NotBlank(groups = AdvanceInfo.class)
    private String phone;
}
