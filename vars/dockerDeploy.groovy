def call(Map config) {
    withCredentials([
        usernamePassword(
            credentialsId: config.dockerCreds,
            usernameVariable: 'DOCKER_USER',
            passwordVariable: 'DOCKER_PASS'
        )
    ]) {
        bat """
        echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin
        docker rm -f ${config.container} || exit 0
        docker run -d --name ${config.container} -p ${config.port}:80 ${config.image}:${config.tag}
        docker logout
        """
    }
}
