package ro.andreip.triblo.home.data.localSource

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import ro.andreip.triblo.home.data.model.ActionItemDto


internal suspend fun parseJSONWithGson(context: Context, filename: String): List<ActionItemDto> {
    return loadJSONFromAsset(context, filename)?.let {
        val gson = Gson()
        val result = gson.fromJson<List<ActionItemDto>>(it)
        Log.d("Result", result?.toString() ?: "nimic")
        result
    } ?: emptyList()
}

private suspend fun loadJSONFromAsset(context: Context, filename: String): String? {
    return withContext(Dispatchers.IO) {
        try {
            val inputStream = context.assets.open(filename)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            Log.e("Exception", "Json reading exception: ", ex)
            null
        }
    }
}

internal inline fun <reified T> Gson.fromJson(json: String) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)