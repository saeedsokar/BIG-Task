#BIG-WEB
This Repo. contains a script to open https://arielkiell.wixsite.com/interview

* use the following command to checkout "git checkout main"
* To Run the solution: open the root path of the project then try this "mvn clean package"

#Description:
1. Script will open the web portal, and scroll down to find a book. you can set the book name inside the main test class "AddBookTest".
2. Select Shop from menu
3. select best seller product/item
4. pick a color
5. increment the items count from input field by clicking on "up arrow"
6. click on view cart 
7. click on checkout
8. check the price

#Features:
1. The solution is developed in JAVA and Selenium.
2. Reports are generated per each test case.
3. A screenshot is captured per each test case.
4. The solution is following -> Page object model design pattern with simple SOLID principles to be adoptable to changes and provide usability.
5. The solution allows parallel testing through testng.xml file, ** however, the solution isn't working with Firefox
6. Dockerfile is configured, but not working

#Setup
1. make sure that Maven is downloaded and installed
2. make sure to project JAVA SDK from project settings = 20
3. Chrome and Firefox drivers are located in the solution
4. From the root path of the checkout main branch locally, type in terminal "mvn clean package"
5. after execution, open report in chrome from reports directory in the solution
6. after execution, open screenshots from /src/test/resources/screenshots
   
