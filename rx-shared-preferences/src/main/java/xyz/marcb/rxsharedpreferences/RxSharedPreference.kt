package xyz.marcb.rxsharedpreferences

import android.content.SharedPreferences
import io.reactivex.Observable

open class RxSharedPreference<T>(
    private val sharedPreferences: SharedPreferences,
    private val key: String,
    private val getter: SharedPreferences.() -> T,
    private val setter: SharedPreferences.Editor.(T) -> Unit
) : Preference<T> {

    override var value: T
        get() = getter(sharedPreferences)
        set(value) = sharedPreferences.edit { setter(value) }

    override val isSet: Boolean
        get() = sharedPreferences.contains(key)

    override fun asObservable(): Observable<T> = sharedPreferences.observe(key).map { value }

    override fun clear() = sharedPreferences.edit { remove(key) }
}
