package com.example.mapapp.tile

import android.util.Log

data class Tile(var direction: Direction,var x: Int, var y: Int, var zoom: Int = 11, var index: Int = 0)
enum class Direction { N, S, W, E, NW, NE, SE, SW, CENTER } // Directions (move vectors) + center pos.

class Tiles {

    companion object {
        val TILES by lazy { Tiles() }
    }

    private val tileList = mutableListOf<Tile>(
        Tile(Direction.CENTER, 1164, 601),
        Tile(Direction.N, 1164, 601),
        Tile(Direction.NE, 1164, 601),
        Tile(Direction.E, 1164, 601),
        Tile(Direction.SE, 1164, 601),
        Tile(Direction.S, 1164, 601),
        Tile(Direction.SW, 1164, 601),
        Tile(Direction.W, 1164, 601),
        Tile(Direction.NW, 1164, 601)
    )

    fun getTileList(): List<Tile>{
        calculateTilesFromCenter(tileList[0])
        return tileList
    }



    private fun addTile(tile: Tile) {
        var tempTile = tile
        tempTile.index++
        tileList.add(tempTile)
    }

    private fun getTileByDirection(direction: Direction, index: Int): Tile? {

        for (tile in tileList.filter { it.direction == direction }) {
            if (tile.index == index) {
                return tile
            }
        }
        Log.i("NULL", "Tile not found, get ready for NPE")
        return null
    }

    private fun moveTilesByDirection(direction: Direction) {

        for (tile in tileList.filter { it.direction == direction }) {
            if (tile.direction == direction) {

                when (direction) {
                    Direction.N -> moveTileCoordinate(tile, false, true, false,  true)
                    Direction.NE -> moveTileCoordinate(tile, true, true, true, true)
                    Direction.E -> moveTileCoordinate(tile, true, false, true, false)
                    Direction.SE -> moveTileCoordinate(tile, true, true, true, false)
                    Direction.S -> moveTileCoordinate(tile, false, true, false, false)
                    Direction.SW -> moveTileCoordinate(tile, true, true, false, false )
                    Direction.W -> moveTileCoordinate(tile, true, false, false, false)
                    Direction.NW -> moveTileCoordinate(tile, true, true, false, true)
                }

            }
        }

    }

    fun calculateTilesFromCenter(centeredTile: Tile) {

        for (tile in tileList) {
           tile.x = centeredTile.x
           tile.y = centeredTile.y

            when (tile.direction) {
                Direction.N -> moveTileCoordinate(tile, false, true, false,  true)
                Direction.NE -> moveTileCoordinate(tile, true, true, true, true)
                Direction.E -> moveTileCoordinate(tile, true, false, true, true)
                Direction.SE -> moveTileCoordinate(tile, true, true, true, false)
                Direction.S -> moveTileCoordinate(tile, false, true, true, false)
                Direction.SW -> moveTileCoordinate(tile, true, true, false, false)
                Direction.W -> moveTileCoordinate(tile, true, false, false, true)
                Direction.NW -> moveTileCoordinate(tile, true, true, false, true)
            }
        }
    }

     fun moveTileCoordinate(tile: Tile, x: Boolean, y: Boolean, increment_x: Boolean, increment_y: Boolean) {

        if(x && y) {
            if(increment_x) tile.x++ else tile.x--
            if(increment_y) tile.y-- else tile.y++
            return
        }

        if(x) {
            if(increment_x) tile.x++ else tile.x--
            return
        }

        if(y) {
            if(increment_y) tile.y-- else tile.y++
            return
        }

    }



}

