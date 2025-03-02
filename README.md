# University Management Application
This application would be responsible for user management, academic management, sending notifications (real-time and stored in database), monitoring health of all microservices.

## Microservices
### User Management Service
Endpoints:
- /api/auth/login: Handles user login.
- /api/users: Creates new user.
- /api/users/{id}: Update user details.

### Notification Service
Endpoints:
- /notifications/send: Used for sending the notification to client from a server.
- /notifications/unread: Fetch all the unread notifications of a client from the database.
- /notifications/mark-as-read/{id}: Mark the notification as read by the user.
- ws:\<domain\>:\<port\>:ws/websocket: Used to connect to the server to receive notifications intended for the user in real time.
