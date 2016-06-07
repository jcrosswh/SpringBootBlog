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

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import us.xwhite.blog.exception.ArticleNotFoundException;

/**
 *
 * @author Joel Crosswhite <joel.crosswhite at ix.netcom.com
 */
@ControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(ArticleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody Error articleNotFoundHandler(ArticleNotFoundException e) {
        Long articleId = e.getArticleId();
        return new Error("Article [" + articleId.toString() + "] not found");
    }

    @ExceptionHandler(Exception.class)
    public Error globalHandler(Exception e) {
        return new Error(e.getMessage());
    }
}
