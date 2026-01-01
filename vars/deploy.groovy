def call(String appName, String env) {
    withCredentials([usernamePassword(
        credentialsId: 'dockerhub-creds',
        usernameVariable: 'DOCKER_USER',
        passwordVariable: 'DOCKER_PASS'
    )]) {
        bat """
        echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin
        docker rm -f ${appName}-prod || echo No running container
        docker run -d --name ${appName}-prod -p 8080:80 %DOCKER_USER%/${appName}:latest
        docker logout
        """
    }
}
