def call() {
    def imageTag = env.GIT_COMMIT

    sh """
      docker rm -f portfolio-app-test || true
      docker run -d --name portfolio-app-test -p 8085:80 udhaya2705/portfolio-app:${imageTag}
      sleep 5
      docker rm -f portfolio-app-test
    """
}
