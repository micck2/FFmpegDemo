package com.example.ffmpegdemo.util

/**
 * @author lilin
 * @time on 2021/7/6 5:00 PM
 */

sealed class BooleanExt<out T>

class Success<T>(val data: T) : BooleanExt<T>()

object Otherwise : BooleanExt<Nothing>()

inline fun <T> Boolean.yes(block: () -> T): BooleanExt<T> =
    when {
        this -> Success(block())
        else -> Otherwise
    }

inline fun <T> Boolean.no(block: () -> T): BooleanExt<T> =
    when {
        this -> Otherwise
        else -> Success(block())
    }

inline fun <T> BooleanExt<T>.otherwise(block: () -> T): T =
    when (this){
        is Success -> this.data
        Otherwise -> block()
    }