//
// Created by ron.liu on 18/7/2023.
//

#include "tile_android.hpp"
using namespace mbgl::android;

jni::Local<jni::Object<TileId>> TileId::New(jni::JNIEnv& env, int x, int y, int z, int overscaledZ, int wrap, bool isLoaded) {
    static auto& javaClass = jni::Class<TileId>::Singleton(env);
    static auto constructor = javaClass.GetConstructor<int, int, int, int, int,jboolean>(env);
    return javaClass.New(env, constructor, x, y, z, overscaledZ, wrap, jni::jboolean(isLoaded));
}

jni::Local<jni::Object<TileId>> TileId::New(jni::JNIEnv& env, int x, int y, int z, int type) {
    static auto& javaClass = jni::Class<TileId>::Singleton(env);
    static auto constructor = javaClass.GetConstructor<int, int, int, int>(env);
    return javaClass.New(env, constructor, x, y, z, type);
}

int TileId::getX(jni::JNIEnv& env, const jni::Object<TileId>& tile) {
    static auto& javaClass = jni::Class<TileId>::Singleton(env);
    static auto field = javaClass.GetField<jint>(env, "x");
    return tile.Get(env, field);
}

int TileId::getY(jni::JNIEnv& env, const jni::Object<TileId>& tile) {
    static auto& javaClass = jni::Class<TileId>::Singleton(env);
    static auto field = javaClass.GetField<jint>(env, "y");
    return tile.Get(env, field);
}

int TileId::getZ(jni::JNIEnv& env, const jni::Object<TileId>& tile) {
    static auto& javaClass = jni::Class<TileId>::Singleton(env);
    static auto field = javaClass.GetField<jint>(env, "z");
    return tile.Get(env, field);
}

int TileId::getOverscaledZ(jni::JNIEnv& env, const jni::Object<TileId>& tile) {
    static auto& javaClass = jni::Class<TileId>::Singleton(env);
    static auto field = javaClass.GetField<jint>(env, "overscaledZ");
    return tile.Get(env, field);
}

int TileId::getWrap(jni::JNIEnv& env, const jni::Object<TileId>& tile) {
    static auto& javaClass = jni::Class<TileId>::Singleton(env);
    static auto field = javaClass.GetField<jint>(env, "wrap");
    return tile.Get(env, field);
}

void TileId::setType(jni::JNIEnv& env, const jni::Object<TileId>& tile, const jni::jint value) {
    static auto& javaClass = jni::Class<TileId>::Singleton(env);
    static auto field = javaClass.GetField<jint>(env, "type");
    tile.Set(env, field,(jint) value);
}

int TileId::getType(jni::JNIEnv& env, const jni::Object<TileId>& tile) {
    static auto& javaClass = jni::Class<TileId>::Singleton(env);
    static auto field = javaClass.GetField<jint>(env, "type");
    return tile.Get(env, field);
}