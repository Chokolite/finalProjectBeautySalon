package ua.kharkiv.syvolotskyi.service.impl;

import ua.kharkiv.syvolotskyi.entity.User;
import ua.kharkiv.syvolotskyi.exception.ValidationEnum;
import ua.kharkiv.syvolotskyi.exception.ValidationException;
import ua.kharkiv.syvolotskyi.repository.UserRepository;
import ua.kharkiv.syvolotskyi.service.UserService;
import ua.kharkiv.syvolotskyi.transaction.TransactionManager;

import java.util.List;

public class UserServiceImpl implements UserService {

    private TransactionManager transactionManager;
    private UserRepository userRepository;

    public UserServiceImpl(TransactionManager transactionManager, UserRepository userRepository) {
        this.transactionManager = transactionManager;
        this.userRepository = userRepository;
    }

    @Override
    public User getById(Long id) {
        return transactionManager.execute(c -> userRepository.getById(c, id));
    }

    @Override
    public List<User> getAll() {
        return transactionManager.execute(c -> userRepository
                .getAll(c));
    }


    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        return transactionManager.execute(c -> userRepository.getUserByEmailAndPassword(c, email, password));
    }

    @Override
    public Long save(User user) {
        return transactionManager.execute(c -> userRepository.save(c, user));
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(User user) {
        if (existsByIdAndEmail(user.getId(), user.getEmail())) {
            ValidationException.builder().put("emailError", ValidationEnum.EMAIL_EXISTS);
        } else {
            transactionManager.execute(c -> {
                userRepository.update(c, user);
                return null;
            });
        }
    }

    private boolean existsByIdAndEmail(Long id, String email) {
        return transactionManager.execute(c -> userRepository.existsByIdAndEmail(c, id, email));
    }


    @Override
    public boolean existsByEmail(String email) {
        return transactionManager.execute(c -> userRepository.existsByEmail(c, email));
    }

    @Override
    public boolean isEnabledById(Long id) {
        return transactionManager.execute(c -> userRepository.isEnabledById(c, id));
    }

    @Override
    public void updateEnabled(Long id, boolean b) {
        transactionManager.execute(c -> {
            userRepository.updateEnabled(c, id, b);
            return null;
        });
    }
}

