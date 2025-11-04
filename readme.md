# StarLord

StarLord is a project developed for Android using Kotlin.

The application's purpose is to list GitHub repositories with the most stars ⭐.

## 🚀 Getting Started

To start working with the StarLord project, follow the steps below:

### 🧰 Prerequisites

- Git
- Android Studio Narwhal 3 Feature Drop | 2025.1.3 or higher
- JDK 21 or higher (optional, as Android Studio has an integrated JDK)
- [Gradle 8+](https://gradle.org/install/) (optional, as the project includes the wrapper)

### 📦 Installation

## 1. Clone the repository:

```bash
   git clone git@github.com:Jhonnycpp/StarLord.git
   cd StarLord
```
   
## 2. 📁 Directory Structure

```
StarLord/
├── .gitignore
├── LICENSE
├── build.gradle.kts
├── gradle/
|     └── libs.versions.toml                   <--- Version catalog and dependencies
├── gradlew
├── gradlew.bat
├── settings.gradle.kts
└── app/
    └── src/
        └── main/
            └── kotlin/
                └── br/com/jhonny/starlord/
                    ├── di                      <--- Dependency injection configuration (Koin)
                    ├── extension               <--- Extension functions
                    ├── feature                 <--- Domain and data layers (Use Cases, Repositories)
                    ├── ui                      <--- Presentation layer (Compose Screens, ViewModels)
                    |    ├── navigation         <--- Navigation logic
                    |    ├── screen             <--- Screens and their ViewModels
                    |    ├── theme              <--- App theme (Material 3)
                    |    └── MainActivity.kt    <--- UI entry point
                    └── MainApplication.kt      <--- Project entry point (Library initialization)
```
## 3. 🛠 Technologies Used

This project uses a modern set of technologies for Android development:
- Language: Kotlin
- Architecture: 
  - Clean Architecture: This is a software architecture that aims to separate your code's concerns into distinct layers (such as Domain, Use Cases, Interface Adapters, and Frameworks & Drivers). The goal is to create a more testable, maintainable, and independent system from external frameworks or implementation details.
  - MVVM: This is a presentation architecture (part of the "Interface Adapters" layer in Clean Architecture) that facilitates the separation of presentation logic from the UI.
    - Model: Represents the data and business logic (usually coming from the Domain/Use Cases layer in Clean Architecture).
    - View: The UI (in your case, implemented with Jetpack Compose) that observes the ViewModel.
    - ViewModel: Exposes data from the Model to the View and handles presentation logic and user interactions. It has no direct knowledge of the View.
- Code Design Principles:
  - SOLID: We adopted the five SOLID principles (Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, Dependency Inversion) as a guide to writing high-quality code, promoting the project's maintainability, testability, and scalability.
- User Interface:
  - Jetpack Compose for a declarative and modern UI.
  - Material Design 3 for visual components.
- Dependency Injection:
  - Koin for lightweight and pragmatic dependency management.
- Networking:
  - Retrofit for type-safe HTTP requests.
  - OkHttp (indirectly via Coil and Retrofit) as the HTTP client.
  - KotlinX Serialization for JSON parsing.
- Image Loading:
  - Coil for efficient image loading.
- Navigation: Jetpack Navigation Compose for navigation between Compose screens.
- Tests:
  - Unit: JUnit 4, MockK, Turbine (for Flows), KotlinX Coroutines Test, Robolectric.
  - Instrumented: JUnit 4, Espresso, MockK, Compose UI Test, Navigation Testing.
- AndroidX/Jetpack Utilities: Core KTX, Lifecycle KTX.
- Build System: Gradle with Kotlin DSL.
- Code Coverage: Kover.

## 4. 🧰 Compile the project:
```bash
    ./gradlew assemble
```

## 5. 🧪 Tests
### Unit 
```bash
    ./gradlew check
```

### Instrumented
```bash
    emulator -list-avds # List available avds
    emulator -avd <avd_name> -netdelay none -netspeed full # Start the emulator with maximum network speed
    adb wait-for-device # Wait until the device is connected
    while [[ $(adb shell getprop sys.boot_completed | tr -d '\r') != "1" ]]; do # Wait until the device is booted
    sleep 1
    done
    ./gradlew connectedAndroidTest # Run instrumented tests
```  

## 🌡️ 6. Coverage
This command generates a unit and instrumented code coverage report.
```bash
    ./gradlew koverHtmlReport
```

To view it, simply open the HTML report in the `./app/build/reports/kover/html/index.html` folder in your preferred browser.

## 📱 7. App
The app has the functionality to display the GitHub repositories with the most stars ⭐

|Portrait|Landscape|
|-|-|
|![list-and-search-portrait.gif](doc/list-and-search-portrait.gif)|![list-and-search-landscape.gif](doc/list-and-search-landscape.gif)|

And it also has the functionality to display the information of the selected repository.

|Portrait|Landscape|
|-|-|
|![detail-portrait.gif](doc/detail-portrait.gif)|![detail-landscape.gif](doc/detail-landscape.gif)|
