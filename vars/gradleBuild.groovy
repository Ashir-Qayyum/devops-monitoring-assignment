def call(repoUrl, container) {
    sh """
    docker exec ${container} bash -c '
        rm -rf /tmp/app &&
        git clone ${repoUrl} /tmp/app &&
        cd /tmp/app &&
        ./gradlew clean build
    '
    """
}