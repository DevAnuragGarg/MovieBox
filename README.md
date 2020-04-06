<h1 align="center">MoviesBox</h1><br>

An android application showing movies related stuff. The main focus of the app is to design and integrate the latest new features/recommendations provided/changes released by Google. The app follows MVVM with Clean architecture. Follow me at [Anurag Garg](https://github.com/DevAnuragGarg) to get all the new updates.<br>

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
- JetPack
  - LiveData - notify domain layer data to views.
  - ViewModel - UI related data holder, lifecycle aware.
  - View Binding - removed butter knife, kotlin synthetic, findViewById
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - persistence library, database  
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository pattern
  - [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) with SOLID principles
  - [Dagger2](https://github.com/google/dagger) - dependency injection
- Material Design & Animations
- [Retrofit2 & Gson](https://github.com/square/retrofit) - constructing the REST API
- [OkHttp3](https://github.com/square/okhttp) - implementing interceptor, logging and mocking web server
- [Picasso](hhttps://github.com/square/picasso) - loading images
- [Timber](https://github.com/JakeWharton/timber) - logging
- [RxAndroid](https://github.com/ReactiveX/RxAndroid)
- [Toast](https://developer.android.com/guide/topics/ui/notifiers/toasts) - Customized toast with Gravity
- [Support In-App Updates](https://developer.android.com/guide/app-bundle/in-app-updates)
- [CircleCI](https://circleci.com/) - for continuous integration
- [Mockito](https://site.mockito.org)

## TODOs
- Work manager for cleaning up the database at particular time
- making the status bar transparent in movie detail screen
- pagination for all the recycler views
- Handling of error scenarios
- latest dagger changes
- make binder null in fragment
- implementation of similar movies in movie detail screen
- implementation of data binding into movie detail screen
- writing test cases for all the modules and having maximum code coverage 

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