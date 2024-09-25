package ru.kovynev.vahta.services;

import jdk.jfr.Label;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.kovynev.vahta.entity.Company;
import ru.kovynev.vahta.entity.Review;
import ru.kovynev.vahta.entity.Speciality;
import ru.kovynev.vahta.entity.Vacancy;
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
    @Mock
    private Company company;

 

    @InjectMocks
    private CompanyService companyService;

    @Test
    @Label("getReviewByCompany")
    void getReviewsByCompany() {
        company.setId(1L);
        List<Review> reviewList = new ArrayList<>();
        when(reviewRepository.findByCompany(company)).thenReturn(reviewList);
        String expected = "Отзывы по данной компании отсутствуют. Ваш отзыв может стать первым.";
        Assertions.assertEquals(expected, companyService.getReviewsByCompany(company).get(0).getText());
    }

    @Test
    void getVacanciesByCompany() {
        List<Vacancy> vacancyList = new ArrayList<>();
        when(vacanciesRepository.findByCompany(company)).thenReturn(vacancyList);
        String expected = "Вакансии по данной компании отсутствуют.";

        Assertions.assertEquals(expected, companyService.getVacanciesByCompany(company).get(0).getSpeciality().getName());

        Speciality speciality = Speciality.build()
                .id(1L)
                .name("TaxiDriver")
                .build();


        Vacancy vacancy = Vacancy.builder()
                .speciality(speciality)
                .build();

        vacancyList.add(vacancy);

        expected = "TaxiDriver";
        assertEquals(expected, companyService.getVacanciesByCompany(company).get(1).getSpeciality().getName());
    }

    @Test
    void getAllCompaniesTest(){
        List<Company> companyList = new ArrayList<>();
        Company company = new Company();
        company.setName("ddt");
        companyList.add(company);
        when(companyRepository.findAll()).thenReturn(companyList);
        Assertions.assertEquals("ddt", companyRepository.findAll().get(0).getName());
    }
}