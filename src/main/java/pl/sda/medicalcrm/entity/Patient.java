package pl.sda.medicalcrm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@DiscriminatorValue("patient")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends User {

    @NotEmpty
    @NotBlank
    @Pattern(regexp = "\\w[a-zA-Z]{1,}")
    private String name;

    @NotEmpty
    @NotBlank
    @Pattern(regexp = "\\w[a-zA-Z\\-]{1,}")
    private String surname;

    @NotEmpty
    @NotBlank
    @Pattern(regexp = "\\d{11}")
    private String pesel;

}
