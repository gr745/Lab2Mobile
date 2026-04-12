package com.example.artspace.data

import com.example.artspace.R
import com.example.artspace.model.Artwork

object ArtworkDataSource {
    val artworks = listOf(
        Artwork(
            imageResId = R.drawable.art1,
            titleResId = R.string.art1_title,
            artistResId = R.string.art1_artist,
            yearResId = R.string.art1_year
        ),
        Artwork(
            imageResId = R.drawable.art2,
            titleResId = R.string.art2_title,
            artistResId = R.string.art2_artist,
            yearResId = R.string.art2_year
        ),
        Artwork(
            imageResId = R.drawable.art3,
            titleResId = R.string.art3_title,
            artistResId = R.string.art3_artist,
            yearResId = R.string.art3_year
        )

    )
}