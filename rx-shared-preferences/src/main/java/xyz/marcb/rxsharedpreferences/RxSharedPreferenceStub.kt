package xyz.marcb.rxsharedpreferences

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class RxSharedPreferenceStub<T>(private val defaultValue: T): Preference<T> {

    private val behaviorSubject: BehaviorSubject<T> = BehaviorSubject.createDefault(defaultValue)

    override var value: T
        get() = behaviorSubject.value ?: defaultValue
        set(value) = behaviorSubject.onNext(value)

    override val isSet: Boolean
        get() = true

    override fun asObservable(): Observable<T> = behaviorSubject

    override fun clear() = behaviorSubject.onNext(defaultValue)
}
