package com.konge.pad.data

import androidx.compose.ui.graphics.toArgb
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.konge.pad.ui.theme.BabyBlue
import com.konge.pad.ui.theme.LightGreen
import com.konge.pad.ui.theme.LightWhite
import com.konge.pad.ui.theme.RedOrange
import com.konge.pad.ui.theme.RedPink
import com.konge.pad.ui.theme.Violet

@Entity(tableName = "NOTES")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val content: String,
    val inArchive: Boolean,
    val color: Int = LightWhite.toArgb()
) {
    companion object {
        val noteColors = listOf(LightWhite, RedOrange, RedPink, BabyBlue, Violet, LightGreen)
    }
}