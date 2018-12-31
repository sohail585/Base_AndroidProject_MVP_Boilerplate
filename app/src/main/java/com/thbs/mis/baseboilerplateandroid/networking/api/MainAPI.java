package com.thbs.mis.baseboilerplateandroid.networking.api;

import com.thbs.mis.baseboilerplateandroid.modules.main.model.server_response.MainModelResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MainAPI {

    @GET("5c1acb3533000070007fd56d/rest/bfs/installedProduct/1.0/installedProducts")
    Observable<Response<MainModelResponse>> getMainModel();
}
