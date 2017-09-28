package org.liuyi.projects.personal.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {

    @Autowired
    private IUserService userService;                
    @RequestMapping(value = "/login/login.do")
    public ModelAndView searchUserList(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("userList",userService.getUser());
        ModelAndView mav=new ModelAndView("",model);
        System.out.println("能开始那");
        return mav;
    }

}
