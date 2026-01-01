def call(Map config) {
    sh """
        docker rm -f ${config.testContainer} || true
        docker run -d --name ${config.testContainer} -p ${config.testPort}:80 ${config.image}:${config.tag}
        sleep 5
        docker rm -f ${config.testContainer}
    """
}
