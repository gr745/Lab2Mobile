package com.example.artspace.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.artspace.data.ArtworkDataSource
import com.example.artspace.model.Artwork
import com.example.artspace.R
import com.example.artspace.ui.theme.ArtSpaceTheme
import com.example.artspace.ui.theme.Dimens

@Composable
fun PortraitLayout(
    currentArtwork: Artwork,
    currentIndex: Int,
    totalArtworks: Int,
    onPreviousClicked: () -> Unit,
    onNextClicked: () -> Unit
) {
    val paddingMedium = Dimens.paddingScreenMedium()
    val spaceImageToInfo = Dimens.spaceImageToInfo()
    val spaceInfoToButtons = Dimens.spaceInfoToButtons()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingMedium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        ArtworkImage(
            artwork = currentArtwork,
            modifier = Modifier.weight(0.6f)
        )

        Spacer(modifier = Modifier.height(spaceImageToInfo))

        ArtworkInfo(
            artwork = currentArtwork,
            modifier = Modifier.weight(0.2f)
        )

        Spacer(modifier = Modifier.height(spaceInfoToButtons))

        NavigationButtons(
            currentIndex = currentIndex,
            totalArtworks = totalArtworks,
            onPreviousClicked = onPreviousClicked,
            onNextClicked = onNextClicked,
            modifier = Modifier.weight(0.2f)
        )
    }
}

@Composable
fun LandscapeLayout(
    currentArtwork: Artwork,
    currentIndex: Int,
    totalArtworks: Int,
    onPreviousClicked: () -> Unit,
    onNextClicked: () -> Unit
) {
    val paddingMedium = Dimens.paddingScreenMedium()
    val spaceImageToText = Dimens.spaceImageToInfo()

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingMedium),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ArtworkImage(
            artwork = currentArtwork,
            modifier = Modifier.weight(0.5f)
        )


        Spacer(modifier = Modifier.width(spaceImageToText))

        Column(
            modifier = Modifier.weight(0.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ArtworkInfo(artwork = currentArtwork)

            Spacer(modifier = Modifier.height(Dimens.buttonHeight()))

            NavigationButtons(
                currentIndex = currentIndex,
                totalArtworks = totalArtworks,
                onPreviousClicked = onPreviousClicked,
                onNextClicked = onNextClicked
            )
        }
    }
}

@Composable
fun ArtworkImage(
    artwork: Artwork,
    modifier: Modifier = Modifier
) {
    val imageDescription = stringResource(artwork.titleResId)
    val cornerRadius = Dimens.imageCornerRadius()
    val imagePadding = Dimens.imagePadding()

    Image(
        painter = painterResource(id = artwork.imageResId),
        contentDescription = imageDescription,
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.getAdaptiveImageHeight())
            .padding(imagePadding)
            .clip(RoundedCornerShape(cornerRadius))
    )
}

@Composable
fun ArtworkInfo(
    artwork: Artwork,
    modifier: Modifier = Modifier
) {
    val paddingMedium = Dimens.paddingScreenMedium()
    val spaceInfoLines = Dimens.spaceInfoLines()
    val textSizeTitle = Dimens.textSizeTitle()
    val textSizeArtist = Dimens.textSizeArtist()
    val textSizeYear = Dimens.textSizeYear()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(paddingMedium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = artwork.titleResId),
            style = MaterialTheme.typography.headlineMedium,
            fontSize = textSizeTitle,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(spaceInfoLines))

        Text(
            text = stringResource(id = artwork.artistResId),
            style = MaterialTheme.typography.titleMedium,
            fontSize = textSizeArtist,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(spaceInfoLines / 2))

        Text(
            text = stringResource(id = artwork.yearResId),
            style = MaterialTheme.typography.bodyLarge,
            fontSize = textSizeYear
        )
    }
}

@Composable
fun NavigationButtons(
    currentIndex: Int,
    totalArtworks: Int,
    onPreviousClicked: () -> Unit,
    onNextClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val buttonHeight = Dimens.buttonHeight()
    val buttonWidth = Dimens.buttonWidth()
    val buttonCornerRadius = Dimens.buttonCornerRadius()
    val buttonSpacing = Dimens.spaceButtonsHorizontal()
    val buttonTextSize = Dimens.buttonTextSize()
    val paddingMedium = Dimens.paddingScreenMedium()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = paddingMedium),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onPreviousClicked,
            enabled = currentIndex > 0,
            modifier = Modifier
                .width(buttonWidth)
                .height(buttonHeight)
                .semantics {
                    contentDescription = "Previous button"
                },
            shape = RoundedCornerShape(buttonCornerRadius)
        ) {
            Text(
                text = stringResource(id = R.string.previous),
                fontSize = buttonTextSize,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.width(buttonSpacing))

        Button(
            onClick = onNextClicked,
            enabled = currentIndex < totalArtworks - 1,
            modifier = Modifier
                .width(buttonWidth)
                .height(buttonHeight)
                .semantics {
                    contentDescription = "Next button"
                },
            shape = RoundedCornerShape(buttonCornerRadius)
        ) {
            Text(
                text = stringResource(id = R.string.next),
                fontSize = buttonTextSize,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun ArtSpaceScreen() {
    var currentIndex by rememberSaveable { mutableStateOf(0) }
    val artworks = ArtworkDataSource.artworks
    val currentArtwork = artworks[currentIndex]

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (isLandscape) {
            LandscapeLayout(
                currentArtwork = currentArtwork,
                currentIndex = currentIndex,
                totalArtworks = artworks.size,
                onPreviousClicked = { if (currentIndex > 0) currentIndex-- },
                onNextClicked = { if (currentIndex < artworks.size - 1) currentIndex++ }
            )
        } else {
            PortraitLayout(
                currentArtwork = currentArtwork,
                currentIndex = currentIndex,
                totalArtworks = artworks.size,
                onPreviousClicked = { if (currentIndex > 0) currentIndex-- },
                onNextClicked = { if (currentIndex < artworks.size - 1) currentIndex++ }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceScreenPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}