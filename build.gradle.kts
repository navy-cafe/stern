plugins {
    id("java")
    id("maven-publish")
    id("org.checkerframework") apply(false)
    id("com.github.johnrengelman.shadow") apply(false)
}

allprojects {
    group = "cafe.navy.stern"
    version = "1.0-SNAPSHOT"

    apply(plugin="java")
    apply(plugin="maven-publish")
    apply(plugin="org.checkerframework")

    repositories {
        mavenCentral()
    }
}
