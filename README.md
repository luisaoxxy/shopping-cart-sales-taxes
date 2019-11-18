To run the program you should excute in root folder: 

mvn spring-boot:run -Dspring-boot.run.arguments="FILENAMES"

Where FILENAME name could be one or all of this fields:

	* input1.txt
	* input2.txt
	* input3.txt
	
For example to run the full test you should run:

mvn spring-boot:run -Dspring-boot.run.arguments="input1.txt,input2.txt,input3.txt"

 
