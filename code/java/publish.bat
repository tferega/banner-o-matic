@echo off

echo Publishing project...
call "%~dp0\sbt.bat" --no-jrebel %* clean publish
