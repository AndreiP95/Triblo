package ro.andreip.triblo.generic.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ro.andreip.triblo.home.domain.RewardsRepository
import ro.andreip.triblo.home.domain.RewardsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RewardsModule {

    @Binds
    @Singleton
    abstract fun provideRewardsRepository(rewardsRepository: RewardsRepositoryImpl) : RewardsRepository

}