package com.example.ffmpegdemo.mediacodec

/**
 * @author lilin
 * @time on 2021/6/30 4:43 PM
 */
interface IDecoderStateListener {
    fun decoderPrepare(iDecoder: IDecoder)

    fun decoderFinish(iDecoder: IDecoder)

    fun decoderPause(iDecoder: IDecoder)

    fun decoderRunning(iDecoder: IDecoder)

    fun decoderError(iDecoder: IDecoder, errorMsg: String?)

    fun decoderDestroy(iDecoder: IDecoder)
}