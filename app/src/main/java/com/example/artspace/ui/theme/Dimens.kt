package com.example.artspace.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.R

object Dimens {

    @Composable
    fun paddingScreenMedium(): Dp {
        return LocalContext.current.resources.getDimension(R.dimen.padding_screen_medium).dp
    }

    @Composable
    fun spaceImageToInfo(): Dp {
        return LocalContext.current.resources.getDimension(R.dimen.space_image_to_info).dp
    }

    @Composable
    fun spaceInfoToButtons(): Dp {
        return LocalContext.current.resources.getDimension(R.dimen.space_info_to_buttons).dp
    }

    @Composable
    fun spaceButtonsHorizontal(): Dp {
        return LocalContext.current.resources.getDimension(R.dimen.space_buttons_horizontal).dp
    }

    @Composable
    fun spaceInfoLines(): Dp {
        return LocalContext.current.resources.getDimension(R.dimen.space_info_lines).dp
    }

    @Composable
    fun imageCornerRadius(): Dp {
        return LocalContext.current.resources.getDimension(R.dimen.image_corner_radius).dp
    }

    @Composable
    fun imagePadding(): Dp {
        return LocalContext.current.resources.getDimension(R.dimen.image_padding).dp
    }

    @Composable
    fun buttonHeight(): Dp {
        return LocalContext.current.resources.getDimension(R.dimen.button_height).dp
    }

    @Composable
    fun buttonWidth(): Dp {
        return LocalContext.current.resources.getDimension(R.dimen.button_width).dp
    }

    @Composable
    fun buttonCornerRadius(): Dp {
        return LocalContext.current.resources.getDimension(R.dimen.button_corner_radius).dp
    }

    @Composable
    fun buttonTextSize(): TextUnit {
        return LocalContext.current.resources.getDimension(R.dimen.button_text_size).sp
    }

    @Composable
    fun textSizeTitle(): TextUnit {
        return LocalContext.current.resources.getDimension(R.dimen.text_size_title).sp
    }

    @Composable
    fun textSizeArtist(): TextUnit {
        return LocalContext.current.resources.getDimension(R.dimen.text_size_artist).sp
    }

    @Composable
    fun textSizeYear(): TextUnit {
        return LocalContext.current.resources.getDimension(R.dimen.text_size_year).sp
    }

    @Composable
    fun getAdaptiveImageHeight(): Dp {
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp

        return if (configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT) {
            screenHeight * 0.4f
        } else {
            screenHeight * 0.6f
        }
    }
}