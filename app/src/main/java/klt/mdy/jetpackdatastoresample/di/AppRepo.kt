package klt.mdy.jetpackdatastoresample.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import klt.mdy.jetpackdatastoresample.constant.Constants
import klt.mdy.jetpackdatastoresample.data.pref.PrefRepository
import klt.mdy.jetpackdatastoresample.data.pref.PrefRepositoryImpl
import klt.mdy.jetpackdatastoresample.data.proto.AppSettingsSerializer
import klt.mdy.jetpackdatastoresample.data.proto.ProtoRepository
import klt.mdy.jetpackdatastoresample.data.proto.ProtoRepositoryImpl
import klt.mdy.jetpackdatastoresample.model.AppSettings
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppRepo {


    @Provides
    @Singleton
    fun providePreferencesDs(
        @ApplicationContext appContext: Context,
        @Qualifier.Io io: CoroutineDispatcher,
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { appContext.preferencesDataStoreFile(name = Constants.PREF_NAME) },
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(
                SharedPreferencesMigration(
                    context = appContext,
                    sharedPreferencesName = Constants.PREF_NAME
                )
            ),
            scope = CoroutineScope(io + SupervisorJob()),
        )
    }


    @Provides
    @Singleton
    fun provideProtoDs(
        @ApplicationContext appContext: Context,
        @Qualifier.Io io: CoroutineDispatcher,
    ): DataStore<AppSettings> {
        return DataStoreFactory.create(
            produceFile = { appContext.dataStoreFile(fileName = Constants.PROTO_NAME) },
            serializer = AppSettingsSerializer,
            corruptionHandler = null,
            scope = CoroutineScope(io + SupervisorJob()),
        )
    }


    @Provides
    @Singleton
    fun providePrefDataSource(
        ds: DataStore<Preferences>,
        @Qualifier.Io io: CoroutineDispatcher
    ): PrefRepository {
        return PrefRepositoryImpl(
            ds = ds,
            io = io
        )
    }

    @Provides
    @Singleton
    fun provideProtoDataSource(
        ds: DataStore<AppSettings>,
        @Qualifier.Io io: CoroutineDispatcher
    ): ProtoRepository {
        return ProtoRepositoryImpl(
            ds = ds,
            io = io
        )
    }

}