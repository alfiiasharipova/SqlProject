# SqlProject

Проект тестирования логина

## Начало работы

### Запуск контейнера с базой

```
docker-compose up
```

### Запуск SUT

```
java -jar app-deadline.jar -P:jdbc.url=jdbc:mysql://localhost:3306/app -P:jdbc.user=app -P:jdbc.password=9mREsvXDs9Gk89Ef
```
