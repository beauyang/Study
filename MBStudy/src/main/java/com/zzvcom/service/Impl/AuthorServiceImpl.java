package com.zzvcom.service.Impl;

import com.zzvcom.dao.AuthorMapper;
import com.zzvcom.entity.Author;
import com.zzvcom.service.AuthorService;
import com.zzvcom.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import sun.plugin.javascript.navig.LinkArray;

import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by young on 2017/3/8.
 */
public class AuthorServiceImpl implements AuthorService {
    /**
     *  Add author info.
     *  @param author author instance
     *  @return The key of current record in database.
     */
    public int addAuthor(Author author) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            AuthorMapper authorMapper = sqlSession.getMapper(AuthorMapper.class);
            int authorId = authorMapper.insert(author);
            sqlSession.commit();
            return authorId;
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * delete author info
     * @param authorId
     * @return The result of delete record
     */
    public int deleteAuthor(int authorId) {
        SqlSession sqlSession = null;
        sqlSession = MybatisUtil.getSqlSession();
        AuthorMapper authorMapper = sqlSession.getMapper(AuthorMapper.class);
        int result = authorMapper.deleteByPrimaryKey(authorId);
        return result;
    }

    public int updateAuthor(Author author) {
        return 0;
    }

    public List<Author> getAllAuthors() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        AuthorMapper authorMapper = sqlSession.getMapper(AuthorMapper.class);
        List<Author> list = authorMapper.getAllAuthors();
        return list;
    }

    public int getAllAuthorsCount() {
        int count = 0;
        SqlSession  sqlSession = MybatisUtil.getSqlSession();
        AuthorMapper authorMapper = sqlSession.getMapper(AuthorMapper.class);
        count = authorMapper.getAllAuthorsCount();
        return count;
    }

    public int insertGenKey(Author author) {
        SqlSession sqlSession =MybatisUtil.getSqlSession();
        AuthorMapper authorMapper = sqlSession.getMapper(AuthorMapper.class);
        int count = authorMapper.insertGenKey(author);
        return count;
    }

    public int insertSelective(Author author) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        AuthorMapper authorMapper = sqlSession.getMapper(AuthorMapper.class);
        int couunt = authorMapper.insertSelective(author);
        return 0;
    }

}
