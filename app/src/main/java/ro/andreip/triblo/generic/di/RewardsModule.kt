package ro.andreip.triblo.generic.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ro.andreip.triblo.home.domain.ActionsRepository
import ro.andreip.triblo.home.domain.ActionsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RewardsModule {

    @Binds
    @Singleton
    abstract fun provideRewardsRepository(rewardsRepository: ActionsRepositoryImpl) : ActionsRepository

}