package com.example.alex.ruletkacsgo.interfaces;

/**
 * Created by alex on 16.12.17.
 */

public interface ILoadingStatus<T> {

    void onSuccess(T info);

    void onFailure(String message);
}
