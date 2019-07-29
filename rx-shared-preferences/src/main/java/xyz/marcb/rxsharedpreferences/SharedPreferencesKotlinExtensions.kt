package xyz.marcb.rxsharedpreferences

import android.content.SharedPreferences

inline fun SharedPreferences.edit(action: SharedPreferences.Editor.() -> Unit) =
    edit().run {
        action(this)
        apply()
    }
