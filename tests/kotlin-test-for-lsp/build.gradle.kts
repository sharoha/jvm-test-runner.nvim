plugins {
	kotlin("jvm") version "2.2.21"
	kotlin("plugin.spring") version "2.2.21"
	kotlin("plugin.jpa") version "2.2.21"
	id("org.springframework.boot") version "4.0.4"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

val intTest by sourceSets.creating {
	kotlin.srcDir("intTest/kotlin")
	resources.srcDir("intTest/resources")

	compileClasspath += sourceSets.main.get().output + configurations.testRuntimeClasspath.get()
	runtimeClasspath += output + compileClasspath
}

configurations[intTest.implementationConfigurationName].extendsFrom(configurations.testImplementation.get())
configurations[intTest.runtimeOnlyConfigurationName].extendsFrom(configurations.testRuntimeOnly.get())

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-webmvc")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("tools.jackson.module:jackson-module-kotlin")
	runtimeOnly("org.postgresql:postgresql")

	testImplementation(platform("org.testcontainers:testcontainers-bom:1.21.3"))
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:postgresql")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	add(intTest.implementationConfigurationName, platform("org.testcontainers:testcontainers-bom:1.21.3"))
	add(intTest.implementationConfigurationName, "org.springframework.boot:spring-boot-starter-test")
	add(intTest.implementationConfigurationName, "org.springframework.boot:spring-boot-starter-webmvc-test")
	add(intTest.implementationConfigurationName, "org.jetbrains.kotlin:kotlin-test-junit5")
	add(intTest.implementationConfigurationName, "org.testcontainers:junit-jupiter")
	add(intTest.implementationConfigurationName, "org.testcontainers:postgresql")
	add(intTest.runtimeOnlyConfigurationName, "org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

val integrationTest = tasks.register<Test>("integrationTest") {
	description = "Runs integration tests."
	group = "verification"
	testClassesDirs = intTest.output.classesDirs
	classpath = intTest.runtimeClasspath
	shouldRunAfter(tasks.test)
	useJUnitPlatform()
}

tasks.check {
	dependsOn(integrationTest)
}
