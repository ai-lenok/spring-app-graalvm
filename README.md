# Example of using GraalVM with Spring boot 3

Example implementation of an application on GraalVM

## Requirements

- Java 17
- Docker
- [Helm](https://helm.sh/ru/docs/intro/install/) (optional)

## Build native image

```shell
docker image build --tag spring-app-graalvm:1 --file Dockerfile.native .
```

## Running an Application on Kubernetes Using Helm

### Deploy the first version of the application

```shell
helm install addressbook helm-chart
```

### Application update

```shell
helm upgrade addressbook helm-chart
```

### Return to previous version

#### View installation history

```shell
helm history addressbook
```

#### Return to version 5

It is implied that there is a 5th revision version of the revision

```shell
helm rollback addressbook 5
```

### Delete application

```shell
helm uninstall addressbook
```

### Idle start

Run any helm command

With log output, but without real actions

```shell
helm upgrade addressbook helm-chart --dry-run
```

Often used in conjunction with `--debug`

```shell
helm upgrade addressbook helm-chart --dry-run --debug
```

## Checking work

[The file](http/addressbook.http) contains example requests in IDEA REST Client notation