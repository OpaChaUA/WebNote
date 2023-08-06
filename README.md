### To run this app from IDE:
1. **with PostgreSQL db, fill in the following fields in the build configuration**
- **VM options**<br>
  *-Dspring.profiles.active=prod*<br><br>
- **Environment variables**<br>
  *DB_URL=jdbc:postgresql://localhost/**[db_name_here]**;DB_USERNAME=**[db_username_here]**;DB_PASSWORD=**[db_password_here]***
2. **with in-memory H2 db, delete the 'VM options' field's value.**<br><br>
### To run this app from executable jar file, just execute the next command (change [ ] with your data) by opening command line in directory where jar file is located:
1. **with PostgreSQL db**<br>

   *java -Dspring.profiles.active=prod -DDB_URL=jdbc:postgresql://localhost/**[db_name_here]** -DDB_USERNAME=**[db_username_here]** -DDB_PASSWORD=**[db_password_here]** -jar **[spring_app_name_here]**.jar*<br><br>

2. **with in-memory H2 db**<br>
   *java -jar **[spring_app_name_here]**.jar*