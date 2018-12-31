package com.thbs.mis.baseboilerplateandroid.database.base;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

import com.thbs.mis.baseboilerplateandroid.modules.common.application.MyApplication;

//Below commented line denotes the tables that you require in your database. Please head to Room database website for more information
//@Database(entities = {UserProfile.class, Reminder.class, UserProfileDetails.class}, version = 1, exportSchema = false)
public abstract class MyTorryHarrisRoomDatabase extends RoomDatabase {

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
            database.execSQL(
                    "CREATE TABLE IF NOT EXISTS reminder_table(employeeId TEXT NOT NULL, dateOfBirth TEXT, name TEXT, imageUrl TEXT, dateOfJoining TEXT, reminderTime TEXT, PRIMARY KEY(employeeId))");
        }
    };

    private static MyTorryHarrisRoomDatabase INSTANCE;

    public static MyTorryHarrisRoomDatabase getDatabase() {
        if (INSTANCE == null) {
            synchronized (MyTorryHarrisRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(MyApplication.getContext(),
                            MyTorryHarrisRoomDatabase.class, "mytorryharris_room_database")
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