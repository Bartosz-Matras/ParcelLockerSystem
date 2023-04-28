package pl.matrasbartosz.parcellockersystem.entity.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private String country;
    private String state;
    private String city;
    private String zipCode;
    private String street;
    private String buildingNumber;
    private String apartmentNumber;
}
