// def call(container, sonarHost, sonarToken) {
//     sh """
//     docker exec ${container} bash -c '
//         cd /tmp/app/app &&
//         ./gradlew sonarqube \
//         -Dsonar.host.url=${sonarHost} \
//         -Dsonar.token=${sonarToken}
//     '
//     """
// }

def call(container, host, token, projectKey) {
    sh """
    docker exec ${container} bash -c '
    cd /tmp/app/app &&
    ./gradlew sonarqube \
      -Dsonar.host.url=${host} \
      -Dsonar.login=${token} \
      -Dsonar.projectKey=${projectKey} \
      -Dsonar.projectName=${projectKey}
    '
    """
}

