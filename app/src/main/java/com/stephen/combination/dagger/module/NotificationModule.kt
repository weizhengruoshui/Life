package com.stephen.combination.dagger.module

import com.stephen.combination.notification.RegularNotificationFactory
import com.stephen.combination.notification.WithParentNotification
import dagger.Module
import dagger.Provides

@Module
class NotificationModule {

    @Provides
    fun provideRegularNotificationFactory(): RegularNotificationFactory {
        return RegularNotificationFactory()
    }

    @Provides
    fun provideWithParentNotificationFactory(): WithParentNotification {
        return WithParentNotification()
    }
}