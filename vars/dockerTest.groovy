def call(Map config) {
    bat """
    docker rm -f ${config.container} || exit 0
    docker run -d --name ${config.container} -p ${config.port}:80 ${config.image}:${config.tag}
    timeout /t 5
    docker rm -f ${config.container}
    """
}
