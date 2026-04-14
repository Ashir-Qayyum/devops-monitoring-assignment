def call(container, sonarHost, sonarToken) {
    sh """
    docker exec ${container} bash -c '
        cd /tmp/app &&
        ./gradlew sonarqube \
        -Dsonar.host.url=${sonarHost} \
        -Dsonar.token=${sonarToken}
    '
    """
}