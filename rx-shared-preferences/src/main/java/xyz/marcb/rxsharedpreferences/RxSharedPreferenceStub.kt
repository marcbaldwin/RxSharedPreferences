package xyz.marcb.rxsharedpreferences

import rx.Observable
import rx.subjects.BehaviorSubject

class RxSharedPreferenceStub<T>(private val defaultValue: T): Preference<T> {

    private val behaviorSubject: BehaviorSubject<T> = BehaviorSubject.create(defaultValue)

    override var value: T
        get() = behaviorSubject.value
        set(value) = behaviorSubject.onNext(value)

    override val isSet: Boolean
        get() = true

    override fun asObservable(): Observable<T> = behaviorSubject.asObservable()

    override fun clear() = behaviorSubject.onNext(defaultValue)
}
