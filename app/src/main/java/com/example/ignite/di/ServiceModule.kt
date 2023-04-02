package com.example.ignite.di

import com.example.ignite.models.service.AccountService
import com.example.ignite.models.service.LogService
import com.example.ignite.models.service.impl.AccountServiceImpl
import com.example.ignite.models.service.impl.LogServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {

    @Binds abstract fun provideAccountService(impl: AccountServiceImpl): AccountService
    @Binds abstract fun provideLogService(impl: LogServiceImpl): LogService
}