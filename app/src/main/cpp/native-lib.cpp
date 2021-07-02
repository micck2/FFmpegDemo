#include <jni.h>
#include <string>
#include "media/player.h"

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_ffmpegdemo_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C" {
    #include <libavcodec/avcodec.h>
    #include <libavformat/avformat.h>
    #include <libavfilter/avfilter.h>
    #include <libavcodec/jni.h>

    JNIEXPORT jstring JNICALL
    Java_com_example_ffmpegdemo_MainActivity_ffmpegInfoFromJNI(JNIEnv* env, jobject) {
        char info[40000] = {0};
        AVCodec *c_temp = av_codec_next(nullptr);
        while (c_temp != nullptr) {
            if (c_temp->decode != nullptr) {
                sprintf(info, "%sdecode:", info);
            } else {
                sprintf(info, "%sencode:", info);
            }
            switch (c_temp->type) {
                case AVMEDIA_TYPE_VIDEO:
                    sprintf(info, "%s(video):", info);
                    break;
                case AVMEDIA_TYPE_AUDIO:
                    sprintf(info, "%s(audio):", info);
                    break;
                default:
                    sprintf(info, "%s(other):", info);
                    break;
            }
            sprintf(info, "%s[%s]\n", info, c_temp->name);
            c_temp = c_temp->next;
        }

        return env->NewStringUTF(info);
    }

    JNIEXPORT jint JNICALL
    Java_com_example_ffmpegdemo_MainActivity_createPlayer(JNIEnv *env,
                                                           jobject  /* this */,
                                                           jstring path,
                                                           jobject surface) {
        Player *player = new Player(env, path, surface);
        //LOGE("createPlayer", "%d", player)
        return (jint) player;
    }

    JNIEXPORT void JNICALL
    Java_com_example_ffmpegdemo_MainActivity_play(JNIEnv *env,
                                                   jobject  /* this */,
                                                   jint player) {
        Player *p = (Player *) player;
        p->play();
    }

    JNIEXPORT void JNICALL
    Java_com_example_ffmpegdemo_MainActivity_pause(JNIEnv *env,
                                                    jobject  /* this */,
                                                    jint player) {
        Player *p = (Player *) player;
        p->pause();
    }
}