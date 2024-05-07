package com.pfa.brainboost.web.models.request;

import jakarta.validation.constraints.Min;
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

public class CourseForm {
    
    @NotEmpty(message="the title is required")
    private String title;
    @Size(min= 10 , message= "the description should be at least 10 characters")
    @Size(max= 20 , message= "the description can not exceed  30 characters")
    private String description;

    
    private Boolean isFree;
    
    @Min(0)
    private double price;
    
}
