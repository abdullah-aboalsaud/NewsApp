package com.example.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ArticleEntityDM::class], version = 1)
@TypeConverters(Converters::class)
abstract class FavoriteDatabase: RoomDatabase() {

    abstract fun articleDao(): ArticlesDao

    companion object{
        const val DATABASE_NAME="favorite_db"
    }

}