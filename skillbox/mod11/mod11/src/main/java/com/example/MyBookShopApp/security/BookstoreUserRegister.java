package com.example.MyBookShopApp.security;

import com.example.MyBookShopApp.security.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BookstoreUserRegister {

    private final BookstoreUserRepository bookstoreUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final BookstoreUserDetailsService bookstoreUserDetailsService;
    private final JWTUtil jwtUtil;

    @Autowired
    public BookstoreUserRegister(BookstoreUserRepository bookstoreUserRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, BookstoreUserDetailsService bookstoreUserDetailsService, JWTUtil jwtUtil) {
        this.bookstoreUserRepository = bookstoreUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.bookstoreUserDetailsService = bookstoreUserDetailsService;
        this.jwtUtil = jwtUtil;
    }

    public BookstoreUser registerNewUser(RegistrationForm registrationForm) {
        BookstoreUser userbyEmail = bookstoreUserRepository.findBookstoreUserByEmail(registrationForm.getEmail());
        BookstoreUser userbyPhone = bookstoreUserRepository.findBookstoreUserByPhone(registrationForm.getPhone());

        if (userbyEmail == null && userbyPhone == null) {
            BookstoreUser user = new BookstoreUser();
            user.setName(registrationForm.getName());
            user.setEmail(registrationForm.getEmail());
            user.setPhone(registrationForm.getPhone());
            user.setPassword(passwordEncoder.encode(registrationForm.getPass()));
            user.setCount(0L);
            bookstoreUserRepository.save(user);
            return user;
        }else {
            return userbyPhone;
        }
    }

    public ContactConfirmationResponse login(ContactConfirmationPayload payload) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(payload.getContact(),
                        payload.getCode()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        ContactConfirmationResponse response = new ContactConfirmationResponse();
        response.setResult("true");
        return response;
    }

    public ContactConfirmationResponse jwtLoginByPhoneNumber(ContactConfirmationPayload payload) {
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setPhone(payload.getContact());
        registrationForm.setPass(payload.getCode());
        registerNewUser(registrationForm);
        UserDetails userDetails = bookstoreUserDetailsService.loadUserByUsername(payload.getContact());
        String jwtToken = jwtUtil.generateToken(userDetails);
        ContactConfirmationResponse response = new ContactConfirmationResponse();
        response.setResult(jwtToken);
        return response;
    }

    public ContactConfirmationResponse jwtLogin(ContactConfirmationPayload payload) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(payload.getContact(),
                payload.getCode()));
        BookstoreUserDetails userDetails =
                (BookstoreUserDetails) bookstoreUserDetailsService.loadUserByUsername(payload.getContact());

        String jwtToken = jwtUtil.generateToken(userDetails);
        ContactConfirmationResponse response = new ContactConfirmationResponse();
        response.setResult(jwtToken);
        return response;
    }

    public Object getCurrentUser() {
        BookstoreUserDetails userDetails =
                (BookstoreUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getBookstoreUser();
    }

    public void changeNewPasswordAndUserData(ChangePasswordAndUserDataForm changePasswordAndUserDataForm) {
        String userName = getUser();
        System.out.println(userName);
        BookstoreUser userCurrent = bookstoreUserRepository.findBookstoreUserByEmail(userName);

        if (userCurrent != null) {
            BookstoreUser user = new BookstoreUser();
            user.setName(userCurrent.getName());
            user.setEmail(userCurrent.getEmail());
            user.setPhone(userCurrent.getPhone());
            user.setPassword(passwordEncoder.encode(changePasswordAndUserDataForm.getPassword()));
            user.setCount(userCurrent.getCount());
            bookstoreUserRepository.save(user);
        }
    }

    public String getUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public void topUpYourAccount(CountForm countForm) {
        String userName = getUser();
        BookstoreUser userCurrent = bookstoreUserRepository.findBookstoreUserByEmail(userName);

        if (userCurrent != null) {
            Long totalCount = userCurrent.getCount() + countForm.getCount();
            BookstoreUser user = new BookstoreUser();
            user.setName(userCurrent.getName());
            user.setEmail(userCurrent.getEmail());
            user.setPhone(userCurrent.getPhone());
            user.setPassword(userCurrent.getPassword());
            user.setCount(totalCount);
            bookstoreUserRepository.save(user);
        }
    }
}
