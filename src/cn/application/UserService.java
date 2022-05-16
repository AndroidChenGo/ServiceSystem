package cn.application;

import cn.domain.model.User;
import cn.domain.resposity.UserRepository;
import cn.infrastructure.repositoryImpl.UserRepositoryImpl;

public class UserService  {

    private UserRepository userRepository = new UserRepositoryImpl();

    public void registUser(User user,String userType) {
        userRepository.saveUser(user,userType);
    }

    public User login(User user,String userType) {

        return userRepository.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword(),userType);
    }

    public boolean existsUsername(String username,String userType) {

        if (userRepository.queryUserByUsername(username,userType) == null) {
           // 等于null,说明没查到，没查到表示可用
           return false;
        }
        return true;
    }
}
