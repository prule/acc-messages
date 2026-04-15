plugins {
    id("java")
    kotlin("jvm")
    id("maven-publish")
}

group = "com.github.prule"
version = "main-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api("io.kaitai:kaitai-struct-runtime:0.10")
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
