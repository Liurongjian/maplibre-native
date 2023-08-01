#include "raster_dem_source.hpp"

#include "../android_conversion.hpp"
#include "../value.hpp"
#include "../conversion/url_or_tileset.hpp"
#include "source.hpp"
#include "mbgl/tile/raster_dem_tile.hpp"
#include <mbgl/renderer/buckets/hillshade_bucket.hpp>


#include <mbgl/util/variant.hpp>

#include <string>

namespace mbgl {
namespace android {

    RasterDEMSource::RasterDEMSource(jni::JNIEnv& env, const jni::String& sourceId, const jni::Object<>& urlOrTileSet, jni::jint tileSize)
        : Source(
            env,
            std::make_unique<mbgl::style::RasterDEMSource>(
                jni::Make<std::string>(env, sourceId),
                convertURLOrTileset(Value(env, urlOrTileSet)),
                tileSize
            )
        ) {
    }

    RasterDEMSource::RasterDEMSource(jni::JNIEnv& env,
                                     mbgl::style::Source& coreSource,
                                     AndroidRendererFrontend* frontend)
        : Source(env, coreSource, createJavaPeer(env), frontend) {}

    RasterDEMSource::~RasterDEMSource() = default;

    jni::Local<jni::String> RasterDEMSource::getURL(jni::JNIEnv& env) {
        optional<std::string> url = source.as<mbgl::style::RasterDEMSource>()->RasterDEMSource::getURL();
        return url ? jni::Make<jni::String>(env, *url) : jni::Local<jni::String>();
    }

    jni::Local<jni::Object<Source>> RasterDEMSource::createJavaPeer(jni::JNIEnv& env) {
        static auto& javaClass = jni::Class<RasterDEMSource>::Singleton(env);
        static auto constructor = javaClass.GetConstructor<jni::jlong>(env);
        return javaClass.New(env, constructor, reinterpret_cast<jni::jlong>(this));
    }

    jni::Local<jni::Object<Bitmap>> RasterDEMSource::queryTileBitmap(jni::JNIEnv& env, jni::Object<TileId>& tile) {
        auto sourceTile = rendererFrontend->querySourceTile(source.getID(), {(uint8_t) TileId::getOverscaledZ(env, tile), (int16_t) TileId::getWrap(env, tile),
                                                                             (uint8_t) TileId::getZ(env, tile), (uint32_t) TileId::getX(env, tile),
                                                                             (uint32_t) TileId::getY(env, tile)});
        if (sourceTile != nullptr) {
            auto demTile = dynamic_cast<RasterDEMTile *>(sourceTile);
            if (demTile->isLoaded()) {
                auto image = demTile->getBucket()->getDEMData().getImage();
                return Bitmap::CreateBitmap(env, *image);
            }
        }
        return jni::Local<jni::Object<Bitmap>>();
    }

    void RasterDEMSource::registerNative(jni::JNIEnv& env) {
        // Lookup the class
        static auto& javaClass = jni::Class<RasterDEMSource>::Singleton(env);

        #define METHOD(MethodPtr, name) jni::MakeNativePeerMethod<decltype(MethodPtr), (MethodPtr)>(name)

        // Register the peer
        jni::RegisterNativePeer<RasterDEMSource>(
            env, javaClass, "nativePtr",
            jni::MakePeer<RasterDEMSource, const jni::String&, const jni::Object<>&, jni::jint>,
            "initialize",
            "finalize",
            METHOD(&RasterDEMSource::getURL, "nativeGetUrl"),
            METHOD(&RasterDEMSource::queryTileBitmap, "nativeQueryTileBitmap")
        );
    }

} // namespace android
} // namespace mbgl
