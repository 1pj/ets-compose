# Introduction
Hello there! This project was created to assist us in the recruitment process by allowing you to showcase how you work in typical day to day scenario. It consists of the toy project and a set of quests we would like you to solve.

We've used [Jetpack Compose](https://developer.android.com/jetpack/compose) to build UI layer in this project because it's our framework of choise for the future projects.

<img src="/results/screenshot_3.jpg" width="260">&emsp;<img src="/results/screenshot_4.gif" width="260">

# Setup
You would need [Android Studio](https://developer.android.com/studio) and [Github](https://github.com/signup) account. 

Click on [Use this template](https://docs.github.com/en/repositories/creating-and-managing-repositories/creating-a-repository-from-a-template) button for create repository copy (please do not fork it :ghost:)

# :boat: Quests
## :star2: Fit the Locals
Compose provides special mechanism to propagate objects across composable functions called `CompositionLocal`. Main screen uses `SystemUiController` which is not currently provided. This triggers application crash in runtime. Your task is to find most appropriate place to provide this object.

## :star2: Knives Out!
The project uses simple dependency container. Let's improve it by implementing proper [Dagger/Hilt](https://github.com/google/dagger) DI solution.

## :star2: Let There Be Light
Compose allows us to easily support both dark and light themes. Application already has all colors prepared, your task is to add implementation which would switch theme automatically depending on system settings.

## :star2: The missing Block
Application has bottom sheet which displays weekly weather forecast for the user, your task is to implement WeatherDetailsBlock composable which is used to display information for each day. Use screenshots as design mockup. Remember to maintain expanded/collapsed row state after screen rotation.

## :star2: Switch it
The user can conveniently tap on city name and pick another using list dialog. But it seems the data is not being updated. Your task is to fix this issue, following current state management logic.

# Wrapping up
After completing all quests, make sure that project is properly formatted with `spotless` and all tests complete with success. You can track current state using this badge: 

![Workflow result](https://github.com/ets-recruiting/androiddev/workflows/Check/badge.svg)
