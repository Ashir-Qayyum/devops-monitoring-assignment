
def call(imageName, workspaceDir) {
    sh """
    docker build -t ${imageName} -f Dockerfile .
    """
}