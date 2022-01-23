# Word merger
###### A project by Manfred Lambrecht for One16.

## How to run application (Docker)

To start application run ``docker compose up``
<br>
<br>
To use the application you can use ``postman`` or ``curl``
<br>

- curl command _(when in project root folder)_
<br>
`curl --location --request POST 'http://localhost:8080/api/file' --form 'file=@"src/main/resources/input.txt"'`
- Postman
<br>
create Post call to `localhost:8080/api/file`and add a body with form-data with key `file` and value the file itself.

<br>

_Total time spent on this project approx X hours._



