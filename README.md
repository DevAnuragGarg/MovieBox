<h1 align="center">MoviesBox</h1><br>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/DevAnuragGarg/MovieBox/actions"><img alt="Build Status" src="https://github.com/DevAnuragGarg/MovieBox/workflows/Android%20CI/badge.svg"/></a> 
  <a href="https://github.com/DevAnuragGarg"><img alt="License" src="https://img.shields.io/static/v1?label=GitHub&message=DevAnuragGarg&color=C51162"/></a> 
  <a href="https://app.codacy.com/manual/DevAnuragGarg/MovieBox?utm_source=github.com&utm_medium=referral&utm_content=DevAnuragGarg/MovieBox&utm_campaign=Badge_Grade_Dashboard"><img alt="Code Quality" src="https://api.codacy.com/project/badge/Grade/f001fda745ed4f918e16f27b9594c9fa"/></a> 
</p><br>

A sample android application showing the data related to movies(latest, popular, all time grosser). The main focus on the app is to design and integrate the latest new features/recommendations provided/released by Google on MVVM design pattern.<br>

<h2 align="center">Contents</h2>

## Tech stack & Open-source libraries
- Minimum SDK level 21
- 100% [Kotlin](https://kotlinlang.org/) based
- JetPack
  - LiveData - notify domain layer data to views.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct database.
  - View Binding - removed butter knife, kotlin synthetic, findViewById  
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
- [Support In-App Updates](https://developer.android.com/guide/app-bundle/in-app-updates)
- [CircleCI](https://circleci.com/) for continous integration
- [Mockito](https://site.mockito.org)
