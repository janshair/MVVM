# MVVM
 Android MVVM architecture

# 1. Core

This can be accessed by all modules. Contains utils and abstractions 

# 2. app

Contains main application and injections. Has access to all modules. 

# 3. Users

Provides list of users and their details

## Gradle

# 1. config-android.gradle

    - Contains android configuration. Enabled viewbinding and buildvariants. Flavors needs to be added here and this will be accessed by all libraries build.gradles

# 2. config-properties.gradle

    - Contains properties that needs to be secured. BASE_URL in this code.

# 3. dependencies-all.gradle

    - Contains group of dependencies.


# Libraries Used

1. Koin(Dependency Injection)
2. Android Architecture Components(AndroidX, Lifecycle, LiveData, ViewModel, Coroutines, navigation)
3. Retrofit, OkHttp, Gson
4. Glide
5. Mockito
6. Junit(For unit tests)
7. Ktlint(For lint fixes)

In case you have any question, please let me know.
