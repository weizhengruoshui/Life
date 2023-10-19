package com.yaya.life.hilt

import com.yaya.life.notification.RegularNotificationFactory
import com.yaya.life.notification.WithParentNotification
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