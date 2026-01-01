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

            docker stop ${config.container} || true
            docker rm ${config.container} || true

            docker pull ${config.image}:${config.tag}

            docker run -d \
              --name ${config.container} \
              -p ${config.port}:80 \
              ${config.image}:${config.tag}

            docker logout
        """
    }
}
