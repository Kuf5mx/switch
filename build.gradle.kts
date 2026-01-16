plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("junit:junit:4.13.2")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

application {
<<<<<<< Updated upstream
    mainClass = "org.example.App"
=======
    mainClass.set("edu.erick.Main")
>>>>>>> Stashed changes
}
