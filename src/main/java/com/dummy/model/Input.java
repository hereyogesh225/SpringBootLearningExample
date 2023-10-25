package com.dummy.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class Input {

    @Min(1)
    @Max(10)
    private int numberBetweenOneAndTen;

    @Email
    private String emailId;

    @NotNull
    private String name;

    @NotEmpty
    private String surname;

    @NotNull
    @Size(min = 4, max = 20, message = "size must be between 4 and 20")
    private String password;
}
