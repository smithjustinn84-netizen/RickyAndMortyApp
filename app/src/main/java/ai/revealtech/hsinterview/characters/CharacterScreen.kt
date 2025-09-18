package ai.revealtech.hsinterview.characters

import ai.revealtech.hsinterview.R
import ai.revealtech.hsinterview.model.Character
import ai.revealtech.hsinterview.model.exampleCharacters
import ai.revealtech.hsinterview.ui.ErrorScreen
import ai.revealtech.hsinterview.ui.LoadingScreen
import ai.revealtech.hsinterview.ui.previewHandler
import ai.revealtech.hsinterview.ui.theme.HsInterviewTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.LocalAsyncImagePreviewHandler
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun CharacterScreen(
    modifier: Modifier,
    viewModel: CharacterViewModel = hiltViewModel(),
    onClick: (Int) -> Unit = {}
) {
    Scaffold { innerPadding ->
        val lazyPagingItems = viewModel.pager.collectAsLazyPagingItems()

        CharacterContent(
            characters = lazyPagingItems,
            modifier = modifier.padding(innerPadding),
            onClick = onClick
        )

        lazyPagingItems.loadState.apply {
            when {
                refresh is androidx.paging.LoadState.Loading -> {
                    LoadingScreen()
                }

                append is androidx.paging.LoadState.Loading -> {
                    LoadingScreen()
                }

                refresh is androidx.paging.LoadState.Error -> {
                    val error = (refresh as androidx.paging.LoadState.Error).error
                    ErrorScreen(error = error.message ?: "Unknown error")
                }

                append is androidx.paging.LoadState.Error -> {
                    val error = (append as androidx.paging.LoadState.Error).error
                    ErrorScreen(error = error.message ?: "Unknown error")
                }
            }
        }
    }
}

@Composable
private fun CharacterContent(
    characters: LazyPagingItems<Character>,
    modifier: Modifier,
    onClick: (Int) -> Unit = {}
) {

    Column(modifier = modifier) {
        LazyColumn {
            stickyHeader {
                CharacterHeader()
            }
            items(
                count = characters.itemCount,
            ) { index ->
                characters[index]?.let {
                    CharacterRow(
                        character = it,
                        onClick = onClick
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CharacterRow(
    character: Character,
    onClick: (Int) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = dimensionResource(R.dimen.vertical_margin),
                horizontal = dimensionResource(R.dimen.horizontal_margin)
            )
            .clickable { onClick(character.id) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
                AsyncImage(
                    model = character.image,
                    contentDescription = stringResource(R.string.image_description, character),
                )
            }
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.small_space)))
            CharacterSummary(character)
        }
    }
}

@Composable
private fun CharacterSummary(
    character: Character,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = character.name,
            style = MaterialTheme.typography.headlineMedium,
        )
        Row {
            StatusIcon(character.status)
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.small_space)))
            Text(text = "${character.status} - ${character.species}")
        }
    }
}

@Composable
private fun StatusIcon(status: String) {
    when (status) {
        "Alive" -> {
            Image(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = stringResource(R.string.alive),
                colorFilter = ColorFilter.tint(Color.Green),
            )
        }

        "Dead" -> {
            Image(
                imageVector = Icons.Default.Warning,
                contentDescription = stringResource(R.string.dead),
                colorFilter = ColorFilter.tint(Color.Red),
            )
        }

        else -> {
            Image(
                imageVector = Icons.Default.Info,
                contentDescription = stringResource(R.string.unknown),
                colorFilter = ColorFilter.tint(Color.Gray),
            )
        }
    }
}

@Composable
fun CharacterHeader() {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.header_padding)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        LogoImage()
    }
}

@Composable
fun LogoImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(
            id = R.drawable.rick_and_morty_hero,
        ),
        contentDescription = stringResource(R.string.rick_and_morty_logo),
        modifier = modifier
            .requiredHeight(dimensionResource(R.dimen.header_height)),
        contentScale = ContentScale.Fit
    )
}

@Preview(showBackground = true)
@Composable
fun CharacterContentPreview() {
    // create list of fake data for preview
    val fakeData = exampleCharacters
    // create pagingData from a list of fake data
    val pagingData = PagingData.from(fakeData)
    // pass pagingData containing fake data to a MutableStateFlow
    val fakeDataFlow = MutableStateFlow(pagingData)
    // pass flow to composable
    HsInterviewTheme {
        Surface {
            CharacterContent(
                characters = fakeDataFlow.collectAsLazyPagingItems(),
                modifier = Modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterHeaderPreview() {
    HsInterviewTheme {
        Surface {
            CharacterHeader()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterRowPreview() {
    HsInterviewTheme {
        Surface {
            CharacterRow(character = exampleCharacters[0])
        }
    }
}
