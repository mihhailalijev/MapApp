package com.example.mapapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mapapp.tile.Direction.*
import com.example.mapapp.tile.Tiles
import kotlinx.android.synthetic.main.activity_map.*

class MapActivity : AppCompatActivity() {

    lateinit var debugButtons: List<Button>
    var tiles = Tiles.TILES

    var centerTile = tiles.centerTile
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        displayMap()

        debugButtons = listOf(upButton, rightButton, downButton, leftButton)

        for (button in debugButtons) {
            button.setOnClickListener {

                Thread({
                    when (button.id) {
                        R.id.upButton -> tiles.moveTileCoordinateByY(centerTile, true)
                        R.id.downButton -> tiles.moveTileCoordinateByY(centerTile, false)
                        R.id.leftButton -> tiles.moveTileCoordinateByX(centerTile, false)
                        R.id.rightButton -> tiles.moveTileCoordinateByX(centerTile, true)
                    }

                    displayMap()
                }).start()
            }
        }
    }

    private fun displayMap() {

        Tiles.TILES.calculateAndLoadTiles()

        for (Pair in tiles.tilesHashMap){

            var tempBitmap = Pair.value

            Log.i("DEBUG","displayTiles, current tile is ${Pair.key.x} / ${Pair.key.y} / ${Pair.key.direction}")

            when (Pair.key.direction) {
                CENTER -> ivC.setImageBitmap(tempBitmap)
                N -> ivN.setImageBitmap(tempBitmap)
                NE -> ivNE.setImageBitmap(tempBitmap)
                E -> ivE.setImageBitmap(tempBitmap)
                SE -> ivSE.setImageBitmap(tempBitmap)
                S -> ivS.setImageBitmap(tempBitmap)
                SW -> ivSW.setImageBitmap(tempBitmap)
                W -> ivW.setImageBitmap(tempBitmap)
                NW -> ivNW.setImageBitmap(tempBitmap)
            }
        }
    }
}
