// def call(imageName, workspaceDir) {
//     sh """
//     cd ${workspaceDir}
//     docker build -t ${imageName} .
//     """
// }


def call(imageName, workspaceDir) {
    sh """
    cd ${workspaceDir}
    docker build -t ${imageName} .
    """
}