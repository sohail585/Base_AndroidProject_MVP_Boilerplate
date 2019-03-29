package com.thbs.baseboilerplateandroid.networking.api;

/**
 * Created by muhammed_suhail on 3/12/2018.
 */

import com.thbs.baseboilerplateandroid.modules.main.model.server_response.MainModelResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

public interface MainAPI {

    @GET("5c1acb3533000070007fd56d/rest/bfs/installedProduct/1.0/installedProducts")
    Observable<Response<MainModelResponse>> getMainModel();
}
