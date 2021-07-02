//
// Created by lilin503 on 2021/6/22.
//

#ifndef FFMPEGDEMO_PLAYER_H
#define FFMPEGDEMO_PLAYER_H


#include "decoder/video/v_decoder.h"

class Player {
private:
    VideoDecoder *m_v_decoder;
    VideoRender *m_v_render;

public:
    Player(JNIEnv *jniEnv, jstring path, jobject surface);
    ~Player();

    void play();
    void pause();
};


#endif //FFMPEGDEMO_PLAYER_H
