package com.thbs.baseboilerplateandroid.modules.main.contract;

/**
 * Created by muhammed_suhail on 3/12/2018.
 */

import com.thbs.baseboilerplateandroid.base.BaseInteractor;
import com.thbs.baseboilerplateandroid.base.BasePresenter;
import com.thbs.baseboilerplateandroid.base.BaseView;
import com.thbs.baseboilerplateandroid.modules.main.model.server_response.MainModelResponse;

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
