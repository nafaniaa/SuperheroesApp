package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.Hero
import com.example.superheroes.model.HeroesRepository.heroes
import com.example.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HeroApp()
                }
            }
        }
    }
}

@Composable
fun HeroApp(){
    Scaffold(
        topBar = {
            HeroTopAppBar()
        }
    ) {it ->
        LazyColumn(contentPadding = it){
            items(heroes){
                HeroItem(
                    hero = it,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_card)))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroTopAppBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(title = {
        Text(
            text = "Superheroes",
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center
        )
    },
        modifier = modifier
    )
}
@Composable
fun HeroInformation(
    @StringRes name: Int,
    @StringRes description: Int,
    modifier: Modifier = Modifier
){
        Column(
            modifier = modifier
        ) {
            Text(
                text = stringResource(name),
                style = MaterialTheme.typography.displaySmall
            )
            Text(
                text = stringResource(description),
                style = MaterialTheme.typography.bodyLarge
            )
        }
}

@Composable
fun HeroIcon(
    @DrawableRes image: Int,
    @StringRes name: Int,
    modifier: Modifier = Modifier
){
    Box(
        modifier = Modifier
            .size(72.dp)
            .clip(MaterialTheme.shapes.small)
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = stringResource(name),
            alignment = Alignment.TopCenter,
            modifier = modifier
                .clip(MaterialTheme.shapes.small),
            contentScale = ContentScale.FillWidth
        )
    }
}


@Composable
fun HeroItem(
    hero: Hero,
    modifier: Modifier = Modifier
){
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
            .sizeIn(minHeight = 72.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding))
        ) {
            HeroInformation(name = hero.nameRes, description =hero.descriptionRes, Modifier.weight(1f) )
            Spacer(Modifier.width(16.dp))
            HeroIcon(image = hero.imageRes, name = hero.nameRes )
        }
    }
}


@Preview
@Composable
fun HeroPreview(){
    SuperheroesTheme(darkTheme = false) {
        HeroApp()
    }
}

@Preview
@Composable
fun HeroPreviewDark(){
    SuperheroesTheme(darkTheme = true) {
        HeroApp()
    }
}
