package com.zzvcom.dao;

import com.zzvcom.entity.Author;

import java.util.List;

public interface AuthorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Author record);

    int insertSelective(Author record);

    Author selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Author record);

    int updateByPrimaryKey(Author record);

    List<Author> getAllAuthors();

    int getAllAuthorsCount();
    int insertGenKey(Author author);
}