package com.example.pokemon.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pokemon.R
import com.example.pokemon.model.Pokemon
import com.example.pokemon.model.PokemonType

@Composable
fun PokemonItem(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
    onPokemonClicked: (Pokemon) -> Unit,

    ) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(3.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(
                pokemon
                    .getType()
                    .getDefaultColor()
            )
            .height(70.dp)
            .clickable(onClick = {
                onPokemonClicked(pokemon)
            }),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PokemonImage(pokemon.image_url, Modifier, pokemon.getType().getBackgroundColor())
            Text(
                text = pokemon.name,
                fontSize = 6.em,
                color = pokemon.getType().getTextColor(),
                modifier = Modifier.padding(start = 20.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxHeight(),
        ) {
            PokemonTypeIconsContainer(pokemon)
            PokemonId(pokemon.id)
        }
    }
}


@Composable
fun PokemonImage(
    imageUrl: String,
    modifier: Modifier,
    color: Color
) {
    Box(
        modifier = Modifier
            .width(80.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(50.dp))
            .background(color)
        ,
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.pokemon),
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth()

        )
    }
}

@Composable
fun PokemonTypeIconsContainer(pokemon: Pokemon) {
    Row(
        modifier = Modifier
            .padding(5.dp)
            .width(80.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        PokemonTypeIcon(pokemon.getType())
        val pokemonType = pokemon.getSecondaryType()
        if (pokemonType != null) {
            PokemonTypeIcon(pokemonType)
        }
    }
}

@Composable
fun PokemonTypeIcon(
    pokemonType: PokemonType
) {
    Image(
        painter = painterResource(pokemonType.iconResourceId),
        contentDescription = pokemonType.name,
        modifier = Modifier
            .size(35.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(pokemonType.getBackgroundColor())
            .padding(5.dp)

    )
}


@Composable
fun PokemonId(pokemonId: Int) {
    fun format(id: Int): String {
        var formattedId = id.toString()
        while (formattedId.length < 3) {
            formattedId = "0$formattedId"
        }
        return formattedId
    }

    Box(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(100.dp))
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = format(pokemonId),
            fontSize = 5.em,
            modifier = Modifier
                .padding(10.dp),
            color = MaterialTheme.colorScheme.secondary
        )
    }
}
