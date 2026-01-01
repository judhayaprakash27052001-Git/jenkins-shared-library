def call(String appName) {
    echo "Testing Docker container for ${appName}"

    bat """
    docker run -d --name ${appName}-test -p 8085:80 ${appName}:latest
    timeout /t 5
    docker ps | findstr ${appName}-test
    docker rm -f ${appName}-test
    """
}
