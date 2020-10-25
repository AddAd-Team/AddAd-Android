package com.add.ad.domain.base;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class UseCase <T,E> {
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected abstract Single<E> create(T data);

    public void execute(T data, DisposableSingleObserver singleObserver) {
        DisposableSingleObserver observer = create(data)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(singleObserver);

        compositeDisposable.add(observer);
    }

    public void dispose() {
        compositeDisposable.dispose();
    }

}