#pragma once

#include <mbgl/renderer/sources/render_tile_source.hpp>
#include <mbgl/renderer/tile_pyramid.hpp>
#include <mbgl/style/sources/vector_source_impl.hpp>

namespace mbgl {

class RenderVectorSource final : public RenderTileSetSource {
public:
    explicit RenderVectorSource(Immutable<style::VectorSource::Impl>);
    std::vector<std::reference_wrapper<Tile>> findOrCreateTile(Immutable<style::Source::Impl>,
                                             const std::vector<Immutable<style::LayerProperties>>&,
                                             const TileParameters&);
private:
    void updateInternal(const Tileset&,
                        const std::vector<Immutable<style::LayerProperties>>&,
                        bool needsRendering,
                        bool needsRelayout,
                        const TileParameters&) override;

    const optional<Tileset>& getTileset() const override;
};

} // namespace mbgl
