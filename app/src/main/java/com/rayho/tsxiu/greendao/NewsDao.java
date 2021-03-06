package com.rayho.tsxiu.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.rayho.tsxiu.utils.StringConverter;
import java.util.List;

import com.rayho.tsxiu.module_news.dao.News;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NEWS".
*/
public class NewsDao extends AbstractDao<News, Long> {

    public static final String TABLENAME = "NEWS";

    /**
     * Properties of entity News.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Nid = new Property(1, String.class, "nid", false, "NID");
        public final static Property ViewCount = new Property(2, int.class, "viewCount", false, "VIEW_COUNT");
        public final static Property PublishDateStr = new Property(3, String.class, "publishDateStr", false, "PUBLISH_DATE_STR");
        public final static Property CatPathKey = new Property(4, String.class, "catPathKey", false, "CAT_PATH_KEY");
        public final static Property Title = new Property(5, String.class, "title", false, "TITLE");
        public final static Property PublishDate = new Property(6, long.class, "publishDate", false, "PUBLISH_DATE");
        public final static Property DislikeCount = new Property(7, int.class, "dislikeCount", false, "DISLIKE_COUNT");
        public final static Property CommentCount = new Property(8, int.class, "commentCount", false, "COMMENT_COUNT");
        public final static Property LikeCount = new Property(9, int.class, "likeCount", false, "LIKE_COUNT");
        public final static Property PosterId = new Property(10, String.class, "posterId", false, "POSTER_ID");
        public final static Property Html = new Property(11, String.class, "html", false, "HTML");
        public final static Property Url = new Property(12, String.class, "url", false, "URL");
        public final static Property CoverUrl = new Property(13, String.class, "coverUrl", false, "COVER_URL");
        public final static Property Content = new Property(14, String.class, "content", false, "CONTENT");
        public final static Property PosterScreenName = new Property(15, String.class, "posterScreenName", false, "POSTER_SCREEN_NAME");
        public final static Property ImageUrls = new Property(16, String.class, "imageUrls", false, "IMAGE_URLS");
        public final static Property Tags = new Property(17, String.class, "tags", false, "TAGS");
        public final static Property Type = new Property(18, int.class, "type", false, "TYPE");
    }

    private final StringConverter imageUrlsConverter = new StringConverter();
    private final StringConverter tagsConverter = new StringConverter();

    public NewsDao(DaoConfig config) {
        super(config);
    }
    
    public NewsDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NEWS\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NID\" TEXT NOT NULL UNIQUE ," + // 1: nid
                "\"VIEW_COUNT\" INTEGER NOT NULL ," + // 2: viewCount
                "\"PUBLISH_DATE_STR\" TEXT," + // 3: publishDateStr
                "\"CAT_PATH_KEY\" TEXT," + // 4: catPathKey
                "\"TITLE\" TEXT," + // 5: title
                "\"PUBLISH_DATE\" INTEGER NOT NULL ," + // 6: publishDate
                "\"DISLIKE_COUNT\" INTEGER NOT NULL ," + // 7: dislikeCount
                "\"COMMENT_COUNT\" INTEGER NOT NULL ," + // 8: commentCount
                "\"LIKE_COUNT\" INTEGER NOT NULL ," + // 9: likeCount
                "\"POSTER_ID\" TEXT," + // 10: posterId
                "\"HTML\" TEXT," + // 11: html
                "\"URL\" TEXT," + // 12: url
                "\"COVER_URL\" TEXT," + // 13: coverUrl
                "\"CONTENT\" TEXT," + // 14: content
                "\"POSTER_SCREEN_NAME\" TEXT," + // 15: posterScreenName
                "\"IMAGE_URLS\" TEXT," + // 16: imageUrls
                "\"TAGS\" TEXT," + // 17: tags
                "\"TYPE\" INTEGER NOT NULL );"); // 18: type
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NEWS\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, News entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getNid());
        stmt.bindLong(3, entity.getViewCount());
 
        String publishDateStr = entity.getPublishDateStr();
        if (publishDateStr != null) {
            stmt.bindString(4, publishDateStr);
        }
 
        String catPathKey = entity.getCatPathKey();
        if (catPathKey != null) {
            stmt.bindString(5, catPathKey);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(6, title);
        }
        stmt.bindLong(7, entity.getPublishDate());
        stmt.bindLong(8, entity.getDislikeCount());
        stmt.bindLong(9, entity.getCommentCount());
        stmt.bindLong(10, entity.getLikeCount());
 
        String posterId = entity.getPosterId();
        if (posterId != null) {
            stmt.bindString(11, posterId);
        }
 
        String html = entity.getHtml();
        if (html != null) {
            stmt.bindString(12, html);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(13, url);
        }
 
        String coverUrl = entity.getCoverUrl();
        if (coverUrl != null) {
            stmt.bindString(14, coverUrl);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(15, content);
        }
 
        String posterScreenName = entity.getPosterScreenName();
        if (posterScreenName != null) {
            stmt.bindString(16, posterScreenName);
        }
 
        List imageUrls = entity.getImageUrls();
        if (imageUrls != null) {
            stmt.bindString(17, imageUrlsConverter.convertToDatabaseValue(imageUrls));
        }
 
        List tags = entity.getTags();
        if (tags != null) {
            stmt.bindString(18, tagsConverter.convertToDatabaseValue(tags));
        }
        stmt.bindLong(19, entity.getType());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, News entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getNid());
        stmt.bindLong(3, entity.getViewCount());
 
        String publishDateStr = entity.getPublishDateStr();
        if (publishDateStr != null) {
            stmt.bindString(4, publishDateStr);
        }
 
        String catPathKey = entity.getCatPathKey();
        if (catPathKey != null) {
            stmt.bindString(5, catPathKey);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(6, title);
        }
        stmt.bindLong(7, entity.getPublishDate());
        stmt.bindLong(8, entity.getDislikeCount());
        stmt.bindLong(9, entity.getCommentCount());
        stmt.bindLong(10, entity.getLikeCount());
 
        String posterId = entity.getPosterId();
        if (posterId != null) {
            stmt.bindString(11, posterId);
        }
 
        String html = entity.getHtml();
        if (html != null) {
            stmt.bindString(12, html);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(13, url);
        }
 
        String coverUrl = entity.getCoverUrl();
        if (coverUrl != null) {
            stmt.bindString(14, coverUrl);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(15, content);
        }
 
        String posterScreenName = entity.getPosterScreenName();
        if (posterScreenName != null) {
            stmt.bindString(16, posterScreenName);
        }
 
        List imageUrls = entity.getImageUrls();
        if (imageUrls != null) {
            stmt.bindString(17, imageUrlsConverter.convertToDatabaseValue(imageUrls));
        }
 
        List tags = entity.getTags();
        if (tags != null) {
            stmt.bindString(18, tagsConverter.convertToDatabaseValue(tags));
        }
        stmt.bindLong(19, entity.getType());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public News readEntity(Cursor cursor, int offset) {
        News entity = new News( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // nid
            cursor.getInt(offset + 2), // viewCount
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // publishDateStr
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // catPathKey
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // title
            cursor.getLong(offset + 6), // publishDate
            cursor.getInt(offset + 7), // dislikeCount
            cursor.getInt(offset + 8), // commentCount
            cursor.getInt(offset + 9), // likeCount
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // posterId
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // html
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // url
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // coverUrl
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // content
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // posterScreenName
            cursor.isNull(offset + 16) ? null : imageUrlsConverter.convertToEntityProperty(cursor.getString(offset + 16)), // imageUrls
            cursor.isNull(offset + 17) ? null : tagsConverter.convertToEntityProperty(cursor.getString(offset + 17)), // tags
            cursor.getInt(offset + 18) // type
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, News entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNid(cursor.getString(offset + 1));
        entity.setViewCount(cursor.getInt(offset + 2));
        entity.setPublishDateStr(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCatPathKey(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setTitle(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setPublishDate(cursor.getLong(offset + 6));
        entity.setDislikeCount(cursor.getInt(offset + 7));
        entity.setCommentCount(cursor.getInt(offset + 8));
        entity.setLikeCount(cursor.getInt(offset + 9));
        entity.setPosterId(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setHtml(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setUrl(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setCoverUrl(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setContent(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setPosterScreenName(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setImageUrls(cursor.isNull(offset + 16) ? null : imageUrlsConverter.convertToEntityProperty(cursor.getString(offset + 16)));
        entity.setTags(cursor.isNull(offset + 17) ? null : tagsConverter.convertToEntityProperty(cursor.getString(offset + 17)));
        entity.setType(cursor.getInt(offset + 18));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(News entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(News entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(News entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
