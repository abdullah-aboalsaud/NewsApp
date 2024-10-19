# NewsApp

## Overview
**NewsApp** is a modern Android application that provides the latest news articles from various sources. The app fetches data from a news API and allows users to read articles, view news details, and add articles to their favorites for offline viewing.

The project is built using **Kotlin**, **MVVM architecture**, **Dagger Hilt** for dependency injection, **Room** for local data persistence, and **Coroutines** for asynchronous tasks.

## Features
- Fetch and display the latest headlines.
- Browse news from different sources.
- View detailed news articles.
- Add articles to favorites for offline access.
- Open article URLs in the browser.
- Save the favorite state of articles across app sessions.
- search for news

## Technologies & Libraries
# Libraries and technologies used
* [Clean Arch](https://medium.com/@jeremy.leyvraz/clean-architecture-in-android-kotlin-a-comprehensive-overview-743ac2214e88#:~:text=Clean%20architecture%20is%20a%20software,evolution%2C%20testing%2C%20and%20maintenance.) and repo pattern to build the project structure.
* [Navigation component](https://developer.android.com/guide/navigation): single activity contains multiple fragments instead of creating multiple activites.
* [Retrofit](https://square.github.io/retrofit/) : making HTTP connection with the rest API and convert News json file to Kotlin/Java object.
* also used interceptor for offline cashing and Auth
* [Room Database](https://developer.android.com/training/data-storage/room) : Save favorites news in local database.
* [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
* [MVVM](https://developer.android.com/topic/libraries/architecture/viewmodel): Saperate logic code from views and save the state in case the screen configuration changes.
* [Coroutines](https://developer.android.com/kotlin/coroutines) : do some code in the background.
* [view binding](https://developer.android.com/topic/libraries/view-binding) : instead of inflating views manually view binding will take care of that.
* [Glide](https://github.com/bumptech/glide) : Catch images and load them in imageView.
* [**Material Design**](https://material.io/develop/android/docs/getting-started): Provides modern UI components like buttons, toolbars, FABs, and other design patterns following Google’s Material Design guidelines.

## Screenshots

<p float="left">
  <img src="https://github.com/user-attachments/assets/382f36fa-f7f8-46e8-aba7-80ec0ca3e423" alt="Screenshot 1" width="200" />
  <img src="https://github.com/user-attachments/assets/f15e4890-f213-4836-b710-84f9bdaeb3b4" alt="Screenshot 2" width="200" />
  <img src="https://github.com/user-attachments/assets/18c14205-6236-4a0c-8cee-61a3f14e2db6" alt="Screenshot 2" width="200" />
  <img src="https://github.com/user-attachments/assets/ec947c3b-dc93-4c0a-8902-31c5a7cb84a9" alt="Screenshot 2" width="200" />

</p>

