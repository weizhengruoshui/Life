package com.yaya.life.firebase.messaging

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessageService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        com.yaya.utils.LogUtils.logD(javaClass.simpleName, token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        com.yaya.utils.LogUtils.logD(javaClass.simpleName, "From: ${message.from}")
        message.data.isEmpty().let {
            com.yaya.utils.LogUtils.logD(javaClass.simpleName, "Message data payload" + message.data)
        }
    }

    override fun onDeletedMessages() {

    }
}