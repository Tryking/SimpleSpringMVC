package com.tryking.dao;

import com.tryking.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;
    private final static String MATCH_COUNT_SQL = "SELECT count(*) FROM t_user WHERE user_name =? and password=?";
    private final static String FIND_USER_SQL = "SELECT user_id,user_name,credits FROM t_user WHERE user_name=?";
    private final static String UPDATE_LOGIN_INFO_SQL = "UPDATE t_user SET credits=? WHERE user_id=?";

    //根据用户名和密码获取匹配的数量
    public int getMatchCount(String userName, String password) {
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL, new Object[]{userName, password}, Integer.class);
    }

    //更新用户的信息
    public void updateLoginInfo(User user) {
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL, new Object[]{user.getCredits(), user.getUserId()});
    }

    //根据用户名找到用户
    public User findUserByUserName(final String userName) {
        final User user = new User();
        jdbcTemplate.query(FIND_USER_SQL, new Object[]{userName}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setCredits(resultSet.getInt("credits"));
            }
        });
        return user;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
