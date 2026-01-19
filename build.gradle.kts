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
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

application {
    mainClass = "erick.Act1.Starter"
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
