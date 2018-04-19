# movie-database-and-gui

1. README
To add any record simply hit the add button for the record you would like to add.
To remove any record highlight the record you would like remove then click the remove button.
To update any record highlight the record you would like to update then click the update button.
To read data from the database hit any of the query buttons at the bottom of the window, you may
also search based on title/name/role for all the data.

To run this you will need a Java IDE (We used IntelliJ also had a bit of trouble using eclipse).
Also a MySQL server, please make sure the path to your MySQL is updated within the java code to be able to run it
that will be within the "databaseConnection" class.

All you have to do is run the main function in the Table class and you can begin to interact with the system, all functionality
provided by the buttons.

UML

/Users/brennankinnel/Desktop/Screen Shot 2018-04-19 at 6.08.36 PM.png


EER

/Users/brennankinnel/Desktop/Screen Shot 2018-04-19 at 5.57.12 PM.png

Lessons Learned:
We ended up changing our schema a lot from the orginal design due to the fact that the front end was developed first. in the future
I would make sure the schema, functions, and procedures are updated first before starting the front end so less rework is needed

Future Work:
It would be cool to embed the trailers of the movies in the front end so you can watch the youtube trailers right in the application.
I would also like to simplify interactions for adding/removing/updating so that you can update without the numeric ID of each of
those things because right now its a little difficult to do those things.
