package sanji.ko.to_dolistapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoItem::class], version = 1, exportSchema = false)

abstract class AppDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
}