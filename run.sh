#!/bin/bash

# Phone Inventory Management System - Run Script
# This script compiles and runs the application

echo "================================================"
echo "Phone Inventory Management System"
echo "================================================"
echo ""

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "❌ Error: Java is not installed!"
    echo ""
    echo "Please install Java JDK 8 or higher."
    echo "See SETUP.md for installation instructions."
    echo ""
    echo "Quick install with Homebrew:"
    echo "  brew install openjdk@17"
    exit 1
fi

# Check if javac is installed
if ! command -v javac &> /dev/null; then
    echo "❌ Error: Java compiler (javac) is not installed!"
    echo ""
    echo "Please install Java JDK (not just JRE)."
    echo "See SETUP.md for installation instructions."
    exit 1
fi

# Display Java version
echo "✓ Java version:"
java -version 2>&1 | head -n 1
echo ""

# Check if class files exist
if [ ! -f "CreateLoginForm.class" ]; then
    echo "📦 Compiling Java files..."
    javac *.java
    
    if [ $? -eq 0 ]; then
        echo "✓ Compilation successful!"
        echo ""
    else
        echo "❌ Compilation failed!"
        echo "Please check for errors above."
        exit 1
    fi
else
    echo "✓ Class files found (already compiled)"
    echo ""
fi

# Run the application
echo "🚀 Starting Phone Inventory Management System..."
echo ""
java CreateLoginForm

# Check exit status
if [ $? -ne 0 ]; then
    echo ""
    echo "❌ Application exited with an error."
    exit 1
fi
