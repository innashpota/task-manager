if not exist .\build\classes md .\build\classes 
javac -d ./build/classes ^
-sourcepath sources sources/ua/shpota/tasks/controller/TaskController.java ^
sources/ua/shpota/tasks/model/*.java ^
sources/ua/shpota/tasks/view/*.java
pause