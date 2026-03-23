plugins {
    id("java")
    kotlin("jvm")
    id("maven-publish")
}

group = "io.github.prule.acc.messages"
version = "main-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api("io.kaitai:kaitai-struct-runtime:0.10")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
    repositories {
        mavenLocal()
    }
}
