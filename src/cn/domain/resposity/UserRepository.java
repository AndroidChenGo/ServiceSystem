package cn.domain.resposity;

import cn.domain.model.Admin;
import cn.domain.model.Client;
import cn.domain.model.Freelancer;
import cn.domain.model.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserRepository {
    /**
     * 根据 username 查询用户
     */
    User queryUserByUsername(String username,String userType);

    /**
     * 根据 username和 password查询用户
     */
    User queryUserByUsernameAndPassword(String username, String password,String userType);

    /**
     * 保存用户
     */
    int saveUser(User user,String userType);
}
