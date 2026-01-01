def call(Map config) {
    withCredentials([
        usernamePassword(
            credentialsId: config.dockerCreds,
            usernameVariable: 'DOCKER_USER',
            passwordVariable: 'DOCKER_PASS'
        )
    ]) {
        sh """
            echo \$DOCKER_PASS | docker login -u \$DOCKER_USER --password-stdin
            docker build --no-cache -t ${config.image}:${config.tag} .
            docker push ${config.image}:${config.tag}
            docker logout
        """
    }
}
