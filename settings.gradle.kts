rootProject.name = "stern"

include("api")
include("backend")

include(":clients:paper")
include(":clients:discord")
project(":clients:paper").name = "clients-paper"
project(":clients:discord").name = "clients-discord"

include(":relays:json:client")
include(":relays:json:server")
include(":relays:redis:client")
include(":relays:redis:server")
include(":relays:websockets:client")
include(":relays:websockets:server")
project(":relays:json:client").name = "relay-json-client"
project(":relays:json:server").name = "relay-json-server"
project(":relays:redis:client").name = "relay-redis-client"
project(":relays:redis:server").name = "relay-redis-server"
project(":relays:websockets:client").name = "relay-websockets-client"
project(":relays:websockets:server").name = "relay-websockets-server"

include(":targets:docker")
include(":targets:host")
include(":targets:kubernetes")
include(":targets:pterodactyl")
project(":targets:docker").name = "targets-docker"
project(":targets:host").name = "targets-host"
project(":targets:kubernetes").name = "targets-kubernetes"
project(":targets:pterodactyl").name = "targets-pterodactyl"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.stellardrift.ca/repository/snapshots/")
        maven("https://papermc.io/repo/repository/maven-public/")
    }
}

plugins {
    id("ca.stellardrift.polyglot-version-catalogs") version "5.0.0-SNAPSHOT"
}

/**
 * Renames a set of project IDs.
 *
 * @param names the list of projects to rename
 */
fun projects(vararg names: String) {
    include(*names)

    names.forEach {
        project(":$it").name = "stern-$it"
    }
}
