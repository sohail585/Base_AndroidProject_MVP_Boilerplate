package com.thbs.mis.baseboilerplateandroid.modules.main.model.server_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class MainModelResponse {
    @SerializedName("commercialReference")
    @Expose
    public String name;

}
