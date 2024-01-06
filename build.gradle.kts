import java.io.ByteArrayOutputStream

plugins {
    id("java")
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    id("xyz.jpenilla.run-paper") version "2.2.2"
}

group = "com.semivanilla.chatitem"
version = "2.0-SNAPSHOT"
description = "Show an item preview in chat."

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT") // Paper
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks {

    runServer {
        minecraftVersion("1.20.4")
    }

}

bukkit {
    name = rootProject.name
    main = "$group.${rootProject.name}"
    version = "${rootProject.version}-${gitCommit()}"
    apiVersion = "1.20"
    website = "https://github.com/SemiVanilla-MC/${rootProject.name}"
    authors = listOf("destro174")
}

fun gitCommit(): String {
    val os = ByteArrayOutputStream()
    project.exec {
        commandLine = "git rev-parse --short HEAD".split(" ")
        standardOutput = os
    }
    return String(os.toByteArray()).trim()
}