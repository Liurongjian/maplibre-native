package com.mapbox.mapboxsdk.maps.tile;

import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.maps.TileId;

/**
 * <p>Description:</p>
 *
 * @author create at 15/10/2024 14:57 by ron.liu for dji-pilot
 * @version v1.0
 */
public class VectorTile {
    private TileId mId;
    private boolean isLoad;
    private byte[] mData;
    //四个角的经纬度
    public LatLngBounds bounds;

    /**
     * 保留字段
     */
    public int flag;

    public VectorTile(TileId id) {
        mId = id;
    }

    public void setData(byte[] data) {
        mData = data;
        isLoad = true;
    }

    public boolean isLoad() {
        return isLoad;
    }

    public byte[] getData() {
        return mData;
    }

    public TileId getId() {
        return mId;
    }
}
