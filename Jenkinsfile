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
        stage('Test') {
            steps {
                sh 'mvn test'
                junit '**/surefire-reports/*.xml'
            }
            post {
                always {
                    mail to: "enchantment.com@gmail.com",
                    subject: "Test Email",
                    body: "Test"
                }
            }
        }
        stage('Build') {
            steps {
                sh 'mvn package'
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