package com.mapbox.mapboxsdk.maps.tile;

import android.graphics.Bitmap;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.maps.TileId;
import com.mapbox.mapboxsdk.style.sources.RasterDemSource;

/**
 * <p>Description:</p>
 *
 * @author create at 19/7/2023 21:02 by ron.liu for dji-pilot
 * @version v1.0
 */
public class RasterDemTile {
    private TileId mId;
    private Bitmap mData;
    private boolean isLoadData;

    //四个角的经纬度
    public LatLngBounds bounds;

    /**
     * 保留字段
     */
    public int flag;

    public RasterDemTile(TileId id) {
        mId = id;
    }

    public TileId getId() {
        return mId;
    }

    public void loadDemData(RasterDemSource source) {
        if(mId.isLoaded && source != null) {
            mData = source.queryTileBitmap(mId);
            isLoadData = true;
        }
    }

    public void clearDemData() {
        mData = null;
    }

    public Bitmap getData() {
        return mData;
    }

    public boolean isLoadData() {
        return isLoadData;
    }
}
