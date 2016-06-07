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

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.MediaType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import us.xwhite.blog.domain.Article;
import us.xwhite.blog.domain.Author;
import us.xwhite.blog.domain.Tag;
import us.xwhite.blog.exception.ArticleNotFoundException;
import us.xwhite.blog.service.ArticlesService;
import us.xwhite.test.TestUtil;

/**
 *
 * @author Joel Crosswhite <joel.crosswhite at ix.netcom.com
 */
public class ArticlesControllerTest {

    private ArticlesController controller;
    private MockMvc mockMvc;

    @Before
    public void setup() {

        ArticlesService mockService = Mockito.mock(ArticlesService.class);

        this.controller = new ArticlesController();
        this.controller.setArticlesService(mockService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new AppWideExceptionHandler()).build();
    }

    @Test
    public void getAllArticles() throws Exception {

        List<Article> expectedList = new ArrayList<>();
        Author author = new Author();
        author.setFirstName("Joel");
        author.setLastName("Crosswhite");
        author.setEmail("joel.crosswhite@xwhite.us");
        author.setPhone("8005551212");
        Article article1 = new Article();
        article1.setAuthor(author);
        article1.setTitle("Brand New Article");
        article1.setContent("Writing it up!!");
        Article article2 = new Article();
        article2.setAuthor(author);
        article2.setTitle("Second Article");
        article2.setContent("Here's what's going on.");
        expectedList.add(article1);
        expectedList.add(article2);
        Mockito.when(controller.getArticlesService().getArticles()).thenReturn(expectedList);

        mockMvc.perform(MockMvcRequestBuilders.get("/articles").accept(MediaType.APPLICATION_JSON))
                // .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("Brand New Article")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title", Matchers.is("Second Article")));
    }

    @Test
    public void getOneArticle_SuccessfulFind() throws Exception {

        Article expectedArticle = new Article();
        expectedArticle.setTitle("Brand New Article");
        expectedArticle.setContent("Writing it up!!");
        Mockito.when(controller.getArticlesService().getArticle(17L)).thenReturn(expectedArticle);

        mockMvc.perform(MockMvcRequestBuilders.get("/articles/17").accept(MediaType.APPLICATION_JSON))
                // .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Brand New Article")));
    }

    @Test
    public void getOneArticle_NoFind() throws Exception {

        Mockito.when(controller.getArticlesService().getArticle(17L)).thenThrow(new ArticleNotFoundException(17L));

        mockMvc.perform(MockMvcRequestBuilders.get("/articles/17").accept(MediaType.APPLICATION_JSON))
                // .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Article [17] not found")));
    }

    @Test
    public void saveArticle() throws Exception {
        Tag newTag = new Tag();
        newTag.setName("brand-new-article");
        Author author = new Author();
        author.setFirstName("Joel");
        author.setLastName("Crosswhite");
        author.setEmail("joel.crosswhite@xwhite.us");
        author.setPhone("8005551212");
        Article article = new Article();
        article.setTitle("Brand New Article");
        article.setContent("Writing it up!!");
        article.setAuthor(author);
        article.addTag(newTag);
        Article expectedArticle = new Article();
        expectedArticle.setId(1L);
        expectedArticle.setTitle("Brand New Article");
        expectedArticle.setContent("Writing it up!!");
        expectedArticle.setAuthor(author);
        expectedArticle.addTag(newTag);
        Mockito.when(controller.getArticlesService().saveArticle(article)).thenReturn(expectedArticle);

        mockMvc.perform(MockMvcRequestBuilders.post("/articles").contentType(MediaType.APPLICATION_JSON).content(TestUtil.objectToJson(article))
        )
                // .andDo(MockMvcResultHandlers.print())
                // not sure if having 'localhost' makes this fragile or not - leaving it in for now
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/articles/1"));

        Mockito.verify(controller.getArticlesService(), Mockito.atLeastOnce()).saveArticle(article);
    }

    @Test
    public void badURI() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/just_not_here").accept(MediaType.APPLICATION_JSON))
                // .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
