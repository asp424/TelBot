package com.lm.bot.di.dagger.modules

import com.lm.bot.notification.NotificationProvider
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface NotificationProviderModule {

    @Binds
    @Singleton
    fun bindsNotificationProvider(notificationProvider: NotificationProvider.Base): NotificationProvider

}