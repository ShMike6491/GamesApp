### About 

This app is designed with an idea in mind of people having more fun together. This is a board game collection of games 
that allows users to connect and play over shared network. This is not meant to play online, but instead for people to 
have more fun together in real world. It is difficult to care all those cool games around all the time, but everybody has
a phone!

Those are some of the games that are planned to be implemented:
- Cards Against Humanity (sample: https://github.com/nodanaonlyzuul/against-humanity)
- Uno
- TicTacToy
- Imaginarium
- Drawful

### Architecture 

Here we will implement DDD (domain driven development https://infinum.com/handbook/android/project-architecture/mobile-app-architecture) 
approach with Clean architecture (https://developer.android.com/topic/architecture) and MVI (https://medium.com/swlh/mvi-architecture-with-android-fcde123e3c4a). 

This is a reference app for the architecture on this project: https://github.com/philipplackner/CleanArchitectureNoteApp/tree/master

### Dependencies

We use compose for ui: https://developer.android.com/jetpack/compose?gclid=CjwKCAiAjrarBhAWEiwA2qWdCIZietyLzGkbqRZK9oAWbaT0bA6cFZO15ZyALVHEWtx0m2R2acjzNRoCl8IQAvD_BwE&gclsrc=aw.ds
Ktor for the server and client: https://ktor.io/
Coroutines for multitasking: https://developer.android.com/kotlin/coroutines?gclid=CjwKCAiAjrarBhAWEiwA2qWdCIL_Zf9d2oCGOQjGRdrdziZycUFRHVC682Rn6caHN16-2qqYpS3J3xoC6awQAvD_BwE&gclsrc=aw.ds
Compose Jetpack Navigation: https://developer.android.com/jetpack/compose/navigation
Koin for dependency injection: https://insert-koin.io/docs/quickstart/android/

### Ui/Ux Design

Here are some app references with the design guidelines. Those are gonna be the recommended designs for this application.
https://x.com/Shubham_iosdev/status/1708184745883107810?t=bdEcZucivPbGzfDpSSpxfw&s=08

### Embedded Server

Here we setup a ktor server on the local machine of the game host user along with foreground service to keep the process
running. On the client side ktor allows us to have constant connection with the use of web sockets. 

Here are the sample apps that can be used as a reference.
client: https://github.com/philipplackner/KtorAndroidChat/tree/app
server: https://github.com/philipplackner/ChatApp-Server

### How to Contribute

Not possible at this moment but maybe I'll open this possibility in the future