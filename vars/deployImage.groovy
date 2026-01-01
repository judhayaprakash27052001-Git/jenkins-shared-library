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
          docker rm -f portfolio-app-prod || true
          docker pull udhaya2705/portfolio-app:${imageTag}
          docker run -d --name portfolio-app-prod -p 8080:80 udhaya2705/portfolio-app:${imageTag}
          docker logout
        """
    }
}
