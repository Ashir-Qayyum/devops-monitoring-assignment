// def call(user, server, key, image, release, chartPath) {
//     sh """
//     ssh -i ${key} ${user}@${server} "
//         export PATH=\\$PATH:/usr/local/bin
        

//         helm upgrade --install ${release} '${chartPath}' \
//         --set image.repository=${image}
//     "
//     """
// }

def call(user, server, key, image, release, chartPath) {
        sh """
        ssh -i ${key} ${user}@${server} "
            export PATH=\\$PATH:/usr/local/bin

            helm upgrade --install ${release} '${chartPath}' \
            --set image.repository=ashirqayyum/asgn-09-java-app \
            --set image.tag=latest
        "
        """
}