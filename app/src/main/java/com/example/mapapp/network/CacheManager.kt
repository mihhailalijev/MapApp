package com.example.mapapp.network

import android.graphics.Bitmap
import android.util.LruCache
import com.example.mapapp.tile.Tile

class CacheManager {

    companion object {
        val MEMORY_LRU_CACHE by lazy { CacheManager() }
    }

    private var maxMemorySize = 128 * 1024 * 1024 // 128 mb

    var memoryLruCache = object : LruCache<Tile, Bitmap>(maxMemorySize) {
        override fun sizeOf(key: Tile, value: Bitmap): Int {
            return value.byteCount
        }
    }

    fun getBitmapFromMemCache(key: Tile): Bitmap? {
        return memoryLruCache.get(key)
    }

    fun addBitmapToMemoryCache(key: Tile, bitmap: Bitmap) {
        memoryLruCache.put(key, bitmap)
    }

    fun getCacheSize(): Int {
        return memoryLruCache.size()
    }

    fun getCacheElementCount(): Int {
        return memoryLruCache.snapshot().size
    }

    fun contains(key: Tile): Boolean {
        return getBitmapFromMemCache(key) != null
    }
}