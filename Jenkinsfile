pipeline {
    agent any
    tools {
        maven 'maven-3.9'
    }
    stages {
        stage("increment version") {
            steps {
                script {
                        echo "incrementing version"
                        sh 'mvn build-helper:parse-version versions:set \
                            -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                            versions:commit'
                        def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                        def version = matcher[0][1]
                        env.IMAGE_NAME = "$version-$BUILD_NUMBER"
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                        echo "building the application"
                        sh 'mvn clean package'
                }
            }
        }
        stage("build image") {
            steps {
                script {
                        echo "building the docker image"
                        withCredentials ([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                            sh "docker build -t karentrasporte/demo-app:${IMAGE_NAME} ."
                            sh 'echo $PASS | docker login -u $USER --password-stdin'
                            sh "docker push karentrasporte/demo-app:${IMAGE_NAME}"
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
        stage("commit version update") {
           steps {
                script {
                    withCredentials ([usernamePassword(credentialsId: 'f9a0a753-db4b-4c34-8441-0cdf9e3e6fd6', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        def encodedPass = URLEncoder.encode(PASS, "UTF-8")

                        sh 'git config --global user.email "test@test.com"'
                        sh 'git config --global user.name "Jenkins test"'

                        sh 'git status'
                        sh 'git branch'
                        sh 'git config --list'

                        sh "git remote set-url origin https://${USER}:${encodedPass}@github.com/twn-devops-bootcamp/twn08-jenkins-demo5.git"
                        sh 'git add .'
                        sh 'git commit -m "Jenkins version bump"'
                        sh 'git push origin HEAD:jenkins-jobs'
                    }
                }
           }
        }
    }

}