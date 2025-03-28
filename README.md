# API User Management

## Test de l'authentification
1. Enregistrez un utilisateur via POST `/api/auth/signup`
2. Connectez-vous via POST `/api/auth/login` pour obtenir un JWT
3. Utilisez le JWT dans le header `Authorization: Bearer <token>` pour accéder aux routes protégées

## Accès à la documentation
- Swagger UI: http://localhost:3300/swagger-ui.html

## Lancer l'API
- `mvn sprinboot:run`
