
def call(sonarHost, sonarToken, projectKey) {
    sh """
    sleep 20

    RESPONSE=\$(curl -s -u ${sonarToken}: \
    ${sonarHost}/api/qualitygates/project_status?projectKey=${projectKey})

    echo "Sonar Response: \$RESPONSE"

    echo "\$RESPONSE" | grep '"status":"OK"' && {
        echo "Quality Gate PASSED"
        exit 0
    }

    echo "Quality Gate NOT OK or NOT READY"
    exit 1
    """
}