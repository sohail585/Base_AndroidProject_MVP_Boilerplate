package com.thbs.mis.baseboilerplateandroid.modules.main.contract;

import com.thbs.mis.baseboilerplateandroid.base.BaseInteractor;
import com.thbs.mis.baseboilerplateandroid.base.BasePresenter;
import com.thbs.mis.baseboilerplateandroid.base.BaseView;
import com.thbs.mis.baseboilerplateandroid.modules.main.model.server_response.MainModelResponse;

import retrofit2.Response;

public interface MainActivityContract {
    interface View extends BaseView{

        void showMainModelResponse(Response<MainModelResponse> mainModelResponse, Throwable throwable);
    }

    interface Presenter extends BasePresenter{

        void getMainModel();

        void returnMainModel(Response<MainModelResponse> mainModelResponse, Throwable throwable);
    }

    interface Interactor extends BaseInteractor{

        void fetchMainModel();
    }
}
