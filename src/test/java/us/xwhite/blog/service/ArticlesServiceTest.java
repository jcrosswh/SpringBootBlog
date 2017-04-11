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
package us.xwhite.blog.service;

import java.util.List;
import javax.transaction.Transactional;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import us.xwhite.blog.domain.Article;
import us.xwhite.blog.domain.Author;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Joel Crosswhite <joel.crosswhite at ix.netcom.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ActiveProfiles("test")
public class ArticlesServiceTest {

    @Autowired
    ArticlesService articlesService;

    @Test
    @Transactional
    public void findOne() {
        Article thirteen = articlesService.getArticle(13L);
        assertEquals(13, (long) thirteen.getId());
        assertEquals("Lucky #13", thirteen.getTitle());
        assertEquals("Now I know why skyscrapers skip this floor.", thirteen.getContent());
        assertEquals(1, (long) thirteen.getAuthor().getId());
        assertEquals("Joel", thirteen.getAuthor().getFirstName());
        assertEquals("Crosswhite", thirteen.getAuthor().getLastName());
        assertEquals("joel.crosswhite@xwhite.us", thirteen.getAuthor().getEmail());
        assertEquals("8005551212", thirteen.getAuthor().getPhone());
        assertEquals(2, thirteen.getTags().size());
        assertEquals("miscelaneous", thirteen.getTags().get(1).getName());
    }

    @Test
    @Transactional
    public void findAll() {
        List<Article> allArticles = articlesService.getArticles();
        assertEquals(15, allArticles.size());
        for (int i = 0; i < allArticles.size(); i++) {
            assertEquals((long) (i + 1), (long) allArticles.get(i).getId());
        }
    }
    
    @Test(expected = AccessDeniedException.class)
    @Transactional
    @WithMockUser(username = "jcrosswh")
    public void save_unauthorized() {
        Author author = articlesService.getArticle(13L).getAuthor();
        Article article = new Article(); //"What I Learned in Java Today", "Java so rocks!", author, null);
        article.setTitle("What I Learned in Java Today");
        article.setContent("Java so rocks!");
        article.setAuthor(author);
        articlesService.saveArticle(article);
    }

    @Test
    @Transactional
    @WithMockUser(username = "jcrosswh", roles = { "author" })
    public void save() {
        Author author = articlesService.getArticle(13L).getAuthor();
        Article article = new Article(); //"What I Learned in Java Today", "Java so rocks!", author, null);
        article.setTitle("What I Learned in Java Today");
        article.setContent("Java so rocks!");
        article.setAuthor(author);
        Article saved = articlesService.saveArticle(article);
        assertNewArticle(saved);
        assertNewArticle(articlesService.getArticle(16L));
    }

    private void assertNewArticle(Article article) {
        assertEquals(16, (long) article.getId());
    }
}
