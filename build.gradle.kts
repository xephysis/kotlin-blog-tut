import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.allopen.gradle.AllOpenExtension
//import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
	val kotlinVersion = "1.5.30"
	val springBootVersion = "2.5.4"
	val dependencyManagementVersion = "1.0.11.RELEASE"

	id("org.springframework.boot") version springBootVersion
	id("io.spring.dependency-management") version dependencyManagementVersion
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion
	kotlin("plugin.allopen") version kotlinVersion
	kotlin("plugin.noarg") version kotlinVersion
	kotlin("plugin.jpa") version kotlinVersion

	//https://kotlinlang.org/docs/kapt.html
	kotlin("kapt") version kotlinVersion
}

group = "me.xep"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-mustache")
	implementation("org.springframework.boot:spring-boot-starter-web")

	//이거 왜 필요한지는 모르겠음
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
//		exclude(module = "junit")
		exclude(module = "mockito-core")
	}
	testImplementation("com.ninja-squad:springmockk:3.0.1")

	implementation("com.atlassian.commonmark:commonmark:0.11.0")
	implementation("com.atlassian.commonmark:commonmark-ext-autolink:0.11.0")

	kapt("org.springframework.boot:spring-boot-configuration-processor")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.Embeddable")
	annotation("javax.persistence.MappedSuperclass")
}

