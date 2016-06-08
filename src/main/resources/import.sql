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
/**
 * Author:  Joel Crosswhite <joel.crosswhite at ix.netcom.com
 * Created: Jun 7, 2016
 */

insert into AUTHORS (first_name, last_name, email, phone) values ('Joel', 'Crosswhite', 'joel.crosswhite@xwhite.us', '8005551212');
insert into AUTHORS (first_name, last_name, email, phone) values ('Chuck', 'Wagon', 'chuck.wagon@xwhite.us', '8005551212');

insert into ARTICLES (title, content, author_id) values ('Hello World!', 'Let''s see how this blog software works.', 1);
insert into ARTICLES (title, content, author_id) values ('Gettin It Started', 'Step 1: Write some words and hit enter', 1);
insert into ARTICLES (title, content, author_id) values ('How To Solve World Hunger', 'I have a perfect plan, but I don''t have space in this margin.', 1);
insert into ARTICLES (title, content, author_id) values ('What Is Right With Computers', 'Here is where everything is right', 1);
insert into ARTICLES (title, content, author_id) values ('What Is Wrong With Computers', 'Have you ever seen "The Terminator"?', 2);
insert into ARTICLES (title, content, author_id) values ('Taking The Fifth', 'Just one article too late.', 1);
insert into ARTICLES (title, content, author_id) values ('What Is the Name of the Man on First Base?', 'No, he''s on second.', 2);
insert into ARTICLES (title, content, author_id) values ('Why Did the Chicken Cross the Road?', 'To get to the other side.', 1);
insert into ARTICLES (title, content, author_id) values ('Star Wars vs. Star Trek', 'Captain Kirk beats all - need I say more?', 1);
insert into ARTICLES (title, content, author_id) values ('Survivor vs. Big Brother', 'Now that''s just getting silly.', 1);
insert into ARTICLES (title, content, author_id) values ('10 Ways to Find Out About Anything', 'Let me know which ways work for you.', 1);
insert into ARTICLES (title, content, author_id) values ('The Truth Behind Cat Videos', 'They''re just so darn cute.', 2);
insert into ARTICLES (title, content, author_id) values ('Lucky #13', 'Now I know why skyscrapers skip this floor.', 1);
insert into ARTICLES (title, content, author_id) values ('Where Is the Money In Blogging?', 'Why don''t sponsors want to pay for one sentence articles?', 1);
insert into ARTICLES (title, content, author_id) values ('Say Good Night, Charlie!', 'Good night, Charlie.', 1);

insert into TAGS (tag_name) values ('fun-post');
insert into TAGS (tag_name) values ('information');
insert into TAGS (tag_name) values ('lists');
insert into TAGS (tag_name) values ('sci-fi');
insert into TAGS (tag_name) values ('miscelaneous');

insert into ARTICLES_TAGS (article_id, tag_id) values (1, 5);
insert into ARTICLES_TAGS (article_id, tag_id) values (2, 2);
insert into ARTICLES_TAGS (article_id, tag_id) values (2, 1);
insert into ARTICLES_TAGS (article_id, tag_id) values (3, 2);
insert into ARTICLES_TAGS (article_id, tag_id) values (4, 3);
insert into ARTICLES_TAGS (article_id, tag_id) values (5, 3);
insert into ARTICLES_TAGS (article_id, tag_id) values (6, 1);
insert into ARTICLES_TAGS (article_id, tag_id) values (6, 5);
insert into ARTICLES_TAGS (article_id, tag_id) values (7, 1);
insert into ARTICLES_TAGS (article_id, tag_id) values (8, 1);
insert into ARTICLES_TAGS (article_id, tag_id) values (9, 4);
insert into ARTICLES_TAGS (article_id, tag_id) values (10, 5);
insert into ARTICLES_TAGS (article_id, tag_id) values (11, 3);
insert into ARTICLES_TAGS (article_id, tag_id) values (12, 1);
insert into ARTICLES_TAGS (article_id, tag_id) values (13, 2);
insert into ARTICLES_TAGS (article_id, tag_id) values (13, 5);
insert into ARTICLES_TAGS (article_id, tag_id) values (14, 2);