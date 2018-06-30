package DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Bakota on 26/06/2018.
 */

@Dao
public interface DiaryDao {

    @Query("SELECT * FROM diary ORDER BY added_date")
    List<DiaryEntry> allDiary();

    @Insert
    void addDiary(DiaryEntry diaryEntry);

    @Update(onConflict= OnConflictStrategy.REPLACE)
    void updateDiary(DiaryEntry diaryEntry);

    @Delete
    void deleteDiary(DiaryEntry diaryEntry);
}
