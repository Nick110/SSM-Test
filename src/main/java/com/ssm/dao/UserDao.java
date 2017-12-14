package com.ssm.dao;

import com.ssm.entity.Equipment;
import com.ssm.entity.User;

import java.util.List;

public interface UserDao {
    //这里以接口形式定义了数据库操作方法,我们只需
    // 在Mybatis映射文件中对其进行映射就可以直接使用
    public void addUser(User user); //添加
//    public User selectById(int id);
    public User selectByName(String loginName);
    public List<Equipment> selectEqmtById (int userId);
    public List<Equipment> selectAllEquipments();
    public void addEquipment(Equipment equipment);
}
