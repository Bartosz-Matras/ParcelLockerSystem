package pl.matrasbartosz.parcellockersystem;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(name = "parcellockersystem", scheme = "basic",
        type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@OpenAPIDefinition(info = @Info(title = "Parcel Locker System", version = "1.0", description = "Parcel Locker System"))
public class ParcelLockerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParcelLockerSystemApplication.class, args);
    }

}
