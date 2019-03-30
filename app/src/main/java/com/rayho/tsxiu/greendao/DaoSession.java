package com.rayho.tsxiu.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.rayho.tsxiu.module_news.dao.Channel;
import com.rayho.tsxiu.module_news.dao.News;
import com.rayho.tsxiu.module_news.dao.NewsCache;
import com.rayho.tsxiu.module_news.dao.SearchHistory;

import com.rayho.tsxiu.greendao.ChannelDao;
import com.rayho.tsxiu.greendao.NewsDao;
import com.rayho.tsxiu.greendao.NewsCacheDao;
import com.rayho.tsxiu.greendao.SearchHistoryDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig channelDaoConfig;
    private final DaoConfig newsDaoConfig;
    private final DaoConfig newsCacheDaoConfig;
    private final DaoConfig searchHistoryDaoConfig;

    private final ChannelDao channelDao;
    private final NewsDao newsDao;
    private final NewsCacheDao newsCacheDao;
    private final SearchHistoryDao searchHistoryDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        channelDaoConfig = daoConfigMap.get(ChannelDao.class).clone();
        channelDaoConfig.initIdentityScope(type);

        newsDaoConfig = daoConfigMap.get(NewsDao.class).clone();
        newsDaoConfig.initIdentityScope(type);

        newsCacheDaoConfig = daoConfigMap.get(NewsCacheDao.class).clone();
        newsCacheDaoConfig.initIdentityScope(type);

        searchHistoryDaoConfig = daoConfigMap.get(SearchHistoryDao.class).clone();
        searchHistoryDaoConfig.initIdentityScope(type);

        channelDao = new ChannelDao(channelDaoConfig, this);
        newsDao = new NewsDao(newsDaoConfig, this);
        newsCacheDao = new NewsCacheDao(newsCacheDaoConfig, this);
        searchHistoryDao = new SearchHistoryDao(searchHistoryDaoConfig, this);

        registerDao(Channel.class, channelDao);
        registerDao(News.class, newsDao);
        registerDao(NewsCache.class, newsCacheDao);
        registerDao(SearchHistory.class, searchHistoryDao);
    }
    
    public void clear() {
        channelDaoConfig.clearIdentityScope();
        newsDaoConfig.clearIdentityScope();
        newsCacheDaoConfig.clearIdentityScope();
        searchHistoryDaoConfig.clearIdentityScope();
    }

    public ChannelDao getChannelDao() {
        return channelDao;
    }

    public NewsDao getNewsDao() {
        return newsDao;
    }

    public NewsCacheDao getNewsCacheDao() {
        return newsCacheDao;
    }

    public SearchHistoryDao getSearchHistoryDao() {
        return searchHistoryDao;
    }

}