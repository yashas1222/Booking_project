start "SERVICE-REGISTRY" cmd /k "cd service-registry && mvn spring-boot:run"
start "AUTH-SERVICE" cmd /k "cd auth-service && mvn spring-boot:run"
start "USER-SERVICE" cmd /k "cd user-service && mvn spring-boot:run"
start "API-GATEWAY" cmd /k "cd api-gateway && mvn spring-boot:run"
