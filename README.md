# University Management Application
This application would be responsible for user management, academic management, sending notifications (real-time and stored in database), and monitoring health of all microservices.

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

### Grade Management Service
Endpoints:
- /api/grades/semester/{semester}: get grades of user for specific semester.
- /api/grades/semester/{semester}/course/{course}: get grades of user for specific semester and course

### Academic Manager Service
Endpoints:
- /api/attendance/semester/{semester}: get attendance of user for specific semester.
- /api/attendance/course/{courseCode}: get attendance of user for specific course.
- /api/attendance/triggerNotification: triggers a notification to the notification service for a specific student.
- /api/courses/{courseId}: get specific course by Id.
- /api/courses: create new course.
