@REM ----------------------------------------------------------------------------
@REM Maven Start Up Batch script
@REM ----------------------------------------------------------------------------
@echo off
setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set MAVEN_PROJECT_ROOT=%DIRNAME%

set MAVEN_WRAPPER_JAR="%MAVEN_PROJECT_ROOT%\.mvn\wrapper\maven-wrapper.jar"

@REM Execute Maven
if exist "%DIRNAME%\tools\apache-maven-3.9.6\bin\mvn.cmd" (
    "%DIRNAME%\tools\apache-maven-3.9.6\bin\mvn.cmd" %*
) else (
    mvn %*
)
