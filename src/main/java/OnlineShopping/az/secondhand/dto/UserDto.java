package OnlineShopping.az.secondhand.dto;

import lombok.*;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String email;
    private String firstName;
    private String lastName;
    private String middleName;
    private String postCode;
    private Boolean isActive;
}
