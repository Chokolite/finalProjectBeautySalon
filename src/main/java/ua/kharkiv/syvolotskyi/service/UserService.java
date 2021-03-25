package ua.kharkiv.syvolotskyi.service;

import ua.kharkiv.syvolotskyi.entity.User;

import java.util.List;

public interface UserService {
    User getById(Long id);

    List<User> getAll();

    Long save(User user);

    void delete(Long id);

    void update(User user);

    User getUserByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    boolean isEnabledById(Long id);

    void updateEnabled(Long id, boolean b);
}
