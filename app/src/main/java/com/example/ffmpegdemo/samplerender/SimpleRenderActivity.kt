package com.example.ffmpegdemo.samplerender

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ffmpegdemo.R
import kotlinx.android.synthetic.main.activity_simple_render.*

/**
 * @author lilin
 * @time on 2021/6/24 11:02 AM
 */
public class SimpleRenderActivity : AppCompatActivity() {

    private var mDrawer: IDrawer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_render)

        mDrawer = TriangleDrawer()

        initRender()
    }

    private fun initRender() {
        gl_surface_view.setEGLContextClientVersion(2)
        mDrawer?.let {
            gl_surface_view.setRenderer(SimpleRender(it))
        }
    }

}