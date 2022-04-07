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
        stage("verify tooling") {
            steps {
            sh '''
                docker version
                docker info
                docker compose version
                '''
            }
        }
        '''
        stage ('Cloning git') {
            steps {
                git([url: 'https://github.com/MikhailLeonovets/GiftCertificates.git', branch: 'master',
                credentialsId: 'git-hub'])
            }
        }
        '''
        stage ('Build and Run') {
            steps {
                sh 'docker compose up -d --no-color --wait'
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