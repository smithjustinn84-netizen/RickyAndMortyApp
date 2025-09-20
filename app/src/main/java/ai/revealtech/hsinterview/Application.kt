package ai.revealtech.hsinterview

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * The main [Application] class for the Rick and Morty application.
 * This class is annotated with [HiltAndroidApp] to enable Hilt for dependency injection.
 */
@HiltAndroidApp
class Application : Application()
