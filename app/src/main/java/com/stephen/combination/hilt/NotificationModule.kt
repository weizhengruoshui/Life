package com.stephen.combination.hilt

import com.stephen.combination.notification.RegularNotificationFactory
import com.stephen.combination.notification.WithParentNotification
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
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