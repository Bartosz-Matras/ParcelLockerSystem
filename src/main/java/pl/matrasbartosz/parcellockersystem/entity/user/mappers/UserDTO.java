package pl.matrasbartosz.parcellockersystem.entity.user.mappers;

public record UserDTO(Long id, String email, String name, String surname, String password, String phoneNumber,
                    String country, String state, String city, String zipCode, String street, String buildingNumber, String apartmentNumber) {
}
