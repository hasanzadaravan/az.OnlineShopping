package OnlineShopping.service

import OnlineShopping.az.secondhand.dto.CreateUserRequest
import OnlineShopping.az.secondhand.dto.UpdateUserRequest
import OnlineShopping.az.secondhand.mapper.UserMapper
import OnlineShopping.az.secondhand.model.UserEntity
import OnlineShopping.az.secondhand.repository.UserRepository
import OnlineShopping.az.secondhand.service.UserService
import io.github.benas.randombeans.EnhancedRandomBuilder
import spock.lang.Specification

class UserServiceTest extends Specification {
    private UserRepository userRepository
    private UserService userService
    private random = EnhancedRandomBuilder.aNewEnhancedRandom()

    void setup() {
        userRepository = Mock()
        userService = new UserService(userRepository)
    }

    def "GetAllUsers"() {
        given:
        def users = [random.nextObject(UserEntity)]

        when:
        def result = userService.getAllUsers()
        then:
        1 * userRepository.findByIsActiveIsTrue() >> users

        result == UserMapper.INSTANCE.mapListEntityToListDto(users)
    }

    def "GetUserById"() {
        given:
        def user = random.nextObject(UserEntity)
        def id = random.nextLong()

        when:
        def result = userService.getUserById(id)

        then:
        1 * userRepository.findById(id) >> Optional.of(user)

        result == UserMapper.INSTANCE.mapEntityToDto(user)

    }

    def "CreateUser"() {
        given:
        def user = random.nextObject(CreateUserRequest)

        when:
        userService.createUser(user)

        then:
        1 * userRepository.save(UserMapper.INSTANCE.mapCreateUserRequestToUserEntity(user))
    }

    def "UpdateUser"() {
        given:
        def updateUserRequest = new UpdateUserRequest(
                "ravanhasanzada@gmail.com",
                "Ravan", "Hasanzada",
                " ", " ", true)
        def iD = 2L
        def userEntity = new UserEntity(2l, "ravanhasanzada@gmail.com",
                "Ravan", "Hasanzada",
                " ", " ", true)
        when:
        userService.updateUser(updateUserRequest, iD)

        then:
        1 * userRepository.findById(iD) >> Optional.of(userEntity)

        1 * userRepository.save(userEntity)

        updateUserRequest.middleName == userEntity.middleName
        updateUserRequest.postCode == userEntity.postCode
        updateUserRequest.email == userEntity.email
    }

    def "DeactiveUser"() {
        given:
        def iD = random.nextLong()
        def userEntity = random.nextObject(UserEntity)

        when:
        userService.deactiveUser(iD)
        then:
        1 * userRepository.findById(iD) >> Optional.of(userEntity)
        userEntity.setIsActive(false)
        1 * userRepository.save(userEntity)

    }

    def "DeleteUser"() {
        given:
        def iD = random.nextLong()
        def userEntity = random.nextObject(UserEntity)
        when:
        userService.deleteUser(iD)
        then:
        1 * userRepository.findById(iD) >> Optional.of(userEntity)
        1 * userRepository.delete(userEntity)
    }
}
