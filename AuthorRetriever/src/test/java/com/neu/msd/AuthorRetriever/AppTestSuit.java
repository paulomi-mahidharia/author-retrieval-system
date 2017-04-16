package com.neu.msd.AuthorRetriever;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.neu.msd.AuthorRetriever.dao.TestSearchAuthorDao;
import com.neu.msd.AuthorRetriever.dao.TestSearchConferenceDao;
import com.neu.msd.AuthorRetriever.dao.TestSearchPaperDao;
import com.neu.msd.AuthorRetriever.dao.TestUserDao;
import com.neu.msd.AuthorRetriever.service.TestAuthorInfoService;
import com.neu.msd.AuthorRetriever.service.TestResultService;
import com.neu.msd.AuthorRetriever.service.TestSearchAuthorService;
import com.neu.msd.AuthorRetriever.service.TestSearchSimilarProfileService;
import com.neu.msd.AuthorRetriever.service.TestUserService;

//JUnit Suite Test
@RunWith(Suite.class)

@SuiteClasses({ 
   TestSearchAuthorDao.class, TestSearchConferenceDao.class, 
   TestSearchPaperDao.class, TestUserDao.class,
   TestAuthorInfoService.class, TestSearchAuthorService.class, 
   TestSearchSimilarProfileService.class, TestUserService.class,
   TestResultService.class
})

public class AppTestSuit {
}

