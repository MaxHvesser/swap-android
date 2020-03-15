package no.mhl.swap.data.remote.common

import no.mhl.swap.data.remote.common.Status.SUCCESS
import no.mhl.swap.data.remote.common.Status.ERROR

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(ERROR, data, msg)
        }
    }
}