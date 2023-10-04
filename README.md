# SOFE4640 - Assignment 1
This repository contains all project files for Assignment 1

## Usage
Please make sure you use the included gradle build files (project and module) to run the app without errors. Due to dependency constraints with Android SDK 34, you will run into errors when running if you do not use my included gradle build.
Also please make sure you are running the app on a API level 34 device for proper runtime environment and to avoid errors.
NOTE: If you are using your own gradle build and you encounter any duplicate class errors, please add this to your dependencies definition:
```
constraints {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.0") {
        because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
    }
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0") {
        because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
    }
}
```
