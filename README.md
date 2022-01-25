# A Merchants Guide to the Galaxy #
Program to solve the Merchants Guide to the Galaxy Problem.  
The problem can be read in [the PROBLEM.md file](PROBLEM.md).

##Requirements
Java 11

##Assumptions
Assumptions that i made while solving the Problem:  
- Words are not case sensitive
- Materials are not case sensitive

##Building
If you do not want to build, there is a finished .jar in the "example" folder.

A maven wrapper is included in the project.  
To build, run "./mvnw clean install" in the project folder.
This will download any needed dependencies and create a .jar in the "target" folder.  

##Usage
1) Locate the MGTTG.jar file folder. This is either /target, if you built yourself, or /example, if you want to use the pre-built jar.  
2) Open the folder in a terminal of your choice.  
3) Launch the Program: 
   - Either launch the program with "java -jar MGTTG.jar"
   - Or choose a file to read from with "java -jar MGTTG.jar -f FILENAME", where FILENAME is the relative path to the file. To avoid problems, try to have the file in the same directory as the jar. There is an example_input.txt included in the example folder, which contains the example input from the [Problem](PROBLEM.md).
4) If you launched the program without a file parameter, try typing "help" to list all the available commands!
##Disclaimer
The Itemis Logo is property of the [itemis AG](https://www.itemis.com).  
I am neither sponsored nor associated with the itemis AG and will remove the ASCII
representation of the logo upon request.