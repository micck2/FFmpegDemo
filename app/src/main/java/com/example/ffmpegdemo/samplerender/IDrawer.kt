package com.example.ffmpegdemo.samplerender

/**
 * @author lilin
 * @time on 2021/6/24 11:07 AM
 */
interface IDrawer {
    fun draw()
    fun setTextureID(id: Int)
    fun release()
}