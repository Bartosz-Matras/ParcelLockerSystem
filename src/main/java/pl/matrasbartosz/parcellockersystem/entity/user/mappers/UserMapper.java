package pl.matrasbartosz.parcellockersystem.entity.user.mappers;

import pl.matrasbartosz.parcellockersystem.entity.user.Address;
import pl.matrasbartosz.parcellockersystem.entity.user.User;

public class UserMapper {

    private UserMapper() {}

    public static User mapUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.name());
        user.setSurname(userDTO.surname());
        user.setPassword(userDTO.password());
        user.setEmail(userDTO.email());
        user.setPhoneNumber(userDTO.phoneNumber());
        user.setAddress(returnAddress(userDTO));
        return user;
    }

    private static Address returnAddress(UserDTO userDTO) {
        Address address = new Address();
        address.setCountry(userDTO.country());
        address.setState(userDTO.state());
        address.setCity(userDTO.city());
        address.setZipCode(userDTO.zipCode());
        address.setStreet(userDTO.street());
        address.setBuildingNumber(userDTO.buildingNumber());
        address.setApartmentNumber(userDTO.apartmentNumber());
        return address;
    }

    public static UserDTO mapUserToUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getSurname(),
                "",
                user.getPhoneNumber(),
                user.getAddress().getCountry(),
                user.getAddress().getState(),
                user.getAddress().getCity(),
                user.getAddress().getZipCode(),
                user.getAddress().getStreet(),
                user.getAddress().getBuildingNumber(),
                user.getAddress().getApartmentNumber()
        );
    }

}
