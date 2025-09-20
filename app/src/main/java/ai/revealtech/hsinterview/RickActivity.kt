package ai.revealtech.hsinterview

import ai.revealtech.hsinterview.ui.theme.HsInterviewTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint

/**
 * The main activity for the Rick and Morty application.
 * This activity serves as the entry point and hosts the [RickNavGraph].
 */
@AndroidEntryPoint
class RickActivity : ComponentActivity() {
    /**
     * Called when the activity is first created.
     * This is where you should do all of your normal static set up: create views, bind data to lists, etc.
     * This method also calls [enableEdgeToEdge] to enable edge-to-edge display and sets the content to [HsInterviewTheme] wrapping the [RickNavGraph].
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in [onSaveInstanceState]. Otherwise it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HsInterviewTheme {
                RickNavGraph()
            }
        }
    }
}
