package com.example.ffmpegdemo.mediacodec

import android.media.MediaCodec
import android.media.MediaFormat
import android.util.Log
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.nio.ByteBuffer

/**
 * 视频解码器
 *
 * @author lilin
 * @time on 2021/7/2 2:52 PM
 */

class VideoDecoder(path: String,
                   private val sfv: SurfaceView?,
                   private var surface: Surface?) : BaseDecoder(path) {

    private val TAG = "VideoDecoder"

    override fun check(): Boolean {
        if (sfv == null && surface == null) {
            Log.w(TAG, "SurfaceView和Surface都为空，至少需要一个不为空")
            mStateListener?.decoderError(this, "显示器为空")
            return false
        }

        return true
    }

    override fun initExtractor(path: String): IExtractor {
        return VideoExtractor(path)
    }

    override fun initSpecParams(format: MediaFormat) {

    }

    override fun configCodec(codec: MediaCodec, format: MediaFormat): Boolean {
        if (surface != null) {
            codec.configure(format, surface, null, 0)
            notifyDecode()
        } else {
            sfv?.holder?.addCallback(object : SurfaceHolder.Callback2 {
                override fun surfaceRedrawNeeded(holder: SurfaceHolder) {

                }

                override fun surfaceChanged(
                    holder: SurfaceHolder,
                    format: Int,
                    width: Int,
                    height: Int
                ) {

                }

                override fun surfaceDestroyed(holder: SurfaceHolder) {

                }

                override fun surfaceCreated(holder: SurfaceHolder) {
                    surface = holder.surface
                    configCodec(codec, format)
                }
            })

            return false
        }

        return true
    }

    override fun initRender(): Boolean {
        return true
    }

    override fun render(outputBuffers: ByteBuffer, bufferInfo: MediaCodec.BufferInfo) {

    }

    override fun finishDecode() {

    }
}