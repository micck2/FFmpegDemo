package com.example.ffmpegdemo.mediacodec

import android.media.MediaFormat
import java.nio.ByteBuffer

/**
 * @author lilin
 * @time on 2021/6/30 4:37 PM
 */
interface IExtractor {
    /**
     * 获取音视频格式参数
     */
    fun getFormat(): MediaFormat?

    /**
     * 读取音视频数据
     */
    fun readBuffer(byteBuffer: ByteBuffer): Int

    /**
     * 获取当前帧时间
     */
    fun getCurrentTimestamp(): Long

    /**
     * Seek到指定位置，并返回实际帧的时间戳
     */
    fun seek(pos: Long): Long

    fun setStartPos(pos: Long)

    /**
     * 停止读取数据
     */
    fun stop()
}
