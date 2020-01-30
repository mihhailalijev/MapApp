package com.example.mapapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mapapp.network.Requests
import com.example.mapapp.tile.Direction
import com.example.mapapp.tile.Tile
import com.example.mapapp.tile.Tiles
import kotlinx.android.synthetic.main.activity_map.*

class MapActivity : AppCompatActivity() {

  //  private val initTile = (Direction.CENTER, 1164, 601) //lat =59.452352 long= 24.702039
    private var myRequests = Requests()
    private var tileList = Tiles.TILES.getTileList()
    lateinit var debugButtons: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)




        displayTiles(tileList)


        debugButtons = listOf(upButton, rightButton, downButton, leftButton)

        for(button in debugButtons) {
            button.setOnClickListener {

                when(button.id) {
                    R.id.upButton -> Tiles.TILES.moveTileCoordinate(tileList[0], false, true, false, true)
                    R.id.downButton -> Tiles.TILES.moveTileCoordinate(tileList[0], false, true, false, false)
                    R.id.leftButton -> Tiles.TILES.moveTileCoordinate(tileList[0], true, false, false, false)
                    R.id.rightButton -> Tiles.TILES.moveTileCoordinate(tileList[0], true, false, true, false)
                }


                Tiles.TILES.calculateTilesFromCenter(tileList[0])
                displayTiles(tileList)

            }
        }


    }

    private fun displayTiles(tiles: List<Tile>) {

        for (tile in tiles) {

            Log.i("DEBUG", "displayTiles, current tile is ${tile.x} / ${tile.y} / ${tile.direction}")

            when(tile.direction) {
                Direction.CENTER -> ivC.setImageBitmap(myRequests.getBitmap(tile))
                Direction.N -> ivN.setImageBitmap(myRequests.getBitmap(tile))
                Direction.NE ->ivNE.setImageBitmap(myRequests.getBitmap(tile))
                Direction.E -> ivE.setImageBitmap(myRequests.getBitmap(tile))
                Direction.SE -> ivSE.setImageBitmap(myRequests.getBitmap(tile))
                Direction.S -> ivS.setImageBitmap(myRequests.getBitmap(tile))
                Direction.SW -> ivSW.setImageBitmap(myRequests.getBitmap(tile))
                Direction.W -> ivW.setImageBitmap(myRequests.getBitmap(tile))
                Direction.NW -> ivNW.setImageBitmap(myRequests.getBitmap(tile))
            }
        }
    }


}
