package com.example.ffmpegdemo.samplerender

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * @author lilin
 * @time on 2021/6/24 11:08 AM
 */
class SimpleRender(private val mDrawer: IDrawer) : GLSurfaceView.Renderer {

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        GLES20.glClearColor(0f,0f,0f,0f)
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
        mDrawer.setTextureID(OpenGLTools.createTextureIds(1)[0])
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
    }

    override fun onDrawFrame(gl: GL10?) {
        mDrawer.draw()
    }
}