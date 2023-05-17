package pl.matrasbartosz.parcellockersystem.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String email;
    private String name;
    private String surname;
    private String password;
    private String phoneNumber;
    private String country;
    private String state;
    private String city;
    private String zipCode;
    private String street;
    private String buildingNumber;
    private String apartmentNumber;
}
