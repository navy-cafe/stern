plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version("7.1.2")
}

repositories {
    maven("https://m2.dv8tion.net/releases")
}

dependencies {
    implementation(projects.api)
    implementation(projects.relays.json.client)
    implementation("net.dv8tion:JDA:4.4.0_350")
    implementation("cloud.commandframework:cloud-jda:1.7.1")
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }

    jar {
        manifest {
            attributes(
                "Main-Class" to "cafe.navy.stern.clients.discord.SternDiscordClient"
            )
        }
    }

    shadowJar {
        archiveBaseName.set("discord-client")
        archiveClassifier.set("")
        archiveVersion.set("")
    }
}