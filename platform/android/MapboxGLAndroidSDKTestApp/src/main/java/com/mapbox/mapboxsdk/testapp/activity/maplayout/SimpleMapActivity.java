package com.mapbox.mapboxsdk.testapp.activity.maplayout;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;

import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.log.Logger;
import com.mapbox.mapboxsdk.maps.Image;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.maps.TileId;
import com.mapbox.mapboxsdk.testapp.R;
import com.mapbox.mapboxsdk.testapp.utils.NavUtils;
import java.nio.ByteBuffer;

/**
 * Test activity showcasing a simple MapView without any MapboxMap interaction.
 */
public class SimpleMapActivity extends AppCompatActivity {

  private MapView mapView;
  private MapboxMap map;
  private String TAG = "SimpleMapActivity";
  private ImageView iv;
  private String key = "cikR6pn5PCrTe2nF2XPm";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_map_simple);
    mapView = findViewById(R.id.mapView);
    mapView.onCreate(savedInstanceState);
    mapView.getMapAsync(mapboxMap -> {
      map = mapboxMap;
      String style = "https://api.maptiler.com/maps/outdoor-v2/style.json?key=" + key;
      map.setStyle(new Style.Builder().fromUri(style));
      map.setCameraPosition(new CameraPosition.Builder().target(new LatLng( 22.557342, 113.864951)).zoom(14).build());
      map.setTileDataCallback((tileId, code, data) -> {
        Logger.d(TAG, "tile: " + tileId + " code: " + code + " size " + data.length);
        if(tileId.type == 2 && code == 0 || code ==1) {
          runOnUiThread(() -> {
            Bitmap bp = BitmapFactory.decodeByteArray(data, 0, data.length);
            if (bp.getConfig() != Bitmap.Config.ARGB_8888) {
              bp = bp.copy(Bitmap.Config.ARGB_8888, false);
            }
            ByteBuffer buffer = ByteBuffer.allocate(bp.getByteCount());
            bp.copyPixelsToBuffer(buffer);
            buffer.array();
            iv.setImageBitmap(bp);
          });
        }
      });
    });
    iv = findViewById(R.id.req_dem_src);
    findViewById(R.id.req_tile_btn).setOnClickListener(view -> {
      String pbfURL = "https://api.maptiler.com/tiles/v3/{z}/{x}/{y}.pbf?key=" + key;
      String demURL = "https://api.maptiler.com/tiles/terrain-rgb-v2/{z}/{x}/{y}.webp?key=" + key;
      TileId pbfTid = new TileId(26748, 14275, 15, 1);
      TileId demTid = new TileId(26748, 14275, 15, 2);
      map.requestTile(pbfURL,pbfTid);
      map.requestTile(demURL, demTid);


      pbfTid = new TileId(26748, 14276, 15, 1);
      demTid = new TileId(13375, 7138, 14, 2);
      map.requestTile(pbfURL,pbfTid);
      // map.requestTile(demURL, demTid);

      pbfTid = new TileId(26748, 14277, 15, 1);
      demTid = new TileId(13375, 7139, 14, 2);
      map.requestTile(pbfURL,pbfTid);
      // map.requestTile(demURL, demTid);

      pbfTid = new TileId(26748, 14278, 15, 1);
      demTid = new TileId(13375, 7140, 14, 2);
      map.requestTile(pbfURL,pbfTid);
      map.requestTile(demURL, demTid);
    });
  }

  @Override
  protected void onStart() {
    super.onStart();
    mapView.onStart();
  }

  @Override
  protected void onResume() {
    super.onResume();
    mapView.onResume();
  }

  @Override
  protected void onPause() {
    super.onPause();
    mapView.onPause();
  }

  @Override
  protected void onStop() {
    super.onStop();
    mapView.onStop();
  }

  @Override
  public void onLowMemory() {
    super.onLowMemory();
    mapView.onLowMemory();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mapView.onDestroy();
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    mapView.onSaveInstanceState(outState);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        // activity uses singleInstance for testing purposes
        // code below provides a default navigation when using the app
        onBackPressed();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onBackPressed() {
    // activity uses singleInstance for testing purposes
    // code below provides a default navigation when using the app
    NavUtils.navigateHome(this);
  }
}
