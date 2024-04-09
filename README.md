# Тестовое задание для Гринатом

 Для запуска приложения следует использовать docker-compose
- По умолчанию все endpoint authenticated (Jwt bearer token)
- Api prefix api/v1
- Есть миграции, которые запускаются при старте приложения (seed data)
- Документацию можно открыть по пути /swagger-ui/index.html
- Дефолтный пользователь - user, пароль password,
- Админ пользователь - user1, password (Ему доступны изменения любых
   месседжей, благодаря spring SpEl)
