package ro.andreip.triblo.generic.di

import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ro.andreip.triblo.home.data.localSource.LocalDataSource
import ro.andreip.triblo.home.data.localSource.LocalDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(@ApplicationContext context: Context): LocalDataSource = LocalDataSourceImpl(context)

    @Provides
    @Singleton
    fun provideMoshi() = Moshi
        .Builder()
        .build()
}