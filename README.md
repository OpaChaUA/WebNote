### To run this app from executable jar file, just execute the next command by opening command line in directory where jar file is located:
1. **with PostgreSQL db**<br><br>
   *java -Dspring.profiles.active=prod -DDB_URL=jdbc:postgresql://localhost/<<DB_NAME_YOU_HAVE_CREATED>> -DDB_USERNAME=postgres -DDB_PASSWORD=postgres -jar SpringAppName.jar*<br><br>
2. **with in-memory db**<br><br>
   *java -jar SpringAppName.jar*

### To run this app from IDE:
1. **with PostgreSQL db, fill in the following fields in the build configuration**
- **VM options**<br>
  *-Dspring.profiles.active=prod*<br><br>
- **Environment variables**<br>
  *DB_URL=jdbc:postgresql://localhost/<<DB_NAME_YOU_HAVE_CREATED>>;DB_USERNAME=postgres;DB_PASSWORD=postgres*
2. **with in-memory db, delete the 'VM options' field's value.**<br><br>