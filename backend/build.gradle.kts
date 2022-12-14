plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version("7.1.2")
}

dependencies {
    implementation(projects.api)
    implementation(projects.relays.json.server)
    implementation(projects.targets.docker)
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }

    jar {
        manifest {
            attributes(
                "Main-Class" to "cafe.navy.stern.backend.BackendApplication"
            )
        }
    }

    shadowJar {
        archiveBaseName.set("backend")
        archiveClassifier.set("")
        archiveVersion.set("")
    }
}