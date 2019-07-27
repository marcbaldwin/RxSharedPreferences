package xyz.marcb.rxsharedpreferences

import android.content.SharedPreferences
import rx.Observable

// Emits key
fun SharedPreferences.observe(key: String): Observable<String> =
    Observable.create(
        { subscriber ->
            val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, changedKey ->
                if (key == changedKey) {
                    subscriber.onNext(changedKey)
                }
            }

            registerOnSharedPreferenceChangeListener(listener)

            subscriber.setCancellation {
                unregisterOnSharedPreferenceChangeListener(listener)
            }

            subscriber.onNext(key)
        },
        rx.Emitter.BackpressureMode.NONE
    )
