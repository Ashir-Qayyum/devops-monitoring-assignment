// def call(sonarHost, sonarToken, projectKey) {
//     sh """
//     sleep 60

//     STATUS=\$(curl -s -u ${sonarToken}: \
//     ${sonarHost}/api/qualitygates/project_status?projectKey=${projectKey} \
//     | grep -o '"status":"OK"')

//     echo "Quality Gate: \$STATUS"

//     if [ -z "\$STATUS" ]; then
//         echo "Quality Gate Failed"
//         exit 1
//     fi
//     """
// }

// def call(sonarHost, sonarToken, projectKey) {
//     sh """
//     sleep 60

//     STATUS=\$(curl -s -u ${sonarToken}: \
//     ${sonarHost}/api/qualitygates/project_status?projectKey=${projectKey})

//     echo "\$STATUS" | grep '"status":"OK"' || {
//         echo "Quality Gate Failed"
//         exit 1
//     }
//     """
// }

def call(sonarHost, sonarToken, projectKey) {
    sh """
    sleep 60

    RESPONSE=\$(curl -s -u ${sonarToken}: \
    ${sonarHost}/api/qualitygates/project_status?projectKey=${projectKey})

    echo "Sonar Response: \$RESPONSE"

    if echo "\$RESPONSE" | grep '"status":"OK"' > /dev/null; then
        echo "Quality Gate Passed"
        exit 0
    else
        echo "Quality Gate Failed or not ready"
        exit 1
    fi
    """
}