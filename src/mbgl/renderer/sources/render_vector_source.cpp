#include <mbgl/renderer/sources/render_vector_source.hpp>
#include <mbgl/renderer/render_tile.hpp>
#include <mbgl/renderer/paint_parameters.hpp>
#include <mbgl/tile/vector_tile.hpp>
#include <mbgl/renderer/tile_parameters.hpp>

namespace mbgl {

using namespace style;

RenderVectorSource::RenderVectorSource(Immutable<style::VectorSource::Impl> impl_)
    : RenderTileSetSource(std::move(impl_)) {
}

const optional<Tileset>& RenderVectorSource::getTileset() const {
    return static_cast<const style::VectorSource::Impl&>(*baseImpl).tileset;
}

void RenderVectorSource::updateInternal(const Tileset& tileset,
                                        const std::vector<Immutable<style::LayerProperties>>& layers,
                                        const bool needsRendering,
                                        const bool needsRelayout,
                                        const TileParameters& parameters) {
    tilePyramid.update(layers,
                       needsRendering,
                       needsRelayout,
                       parameters,
                       *baseImpl,
                       util::tileSize,
                       tileset.zoomRange,
                       tileset.bounds,
                       [&](const OverscaledTileID& tileID) {
                           return std::make_unique<VectorTile>(tileID, baseImpl->id, parameters, tileset);
                       });
}

std::vector<std::reference_wrapper<Tile>> RenderVectorSource::requestTiles(Immutable<style::Source::Impl> impl,
                                                                           std::vector<CanonicalTileID> tileIds,
                                                                           const std::vector<Immutable<style::LayerProperties>> &layers,
                                                                           const TileParameters &parameters)  {
    auto tileset = getTileset();
    if(tileset == nullopt) {
        return {};
    }
    return tilePyramid.requestTiles(layers, tileIds, *impl, tileset->zoomRange,[&](const OverscaledTileID &tileID) {
        return std::make_unique<VectorTile>(tileID,
                                            baseImpl->id,
                                            parameters,
                                            *tileset);
    });
}

} // namespace mbgl
