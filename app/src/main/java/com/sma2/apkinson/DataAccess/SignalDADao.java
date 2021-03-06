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

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SIGNAL_DA".
*/
public class SignalDADao extends AbstractDao<SignalDA, Long> {

    public static final String TABLENAME = "SIGNAL_DA";

    /**
     * Properties of entity SignalDA.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property PatientDAId = new Property(1, long.class, "patientDAId", false, "PATIENT_DAID");
        public final static Property ExerciseID = new Property(2, long.class, "exerciseID", false, "EXERCISE_ID");
        public final static Property SignalPath = new Property(3, String.class, "signalPath", false, "SIGNAL_PATH");
        public final static Property ExerciseName = new Property(4, String.class, "exerciseName", false, "EXERCISE_NAME");
        public final static Property RecordingTime = new Property(5, java.util.Date.class, "recordingTime", false, "RECORDING_TIME");
        public final static Property SessionNumber = new Property(6, int.class, "sessionNumber", false, "SESSION_NUMBER");
    }

    private DaoSession daoSession;


    public SignalDADao(DaoConfig config) {
        super(config);
    }
    
    public SignalDADao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SIGNAL_DA\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"PATIENT_DAID\" INTEGER NOT NULL ," + // 1: patientDAId
                "\"EXERCISE_ID\" INTEGER NOT NULL ," + // 2: exerciseID
                "\"SIGNAL_PATH\" TEXT NOT NULL ," + // 3: signalPath
                "\"EXERCISE_NAME\" TEXT," + // 4: exerciseName
                "\"RECORDING_TIME\" INTEGER," + // 5: recordingTime
                "\"SESSION_NUMBER\" INTEGER NOT NULL );"); // 6: sessionNumber
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SIGNAL_DA\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, SignalDA entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getPatientDAId());
        stmt.bindLong(3, entity.getExerciseID());
        stmt.bindString(4, entity.getSignalPath());
 
        String exerciseName = entity.getExerciseName();
        if (exerciseName != null) {
            stmt.bindString(5, exerciseName);
        }
 
        java.util.Date recordingTime = entity.getRecordingTime();
        if (recordingTime != null) {
            stmt.bindLong(6, recordingTime.getTime());
        }
        stmt.bindLong(7, entity.getSessionNumber());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, SignalDA entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getPatientDAId());
        stmt.bindLong(3, entity.getExerciseID());
        stmt.bindString(4, entity.getSignalPath());
 
        String exerciseName = entity.getExerciseName();
        if (exerciseName != null) {
            stmt.bindString(5, exerciseName);
        }
 
        java.util.Date recordingTime = entity.getRecordingTime();
        if (recordingTime != null) {
            stmt.bindLong(6, recordingTime.getTime());
        }
        stmt.bindLong(7, entity.getSessionNumber());
    }

    @Override
    protected final void attachEntity(SignalDA entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public SignalDA readEntity(Cursor cursor, int offset) {
        SignalDA entity = new SignalDA( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // patientDAId
            cursor.getLong(offset + 2), // exerciseID
            cursor.getString(offset + 3), // signalPath
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // exerciseName
            cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)), // recordingTime
            cursor.getInt(offset + 6) // sessionNumber
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, SignalDA entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPatientDAId(cursor.getLong(offset + 1));
        entity.setExerciseID(cursor.getLong(offset + 2));
        entity.setSignalPath(cursor.getString(offset + 3));
        entity.setExerciseName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setRecordingTime(cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)));
        entity.setSessionNumber(cursor.getInt(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(SignalDA entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(SignalDA entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(SignalDA entity) {
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
            SqlUtils.appendColumns(builder, "T0", daoSession.getPatientDADao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getExerciseDao().getAllColumns());
            builder.append(" FROM SIGNAL_DA T");
            builder.append(" LEFT JOIN PATIENT_DA T0 ON T.\"PATIENT_DAID\"=T0.\"_id\"");
            builder.append(" LEFT JOIN EXERCISE T1 ON T.\"EXERCISE_ID\"=T1.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected SignalDA loadCurrentDeep(Cursor cursor, boolean lock) {
        SignalDA entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        PatientDA patient = loadCurrentOther(daoSession.getPatientDADao(), cursor, offset);
         if(patient != null) {
            entity.setPatient(patient);
        }
        offset += daoSession.getPatientDADao().getAllColumns().length;

        Exercise exercise = loadCurrentOther(daoSession.getExerciseDao(), cursor, offset);
         if(exercise != null) {
            entity.setExercise(exercise);
        }

        return entity;    
    }

    public SignalDA loadDeep(Long key) {
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
    public List<SignalDA> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<SignalDA> list = new ArrayList<SignalDA>(count);
        
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
    
    protected List<SignalDA> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<SignalDA> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
