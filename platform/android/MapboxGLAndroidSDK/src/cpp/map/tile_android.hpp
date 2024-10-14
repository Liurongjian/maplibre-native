#pragma once

#include <mbgl/util/noncopyable.hpp>
#include <jni/jni.hpp>

namespace mbgl {
    namespace android {
        class TileId : private mbgl::util::noncopyable{
        public:
            static constexpr auto Name() { return "com/mapbox/mapboxsdk/maps/TileId"; };
            static jni::Local<jni::Object<TileId>> New(jni::JNIEnv&, int, int, int, int, int, bool);
            static jni::Local<jni::Object<TileId>> New(jni::JNIEnv& env, int x, int y, int z, int type);
            static int getX(jni::JNIEnv&, const jni::Object<TileId>&);
            static int getY(jni::JNIEnv&, const jni::Object<TileId>&);
            static int getZ(jni::JNIEnv&, const jni::Object<TileId>&);
            static int getOverscaledZ(jni::JNIEnv&, const jni::Object<TileId>&);
            static int getWrap(jni::JNIEnv&, const jni::Object<TileId>&);
            static void setType(jni::JNIEnv&, const jni::Object<TileId>&, const jni::jint value);
            static int getType(jni::JNIEnv&, const jni::Object<TileId>&);
        };
    }
}


