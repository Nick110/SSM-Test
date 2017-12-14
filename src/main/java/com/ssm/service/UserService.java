package com.ssm.service;

import com.ssm.entity.Equipment;
import com.ssm.entity.User;

import java.util.List;

public interface UserService {
    public void addUser(User user);
    public boolean login(String loginName, String password);
    public int isAdmin(User user);
    public User selectByName(String loginName);
//    public User selectById(int id);
    public List<Equipment> selectEqmtById(int userId);
    public List<Equipment> selectAllEquipments();
    public void addEquipment(Equipment equipment);
}
