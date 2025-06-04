package ru.itis.homework7

import com.google.firebase.crashlytics.FirebaseCrashlytics
import ru.itis.homework7.data.Crashlytics
import javax.inject.Inject

class CrashlyticsImpl @Inject constructor() : Crashlytics {
    override fun setCustomKeyToCrashlytics(key: String, value: String) {
        val crashlytics = FirebaseCrashlytics.getInstance()
        crashlytics.setCustomKey(key, value)
    }
}