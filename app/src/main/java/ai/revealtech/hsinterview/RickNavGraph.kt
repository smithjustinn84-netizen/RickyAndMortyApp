package ai.revealtech.hsinterview

import ai.revealtech.hsinterview.characterdetail.DetailScreen
import ai.revealtech.hsinterview.characters.CharacterScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Composable
fun RickNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: Destination = CharacterRoute
) {
    NavHost(navController, startDestination) {
        composable<CharacterRoute> {
            CharacterScreen(
                modifier = modifier,
                onClick = { characterId ->
                    navController.navigate(DetailRoute(characterId))
                }
            )
        }
        composable<DetailRoute> {
            DetailScreen(
                modifier = modifier,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

interface Destination

@Serializable
object CharacterRoute : Destination

@Serializable
data class DetailRoute(val characterId: Int) : Destination