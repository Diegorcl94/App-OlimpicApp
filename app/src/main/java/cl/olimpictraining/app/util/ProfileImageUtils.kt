package cl.olimpictraining.app.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

object ProfileImageUtils {

    private const val MAX_DIMENSION = 512
    private const val JPEG_QUALITY = 82
    const val CUSTOM_AVATAR_FILENAME = "custom_avatar.jpg"

    fun compressAndSaveImage(context: Context, uri: Uri): Boolean {
        return try {
            val inputStream: InputStream = context.contentResolver.openInputStream(uri) ?: return false
            val originalBitmap = BitmapFactory.decodeStream(inputStream) ?: return false
            inputStream.close()

            val rotatedBitmap = fixRotation(context, uri, originalBitmap)

            val width = rotatedBitmap.width
            val height = rotatedBitmap.height
            val scaledBitmap = if (width > MAX_DIMENSION || height > MAX_DIMENSION) {
                val ratio = width.toFloat() / height.toFloat()
                val (newWidth, newHeight) = if (ratio > 1f) {
                    MAX_DIMENSION to (MAX_DIMENSION / ratio).toInt()
                } else {
                    (MAX_DIMENSION * ratio).toInt() to MAX_DIMENSION
                }
                Bitmap.createScaledBitmap(rotatedBitmap, newWidth, newHeight, true)
            } else {
                rotatedBitmap
            }

            val outputFile = File(context.filesDir, CUSTOM_AVATAR_FILENAME)
            val outputStream = FileOutputStream(outputFile)
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, JPEG_QUALITY, outputStream)
            outputStream.flush()
            outputStream.close()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    private fun fixRotation(context: Context, uri: Uri, bitmap: Bitmap): Bitmap {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri) ?: return bitmap
            val exif = ExifInterface(inputStream)
            val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
            inputStream.close()

            val matrix = Matrix()
            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> matrix.postRotate(90f)
                ExifInterface.ORIENTATION_ROTATE_180 -> matrix.postRotate(180f)
                ExifInterface.ORIENTATION_ROTATE_270 -> matrix.postRotate(270f)
                else -> return bitmap
            }
            Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        } catch (e: Exception) {
            bitmap
        }
    }

    fun getCustomAvatarFile(context: Context): File? {
        val file = File(context.filesDir, CUSTOM_AVATAR_FILENAME)
        return if (file.exists()) file else null
    }
}
