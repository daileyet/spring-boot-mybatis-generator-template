@echo off
set MODULE_NAME=#MODULE_NAME#
set APP_NAME=#BUILD_NAME#.#BUILD_SUFFIX#
set LIB_PATH="%~dp0"
cd "%LIB_PATH%\.."
set RUN_HOME="%cd%"
set MODULE_ARGS="#MODULE_ARGS#"
@echo start app %MODULE_NAME%
@ java -jar "%RUN_HOME%/lib/%APP_NAME%" %MODULE_ARGS%