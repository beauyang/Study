package com.zzvcom.tests;

import com.zzvcom.dao.AuthorMapper;
import com.zzvcom.entity.Author;
import com.zzvcom.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by young on 2017/3/22.
 */
public class AuthorServiceTest {
    public static void main(String[] args) {

    }
    private static void insertUser(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        AuthorMapper authorMapper = (AuthorMapper) sqlSession.getMapper(Author.class);
    }
}
