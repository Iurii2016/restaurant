package javaonline.web;

import javaonline.dao.IUserDao;
import javaonline.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private IUserDao IUserDao;

    @Autowired
    public void setIUserDao(IUserDao IUserDao) {
        this.IUserDao = IUserDao;
    }

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public ModelAndView login(@ModelAttribute String username, @ModelAttribute String password) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("client/index");
//        modelAndView.addObject("ListOfDishes", IUserDao.findByUserName(username));
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public ModelAndView logout(@ModelAttribute String username, @ModelAttribute String password) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("client/index");
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        IUserDao.registerUser(user);
//        return modelAndView;
//    }
}
