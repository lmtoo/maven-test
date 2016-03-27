@echo off
color 0a
set MN_HOME=%~dp0
del %MN_HOME%*lastUpdate* /a/s
echo pause>nul exit
