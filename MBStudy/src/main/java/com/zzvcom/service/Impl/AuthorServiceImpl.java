package com.zzvcom.service.Impl;

import com.zzvcom.dao.AuthorMapper;
import com.zzvcom.entity.Author;
import com.zzvcom.service.AuthorService;
import com.zzvcom.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

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
            int authorId = authorMapper.addAuthor(author);
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
        int result = authorMapper.deleteAuthor(authorId);
        return result;
    }

    public int updateAuthor(Author author) {
        return 0;
    }

    public List<Author> getAllAuthors() {
        return null;
    }
}
