package com.buiducha.teamtracker.repository

import android.content.Context
import android.util.Log
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.models.User
import io.getstream.chat.android.offline.plugin.factory.StreamOfflinePluginFactory
import io.getstream.chat.android.state.plugin.config.StatePluginConfig
import io.getstream.chat.android.state.plugin.factory.StreamStatePluginFactory

class StreamRepository private constructor(context: Context){
    private val offlinePluginFactory = StreamOfflinePluginFactory(appContext = context)
    private val statePluginFactory = StreamStatePluginFactory(config = StatePluginConfig(), appContext = context)

    // 2 - Set up the client for API calls and with the plugin for offline storage
    val client = ChatClient.Builder("xfs7hhzdgxnk", context)
        .withPlugins(offlinePluginFactory, statePluginFactory)
        .logLevel(ChatLogLevel.ALL) // Set to NOTHING in prod
        .build()

    fun createChannel(
        memberList: List<String>,
        onCreateSuccess: (String) -> Unit
    ) {

        client.createChannel(
            channelId = "",
            channelType = "messaging",
            memberIds = memberList,
            extraData = mapOf(
            )
        ).enqueue { result ->
            if (result.isSuccess) {
                val value = result.getOrNull()
                value?.let {
                    onCreateSuccess(it.cid)
                }
            } else {
                Log.d(TAG, "channel create failure")
            }
        }
    }

    fun createTeamChannel(
        channelId: String,
        channelName: String,
        memberList: List<String>,
        onCreateSuccess: (String) -> Unit
    ) {
        client.createChannel(
            channelId = channelId,
            channelType = "team",
            memberIds = memberList,
            extraData = mapOf(
                "name" to channelName
            )
        ).enqueue { result ->
            if (result.isSuccess) {
                Log.d(TAG, "team channel create successfully")
                val value = result.getOrNull()
                value?.let {
                    onCreateSuccess(it.cid)
                }
            } else {
                Log.d(TAG, "team channel create failure")
            }
        }
    }

    fun initUser(
       user: User
    ) {
        Log.d(TAG, "initUser: $user")
        tokenGenerate(user.id)?.let {
            client.connectUser(
                user = user,
                token = it
            ).enqueue {result ->
                if (result.isSuccess) {
                    Log.d(TAG, "user init successfully")
                } else if (result.isFailure) {
                    Log.e(TAG, "user init failure", )
                }
            }
        }
    }

    private fun tokenGenerate(
        userId: String
    ): String? {
        val algorithm =
            Algorithm.HMAC256("8vy5k74p98xncxxghtfdsptmp958jh4kg2ug2dww26adu2rhbm84cb2ss76wacmf")

        return JWT.create()
            .withIssuer("example.com")
            .withClaim("user_id", userId)
//            .withExpiresAt(Date(System.currentTimeMillis() + 3600000)) // Token expires in 1 hour
            .sign(algorithm)
    }

    companion object {
        const val TAG = "StreamRepository"
        private var INSTANCE: StreamRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = StreamRepository(context)
            }
        }

        fun get(): StreamRepository {
            return INSTANCE ?: throw IllegalStateException("repo must be init")
        }
    }
}