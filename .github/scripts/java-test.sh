#!/bin/bash
# Directorios del proyecto
PROJECT_ROOT="src"
CORE_SRC="$PROJECT_ROOT/core/src/main/java"
TEST_SRC="$PROJECT_ROOT/core/src/test/java"
LIB_DIR="testing-lib"
BUILD_DIR="testing-build"
# Versiones de las dependencias
JUNIT_VERSION="5.8.1"
MOCKITO_VERSION="4.2.0"
LIBGDX_VERSION="1.12.1"
# Crear directorios necesarios
mkdir -p $LIB_DIR
mkdir -p $BUILD_DIR
# Descargar dependencias si no existen
if [ ! -f "$LIB_DIR/junit-platform-console-standalone-$JUNIT_VERSION.jar" ]; then
    echo "Downloading JUnit dependencies..."
    wget -P $LIB_DIR "https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/$JUNIT_VERSION/junit-platform-console-standalone-$JUNIT_VERSION.jar"
fi
if [ ! -f "$LIB_DIR/mockito-core-$MOCKITO_VERSION.jar" ]; then
    echo "Downloading Mockito dependencies..."
    wget -P $LIB_DIR "https://repo1.maven.org/maven2/org/mockito/mockito-core/$MOCKITO_VERSION/mockito-core-$MOCKITO_VERSION.jar"
    wget -P $LIB_DIR "https://repo1.maven.org/maven2/net/bytebuddy/byte-buddy/$MOCKITO_VERSION/byte-buddy-$MOCKITO_VERSION.jar"
    wget -P $LIB_DIR "https://repo1.maven.org/maven2/net/bytebuddy/byte-buddy-agent/$MOCKITO_VERSION/byte-buddy-agent-$MOCKITO_VERSION.jar"
    wget -P $LIB_DIR "https://repo1.maven.org/maven2/org/objenesis/objenesis/3.2/objenesis-3.2.jar"
fi
# Descargar dependencias de LibGDX
if [ ! -f "$LIB_DIR/gdx-$LIBGDX_VERSION.jar" ]; then
    echo "Downloading LibGDX dependencies..."
    wget -P $LIB_DIR "https://repo.maven.apache.org/maven2/com/badlogicgames/gdx/gdx/$LIBGDX_VERSION/gdx-$LIBGDX_VERSION.jar"
    wget -P $LIB_DIR "https://repo.maven.apache.org/maven2/com/badlogicgames/gdx/gdx-backend-headless/$LIBGDX_VERSION/gdx-backend-headless-$LIBGDX_VERSION.jar"
    wget -P $LIB_DIR "https://repo.maven.apache.org/maven2/com/badlogicgames/gdx/gdx-platform/$LIBGDX_VERSION/gdx-platform-$LIBGDX_VERSION-natives-desktop.jar"
fi
# Construir classpath
CLASSPATH="$LIB_DIR/*:$BUILD_DIR"
# Compilar código fuente principal
echo "Compiling main sources..."
find $CORE_SRC -name "*.java" > sources.txt
javac -d $BUILD_DIR -cp "$CLASSPATH" @sources.txt
# Compilar tests
echo "Compiling tests..."
find $TEST_SRC -name "*.java" > tests.txt
javac -d $BUILD_DIR -cp "$CLASSPATH" @tests.txt
# Ejecutar tests
echo "Running tests..."
java -jar "$LIB_DIR/junit-platform-console-standalone-$JUNIT_VERSION.jar" \
     --class-path "$CLASSPATH" \
     --scan-class-path \
     --reports-dir="test-results"
# Limpiar archivos temporales
rm sources.txt tests.txt
# Verificar resultado
EXIT_CODE=$?
if [ $EXIT_CODE -eq 0 ]; then
    echo "Tests completed successfully"
else
    echo "Tests failed with exit code $EXIT_CODE"
    exit $EXIT_CODE
fi