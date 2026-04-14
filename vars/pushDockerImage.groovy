def call(imageName, username, passwordVar) {
    sh """
    echo ${passwordVar} | docker login -u ${username} --password-stdin
    docker push ${imageName}
    """
}