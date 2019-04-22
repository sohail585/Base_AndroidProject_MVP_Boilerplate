package com.thbs.baseboilerplateandroid.base;

/**
 * Created by muhammed_suhail on 1/4/2018.
 */

public interface BaseInteractor<T extends BasePresenter> {

    void start(T basePresenter);

}
