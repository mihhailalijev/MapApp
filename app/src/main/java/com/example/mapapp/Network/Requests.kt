package com.example.mapapp.Network

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class Requests (context: Context) {

    private val _context = context

    fun getGoogleImage(view: ImageView) {
        val queue = Volley.newRequestQueue(_context)
        val url = "http://open.mapquestapi.com/staticmap/v4/getplacemap"

        // Request a string response from the provided URL.
        val imageRequest = ImageRequest(url, Response.Listener<Bitmap> { response ->
            view.setImageBitmap(response)
        }, 500,500, null,null)

        queue.add(imageRequest)
    }
}