def call(imageName, workspaceDir) {
    sh """
    cd ${workspaceDir}
    docker build -t ${imageName} .
    """
}