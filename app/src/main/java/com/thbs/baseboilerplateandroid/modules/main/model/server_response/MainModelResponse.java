package com.thbs.baseboilerplateandroid.modules.main.model.server_response;

/**
 * Created by muhammed_suhail on 3/12/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class MainModelResponse {
    @SerializedName("commercialReference")
    @Expose
    public String name;

}
