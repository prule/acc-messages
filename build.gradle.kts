plugins {
    id("java")
    kotlin("jvm")
    id("maven-publish")
    id("com.diffplug.spotless") version "8.4.0"
}

group = "com.github.prule"
version = "main-SNAPSHOT"

repositories {
    mavenCentral()
}

spotless {
    java {
        googleJavaFormat()
    }
}

dependencies {
    api("io.kaitai:kaitai-struct-runtime:0.11")
    testImplementation(platform("org.junit:junit-bom:6.0.3"))
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
