package xyz.marcb.rxsharedpreferences

import android.content.SharedPreferences
import io.reactivex.Observable

//
// Emits the key when there is a change to the preference
//
fun SharedPreferences.observe(key: String): Observable<String> =
    Observable.create { subscriber ->

        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, changedKey ->
            if (key == changedKey) {
                subscriber.onNext(changedKey)
            }
        }

        registerOnSharedPreferenceChangeListener(listener)

        subscriber.setCancellable {
            unregisterOnSharedPreferenceChangeListener(listener)
        }

        subscriber.onNext(key)
    }
