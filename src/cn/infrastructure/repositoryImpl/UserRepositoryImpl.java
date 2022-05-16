package cn.infrastructure.repositoryImpl;

import cn.domain.model.User;
import cn.domain.resposity.UserRepository;

public class UserRepositoryImpl extends BaseRespository implements UserRepository {
    @Override
    public User queryUserByUsername(String username,String userType) {
        StringBuilder sql = new StringBuilder("select `username`,`password` from ");
        sql.append(userType).append(" where username = ?");
        return queryForOne(User.class, sql.toString(), username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password,String userType) {
        StringBuilder sql = new StringBuilder("select `username`,`password` from ");
        sql.append(userType).append(" where username = ? and password = ?");
        return queryForOne(User.class, sql.toString(), username,password);
    }

    @Override
    public int saveUser(User user,String userType) {
        StringBuilder sql = new StringBuilder("insert into ");
        sql.append(userType).append("(`username`,`password`,`email`) values(?,?,?)");
        return update(sql.toString(), user.getUsername(), user.getPassword());
    }

}
