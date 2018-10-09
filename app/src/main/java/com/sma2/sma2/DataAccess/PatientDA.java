package com.sma2.sma2.DataAccess;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class PatientDA {
    @Id
    private long userId;
//    @Property
//    private UUID globalId;

    @Property
    @NotNull
    private String username;
    @Property
    private Date birthday;
    @Property
    private String gender;
    @Property
    int hand;
    @Property
    private boolean smoker;
    @Property
    int educational_level;
    @Property
    private Date year_diag;
    @Property
    private String other_disorder;
    @Property
    private float weight;
    @Property
    private int height;
    @Generated(hash = 1860963864)
    public PatientDA(long userId, @NotNull String username, Date birthday, String gender,
            int hand, boolean smoker, int educational_level, Date year_diag,
            String other_disorder, float weight, int height) {
        this.userId = userId;
        this.username = username;
        this.birthday = birthday;
        this.gender = gender;
        this.hand = hand;
        this.smoker = smoker;
        this.educational_level = educational_level;
        this.year_diag = year_diag;
        this.other_disorder = other_disorder;
        this.weight = weight;
        this.height = height;
    }
    @Generated(hash = 1204534041)
    public PatientDA() {
    }
    public long getUserId() {
        return this.userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Date getBirthday() {
        return this.birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public int getHand() {
        return this.hand;
    }
    public void setHand(int hand) {
        this.hand = hand;
    }
    public boolean getSmoker() {
        return this.smoker;
    }
    public void setSmoker(boolean smoker) {
        this.smoker = smoker;
    }
    public int getEducational_level() {
        return this.educational_level;
    }
    public void setEducational_level(int educational_level) {
        this.educational_level = educational_level;
    }
    public Date getYear_diag() {
        return this.year_diag;
    }
    public void setYear_diag(Date year_diag) {
        this.year_diag = year_diag;
    }
    public String getOther_disorder() {
        return this.other_disorder;
    }
    public void setOther_disorder(String other_disorder) {
        this.other_disorder = other_disorder;
    }
    public float getWeight() {
        return this.weight;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }
    public int getHeight() {
        return this.height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

}
