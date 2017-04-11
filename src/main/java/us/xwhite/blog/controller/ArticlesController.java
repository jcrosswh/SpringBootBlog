/*
 * Copyright 2016 Crosswhite Consulting, Inc..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package us.xwhite.blog.controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import us.xwhite.blog.domain.Article;
import us.xwhite.blog.service.ArticlesService;

/**
 *
 * @author Joel Crosswhite <joel.crosswhite at ix.netcom.com
 */
@RestController
@RequestMapping("/")
public class ArticlesController {

    @Autowired
    ArticlesService articlesService;

    @RequestMapping(value = {"/", "/articles"}, method = RequestMethod.GET)
    public List<Article> getAllArticles() {
        return articlesService.getArticles();
    }

    @RequestMapping(value = "/articles/{id}", method = RequestMethod.GET)
    public Article getOneArticle(@PathVariable("id") Long id) {
        return articlesService.getArticle(id);
    }

    @RequestMapping(value = "/articles", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Article> saveArticle(@RequestBody Article article, UriComponentsBuilder ucb) {

        Article savedArticle = articlesService.saveArticle(article);
        HttpHeaders headers = new HttpHeaders();
        URI locationUri
                = ucb.path("/articles/")
                .path(String.valueOf(savedArticle.getId()))
                .build()
                .toUri();
        headers.setLocation(locationUri);
        ResponseEntity<Article> responseEntity
                = new ResponseEntity<>(
                        savedArticle, headers, HttpStatus.CREATED);
        return responseEntity;
    }

    public ArticlesService getArticlesService() {
        return articlesService;
    }

    public void setArticlesService(ArticlesService articlesService) {
        this.articlesService = articlesService;
    }

}
