# Инженерное и архитектурное развитие

Репозиторий для учебных проектов по Computer Science, разработке и архитектуре.

## Проекты

- [Computer Science](computer-science/) — алгоритмы, структуры данных и задачи на Java. [План обучения](computer-science/CS-STUDY-PLAN.md)
- [JDK Internals](jdk-internals/README.md) — глубокое изучение OpenJDK 25, HotSpot и стандартной библиотеки.

## Сборка и тесты

Запускай Gradle на JDK 21 из корня репозитория. Для сборки `jdk-internals` понадобится установленный JDK 25, который Gradle использует как toolchain этого подпроекта.

```sh
./gradlew build
./gradlew :computer-science:test
./gradlew :jdk-internals:build
```

Первая команда собирает все проекты и запускает их тесты. Две следующие запускают задачи отдельных подпроектов. В Windows используй `gradlew.bat`.

В IntelliJ IDEA открой корень репозитория как Gradle-проект и выбери JDK 21 для Gradle JVM.

## Как добавить проект

Создай каталог рядом с `computer-science`, добавь в него `build.gradle.kts` и подключи каталог в [settings.gradle.kts](settings.gradle.kts). Например, для проекта `architecture`:

```kotlin
include("architecture")
```

Проверь подключение командой `./gradlew projects`.
