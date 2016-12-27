if not exist .\doc md .\doc 
javadoc -d ./doc -sourcepath ^
sources sources/ua/shpota/tasks/controller/TaskController.java ^
        sources/ua/shpota/tasks/model/*.java ^
        sources/ua/shpota/tasks/view/*.java
pause