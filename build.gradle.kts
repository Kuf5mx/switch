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

tasks.withType<JavaCompile>().configureEach {
    options.release.set(8)
}

application {
    mainClass = "org.example.App"
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

tasks.register<JavaExec>("runCalculadora") {
    group = "application"
    description = "Corre la calculadora interactiva en terminal."

    dependsOn("testClasses")
    classpath = sourceSets["test"].runtimeClasspath
    mainClass.set("erick.actividad2.calculadora.Main")
    standardInput = System.`in`
}
