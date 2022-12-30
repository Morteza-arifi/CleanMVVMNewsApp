# Clean MVVM NewsApp

<h4 align="center">A sample code for Clean MVVM Architecture.</h4>

## Description
A Simple News Android Application which has been implemented using Clean Architecture alongside MVVM design to Search worldwide news with code, displays top headline news, save favorite news, and search for desired news.

## demo

<p align="center">
<img src="https://github.com/Morteza-arifi/CleanMVVMNewsApp/blob/master/newsAppGif.gif" width="280" height="480"/>

</P>

## Architecture and patterns
The Clean MVVM News App follows best practices and the architecture recommendations from [Guide to app architecture](https://developer.android.com/topic/architecture), such as:

* separation of concerns
  - UI (Presentation)
  - Domain
  - Data
* Driving UI from data models
* Unidirectional data flow
  - Events flow down
  - Data flows up  
* Managing dependencies between components using dependency injection (this project uses Hilt)
* Repository pattern

## Components used in this repository
*	Kotlin Coroutines
*	Kotlin Flow
*	Retrofit2
*	Room database
*	Hilt (dependency injection)
*	ViewModel
*	LiveData
*	Navigation Component
*	RecyclerView
*	Material Design
*	Glide
* ...

## The Rest Api

[NewsApi](https://newsapi.org/) - Search worldwide news with code


