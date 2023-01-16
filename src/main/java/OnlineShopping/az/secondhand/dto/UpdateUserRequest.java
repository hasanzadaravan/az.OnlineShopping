package OnlineShopping.az.secondhand.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UpdateUserRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String middleName;
    private String postCode;
    private Boolean isActive;
}
