package DAO;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by Bakota on 26/06/2018.
 */

@Entity(tableName = "diary")
public class DiaryEntry {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public  String title;
    public  String Description;
    @ColumnInfo(name = "added_date")
    public Date AddedDate;


    public DiaryEntry(int id, String title, String description, Date addedDate) {
        this.id = id;
        this.title = title;
        Description = description;
        AddedDate = addedDate;
    }

    @Ignore
    public DiaryEntry(String title, String description, Date addedDate) {
        this.title = title;
        Description = description;
        AddedDate = addedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getAddedDate() {
        return AddedDate;
    }

    public void setAddedDate(Date addedDate) {
        AddedDate = addedDate;
    }
}
