package com.example.ffmpegdemo.mediacodec

/**
 * @author lilin
 * @time on 2021/6/30 4:37 PM
 */
enum class DecodeState {
    /**开始状态*/
    START,
    /**解码中*/
    DECODING,
    /**解码暂停*/
    PAUSE,
    /**正在快进*/
    SEEKING,
    /**解码完成*/
    FINISH,
    /**解码器释放*/
    STOP
}