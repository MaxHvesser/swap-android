package no.mhl.clarence.data.remote.common

import okhttp3.ResponseBody

data class Resource<out T>(val status: Status, val data: T?, val errorBody: ResponseBody?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(error: ResponseBody?, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, error)
        }
    }
}