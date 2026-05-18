pipeline {
    agent any
    tools {
        maven 'maven-3.9'
    }
    stages {
        stage("build jar") {
            steps {
                script {
                        echo "building the application"
                        sh 'mvn package'
                }
            }
        }
        stage("build image") {
            steps {
                script {
                        echo "building the docker image"
                        withCredentials ([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                            sh 'docker build -t karentrasporte/demo-app:1.2'
                            sh 'echo $PASS | docker login -u $USER --password-stdin'
                            sh 'docker push karentrasporte/demo-app:1.2'
                        }
                }
            }
        }
        stage("deploy") {
           steps {
                script {
                    echo "deploying the application"
                }
           }
        }
    }

}
