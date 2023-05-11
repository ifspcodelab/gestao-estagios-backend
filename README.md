# Internship Management Back-end

![Internship Management Back-end image](/readme-files/GestaoEstagioGif.gif)

The Internship Management project aims to contribute to a better organization of internships 
at the Federal Institute of Education, Science and Technology of São Paulo. 
The application centralizes the information related to the sending of 
activity plans, reports, term of realization and request for orientation of internships.

The project work with the [Internship Management Front-end](https://github.com/ifspcodelab/gestao-estagios-frontend).

## How to Run

1. Clone this repo

2. Install [Open JDK 11](https://adoptium.net/temurin/releases/?version=11)

3. Install [PostgreSQL 13](https://www.postgresql.org/download/)

4. The database is named "gestao_estagio_backend". You can create it by IDE, pgadmin or psql as this way:  `CREATE DATABASE gestao_estagio_backend`\
   <br> On `Windows` operating system, you can create the database in the prompt or Powershell. Navigate to the PostgreSQL bin directory and execute the following command:

    <br>
   
   With PowerShell
    ```
    ./psql -U postgres -c "CREATE DATABASE gestao_estagio_backend"
    ```
    With prompt
     ```
    psql -U postgres -c "CREATE DATABASE gestao_estagio_backend"
    ```

5. Set environment variables:

   ##### For local and production
* `APP_ADMIN_EMAIL`: E-mail of the admin user, ex: `admin@email.com`
* `APP_ADMIN_PASSWORD`: Admin password to login, you can create the hash in `https://bcrypt-generator.com/`
* `APP_ADMIN_REGISTRATION`: Admin registration to login, ex: 'SP1234567'
* `CLOUD_NAME`: Name of Cloudinary product environment, ex: `dmnumhilq`
* `CLOUDINARY_KEY`: Unique API Key of Cloudinary product environment
* `CLOUDINARY_SECRET`: Signature credential element required to use Cloudinary API
* `DATASOURCE_DRIVER_CLASS_NAME`: Name of JDBC driver to communicate with database, ex: `org.postgresql.Driver`
* `DATASOURCE_PASSWORD`: PostgreSQL database password
* `DATASOURCE_URL`: PostgreSQL Datasource URL, ex: `jdbc:postgresql://localhost:5432/gestao_estagios_backend`
* `DATASOURCE_USERNAME`: PostgreSQL database username
* `FRONTEND_URL`: Frontend URL to communicate, ex: `http://localhost:4200`
   
  ##### For local
* `PORT`: Standalone application HTTP port, ex: `8080`

   ##### For production
* `JWT_SECRETKEY`: secret key used to encode and decode Json Web Tokens
* `MAIL_PASSWORD`: Login password to SMTP server
* `MAIL_PORT`: SMTP server port, ex: `587`
* `MAIL_SERVER`: SMTP server host, ex: `smtp.gmail.com`
* `MAIL_USERNAME`: Login user to SMTP server, ex: `admin@email.com`

6. Run with gradle wrapper:
```
./gradlew bootRun
```

## How to Use

You can use SQL and Postman data for testing the project in your local environment, located in the "readme-files" directory, at the root of the project.
To use SQL data, you can copy and paste the commands into a query console and run them. 
To use Postman collection, you need to download the file and import it into the software.

**Note: users password is "Senha1234".**

#### Postman's warnings
- There are two collections, the one with variables and the other without. The one with variables requires
  that you import the Postman environment file and keep it selected in Postman's environment option.

- The Postman's collection with variables requires authentication with bearer token in every request, which
  requires a valid access token. You can create one by sending a login request. PS: the lack of a valid
  token will make the system return a token expired error message or similar.

- In regard to Postman's collection without variables, you must put UUIDs in their path manually
  where is needed (monthly report, draft monthly report, final report, realizion term, etc).

- The best order to send the requests is top to bottom sequentially.

- Some requests need files attached to their body, such as some of activity plan, monthly report, draft
  monthly report, final monthly report and realization term. For the sake of simplifying testing,
  all monthly reports types (including draft and final) are being represented by the same sample file.

- Either if you get a 500 HTTP response while requesting actions that have files in their  bodies or if you
  receive a multipart error message, reupload the file.

## Tech Stack

- Java application - JDK 11
- Spring Boot 2.5.3
- PostgreSQL 13