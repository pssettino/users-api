# users-api


Api para gestion de RRHH

* To run all tests (unit and integration)

        ./gradlew test

* To run App

        ./gradlew run


## Endpoints

### Get All Employee

`curl --request GET \
  --url http://localhost:8080/users-api/api/employees/`

Response:

`{
{
  "documentNumber": 33633264,
  "documentType": "DNI",
  "fullName": "Pablo Settino",
  "email": "psettino@gmail.com",
  "startDate": "2010-08-30T00:00:00.000+0000",
  "endDate": null,
  "status": true,
  "role": "GERENTE",
  "address": {
    "address": "Murguindo y Ercilla",
    "localidad": "CABA",
    "provincia": "Buenos Aires",
    "codigoPostal": null
  },
  "telephones": null,
  "cuil": "20336332649",
  "birthday": null,
  "shiftId": 1,
  "imageProfile": "/profile/33633264/profile.png"
}
 }`
