plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.kafka:kafka-clients:3.6.1")
    implementation ("org.slf4j:slf4j-api:2.0.12")
    implementation ("org.slf4j:slf4j-simple:2.0.12")
}

tasks.test {
    useJUnitPlatform()
}