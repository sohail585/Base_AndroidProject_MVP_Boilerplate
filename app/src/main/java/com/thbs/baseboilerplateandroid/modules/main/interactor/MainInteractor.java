package com.thbs.baseboilerplateandroid.modules.main.interactor;

/**
 * Created by muhammed_suhail on 3/12/2018.
 */

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.thbs.baseboilerplateandroid.base.BasePresenter;
import com.thbs.baseboilerplateandroid.modules.main.contract.MainActivityContract;
import com.thbs.baseboilerplateandroid.modules.main.model.server_response.MainModelResponse;
import com.thbs.baseboilerplateandroid.networking.api.MainAPI;
import com.thbs.baseboilerplateandroid.networking.base.RetrofitBuilderHelper;
import com.thbs.baseboilerplateandroid.networking.constants.BaseURLs;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MainInteractor implements MainActivityContract.Interactor {

    private MainActivityContract.Presenter mainPresenter;
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    public void start(BasePresenter basePresenter) {
        mainPresenter = (MainActivityContract.Presenter) basePresenter;
    }

    @Override
    public void fetchMainModel() {
        RetrofitBuilderHelper retrofitBuilderHelper = RetrofitBuilderHelper.getInstance();
        retrofitBuilderHelper.setBaseUrl(BaseURLs.BASE_URL);

        MainAPI getProductDetailsApi = retrofitBuilderHelper.createServiceWithoutBasicAuth(MainAPI.class);
        Observable<Response<MainModelResponse>> viewProfileDetailsObservable = getProductDetailsApi.getMainModel();


        disposables.add(viewProfileDetailsObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<MainModelResponse>>() {
                    @Override
                    public void accept(@NonNull Response<MainModelResponse> mainModelResponse) {
                        // success response
                        mainPresenter.returnMainModel(mainModelResponse, null);
                        //leaveManagementPresenter.returnProfileDetailsToView(userProfileDetails, null);
                    }
                }, new Consumer<Throwable>() {
                    @SuppressLint("CheckResult")
                    @Override
                    public void accept(@NonNull Throwable throwable) {
                        // failure response
                        if(throwable != null){
                            mainPresenter.returnMainModel(null, throwable);
                        }else{
                            mainPresenter.returnMainModel(null , new Throwable("Something went wrong!!!"));
                        }
                        //leaveManagementPresenter.returnProfileDetailsToView(null, new Throwable("Something went wrong!!!"));
                    }
                }));
    }
}
