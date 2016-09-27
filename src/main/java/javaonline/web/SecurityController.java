package javaonline.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SecurityController {

    @RequestMapping(value = "/admin/index", method = RequestMethod.GET)
    public String admin() {
        return "/admin/index";
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accessDenied(ModelMap model) {
        model.addAttribute("error", "true");
        return "/denied";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "/login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout (HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null){
        new SecurityContextLogoutHandler().logout(request, response, auth);
    }
    return "redirect:/login?logout";
}

    @RequestMapping(value = "/error403", method = RequestMethod.GET)
    public String error() {
        return "error403";
    }

}
