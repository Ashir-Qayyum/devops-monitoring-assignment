def call(container, sourcePath, targetPath) {
    sh """
    docker cp ${container}:${sourcePath} ${targetPath}
    """
}