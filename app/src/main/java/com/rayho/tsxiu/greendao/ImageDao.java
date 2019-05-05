package com.rayho.tsxiu.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.rayho.tsxiu.module_photo.dao.Image;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "IMAGE".
*/
public class ImageDao extends AbstractDao<Image, Long> {

    public static final String TABLENAME = "IMAGE";

    /**
     * Properties of entity Image.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ImageId = new Property(1, String.class, "imageId", false, "IMAGE_ID");
        public final static Property Url = new Property(2, String.class, "url", false, "URL");
        public final static Property Date = new Property(3, String.class, "date", false, "DATE");
        public final static Property Title = new Property(4, String.class, "title", false, "TITLE");
        public final static Property Favorites = new Property(5, String.class, "favorites", false, "FAVORITES");
        public final static Property Comments = new Property(6, String.class, "comments", false, "COMMENTS");
    }


    public ImageDao(DaoConfig config) {
        super(config);
    }
    
    public ImageDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"IMAGE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"IMAGE_ID\" TEXT NOT NULL UNIQUE ," + // 1: imageId
                "\"URL\" TEXT NOT NULL ," + // 2: url
                "\"DATE\" TEXT," + // 3: date
                "\"TITLE\" TEXT," + // 4: title
                "\"FAVORITES\" TEXT," + // 5: favorites
                "\"COMMENTS\" TEXT);"); // 6: comments
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"IMAGE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Image entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getImageId());
        stmt.bindString(3, entity.getUrl());
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(4, date);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(5, title);
        }
 
        String favorites = entity.getFavorites();
        if (favorites != null) {
            stmt.bindString(6, favorites);
        }
 
        String comments = entity.getComments();
        if (comments != null) {
            stmt.bindString(7, comments);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Image entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getImageId());
        stmt.bindString(3, entity.getUrl());
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(4, date);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(5, title);
        }
 
        String favorites = entity.getFavorites();
        if (favorites != null) {
            stmt.bindString(6, favorites);
        }
 
        String comments = entity.getComments();
        if (comments != null) {
            stmt.bindString(7, comments);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Image readEntity(Cursor cursor, int offset) {
        Image entity = new Image( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // imageId
            cursor.getString(offset + 2), // url
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // date
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // title
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // favorites
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // comments
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Image entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setImageId(cursor.getString(offset + 1));
        entity.setUrl(cursor.getString(offset + 2));
        entity.setDate(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTitle(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setFavorites(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setComments(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Image entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Image entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Image entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
