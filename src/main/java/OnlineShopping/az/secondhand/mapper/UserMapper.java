package OnlineShopping.az.secondhand.mapper;

import OnlineShopping.az.secondhand.dto.CreateUserRequest;
import OnlineShopping.az.secondhand.dto.UpdateUserRequest;
import OnlineShopping.az.secondhand.dto.UserDto;
import OnlineShopping.az.secondhand.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    public abstract UserEntity mapDtoToEntity(UserDto userDto);

    public abstract UserDto mapEntityToDto(UserEntity userEntity);

    public abstract List<UserDto> mapListEntityToListDto(List<UserEntity> userEntityList);

    public abstract UserDto mapCreateUserRequestToUserDto(CreateUserRequest createUserRequest);

    @Mapping(target = "id", ignore = true)

    public abstract UserEntity mapCreateUserRequestToUserEntity(CreateUserRequest createUserRequest);


    public UserEntity getEntity(UpdateUserRequest userRequest, UserEntity userEntity) {

        userEntity.setFirstName(userRequest.getFirstName());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setLastName(userEntity.getLastName());
        userEntity.setMiddleName(userEntity.getMiddleName());
        userEntity.setPostCode(userEntity.getPostCode());
        userEntity.setIsActive(userRequest.getIsActive());
        return userEntity;
    }
}
