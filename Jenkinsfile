def gv

pipeline {
    agent any
    parameters {
        choice(name: 'VERSION', choices: ['1.1.1','2.2.2','3.3.3'])
        booleanParam(name: 'executeTests', defaultValue: true, description:'')
    }

    stages {
        stage("init") {
            script {
                    gv = load "script.groovy"
                }
        }

        
        stage("build") {
            script {
                    gv.buildApp()
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
                    gv.deployApp()
                }
           }
        }
    }
    
}
