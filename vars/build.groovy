def call(String appName) {
    echo "Building Docker image for ${appName}"

    withCredentials([usernamePassword(
        credentialsId: 'dockerhub-creds',
        usernameVariable: 'DOCKER_USER',
        passwordVariable: 'DOCKER_PASS'
    )]) {
        bat """
        echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin
        docker build --no-cache -t %DOCKER_USER%/${appName}:latest .
        docker logout
        """
    }
}

