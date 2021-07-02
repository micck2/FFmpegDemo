package com.example.ffmpegdemo.mediacodec

import android.media.MediaExtractor
import android.media.MediaFormat
import java.nio.ByteBuffer

/**
 * @author lilin
 * @time on 2021/7/2 11:12 AM
 */
class MMExtractor(path: String?) {

    /**音视频分离器*/
    private var mExtractor: MediaExtractor? = null

    /**音频通道索引*/
    private var mAudioTrack = -1

    /**视频通道索引*/
    private var mVideoTrack = -1

    /**当前帧时间戳*/
    private var mCurSampleTime: Long = 0

    /**开始解码时间点*/
    private var mStartPos: Long = 0

    init {
        //【1，初始化】
        mExtractor = MediaExtractor()
        path?.let { mExtractor?.setDataSource(it) }
    }

    /**
     * 获取视频格式参数
     */
    fun getVideoFormat(): MediaFormat? {
        //【2.1，获取视频多媒体格式】
        for (i in 0 until mExtractor!!.trackCount) {
            val format = mExtractor!!.getTrackFormat(i)
            val mime = format.getString(MediaFormat.KEY_MIME)
            if (mime?.startsWith("video/") == true) {
                mVideoTrack = i
                break
            }
        }

        return if (mVideoTrack >= 0)
            mExtractor!!.getTrackFormat(mVideoTrack)
        else null
    }

    /**
     * 获取音频格式参数
     */
    fun getAudioFormat(): MediaFormat? {
        //【2.2，获取音频频多媒体格式】
        for (i in 0 until mExtractor!!.trackCount) {
            val format = mExtractor!!.getTrackFormat(i)
            val mime = format.getString(MediaFormat.KEY_MIME)
            if (mime?.startsWith("audio/") == true) {
                mAudioTrack = i
                break
            }
        }

        return if (mAudioTrack >= 0)
            mExtractor!!.getTrackFormat(mAudioTrack)
        else null
    }

    /**
     * 读取视频数据
     */
    fun readBuffer(byteBuffer: ByteBuffer): Int {
        byteBuffer.clear()
        selectSourceTrack()
        val readSampleSize = mExtractor!!.readSampleData(byteBuffer, 0)
        if (readSampleSize < 0) {
            return -1
        }
        mCurSampleTime = mExtractor!!.sampleTime
        mExtractor!!.advance()
        return readSampleSize
    }

    /**
     * 选择通道
     */
    private fun selectSourceTrack() {
        if (mVideoTrack >= 0) {
            mExtractor!!.selectTrack(mVideoTrack)
        } else if (mAudioTrack >= 0) {
            mExtractor!!.selectTrack(mAudioTrack)
        }
    }

    /**
     * Seek到指定位置，并返回实际帧的时间戳
     */
    fun seek(pos: Long): Long {
        mExtractor!!.seekTo(pos, MediaExtractor.SEEK_TO_PREVIOUS_SYNC)
        return mExtractor!!.sampleTime
    }

    /**
     * 停止读取数据
     */
    fun stop() {
        //【4，释放提取器】
        mExtractor?.release()
        mExtractor = null
    }

    fun getVideoTrack(): Int {
        return mVideoTrack
    }

    fun getAudioTrack(): Int {
        return mAudioTrack
    }

    fun setStartPos(pos: Long) {
        mStartPos = pos
    }

    /**
     * 获取当前帧时间
     */
    fun getCurrentTimestamp(): Long {
        return mCurSampleTime
    }
}