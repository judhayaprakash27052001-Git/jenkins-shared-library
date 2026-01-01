def call() {
    def imageTag = env.GIT_COMMIT

    withCredentials([
        usernamePassword(
            credentialsId: 'docker-hub-creds',
            usernameVariable: 'DOCKER_USER',
            passwordVariable: 'DOCKER_PASS'
        )
    ]) {
        sh """
          echo "\$DOCKER_PASS" | docker login -u "\$DOCKER_USER" --password-stdin
          docker build --no-cache -t udhaya2705/portfolio-app:${imageTag} .
          docker push udhaya2705/portfolio-app:${imageTag}
          docker logout
        """
    }
}
