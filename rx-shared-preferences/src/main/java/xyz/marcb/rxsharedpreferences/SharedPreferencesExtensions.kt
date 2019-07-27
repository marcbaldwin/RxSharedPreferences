package xyz.marcb.rxsharedpreferences

import android.content.SharedPreferences

fun SharedPreferences.string(key: String, defaultValue: String): Preference<String> =
    RxSharedPreference(this, key,
        { getString(key, defaultValue) ?: defaultValue },
        { value -> putString(key, value) }
    )

fun SharedPreferences.optionalString(key: String, defaultValue: String? = null): Preference<String?> =
    RxSharedPreference(this, key,
        { if (contains(key)) getString(key, defaultValue) else defaultValue },
        { value -> if (value != null) putString(key, value) else remove(key) }
    )

fun SharedPreferences.boolean(key: String, defaultValue: Boolean = false): Preference<Boolean> =
    RxSharedPreference(this, key,
        { getBoolean(key, defaultValue) },
        { value -> putBoolean(key, value) }
    )

fun SharedPreferences.optionalInt(key: String, defaultValue: Int? = null): Preference<Int?> =
    RxSharedPreference(this, key,
        { if (contains(key)) getInt(key, 0) else defaultValue },
        { value -> if (value != null) putInt(key, value) else remove(key) }
    )

fun SharedPreferences.float(key: String, defaultValue: Float = 0f): Preference<Float> =
    RxSharedPreference(this, key,
        { if (contains(key)) getFloat(key, defaultValue) else defaultValue },
        { value -> putFloat(key, value) }
    )

fun <T : Enum<T>> SharedPreferences.enum(key: String, defaultValue: T, converter: (String) -> T): Preference<T> =
    RxSharedPreference(this, key,
        { converter(getString(key, defaultValue.name) ?: defaultValue.name) },
        { value -> edit { putString(key, value.name) } }
    )

fun <T> SharedPreferences.custom(key: String, serializer: (T) -> String, deserializer: (String) -> T): Preference<T?> =
    RxSharedPreference(this, key,
        { if (contains(key)) deserializer(getString(key, null)!!) else null },
        { value -> if (value != null) putString(key, serializer(value)) else remove(key) }
    )
