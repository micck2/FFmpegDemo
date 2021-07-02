package com.example.ffmpegdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.TextView
import android.widget.Toast
import com.example.ffmpegdemo.mediacodec.MediaCodecActivity
import com.example.ffmpegdemo.samplerender.SimpleRenderActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    private var player: Int? = null
    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example of a call to a native method
        findViewById<TextView>(R.id.tv).text = ffmpegInfoFromJNI()
        initSfv()

        findViewById<TextView>(R.id.tv).setOnClickListener {
            player?.let { player ->
                if (isPlaying) {
                    isPlaying = false
                    pause(player)
                } else {
                    isPlaying = true
                    play(player)
                }
            }
        }

        tv_simple_render.setOnClickListener {
            startActivity(Intent(this, SimpleRenderActivity::class.java))
        }

        tv_media_codec.setOnClickListener {
            startActivity(Intent(this, MediaCodecActivity::class.java))
        }
    }

    private fun initSfv() {
        val path = "/mnt/sdcard/test.mp4"
        if (File(path).exists()) {
            findViewById<SurfaceView>(R.id.sfv).holder.addCallback(object : SurfaceHolder.Callback {
                override fun surfaceChanged(holder: SurfaceHolder, format: Int,
                                            width: Int, height: Int) {}
                override fun surfaceDestroyed(holder: SurfaceHolder) {}

                override fun surfaceCreated(holder: SurfaceHolder) {
                    if (player == null) {
                        player = createPlayer(path, holder.surface)
                        //play(player!!)
                    }
                }
            })
        } else {
            Toast.makeText(this, "视频文件不存在，请在手机根目录下放置 test.mp4", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    //external fun stringFromJNI(): String

    private external fun ffmpegInfoFromJNI(): String

    private external fun createPlayer(path: String, surface: Surface): Int

    private external fun play(player: Int)

    private external fun pause(player: Int)

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}