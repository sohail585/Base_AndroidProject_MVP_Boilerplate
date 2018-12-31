package com.thbs.mis.baseboilerplateandroid.networking.base;

import android.support.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class XmlOrJsonConverterFactory extends Converter.Factory {

    final Converter.Factory xml = SimpleXmlConverterFactory.create();
    final Converter.Factory json = GsonConverterFactory.create();


    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {

        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == AnnotatedConverterFactory.Xml.class) {
                return xml.responseBodyConverter(type, annotations, retrofit);
            }
            if (annotation.annotationType() == AnnotatedConverterFactory.Json.class) {
                return json.responseBodyConverter(type, annotations, retrofit);
            }
        }
        return null;
    }
}
