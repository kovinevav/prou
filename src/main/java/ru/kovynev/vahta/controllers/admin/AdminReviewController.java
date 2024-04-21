package ru.kovynev.vahta.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kovynev.vahta.rep.ReviewRepository;

@Controller
public class AdminReviewController {

    final
    ReviewRepository reviewRepository;

    public AdminReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/admin/reviews")
    public String showAll(Model model) {
        model.addAttribute("reviews", reviewRepository.findAllByOrderByIdDesc());
        return "admin/reviews/all_reviews";
    }
    @GetMapping("/admin/reviews/{id}/edit")
    public  String editReview(@PathVariable("id") Long id, Model model){
        model.addAttribute("review",reviewRepository.findById(id).orElseThrow());
        return "admin/reviews/edit_review";
    }




    @DeleteMapping("admin/reviews/{id}")
    public String delete(@PathVariable("id") long id){
        reviewRepository.deleteById(id);
        return "redirect:/admin/reviews";
    }

}
