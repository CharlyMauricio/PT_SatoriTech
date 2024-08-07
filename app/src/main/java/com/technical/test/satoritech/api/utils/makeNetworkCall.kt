package com.technical.test.satoritech.api.utils

import com.technical.test.satoritech.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.UnknownHostException

private const val UNAUTHORIZED_ERROR_CODE = 401

suspend fun <T> makeNetworkCall(
    call: suspend () -> T
): ApiResponseStatus<T> = withContext(Dispatchers.IO) {
    try {
        ApiResponseStatus.Success(call())
    } catch (e: UnknownHostException) {
        ApiResponseStatus.Error(R.string.unknown_host_exception_error)
    } catch (e: HttpException) {
        val errorMessage = if (e.code() == UNAUTHORIZED_ERROR_CODE) {
            R.string.wrong_user_or_password
        } else {
            R.string.unknown_error
        }
        ApiResponseStatus.Error(errorMessage)
    } catch (e: Exception) {
        val errorMessage = when (e.message) {
            "user_already_exists" -> R.string.user_already_exists
            else -> {
                if (e.message!!.contains("Parameter specified as non-null is null")) {
                    R.string.parameter_non_null_is_null
                } else {
                    R.string.unknown_error
                }
            }
        }
        ApiResponseStatus.Error(errorMessage)
    }
}