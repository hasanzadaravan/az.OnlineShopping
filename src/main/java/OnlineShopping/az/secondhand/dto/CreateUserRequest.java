package OnlineShopping.az.secondhand.dto;

import lombok.*;

@Data
@EqualsAndHashCode
public class CreateUserRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String middleName;
    private String postCode;
    private Boolean isActive;
}
