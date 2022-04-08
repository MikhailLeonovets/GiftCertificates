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
        stage('Build') {
            steps {
                sh 'mvn package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '/surefire-reports/*.xml'
                }
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
                    docker.withRegistry('https://registry.hub.docker.com', 'docker') {
                        docker.image("leonovets/gift_certificate:${TAG}").push()
                        docker.image("leonovets/gift_certificate:${TAG}").push("latest")
                    }
                }
            }
        }
    }
}