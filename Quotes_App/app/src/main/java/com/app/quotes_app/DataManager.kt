package com.app.quotes_app

import android.content.Context
import java.nio.charset.Charset

object DataManager {
    // var data = emptyArray<Quote>()

    fun loadAssetsFromFile(context: Context) {
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()


    } // End of loadAssetsFromFile
} // End of DataManager
