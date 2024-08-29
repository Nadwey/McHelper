plugins {
    java
    application

    id("com.gradleup.shadow") version "8.3.0"
}

group = "pl.nadwey"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven("https://storehouse.okaeri.eu/repository/maven-public/")
}

dependencies {
    implementation("net.dv8tion:JDA:5.1.0")
    implementation("eu.okaeri:okaeri-configs-yaml-snakeyaml:5.0.5")
    implementation("com.google.guava:guava:33.3.0-jre")
    implementation("com.github.spullara.mustache.java:compiler:0.9.14")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
}

application {
    mainClass = "pl.nadwey.mchelperbot.McHelperBot"
}

tasks.build {
    dependsOn(tasks.named("shadowJar"))
}
