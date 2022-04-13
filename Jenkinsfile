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
                sh 'mvn --batch-mode -V -U -e checkstyle:checkstyle'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn surefire-report:report'
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
                        docker.image("leonovets/gift_certificate:${TAG}").push("latest")
                    }
                }
            }
        }
    }
    post('Generate report') {
        always {
            script {
                recordIssues enabledForFailure: true, tool: checkStyle()

                junit '**/surefire-reports/*.xml'

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

                emailext subject: "Automation Result: CHECKSTYLE",
                        body: '''    
                        Total amount of warnings ${ANALYSIS_ISSUES_COUNT}
                           Amount of new warnings (since the last build i guess) ${ANALYSIS_ISSUES_COUNT, type="NEW"} ''',
                        to: '$DEFAULT_RECIPIENTS'
            }
        }
    }
}