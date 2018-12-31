package com.thbs.mis.baseboilerplateandroid.modules.main.presenter;

import com.thbs.mis.baseboilerplateandroid.base.BaseInteractor;
import com.thbs.mis.baseboilerplateandroid.base.BaseView;
import com.thbs.mis.baseboilerplateandroid.modules.main.contract.MainActivityContract;
import com.thbs.mis.baseboilerplateandroid.modules.main.interactor.MainInteractor;
import com.thbs.mis.baseboilerplateandroid.modules.main.model.server_response.MainModelResponse;

import retrofit2.Response;

public class MainPresenter implements MainActivityContract.Presenter {

    private MainInteractor mainInteractor;
    private MainActivityContract.View mainView;

    @Override
    public void stop() {

    }

    @Override
    public void setUpInteractor() {
        mainInteractor = new MainInteractor();
        setInteractor(mainInteractor);
    }

    @Override
    public void setInteractor(BaseInteractor baseInteractor) {
        mainInteractor.start(this);
    }

    @Override
    public void start(BaseView baseView) {
        mainView = (MainActivityContract.View) baseView;
        setUpInteractor();
    }

    @Override
    public void logTheUserOut() {

    }

    @Override
    public void getMainModel() {
        mainInteractor.fetchMainModel();
    }

    @Override
    public void returnMainModel(Response<MainModelResponse> mainModelResponse, Throwable throwable) {
        mainView.showMainModelResponse(mainModelResponse, throwable);
    }
}
