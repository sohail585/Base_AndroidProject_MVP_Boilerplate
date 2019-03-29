package com.thbs.baseboilerplateandroid.modules.main.view.activity;

/**
 * Created by muhammed_suhail on 3/12/2018.
 */

import android.os.Bundle;

import com.thbs.baseboilerplateandroid.R;
import com.thbs.baseboilerplateandroid.base.BaseActivity;
import com.thbs.baseboilerplateandroid.base.BasePresenter;
import com.thbs.baseboilerplateandroid.modules.main.contract.MainActivityContract;
import com.thbs.baseboilerplateandroid.modules.main.model.server_response.MainModelResponse;
import com.thbs.baseboilerplateandroid.modules.main.presenter.MainPresenter;

import retrofit2.Response;

public class MainActivity extends BaseActivity implements MainActivityContract.View {

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        setUpPresenter();
        mainPresenter.getMainModel();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setUpPresenter() {
        mainPresenter = new MainPresenter();
        setPresenter(mainPresenter);
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        mainPresenter.start(this);
    }

    @Override
    public void logTheUserOut() {

    }

    @Override
    public void showMainModelResponse(Response<MainModelResponse> mainModelResponse, Throwable throwable) {
        if(throwable != null){
            showLongToast(throwable.getMessage());
        }else{
            showLongToast(String.valueOf(mainModelResponse.code()));
        }
    }
}
