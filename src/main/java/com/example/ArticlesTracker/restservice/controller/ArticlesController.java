package com.example.ArticlesTracker.restservice.controller;

import com.example.ArticlesTracker.model.Importance;
import com.example.ArticlesTracker.model.Status;
import com.example.ArticlesTracker.restservice.dto.ArticleCreationForm;
import com.example.ArticlesTracker.restservice.payload.ArticleResponse;
import com.example.ArticlesTracker.security.domain.ArticleAppUser;
import com.example.ArticlesTracker.service.article.ArticleService;
import com.example.ArticlesTracker.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * todo Document type ArticlesController
 */
@RestController
@RequestMapping("/articles")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ArticlesController {

    private ArticleService articleService;

    private UserService userService;

    @Autowired
    public ArticlesController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "3") int size ) {
        Pageable pageable = PageRequest.of(page,size);
        Map<String, Object> responseData = articleService.findPage(pageable);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addArticle(@RequestBody ArticleCreationForm creationRequest) {
        try {
            articleService.createArticle(
                creationRequest.getDescription(),
                creationRequest.getUrl(),
                creationRequest.getTheme(),
                creationRequest.getImportance(),
                creationRequest.getStatus(),
                creationRequest.getLogin()
            );
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok("Article added successfully");
    }

    @GetMapping("/myarticles")
    public List<ArticleResponse> findUserArticles(@RequestParam String login) {
        return articleService.findByUser(userService.findByLogin(login))
            .stream().map(ArticleResponse::new).toList();
    }

    @GetMapping("/myarticles/filter")
    public ResponseEntity<Map<String, Object>> findByFilters(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "3") int size,
        @RequestParam String username,
        @RequestParam String statusName,
        @RequestParam String importanceName,
        @RequestParam String theme
        ) {
        Map<String, Object> responseData;
        Pageable pageable = PageRequest.of(page, size);
        ArticleAppUser user = userService.findByLogin(username);

        try {
            responseData = articleService.findByFilters(user, statusName, importanceName, theme, pageable);
        } catch (Exception e) {
            System.out.println("Bad request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        for (Map.Entry<String, Object> entry: responseData.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
