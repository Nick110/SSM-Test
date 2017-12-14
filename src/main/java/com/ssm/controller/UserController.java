package com.ssm.controller;

import com.ssm.entity.Equipment;
import com.ssm.entity.User;
import com.ssm.service.UserService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
@Scope("prototype")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping("/index")
    public String index(User user) {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam String loginName, @RequestParam String password) {
        boolean loginSuccess = userService.login(loginName, password);

        User user1 = userService.selectByName(loginName);
        int userType = userService.isAdmin(user1);
        System.out.println(user1.getId());
        ModelAndView mav1 = new ModelAndView("index");
        ModelAndView mav2 = new ModelAndView("userHome");
        if (loginSuccess) {
            if (userType == 1) {
                List<Equipment> equipments  = userService.selectEqmtById(user1.getId());
                mav2.addObject("equipments", equipments);
            }
            else {
                List<Equipment> equipments = userService.selectAllEquipments();
                mav2.addObject("equipments", equipments);
            }
            mav2.addObject("user1", user1);
            return mav2;
        }
        mav1.addObject("error", "用户名或密码填写错误！");
        return mav1;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(User user){
        return "register";
    }

    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    public String doRegister(User user, HttpServletRequest request){
        userService.addUser(user);
        request.setAttribute("user", user);
        return "registerSuccess";
    }

    @RequestMapping(value = "addEquipment", method = RequestMethod.GET)
    public String addEquipment(Equipment equipment){
        return "addEquipment";
    }

    @RequestMapping(value = "doAddEquipment", method = RequestMethod.POST)
    public String doAddEquipment(Equipment equipment, HttpServletRequest request){
        Date date = new Date();
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        equipment.setAddTime(date);
        logger.info(date.toString());
        userService.addEquipment(equipment);
        List<Equipment> equipments = userService.selectAllEquipments();
        request.setAttribute("equipments",equipments);
        return "userHome";
    }
}
