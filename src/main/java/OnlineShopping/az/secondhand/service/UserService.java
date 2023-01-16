package OnlineShopping.az.secondhand.service;

import OnlineShopping.az.secondhand.dto.CreateUserRequest;
import OnlineShopping.az.secondhand.dto.UpdateUserRequest;
import OnlineShopping.az.secondhand.dto.UserDto;
import OnlineShopping.az.secondhand.exception.UserNotFoundException;
import OnlineShopping.az.secondhand.mapper.UserMapper;
import OnlineShopping.az.secondhand.model.UserEntity;
import OnlineShopping.az.secondhand.repository.UserRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Data
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        log.info("MY_METHOD_IS_STARTED");
        return UserMapper.INSTANCE.mapListEntityToListDto(userRepository.findByIsActiveIsTrue());
    }

    public UserDto getUserById(Long id) {
        log.info("MY_METHOD_IS_STARTED");
        return UserMapper.INSTANCE.mapEntityToDto(findUserById(id));
    }

    public UserDto createUser(CreateUserRequest createUserRequest) {
        log.info("MY_METHOD_IS_STARTED");
        return UserMapper.INSTANCE.mapEntityToDto(userRepository.save(UserMapper.INSTANCE.mapCreateUserRequestToUserEntity(createUserRequest)));
    }

    public UserDto updateUser(UpdateUserRequest updateUserRequest, Long id) {
        log.info("MY_METHOD_IS_STARTED");
        UserEntity userEntity = findUserById(id);
        UserEntity instanceEntity = UserMapper.INSTANCE.getEntity(updateUserRequest, userEntity);
        log.info("MY_METHOD_IS_FINISHED");
        return UserMapper.INSTANCE.mapEntityToDto(userRepository.save(instanceEntity));
    }

    public void deactiveUser(Long id) {
        log.info("MY_METHOD_IS_STARTED");
        UserEntity userEntity = findUserById(id);
        userEntity.setIsActive(false);
        userRepository.save(userEntity);
        log.info("MY_METHOD_IS_FINISHED");
    }

    public void deleteUser(Long id) {
        log.info("MY_METHOD_IS_STARTED");
        UserEntity userEntity = findUserById(id);
        userRepository.delete(userEntity);
        log.info("MY_METHOD_IS_FINISHED");
    }

    private UserEntity findUserById(Long id) {
        log.info("MY_METHOD_IS_STARTED");
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException("USER_NOT_FOUND");
        });
        log.info("MY_METHOD_IS_FINISHED");
        return userEntity;
    }
}
