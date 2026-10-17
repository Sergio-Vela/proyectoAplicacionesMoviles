
package com.example.clasedesmov2026.ui.profile.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import androidx.exifinterface.media.ExifInterface

fun cargarBitmapOrientado(
    context: Context,
    uri: Uri
): Bitmap? {
    return try {
        val orientacion = context.contentResolver
            .openInputStream(uri)
            ?.use { input ->
                ExifInterface(input).getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL
                )
            } ?: ExifInterface.ORIENTATION_NORMAL

        val bitmap = context.contentResolver
            .openInputStream(uri)
            ?.use { input ->
                BitmapFactory.decodeStream(input)
            } ?: return null

        val matriz = Matrix()

        when (orientacion) {
            ExifInterface.ORIENTATION_ROTATE_90 ->
                matriz.postRotate(90f)

            ExifInterface.ORIENTATION_ROTATE_180 ->
                matriz.postRotate(180f)

            ExifInterface.ORIENTATION_ROTATE_270 ->
                matriz.postRotate(270f)

            ExifInterface.ORIENTATION_FLIP_HORIZONTAL ->
                matriz.postScale(-1f, 1f)

            ExifInterface.ORIENTATION_FLIP_VERTICAL ->
                matriz.postScale(1f, -1f)

            ExifInterface.ORIENTATION_TRANSPOSE -> {
                matriz.postRotate(90f)
                matriz.postScale(-1f, 1f)
            }

            ExifInterface.ORIENTATION_TRANSVERSE -> {
                matriz.postRotate(270f)
                matriz.postScale(-1f, 1f)
            }
        }

        if (
            orientacion == ExifInterface.ORIENTATION_NORMAL ||
            orientacion == ExifInterface.ORIENTATION_UNDEFINED
        ) {
            bitmap
        } else {
            Bitmap.createBitmap(
                bitmap,
                0,
                0,
                bitmap.width,
                bitmap.height,
                matriz,
                true
            )
        }
    } catch (e: Exception) {
        null
    }
}
