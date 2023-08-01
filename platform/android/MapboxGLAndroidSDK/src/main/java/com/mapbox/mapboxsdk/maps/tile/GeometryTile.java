package com.mapbox.mapboxsdk.maps.tile;

import androidx.annotation.Nullable;
import androidx.annotation.Size;
import com.mapbox.geojson.Feature;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.maps.TileId;
import com.mapbox.mapboxsdk.style.expressions.Expression;
import com.mapbox.mapboxsdk.style.sources.VectorSource;
import java.util.List;

/**
 * <p>Description:</p>
 *
 * @author create at 19/7/2023 20:57 by ron.liu for dji-pilot
 * @version v1.0
 */
public class GeometryTile {
    private TileId mId;
    private List<Feature> mFeatures;
    private VectorSource mSource;

    private boolean isFeatureLoad;
    private String[] mLoadLayerIds;
    private Expression mLoadFilter;

    //四个角的经纬度
    public LatLngBounds bounds;

    public GeometryTile(VectorSource source, TileId id) {
        mSource = source;
        mId = id;
    }

    public void loadFeatures(@Size(min = 1) String[] sourceLayerIds,
                             @Nullable Expression filter) {
        if(mId.isLoaded) {
            mFeatures = mSource.queryTileFeatures(sourceLayerIds, mId, filter);
            mLoadLayerIds = sourceLayerIds;
            mLoadFilter = filter;
            isFeatureLoad = true;
        }
    }

    public List<Feature> getFeatures() {
        return mFeatures;
    }

    public TileId getId() {
        return mId;
    }

    public String[] getLoadLayerIds() {
        return mLoadLayerIds;
    }

    public Expression getLoadFilter() {
        return mLoadFilter;
    }

    public boolean isLoaded() {
        return isFeatureLoad;
    }
}
