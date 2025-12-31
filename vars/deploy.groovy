def call(String env) {
    echo "Shared Library: Deploying to ${env}"
    bat "echo Deploying to ${env} from shared library"
}
