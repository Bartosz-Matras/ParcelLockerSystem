package pl.matrasbartosz.parcellockersystem.entity.user;

public class UserDTOMapper {

    private UserDTOMapper() {}

    public static User mapUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAddress(returnAddress(userDTO));
        user.setUserRole(UserRole.CUSTOMER);
        return user;
    }

    private static Address returnAddress(UserDTO userDTO) {
        Address address = new Address();
        address.setCountry(userDTO.getCountry());
        address.setState(userDTO.getState());
        address.setCity(userDTO.getCity());
        address.setZipCode(userDTO.getZipCode());
        address.setStreet(userDTO.getStreet());
        address.setBuildingNumber(userDTO.getBuildingNumber());
        address.setApartmentNumber(userDTO.getApartmentNumber());
        return address;
    }

    public static UserDTO mapUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setCountry(user.getAddress().getCountry());
        userDTO.setState(user.getAddress().getState());
        userDTO.setCity(user.getAddress().getCity());
        userDTO.setZipCode(user.getAddress().getZipCode());
        userDTO.setStreet(user.getAddress().getStreet());
        userDTO.setBuildingNumber(user.getAddress().getBuildingNumber());
        userDTO.setApartmentNumber(user.getAddress().getApartmentNumber());
        return userDTO;
    }

}
