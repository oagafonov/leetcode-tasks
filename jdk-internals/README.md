# JDK Internals

Цель трека — научиться объяснять работу OpenJDK от компиляции `.java` до исполнения машинного кода, сборки мусора, планирования потоков и взаимодействия с ОС. JLS и JVMS описывают гарантии платформы. Исходники HotSpot и стандартной библиотеки показывают, как OpenJDK их реализует. Для разбора исходников зафиксирован тег `jdk-25.0.3+9`. [Спецификации Java SE 25](https://docs.oracle.com/en/java/javase/25/docs/specs/index.html) · [Исходники OpenJDK 25](https://github.com/openjdk/jdk25u/tree/jdk-25.0.3%2B9)

Ориентир для первого прохода — 8–10 месяцев при 6–8 часах в неделю. План предполагает опыт программирования на Java. C++, ассемблер и устройство ОС разбираем по мере чтения исходников.

## Gradle

Запускай Gradle на JDK 21. Для компиляции и запуска тестов подпроекта Gradle использует отдельный toolchain JDK 25. Учебный Java-код размещаем в `jdk-internals/src/main/java`, тесты — в `jdk-internals/src/test/java`.

Из корня репозитория:

```sh
./gradlew projects
./gradlew -q javaToolchains
./gradlew :jdk-internals:build
```

Для опытов с конкретной сборкой указывай её `bin/java`, `bin/javac` и `bin/javap` явно. Для лабораторных по HotSpot понадобится отдельная сборка OpenJDK с уровнем отладки `fastdebug`.

## План обучения

### 1. Сборка и запуск JDK

Структура репозитория, boot JDK, сборки `release` и `fastdebug`, устройство образа JDK, нативный launcher, создание VM и вход в `main`.

**Практика.** Собрать `fastdebug`, запустить целевой тест через `jtreg` и пройти в нативном отладчике от launcher до `Threads::create_vm`. [Сборка OpenJDK](https://github.com/openjdk/jdk25u/blob/jdk-25.0.3%2B9/doc/building.md) · [Тестирование OpenJDK](https://github.com/openjdk/jdk25u/blob/jdk-25.0.3%2B9/doc/testing.md)

### 2. `javac`, `.class` и байткод

Парсинг, AST, разрешение имён и типов, стирание типов и преобразования перед генерацией байткода. Constant pool, локальные переменные, operand stack, `StackMapTable`, исключения, инструкции `invoke*` и `invokedynamic`.

**Практика.** Предсказать байткод обобщённого метода, лямбды и `try-with-resources`, сверить с `javap -v -c`. Сгенерировать и запустить простой класс через Class-File API. [JVMS, главы 2, 4 и 6](https://docs.oracle.com/javase/specs/jvms/se25/html/) · [Class-File API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/classfile/package-summary.html)

### 3. Жизнь класса внутри VM

Загрузчики и идентичность класса, verification, preparation, resolution, `<clinit>`, `Klass`, metaspace и выгрузка классов. JPMS, runtime image, CDS и AOT-кэш.

**Практика.** Загрузить один `.class` двумя загрузчиками, объяснить результат `instanceof`, проследить инициализацию и выгрузку по логам. Найти соответствующие пути в HotSpot. [JVMS, глава 5](https://docs.oracle.com/javase/specs/jvms/se25/html/jvms-5.html) · [Исходники HotSpot](https://github.com/openjdk/jdk25u/tree/jdk-25.0.3%2B9/src/hotspot/share)

### 4. Интерпретатор, объекты и память

Фреймы и вызовы методов, template interpreter и переходы в runtime, safepoints и thread-local handshakes. `oop`, заголовки объектов, сжатые ссылки, TLAB, куча, нативные стеки и code cache.

**Практика.** В режиме `-Xint` проследить `new` и `invokevirtual`. Показать, где выделяется объект, где лежат метаданные его класса и как VM находит ссылки в стеке. [Исходники HotSpot](https://github.com/openjdk/jdk25u/tree/jdk-25.0.3%2B9/src/hotspot/share)

### 5. JIT и машинный код

Профилирование, tiered compilation, C1/C2, OSR, speculative inlining, C2 IR, escape analysis, scalar replacement, intrinsics, генерация кода и deoptimization.

**Практика.** Прогреть одну точку вызова с одним типом, затем подать другой. По логам компиляции и машинному коду объяснить, что было встроено и что произошло при смене профиля. Провести измерения через JMH. [Исходники C2](https://github.com/openjdk/jdk25u/tree/jdk-25.0.3%2B9/src/hotspot/share/opto) · [JMH](https://github.com/openjdk/jmh)

### 6. Garbage collection

GC roots и достижимость, обработка `Reference`, marking, evacuation, compaction, barriers и remembered sets. Serial и Parallel для сравнения, затем подробно G1, ZGC и Shenandoah.

**Практика.** На одной нагрузке независимо менять скорость аллокаций и размер живых данных. По GC-логам и JFR объяснить паузы, работу concurrent-фаз и разницу между сборщиками. [Руководство по GC в JDK 25](https://docs.oracle.com/en/java/javase/25/gctuning/)

### 7. JMM, синхронизация и потоки

Happens-before, safe publication, `final`, `volatile`, `VarHandle`, атомики и барьеры CPU. Мониторы, `park/unpark`, AQS, ForkJoinPool, platform и virtual threads, continuations, carriers и pinning.

**Практика.** Предсказать допустимые исходы теста на публикацию объекта, проверить через `jcstress` и сопоставить результаты с JLS. По JFR и дампу потоков объяснить, где ждёт виртуальный поток и чем занят его carrier. [JLS, глава 17](https://docs.oracle.com/javase/specs/jls/se25/html/jls-17.html) · [jcstress](https://github.com/openjdk/jcstress)

### 8. Стандартная библиотека и граница с ОС

Коллекции, `String`, Stream API, reflection и method handles. NIO, сокеты, TLS, `mmap`, JNI и Foreign Function & Memory API. Отдельные разборы JCA/JSSE, локалей, XML и desktop-модулей.

**Практика.** Разобрать `HashMap.put` при коллизии и вызов через `MethodHandle`. Проследить чтение через NIO до платформенного кода. Для каждого случая найти тесты OpenJDK, которые проверяют это поведение. [Исходники JDK](https://github.com/openjdk/jdk25u/tree/jdk-25.0.3%2B9/src) · [Спецификации JDK 25](https://docs.oracle.com/en/java/javase/25/docs/specs/index.html)

### 9. Диагностика и изменение OpenJDK

`jcmd`, JFR, unified logging, heap/thread dumps, Native Memory Tracking, Java agents, JVMTI и Serviceability Agent.

**Практика.** Разобрать рост памяти или скачок задержки в учебном процессе. Внести небольшое изменение в HotSpot или библиотеку, пересобрать JDK и проверить поведение целевым тестом `jtreg`. [Руководство по диагностике](https://docs.oracle.com/en/java/javase/25/troubleshoot/) · [`jcmd`](https://docs.oracle.com/en/java/javase/25/docs/specs/man/jcmd.html)

## Как проводить занятия

1. Сформулировать конкретный вопрос и предсказать результат минимального эксперимента.
2. Прочитать нужный фрагмент спецификации и найти соответствующий путь в исходниках.
3. Запустить эксперимент и сохранить версию JDK, архитектуру, команду и наблюдаемый результат.
4. Изменить одно условие, сделать новый прогноз и объяснить расхождения, если они появились.

**Первое занятие.** Выяснить, что происходит между `java Main` и первой инструкцией `main`. Взять класс со статическим инициализатором, найти `<clinit>` и `main` через `javap -v -c`, проверить порядок загрузки и инициализации с `-Xlog:class+load,class+init` и найти точку входа в HotSpot.

**Итоговая работа.** Взять небольшую программу с полиморфным вызовом, аллокациями и виртуальным потоком. Проследить её от `.java` до машинного кода, объяснить создание и сборку объектов и разобрать ожидание потока. Подтвердить выводы байткодом, исходниками и диагностикой, затем изменить программу и проверить прогноз о последствиях изменения.
