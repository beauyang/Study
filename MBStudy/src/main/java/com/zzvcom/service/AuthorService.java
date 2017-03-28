package com.zzvcom.service;

import com.zzvcom.entity.Author;

import java.util.List;

/**
 * Created by young on 2017/3/8.
 */
public interface AuthorService {

    int addAuthor(Author author);
    int deleteAuthor(int authorId);
    int updateAuthor(Author author);
    List<Author> getAllAuthors();
}
