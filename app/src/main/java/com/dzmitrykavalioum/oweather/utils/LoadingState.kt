package com.dzmitrykavalioum.oweather.utils




data class LoadingState private constructor(val status: Status, val message:String ?=null){

    companion object{
        val LOADED = LoadingState(Status.SUCCESS)
        val LOADING = LoadingState(Status.RUNNING)
        fun error (message: String?) = LoadingState(Status.FAILED,message)
    }

    enum class Status{
        RUNNING,
        SUCCESS,
        FAILED
    }
}