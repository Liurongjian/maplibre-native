package com.mapbox.mapboxsdk.maps.renderer

import android.view.SurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

interface ICustomRenderer {

    fun onViewCreate(view: SurfaceView)

    fun onSurfaceCreated(gl: GL10?, glConfig: EGLConfig?)

    fun onSurfaceChanged(p0: GL10?, width: Int, height: Int)

    fun onDrawFrame(gl: GL10?)
}