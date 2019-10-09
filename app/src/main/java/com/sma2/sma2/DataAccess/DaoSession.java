package com.sma2.sma2.DataAccess;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.sma2.sma2.DataAccess.PatientDA;
import com.sma2.sma2.DataAccess.ScheduledExercise;
import com.sma2.sma2.DataAccess.MedicineDA;
import com.sma2.sma2.DataAccess.Exercise;
import com.sma2.sma2.DataAccess.SignalDA;

import com.sma2.sma2.DataAccess.PatientDADao;
import com.sma2.sma2.DataAccess.ScheduledExerciseDao;
import com.sma2.sma2.DataAccess.MedicineDADao;
import com.sma2.sma2.DataAccess.ExerciseDao;
import com.sma2.sma2.DataAccess.SignalDADao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig patientDADaoConfig;
    private final DaoConfig scheduledExerciseDaoConfig;
    private final DaoConfig medicineDADaoConfig;
    private final DaoConfig exerciseDaoConfig;
    private final DaoConfig signalDADaoConfig;

    private final PatientDADao patientDADao;
    private final ScheduledExerciseDao scheduledExerciseDao;
    private final MedicineDADao medicineDADao;
    private final ExerciseDao exerciseDao;
    private final SignalDADao signalDADao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        patientDADaoConfig = daoConfigMap.get(PatientDADao.class).clone();
        patientDADaoConfig.initIdentityScope(type);

        scheduledExerciseDaoConfig = daoConfigMap.get(ScheduledExerciseDao.class).clone();
        scheduledExerciseDaoConfig.initIdentityScope(type);

        medicineDADaoConfig = daoConfigMap.get(MedicineDADao.class).clone();
        medicineDADaoConfig.initIdentityScope(type);

        exerciseDaoConfig = daoConfigMap.get(ExerciseDao.class).clone();
        exerciseDaoConfig.initIdentityScope(type);

        signalDADaoConfig = daoConfigMap.get(SignalDADao.class).clone();
        signalDADaoConfig.initIdentityScope(type);

        patientDADao = new PatientDADao(patientDADaoConfig, this);
        scheduledExerciseDao = new ScheduledExerciseDao(scheduledExerciseDaoConfig, this);
        medicineDADao = new MedicineDADao(medicineDADaoConfig, this);
        exerciseDao = new ExerciseDao(exerciseDaoConfig, this);
        signalDADao = new SignalDADao(signalDADaoConfig, this);

        registerDao(PatientDA.class, patientDADao);
        registerDao(ScheduledExercise.class, scheduledExerciseDao);
        registerDao(MedicineDA.class, medicineDADao);
        registerDao(Exercise.class, exerciseDao);
        registerDao(SignalDA.class, signalDADao);
    }
    
    public void clear() {
        patientDADaoConfig.clearIdentityScope();
        scheduledExerciseDaoConfig.clearIdentityScope();
        medicineDADaoConfig.clearIdentityScope();
        exerciseDaoConfig.clearIdentityScope();
        signalDADaoConfig.clearIdentityScope();
    }

    public PatientDADao getPatientDADao() {
        return patientDADao;
    }

    public ScheduledExerciseDao getScheduledExerciseDao() {
        return scheduledExerciseDao;
    }

    public MedicineDADao getMedicineDADao() {
        return medicineDADao;
    }

    public ExerciseDao getExerciseDao() {
        return exerciseDao;
    }

    public SignalDADao getSignalDADao() {
        return signalDADao;
    }

}
