package com.sma2.apkinson.DataAccess;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import android.net.Uri;
import com.sma2.apkinson.DataAccess.Exercise.UriConverter;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SCHEDULED_EXERCISE".
*/
public class ScheduledExerciseDao extends AbstractDao<ScheduledExercise, Long> {

    public static final String TABLENAME = "SCHEDULED_EXERCISE";

    /**
     * Properties of entity ScheduledExercise.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ExerciseId = new Property(1, long.class, "exerciseId", false, "EXERCISE_ID");
        public final static Property SessionId = new Property(2, Integer.class, "sessionId", false, "SESSION_ID");
        public final static Property CompletionDate = new Property(3, long.class, "completionDate", false, "COMPLETION_DATE");
        public final static Property ResultPath = new Property(4, String.class, "resultPath", false, "RESULT_PATH");
    }

    private DaoSession daoSession;

    private final UriConverter resultPathConverter = new UriConverter();

    public ScheduledExerciseDao(DaoConfig config) {
        super(config);
    }
    
    public ScheduledExerciseDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SCHEDULED_EXERCISE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"EXERCISE_ID\" INTEGER NOT NULL ," + // 1: exerciseId
                "\"SESSION_ID\" INTEGER NOT NULL ," + // 2: sessionId
                "\"COMPLETION_DATE\" INTEGER NOT NULL ," + // 3: completionDate
                "\"RESULT_PATH\" TEXT);"); // 4: resultPath
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SCHEDULED_EXERCISE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ScheduledExercise entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getExerciseId());
        stmt.bindLong(3, entity.getSessionId());
        stmt.bindLong(4, entity.getCompletionDate());
 
        Uri resultPath = entity.getResultPath();
        if (resultPath != null) {
            stmt.bindString(5, resultPathConverter.convertToDatabaseValue(resultPath));
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ScheduledExercise entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getExerciseId());
        stmt.bindLong(3, entity.getSessionId());
        stmt.bindLong(4, entity.getCompletionDate());
 
        Uri resultPath = entity.getResultPath();
        if (resultPath != null) {
            stmt.bindString(5, resultPathConverter.convertToDatabaseValue(resultPath));
        }
    }

    @Override
    protected final void attachEntity(ScheduledExercise entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ScheduledExercise readEntity(Cursor cursor, int offset) {
        ScheduledExercise entity = new ScheduledExercise( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // exerciseId
            cursor.getInt(offset + 2), // sessionId
            cursor.getLong(offset + 3), // completionDate
            cursor.isNull(offset + 4) ? null : resultPathConverter.convertToEntityProperty(cursor.getString(offset + 4)) // resultPath
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ScheduledExercise entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setExerciseId(cursor.getLong(offset + 1));
        entity.setSessionId(cursor.getInt(offset + 2));
        entity.setCompletionDate(cursor.getLong(offset + 3));
        entity.setResultPath(cursor.isNull(offset + 4) ? null : resultPathConverter.convertToEntityProperty(cursor.getString(offset + 4)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ScheduledExercise entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ScheduledExercise entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ScheduledExercise entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getExerciseDao().getAllColumns());
            builder.append(" FROM SCHEDULED_EXERCISE T");
            builder.append(" LEFT JOIN EXERCISE T0 ON T.\"EXERCISE_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected ScheduledExercise loadCurrentDeep(Cursor cursor, boolean lock) {
        ScheduledExercise entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Exercise exercise = loadCurrentOther(daoSession.getExerciseDao(), cursor, offset);
         if(exercise != null) {
            entity.setExercise(exercise);
        }

        return entity;    
    }

    public ScheduledExercise loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<ScheduledExercise> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<ScheduledExercise> list = new ArrayList<ScheduledExercise>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<ScheduledExercise> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<ScheduledExercise> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
