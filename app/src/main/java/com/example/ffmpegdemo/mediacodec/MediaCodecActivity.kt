package com.example.ffmpegdemo.mediacodec

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ffmpegdemo.R
import kotlinx.android.synthetic.main.activity_mediacodec.*
import java.util.concurrent.Executors

/**
 * @author lilin
 * @time on 2021/7/2 5:31 PM
 */

class MediaCodecActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mediacodec)
        initPlayer()
    }

    private fun initPlayer() {
        val path = "/mnt/sdcard/test.mp4"
        val threadPool = Executors.newFixedThreadPool(2)

        //创建视频解码器
        val videoDecoder = VideoDecoder(path, sfv, null)
        threadPool.execute(videoDecoder)

        //创建音频解码器
        val audioDecoder = AudioDecoder(path)
        threadPool.execute(audioDecoder)

        videoDecoder.goOn()
        audioDecoder.goOn()
    }

}