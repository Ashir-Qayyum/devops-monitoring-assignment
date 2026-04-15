// def call(imageName, workspaceDir) {
//     sh """
//     cd ${workspaceDir}
//     docker build -t ${imageName} .
//     """
// }


def call(imageName, workspaceDir) {
    sh """
    docker build -t ${imageName} -f ${workspaceDir}/Dockerfile ${workspaceDir}
    """
}