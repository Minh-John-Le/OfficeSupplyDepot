@echo off

echo Downloading Git Bash for Windows...

set DOWNLOAD_URL=https://github.com/git-for-windows/git/releases/download/v2.31.1.windows.1/Git-2.31.1-64-bit.exe
set DOWNLOAD_PATH=C:\Users\%USERNAME%\Downloads\Git-2.31.1-64-bit.exe

curl -L -o "%DOWNLOAD_PATH%" "%DOWNLOAD_URL%"

echo Download complete!
