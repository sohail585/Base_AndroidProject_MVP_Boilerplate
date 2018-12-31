package com.thbs.mis.baseboilerplateandroid.base;

/**
 * Created by muhammed_suhail on 1/4/2018.
 */

public interface BasePresenter<T extends BaseInteractor, TT extends BaseView> {
    void stop();

    void setUpInteractor();

    void setInteractor(T baseInteractor);

    void start(TT baseView);

    void logTheUserOut();
}
