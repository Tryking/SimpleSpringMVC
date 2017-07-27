package com.tryking.service;

import com.tryking.dao.UserDao;
import com.tryking.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private UserDao userDao;

    //是否有匹配用户
    public boolean hasMatchUser(String userName, String password) {
        int matchCount = userDao.getMatchCount(userName, password);
        return matchCount > 0;
    }

    //根据用户名找用户
    public User findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    //登录成功，给用户加上5个积分
    @Transactional
    public void loginSuccess(User user) {
        user.setCredits(5 + user.getCredits());
        userDao.updateLoginInfo(user);
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
