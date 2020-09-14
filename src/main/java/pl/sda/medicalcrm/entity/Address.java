package pl.sda.medicalcrm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @NotBlank
    private String street;

    @NotEmpty
    @NotBlank
    private String city;

    @NotEmpty
    @NotBlank
    @Pattern(regexp = "[0-9]{2}\\-[0-9]{3}") //pattern for PL
    private String zipCode;

    @NotEmpty
    @NotBlank
    private String country;

}
