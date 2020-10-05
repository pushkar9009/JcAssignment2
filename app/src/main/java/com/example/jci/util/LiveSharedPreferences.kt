package com.example.jci.util

import android.content.SharedPreferences
import io.reactivex.subjects.PublishSubject

class LiveSharedPreferences constructor(private val _preferences: SharedPreferences) {

    private val updates = PublishSubject.create<String>()

    private val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        updates.onNext(key)
    }

    val preferences: SharedPreferences
        get() = _preferences

    init {
        _preferences.registerOnSharedPreferenceChangeListener(listener)
    }

    fun getString(key: String, defaultValue: String?) =
        LivePreference(updates, _preferences, key, defaultValue)

    fun getInt(key: String, defaultValue: Int?) =
        LivePreference(updates, _preferences, key, defaultValue)

    fun getBoolean(key: String, defaultValue: Boolean?) =
        LivePreference(updates, _preferences, key, defaultValue)

    fun getFloat(key: String, defaultValue: Float?) =
        LivePreference(updates, _preferences, key, defaultValue)

    fun getLong(key: String, defaultValue: Long?) =
        LivePreference(updates, _preferences, key, defaultValue)


}