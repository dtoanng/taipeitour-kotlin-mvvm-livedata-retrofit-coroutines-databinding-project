package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.datasource.local

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceDataStoreConstants {
    val COUNTRY_KEY = stringPreferencesKey("COUNTRY_KEY")

    /** Sample:
    val IS_MINOR_KEY = booleanPreferencesKey("IS_MINOR_KEY")
    val COUNTRY_KEY = intPreferencesKey("AGE_KEY")
    val MOBILE_NUMBER = longPreferencesKey("MOBILE_NUMBER")
    */
}