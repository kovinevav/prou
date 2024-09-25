package ru.kovynev.vahta.services;

import jdk.jfr.Label;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.kovynev.vahta.entity.Company;
import ru.kovynev.vahta.entity.Review;
import ru.kovynev.vahta.rep.CompanyRepository;
import ru.kovynev.vahta.rep.ReviewRepository;
import ru.kovynev.vahta.rep.VacanciesRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private VacanciesRepository vacanciesRepository;
    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private CompanyService companyService;

    @Test
    @Label("getReviewByCompany")
    void getReviewsByCompany() {
        Company company = new Company();
        company.setId(1L);
        List<Review> reviewList = new ArrayList<>();
        when(reviewRepository.findByCompany(company)).thenReturn(reviewList);
        String expected = "Отзывы по данной компании отсутствуют. Ваш отзыв может стать первым.";
        assertEquals(expected, companyService.getReviewsByCompany(company).get(0).getText());
    }

    @Test
    void getVacanciesByCompany() {
    }
}