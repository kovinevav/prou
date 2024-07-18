package ru.kovynev.vahta.controllers;

import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import ru.kovynev.vahta.rep.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(MainController.class)
class MainControllerTest {
    @Mock
    UserRepository userRepository;


}