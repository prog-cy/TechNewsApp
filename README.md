
# TechNewsApp

An Android Application which uses custom API of wordpress to show different-2 news of the tech world.
Using Youtube API I have shown videos of some tech Youtube channels in my Application.




## Requirements
- Android Studio
- JDK(Java Development Key)
- XAMPP Server.
## Tech Stack

**UI Design:** XML(Extensible Markup Language)

**Backend:** Java

**Backend Server:** XAMPP Server(for hosting my wordpress website using localhost).

**Custom API:** Custom API of wordpress

**Youtube API:** Youtube API V3 for showing video in APP.

**ImageSlider:** GlideImageSlider


## Dependencies

### Gradle Project Level
```

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Add the dependency for the Google services Gradle plugin
        classpath 'com.google.gms:google-services:4.3.15'

    }
}

```
### Gradle Module Level
```
    //For swipe refresh layout dependency
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    //for auto image slider and glide
    implementation "com.github.firdausmaulan:GlideSlider:1.5.2"
    implementation 'com.github.bumptech.glide:glide:4.14.2'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.14.2'

    //Circular image library
    implementation 'de.hdodenhof:circleimageview:3.1.0'

    //Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:4.10.0'

    //javax dependency for json to pojo annotation
    implementation 'javax.annotation:javax.annotation-api:1.3.2'

    // Import the Firebase BoM
    implementation platform('com.google.firebase:firebase-bom:31.2.2')

    // When using the BoM, don't specify versions in Firebase dependencies
    implementation 'com.google.firebase:firebase-analytics'


    //Youtube video player zip file
    implementation files('libs/YouTubeAndroidPlayerApi.jar')
```
## Permissions

### For INTERNET
```
<uses-permission android:name="android.permission.INTERNET" />

```

### For Playing Youtube Video
```
//Inside AndroidManifest
<manifest>
    <queries>
            <intent>
                <action android:name="com.google.android.youtube.api.service.START" />
            </intent>
    </queries>
</manifest>

```
## Screenshots

<p align="center">
    <img src="https://github.com/prog-cy/TechNewsApp/blob/master/screen1.jpeg" width = "200" height = "400" 
    margin = "10">
    <img src="https://github.com/prog-cy/TechNewsApp/blob/master/screen2.jpeg" width = "200" height = "400"
    margin = "10">
    <img src="https://github.com/prog-cy/TechNewsApp/blob/master/screen3.jpeg" width = "200" height = "400"
    margin = "10">   
    <img src="https://github.com/prog-cy/TechNewsApp/blob/master/screen4.jpeg" width = "200" height = "400"
    margin = "10">   
    <img src="https://github.com/prog-cy/TechNewsApp/blob/master/screen5.jpeg" width = "200" height = "400"
    margin = "10">   
    <img src="https://github.com/prog-cy/TechNewsApp/blob/master/screen6.jpeg" width = "200" height = "400"
    margin = "10">
    <img src="https://github.com/prog-cy/TechNewsApp/blob/master/screen7.jpeg" width = "200" height = "400"
    margin = "10">
    <img src="https://github.com/prog-cy/TechNewsApp/blob/master/screen8.jpeg" width = "200" height = "400"
    margin = "10">    

</p>
