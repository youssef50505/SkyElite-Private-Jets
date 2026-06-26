@echo off
echo ========================================================
echo SkyElite Private Jets (Ascend Global) - Startup Script
echo ========================================================
echo.
echo Starting Spring Boot Backend...
start "Backend (Spring Boot)" cmd /k "cd backend && .\mvnw.cmd spring-boot:run"

echo Starting Angular Frontend...
start "Frontend (Angular)" cmd /k "cd frontend && npm run start -- -o"

echo.
echo The backend and frontend are now starting in separate windows.
echo The application will automatically open in your browser once the frontend compiles.
echo.
pause
