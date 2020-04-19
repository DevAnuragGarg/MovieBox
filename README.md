<h1 align="center">MoviesBox :movie_camera:</h1><br>

An android phone application :iphone: showing movies :video_camera: related stuff. The main focus of the app is to design and integrate the latest new features/recommendations provided/changes released by Google. The app follows MVVM with Clean architecture. Follow me at [Anurag Garg](https://github.com/DevAnuragGarg) to get all the new updates.<br>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/DevAnuragGarg/MovieBox/actions"><img alt="Build Status" src="https://github.com/DevAnuragGarg/MovieBox/workflows/Android%20CI/badge.svg"/></a> 
  <a href="https://github.com/DevAnuragGarg"><img alt="License" src="https://img.shields.io/static/v1?label=GitHub&message=DevAnuragGarg&color=C51162"/></a> 
  <a href="https://app.codacy.com/manual/DevAnuragGarg/MovieBox?utm_source=github.com&utm_medium=referral&utm_content=DevAnuragGarg/MovieBox&utm_campaign=Badge_Grade_Dashboard"><img alt="Code Quality" src="https://api.codacy.com/project/badge/Grade/f001fda745ed4f918e16f27b9594c9fa"/></a> 
</p><br>

## Releases
Go to the [Releases](https://github.com/DevAnuragGarg/MovieBox/releases) to download the lastest APK.

## Contents
- Minimum SDK level 21
- 100% [Kotlin](https://kotlinlang.org/) based
- JetPack: BEHAVIOR
  - [Notifications](https://developer.android.com/guide/topics/ui/notifiers/notifications) - added notifications for the application when clicked on upcoming movies
- JetPack: FOUNDATION
- JetPack: ARCHITECTURE
  - [Data binding](https://developer.android.com/topic/libraries/data-binding) - used in detail screen and home screen
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - notify domain layer data to views.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - UI related data holder, lifecycle aware.
  - [View Binding](https://developer.android.com/topic/libraries/view-binding) - removed butter knife, kotlin synthetic, findViewById
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - persistence library, database  
  - [Work Manager](https://developer.android.com/topic/libraries/architecture/workmanager) - work manager to show notifications after fetching data from server
 
- DESIGN PATTERNS
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository pattern
  - [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) with SOLID principles
- Material Design & Animations
- [Dagger2](https://github.com/google/dagger) - dependency injection
- [Retrofit2 & Gson](https://github.com/square/retrofit) - constructing the REST API
- [OkHttp3](https://github.com/square/okhttp) - implementing interceptor, logging and mocking web server
- [Picasso](hhttps://github.com/square/picasso) - loading images throughout the app
- [Timber](https://github.com/JakeWharton/timber) - logging library added
- [RxAndroid](https://github.com/ReactiveX/RxAndroid)
- [AndroidX](https://developer.android.com/jetpack/androidx) Using latest versions of jetpack libraries
- [Toast](https://developer.android.com/guide/topics/ui/notifiers/toasts) - Customized toast with Gravity
- [Support In-App Updates](https://developer.android.com/guide/app-bundle/in-app-updates)
- [CircleCI](https://circleci.com/) - for continuous integration
- [Mockito](https://site.mockito.org)

## TODOs
- Work manager for cleaning up the database at particular time
- Work manager fetch movies 
- Work manager applying dagger
- making the status bar transparent in movie detail screen
- pagination for all the recycler views
- Handling of error scenarios
- latest dagger changes
- sealed classes for checking data received and transfer to UI
- implementation of similar movies in movie detail screen
- writing test cases for all the modules and having maximum code coverage 
- https://www.androidhive.info/2020/01/viewpager2-pager-transformations-intro-slider-pager-animations-pager-transformations/

## License
```
Designed and developed by 2020 devanuraggarg (Anurag Garg)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```