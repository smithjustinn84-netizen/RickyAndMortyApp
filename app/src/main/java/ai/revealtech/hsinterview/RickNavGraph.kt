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

/**
 * Composable function that defines the navigation graph for the Rick and Morty application.
 *
 * @param modifier Modifier to be applied to the NavHost.
 * @param navController The [NavHostController] used for navigation.
 * @param startDestination The starting destination for the navigation graph.
 */
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

/**
 * Interface representing a navigation destination in the application.
 */
interface Destination

/**
 * Represents the navigation route for the character list screen.
 */
@Serializable
object CharacterRoute : Destination

/**
 * Represents the navigation route for the character detail screen.
 *
 * @property characterId The ID of the character to display.
 */
@Serializable
data class DetailRoute(val characterId: Int) : Destination
