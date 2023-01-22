import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    id("org.springframework.boot") version "3.0.2"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
}

dependencies {
    // Project Modules
    implementation(project(":application"))
    implementation(project(":domain"))
    implementation(project(":client-http"))

    // Root Dependencies
    implementation("org.springframework.boot:spring-boot-starter")
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
allprojects {
    apply {
        plugin("io.spring.dependency-management")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
    }

    version = "0.1.0"
    java.sourceCompatibility = JavaVersion.VERSION_17

    repositories {
        mavenCentral()
    }

    dependencies {
        // General Dependencies
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("io.github.microutils:kotlin-logging:3.0.4")

        // Test Dependencies
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:1.8.0")
        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude("org.junit.jupiter")
        }
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:2022.0.0")
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_17.majorVersion
            freeCompilerArgs = listOf(
                "-Xjsr305=strict",
                "-Xemit-jvm-type-annotations",
            )
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
subprojects {
    apply {
        plugin("java-library")
    }

    dependencyManagement {
        imports {
            mavenBom(SpringBootPlugin.BOM_COORDINATES)
        }
    }

    tasks.withType<Jar> {
        archiveBaseName.set(rootProject.name)
        archiveAppendix.set(project.name)
    }
}
