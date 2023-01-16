package OnlineShopping.az.secondhand.repository;

import OnlineShopping.az.secondhand.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity>  findByIsActiveIsTrue();
}
