@echo off
REM Phone Inventory Management System - Run Script for Windows

echo ================================================
echo Phone Inventory Management System
echo ================================================
echo.

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo Error: Java is not installed!
    echo.
    echo Please install Java JDK 8 or higher.
    echo Visit: https://www.oracle.com/java/technologies/downloads/
    echo.
    pause
    exit /b 1
)

REM Check if javac is installed
javac -version >nul 2>&1
if %errorlevel% neq 0 (
    echo Error: Java compiler (javac) is not installed!
    echo.
    echo Please install Java JDK (not just JRE).
    echo.
    pause
    exit /b 1
)

REM Display Java version
echo Java version:
java -version 2>&1 | findstr /C:"version"
echo.

REM Check if class files exist
if not exist "CreateLoginForm.class" (
    echo Compiling Java files...
    javac *.java
    
    if %errorlevel% equ 0 (
        echo Compilation successful!
        echo.
    ) else (
        echo Compilation failed!
        echo Please check for errors above.
        pause
        exit /b 1
    )
) else (
    echo Class files found (already compiled)
    echo.
)

REM Run the application
echo Starting Phone Inventory Management System...
echo.
java CreateLoginForm

if %errorlevel% neq 0 (
    echo.
    echo Application exited with an error.
    pause
    exit /b 1
)
