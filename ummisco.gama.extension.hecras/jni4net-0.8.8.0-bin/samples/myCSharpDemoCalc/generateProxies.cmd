@echo off
copy ..\..\lib\*.* work
..\..\bin\proxygen.exe work\HecRas_Gama.dll -wd work
cd work
call build.cmd
cd ..

echo compiling usage
javac -d work\ -cp work\jni4net.j-0.8.8.0.jar;work\HecRas_Gama.j4n.jar MyCalcUsageInJava.java
