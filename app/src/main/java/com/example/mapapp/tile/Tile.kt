package com.example.mapapp.tile

import android.graphics.Bitmap
import android.util.Log
import com.example.mapapp.network.Requests
import com.example.mapapp.tile.Direction.*

data class Tile(var direction: Direction,var x: Int, var y: Int)
enum class Direction { N, S, W, E, NW, NE, SE, SW, CENTER }

class Tiles {

    companion object {
        val TILES by lazy { Tiles() }
    }

    private var requests = Requests()

    var centerTile : Tile = Tile(CENTER, 1164, 601)
    var tilesHashMap: HashMap<Tile, Bitmap?> = HashMap()

    private fun initTiles() {
        tilesHashMap[Tile(CENTER, 1164, 601)] = null
        tilesHashMap[Tile(N, 1164, 601)] = null
        tilesHashMap[Tile(NE, 1164, 601)] = null
        tilesHashMap[Tile(E, 1164, 601)] = null
        tilesHashMap[Tile(SE, 1164, 601)] = null
        tilesHashMap[Tile(S, 1164, 601)] = null
        tilesHashMap[Tile(SW, 1164, 601)] = null
        tilesHashMap[Tile(W, 1164, 601)] = null
        tilesHashMap[Tile(NW, 1164, 601)] = null

        calculateTileCoordinatesFromCenter()

        // Work with calculated tile's coordinates
        for (tile in tilesHashMap.entries) {
            tile.setValue(requests.getBitmap(tile.key))
        }

    }

    fun calculateTileCoordinatesFromCenter() {

        for (tile in tilesHashMap.keys) {
            tile.x = centerTile.x
            tile.y = centerTile.y

            when (tile.direction) {
                N -> moveTileCoordinateByY(tile, true)
                E -> moveTileCoordinateByX(tile, true)
                S -> moveTileCoordinateByY(tile, false)
                W -> moveTileCoordinateByX(tile,false)

                NE -> moveTileCoordinateByXY(tile, true, true)
                SE -> moveTileCoordinateByXY(tile, true, false)
                SW -> moveTileCoordinateByXY(tile, false, false)
                NW -> moveTileCoordinateByXY(tile, false, true)
                else -> Log.i("TILE", "UNKNOWN TILE / CENTER TILE")
            }
        }
    }

    fun calculateAndLoadTiles() {
        calculateTileCoordinatesFromCenter()
        loadTiles()
    }

    private fun loadTiles() {
        for (tile in tilesHashMap.entries) {
            tile.setValue(requests.getBitmap(tile.key))
        }
    }

    fun moveTileCoordinateByXY(tile: Tile, increment_x: Boolean, increment_y: Boolean) {
            if(increment_x) tile.x++ else tile.x--
            if(increment_y) tile.y-- else tile.y++
    }
    fun moveTileCoordinateByX(tile: Tile, increment_x: Boolean) =  if(increment_x) tile.x++ else tile.x--
    fun moveTileCoordinateByY(tile: Tile, increment_y: Boolean) =  if(increment_y) tile.y-- else tile.y++

    init {
        initTiles()
    }
}

