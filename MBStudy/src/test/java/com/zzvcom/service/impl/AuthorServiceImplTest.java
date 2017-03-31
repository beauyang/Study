package com.zzvcom.service.impl;

import com.zzvcom.entity.Author;
import com.zzvcom.service.AuthorService;
import com.zzvcom.service.Impl.AuthorServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by young on 2017/3/28.
 */
public class AuthorServiceImplTest {
    private AuthorService authorService;
    @Before
    public void setUp() throws Exception{
        authorService = new AuthorServiceImpl();
    }
    @Test
    public void getAllAuthorsCount() throws Exception{
        Assert.assertEquals(true,authorService.getAllAuthorsCount()>0);
    }
    @Test
    public void testaddAuthor() throws Exception{
        Assert.assertEquals(true,authorService.addAuthor(new Author(3,"bb"))>0);
    }
    @Test
    public void testdelAuthor() throws  Exception{
        Assert.assertEquals(true,authorService.deleteAuthor(1)>0);
    }
    @Test
    public void testgetAllAuthors() throws Exception{
        Assert.assertEquals(true, !authorService.getAllAuthors().isEmpty());
    }
    @Test
    public void testgetAllAuthors2() throws Exception{
        List<Author> list =authorService.getAllAuthors();
        for (Author author : list){
            System.out.println(author.toString());
        }
    }
    @Test
    public void testinsertGenKey() throws Exception{
        authorService.insertGenKey(new Author("yang","ss","ss","",""));
    }
    @Test
    public void testinsertSen() throws Exception{
        authorService.insertGenKey(new Author("yang","ss","ss","",""));
    }
}
