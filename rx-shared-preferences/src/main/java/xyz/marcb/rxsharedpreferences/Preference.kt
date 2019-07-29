package xyz.marcb.rxsharedpreferences

import io.reactivex.Observable

interface Preference<T> {

    var value: T

    val isSet: Boolean

    fun asObservable(): Observable<T>

    fun clear()
}
