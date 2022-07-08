# kotlin-repos-app

This project is a native android application using Kotlin that displays dynamic rows datasets of repositories fetched from GitHub public API.


The first tab is a kotlin native screen containing:

 - [x] Render a list with rows containing "repository ID", "title", "owner", "stars" and "created at" timestamp
 - [x] Render an input element to search for repositories by name
 - [x] Cache the results of every search (or have a limit) - do not make an API request if the results are already stored
 - [ ] Do not fire requests as long as the user is typing - use throttle or debounce
 - [ ] Implement pagination (locally, not by using API queries)
 - [ ] Add ability to control number of rows rendered per page (5 - default/10/15/20)
 - [x] Implement ASC/DESC sorting by every field
 
The second tab is a simple view with React Native root view rendered inside.
The contents of rendered React Native view cointains:

- [x] "Repo Title" from the topmost search result (repositody that's rendered as #1 in first tab)
- [x] If there are no results it should cointain "No data" label isntead.

## Sturcture/Arc
- Data binding follows an Model-View-ViewModel (MVVM) pattern.
- Used Dagger for Class and Constructor injection.
- Used Room DB for storage and cahcing.
- Used Retrofit to handle network requests.
- Used Korutines and flow to handle async taks
- Used a shared ViewModel between the repos tab and react-native fragment
- Used Picasso to handle images


## For react-native side

`yarn install`
`yarn start` 

## For Kotlin app
Run from Android Studio
