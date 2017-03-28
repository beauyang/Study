package com.zzvcom.service.impl;

import com.zzvcom.entity.Author;
import com.zzvcom.service.AuthorService;
import com.zzvcom.service.Impl.AuthorServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        Assert.assertEquals(true,authorService.getAllAuthors());
    }
    @Test
    public void testaddAuthor() throws Exception{
        Assert.assertEquals(true,authorService.addAuthor(new Author(3,"bb"))>0);
    }
}
