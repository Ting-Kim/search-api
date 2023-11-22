plugins {
	java
	id("org.springframework.boot") version "2.5.15"
	id("io.spring.dependency-management") version "1.1.3"
}

group = "com.kth"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_11
}

repositories {
	mavenCentral()
}

dependencies {
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-elasticsearch
	implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch:2.5.15")
	implementation("org.springframework.boot:spring-boot-starter-web")

	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
