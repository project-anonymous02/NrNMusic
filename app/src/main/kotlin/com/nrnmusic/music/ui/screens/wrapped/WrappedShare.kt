/**
 * NrN Music Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package com.nrnmusic.music.ui.screens.wrapped

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.LinearGradient
import android.graphics.Typeface
import android.widget.Toast
import androidx.core.content.FileProvider
import com.nrnmusic.music.R
import java.io.File
import java.io.FileOutputStream

fun shareWrappedRecap(context: Context, state: WrappedState) {
    runCatching {
        val width = 1080
        val height = 1350
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val background = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            shader = LinearGradient(0f, 0f, width.toFloat(), height.toFloat(), 0xFF111827.toInt(), 0xFF3B245C.toInt(), Shader.TileMode.CLAMP)
        }
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), background)

        fun text(value: String, x: Float, y: Float, size: Float, color: Int, bold: Boolean = false) {
            val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                this.color = color
                textSize = size
                typeface = if (bold) Typeface.create("sans-serif", Typeface.BOLD) else Typeface.create("sans-serif", Typeface.NORMAL)
            }
            canvas.drawText(value.take(48), x, y, paint)
        }

        val white = android.graphics.Color.WHITE
        val muted = 0xFFCFD4E2.toInt()
        text("NRNMUSIC  /  LISTENING RECAP", 76f, 132f, 34f, muted, true)
        text("Your listening story", 76f, 250f, 72f, white, true)
        text("${state.totalMinutes} minutes", 76f, 410f, 92f, white, true)
        text("LISTENED TO MUSIC", 80f, 466f, 28f, muted, true)

        canvas.drawRoundRect(RectF(64f, 540f, 1016f, 785f), 36f, 36f, Paint(Paint.ANTI_ALIAS_FLAG).apply { color = 0x33FFFFFF })
        text("TOP ARTIST", 104f, 620f, 28f, muted, true)
        text(state.topArtists.firstOrNull()?.artist?.name ?: "Not available yet", 104f, 700f, 54f, white, true)

        canvas.drawRoundRect(RectF(64f, 825f, 1016f, 1080f), 36f, 36f, Paint(Paint.ANTI_ALIAS_FLAG).apply { color = 0x33FFFFFF })
        text("TOP SONG", 104f, 910f, 28f, muted, true)
        text(state.topSongs.firstOrNull()?.title ?: "Not available yet", 104f, 990f, 48f, white, true)
        text("${state.uniqueSongCount} songs  •  ${state.uniqueArtistCount} artists", 76f, 1190f, 34f, muted)
        text("Made with NrNMusic", 76f, 1280f, 28f, muted)

        val directory = File(context.cacheDir, "nrnmusic-share").apply { mkdirs() }
        val file = File(directory, "listening-recap.png")
        FileOutputStream(file).use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
        bitmap.recycle()

        val uri = FileProvider.getUriForFile(context, "${context.packageName}.FileProvider", file)
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            type = "image/png"
            putExtra(Intent.EXTRA_STREAM, uri)
            clipData = ClipData.newUri(context.contentResolver, "NrNMusic listening recap", uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(sendIntent, context.getString(R.string.share_recap)))
    }.onFailure {
        Toast.makeText(context, context.getString(R.string.share_recap_failed), Toast.LENGTH_SHORT).show()
    }
}
