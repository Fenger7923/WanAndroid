@file:Suppress("UNCHECKED_CAST")

package com.example.library_base.utils

import android.content.Context
import android.util.Log
import com.tencent.mmkv.MMKV

/**
 * @author fengerzhang
 * @date 2021/8/10 12:21
 */
object MMKVHelper {
    private const val TAG = "MMKVHelper"

    fun initMMKV(context: Context) {
        val dir = MMKV.initialize(context)
        Log.e(TAG, "MMKV init root: $dir")
    }

    fun <T> setValue(key: String, value: T) {
        val kv = MMKV.defaultMMKV()
        if (key.isEmpty()) {
            Log.e(TAG, "key is empty,could not save")
        }
        when(value) {
            is String -> kv.encode("WAN_ANDROID_MMKV_$key", value)
            is Int -> kv.encode("WAN_ANDROID_MMKV_$key", value)
            is Long -> kv.encode("WAN_ANDROID_MMKV_$key", value)
            is Boolean -> kv.encode("WAN_ANDROID_MMKV_$key", value)
            is Float -> kv.encode("WAN_ANDROID_MMKV_$key", value)
            else -> Log.e(TAG, "can not save special type")
        }
    }

    @SuppressWarnings("unchecked")
    fun <T> getValue(key: String, defaultValue: T): T {
        val kv = MMKV.defaultMMKV()
        if (key.isEmpty()) {
            Log.e(TAG, "key is null,could not get value")
        }
        return when (defaultValue) {
            is String -> kv.decodeString(key, defaultValue) as T ?: defaultValue
            is Int -> kv.decodeInt(key, defaultValue) as T
            is Long -> kv.decodeLong(key, defaultValue) as T
            is Boolean -> kv.decodeBool(key, defaultValue) as T
            is Float -> kv.decodeFloat(key, defaultValue) as T
            else -> defaultValue
        }
    }
}