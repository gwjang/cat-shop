import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.flywaydb.flyway") version "10.13.0"
    kotlin("kapt") version "1.9.21"
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.spring") version "1.9.21"
    kotlin("plugin.allopen") version "1.9.21"
    kotlin("plugin.jpa") version "1.9.21"
}

group = "purr"
version = "0.0.1-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    testImplementation("io.projectreactor:reactor-test")
    runtimeOnly("org.postgresql:postgresql")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    runtimeOnly("org.postgresql:r2dbc-postgresql")
    implementation("org.hibernate:hibernate-envers:6.2.3.Final")
    implementation("org.flywaydb:flyway-core")
}

val dbHost = System.getenv("DB_HOST") ?: "localhost"

flyway {
    url = "jdbc:postgresql://$dbHost:5432/cat-shop"
    user = System.getenv("DB_USERNAME")
    password = System.getenv("DB_PASSWORD")
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

tasks.getByName<BootRun>("bootRun") {
    environment.put("SPRING_PROFILES_ACTIVE", environment.get("SPRING_PROFILES_ACTIVE") ?: "local")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
