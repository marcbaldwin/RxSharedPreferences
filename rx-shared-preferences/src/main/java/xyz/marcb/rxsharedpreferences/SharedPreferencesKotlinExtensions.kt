package xyz.marcb.rxsharedpreferences

import android.content.SharedPreferences

inline fun SharedPreferences.edit(action: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    action(editor)
    editor.apply()
}
