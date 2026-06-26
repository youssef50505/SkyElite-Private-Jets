@echo off
setlocal

echo ========================================================
echo SkyElite Private Jets - Database Seeder
echo ========================================================
echo.

where node >nul 2>nul
if %ERRORLEVEL% neq 0 (
    echo [ERROR] Node.js is not installed. Please install Node.js to continue.
    pause
    exit /b 1
)

cd db-seeder
echo Installing Database Seeder dependencies...
call npm install >nul 2>&1
echo Running Database Seeder...
node seed.js
cd ..
echo.
echo Seeding Complete!
pause
