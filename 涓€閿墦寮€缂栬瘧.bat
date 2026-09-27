@echo off
chcp 65001 >nul
setlocal
set "ROOT=%~dp0"
echo ========================================
echo   润城商贸手机进销存 - 安卓APP
 echo ========================================
echo.
where studio64.exe >nul 2>nul
if %errorlevel%==0 (
  echo 正在用 Android Studio 打开工程...
  start "" studio64.exe "%ROOT%"
  exit /b 0
)
if exist "%LOCALAPPDATA%\Programs\Android Studio\bin\studio64.exe" (
  start "" "%LOCALAPPDATA%\Programs\Android Studio\bin\studio64.exe" "%ROOT%"
  exit /b 0
)
if exist "C:\Program Files\Android\Android Studio\bin\studio64.exe" (
  start "" "C:\Program Files\Android\Android Studio\bin\studio64.exe" "%ROOT%"
  exit /b 0
)
echo 没有找到 Android Studio。
echo 请先安装 Android Studio，然后再次双击本文件。
pause
