def suites = "None\n" +
        "DefinitionTestSuite\n"
//        "DefinitionTestSuite_Regression\n"
//        "DefinitionTestSuiteCheckout\n" +
//        "DefinitionTestSuiteMyPrescription\n" +
//        "DefinitionTestSuitePDP\n" +
//        "DefinitionTestSuiteProductsPage\n" +
//        "DefinitionTestSuiteShoppingCart\n"

def baseurls = "https://staging.ict4apps.aimprosoft.com\n" +
        "https://dev.ecom.ict4apps.aimprosoft.com\n"

def baselanguage = " русский \n" +
        " українська \n"

pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        disableConcurrentBuilds()
    }
    parameters {
        choice(name: 'url', choices: "${baseurls}", description: 'select url')
        choice(name: 'tc_one', choices: "${suites}", description: 'Test suite 1')
        choice(name: 'language', choices: "${baselanguage}", description: 'select language')
//        choice(name: 'tc_two', choices: "${suites}", description: 'Test suite 2')
//        choice(name: 'tc_three', choices: "${suites}", description: 'Test suite 3')
//        choice(name: 'tc_four', choices: "${suites}", description: 'Test suite 4')
    }
    environment {
        MVN_GOAL = "clean verify"
        CHOICE_SUITE = "-Dit.test="
    }
    stages {
        stage('Tests execution') {
            parallel {
                stage('Execution of Test Suite 1') {
                    when {
                        expression { return params.tc_one != 'None' }
                    }
                    agent any
                    steps {
                        wrap([$class: 'Xvfb', screen: '1920x1080x16', autoDisplayName: true]) {
                            sh "${M2_HOME}/bin/mvn ${env.MVN_GOAL} ${env.CHOICE_SUITE}${params.tc_one}"
                        }
                    }
                    post {
                        always {
                            publishHTML(target: [
//                                    reportName           : 'Serenity Test Suite 1',
reportName           : params.tc_one,
reportDir            : 'target/site/serenity',
reportFiles          : 'index.html',
keepAll              : true,
alwaysLinkToLastBuild: true,
allowMissing         : false
                            ])
                        }
                    }
                }
//                stage('Execution of Test Suite 2') {
//                    when {
//                        expression { return params.tc_two != 'None' }
//                    }
//                    agent any
//                    steps {
//                        wrap([$class: 'Xvfb', screen: '1920x1080x16', autoDisplayName: true]) {
//                            sh "${M2_HOME}/bin/mvn ${env.MVN_GOAL} ${env.CHOICE_SUITE}${params.tc_two}"
//                        }
//                    }
//                    post {
//                        always {
//                            publishHTML(target: [
////                                    reportName           : 'Serenity Test Suite 2',
//reportName           : params.tc_two,
//reportDir            : 'target/site/serenity',
//reportFiles          : 'index.html',
//keepAll              : true,
//alwaysLinkToLastBuild: true,
//allowMissing         : false
//                            ])
//                        }
//                    }
//                }
//                stage('Execution of Test Suite 3') {
//                    when {
//                        expression { return params.tc_three != 'None' }
//                    }
//                    agent any
//                    steps {
//                        wrap([$class: 'Xvfb', screen: '1920x1080x16', autoDisplayName: true]) {
//                            sh "${M2_HOME}/bin/mvn ${env.MVN_GOAL} ${env.CHOICE_SUITE}${params.tc_three}"
//                        }
//                    }
//                    post {
//                        always {
//                            publishHTML(target: [
////                                    reportName           : 'Serenity Test Suite 3',
//reportName           : params.tc_three,
//reportDir            : 'target/site/serenity',
//reportFiles          : 'index.html',
//keepAll              : true,
//alwaysLinkToLastBuild: true,
//allowMissing         : false
//                            ])
//                        }
//                    }
//                }
//                stage('Execution of Test Suite 4') {
//                    when {
//                        expression { return params.tc_four != 'None' }
//                    }
//                    agent any
//                    steps {
//                        wrap([$class: 'Xvfb', screen: '1920x1080x16', autoDisplayName: true]) {
//                            sh "${M2_HOME}/bin/mvn ${env.MVN_GOAL} ${env.CHOICE_SUITE}${params.tc_four}"
//                        }
//                    }
//                    post {
//                        always {
//                            publishHTML(target: [
////                                    reportName           : 'Serenity Test Suite 4',
//reportName           : params.tc_four,
//reportDir            : 'target/site/serenity',
//reportFiles          : 'index.html',
//keepAll              : true,
//alwaysLinkToLastBuild: true,
//allowMissing         : false
//                            ])
//                        }
//                    }
//                }
            }
        }
    }
}