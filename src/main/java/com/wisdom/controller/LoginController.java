package com.wisdom.controller;

import com.wisdom.common.annotation.SysLog;
import com.wisdom.entity.UsersEntity;
import com.wisdom.service.UsersEntityService;
import com.wisdom.utils.HttpContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsersEntityService usersEntityService;

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    public static final Map<String, String> SESSION_USER = new HashMap<>();

    @RequestMapping(value="/wisdom")
//    @ResponseBody
    public String toLogin(){
        return "loginPage/login";
    }

    //    去登录并限制重复登录
    /*@SysLog("用户登录")
    @RequestMapping(value="doLogin")
    public String toLogin(@RequestParam(value = "username",required = false)String username, @RequestParam(value = "password",required = false)String password,RedirectAttributes attributes){
        HttpSession session = HttpContextUtils.getHttpServletRequest().getSession();
//        System.out.println("account======" + account);
////        System.out.println("password======" + password);
        UsersEntity user = usersEntityService.login(username, password);
        session.invalidate();
        if (user != null) {
//            判断map中是否包含用户账号
            if (SESSION_USER.containsValue(user.getUsername())) {
                attributes.addFlashAttribute("info", "对不起，您已在其他地方登录，您被迫下线！");
                SESSION_USER.remove("uid");

                return "redirect:/login/wisdom";
            } else {
                SESSION_USER.put("uid",user.getUsername());
                session.setAttribute("loginUser", user);
                return "redirect:/login/toIndex";
            }
        } else {
            attributes.addFlashAttribute("info","用户名或者密码错误");
            return "redirect:/login/wisdom";
        }
    }*/

    @SysLog("用户登录")
    @RequestMapping(value="doLogin")
    public String login(@RequestParam(value = "username",required = false)String username, @RequestParam(value = "password",required = false)String password,RedirectAttributes model){
        System.out.println("username==="+username);
        System.out.println("password==="+password);
        HttpSession session = null;
        UsersEntity usersEntity = null;
        if (username != null && password != null && !"".equals(username) && !"".equals(password)) {
            usersEntity = usersEntityService.login(username, password);
            if (usersEntity != null) {
                session = HttpContextUtils.getHttpServletRequest().getSession();
                session.setAttribute("loginUser", usersEntity);
//            return "redirect:/login/toIndex";
                return "redirect:/login/toIndex";
            } else {
                model.addFlashAttribute("info", "用户名或者密码错误！");
            }
        }
        return "redirect:/login/wisdom";
    }

    @RequestMapping(value="toRight")
    public String toRight(){
        return "rightPage";
    }

    @RequestMapping(value="toIndex")
    public String toIndex(){
//        System.out.println("++++++++++++++++++++++");
        return "indexPage/index";
    }

    //    退出登录
    @RequestMapping(value="logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        logger.info("==========清除session================");
        //清除session
        request.getSession().removeAttribute("loginUser");
        session.invalidate();
        return "redirect:/login/wisdom";
    }

    @RequestMapping(value="test66666666666")
    @ResponseBody
    public String test66666666666(){
        return "99999999999999";
    }

}
