@echo off
setlocal

echo ========================================================
echo SkyElite Private Jets (Ascend Global) - Robust Startup
echo ========================================================
echo.

echo [1/4] Checking Prerequisites...
where node >nul 2>nul
if %ERRORLEVEL% neq 0 (
    echo [ERROR] Node.js is not installed. Please install Node.js to continue.
    pause
    exit /b 1
)

where java >nul 2>nul
if %ERRORLEVEL% neq 0 (
    echo [ERROR] Java is not installed. Please install Java (JDK 21+) to continue.
    pause
    exit /b 1
)
echo [OK] Prerequisites found.
echo.

echo [2/4] Initializing and Seeding Database...
cd db-seeder
echo Installing Database Seeder dependencies...
call npm install >nul 2>&1
echo Running Database Seeder...
node seed.js
cd ..
echo.

echo [3/4] Starting Spring Boot Backend...
echo The backend will start in a new window.
start "Backend (Spring Boot)" cmd /k "cd backend && .\mvnw.cmd spring-boot:run"

echo.
echo [4/4] Preparing Angular Frontend...
cd frontend
echo Installing Frontend dependencies (this may take a minute on the first run)...
call npm install
cd ..

echo Starting Angular Frontend...
echo The frontend will start in a new window and automatically open your browser.
start "Frontend (Angular)" cmd /k "cd frontend && npm run start -- -o"

echo.
echo ========================================================
echo Startup Sequence Initiated!
echo ========================================================
echo Note: If this is the first time running on a new machine, 
echo ensure PostgreSQL is installed locally with a database 
echo named "sAir" running on port 5432 with default credentials.
echo.
pause
