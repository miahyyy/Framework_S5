@echo off

:: Variables
set APP_NAME=FrameworkServlet
set SRC_DIR=src\main\java

set BUILD_DIR=build
set LIB_DIR=lib
set SERVLET_API_JAR=%LIB_DIR%\servlet-api.jar

:: Nettoyage et création du répertoire temporaire
if exist %BUILD_DIR% (
    rmdir /s /q %BUILD_DIR%
)
mkdir %BUILD_DIR%\WEB-INF\classes

:: Compilation des fichiers Java avec le JAR des servlets
dir /b /s %SRC_DIR%\*.java > sources.txt
javac -cp "%SERVLET_API_JAR%" -d %BUILD_DIR%\WEB-INF\classes @sources.txt
del sources.txt

:: Générer le fichier .war dans le dossier build
cd %BUILD_DIR%
jar -cvf %APP_NAME%.war *
cd ..

echo.
echo Déploiement terminé
echo.
pause
