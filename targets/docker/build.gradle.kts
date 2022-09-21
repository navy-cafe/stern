plugins {
    `java-library`
}

dependencies {
    api(projects.api)
    api("com.github.docker-java:docker-java-core:3.2.13")
    api("com.github.docker-java:docker-java-transport-httpclient5:3.2.13")
}