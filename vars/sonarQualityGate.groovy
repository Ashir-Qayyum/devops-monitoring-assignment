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

def call(sonarHost, sonarToken, projectKey) {
    sh """
    sleep 60

    STATUS=\$(curl -s -u ${sonarToken}: \
    ${sonarHost}/api/qualitygates/project_status?projectKey=${projectKey})

    echo "\$STATUS" | grep '"status":"OK"' || {
        echo "Quality Gate Failed"
        exit 1
    }
    """
}