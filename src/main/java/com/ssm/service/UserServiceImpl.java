package com.ssm.service;

import com.ssm.dao.UserDao;
import com.ssm.entity.Equipment;
import com.ssm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    //自动注入userDao 用于访问数据库
    @Autowired
    private UserDao userDao;

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public boolean login(String loginName, String password) {
        System.out.println("输入的账号:" + loginName + "输入的密码:" + password);
        User user = userDao.selectByName(loginName);
        if (user != null) {
            System.out.println("查询出来的真名：" + user.getRealName() + "查询出来的密码:" + user.getPassword());
            if (password.equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public int isAdmin(User user) {
        return user.getType();
    }

    public User selectByName(String loginName) {
        return userDao.selectByName(loginName);
    }

    public List<Equipment> selectEqmtById(int userId) {
        return userDao.selectEqmtById(userId);
    }

    public List<Equipment> selectAllEquipments() {
        return userDao.selectAllEquipments();
    }

    public void addEquipment(Equipment equipment) {
        userDao.addEquipment(equipment);
    }
}
