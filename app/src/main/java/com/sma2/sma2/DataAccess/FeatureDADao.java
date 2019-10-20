package com.sma2.sma2.DataAccess;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "FEATURE_DA".
*/
public class FeatureDADao extends AbstractDao<FeatureDA, Long> {

    public static final String TABLENAME = "FEATURE_DA";

    /**
     * Properties of entity FeatureDA.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Feature_name = new Property(1, String.class, "feature_name", false, "FEATURE_NAME");
        public final static Property Feature_date = new Property(2, java.util.Date.class, "feature_date", false, "FEATURE_DATE");
        public final static Property Feature_date_str = new Property(3, String.class, "feature_date_str", false, "FEATURE_DATE_STR");
        public final static Property Feature_value = new Property(4, float.class, "feature_value", false, "FEATURE_VALUE");
    }


    public FeatureDADao(DaoConfig config) {
        super(config);
    }
    
    public FeatureDADao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"FEATURE_DA\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"FEATURE_NAME\" TEXT," + // 1: feature_name
                "\"FEATURE_DATE\" INTEGER," + // 2: feature_date
                "\"FEATURE_DATE_STR\" TEXT," + // 3: feature_date_str
                "\"FEATURE_VALUE\" REAL NOT NULL );"); // 4: feature_value
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"FEATURE_DA\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, FeatureDA entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String feature_name = entity.getFeature_name();
        if (feature_name != null) {
            stmt.bindString(2, feature_name);
        }
 
        java.util.Date feature_date = entity.getFeature_date();
        if (feature_date != null) {
            stmt.bindLong(3, feature_date.getTime());
        }
 
        String feature_date_str = entity.getFeature_date_str();
        if (feature_date_str != null) {
            stmt.bindString(4, feature_date_str);
        }
        stmt.bindDouble(5, entity.getFeature_value());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, FeatureDA entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String feature_name = entity.getFeature_name();
        if (feature_name != null) {
            stmt.bindString(2, feature_name);
        }
 
        java.util.Date feature_date = entity.getFeature_date();
        if (feature_date != null) {
            stmt.bindLong(3, feature_date.getTime());
        }
 
        String feature_date_str = entity.getFeature_date_str();
        if (feature_date_str != null) {
            stmt.bindString(4, feature_date_str);
        }
        stmt.bindDouble(5, entity.getFeature_value());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public FeatureDA readEntity(Cursor cursor, int offset) {
        FeatureDA entity = new FeatureDA( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // feature_name
            cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)), // feature_date
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // feature_date_str
            cursor.getFloat(offset + 4) // feature_value
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, FeatureDA entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setFeature_name(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setFeature_date(cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)));
        entity.setFeature_date_str(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setFeature_value(cursor.getFloat(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(FeatureDA entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(FeatureDA entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(FeatureDA entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
