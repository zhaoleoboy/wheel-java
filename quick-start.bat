@echo off
echo [Pre-Requirement] Makesure install JDK 8.0+ and set the JAVA_HOME.
echo [Pre-Requirement] Makesure install Maven 3.0.3+ and set the PATH.

set MVN=mvn
set MAVEN_OPTS=%MAVEN_OPTS% -XX:MaxPermSize=128M

echo [Step 1] Install all springside modules to local maven repository.
call %MVN% clean install
if errorlevel 1 goto error

echo [Step 2] run wheel project in dev mode.
echo 不执行测试用例，也不编译测试用例类。
mvn spring-boot:run
if errorlevel 1 goto error

goto end
:error
echo Error Happen!!
:end
pause