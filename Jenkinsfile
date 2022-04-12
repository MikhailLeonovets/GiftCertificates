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
                sh 'mvn clean install -U'
            }
        }
        stage('Checkstyle') {
            steps {
                sh 'mvn checkstyle:checkstyle'
            }
        }
    }
    stage('Test') {
        steps {
            sh 'mvn test'
            junit '**/surefire-reports/*.xml'
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
post('Generate report') {
    always {
        script {
            cucumber fileIncludePattern: '**/cucumber-default-reports/*.json', sortingMethod: 'ALPHABETICAL'

            junit '**/cucumber-default-reports/*.xml' // Должен быть этот шаг, иначе нет данных
            emailext subject: "Automation Result5: Job '${env.JOB_NAME} - ${env.BUILD_NUMBER}'",
                    body: '''${SCRIPT,template="groovy-html-larry-refactor.template"}''',
                    to: '$DEFAULT_RECIPIENTS'

            emailext subject: "Automation Result6: Job '${env.JOB_NAME} - ${env.BUILD_NUMBER}'",
                    body: ''' ${SCRIPT,template="groovy-html-refactor.template"}''',
                    to: '$DEFAULT_RECIPIENTS'

            emailext subject: "Automation Result: Job '${env.JOB_NAME} - ${env.BUILD_NUMBER}'",
                    body: '''  
                      total:${TEST_COUNTS,var="total"},
                      pass:${TEST_COUNTS,var="pass"},
                      fail:${TEST_COUNTS,var="fail"}
                   ''',
                    to: '$DEFAULT_RECIPIENTS'

        }
    }
}