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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.data.ArtworkDataSource
import com.example.artspace.model.Artwork
import com.example.artspace.R
import com.example.artspace.ui.theme.ArtSpaceTheme

@Composable
fun ArtSpaceScreen() {
    // rememberSaveable - сохраняет состояние при повороте экрана (важно для задания!)
    var currentIndex by rememberSaveable { mutableStateOf(0) }
    val artworks = ArtworkDataSource.artworks
    val currentArtwork = artworks[currentIndex]

    // Определяем ориентацию для адаптивного макета
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (isLandscape) {
            // Ландшафтная ориентация - горизонтальное расположение
            LandscapeLayout(
                currentArtwork = currentArtwork,
                currentIndex = currentIndex,
                totalArtworks = artworks.size,
                onPreviousClicked = { if (currentIndex > 0) currentIndex-- },
                onNextClicked = { if (currentIndex < artworks.size - 1) currentIndex++ }
            )
        } else {
            // Портретная ориентация - вертикальное расположение
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

@Composable
fun PortraitLayout(
    currentArtwork: Artwork,
    currentIndex: Int,
    totalArtworks: Int,
    onPreviousClicked: () -> Unit,
    onNextClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        // Изображение (занимает 60% экрана)
        ArtworkImage(
            artwork = currentArtwork,
            modifier = Modifier.weight(0.6f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Информация об искусстве
        ArtworkInfo(
            artwork = currentArtwork,
            modifier = Modifier.weight(0.2f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопки навигации
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
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Изображение слева (50% экрана)
        ArtworkImage(
            artwork = currentArtwork,
            modifier = Modifier.weight(0.5f)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Правая колонка с информацией и кнопками
        Column(
            modifier = Modifier.weight(0.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ArtworkInfo(artwork = currentArtwork)

            Spacer(modifier = Modifier.height(24.dp))

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
    // Получаем описание ДО передачи в semantics
    val imageDescription = stringResource(artwork.titleResId)

    Image(
        painter = painterResource(id = artwork.imageResId),
        contentDescription = imageDescription, // Для accessibility
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .semantics {
                contentDescription = imageDescription // Используем сохраненное значение
            }
    )
}

@Composable
fun ArtworkInfo(
    artwork: Artwork,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = artwork.titleResId),
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = stringResource(id = artwork.artistResId),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = stringResource(id = artwork.yearResId),
            style = MaterialTheme.typography.bodyLarge
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
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = onPreviousClicked,
            enabled = currentIndex > 0, // Делаем неактивной для первого
            modifier = Modifier
                .weight(0.4f)
                .semantics {
                    contentDescription = "Navigate to previous artwork"
                }
        ) {
            Text(stringResource(id = R.string.previous))
        }

        Button(
            onClick = onNextClicked,
            enabled = currentIndex < totalArtworks - 1, // Делаем неактивной для последнего
            modifier = Modifier
                .weight(0.4f)
                .semantics {
                    contentDescription = "Navigate to next artwork"
                }
        ) {
            Text(stringResource(id = R.string.next))
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