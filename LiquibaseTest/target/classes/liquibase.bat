@echo off
set CMD_LINE_ARGS=%1
java -cp "lib/*" liquibase.integration.commandline.Main %CMD_LINE_ARGS%
