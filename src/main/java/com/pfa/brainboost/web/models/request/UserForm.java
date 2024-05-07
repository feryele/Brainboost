package com.pfa.brainboost.web.models.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {

    @NotEmpty(message="the name is required")
    private String name;
    @NotEmpty(message="the email is required")
    private String email;
    
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
    private String phone;
    
}
