# TeleKORB

TeleKORB это веб приложение по предоставлению услуг мобильной связи.

## Возможности

- Заказ сим-карты
- Выбор красивого номера при заказе сим-карты
- Личный кабинет
- Выбор одного из готовых тарифов
- Настройка персонального тарифа

## Установка и запуск

Для запуска TeleKORB необходимо иметь Java 21 и правильно настроенную переменную
среды `JAVA_HOME`. Пример установки Java 21 для систем Ubuntu:
```bash
$ wget https://download.oracle.com/java/21/latest/jdk-21_linux-x64_bin.deb
$ sudo apt install ./jdk-21_linux-x64_bin.deb
```

После этого следует скопировать репозиторий на свой диск:
```bash
$ git clone https://github.com/notLinode/TeleKORB
```

Затем вы можете открыть папку с репозиторием:
```bash
$ cd TeleKORB/
```

Для того, чтобы запустить сервер, необходимо сделать `mvnw` запускаемой 
программой:
```bash
$ chmod u+x mvnw
```

Теперь вы можете запустить сервер (по-умолчанию сервер будет доступен по 
адресу `localhost/telekorb/`):
```bash
$ ./mvnw spring-boot:run
```

## GitHub Actions

![Tests](https://github.com/notLinode/TeleKORB/actions/workflows/test.yml/badge.svg)
![Build](https://github.com/notLinode/TeleKORB/actions/workflows/build.yml/badge.svg)

| Workflow    | Назначение                                                                                                            | Условия запуска                |
| ----------- |-----------------------------------------------------------------------------------------------------------------------|--------------------------------|
| `test.yml`  | Автоматически запускает тесты с помощью `mvn test`, чтобы убедиться, что изменения не сломали функциональность.       | При каждом push в ветку `main` |
| `build.yml` | Выполняет сборку приложения Spring Boot с помощью `mvn clean install` и загружает полученный `.jar` файл в артефакты. | При каждом push в ветку `main` |
