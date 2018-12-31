package com.thbs.mis.baseboilerplateandroid.base;

/**
 * Created by muhammed_suhail on 1/4/2018.
 */

public interface BaseView<T extends BasePresenter> {

    void setUpPresenter();

    void setPresenter(T presenter);

    void logTheUserOut();

}
