package com.example.mapapp.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.mapapp.tile.Tile
import java.net.URL
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Requests {
  //  private val tilesMap = Tiles.TILES.tilesHashMap
  //  private val cacheManager = CacheManager.MEMORY_LRU_CACHE
    private val logTag = "DEBUG"

    private val myService: ExecutorService = Executors.newFixedThreadPool(2)
    private val baseUrl = "https://d.tile.openstreetmap.de/"


    fun getBitmap(coordinates: Tile):Bitmap {

        var result = myService.submit(Callable<Bitmap> { requestCenteredMapImage(coordinates)})
        return result.get()

//
//        if (cacheManager.contains(coordinates)) {
//            return cacheManager.getBitmapFromMemCache(coordinates)!! // sry
//        }
    }

    private fun requestCenteredMapImage(tile: Tile):Bitmap? {
        var url = URL("${baseUrl}11/${tile.x}/${tile.y}.png")

        Log.i(logTag, "Making request to: $url")

        val myConnection = url.openConnection()
        val input = myConnection.getInputStream()

        if(input == null) return null

        return BitmapFactory.decodeStream(input)
    }
}

