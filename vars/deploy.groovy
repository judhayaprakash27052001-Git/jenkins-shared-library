def call(String appName, String env) {
    echo "Deploying ${appName} to ${env}"

    bat """
    docker rm -f ${appName}-prod || echo No running container
    docker run -d --name ${appName}-prod -p 8080:80 ${appName}:latest
    """
}
