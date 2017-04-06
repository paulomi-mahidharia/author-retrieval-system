# Team 3



# Phase 3
# How to run project:
  Step1 : Import the project AuthorRetrival as a maven project in Eclipse<br />
  Step2 : Run the mvn test command to check number of test cases running in terminal or eclipse<br />
  Step3 : Run main.java to start application<br/>
  Step4 : In the login and password page insert a and a<br />
  Step5 : You will redirected to search page where you can search authors<br/>


###Phase 2 - Initial System Design




**DELIVERABLES:**

1. FINAL USE CASES: [phase2/USE-CASE.pdf](https://github.ccs.neu.edu/CS5500-Spring2017/team3/blob/master/phase2/USE-CASES.pdf)
2. TEAM DIVISION: [phase2/SUB-TEAMS.pdf](https://github.ccs.neu.edu/CS5500-Spring2017/team3/blob/master/phase2/SUB-TEAMS.pdf)
3. UML DIAGRAM: [phase2/UMLdiagrams/](https://github.ccs.neu.edu/CS5500-Spring2017/team3/tree/master/phase2/UMLdiagrams)
 * [staticUML/CLASS-DIAGRAM.pdf](https://github.ccs.neu.edu/CS5500-Spring2017/team3/blob/master/phase2/UMLdiagrams/CLASS-DIAGRAM.pdf)
 * [seqDiagram/SEQUENCE-FULL-SYSTEM.pdf](https://github.ccs.neu.edu/CS5500-Spring2017/team3/blob/master/phase2/UMLdiagrams/SEQ_Diagrams/Sequence_diagram_entire_system.pdf)
 * [seqDiagram/SEQUENCE-FRONT-END.pdf](https://github.ccs.neu.edu/CS5500-Spring2017/team3/blob/master/phase2/UMLdiagrams/SEQ_Diagrams/Front-End%20Seq.pdf)
 * [seqDiagram/SEQUENCE-QUERY-ENGINE.pdf](https://github.ccs.neu.edu/CS5500-Spring2017/team3/blob/master/phase2/UMLdiagrams/SEQ_Diagrams/Query_Engine_SEQ.png)

The interfaces are defined in the project directory named dblp.
The project also contains model classes and data structures used while defining the interfaces.

4. INTERFACES : [dblp/src/main/java/com/neu/msd/dblp/service/](https://github.ccs.neu.edu/CS5500-Spring2017/team3/tree/master/dblp/src/main/java/com/neu/msd/dblp/service)
 * UserService: Interface that contains functions related to the User operations
 * SearchService: Interface that contains functions related to the Search opera
 * AuthorService: Interface that contains functions related to the Author
 * ResultService: Interface that contains functions related to the Result
 * ImportService: Interface that contains functions related to Import dataset functionality.

### The project follows git-flow process
 1. Please open a ticket first for the change you are going to work
 1. Then create a feature branch having name same as jira ticket id.
 1. Push all your changes in the feature branch.
 1. Upon development assign it to a team member for code review.
 1. Once review is successful merge the feature branch to develop.
 1. Follow same workflow for making releases and bugfixes.


5. DATABASE:
   To view how the database is structured and look up data without interacting with the application, you can connect to this AWS Instance with following username and password. 
   Please note that the credentials used below only have READ ONLY access. 

   Connection : dblp.c1lyqqia3dks.us-east-1.rds.amazonaws.com
   Username : testuser
   password : testuser
   PORT : 3306

   

