call  build.bat
if not exist .\build\test-classes md .\build\test-classes
javac -d ./build/test-classes ^
-classpath "lib/*;build/tasks.jar" ^
-sourcepath tests tests/ua/shpota/tasks/model/*.java