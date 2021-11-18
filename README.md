JAVA EE / JAX-RS - Labb

● CRUD-funktionalitet ska implementeras (Create, Read, Update, Delete)
● Data om en student ska kunna hämtas med efternamn som en Query Parameter
● När en ny student ska läggas till, är alla fält obligatoriska utom telefonnummer
● Anropen ska returnera meningsfulla Response Codes
● Skapa minst en egen exception
● Felhantering ska finnas för varje CRUD-metod, och felmeddelande ska returneras i
JSON-format

● Jag kommer testa eran lösning med Insomnia, så skapa en README.md-fil i erat
repository, där ni beskriver endpoints, parametrar och JSON-bodies ser ut.
● README.md-filen ska också innehålla kort beskrivning om vem som gjort vad (ifall
ni jobbat i grupp), samt om ni stötte på problem som ni inte förstod hur man ska
lösa.


PROJEKT BESKRIVNING:
....
INSOMNIA TEST

First the database is empty. If you try to get all you get the message. The db is empty.

POST CreateStudent:
You create a Student with this JSON format. And this http address.

All fields except phoneNumber are mandatory. If you try to make a student without email or name you
get 400 bad request and a message.
The student id is automatically created.

http://localhost:8080/student-management-system/api/v1/students

{
"firstName": "Student example",
"lastName": "StudentLastName",
"email": "student@testmail.com",
"phoneNumber": "12345"
}

You should get 201 created if a student is successfully created.

GET GetAllStudents:
You can now get info of a student by get all students in the db.

http://localhost:8080/student-management-system/api/v1/students


GET GetStudentById:
Or get one student by id. You can search another student by changing the last id number of the http address.

http://localhost:8080/student-management-system/api/v1/students/1


GET GetStudentWithLastName:
Or you can search for students with a specific last name. You write in the Query lastName and the name
you want to search.

http://localhost:8080/student-management-system/api/v1/students/getbylastname


PATCH UpdateStudent:
You can update students firstName. You can update a different student by changing the last id number
of the http address. You write in Query firstName and the name you want to update to.

http://localhost:8080/student-management-system/api/v1/students/1


DELETE DeleteStudentWithId:
You can delete a student by changing the last id number of the http address.

http://localhost:8080/student-management-system/api/v1/students/delete/1


If you try to get student that don't exist you get 404 not found and a message.
If you try to delete or update a student that don't exist you get
400 bad request and a message.
