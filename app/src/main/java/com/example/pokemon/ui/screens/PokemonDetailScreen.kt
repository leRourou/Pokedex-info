package com.example.pokemon.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pokemon.R
import com.example.pokemon.model.Pokemon
import com.example.pokemon.model.PokemonType
import com.example.pokemon.ui.composables.PokemonEvolutions
import com.example.pokemon.ui.composables.Separator

/**
 * [Pokemon] detail page
 */
@Composable
fun PokemonDetailsScreen(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
    onPokemonClicked: (pokemon: Pokemon) -> Unit
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(
                pokemon
                    .getType()
                    .getColor()
            ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        PokemonDetailsImage(pokemon.image_url)
        Column(modifier = Modifier
            .padding(horizontal = 25.dp)
        )
        {
            PokemonTitle(pokemon)
            Separator(size = 20.dp)
            PokemonDescription(pokemon)
            Separator(size = 20.dp)
            Row(modifier = Modifier
                .align(Alignment.CenterHorizontally)
            ) {
                PokemonTypeIcon(pokemon.getType())
                val secondaryType = pokemon.getSecondaryType();
                if (secondaryType != null) { PokemonTypeIcon(secondaryType) }
            }
            Separator(size = 15.dp)
            PokemonEvolutions(pokemon, onPokemonClicked)
        }
    }
}

@Composable
fun PokemonDetailsImage(
    pokemonImageUrl: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(bottomEnd = 100.dp, bottomStart = 100.dp))
            .background(MaterialTheme.colorScheme.surface)
            .zIndex(2F),
        horizontalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(pokemonImageUrl)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.pokemon),
            modifier = Modifier.padding(20.dp),
        )
    }
}

@Composable
fun PokemonTitle(
    pokemon: Pokemon
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(100.dp))
            .background(
                pokemon
                    .getType()
                    .getBackgroundColor()
            )
    ) {
        Text(
            pokemon.name,
            modifier = Modifier
                .padding(start = 25.dp, top = 5.dp, bottom = 5.dp)
                .align(Alignment.CenterVertically),
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            "#" + pokemon.id.toString(),
            modifier = Modifier
                .padding(start = 15.dp)
                .align(Alignment.CenterVertically),
            color = pokemon.getType().getTextColor(),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun PokemonDescription(pokemon:Pokemon) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(
                pokemon
                    .getType()
                    .getDefaultColor()
            )) {
        Text(
            pokemon.description,
            modifier = Modifier.padding(10.dp),
            color = Color.Black
            )
    }
}

@Composable
fun PokemonTypeIcon(pokemonType: PokemonType) {
    Row(modifier = Modifier
        .padding(end = 20.dp)
        .height(50.dp)
    ) {
        Image(
            painter = painterResource(pokemonType.iconResourceId),
            contentDescription = pokemonType.name,
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(pokemonType.getBackgroundColor())
                .padding(5.dp)

        )
        Text(
            text = pokemonType.name,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 10.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = pokemonType.getTextColor()
        )
    }
}




