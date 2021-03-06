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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.xwhite.blog.db.ArticleRepository;
import us.xwhite.blog.domain.Article;

/**
 *
 * @author Joel Crosswhite <joel.crosswhite at ix.netcom.com
 */
@Service
public class ArticlesServiceImpl implements ArticlesService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticle(Long id) {
        return articleRepository.findOne(id);
    }

    @Override
    @Transactional
    public Article saveArticle(Article article) {
        return articleRepository.saveAndFlush(article);
    }

}
