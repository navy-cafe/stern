plugins {
    id("io.papermc.paperweight.userdev") apply(true)
    id("com.github.johnrengelman.shadow") apply(true)
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    implementation(projects.api)
    implementation(projects.relays.json.client)
    paperDevBundle("1.19.2-R0.1-SNAPSHOT")
}

tasks {
    assemble {
        dependsOn(reobfJar)
    }
    reobfJar {
        outputJar.set(rootProject.layout.buildDirectory.file("libs/${project.name}.jar"))
    }
}