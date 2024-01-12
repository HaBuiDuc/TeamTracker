package com.buiducha.teamtracker.utils

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject


class FcmNotificationsSender(
    var userFcmToken: String,
    var title: String,
    var body: String,
    var context: Context
) {
    private var requestQueue: RequestQueue? = null
    private val postUrl = "https://fcm.googleapis.com/fcm/send"


    fun SendNotifications() {
        requestQueue = Volley.newRequestQueue(context)
        val mainObj = JSONObject()
        try {
            mainObj.put("to", userFcmToken)
            val notiObject = JSONObject()
            notiObject.put("title", title)
            notiObject.put("body", body)
            notiObject.put("icon", "icon") // enter icon that exists in drawable only
            mainObj.put("notification", notiObject)
            val request: JsonObjectRequest = object : JsonObjectRequest(
                Method.POST,
                postUrl,
                mainObj,
                Response.Listener<JSONObject?> {
                    // code run is got response
                },
                Response.ErrorListener {
                    // code run is got error
                }) {
            }
            requestQueue!!.add(request)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}