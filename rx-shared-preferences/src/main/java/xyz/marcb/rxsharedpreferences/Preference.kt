package xyz.marcb.rxsharedpreferences

import rx.Observable

interface Preference<T> {

    var value: T

    val isSet: Boolean

    fun asObservable(): Observable<T>

    fun clear()
}
