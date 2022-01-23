# Word merger
###### A project by Manfred Lambrecht for One16.

## How to run application (Docker)

To start application run ``docker compuse build ; docker compose up`` _(in the root folder of the project)_
<br>
<br>
To use the application you can use ``postman`` or ``curl``
<br>

- curl command _(when in project root folder)_

``curl --location --request POST 'http://localhost:8080/api/file' --form 'file=@"src/main/resources/input.txt"' >> output.txt``

- Postman

Create a post call to `localhost:8080/api/file` and add a body with form-data with a key named `file` and as value the file itself.

<br>

_Total time spent on this project approx 6 hours._



