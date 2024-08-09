package service;

import entity.UserEntity;
import exception.UserNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserService {

    public UserEntity createUser(UserEntity userEntity) {
        UserEntity.persist(userEntity);
        return userEntity;
    }

    public List<UserEntity> findAll(Integer page, Integer size) {
        return UserEntity.findAll()
                .page(page, size)
                .list();
    }

    public UserEntity findById(UUID userId) {
        return (UserEntity) UserEntity.findByIdOptional(userId)
                .orElseThrow(() -> new UserNotFoundException());
    }

    public UserEntity updateUser(UUID userId, UserEntity userEntity) {
        var user = findById(userId);

        user.username = userEntity.username;
        UserEntity.persist(user);

        return user;
    }

    public void deleteById(UUID userId) {
        var user = findById(userId);
        UserEntity.deleteById(user.id);
    }
}
