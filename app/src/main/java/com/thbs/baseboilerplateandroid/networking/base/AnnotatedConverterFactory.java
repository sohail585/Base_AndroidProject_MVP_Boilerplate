package com.thbs.baseboilerplateandroid.networking.base;

import android.support.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class AnnotatedConverterFactory extends Converter.Factory {


    final Map<Class<?>, Converter.Factory> factories;

    public AnnotatedConverterFactory(Map<Class<?>, Converter.Factory> factories) {
        super();
        this.factories = new LinkedHashMap<>(factories);
    }

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        for (Annotation annotation : annotations) {
            Converter.Factory factory = factories.get(annotation.annotationType());

            if (factory != null) {
                return factory.responseBodyConverter(type, annotations, retrofit);
            }

            //try to default to json in case no annotation on current method was found
            Converter.Factory jsonFactory = factories.get(Json.class);
            if (jsonFactory != null) {
                return jsonFactory.responseBodyConverter(type, annotations, retrofit);
            }
        }
        return null;
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface Json {
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface Xml {
    }

    static class Builder {
        Map<Class<?>, Converter.Factory> factories = new LinkedHashMap<>();

        Builder add(Class<? extends Annotation> cls, Converter.Factory factory) {
            if (cls == null)
                throw new NullPointerException("cls you supplied to builder factory is null");
            if (factory == null)
                throw new NullPointerException("factory you supplied to builder is null");
            factories.put(cls, factory);
            return this;
        }

        AnnotatedConverterFactory build() {
            return new AnnotatedConverterFactory(factories);
        }
    }

}
