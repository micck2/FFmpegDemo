package com.example.ffmpegdemo.mediacodec

import android.media.MediaFormat
import java.nio.ByteBuffer

/**
 * 视频提取器
 *
 * @author lilin
 * @time on 2021/7/2 2:43 PM
 */

class VideoExtractor(path: String?) : IExtractor {
    private val mExtractor = MMExtractor(path)

    override fun getFormat(): MediaFormat? {
        return mExtractor.getVideoFormat()
    }

    override fun readBuffer(byteBuffer: ByteBuffer): Int {
        return mExtractor.readBuffer(byteBuffer)
    }

    override fun getCurrentTimestamp(): Long {
        return mExtractor.getCurrentTimestamp()
    }

    override fun seek(pos: Long): Long {
        return mExtractor.seek(pos)
    }

    override fun setStartPos(pos: Long) {
        return mExtractor.setStartPos(pos)
    }

    override fun stop() {
        mExtractor.stop()
    }
}