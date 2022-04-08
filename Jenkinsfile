pipeline {
    agent any
    tools {
        maven 'maven-3.8.4'
    }
    environment {
        DATE = new Date().format('yy.M')
        TAG = "${DATE}.${BUILD_NUMBER}"
    }
    stages {
        stage('Cloning git') {
            steps {
                // Clones the repository from the current branch name
                echo 'Make the output directory'
                sh 'mkdir -p build'

                echo 'Cloning files from (branch: "' + 'master' + '" )'
                dir('build') {
                    git branch: 'master', credentialsId: 'git-hub',
                            url: 'https://github.com/MikhailLeonovets/GiftCertificates.git'
                }
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Docker Build') {
            steps {
                script {
                    docker.build("leonovets/gift_certificate:${TAG}")
                }
            }
        }
        stage('Pushing Docker Image to Dockerhub') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'docker_credential') {
                        docker.image("leonovets/gift_certificate:${TAG}").push()
                        docker.image("leonovets/gift_certificate:${TAG}").push("latest")
                    }
                }
            }
        }
    }
}