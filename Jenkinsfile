def gv

pipeline {
    agent any
    parameters {
        choice(name: 'VERSION', choices: ['1.1.1','2.2.2','3.3.3'])
        booleanParam(name: 'executeTests', defaultValue: true, description:'')
    }

    stages {
        stage("init") {
            steps {
                script {
                        gv = load "script.groovy"
                    }
            }
        }


        stage("build") {
            steps {
                script {
                        gv.buildApp()
                    }
            }
        }


        stage("test") {
            when {
               expression {
                params.executeTests
                }
             }
            steps {
                script {
                        gv.testApp()
                    }
            }
        }

        stage("deploy") {
           steps {
                script {
                    env.ENV = input message: "Select environment to deploy to", ok: "Done", parameters: [choice(name: 'ENV1', choices: ['dev','stg','prod'], description: '')]
                    gv.deployApp()
                    echo "Deploying to ${ENV}"
                }
           }
        }
    }

}
