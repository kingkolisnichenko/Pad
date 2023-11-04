@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.konge.pad

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import com.konge.pad.data.Note

val DefaultLight = Color(0xffcfd8dc)
val DefaultDark = Color(0xff607d8b)

// dark
val NORDIC = Color(0xff183D3D)
val CUTTY_SARK = Color(0xff5C8374)
val FRENCH_PLUM = Color(0xff880e4f)
val PITCH_VIOLET = Color(0xff4a148c)
val LIQUID_DENIM = Color(0xff303f9f)
val NASTURTIUM_FLOWER = Color(0xffe64a19)
val WITCH_SOUP = Color(0xff662549)
val MILANO_RED = Color(0xffA13333)
val CALGAR_BLUE = Color(0xff0288d1)
val BREEN = Color(0xff7C5C36)

// light
val NORDIC_LIGHT = Color(0xffCCDFDF)
val CUTTY_SARK_LIGHT = Color(0xffDFD0DD)
val FRENCH_PLUM_LIGHT = Color(0xffF7BAD8)
val PITCH_VIOLET_LIGHT = Color(0xFFFFAB91)
val LIQUID_DENIM_LIGHT = Color(0xff999ED1)
val NASTURTIUM_FLOWER_LIGHT = Color(0xffF8D4CD)
val WITCH_SOUP_LIGHT = Color(0xFFBD87A3)
val MILANO_RED_LIGHT = Color(0xFF82B1FF)
val CALGAR_BLUE_LIGHT = Color(0xffA7D8F4)
val BREEN_LIGHT = Color(0xffFDD399)

@Composable
@ReadOnlyComposable
fun defaultColor() = DefaultLight

@Composable
fun noteColors() =listOf(
        DefaultLight,
        NORDIC_LIGHT,
        CUTTY_SARK_LIGHT,
        FRENCH_PLUM_LIGHT,
        FRENCH_PLUM_LIGHT,
        PITCH_VIOLET_LIGHT,
        LIQUID_DENIM_LIGHT,
        NASTURTIUM_FLOWER_LIGHT,
        WITCH_SOUP_LIGHT,
        MILANO_RED_LIGHT,
        CALGAR_BLUE_LIGHT,
        BREEN_LIGHT
)
//    if (isSystemInDarkTheme())
//
//    else listOf(
//        DefaultLight,
//        NORDIC_LIGHT,
//        CUTTY_SARK_LIGHT,
//        FRENCH_PLUM_LIGHT,
//        FRENCH_PLUM_LIGHT,
//        PITCH_VIOLET_LIGHT,
//        LIQUID_DENIM_LIGHT,
//        NASTURTIUM_FLOWER_LIGHT,
//        WITCH_SOUP_LIGHT,
//        MILANO_RED_LIGHT,
//        CALGAR_BLUE_LIGHT,
//        BREEN_LIGHT
//    )

@Composable
fun noteColor(note: Note) = Color(note.colorLight)
