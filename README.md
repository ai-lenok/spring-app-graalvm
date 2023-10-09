# Пример использования GraalVM вместе с Spring boot 3

## Реквизиты

- Java 17
- Docker
- Helm

## Сборка

```shell
docker image build --tag spring-app-graalvm:1 --file Dockerfile.native .
```

## Запуск приложения в Kubernetes с помощью Helm

### Deploy первой версии приложения

```shell
helm install addressbook helm-chart
```

### Обновление приложения

```shell
helm upgrade addressbook helm-chart
```

### Возврат к предыдущей версии

#### Посмотреть историю установок

```shell
helm history addressbook
```

#### Вернуться к 5-й версии

Подразумевается, что в истории есть 5-я версия

```shell
helm rollback addressbook 5
```

### Удалить приложение

```shell
helm uninstall addressbook
```

### "Холостой" запуск

Запуск любой команды helm

С выводом логов, но без реальных действий

```shell
helm upgrade addressbook helm-chart --dry-run
```

Часто применяется вместе с `--debug`

```shell
helm upgrade addressbook helm-chart --dry-run --debug
```

## Проверка работы

В каталоге [http](http/) лежит пример запросов в нотации IDEA REST Client