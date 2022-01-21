package project.bfour.debtormaintenance.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.bfour.debtormaintenance.dao.UserDao;
import project.bfour.debtormaintenance.model.RegisterUser;
import project.bfour.debtormaintenance.model.User;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserDao userDao;

//    Debtor Register
    @RequestMapping("/debtor/register")
    public String registerDebtor() {
        return "debtorRegister";
    }

    @RequestMapping(value = "/debtor/register", method = RequestMethod.POST)
    public String registerForm(RegisterUser registerUser, HttpSession session) {

        if (registerUser.getPassword().equals(registerUser.getConfirmPassword())) {
//            TODO check whether the username exists:: to use DAO
            int status = userDao.register(new User(registerUser.getUsername(), registerUser.getPassword()));
            if (status == -1) {
                session.setAttribute("userRegistered", "error");
                return "redirect:/debtor/register";
            } else {
                session.setAttribute("message", "Debtor Account Created Successfully!");
                return "redirect:/"; // debtor login
            }
        } else {
            session.setAttribute("error", "Password Mismatch!");
            return "redirect:/debtor/register";
        }
    }
//    :: Debtor Register

//    Login GetMapping
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST) // debtor Login
    public String login(User user, HttpSession session, @RequestParam String who) {
        return validation(user, who, session);
    }
//    :: Login

//    Admin Login
    @RequestMapping("/admin/login") // admin login
    public String adminLogin() {
        return "adminLogin";
    }
//    ::Admin Login

    public String validation (User user, String who, HttpSession session) {

        int status = userDao.validate(user, who);
        if(status == 1) { // does user exist in out debtor table
//            sout("Checking User");
            session.setAttribute("username", user.getUsername());
            session.setAttribute("who", who);
            session.setAttribute("uid", userDao.getUid(user.getUsername()));
            if (who.equals("deb")){
                return "redirect:/debtor/home";
            } else {
                return "redirect:/admin/home";
            }
        } else if(status == 10){
            session.setAttribute("IncorrectPassword", "true");
            if (who.equals("deb")) {
                return "redirect:/";
            } else {
                return "redirect:/admin/login";
            }
        } else {
//            sout("User Not Found");
            session.setAttribute("UserNotFound", "true");
            if (who.equals("deb")){
                return "redirect:/";
            } else {
                return "redirect:/admin/login";
            }
        }
    }

}
