//
// Created by lilin503 on 2021/6/22.
//

#ifndef FFMPEGDEMO_I_DECODER_H
#define FFMPEGDEMO_I_DECODER_H

class IDecoder {
public:
    virtual void GoOn() = 0;
    virtual void Pause() = 0;
    virtual void Stop() = 0;
    virtual bool IsRunning() = 0;
    virtual long GetDuration() = 0;
    virtual long GetCurPos() = 0;
    //virtual void SetStateReceiver(IDecodeStateCb *cb) = 0;
};

#endif //FFMPEGDEMO_I_DECODER_H
