package com.example.MyBookShopApp.security;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookstoreUserRegisterTests {

    private final BookstoreUserRegister userRegister;
    private final PasswordEncoder passwordEncoder;
    private RegistrationForm registrationForm;
    private AuthenticationManager authenticate;
    private ContactConfirmationPayload payload;

    @MockBean
    private BookstoreUserRepository bookstoreUserRepositoryMock;

    @Autowired
    public BookstoreUserRegisterTests(BookstoreUserRegister userRegister, PasswordEncoder passwordEncoder, AuthenticationManager authenticate) {
        this.userRegister = userRegister;
        this.passwordEncoder = passwordEncoder;
        this.authenticate = authenticate;
    }

    @BeforeEach
    void setUp() {
        registrationForm = new RegistrationForm();
        registrationForm.setEmail("test@mail.org");
        registrationForm.setName("Tester");
        registrationForm.setPass("iddqd");
        registrationForm.setPhone("9031232323");

        payload = new ContactConfirmationPayload();
        payload.setContact("+7 (912) 430-24-02");
        payload.setCode("123456");
    }

    @AfterEach
    void tearDown() {
        registrationForm = null;
        payload = null;
    }

    @Test
    void registerNewUser() {
        BookstoreUser user = userRegister.registerNewUser(registrationForm);
        assertNotNull(user);
        assertTrue(passwordEncoder.matches(registrationForm.getPass(), user.getPassword()));
        assertTrue(CoreMatchers.is(user.getName()).matches(registrationForm.getName()));
        assertTrue(CoreMatchers.is(user.getEmail()).matches(registrationForm.getEmail()));

        Mockito.verify(bookstoreUserRepositoryMock, Mockito.times(1))
                .save(Mockito.any(BookstoreUser.class));
    }

    @Test
    void registerNewUserFail(){
        Mockito.doReturn(new BookstoreUser())
                .when(bookstoreUserRepositoryMock)
                .findBookstoreUserByEmail(registrationForm.getEmail());

        BookstoreUser user = userRegister.registerNewUser(registrationForm);
        assertNull(user);
    }

    @Test
    void jwtLoginTest() {
        Mockito.doReturn(new BookstoreUser())
                .when(bookstoreUserRepositoryMock)
                .findBookstoreUserByEmail("sazh@gmail.com");

        ContactConfirmationResponse contactConfirmationResponse = userRegister.jwtLogin(payload);
        assertNotNull(contactConfirmationResponse);
    }

    @Test
    void logoutTest() {
        Mockito.doReturn(new BookstoreUser())
                .when(bookstoreUserRepositoryMock)
                .findBookstoreUserByEmail(registrationForm.getEmail());

        Authentication authentication =
                authenticate.authenticate(new UsernamePasswordAuthenticationToken(payload.getContact(),
                        payload.getCode()));

        assertNull(authentication);
    }
}