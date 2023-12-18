package com.example.MyBookShopApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AuthUserController {

    private final BookstoreUserRegister userRegister;

    public List<Cookie> blacklist () {
        return new ArrayList<>();
    }

    public List<Transaction> listTransactions = new ArrayList<>();

    @Autowired
    public AuthUserController(BookstoreUserRegister userRegister) {
        this.userRegister = userRegister;
    }

    @GetMapping("/signin")
    public String handleSignin() {
        return "signin";
    }

    @GetMapping("/signup")
    public String handleSignUp(Model model) {
        model.addAttribute("regForm", new RegistrationForm());
        return "signup";
    }

    @PostMapping("/requestContactConfirmation")
    @ResponseBody
    public ContactConfirmationResponse handleRequestContactConfirmation(@RequestBody ContactConfirmationPayload payload) {
        ContactConfirmationResponse response = new ContactConfirmationResponse();
        response.setResult("true");
        return response;
    }

    @PostMapping("/approveContact")
    @ResponseBody
    public ContactConfirmationResponse handleApproveContact(@RequestBody ContactConfirmationPayload payload) {
        ContactConfirmationResponse response = new ContactConfirmationResponse();
        response.setResult("true");
        return response;
    }

    @PostMapping("/reg")
    public String handleUserRegistration(RegistrationForm registrationForm, Model model) {
        userRegister.registerNewUser(registrationForm);
        model.addAttribute("regOk", true);
        return "signin";
    }

    @PostMapping("/login")
    @ResponseBody
    public ContactConfirmationResponse handleLogin(@RequestBody ContactConfirmationPayload payload,
                                                   HttpServletResponse httpServletResponse) {
        ContactConfirmationResponse loginResponse = userRegister.jwtLogin(payload);
        Cookie cookie = new Cookie("token", loginResponse.getResult());

        //task_2
        for(int i = 0; i < blacklist().size(); i++) {
            if (!cookie.equals(blacklist().get(i))) {
                blacklist().remove(cookie);
                break;
            }
        }
        httpServletResponse.addCookie(cookie);

        return loginResponse;
    }

    @PostMapping("/login-by-phone-number")
    @ResponseBody
    public ContactConfirmationResponse handleLoginByPhoneNumber(@RequestBody ContactConfirmationPayload payload,
                                                                HttpServletResponse httpServletResponse) {
        System.out.println("Phone");
        ContactConfirmationResponse loginResponse = userRegister.jwtLoginByPhoneNumber(payload);
        Cookie cookie = new Cookie("token", loginResponse.getResult());
        httpServletResponse.addCookie(cookie);
        return loginResponse;
    }

    @GetMapping("/my")
    public String handleMy(Model model) {
        model.addAttribute("curUsr", userRegister.getCurrentUser());
        return "my";
    }

    @GetMapping("/profile")
    public String handleProfile(Model model) {
        model.addAttribute("curUsr", userRegister.getCurrentUser());
        model.addAttribute("changeForm", new ChangePasswordAndUserDataForm());
        model.addAttribute("countForm", new CountForm());
        model.addAttribute("listTransactions", listTransactions);
        System.out.println(listTransactions.size());
        return "profile";
    }

    @PostMapping("/profile/new/password")
    public String changePasswordCurrentUser(ChangePasswordAndUserDataForm changePasswordForm) {
        userRegister.changeNewPasswordAndUserData(changePasswordForm);
        return "redirect:/profile";
    }

    @PostMapping("/payment")
    public String topUpYourAccount(CountForm countForm) {
        System.out.println(countForm + "Ням ням");
        userRegister.topUpYourAccount(countForm);
        Transaction transaction = new Transaction(countForm);
        listTransactions.add(transaction);
        return "redirect:/profile";
    }
}
