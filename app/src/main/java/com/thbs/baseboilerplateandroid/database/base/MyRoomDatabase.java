package com.thbs.baseboilerplateandroid.database.base;

/**
 * Created by muhammed_suhail on 3/12/2018.
 */

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;

import com.thbs.baseboilerplateandroid.modules.common.application.MyApplication;

//Below commented line denotes the tables that you require in your database. Please head to Room database website for more information
//@Database(entities = {UserProfile.class, Reminder.class, UserProfileDetails.class}, version = 1, exportSchema = false)
public abstract class MyRoomDatabase extends RoomDatabase {

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
            database.execSQL(
                    "CREATE TABLE IF NOT EXISTS reminder_table(employeeId TEXT NOT NULL, dateOfBirth TEXT, name TEXT, imageUrl TEXT, dateOfJoining TEXT, reminderTime TEXT, PRIMARY KEY(employeeId))");
        }
    };

    private static MyRoomDatabase INSTANCE;

    public static MyRoomDatabase getDatabase() {
        if (INSTANCE == null) {
            synchronized (MyRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(MyApplication.getContext(),
                            MyRoomDatabase.class, "my_room_database")
                            //.addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    //public abstract UserProfileDao userProfileDao();

    //public abstract ReminderDao reminderDao();

    //public abstract UserProfileDetailsDao userProfileDetailsDao();
}