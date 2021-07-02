//
// Created by lilin503 on 2021/6/22.
//

#ifndef FFMPEGDEMO_DECODE_STATE_H
#define FFMPEGDEMO_DECODE_STATE_H

enum DecodeState {
    STOP,
    PREPARE,
    START,
    DECODING,
    PAUSE,
    FINISH
};

#endif //FFMPEGDEMO_DECODE_STATE_H
