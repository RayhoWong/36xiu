package com.rayho.tsxiu.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.rayho.tsxiu.module_mine.dao.User;
import com.rayho.tsxiu.module_news.dao.Channel;
import com.rayho.tsxiu.module_news.dao.News;
import com.rayho.tsxiu.module_news.dao.NewsCache;
import com.rayho.tsxiu.module_news.dao.SearchHistory;
import com.rayho.tsxiu.module_photo.dao.Image;
import com.rayho.tsxiu.module_video.dao.VideoAutoPlay;

import com.rayho.tsxiu.greendao.UserDao;
import com.rayho.tsxiu.greendao.ChannelDao;
import com.rayho.tsxiu.greendao.NewsDao;
import com.rayho.tsxiu.greendao.NewsCacheDao;
import com.rayho.tsxiu.greendao.SearchHistoryDao;
import com.rayho.tsxiu.greendao.ImageDao;
import com.rayho.tsxiu.greendao.VideoAutoPlayDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userDaoConfig;
    private final DaoConfig channelDaoConfig;
    private final DaoConfig newsDaoConfig;
    private final DaoConfig newsCacheDaoConfig;
    private final DaoConfig searchHistoryDaoConfig;
    private final DaoConfig imageDaoConfig;
    private final DaoConfig videoAutoPlayDaoConfig;

    private final UserDao userDao;
    private final ChannelDao channelDao;
    private final NewsDao newsDao;
    private final NewsCacheDao newsCacheDao;
    private final SearchHistoryDao searchHistoryDao;
    private final ImageDao imageDao;
    private final VideoAutoPlayDao videoAutoPlayDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        channelDaoConfig = daoConfigMap.get(ChannelDao.class).clone();
        channelDaoConfig.initIdentityScope(type);

        newsDaoConfig = daoConfigMap.get(NewsDao.class).clone();
        newsDaoConfig.initIdentityScope(type);

        newsCacheDaoConfig = daoConfigMap.get(NewsCacheDao.class).clone();
        newsCacheDaoConfig.initIdentityScope(type);

        searchHistoryDaoConfig = daoConfigMap.get(SearchHistoryDao.class).clone();
        searchHistoryDaoConfig.initIdentityScope(type);

        imageDaoConfig = daoConfigMap.get(ImageDao.class).clone();
        imageDaoConfig.initIdentityScope(type);

        videoAutoPlayDaoConfig = daoConfigMap.get(VideoAutoPlayDao.class).clone();
        videoAutoPlayDaoConfig.initIdentityScope(type);

        userDao = new UserDao(userDaoConfig, this);
        channelDao = new ChannelDao(channelDaoConfig, this);
        newsDao = new NewsDao(newsDaoConfig, this);
        newsCacheDao = new NewsCacheDao(newsCacheDaoConfig, this);
        searchHistoryDao = new SearchHistoryDao(searchHistoryDaoConfig, this);
        imageDao = new ImageDao(imageDaoConfig, this);
        videoAutoPlayDao = new VideoAutoPlayDao(videoAutoPlayDaoConfig, this);

        registerDao(User.class, userDao);
        registerDao(Channel.class, channelDao);
        registerDao(News.class, newsDao);
        registerDao(NewsCache.class, newsCacheDao);
        registerDao(SearchHistory.class, searchHistoryDao);
        registerDao(Image.class, imageDao);
        registerDao(VideoAutoPlay.class, videoAutoPlayDao);
    }
    
    public void clear() {
        userDaoConfig.clearIdentityScope();
        channelDaoConfig.clearIdentityScope();
        newsDaoConfig.clearIdentityScope();
        newsCacheDaoConfig.clearIdentityScope();
        searchHistoryDaoConfig.clearIdentityScope();
        imageDaoConfig.clearIdentityScope();
        videoAutoPlayDaoConfig.clearIdentityScope();
    }

    public UserDao getUserDao() {
        return userDao;
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

    public ImageDao getImageDao() {
        return imageDao;
    }

    public VideoAutoPlayDao getVideoAutoPlayDao() {
        return videoAutoPlayDao;
    }

}
