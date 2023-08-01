package com.mapbox.mapboxsdk.maps;

import androidx.annotation.NonNull;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import java.util.Objects;

/**
 * <p>Description:</p>
 *
 * @author create at 18/7/2023 15:28 by ron.liu for dji-pilot
 * @version v1.0
 */
public class TileId {
    public TileId(int x, int y, int z, int overscaledZ, int wrap, boolean isLoaded) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.overscaledZ = overscaledZ;
        this.wrap = wrap;
        this.isLoaded = isLoaded;
    }
    public final int overscaledZ;
    public final int wrap;
    public final int x;
    public final int y;
    public final int z;
    public boolean isLoaded;

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, overscaledZ, wrap);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || !(o instanceof TileId)) {
            return false;
        }
        TileId that = (TileId) o;
        return x == that.x && y == that.y && z == that.z && overscaledZ == that.overscaledZ && wrap == that.wrap;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("{%d, %d, %d, %b}", x, y, z, isLoaded);
    }

    public LatLngBounds getBounds() {
        return LatLngBounds.from(z, x, y);
    }
}
