find * -name "*.java" > source.txt
javac -sourcepath . @source.txt
