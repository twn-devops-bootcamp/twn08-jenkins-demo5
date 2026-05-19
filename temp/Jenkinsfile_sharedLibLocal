#!/user/bin/env groovy

library identifier: 'jenkins-shared-library@master', retriever: modernSCM(
    [
        $class: 'GitSCMSource',
        remote:'https://github.com/twn-devops-bootcamp/twn08-jenkins-demo14-shared-library.git',
        credentialsId: 'f9a0a753-db4b-4c34-8441-0cdf9e3e6fd6'
    ]
)

def gv

pipeline {
    agent any
    tools {
        maven 'maven-3.9'
    }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                    buildJar()
                }
            }
        }
        stage("build and push image") {
            steps {
                script {
                    buildImage 'karentrasporte/demo-app:jma-3.0'
                    dockerLogin()
                    dockerPush 'karentrasporte/demo-app:jma-3.0'
                }
            }
        }
        stage("deploy") {
           steps {
                script {
                    gv.deployApp()
                }
           }
        }
    }
}
