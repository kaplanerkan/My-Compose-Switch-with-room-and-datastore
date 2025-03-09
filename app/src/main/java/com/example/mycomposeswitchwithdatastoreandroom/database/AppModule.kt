package com.example.mycomposeswitchwithdatastoreandroom.database


import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomDatabase.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mycomposeswitchwithdatastoreandroom.dao.SwitchDao
import com.example.mycomposeswitchwithdatastoreandroom.entity.SwitchEntity
import com.example.mycomposeswitchwithdatastoreandroom.helpers.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext

import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    /*
        Yukarıdaki kodda, Room.databaseBuilder kullanılarak veritabanı oluşturulurken addCallback metodu ile bir
        RoomDatabase.Callback ekliyoruz. onCreate metodu, veritabanı ilk kez oluşturulduğunda çalışacak ve dummy verileri ekleyecektir.

        Bu şekilde, veritabanınız ilk kez oluşturulduğunda üç SwitchEntity'yi başlangıçta eklemiş olursunuz ve viewModel içindeki
        switchStates akışı düzgün bir şekilde çalışır.

     */
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): SwitchDatabase {
        return Room.databaseBuilder(
            appContext,
            SwitchDatabase::class.java,
            "switch_database"
        ).addCallback(object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // Pre-populate the database in a background thread
                CoroutineScope(Dispatchers.IO).launch {
                    val switchDao = provideDatabase(appContext).switchDao()
                    switchDao.insertSwitch(SwitchEntity(1, ayarAdi = "Ayar 1", description = "Aciklamasi",false))
                    switchDao.insertSwitch(SwitchEntity(2, ayarAdi = "Ayar 2", description = "Aciklamasi", false))
                    switchDao.insertSwitch(SwitchEntity(3, ayarAdi = "Ayar 3", description = "Aciklamasi", false))
                }
            }
        }).build()
    }

    @Singleton
    @Provides
    fun provideSwitchDao(database: SwitchDatabase): SwitchDao {
        return database.switchDao()
    }

    private val Context.dataStore by preferencesDataStore(name = "settings")

    @Singleton
    @Provides
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }
}