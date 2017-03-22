package com.zzvcom.dao;

import com.zzvcom.entity.Author;

import java.util.List;

/**
 * Created by young on 2017/3/8.
 */
public interface AuthorMapper {
    int addAuthor(Author author);
    int deleteAuthor(int id);
    int updateAuthor(Author author);
    List<Author> getAllAuthors();
    int getAllAuthorsCount();
}
