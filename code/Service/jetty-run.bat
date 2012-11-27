@echo off

echo Starting Lift, firing up Jetty ...
call "%~dp0\sbt.bat" --no-pause --loop %* ~lift